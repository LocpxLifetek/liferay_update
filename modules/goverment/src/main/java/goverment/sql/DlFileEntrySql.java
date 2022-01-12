package goverment.sql;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import goverment.dto.DlFileEntryDto;
import goverment.dto.VideoDto;

public class DlFileEntrySql {
	public List<VideoDto> findListVideoDtoByContent(long groupId) {
		List<VideoDto> listVideoDtos = new ArrayList<>();
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT\r\n" + "    ddm.data_ as data, dlfm.fileentryid as entryId\r\n"
					+ "FROM\r\n" + "    ddmcontent ddm\r\n"
					+ "    INNER JOIN dlfileentrymetadata  dlfm ON ddm.contentid = dlfm.ddmstorageid\r\n"
					+ "    Inner join dlfileentrytype dft on dlfm.ddmstructureid=dft.datadefinitionid\r\n"
					+ "WHERE dft.uuid_='23797965-920c-6560-cf6f-1326ea5e5fea' and ddm.groupid=? ORDER BY\r\n"
					+ "    ddm.modifieddate DESC\r\n" + "OFFSET 0 ROWS FETCH NEXT 3 ROWS ONLY");
			statement.setLong(1, groupId);
			rs = statement.executeQuery();
			while (rs.next()) {
				VideoDto videoDto = new VideoDto();
				String data = rs.getString("data");
				long fileEntryId = rs.getLong("entryId");
				videoDto.setData(data);
				videoDto.setFileEntryId(fileEntryId);
				listVideoDtos.add(videoDto);
			}
			return listVideoDtos;
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

	public List<DlFileEntryDto> findDlFileEntryByFolderId(long groupId, long folderId,long fileEntryID,Integer number) {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<DlFileEntryDto> listDlFileEntry = new ArrayList<>();
			con = DataAccess.getConnection();
			statement =con.prepareStatement("select df.fileentryid as fileEntryId, df.groupId as groupId from dlfileentry df where df.folderId=? and df.groupId=? and df.fileEntryId!=? order by df.modifieddate desc OFFSET 0 ROWS FETCH NEXT ? ROWS only");
			statement.setLong(2, groupId);
			statement.setLong(1, folderId);
			statement.setLong(3, fileEntryID);
			statement.setInt(4, number);
			rs = statement.executeQuery();
			while(rs.next()) {
				DlFileEntryDto dlFileEntryDto=new DlFileEntryDto();
				Integer fileEntryId=rs.getInt("fileEntryId");
				Integer groupIdDlfile=rs.getInt("groupId");
				dlFileEntryDto.setId(fileEntryId);
				dlFileEntryDto.setGroupId(groupIdDlfile);
				listDlFileEntry.add(dlFileEntryDto);
			}
			return listDlFileEntry;
		} catch (Exception e) {
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
	
	public List<VideoDto> findListVideoDtoByContentAndFolder(long groupId,long folderId,Integer page, Integer size) {
		List<VideoDto> listVideoDtos = new ArrayList<>();
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT\r\n" + 
					"    ddm.data_           AS data,\r\n" + 
					"    dlfm.fileentryid    AS entryid\r\n" + 
					"FROM\r\n" + 
					"         ddmcontent ddm\r\n" + 
					"    INNER JOIN dlfileentrymetadata  dlfm ON ddm.contentid = dlfm.ddmstorageid\r\n" + 
					"    INNER JOIN dlFileEntry df on df.fileentryid=dlfm.fileentryid\r\n" + 
					"    INNER JOIN dlfileentrytype      dft ON dlfm.ddmstructureid = dft.datadefinitionid\r\n" + 
					"WHERE\r\n" + 
					"        dft.uuid_ = '23797965-920c-6560-cf6f-1326ea5e5fea'\r\n" + 
					"    AND ddm.groupid = ?\r\n" + 
					"    AND df.folderid = ? ORDER BY df.modifieddate DESC OFFSET (?-1)*? ROWS FETCH NEXT ? ROWS ONLY");
			statement.setLong(1, groupId);
			statement.setLong(2, folderId);
			statement.setInt(3, page);
			statement.setInt(4, size);
			statement.setInt(5, size);;
			rs = statement.executeQuery();
			while (rs.next()) {
				VideoDto videoDto = new VideoDto();
				String data = rs.getString("data");
				long fileEntryId = rs.getLong("entryId");
				videoDto.setData(data);
				videoDto.setFileEntryId(fileEntryId);
				listVideoDtos.add(videoDto);
			}
			return listVideoDtos;
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
	
	public Integer countVideoImage(long groupId,long folderId) {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			Integer result=0;
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT\r\n" + 
					"    COUNT(*) as result\r\n" + 
					"FROM\r\n" + 
					"         ddmcontent ddm\r\n" + 
					"    INNER JOIN dlfileentrymetadata  dlfm ON ddm.contentid = dlfm.ddmstorageid\r\n" + 
					"    INNER JOIN dlfileentry          df ON df.fileentryid = dlfm.fileentryid\r\n" + 
					"    INNER JOIN dlfileentrytype      dft ON dlfm.ddmstructureid = dft.datadefinitionid\r\n" + 
					"WHERE\r\n" + 
					"        dft.uuid_ = '23797965-920c-6560-cf6f-1326ea5e5fea'\r\n" + 
					"    AND ddm.groupid = ?\r\n" + 
					"    AND df.folderid = ?");
			statement.setLong(1, groupId);
			statement.setLong(2, folderId);
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

}
