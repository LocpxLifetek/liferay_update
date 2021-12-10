package hotNews.dto;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestCache implements WebCacheItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<BlogsEntryDto> findAllBlogsByIdCategory(long groupIdCategory) {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {

			List<BlogsEntryDto> listBlogsEntryDto = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"SELECT be.uuid_  AS uuidblogsentry,be.entryid  AS entryid,be.title AS titleblogsentry, be.description AS descriptiondlfileentry,dl.fileentryid AS fileentryid,dl.groupid  AS groupid,dl.folderid AS folderid, dl.title AS titledlfileentry, dl.uuid_ AS uuiddlfileentry FROM assetcategory ac INNER JOIN assetentryassetcategoryrel  aeac ON ac.categoryid = aeac.assetcategoryid INNER JOIN assetentry ae ON aeac.assetentryid = ae.entryid INNER JOIN blogsentry be ON ae.classpk = be.entryid INNER JOIN dlfileentry dl ON dl.fileentryid = be.smallimagefileentryid WHERE upper(REGEXP_REPLACE(ac.name,'[^a-z_A-Z ]')) = upper('TIN MI')  AND ae.classnameid = '31201'  AND be.status = '0' and ac.groupId=? ORDER BY be.modifieddate DESC OFFSET 0 ROWS FETCH NEXT 4 ROWS ONLY");
			statement.setLong(1, groupIdCategory);
			rs = statement.executeQuery();
			while (rs.next()) {
				BlogsEntryDto blogsEntryDto = new BlogsEntryDto();
				String uuidBlogsEntry = rs.getString("uuidblogsentry");
				Integer entryId = rs.getInt("entryid");
				String titleBlogsEntry = rs.getString("titleblogsentry");
				String description = rs.getString("descriptiondlfileentry");
				Integer fileEntryId = rs.getInt("fileentryid");
				Integer groupId = rs.getInt("groupid");
				Integer folderId = rs.getInt("folderid");
				String titleDlFileEntry = rs.getString("titledlfileentry");
				String uuidDlFileEntry = rs.getString("uuiddlfileentry");
				blogsEntryDto.setDescription(description);
				blogsEntryDto.setEntryId(entryId);
				blogsEntryDto.setFileEntryId(fileEntryId);
				blogsEntryDto.setFolderId(folderId);
				blogsEntryDto.setGroupId(groupId);
				blogsEntryDto.setTitleBlogsEntry(titleBlogsEntry);
				blogsEntryDto.setTitleDlFileEntry(titleDlFileEntry);
				blogsEntryDto.setUuidBlogsEntry(uuidBlogsEntry);
				blogsEntryDto.setUuidDlFileEntry(uuidDlFileEntry);
				listBlogsEntryDto.add(blogsEntryDto);
			}
			return listBlogsEntryDto;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					/* Ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					/* Ignored */}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					/* Ignored */}
			}
		}
	}

	@Override
	public Object convert(String key) throws WebCacheException {
		// TODO Auto-generated method stub
		String[] keyValue=key.split("\\,");
		int i=0;
		String groupId=null;
		for (String string : keyValue) {
			i++;
			if(i==2) {
				groupId=string;
			}
		}
		long groupIdCategory = Long.parseLong(groupId);
		
		return findAllBlogsByIdCategory(groupIdCategory);
	}

	@Override
	public long getRefreshTime() {
		// TODO Auto-generated method stub

		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.SECOND * 10;

}
