package goverment.dto;

public class DlFolderDto {
	private Integer folderId;
	private String treePath;
	private String name;
	private String uuid;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Integer getFolderId() {
		return folderId;
	}
	public void setFolderId(Integer folderId) {
		this.folderId = folderId;
	}
	public String getTreePath() {
		return treePath;
	}
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
	public DlFolderDto(Integer folderId, String treePath) {
		super();
		this.folderId = folderId;
		this.treePath = treePath;
	}
	public DlFolderDto() {
		super();
	}
	
}
