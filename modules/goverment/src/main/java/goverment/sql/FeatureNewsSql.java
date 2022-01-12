package goverment.sql;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import goverment.dto.BlogsEntryDto;

public class FeatureNewsSql {
	public List<BlogsEntryDto> findAllBlogsByIdCategory(long groupIdCategory){
		List<BlogsEntryDto> listBlogsEntryDto = new ArrayList<>();
		PreparedStatement statement=null;
		Connection con = null;
		ResultSet rs=null;
		try {
			
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"SELECT be.uuid_  AS uuidblogsentry,be.entryid  AS entryid,be.title AS titleblogsentry, be.description AS descriptiondlfileentry,be.modifieddate AS modifieddate,dl.fileentryid AS fileentryid,dl.groupid  AS groupid,dl.folderid AS folderid, dl.filename AS filename, dl.uuid_ AS uuiddlfileentry FROM assetcategory ac INNER JOIN assetentryassetcategoryrel  aeac ON ac.categoryid = aeac.assetcategoryid INNER JOIN assetentry ae ON aeac.assetentryid = ae.entryid INNER JOIN blogsentry be ON ae.classpk = be.entryid INNER JOIN dlfileentry dl ON dl.fileentryid = be.smallimagefileentryid WHERE upper(REGEXP_REPLACE(ac.name,'[^a-z_A-Z ]')) = upper('TIN TC NI BT')  AND be.status = '0' and ac.groupId=? ORDER BY be.modifieddate DESC OFFSET 0 ROWS FETCH NEXT 7 ROWS ONLY");
			statement.setLong(1, groupIdCategory);
			rs = statement.executeQuery();
			while (rs.next()) {
				BlogsEntryDto blogsEntryDto = new BlogsEntryDto();
				String uuidBlogsEntry = rs.getString("uuidblogsentry");
				Integer entryId = rs.getInt("entryid");
				String titleBlogsEntry = rs.getString("titleblogsentry");
				String description = rs.getString("descriptiondlfileentry");
				Timestamp modifiedDate = rs.getTimestamp("modifieddate");
				Integer fileEntryId = rs.getInt("fileentryid");
				Integer groupId = rs.getInt("groupid");
				Integer folderId = rs.getInt("folderid");
				String filename = rs.getString("filename");
				String uuidDlFileEntry = rs.getString("uuiddlfileentry");
				blogsEntryDto.setDescription(description);
				blogsEntryDto.setEntryId(entryId);
				blogsEntryDto.setFileEntryId(fileEntryId);
				blogsEntryDto.setTitleBlogsEntry(titleBlogsEntry);
				blogsEntryDto.setUuidBlogsEntry(uuidBlogsEntry);
				
				blogsEntryDto.setModifiedDate(modifiedDate);
				String src = "/documents" + "/" + groupId + "/" + folderId + "/" + filename + "/" + uuidDlFileEntry;
				blogsEntryDto.setSrc(src);
				listBlogsEntryDto.add(blogsEntryDto);
			}
			return listBlogsEntryDto;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			 if (rs != null) {
			        try {
			            rs.close();
			        } catch (SQLException e) { /* Ignored */}
			    }
			    if (statement != null) {
			        try {
			        	statement.close();
			        } catch (SQLException e) { /* Ignored */}
			    }
			    if (con != null) {
			        try {
			            con.close();
			        } catch (SQLException e) { /* Ignored */}
			    }
		}
	}
}
