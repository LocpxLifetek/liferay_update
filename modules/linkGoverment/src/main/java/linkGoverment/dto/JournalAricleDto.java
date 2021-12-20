package linkGoverment.dto;

public class JournalAricleDto {
	private long resourcePrimkey;
	private Integer version;
	public long getResourcePrimkey() {
		return resourcePrimkey;
	}
	public void setResourcePrimkey(long resourcePrimkey) {
		this.resourcePrimkey = resourcePrimkey;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public JournalAricleDto(long resourcePrimkey, Integer version) {
		super();
		this.resourcePrimkey = resourcePrimkey;
		this.version = version;
	}
	public JournalAricleDto() {
		super();
	}
	
	
}
