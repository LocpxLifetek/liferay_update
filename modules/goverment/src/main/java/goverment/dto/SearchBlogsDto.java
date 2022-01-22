package goverment.dto;

public class SearchBlogsDto {
	private String uuid;
	private String title;
	private String src;
	private String main;
	
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public SearchBlogsDto() {
		super();
	}
}
