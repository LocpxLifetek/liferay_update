package stateBudegetData.dto;

public class JournalArticleLocalizationDto {
	private String name;
	private Integer folderId;
	private Integer id;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JournalArticleLocalizationDto(String name) {
		super();
		this.name = name;
	}

	public JournalArticleLocalizationDto() {
		super();
	}
	
}
