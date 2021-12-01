package stateBudegetData.dto;

public class JournalArticleProjection {
	private Integer id;
	private Integer folderId;
	public Integer getFolderId() {
		return folderId;
	}
	public void setFolderId(Integer folderId) {
		this.folderId = folderId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public JournalArticleProjection() {
		super();
	}
	
	
}
