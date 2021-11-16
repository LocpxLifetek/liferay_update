package dto;

public class JournalArticleDto {
	private Integer resourcePrimkey;
	private Integer maxVersion;
	
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
	public JournalArticleDto(Integer resourcePrimkey, Integer maxVersion) {
		super();
		this.resourcePrimkey = resourcePrimkey;
		this.maxVersion = maxVersion;
	}
	public JournalArticleDto() {
		super();
	}
	
	
}
