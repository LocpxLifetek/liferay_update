package ResolutionGoverment.portlet;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleLocalization;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import ResolutionGoverment.constants.ResolutionGovermentPortletKeys;
import dto.JournalArticleDto;
import dto.JournalArticleLocazationDto;

/**
 * @author Dell
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=ResolutionGoverment", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ResolutionGovermentPortletKeys.RESOLUTIONGOVERMENT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class ResolutionGovermentPortlet extends MVCPortlet {
	private PreparedStatement statement;
	Connection con = null;

	private static NodeModel convertToNodeMap(Node node, String langId) {
		Node nodeValue = node.selectSingleNode("dynamic-content[@language-id='" + langId + "']");
		String nodeKey = nodeValue.getParent().attribute("name").getStringValue();
		NodeModel nodeModel = new NodeModel();
		nodeModel.setNodeName(nodeKey);
		nodeModel.setNodeValue(nodeValue.getStringValue());
		return nodeModel;
	}

	private static JSONArray fetchChildNodeArray(List<Node> childNodeList, String langId) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		for (Node childNode : childNodeList) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			NodeModel childNodeModel = convertToNodeMap(childNode, langId);
			jsonObject.put(childNodeModel.getNodeName(), childNodeModel.getNodeValue());
			jsonArray.put(jsonObject);
			// Fetch the children node
			fetchNode(jsonObject, childNode, langId);
		}
		return jsonArray;
	}

	private static JSONObject fetchChildNodeObject(List<Node> childNodeList, String langId) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		for (Node childNode : childNodeList) {
			NodeModel childNodeModel = convertToNodeMap(childNode, langId);
			jsonObject.put(childNodeModel.getNodeName(), childNodeModel.getNodeValue());
			// Fetch the children node
			fetchNode(jsonObject, childNode, langId);
		}
		return jsonObject;
	}

	private static void fetchNode(JSONObject jsonObject, Node prentNode, String langId) {
		List<Node> nodes = prentNode.selectNodes("dynamic-element");
		List<String> nodeList = new ArrayList<>();
		for (Node node : nodes) {
			NodeModel nodeModel = convertToNodeMap(node, langId);
			// Checking if same node is existing multiple times on same level, If yes then
			// we have to consider it as array.
			if (!nodeList.contains(nodeModel.getNodeName())) {
				nodeList.add(nodeModel.getNodeName());
				List<Node> childNodeList = prentNode
						.selectNodes("dynamic-element[@name='" + nodeModel.getNodeName() + "']");
				// if we are getting more than 1 time same node name on same level, it means it
				// is repeatable field. So we need to treat it as array.
				if (childNodeList.size() > 1) {
					JSONArray jsonArray = fetchChildNodeArray(childNodeList, langId);
					jsonObject.put(nodeModel.getNodeName(), jsonArray);
				} else {
					List<Node> childNodeWithSingleEntryList = node.selectNodes("dynamic-element");
					// Checking if node has child
					if (!childNodeWithSingleEntryList.isEmpty()) {
						// if node has children
						jsonObject.put(nodeModel.getNodeName(), fetchChildNodeObject(childNodeList, langId));
					} else {
						// if node hasn't children
						jsonObject.put(nodeModel.getNodeName(), nodeModel.getNodeValue());
					}
				}
			}
		}
	}

	public static JSONObject parseContent(String contentXML, String langId) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		try {
			Document doc = SAXReaderUtil.read(contentXML);
			Node root = doc.getRootElement();
			// fetch the nodes of xml
			fetchNode(jsonObject, root, langId);
		} catch (DocumentException e) {
			jsonObject.put("Content", "There are no matching contents.");
		}
		return jsonObject;
	}

	private List<JournalArticleDto> findAllJournalArticleAndDontId(Long idJournalArticle) throws SQLException {
		try {
			List<JournalArticleDto> listJournalArticle = new ArrayList<>();

			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT ja.resourceprimkey    AS resourceprimkey,\r\n"
					+ "                MAX(ja.version)       AS version, Max(ja.id_)as id FROM\r\n"
					+ "                journalarticle ja\r\n"
					+ "            INNER JOIN assetentry                 ae ON ja.resourceprimkey = ae.classpk\r\n"
					+ "            INNER JOIN assetentryassetcategoryrel  aeac ON aeac.assetentryid = ae.entryid\r\n"
					+ "            INNER JOIN assetcategory               ac ON ac.categoryid = aeac.assetcategoryid\r\n"
					+ "            INNER JOIN ddmstructure                ddm ON ddm.structurekey = ja.ddmstructurekey\r\n"
					+ "            WHERE ja.folderid = '189126' AND ja.ddmstructurekey='189235' and ja.id_!=?\r\n"
					+ "            GROUP BY ja.resourceprimkey order by max(ja.id_) desc offset 0 rows fetch next 8 rows only");
			statement.setLong(1, idJournalArticle);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				JournalArticleDto journalArticleDto = new JournalArticleDto();
				Integer resourceprimkey = rs.getInt("resourceprimkey");
				Integer maxVersion = rs.getInt("version");
				Integer id = rs.getInt("id");
				journalArticleDto.setResourcePrimkey(resourceprimkey);
				journalArticleDto.setMaxVersion(maxVersion);
				journalArticleDto.setId(id);
				listJournalArticle.add(journalArticleDto);
			}
			return listJournalArticle;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return null;
		} finally {
			statement.close();
			con.close();
		}

	}

	private JournalArticleLocazationDto findAllJornalArticleByArticlePK(long articlePk, long id) {
		try {
			con = DataAccess.getConnection();
			statement = con
					.prepareStatement(" select ja.title from journalarticlelocalization ja where ja.articlePk=?");
			statement.setLong(1, articlePk);
			ResultSet rs = statement.executeQuery();
			JournalArticleLocazationDto journalArticleLocazationDto = new JournalArticleLocazationDto();
			while (rs.next()) {
				String title = rs.getString("title");
				journalArticleLocazationDto.setTitle(title);
				journalArticleLocazationDto.setId(id);
			}
			return journalArticleLocazationDto;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		try {
			Map<String, String> map = new HashMap<>();
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String id = PortalUtil.getOriginalServletRequest(request).getParameter("class_id");
			long idJournalArticle = Long.parseLong(id);
			JournalArticle journalArticleObject = JournalArticleLocalServiceUtil.getArticle(idJournalArticle);
			JSONObject json = parseContent(journalArticleObject.getContent(),
					journalArticleObject.getDefaultLanguageId());
			Iterator<String> keys = json.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				String removeKeyUtf8 = key.replaceAll("[^\\p{ASCII}]", "");
				String valueGoverment = null;
				String value = json.get(key).toString();
				String keyValue=null;
				int upper=0;
				if (value != null && value.isEmpty() == false) {
					for (int i = 0; i < key.length(); i++) {
						for (int j = i+1; j <= i+1; j++) {
							if ((key.charAt(i) >= 'A' && key.charAt(i) <= 'Z') && (key.charAt(j) >= 'A' && key.charAt(j) <='Z')) {
								upper++;
								
							}
						}
					}
					if(upper == 0) {
						keyValue = Character.toUpperCase(key.charAt(0))
								+ key.substring(1).replaceAll("(?<!_)(?=[A-Z])", " ");						
					}else {
						keyValue=key;
					}
					if (removeKeyUtf8.equals("NgyBanHnh")) {
						String[] parts = value.split("-");
						valueGoverment = parts[2] + "/" + parts[1] + "/" + parts[0];
					} else {
						valueGoverment = value;
					}
					map.put(keyValue, valueGoverment);
				}
			}
			// lấy thông tin trừ id ở trên
			try {
				List<JournalArticleDto> listJournalArticleDtos = findAllJournalArticleAndDontId(idJournalArticle);
				List<JournalArticleLocazationDto> listJournalArticleLocazationDto = new ArrayList<>();
				for (JournalArticleDto journalArticleDto : listJournalArticleDtos) {
					JournalArticle journalArticle = JournalArticleLocalServiceUtil
							.getArticle(journalArticleDto.getId());
					JournalArticleLocazationDto journalArticleLocazationDto = findAllJornalArticleByArticlePK(
							journalArticle.getId(), journalArticle.getId());
					listJournalArticleLocazationDto.add(journalArticleLocazationDto);
				}
				renderRequest.setAttribute("listJournalArticleLocazationDto", listJournalArticleLocazationDto);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			renderRequest.setAttribute("map", map);

		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}

}