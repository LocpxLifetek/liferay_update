package directOperation.portlet;

import java.util.Date;

public class BlogsEntryDto {
	private Date modifiedDate;
	private String description;
	private String titleImage;
	private String titleBlogs;
	private String uuidBlogs;
	private String srcImage;
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitleImage() {
		return titleImage;
	}
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	public String getTitleBlogs() {
		return titleBlogs;
	}
	public void setTitleBlogs(String titleBlogs) {
		this.titleBlogs = titleBlogs;
	}
	public String getUuidBlogs() {
		return uuidBlogs;
	}
	public void setUuidBlogs(String uuidBlogs) {
		this.uuidBlogs = uuidBlogs;
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
