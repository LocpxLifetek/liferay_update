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

public class TagsBlogSql {
	public List<BlogsEntryDto> findAllBlogsByTagsId(long id, long groupIdBlog, Integer page, Integer size) {
		List<BlogsEntryDto> listBlogsEntryDto = new ArrayList<>();
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"select be.uuid_  AS uuidblogsentry,be.entryid  AS entryid,be.title AS titleblogsentry, be.description AS descriptiondlfileentry,dl.fileentryid AS fileentryid,dl.groupid  AS groupid,dl.folderid AS folderid, dl.filename AS filename, dl.uuid_ AS uuiddlfileentry,be.modifiedDate as modifieddate from assetentries_assettags aeat inner join assetEntry ae on aeat.entryid=ae.entryid inner join blogsentry be on be.entryid=ae.classpk inner join dlfileentry dl on be.smallimagefileentryid=dl.fileentryid where aeat.tagid=? and be.groupId=? order by be.modifieddate desc OFFSET (?-1)*? ROWS FETCH NEXT ?  ROWS ONLY ");
			statement.setLong(1, id);
			statement.setLong(2, groupIdBlog);
			statement.setInt(3, page);
			statement.setInt(4, size);
			statement.setInt(5, size);
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
	
	public Integer countViewTags(long groupId,long id) {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			Integer result=0;
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"select count(*) as result from assetentries_assettags aeat inner join assetEntry ae on aeat.entryid=ae.entryid inner join blogsentry be on be.entryid=ae.classpk inner join dlfileentry dl on be.smallimagefileentryid=dl.fileentryid where aeat.tagid=? and be.groupId=?");
			statement.setLong(1, id);
			statement.setLong(2, groupId);
			rs = statement.executeQuery();
			while (rs.next()) {
				result=rs.getInt("result");
			}
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
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
}
