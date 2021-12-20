package linkGoverment.portlet;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
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
import java.util.Iterator;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import linkGoverment.constants.LinkGovermentPortletKeys;
import linkGoverment.dto.DlFileEntryDto;
import linkGoverment.dto.JournalAricleDto;

/**
 * @author Dell
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=LinkGoverment", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + LinkGovermentPortletKeys.LINKGOVERMENT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class LinkGovermentPortlet extends MVCPortlet {
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

	private List<JournalAricleDto> findJournalArticle(long groupId) throws SQLException {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<JournalAricleDto> listJouranlArticleDtos = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT ja.resourceprimkey    AS resourceprimkey,\r\n"
					+ "					MAX(ja.version)       AS version FROM\r\n"
					+ "					journalarticle ja\r\n"
					+ "					INNER JOIN assetentry                 ae ON ja.resourceprimkey = ae.classpk\r\n"
					+ "					INNER JOIN assetentryassetcategoryrel  aeac ON aeac.assetentryid = ae.entryid\r\n"
					+ "					INNER JOIN assetcategory               ac ON ac.categoryid = aeac.assetcategoryid\r\n"
					+ "					INNER JOIN ddmstructure                ddm ON ddm.structurekey = ja.ddmstructurekey\r\n"
					+ "                    inner join journalFolder jf on jf.folderid=ja.folderid\r\n"
					+ "					 WHERE upper(REGEXP_REPLACE(jf.name,'[^a-z_A-Z ]')) = upper('LIN KT') AND upper(REGEXP_REPLACE(ac.name,'[^a-z_A-Z ]'))=upper('LIN KT') and ac.groupid=?\r\n"
					+ "					GROUP BY ja.resourceprimkey ");
			statement.setLong(1, groupId);
			rs = statement.executeQuery();
			while (rs.next()) {
				JournalAricleDto journalAricleDto = new JournalAricleDto();
				long resourceprimkey = rs.getLong("resourceprimkey");
				Integer maxVersion = rs.getInt("version");
				journalAricleDto.setResourcePrimkey(resourceprimkey);
				journalAricleDto.setVersion(maxVersion);
				listJouranlArticleDtos.add(journalAricleDto);
			}
			return listJouranlArticleDtos;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return null;
		} finally {
			rs.close();
			statement.close();
			con.close();

		}
	}

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			List<DlFileEntryDto> listDlFileEntryDtos=new ArrayList<>();
			List<JournalAricleDto> listJournalAricleDtos = findJournalArticle(themeDisplay.getScopeGroupId());
			for (JournalAricleDto journalAricleDto : listJournalAricleDtos) {

				JournalArticle journalArticle = JournalArticleLocalServiceUtil
						.getLatestArticle(journalAricleDto.getResourcePrimkey(), 0);
				JSONObject json = parseContent(journalArticle.getContent(), journalArticle.getDefaultLanguageId());
				Iterator<String> keys = json.keySet().iterator();
				while (keys.hasNext()) {
					String key = keys.next();
					String value = json.get(key).toString();
					JSONObject object = JSONFactoryUtil.createJSONObject(value);
					DlFileEntryDto dlFileEntryDto = new DlFileEntryDto();
					dlFileEntryDto.setClassPK(object.getInt("classPK"));
					dlFileEntryDto.setGroupId(object.getInt("groupId"));
					dlFileEntryDto.setType(object.getString("type"));
					dlFileEntryDto.setTitle(object.getString("title"));
					dlFileEntryDto.setUuid(object.getString("uuid"));
					dlFileEntryDto.setFolderId(journalArticle.getFolderId());
					dlFileEntryDto.setSrc(journalArticle.getSmallImageURL());
					listDlFileEntryDtos.add(dlFileEntryDto);
					
				}

			}
			renderRequest.setAttribute("listDlFileEntryDtos", listDlFileEntryDtos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		super.doView(renderRequest, renderResponse);
	}
}