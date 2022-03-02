package goverment.sql;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import goverment.dto.JournalAricleDto;
import goverment.dto.JournalArticleLocazationDto;
import goverment.dto.JournalFolderDto;

public class JournalSql {
	public JournalFolderDto findFolderByUuid(String uuidFolder, Long group) throws SQLException {
		PreparedStatement statement=null;
		Connection con = null;
		ResultSet rs=null;
		try {
			JournalFolderDto journalFolder = new JournalFolderDto();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("select jf.name as name, jf.folderid as folderid from journalfolder jf where jf.uuid_=? and jf.groupid=?");
			statement.setString(1, uuidFolder);
			statement.setLong(2, group);
			 rs = statement.executeQuery();
			while (rs.next()) {
				String name= rs.getString("name");
				Long folderid= rs.getLong("folderid");
				journalFolder.setName(name);
				journalFolder.setId(folderid);
			}
			return journalFolder;

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
	
	public List<JournalAricleDto> findAllJournalArticleDtoByFolderId(int page, int size,long folderid, long group) throws SQLException {
		PreparedStatement statement=null;
		Connection con = null;
		ResultSet rs=null;
		try {
			List<JournalAricleDto> listJournalArticleDtos = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT ja.resourceprimkey    AS resourceprimkey,\r\n" + 
					"					                MAX(ja.version)       AS version, Max(ja.id_)as id FROM\r\n" + 
					"					                journalarticle ja\r\n" + 
					"					            WHERE  ja.folderid=? and ja.status='0' and ja.groupid=?\r\n" + 
					"					           GROUP BY ja.resourceprimkey order by max(ja.id_) desc offset (?-1)*? rows fetch next ? rows only");
			statement.setLong(1, folderid);
			statement.setLong(2, group);
			statement.setInt(3, page);
			statement.setInt(4, size);
			statement.setInt(5, size);
			 rs = statement.executeQuery();
			while (rs.next()) {
				JournalAricleDto journalArticleDto = new JournalAricleDto();
				Integer version = rs.getInt("version");
				Integer resourcePrimkey = rs.getInt("resourcePrimkey");
				Long idJournalArticle = rs.getLong("id");
				journalArticleDto.setId(idJournalArticle);
				journalArticleDto.setResourcePrimkey(resourcePrimkey);
				journalArticleDto.setVersion(version);
				listJournalArticleDtos.add(journalArticleDto);
			}
			return listJournalArticleDtos;

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
	public List<JournalAricleDto> findAllJournalArticleByMaxVersion(long folderid,long groupId) throws Exception {
		PreparedStatement statement=null;
		Connection con = null;
		ResultSet rs=null;
		try {
			List<JournalAricleDto> listJournalArticle = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT ja.resourceprimkey    AS resourceprimkey,\r\n" + 
					"					                MAX(ja.version)       AS version, Max(ja.id_)as id FROM\r\n" + 
					"					                journalarticle ja\r\n" + 
					"					            INNER JOIN ddmstructure                ddm ON ddm.structurekey = ja.ddmstructurekey\r\n" + 
					"					            WHERE ja.folderid = ? and ja.groupid=?\r\n" + 
					"					            GROUP BY ja.resourceprimkey order by max(ja.id_) desc");
			statement.setLong(1, folderid);
			statement.setLong(2, groupId);
			rs = statement.executeQuery();
			while (rs.next()) {
				JournalAricleDto journalArticleDto = new JournalAricleDto();
				Integer resourceprimkey = rs.getInt("resourceprimkey");
				Integer maxVersion = rs.getInt("version");
				Long id= rs.getLong("id");
				journalArticleDto.setResourcePrimkey(resourceprimkey);
				journalArticleDto.setVersion(maxVersion);
				journalArticleDto.setId(id);
				listJournalArticle.add(journalArticleDto);
			}
			return listJournalArticle;
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
	
	
	public List<JournalAricleDto> findAllJournalArticleAndDontId(int page, int size,Long idJournalArticle,long groupId) throws SQLException {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<JournalAricleDto> listJournalArticle = new ArrayList<>();

			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT ja.resourceprimkey    AS resourceprimkey,\r\n" + 
					"					                MAX(ja.version)       AS version, Max(ja.id_)as id FROM\r\n" + 
					"					                journalarticle ja\r\n" + 
					"					            WHERE ja.folderid=? and ja.groupid=?\r\n" + 
					"					            GROUP BY ja.resourceprimkey order by max(ja.id_) desc offset (?-1)*? rows fetch next ? rows only");
			statement.setLong(1, idJournalArticle);
			statement.setLong(2, groupId);
			statement.setInt(3, page);
			statement.setInt(4, size);
			statement.setInt(5, size);
			rs = statement.executeQuery();
			while (rs.next()) {
				JournalAricleDto journalArticleDto = new JournalAricleDto();
				Integer resourceprimkey = rs.getInt("resourceprimkey");
				Integer maxVersion = rs.getInt("version");
				Long id = rs.getLong("id");
				journalArticleDto.setResourcePrimkey(resourceprimkey);
				journalArticleDto.setVersion(maxVersion);
				journalArticleDto.setId(id);
				listJournalArticle.add(journalArticleDto);
			}
			return listJournalArticle;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
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
	public JournalArticleLocazationDto findAllJornalArticleByArticlePK(long articlePk, long id) {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {

			con = DataAccess.getConnection();
			statement = con
					.prepareStatement(" select ja.title from journalarticlelocalization ja where ja.articlePk=?");
			statement.setLong(1, articlePk);
			rs = statement.executeQuery();
			JournalArticleLocazationDto journalArticleLocazationDto = new JournalArticleLocazationDto();
			while (rs.next()) {
				String title = rs.getString("title");
				journalArticleLocazationDto.setTitle(title);
				journalArticleLocazationDto.setId(id);
			}
			return journalArticleLocazationDto;
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
	public Integer countJournal(long folderid,long groupId) throws SQLException {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			Integer result=0;
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"SELECT count(*) as count  FROM\r\n" + 
					"					                journalarticle ja\r\n" + 
					"                            \r\n" + 
					"					            WHERE ja.folderid = ?  and ja.groupid=?");
			statement.setLong(1, folderid);
			statement.setLong(2, groupId);
			
			rs = statement.executeQuery();
			while (rs.next()) {
				result=rs.getInt("count");
			}
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
	}
	
	public JournalFolderDto findFolderByArticalId(long id,long groupId) throws SQLException {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			JournalFolderDto journalFolder= new JournalFolderDto();
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"select jf.folderid as folderid, jf.name as name"
					+ " from journalarticle ja inner join journalfolder jf on ja.folderid=jf.folderid where ja.id_=? and ja.groupid=?");
			statement.setLong(1, id);
			statement.setLong(2, groupId);
			
			rs = statement.executeQuery();
			while (rs.next()) {
				Long folderId= rs.getLong("folderid");
				String name= rs.getString("name");
				journalFolder.setId(folderId);
				journalFolder.setName(name);
			}
			return journalFolder;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
	}
}
