package goverment.dto;

public class CpattachmentfileentryDto {
	private String uuid;
	private String name;
	private String src;
	public CpattachmentfileentryDto(String uuid, String name, String src) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.src = src;
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
