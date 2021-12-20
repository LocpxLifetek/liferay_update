package listVideoCategory.dto;

public class DlFileFolderDto {
	private String name;
	private Integer count;
	
	

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DlFileFolderDto(String name) {
		super();
		this.name = name;
	}

	public DlFileFolderDto() {
		super();
	}
	
}
