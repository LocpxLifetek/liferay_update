package eventNewsInformation.dto;

public class CategoryAndVocabulartDto {
	
	private String name;
	private Integer groupId;
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public CategoryAndVocabulartDto() {
		super();
	}
	
	
	
}
