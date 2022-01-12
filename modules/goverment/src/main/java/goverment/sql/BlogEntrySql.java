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

public class BlogEntrySql {
	public List<BlogsEntryDto> findAllBlogsByIdCategory(String uuid,Integer number) throws SQLException {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {

			List<BlogsEntryDto> listBlogsEntryDto = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"SELECT\r\n" + 
					"    be.uuid_           AS uuidblogsentry,\r\n" + 
					"    be.entryid         AS entryid,\r\n" + 
					"    dl.filename        AS filename,\r\n" + 
					"    be.title           AS titleblogsentry,\r\n" + 
					"    be.content           AS content,\r\n" +
					"    be.description     AS descriptiondlfileentry,\r\n" + 
					"    be.modifieddate    AS modifieddate,\r\n" + 
					"    dl.fileentryid     AS fileentryid,\r\n" + 
					"    dl.groupid         AS groupid,\r\n" + 
					"    dl.folderid        AS folderid,\r\n" + 
					"    dl.title           AS titledlfileentry,\r\n" + 
					"    dl.uuid_           AS uuiddlfileentry\r\n" + 
					"FROM\r\n" + 
					"         assetcategory ac\r\n" + 
					"    INNER JOIN assetentryassetcategoryrel  aeac ON ac.categoryid = aeac.assetcategoryid\r\n" + 
					"    INNER JOIN assetentry                  ae ON aeac.assetentryid = ae.entryid\r\n" + 
					"    INNER JOIN blogsentry                  be ON ae.classpk = be.entryid\r\n" + 
					"    INNER JOIN dlfileentry                 dl ON dl.fileentryid = be.smallimagefileentryid\r\n" + 
					"WHERE\r\n" + 
					"        ac.uuid_ =?\r\n" + 
					"    AND ae.classnameid = '31201'\r\n" + 
					"    AND be.status = '0'\r\n" + 
					"ORDER BY\r\n" + 
					"    be.modifieddate DESC\r\n" + 
					"OFFSET 0 ROWS FETCH NEXT ? ROWS ONLY");
			statement.setString(1, uuid);
			statement.setInt(2, number);
			rs = statement.executeQuery();
			while (rs.next()) {
				BlogsEntryDto blogsEntryDto = new BlogsEntryDto();
				String uuidBlogsEntry = rs.getString("uuidblogsentry");
				Integer entryId = rs.getInt("entryid");
				String titleBlogsEntry = rs.getString("titleblogsentry");
				String description = rs.getString("descriptiondlfileentry");
				Timestamp modifiedDate = rs.getTimestamp("modifieddate");
				Integer fileEntryId = rs.getInt("fileentryid");
				String uuidDlFile= rs.getString("uuiddlfileentry");
				Integer groupId= rs.getInt("groupid");
				Integer folderId= rs.getInt("folderid");
				String filename= rs.getString("filename");
				String content= rs.getString("content");
				String src = "/documents" + "/" + groupId + "/" + folderId + "/" + filename + "/" + uuidDlFile;
				blogsEntryDto.setDescription(description);
				blogsEntryDto.setEntryId(entryId);
				blogsEntryDto.setFileEntryId(fileEntryId);
				blogsEntryDto.setTitleBlogsEntry(titleBlogsEntry);
				blogsEntryDto.setUuidBlogsEntry(uuidBlogsEntry);
				blogsEntryDto.setModifiedDate(modifiedDate);
				blogsEntryDto.setSrc(src);
				blogsEntryDto.setContent(content);
				listBlogsEntryDto.add(blogsEntryDto);
			}
			return listBlogsEntryDto;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
	}
	public List<BlogsEntryDto> findAllBlogsByCategory(String uuid, Integer number) throws SQLException {
		PreparedStatement statement=null;
		Connection con=null;
		ResultSet rs=null;
		try {
			
			List<BlogsEntryDto> listBlogsEntryDto = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT\r\n" + 
					"    be.entryid         AS entryid,\r\n" + 
					"    be.title           AS titleblogsentry,\r\n" + 
					"    be.description     AS descriptiondlfileentry,\r\n" + 
					"    be.modifieddate    AS modifieddate\r\n" + 
					"FROM\r\n" + 
					"         assetcategory ac\r\n" + 
					"    INNER JOIN assetentryassetcategoryrel  aeac ON ac.categoryid = aeac.assetcategoryid\r\n" + 
					"    INNER JOIN assetentry                  ae ON aeac.assetentryid = ae.entryid\r\n" + 
					"    INNER JOIN blogsentry                  be ON ae.classpk = be.entryid\r\n" + 
					"WHERE\r\n" + 
					"        ac.uuid_ = ?\r\n" + 
					"    AND ae.classnameid = '31201'\r\n" + 
					"    AND be.status = '0'\r\n" + 
					"ORDER BY\r\n" + 
					"    be.modifieddate DESC\r\n" + 
					"OFFSET 0 ROWS FETCH NEXT ? ROWS ONLY");
			statement.setString(1, uuid);
			statement.setInt(2, number);
			rs=statement.executeQuery();
			while(rs.next()) {
				BlogsEntryDto blogsEntryDto=new BlogsEntryDto();
				Integer entryId=rs.getInt("entryid");
				String titleBlogsEntry=rs.getString("titleblogsentry");
				String description=rs.getString("descriptiondlfileentry");
				Timestamp modifiedDate=rs.getTimestamp("modifieddate");
				blogsEntryDto.setDescription(description);
				blogsEntryDto.setEntryId(entryId);
				blogsEntryDto.setTitleBlogsEntry(titleBlogsEntry);
				blogsEntryDto.setModifiedDate(modifiedDate);
				listBlogsEntryDto.add(blogsEntryDto);
			}
			return listBlogsEntryDto;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			if(statement != null) {
				statement.close();
			}
			if(con != null) {
				con.close();
			}
			if(rs != null) {
				rs.close();
			}
		}
	}

}
