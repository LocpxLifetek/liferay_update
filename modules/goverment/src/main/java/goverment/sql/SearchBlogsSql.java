package goverment.sql;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import goverment.dto.SearchBlogsDto;

public class SearchBlogsSql {
	public List<SearchBlogsDto> searchBlogsSql(String titleBlogs,long groupId) throws SQLException {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<SearchBlogsDto> listSearchBlogsDto=new ArrayList<>();
			con=DataAccess.getConnection();
			statement=con.prepareStatement("SELECT\r\n" + 
					"    be.uuid_       AS uuidblogs,\r\n" + 
					"    be.title       AS title,\r\n" + 
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
					"OFFSET 0 ROWS FETCH NEXT 8 ROWS ONLY");
			statement.setString(1, titleBlogs);
			statement.setLong(2, groupId);
			rs=statement.executeQuery();
			while(rs.next()) {
				SearchBlogsDto searchBlogsDto=new SearchBlogsDto();
				Integer groupIdDlfile=rs.getInt("groupId");
				Integer folderId=rs.getInt("folderId");
				String fileName=rs.getString("fileName");
				String uuid=rs.getString("uuid");
				String src = "/documents/" + groupIdDlfile + "/" + folderId + "/" + fileName + "/" + uuid;
				String uuidBlogs=rs.getString("uuidBlogs");
				String title=rs.getString("title");
				searchBlogsDto.setSrc(src);
				searchBlogsDto.setTitle(title);
				searchBlogsDto.setUuid(uuidBlogs);
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
}
