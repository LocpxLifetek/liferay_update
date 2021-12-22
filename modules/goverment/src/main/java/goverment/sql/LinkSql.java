package goverment.sql;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import goverment.dto.JournalAricleDto;

public class LinkSql {
	public List<JournalAricleDto> findJournalArticle(long groupId) throws SQLException {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<JournalAricleDto> listJouranlArticleDtos = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT ja.resourceprimkey    AS resourceprimkey,\r\n"
					+ "					MAX(ja.version)       AS version FROM\r\n"
					+ "					journalarticle ja\r\n"
					+ "					INNER JOIN assetentry                 ae ON ja.resourceprimkey = ae.classpk\r\n"
					+ "					INNER JOIN assetentryassetcategoryrel  aeac ON aeac.assetentryid = ae.entryid\r\n"
					+ "					INNER JOIN assetcategory               ac ON ac.categoryid = aeac.assetcategoryid\r\n"
					+ "					INNER JOIN ddmstructure                ddm ON ddm.structurekey = ja.ddmstructurekey\r\n"
					+ "                    inner join journalFolder jf on jf.folderid=ja.folderid\r\n"
					+ "					 WHERE upper(REGEXP_REPLACE(jf.name,'[^a-z_A-Z ]')) = upper('LIN KT') AND upper(REGEXP_REPLACE(ac.name,'[^a-z_A-Z ]'))=upper('LIN KT') and ac.groupid=?\r\n"
					+ "					GROUP BY ja.resourceprimkey ");
			statement.setLong(1, groupId);
			rs = statement.executeQuery();
			while (rs.next()) {
				JournalAricleDto journalAricleDto = new JournalAricleDto();
				long resourceprimkey = rs.getLong("resourceprimkey");
				Integer maxVersion = rs.getInt("version");
				journalAricleDto.setResourcePrimkey(resourceprimkey);
				journalAricleDto.setVersion(maxVersion);
				listJouranlArticleDtos.add(journalAricleDto);
			}
			return listJouranlArticleDtos;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return null;
		} finally {
			rs.close();
			statement.close();
			con.close();

		}
	}
}
