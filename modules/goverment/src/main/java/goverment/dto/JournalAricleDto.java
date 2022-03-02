package goverment.dto;

public class JournalAricleDto {
	private long resourcePrimkey;
	private Integer version;
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public JournalAricleDto(long resourcePrimkey, Integer version, Long id) {
		super();
		this.resourcePrimkey = resourcePrimkey;
		this.version = version;
		this.id = id;
	}
	public JournalAricleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
