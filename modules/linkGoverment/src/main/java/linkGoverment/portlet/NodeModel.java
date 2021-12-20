package linkGoverment.portlet;

public class NodeModel {
	private String nodeName;
	private String nodeValue;

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeValue() {
		return nodeValue;
	}

	public void setNodeValue(String nodeValue) {
		this.nodeValue = nodeValue;
	}

	public NodeModel(String nodeName, String nodeValue) {
		super();
		this.nodeName = nodeName;
		this.nodeValue = nodeValue;
	}

	public NodeModel() {
		super();
	}
	
	
}

