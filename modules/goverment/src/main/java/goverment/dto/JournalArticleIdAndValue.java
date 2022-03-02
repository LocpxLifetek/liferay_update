package goverment.dto;

public class JournalArticleIdAndValue {
	private long id;
	private String value;
	private String key;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public JournalArticleIdAndValue() {
		super();
	}
	
	
}
