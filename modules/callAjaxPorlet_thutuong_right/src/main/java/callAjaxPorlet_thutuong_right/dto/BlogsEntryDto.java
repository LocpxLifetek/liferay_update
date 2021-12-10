package callAjaxPorlet_thutuong_right.dto;

import java.sql.Timestamp;

public class BlogsEntryDto {
	private Integer entryId;
	private String titleBlogsEntry;
	private String description;
	
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
	
	public BlogsEntryDto() {
		super();
	}
}
