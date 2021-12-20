package goverment.dto;

import java.sql.Timestamp;

public class BlogsEntryDto {
	private String uuidBlogsEntry;
	private Integer entryId;
	private String titleBlogsEntry;
	private String description;
	private Timestamp modifiedDate;
	private Integer fileEntryId;
	private String src;
	
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

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getFileEntryId() {
		return fileEntryId;
	}

	public void setFileEntryId(Integer fileEntryId) {
		this.fileEntryId = fileEntryId;
	}

	
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public BlogsEntryDto() {
		super();
	}
	
}

