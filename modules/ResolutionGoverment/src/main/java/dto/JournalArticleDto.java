package dto;

public class JournalArticleDto {
	private Integer resourcePrimkey;
	private Integer maxVersion;
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getResourcePrimkey() {
		return resourcePrimkey;
	}
	public void setResourcePrimkey(Integer resourcePrimkey) {
		this.resourcePrimkey = resourcePrimkey;
	}
	public Integer getMaxVersion() {
		return maxVersion;
	}
	public void setMaxVersion(Integer maxVersion) {
		this.maxVersion = maxVersion;
	}
	public JournalArticleDto() {
		super();
	}
}
