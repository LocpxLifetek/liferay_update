package pagable.param.portlet;

import com.liferay.document.library.kernel.model.DLFileEntry;

import java.util.Date;

public class ResultDao {
	Integer totalRows;
	String title;
	String content;
	String description;
	Date createDate;
	long smallImageFileEntryId;

	long groupId;
	long folderId;
	String titleDl;
	String uuidDl;

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUuidDl() {
		return uuidDl;
	}

	public void setUuidDl(String uuid_) {
		this.uuidDl = uuid_;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public long getSmallImageFileEntryId() {
		return smallImageFileEntryId;
	}

	public void setSmallImageFileEntryId(long smallImageFileEntryId) {
		this.smallImageFileEntryId = smallImageFileEntryId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getFolderId() {
		return folderId;
	}

	public void setFolderId(long folderId) {
		this.folderId = folderId;
	}

	public String getTitleDl() {
		return titleDl;
	}

	public void setTitleDl(String titledl) {
		this.titleDl = titledl;
	}

	public ResultDao() {
		super();
	}

	@Override
	public String toString() {
		return "ResultDao [title=" + title + ", content=" + content + ", description=" + description + ", createDate="
				+ createDate + ", smallImageFileEntryId=" + smallImageFileEntryId + ", groupId=" + groupId
				+ ", folderId=" + folderId + ", titledl=" + titleDl + "]";
	}

}
