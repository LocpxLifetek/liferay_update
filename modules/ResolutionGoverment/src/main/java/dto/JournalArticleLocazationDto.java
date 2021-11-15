package dto;

public class JournalArticleLocazationDto {
	private String title;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public JournalArticleLocazationDto(String title) {
		super();
		this.title = title;
	}

	public JournalArticleLocazationDto() {
		super();
	}

	@Override
	public String toString() {
		return "JournalArticleLocazationDto [title=" + title + ", id=" + id + "]";
	}
	
	
}
