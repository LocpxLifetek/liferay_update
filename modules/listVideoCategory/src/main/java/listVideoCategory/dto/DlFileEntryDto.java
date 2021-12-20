package listVideoCategory.dto;

public class DlFileEntryDto {
	private String src;
	private String uuidDlFileEntry;
	private String title;
	private String mimeType;
	private String extension;
	
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
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
	public String getUuidDlFileEntry() {
		return uuidDlFileEntry;
	}
	public void setUuidDlFileEntry(String uuidDlFileEntry) {
		this.uuidDlFileEntry = uuidDlFileEntry;
	}
	public DlFileEntryDto(String src, String uuidDlFileEntry) {
		super();
		this.src = src;
		this.uuidDlFileEntry = uuidDlFileEntry;
	}
	public DlFileEntryDto() {
		super();
	}
}
