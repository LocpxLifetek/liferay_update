package callAjax.dto;

public class BlogsEntryDto {
	private String uuidBlogsEntry;
	private Integer entryId;
	private String titleBlogsEntry;
	private String description;
	private Integer fileEntryId;
	private String srcImage;

	public String getUuidBlogsEntry() {
		return uuidBlogsEntry;
	}

	public void setUuidBlogsEntry(String uuidBlogsEntry) {
		this.uuidBlogsEntry = uuidBlogsEntry;
	}

	public Integer getEntryId() {
		return entryId;
	}

	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
	}

	public String getTitleBlogsEntry() {
		return titleBlogsEntry;
	}

	public void setTitleBlogsEntry(String titleBlogsEntry) {
		this.titleBlogsEntry = titleBlogsEntry;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFileEntryId() {
		return fileEntryId;
	}

	public void setFileEntryId(Integer fileEntryId) {
		this.fileEntryId = fileEntryId;
	}

	

	public String getSrcImage() {
		return srcImage;
	}

	public void setSrcImage(String srcImage) {
		this.srcImage = srcImage;
	}

	public BlogsEntryDto() {
		super();
	}

}
