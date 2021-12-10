package countViewBlogs.dto;

public class BlogsEntryDtos {
	private String title;
	private String description;
	private String uuid;
	private String srcImage;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getSrcImage() {
		return srcImage;
	}
	public void setSrcImage(String srcImage) {
		this.srcImage = srcImage;
	}
	public BlogsEntryDtos() {
		super();
	}
	public BlogsEntryDtos(String title, String description, String uuid, String srcImage) {
		super();
		this.title = title;
		this.description = description;
		this.uuid = uuid;
		this.srcImage = srcImage;
	}
	
	
}
