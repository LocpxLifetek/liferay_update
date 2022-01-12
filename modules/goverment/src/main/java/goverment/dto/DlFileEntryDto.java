package goverment.dto;

import java.sql.Timestamp;

public class DlFileEntryDto {
	private String name;
	private String uuidCategory;
	private String src;
	private String title;
	private String fileName;
	private Timestamp modifiedDate;
	private Integer id;
	private String url;
	private Integer classPK;
	private Integer groupId;
	private String type;
	private long folderId;

	
	public Integer getClassPK() {
		return classPK;
	}
	public void setClassPK(Integer classPK) {
		this.classPK = classPK;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getFolderId() {
		return folderId;
	}
	public void setFolderId(long folderId) {
		this.folderId = folderId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUuidCategory() {
		return uuidCategory;
	}
	public void setUuidCategory(String uuidCategory) {
		this.uuidCategory = uuidCategory;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public DlFileEntryDto() {
		super();
	}
	
}
