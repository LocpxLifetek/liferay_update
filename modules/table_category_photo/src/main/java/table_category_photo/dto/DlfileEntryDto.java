package table_category_photo.dto;

import java.sql.Timestamp;

public class DlfileEntryDto {
	private String name;
	private String uuidCategory;
	private String src;
	private String title;
	private String fileName;
	private Timestamp modifiedDate;
	

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
	public DlfileEntryDto() {
		super();
		// TODO Auto-generated constructor stub
	}


}
