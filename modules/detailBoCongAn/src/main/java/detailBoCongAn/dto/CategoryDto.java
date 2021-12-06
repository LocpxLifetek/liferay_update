package detailBoCongAn.dto;

public class CategoryDto {
	private String uuid;
	private String name;
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
	public CategoryDto(String uuid, String name) {
		super();
		this.uuid = uuid;
		this.name = name;
	}
	public CategoryDto() {
		super();
	}
	
	
}
