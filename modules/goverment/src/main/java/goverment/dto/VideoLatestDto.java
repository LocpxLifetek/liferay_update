package goverment.dto;

public class VideoLatestDto {
	private String src;
	private String uuid;
	private String title;
	private long fileEntryId;
	private long userId;
	
	
	public long getFileEntryId() {
		return fileEntryId;
	}
	public void setFileEntryId(long fileEntryId) {
		this.fileEntryId = fileEntryId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
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
	public VideoLatestDto() {
		super();
	}
	
}
