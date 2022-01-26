package goverment.sql;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import goverment.dto.DlFileEntryMetaDataDto;
import goverment.dto.FileEntryDlFileDto;
import goverment.dto.VideoDto;
import goverment.dto.VideoLatestDto;

public class DlFileEntrySql {
	

	public VideoDto findListVideoDtoByFolder(long groupId, long fileEntryId) {

		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			VideoDto videoDto = new VideoDto();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT\r\n" + "    ddm.data_           AS data,\r\n"
					+ "    dlfm.fileentryid    AS entryid\r\n" + "FROM\r\n" + "         ddmcontent ddm\r\n"
					+ "    INNER JOIN dlfileentrymetadata  dlfm ON ddm.contentid = dlfm.ddmstorageid\r\n"
					+ "    INNER JOIN dlfileentrytype      dft ON dlfm.ddmstructureid = dft.datadefinitionid\r\n"
					+ "WHERE\r\n" + "        dft.uuid_ = '23797965-920c-6560-cf6f-1326ea5e5fea'\r\n"
					+ "    AND ddm.groupid =?\r\n" + "    AND dlfm.fileentryid =?");
			statement.setLong(1, groupId);
			statement.setLong(2, fileEntryId);
			rs = statement.executeQuery();
			while (rs.next()) {
				String data = rs.getString("data");
				long fileEntryIdDLFile = rs.getLong("entryId");
				videoDto.setData(data);
				videoDto.setFileEntryId(fileEntryIdDLFile);
				
			}
			return videoDto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
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

	
	public DlFileEntryMetaDataDto fileDlfileEntryMetaDataByFileVersionIdAndFileEntryId(long fileEntryId,long fileVersionId) {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			DlFileEntryMetaDataDto dlFileEntryMetaData=new DlFileEntryMetaDataDto();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("select dlfm.ddmstorageId as ddmstorageId from dlfileentrymetadata dlfm where dlfm.fileEntryId=? and dlfm.fileVersionId=?");
			statement.setLong(1, fileEntryId);
			statement.setLong(2, fileVersionId);
			rs=statement.executeQuery();
			while(rs.next()) {
				long ddmStorageId=rs.getLong("ddmstorageId");
				dlFileEntryMetaData.setDdmContentId(ddmStorageId);
			}
			return dlFileEntryMetaData;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
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
	
	public FileEntryDlFileDto findDlFileByGroupIdAndExtension(long groupId,long fileEntry) {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			
			con = DataAccess.getConnection();
			statement = con.prepareStatement("select dl.title as title,dl.fileEntryId as fileentryId,dl.folderId as folderId,dl.userId as userId, dl.uuid_ as uuid from dlfileentry dl where dl.extension='mp4' and dl.groupId=? and dl.fileEntryId=?");
			statement.setLong(1, groupId);
			statement.setLong(2, fileEntry);
			rs=statement.executeQuery();
			FileEntryDlFileDto fileEntryDlFileDto=new FileEntryDlFileDto();
			while(rs.next()) {
				Long fileEntryId=rs.getLong("fileEntryId");
				Long folderId=rs.getLong("folderId");
				Long userId=rs.getLong("userId");
				String uuid=rs.getString("uuid");
				String title=rs.getString("title");
				fileEntryDlFileDto.setTitle(title);
				fileEntryDlFileDto.setFileEntryId(fileEntryId);
				fileEntryDlFileDto.setFolderId(folderId);
				fileEntryDlFileDto.setUserId(userId);;
				fileEntryDlFileDto.setUuid(uuid);;
				
			}
			return fileEntryDlFileDto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}finally {
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
	
	
	public Integer countVideoImage(long groupId,String uuid) {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			Integer result=0;
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT\r\n" + 
					"    COUNT(*) as result\r\n" + 
					"FROM\r\n" + 
					"         assetcategory ac\r\n" + 
					"    INNER JOIN assetentryassetcategoryrel  aeac ON ac.categoryid = aeac.assetcategoryid\r\n" + 
					"    INNER JOIN assetentry                  ae ON ae.entryid = aeac.assetentryid\r\n" + 
					"    INNER JOIN dlfileentry                 dl ON ae.classpk = dl.fileentryid\r\n" + 
					"WHERE\r\n" + 
					"        ac.groupid = ?\r\n" + 
					"    AND ac.uuid_ = ?");
			statement.setLong(1, groupId);
			statement.setString(2, uuid);
			rs = statement.executeQuery();
			while (rs.next()) {
				result=rs.getInt("result");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
			// TODO: handle exception
		}finally {
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
	
	public List<VideoLatestDto> fileVideoLatestDto(String uuid,long groupIdDlFile){
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<VideoLatestDto> listVideoLatestDtos=new ArrayList<>();
			con = DataAccess.getConnection();
			statement=con.prepareStatement("select dl.userId as userId, dl.title as title, dl.fileentryid AS fileentryid,dl.groupid  AS groupid,dl.folderid AS folderid, dl.filename AS filename, dl.uuid_ AS uuiddlfileentry from DlFileEntry dl where dl.extension='mp4' and dl.uuid_ !=? and dl.groupId=? order by dl.modifiedDate desc OFFSET 0 ROWS FETCH NEXT 3 ROWS ONLY");
			statement.setString(1, uuid);
			statement.setLong(2, groupIdDlFile);
			rs=statement.executeQuery();
			while(rs.next()) {
				VideoLatestDto videoLatestDto=new VideoLatestDto();
				long fileEntryId=rs.getLong("fileentryid");
				long groupId =rs.getLong("groupid");
				long folderId=rs.getLong("folderid");
				long userId=rs.getLong("userId");
				String filename= rs.getString("filename");
				String uuiddlfileentry=rs.getString("uuiddlfileentry");
				String title =rs.getString("title");
				String src = "/documents" + "/" + groupId + "/" + folderId + "/" + filename + "/" + uuiddlfileentry;
				videoLatestDto.setUserId(userId);
				videoLatestDto.setFileEntryId(fileEntryId);
				videoLatestDto.setSrc(src);
				videoLatestDto.setTitle(title);
				videoLatestDto.setUuid(uuiddlfileentry);
				listVideoLatestDtos.add(videoLatestDto);
			}
			return listVideoLatestDtos;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
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
