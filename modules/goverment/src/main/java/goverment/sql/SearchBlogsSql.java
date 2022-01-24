package goverment.sql;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import goverment.dto.SearchBlogsDto;

public class SearchBlogsSql {
	public List<SearchBlogsDto> searchBlogsSql(String titleBlogs,long groupId, Integer page, Integer size) throws SQLException {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<SearchBlogsDto> listSearchBlogsDto=new ArrayList<>();
			con=DataAccess.getConnection();
			statement=con.prepareStatement("SELECT\r\n" + 
					"    be.uuid_       AS uuidblogs,\r\n" + 
					"    be.title       AS title, be.modifiedDate as modifiedDate, be.description as description,\r\n" + 
					"    df.groupid     AS groupid,\r\n" + 
					"    df.folderid    AS folderid,\r\n" + 
					"    df.filename    AS filename,\r\n" + 
					"    df.uuid_       AS uuid\r\n" + 
					"FROM\r\n" + 
					"         blogsentry be\r\n" + 
					"    INNER JOIN dlfileentry df ON be.smallimagefileentryid = df.fileentryid\r\n" + 
					"WHERE\r\n" + 
					"    upper(be.title) LIKE upper(concat(concat('%',?),'%'))\r\n" + 
					"    AND be.groupid = ?\r\n" + 
					"ORDER BY\r\n" + 
					"    be.modifieddate DESC\r\n" + 
					"OFFSET (?-1)*? ROWS FETCH NEXT ? ROWS ONLY");
			statement.setString(1, titleBlogs);
			statement.setLong(2, groupId);
			statement.setInt(3, page);
			statement.setInt(4, size);
			statement.setInt(5, size);
			rs=statement.executeQuery();
			while(rs.next()) {
				SearchBlogsDto searchBlogsDto=new SearchBlogsDto();
				Integer groupIdDlfile=rs.getInt("groupId");
				Integer folderId=rs.getInt("folderId");
				String fileName=rs.getString("fileName");
				String uuid=rs.getString("uuid");
				Date modifiedDate=rs.getDate("modifiedDate");
				String src = "/documents/" + groupIdDlfile + "/" + folderId + "/" + fileName + "/" + uuid;
				String uuidBlogs=rs.getString("uuidBlogs");
				String title=rs.getString("title");
				String description=rs.getString("description");
				searchBlogsDto.setSrc(src);
				searchBlogsDto.setDescription(description);
				searchBlogsDto.setTitle(title);
				searchBlogsDto.setUuid(uuidBlogs);
				searchBlogsDto.setModifiedDate(modifiedDate);
				listSearchBlogsDto.add(searchBlogsDto);
			}
			return listSearchBlogsDto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			rs.close();
			statement.close();
			con.close();

		}
	}
	
	public Integer countViewBlogsSerach(String text,long groupId) throws SQLException {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			Integer countBlogs = 0;
			con=DataAccess.getConnection();
			statement=con.prepareStatement("select count(*) as count from BlogsEntry be where be.groupId=? and upper(be.title) LIKE upper(concat(concat('%',?),'%'))");
			statement.setLong(1, groupId);
			statement.setString(2, text);
			rs=statement.executeQuery();
			while(rs.next()) {
				countBlogs=rs.getInt("count");
			}
			return countBlogs;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			rs.close();
			statement.close();
			con.close();

		}
	}
}
