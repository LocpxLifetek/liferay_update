package countViewVideoMedia.dto;

public class CountViewVideoDto {
	private String uuid;
	private String title;
	private String src;
	private String mimeType;
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
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
	public CountViewVideoDto(String uuid, String title, String src) {
		super();
		this.uuid = uuid;
		this.title = title;
		this.src = src;
	}
	public CountViewVideoDto() {
		super();
	}
	
	
}
