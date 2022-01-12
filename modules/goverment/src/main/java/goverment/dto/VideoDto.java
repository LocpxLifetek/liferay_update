package goverment.dto;

public class VideoDto {
	public String data;
	public long fileEntryId;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public long getFileEntryId() {
		return fileEntryId;
	}
	public void setFileEntryId(long fileEntryId) {
		this.fileEntryId = fileEntryId;
	}
	public VideoDto() {
		super();
	}
	
}
