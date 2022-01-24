package goverment.dto;

public class CpattachmentfileentryDto {
	private String uuid;
	private String name;
	private String src;
	private Integer fileEntryId;
	private Integer flIdCpa;
	
	public Integer getFlIdCpa() {
		return flIdCpa;
	}
	public void setFlIdCpa(Integer flIdCpa) {
		this.flIdCpa = flIdCpa;
	}
	public Integer getFileEntryId() {
		return fileEntryId;
	}
	public void setFileEntryId(Integer fileEntryId) {
		this.fileEntryId = fileEntryId;
	}
	public CpattachmentfileentryDto() {
		super();
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	
	
}
