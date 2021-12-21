package goverment.sql;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import goverment.dto.CategoryDto;

public class AssetCategorySql {
	public CategoryDto findCategoryByVocabulartDto(long groupId) {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			CategoryDto CategoryDto = new CategoryDto();
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"select ac.name as name,ac.groupId as groupId,ac.categoryId as id from AssetCategory ac inner join assetvocabulary av on ac.vocabularyid=av.vocabularyid where ac.groupId=? and upper(REGEXP_REPLACE(av.name,'[^a-z_A-Z ]')) like upper('Tin tc s kin') and upper(REGEXP_REPLACE(ac.name,'[^a-z_A-Z ]')) like upper('Tin tc s kin')");
			statement.setLong(1, groupId);
			rs = statement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				Integer group = rs.getInt("groupId");
				Integer id = rs.getInt("id");
				CategoryDto.setId(id);
				CategoryDto.setGroupId(group);
				CategoryDto.setName(name);
			}
			return CategoryDto;
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
