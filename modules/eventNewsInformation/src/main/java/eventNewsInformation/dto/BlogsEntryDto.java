package eventNewsInformation.dto;

import java.sql.Timestamp;

public class BlogsEntryDto {
	private String uuidBlogsEntry;
	private Integer entryId;
	private String titleBlogsEntry;
	private String description;
	private Timestamp modifiedDate;
	private Integer fileEntryId;
	private Integer groupId;
	private Integer folderId;
	private String titleDlFileEntry;
	private String uuidDlFileEntry;
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
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getFolderId() {
		return folderId;
	}
	public void setFolderId(Integer folderId) {
		this.folderId = folderId;
	}
	public String getTitleDlFileEntry() {
		return titleDlFileEntry;
	}
	public void setTitleDlFileEntry(String titleDlFileEntry) {
		this.titleDlFileEntry = titleDlFileEntry;
	}
	public String getUuidDlFileEntry() {
		return uuidDlFileEntry;
	}
	public void setUuidDlFileEntry(String uuidDlFileEntry) {
		this.uuidDlFileEntry = uuidDlFileEntry;
	}
	public BlogsEntryDto() {
		super();
	}
	
}
