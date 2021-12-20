package goverment.sql;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import goverment.dto.CategoryDto;
import goverment.dto.DlfileEntryDto;
import goverment.dto.cpattachmentfileentryDto;



public class PhotoSql {
	public CategoryDto categoryDto()
	{
		PreparedStatement  statement = null;
		Connection con = null;
		ResultSet rs = null;
		CategoryDto category= new CategoryDto();
		try {
			con = DataAccess.getConnection();
			statement= con.prepareStatement("SELECT ac.name as name , ac.categoryid as categoryId FROM assetcategory ac WHERE ac.categoryid='621347'");
			rs = statement.executeQuery();
			while (rs.next()) {
				String name= rs.getString("name");
				Integer categoryId= rs.getInt("categoryId");
				category.setName(name);
				category.setId(categoryId);
			}
			return category;
		}
			catch (Exception e) {
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
	public List<CategoryDto> findCategoryByParent(Integer parentId){
		PreparedStatement  statement = null;
		Connection con = null;
		ResultSet rs = null;
		List<CategoryDto> listCategory= new ArrayList<>();
		try {
			con = DataAccess.getConnection();
			statement= con.prepareStatement("select ac.categoryid as CategoryId, ac.groupid as groupId from assetcategory ac where ac.parentcategoryid=?");
			statement.setInt(1, parentId);
			rs = statement.executeQuery();
			
			while (rs.next()) {
				CategoryDto categoryDto= new CategoryDto();
				Integer id = rs.getInt("CategoryId");
				Integer groupId= rs.getInt("groupId");
				
				categoryDto.setId(id);
				categoryDto.setGroupId(groupId);
				listCategory.add(categoryDto);
				
			}
			return listCategory;
		}  catch (Exception e) {
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
	public cpattachmentfileentryDto findCpattachByCategory(Integer categoryid) {
		PreparedStatement  statement = null;
		Connection con = null;
		ResultSet rs = null;
		cpattachmentfileentryDto cpa= new cpattachmentfileentryDto();
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT\r\n" + 
					"    cp.fileentryid AS fileentryid\r\n" + 
					"FROM\r\n" + 
					"         cpattachmentfileentry cp\r\n" + 
					"    INNER JOIN assetcategory ac ON ac.categoryid = cp.classpk\r\n" + 
					"WHERE\r\n" + 
					"    ac.categoryid = ?\r\n" + 
					"    ORDER BY\r\n" + 
					"    cp.modifieddate DESC\r\n" + 
					"OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY");
			statement.setInt(1, categoryid);
			rs= statement.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("fileentryid");
				cpa.setId(id);
			}
			return cpa;
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
	public DlfileEntryDto findDlFileEntryByCpa(Integer fileEntryId) {
		PreparedStatement  statement = null;
		Connection con = null;
		ResultSet rs = null;
		DlfileEntryDto dlfileEntryDto = new DlfileEntryDto();
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"SELECT\r\n" + 
					"    ac.uuid_       AS uuidcategory,\r\n" + 
					"    df.groupid     AS groupid,\r\n" + 
					"    df.fileentryid     AS fileentryid,\r\n" + 
					"    df.folderid    AS folderid,\r\n" + 
					"    df.uuid_       AS uuid,\r\n" + 
					"    df.filename    AS fileName,\r\n" + 
					"    df.title       AS title,\r\n" + 
					"    df.modifieddate AS modifiedDate\r\n" +
					"FROM\r\n" + 
					"         assetcategory ac\r\n" + 
					"    INNER JOIN cpattachmentfileentry  cp ON ac.categoryid = cp.classpk\r\n" + 
					"    INNER JOIN dlfileentry            df ON df.fileentryid = cp.fileentryid\r\n" + 
					"WHERE\r\n" + 
					"    cp.fileentryid = ?\r\n" + 
					"ORDER BY\r\n" + 
					"    df.modifieddate DESC\r\n" + 
					"OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY");
			statement.setInt(1, fileEntryId);
			rs = statement.executeQuery();
			while (rs.next()) {
				Timestamp modifiedDate=rs.getTimestamp("modifiedDate");
				String uuidCategory = rs.getString("uuidcategory");
				String title = rs.getString("title");
				String groupId= rs.getString("groupid");
				String folderId= rs.getString("folderid");
				String fileName= rs.getString("fileName");
				String uuid= rs.getString("uuid");
				String src="/documents/" + groupId + "/" + folderId + "/" + fileName + "/" + uuid;
				dlfileEntryDto.setUuidCategory(uuidCategory);
				dlfileEntryDto.setTitle(title);
				dlfileEntryDto.setModifiedDate(modifiedDate);
				dlfileEntryDto.setSrc(src);
			}
			return  dlfileEntryDto;
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
