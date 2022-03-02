package goverment.config;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.util.ArrayList;
import java.util.List;

import goverment.model.NodeModel;

public class ConvertXmlToJson {
	public static NodeModel convertToNodeMap(Node node, String langId) {
		Node nodeValue = node.selectSingleNode("dynamic-content[@language-id='" + langId + "']");
		String nodeKey = nodeValue.getParent().attribute("name").getStringValue();
		NodeModel nodeModel = new NodeModel();
		nodeModel.setNodeName(nodeKey);
		nodeModel.setNodeValue(nodeValue.getStringValue());
		return nodeModel;
	}

	public static JSONArray fetchChildNodeArray(List<Node> childNodeList, String langId) {
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

	public static JSONObject fetchChildNodeObject(List<Node> childNodeList, String langId) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		for (Node childNode : childNodeList) {
			NodeModel childNodeModel = convertToNodeMap(childNode, langId);
			jsonObject.put(childNodeModel.getNodeName(), childNodeModel.getNodeValue());
			// Fetch the children node
			fetchNode(jsonObject, childNode, langId);
		}
		return jsonObject;
	}

	public static void fetchNode(JSONObject jsonObject, Node prentNode, String langId) {
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

}
