package Deatil_Photo_Categories.portlet;

public class DLfileEntryDto {
	
	private String src;
	private String title; 
	private String url;
	private Integer id;
	
	
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



	public String getSrc() {
		return src;
	}



	public void setSrc(String src) {
		this.src = src;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public DLfileEntryDto() {
		super();
	}

}
