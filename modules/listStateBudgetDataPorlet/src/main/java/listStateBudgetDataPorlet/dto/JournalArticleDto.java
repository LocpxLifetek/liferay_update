package listStateBudgetDataPorlet.dto;

public class JournalArticleDto {
	private Integer version;
	private Integer id;
	private Integer resourcePrimkey;
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

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

	public JournalArticleDto() {
		super();
	}
}
