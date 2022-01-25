package goverment.sql;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public CategoryDto findCategoryByUuid(String uuid) {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			CategoryDto CategoryDto = new CategoryDto();
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"SELECT ac.name AS name, ac.groupid AS groupid, ac.categoryid AS id,ac.uuid_ as uuidCa, ac.parentcategoryid as parentId FROM assetcategory ac WHERE ac.uuid_=?");
			statement.setString(1, uuid);
			rs = statement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				Integer group = rs.getInt("groupId");
				Integer id = rs.getInt("id");
				Integer parentId= rs.getInt("parentId");
				String uuidCa= rs.getString("uuidCa");
				CategoryDto.setUuid(uuidCa);
				CategoryDto.setId(id);
				CategoryDto.setGroupId(group);
				CategoryDto.setName(name);
				CategoryDto.setParentCategoryId(parentId);
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
	public List<CategoryDto> findCategoryByParentCategory(Integer parentId) {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"SELECT ac.name AS name, ac.groupid AS groupid, ac.categoryid AS id, ac.uuid_ as uuid FROM assetcategory ac WHERE ac.parentcategoryid=?");
			statement.setLong(1, parentId);
			rs = statement.executeQuery();
			List<CategoryDto> listCategory= new ArrayList<>();
			while (rs.next()) {
				CategoryDto categoryDto = new CategoryDto();
				String name = rs.getString("name");
				Integer group = rs.getInt("groupId");
				Integer id = rs.getInt("id");
				String uuid= rs.getString("uuid");
				categoryDto.setId(id);
				categoryDto.setGroupId(group);
				categoryDto.setName(name);
				categoryDto.setUuid(uuid);
				listCategory.add(categoryDto);
				
			}
			return listCategory;
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
	public CategoryDto findCategoryById(Integer id) {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"SELECT ac.name AS name, ac.groupid AS groupid, ac.uuid_ as uuid FROM assetcategory ac WHERE ac.categoryid=?");
			statement.setLong(1, id);
			rs = statement.executeQuery();
			CategoryDto categoryDto = new CategoryDto();
			while (rs.next()) {
				String name = rs.getString("name");
				Integer group = rs.getInt("groupId");
				String uuid= rs.getString("uuid");
				categoryDto.setGroupId(group);
				categoryDto.setName(name);
				categoryDto.setUuid(uuid);		
			}
			return categoryDto;
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
