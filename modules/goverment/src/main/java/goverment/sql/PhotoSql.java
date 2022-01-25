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
	public CpattachmentfileentryDto findCpattachByCategory(Integer categoryid) {
		PreparedStatement  statement = null;
		Connection con = null;
		ResultSet rs = null;
		CpattachmentfileentryDto cpa= new CpattachmentfileentryDto();
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT\r\n" + 
					"    ac.uuid_ as uuid,ac.name as name,dl.fileentryid AS fileentryid,cp.fileentryid AS cpfileentryid,dl.groupid  AS groupid,dl.folderid AS folderid, dl.filename AS filename, dl.uuid_ AS uuiddlfileentry\r\n" + 
					"FROM\r\n" + 
					"         cpattachmentfileentry cp\r\n" + 
					"    INNER JOIN assetcategory ac ON ac.categoryid = cp.classpk\r\n" + 
					"    Inner join DlFileEntry dl on cp.fileentryid=dl.fileentryid\r\n" + 
					"WHERE\r\n" + 
					"    ac.categoryid = ?\r\n" + 
					"ORDER BY\r\n" + 
					"    cp.modifieddate DESC\r\n" + 
					"OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY");
			statement.setInt(1, categoryid);
			rs= statement.executeQuery();
			while (rs.next()) {
				String uuid=rs.getString("uuid");
				String name =rs.getString("name");
				Integer fileEntryId= rs.getInt("fileentryid");
				Integer flIdCpa= rs.getInt("cpfileentryid");
				Integer groupId = rs.getInt("groupid");
				long folderId = rs.getLong("folderid");
				String filename = rs.getString("filename");
				String uuidDlFileEntry = rs.getString("uuiddlfileentry");
				String src = "/documents" + "/" + groupId + "/" + folderId + "/" + filename + "/" + uuidDlFileEntry;
				cpa.setFlIdCpa(flIdCpa);
				cpa.setFileEntryId(fileEntryId);
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
				Timestamp modifiedDates=rs.getTimestamp("modifiedDate");
				String uuidCategory = rs.getString("uuidcategory");
				String title = rs.getString("title");
				Integer groupId= rs.getInt("groupid");
				long folderId= rs.getLong("folderid");
				String fileName= rs.getString("fileName");
				String uuid= rs.getString("uuid");
				String src="/documents/" + groupId + "/" + folderId + "/" + fileName + "/" + uuid;
				dlfileEntryDto.setUuidCategory(uuidCategory);
				dlfileEntryDto.setTitle(title);
				dlfileEntryDto.setModifiedDate(modifiedDates);
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
					"    dl.title          AS title,\r\n" + 
					"    ac.uuid_          AS uuidCa\r\n" +
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
				String uuidCa= rs.getString("uuidCa");
				Integer groupId = rs.getInt("groupid");
				Integer folderId = rs.getInt("folderid");
				String fileName = rs.getString("fileName");
				String title = rs.getString("title");
				Integer id= rs.getInt("fileentryid");
				String src="/documents/" + groupId + "/" + folderId + "/" + fileName + "/" + uuid1;
				
				
				dlFileEntryDto.setSrc(src);
				dlFileEntryDto.setUuidCategory(uuidCa);
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
	public CategoryDto findCategoryByParentId(Integer parentId) {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"SELECT ac.name AS name, ac.groupid AS groupid, ac.categoryid AS id, ac.uuid_ as uuid FROM assetcategory ac WHERE ac.parentcategoryid=? ORDER BY ac.modifieddate DESC OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY");
			statement.setLong(1, parentId);
			rs = statement.executeQuery();
			CategoryDto categoryDto = new CategoryDto();
			while (rs.next()) {
				
				String name = rs.getString("name");
				Integer group = rs.getInt("groupId");
				Integer id = rs.getInt("id");
				String uuid= rs.getString("uuid");
				categoryDto.setId(id);
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
