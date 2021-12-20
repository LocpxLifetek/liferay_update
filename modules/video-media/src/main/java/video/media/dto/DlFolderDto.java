package video.media.dto;

public class DlFolderDto {
	private Integer folderId;
	private String treePath;
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
