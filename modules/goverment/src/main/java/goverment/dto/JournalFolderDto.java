package goverment.dto;

public class JournalFolderDto {
	private String name;
	private Long id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public JournalFolderDto(String name, Long id) {
		super();
		this.name = name;
		this.id = id;
	}
	public JournalFolderDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
