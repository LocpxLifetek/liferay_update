package listResolutionGoverment.portlet;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import dto.JournalArticleDto;
import dto.JournalArticleIdAndValue;
import dto.NodeModel;
import listResolutionGoverment.constants.ListResolutionGovermentPortletKeys;

/**
 * @author Dell
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=ListResolutionGoverment", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ListResolutionGovermentPortletKeys.LISTRESOLUTIONGOVERMENT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class ListResolutionGovermentPortlet extends MVCPortlet {
	

	private List<JournalArticleDto> findAllJournalArticleByMaxVersion(int page, int size,long groupId) throws Exception {
		PreparedStatement statement=null;
		Connection con = null;
		ResultSet rs=null;
		try {
			List<JournalArticleDto> listJournalArticle = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT ja.resourceprimkey    AS resourceprimkey,\r\n" + 
					"                MAX(ja.version)       AS version, Max(ja.id_)as id FROM\r\n" + 
					"                journalarticle ja\r\n" + 
					"            INNER JOIN assetentry                 ae ON ja.resourceprimkey = ae.classpk\r\n" + 
					"            INNER JOIN assetentryassetcategoryrel  aeac ON aeac.assetentryid = ae.entryid\r\n" + 
					"            INNER JOIN assetcategory               ac ON ac.categoryid = aeac.assetcategoryid\r\n" + 
					"            INNER JOIN ddmstructure                ddm ON ddm.structurekey = ja.ddmstructurekey\r\n" + 
					"            WHERE ja.folderid = '189126' AND ja.ddmstructurekey='189235' and ac.groupid=? and upper(REGEXP_REPLACE(ac.name,'[^a-z_A-Z ]')) = upper('Nghi quyt cua Chinh phu')\r\n" + 
					"            GROUP BY ja.resourceprimkey order by max(ja.id_) desc offset (?-1)*? rows fetch next ? rows only");
			statement.setLong(1, groupId);
			statement.setInt(2, page);
			statement.setInt(3, size);
			statement.setInt(4, size);
			rs = statement.executeQuery();
			while (rs.next()) {
				JournalArticleDto journalArticleDto = new JournalArticleDto();
				Integer resourceprimkey = rs.getInt("resourceprimkey");
				Integer maxVersion = rs.getInt("version");
				journalArticleDto.setResourcePrimkey(resourceprimkey);
				journalArticleDto.setMaxVersion(maxVersion);
				listJournalArticle.add(journalArticleDto);
			}
			return listJournalArticle;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					/* Ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					/* Ignored */}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					/* Ignored */}
			}
		}
	}

	private Integer countJournalArticleByFolderResolutionGoverment() throws Exception {
		List<JournalArticleDto> listJournalArticle = new ArrayList<>();
		PreparedStatement statement=null;
		Connection con = null;
		ResultSet rs=null;
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT\r\n" + "    ja.resourceprimkey    AS resourceprimkey,\r\n"
					+ "    MAX(ja.version)       AS version,\r\n" + "    Max(ja.id_)as id \r\n" + "FROM\r\n"
					+ "         journalarticle ja\r\n"
					+ "    INNER JOIN assetentry                  ae ON ja.resourceprimkey = ae.classpk\r\n"
					+ "    INNER JOIN assetentryassetcategoryrel  aeac ON aeac.assetentryid = ae.entryid\r\n"
					+ "    INNER JOIN assetcategory               ac ON ac.categoryid = aeac.assetcategoryid\r\n"
					+ "    INNER JOIN ddmstructure                ddm ON ddm.structurekey = ja.ddmstructurekey\r\n"
					+ "WHERE\r\n" + "        ja.folderid = '189126'\r\n" + "    AND ja.ddmstructurekey='189235'\r\n"
					+ "GROUP BY\r\n" + "    ja.resourceprimkey order by max(ja.id_) desc");
			rs = statement.executeQuery();
			while (rs.next()) {
				JournalArticleDto journalArticleDto = new JournalArticleDto();
				Integer resourceprimkey = rs.getInt("resourceprimkey");
				Integer maxVersion = rs.getInt("version");
				journalArticleDto.setResourcePrimkey(resourceprimkey);
				journalArticleDto.setMaxVersion(maxVersion);
				listJournalArticle.add(journalArticleDto);
			}
			return listJournalArticle.size();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					/* Ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					/* Ignored */}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					/* Ignored */}
			}
		}

	}

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
			// Fetch the child node
			fetchNode(jsonObject, childNode, langId);
		}
		return jsonArray;
	}

	private static void fetchNode(JSONObject jsonObject, Node childNode, String langId) {
		List<Node> nodes = childNode.selectNodes("dynamic-element");
		List<String> nodeList = new ArrayList<>();
		for (Node node : nodes) {
			NodeModel nodeModel = convertToNodeMap(node, langId);
			// Checking if same node is existing multiple times on same level, If yes then
			// we have to consider it as array.
			if (!nodeList.contains(nodeModel.getNodeName())) {
				nodeList.add(nodeModel.getNodeName());
				List<Node> childNodeList = childNode
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

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String pageDetail = PortalUtil.getOriginalServletRequest(request).getParameter("page");
			int count = countJournalArticleByFolderResolutionGoverment();
			int page = Integer.parseInt(pageDetail == null ? "1" : pageDetail);
			int size = 10;
			int result = (int) Math.ceil((float) count / size);

			List<Map<String, List<JournalArticleIdAndValue>>> mapList = new ArrayList<>();
			List<JournalArticleDto> listJournalArticleDtos = findAllJournalArticleByMaxVersion(page, size,themeDisplay.getScopeGroupId());
			for (JournalArticleDto journalArticleDto : listJournalArticleDtos) {
				Map<String, List<JournalArticleIdAndValue>> map = new LinkedHashMap<>();
				List<JournalArticleIdAndValue> listJournalArticleIdAndValues = new ArrayList<>();

				JournalArticle journalArticleObject = JournalArticleLocalServiceUtil
						.getLatestArticle(journalArticleDto.getResourcePrimkey());
				if (journalArticleObject.getStatus() == 0) {

					long journalArticleId = journalArticleObject.getId();

					JSONObject json = parseContent(journalArticleObject.getContent(),
							journalArticleObject.getDefaultLanguageId());
					Iterator<String> keys = json.keySet().iterator();
					while (keys.hasNext()) {
						JournalArticleIdAndValue journalArticleIdAndValue = new JournalArticleIdAndValue();
						String key = keys.next();
						String removeKeyUtf8 = key.replaceAll("[^\\p{ASCII}]", "");
						if (removeKeyUtf8.equals("NgyBanHnh") || removeKeyUtf8.equals("TrchYu")
								|| removeKeyUtf8.equals("SKHiu")) {
							String value = json.get(key).toString();
							journalArticleIdAndValue.setId(journalArticleId);
							journalArticleIdAndValue.setKey(key);
							if (removeKeyUtf8.equals("NgyBanHnh")) {
								String[] parts = value.split("-");
								String date = parts[2] +"/"+ parts[1] +"/"+ parts[0];
								journalArticleIdAndValue.setValue(date);
							} else {
								journalArticleIdAndValue.setValue(value);
							}
							listJournalArticleIdAndValues.add(journalArticleIdAndValue);

						}
					}
					map.put("1", listJournalArticleIdAndValues);
					mapList.add(map);
				}
			}

			renderRequest.setAttribute("maps", mapList);
			renderRequest.setAttribute("currentPage", page);
			renderRequest.setAttribute("totalPage", result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}

}