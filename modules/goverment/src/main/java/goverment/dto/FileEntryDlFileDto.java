package goverment.dto;

public class FileEntryDlFileDto {
	private long fileEntryId;
	private long folderId;
	private String uuid;
	private long userId;
	private String title;
	
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	

	public long getFileEntryId() {
		return fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		this.fileEntryId = fileEntryId;
	}

	public long getFolderId() {
		return folderId;
	}

	public void setFolderId(long folderId) {
		this.folderId = folderId;
	}

	public FileEntryDlFileDto(Integer fileEntryId) {
		super();
		this.fileEntryId = fileEntryId;
	}

	public FileEntryDlFileDto() {
		super();
	}
	
}
