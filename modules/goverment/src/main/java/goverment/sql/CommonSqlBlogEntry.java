package goverment.sql;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import goverment.dto.BlogsEntryDto;

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
					+ "WHERE\r\n" + "        ae.classnameid = '31201'\r\n" + "    AND be.groupid = ?\r\n"
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
}
