package tabGoverment.dto;

import java.util.Date;

public class CategoryBusinessDto {
	private String uuidBlogs;
	private String titleBlogs;
	private String description;
	private String src;
	private Date modifiedDate;
	public String getUuidBlogs() {
		return uuidBlogs;
	}
	public void setUuidBlogs(String uuidBlogs) {
		this.uuidBlogs = uuidBlogs;
	}
	public String getTitleBlogs() {
		return titleBlogs;
	}
	public void setTitleBlogs(String titleBlogs) {
		this.titleBlogs = titleBlogs;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public CategoryBusinessDto() {
		super();
	}
	public CategoryBusinessDto(String uuidBlogs, String titleBlogs, String description, String src, Date modifiedDate) {
		super();
		this.uuidBlogs = uuidBlogs;
		this.titleBlogs = titleBlogs;
		this.description = description;
		this.src = src;
		this.modifiedDate = modifiedDate;
	}
	
	
	
}
