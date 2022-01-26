package goverment.sql;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import goverment.dto.BlogsEntryDto;
import goverment.dto.CountViewDto;
import goverment.dto.CountViewVideoDto;

public class CommonSqlBlogEntry {
	public List<BlogsEntryDto> findBlogsCountView(long groupIdCategory) {

		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<BlogsEntryDto> listBlogsEntryDtos = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT\r\n" + "    be.title          AS title,\r\n"
					+ "    be.description    AS description,\r\n" + "    be.uuid_          AS uuid,\r\n"
					+ "    de.groupid        AS groupid,\r\n" + "    de.folderid       AS folderid,\r\n"
					+ "    de.filename          AS filename,\r\n" + "    de.uuid_          AS uuiddlfile\r\n"
					+ "FROM\r\n" + "         blogsentry be\r\n"
					+ "    INNER JOIN assetentry      ae ON be.entryid = ae.classpk\r\n"
					+ "    INNER JOIN viewcountentry  vc ON vc.classpk = ae.entryid\r\n"
					+ "     INNER JOIN dlfileentry                 de ON de.fileentryid = be.smallimagefileentryid\r\n"
					+ "WHERE\r\n" + " be.groupid = ?\r\n"
					+ "    AND be.status = '0'\r\n" + "ORDER BY\r\n"
					+ "    vc.viewcount DESC OFFSET 0 ROWS FETCH NEXT 6 ROWS ONLY");
			statement.setLong(1, groupIdCategory);
			rs = statement.executeQuery();
			while (rs.next()) {
				BlogsEntryDto blogsEntryDto = new BlogsEntryDto();
				String title = rs.getString("title");
				String description = rs.getString("description");
				String uuid = rs.getString("uuid");
				Integer groupId = rs.getInt("groupId");
				Integer folderId = rs.getInt("folderId");
				String filename = rs.getString("filename");
				String uuidDlfile = rs.getString("uuiddlfile");
				blogsEntryDto.setUuidBlogsEntry(uuid);
				blogsEntryDto.setDescription(description);
				blogsEntryDto.setTitleBlogsEntry(title);
				String src = "/documents" + "/" + groupId + "/" + folderId + "/" + filename + "/" + uuidDlfile;
				blogsEntryDto.setSrc(src);
				listBlogsEntryDtos.add(blogsEntryDto);
			}
			return listBlogsEntryDtos;
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
	
	public List<CountViewDto> countViewEvent(String currentDate, String fromDate,long groupIdCategory) {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<CountViewDto> listCountViewDtos = new ArrayList<>();

			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT\r\n" + "    be.title          AS title,\r\n"
					+ "    be.description    AS description,\r\n" + "    be.uuid_          AS uuid,\r\n"
					+ "    de.groupid        AS groupid,\r\n" + "    de.folderid       AS folderid,\r\n"
					+ "    de.title          AS titledlfile,\r\n" + "    de.uuid_          AS uuiddlfile\r\n"
					+ "FROM\r\n" + "         assetcategory ac\r\n"
					+ "    INNER JOIN assetentryassetcategoryrel  aeac ON ac.categoryid = aeac.assetcategoryid\r\n"
					+ "    INNER JOIN assetentry                  ae ON ae.entryid = aeac.assetentryid\r\n"
					+ "    INNER JOIN viewcountentry              vc ON vc.classpk = ae.entryid\r\n"
					+ "    INNER JOIN blogsentry                  be ON be.entryid = ae.classpk\r\n"
					+ "    INNER JOIN dlfileentry                 de ON de.fileentryid = be.smallimagefileentryid\r\n"
					+ "WHERE\r\n" + "  ac.groupid=? and upper(REGEXP_REPLACE(ac.name,'[^a-z_A-Z ]')) = upper('Tin tc s kin')\r\n"
					+ "    AND be.status = '0'\r\n"
					+ "    AND be.modifieddate BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD')\r\n"
					+ "ORDER BY\r\n" + "    vc.viewcount DESC\r\n" + "OFFSET 0 ROWS FETCH NEXT 8 ROWS ONLY");
			statement.setLong(1, groupIdCategory);
			statement.setString(2, fromDate);
			statement.setString(3, currentDate);
			rs = statement.executeQuery();
			while (rs.next()) {
				CountViewDto countViewDto = new CountViewDto();
				String title = rs.getString("title");
				String description = rs.getString("description");
				String uuid = rs.getString("uuid");
				Integer groupId = rs.getInt("groupId");
				Integer folderId = rs.getInt("folderId");
				String titleDlfile = rs.getString("titledlfile");
				String uuidDlfile = rs.getString("uuiddlfile");
				countViewDto.setUuid(uuid);
				countViewDto.setDescription(description);
				countViewDto.setTitle(title);
				String src = "/documents" + "/" + groupId + "/" + folderId + "/" + titleDlfile + "/" + uuidDlfile;
				countViewDto.setSrcImage(src);
				listCountViewDtos.add(countViewDto);
			}
			return listCountViewDtos;
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
	
	public List<CountViewVideoDto> getViewCountVideo(long groupIdDlfileEntry) throws SQLException{
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<CountViewVideoDto> listCountViewVideoDto=new ArrayList<>();
			con=DataAccess.getConnection();
			statement=con.prepareStatement("select df.fileEntryId as fileEntryId, df.userId as userId, df.groupid as groupId,df.folderid as folderId,df.filename as fileName,df.extension as extension,df.uuid_ as uuid,df.title as title,df.mimetype as mimeType from viewcountentry vc inner join assetEntry ae on vc.classpk=ae.classpk inner join dlfileentry df on df.fileentryid=ae.classpk where ae.mimetype like concat('video','%') and df.groupId=? order  by vc.viewcount desc OFFSET 0 ROWS FETCH NEXT 5 ROWS ONLY");
			statement.setLong(1, groupIdDlfileEntry);
			rs=statement.executeQuery();
			while(rs.next()) {
				CountViewVideoDto countViewVideoDto=new CountViewVideoDto();
				Long fileEntryId=rs.getLong("fileEntryId");
				Long userId=rs.getLong("userId");
				Integer groupId = rs.getInt("groupId");
				Integer folderId = rs.getInt("folderId");
				String fileName = rs.getString("fileName");
				String uuid = rs.getString("uuid");
				String title = rs.getString("title");
				String mimeType = rs.getString("mimeType");
				String extension = rs.getString("extension");
				String titleDlFileEntry = null;
				if (title.contains(extension)) {
					titleDlFileEntry = title.replace(extension, "");
				} else {
					titleDlFileEntry = title;
				}
				String src = "/documents/" + groupId + "/" + folderId + "/" + fileName + "/" + uuid;
				countViewVideoDto.setSrc(src);
				countViewVideoDto.setTitle(titleDlFileEntry);
				countViewVideoDto.setUuid(uuid);
				countViewVideoDto.setMimeType(mimeType);
				countViewVideoDto.setFileEntryId(fileEntryId);
				countViewVideoDto.setUserId(userId);
				listCountViewVideoDto.add(countViewVideoDto);
			}
			return listCountViewVideoDto;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			rs.close();
			statement.close();
			con.close();

		}

	}
}
