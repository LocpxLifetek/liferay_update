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
import goverment.dto.CpattachmentfileentryDto;
import goverment.dto.DlFileEntryDto;



public class PhotoSql {
	
	public CategoryDto findCategoryByParentId(long id){
		PreparedStatement  statement = null;
		Connection con = null;
		ResultSet rs = null;
		CategoryDto category= new CategoryDto();
		try {
			con = DataAccess.getConnection();
			statement= con.prepareStatement("select ac.categoryid as CategoryId, ac.groupid as groupId, ac.name as name from assetcategory ac where ac.parentcategoryid=? order by ac.modifieddate desc OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY");
			statement.setLong(1, id);
			rs = statement.executeQuery();
			while (rs.next()) {
				Integer categoryId = rs.getInt("CategoryId");
				Integer groupId= rs.getInt("groupId");
				String name= rs.getString("name");
				
				category.setId(categoryId);
				category.setGroupId(groupId);
				category.setName(name);
				
			}
			return category;
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
	public CpattachmentfileentryDto findCpattachByCategory(long categoryid) {
		PreparedStatement  statement = null;
		Connection con = null;
		ResultSet rs = null;
		CpattachmentfileentryDto cpa= new CpattachmentfileentryDto();
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT\r\n" + 
					"    ac.uuid_ as uuid,ac.name as name,dl.fileentryid AS fileentryid,dl.groupid  AS groupid,dl.folderid AS folderid, dl.filename AS filename, dl.uuid_ AS uuiddlfileentry\r\n" + 
					"FROM\r\n" + 
					"         cpattachmentfileentry cp\r\n" + 
					"    INNER JOIN assetcategory ac ON ac.categoryid = cp.classpk\r\n" + 
					"    Inner join DlFileEntry dl on cp.fileentryid=dl.fileentryid\r\n" + 
					"WHERE\r\n" + 
					"    ac.categoryid = ?\r\n" + 
					"ORDER BY\r\n" + 
					"    cp.modifieddate DESC\r\n" + 
					"OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY");
			statement.setLong(1, categoryid);
			rs= statement.executeQuery();
			while (rs.next()) {
				String uuid=rs.getString("uuid");
				String name =rs.getString("name");
				
				Integer groupId = rs.getInt("groupid");
				Integer folderId = rs.getInt("folderid");
				String filename = rs.getString("filename");
				String uuidDlFileEntry = rs.getString("uuiddlfileentry");
				String src = "/documents" + "/" + groupId + "/" + folderId + "/" + filename + "/" + uuidDlFileEntry;
				cpa.setName(name);
				cpa.setSrc(src);
				cpa.setUuid(uuid);
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
	public DlFileEntryDto findDlFileEntryByCpa(Integer fileEntryId) {
		PreparedStatement  statement = null;
		Connection con = null;
		ResultSet rs = null;
		DlFileEntryDto dlfileEntryDto = new DlFileEntryDto();
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"SELECT\r\n" + 
					"    ac.name        AS namecategory,\r\n" + 
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
	public DlFileEntryDto dlfile(String uuid) throws SQLException {
		PreparedStatement statement=null;
		Connection con=null;
		ResultSet rs=null;
		DlFileEntryDto dlfileDto= new DlFileEntryDto();
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT\r\n" + 
					"    df.title AS title,\r\n" + 
					"    df.fileentryid       AS fileentryid\r\n" + 
					"FROM\r\n" + 
					"    assetcategory ac \r\n" + 
					"    INNER JOIN cpattachmentfileentry cp ON ac.categoryid = cp.classpk\r\n" + 
					"    INNER JOIN dlfileentry df ON df.fileentryid = cp.fileentryid\r\n" + 
					"WHERE\r\n" + 
					"    ac.uuid_ = ?");
			statement.setString(1, uuid);
			rs=statement.executeQuery();
			while(rs.next()) {
				String title= rs.getString("title");
				Integer id= rs.getInt("fileentryid");
				dlfileDto.setId(id);
				dlfileDto.setTitle(title);
			}
			return dlfileDto;
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
	public List<DlFileEntryDto> findAllDLfileEntryDtos(String uuid) throws SQLException {
		PreparedStatement statement=null;
		Connection con=null;
		ResultSet rs=null;
		try {
			
			List<DlFileEntryDto> listDlFileEntry = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT\r\n" + 
					"    dl.groupid        AS groupid,\r\n" + 
					"    dl.folderid       AS folderid,\r\n" + 
					"    dl.uuid_          AS uuid,\r\n" + 
					"    dl.fileentryid    AS fileentryid,\r\n" + 
					"    dl.filename       AS filename,\r\n" + 
					"    dl.title          AS title\r\n" + 
					"FROM\r\n" + 
					"         assetcategory ac\r\n" + 
					"    INNER JOIN assetentryassetcategoryrel  aeac ON ac.categoryid = aeac.assetcategoryid\r\n" + 
					"    INNER JOIN assetentry                  ae ON aeac.assetentryid = ae.entryid\r\n" + 
					"    INNER JOIN dlfileentry                 dl ON dl.fileentryid = ae.classpk\r\n" + 
					"WHERE\r\n" + 
					"    ac.uuid_ = ?\r\n" + 
					"ORDER BY\r\n" + 
					"    dl.modifieddate DESC\r\n" + 
					"OFFSET 0 ROWS FETCH NEXT 9 ROWS ONLY");
			statement.setString(1, uuid);
			rs=statement.executeQuery();
			
			while(rs.next()) {
				DlFileEntryDto dlFileEntryDto=new DlFileEntryDto();
				String uuid1= rs.getString("uuid");
				Integer groupId = rs.getInt("groupid");
				Integer folderId = rs.getInt("folderid");
				String fileName = rs.getString("fileName");
				String title = rs.getString("title");
				Integer id= rs.getInt("fileentryid");
				String src="/documents/" + groupId + "/" + folderId + "/" + fileName + "/" + uuid1;
				
				
				dlFileEntryDto.setSrc(src);
				dlFileEntryDto.setTitle(title);
				dlFileEntryDto.setId(id);
				
				listDlFileEntry.add(dlFileEntryDto);
				
			}
			return listDlFileEntry;
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
