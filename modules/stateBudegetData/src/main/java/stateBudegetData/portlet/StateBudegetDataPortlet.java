package stateBudegetData.portlet;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleDisplay;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import stateBudegetData.constants.StateBudegetDataPortletKeys;
import stateBudegetData.dto.JouranlArticleFolderDto;
import stateBudegetData.dto.JournalArticleDto;
import stateBudegetData.dto.JournalArticleLocalizationDto;
import stateBudegetData.dto.JournalArticleProjection;

/**
 * @author Dell
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=StateBudegetData", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + StateBudegetDataPortletKeys.STATEBUDEGETDATA,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class StateBudegetDataPortlet extends MVCPortlet {
	private PreparedStatement statement;
	Connection con = null;

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			List<JournalArticleLocalizationDto> listJournalArticleLocalizationDtos = new ArrayList<>();
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String idFolder = PortalUtil.getOriginalServletRequest(request).getParameter("idFolder");
			String articleId = PortalUtil.getOriginalServletRequest(request).getParameter("articleId");
			renderRequest.setAttribute("articleId", articleId);
			JouranlArticleFolderDto journalArticleFolderDto = findFolderNewsByTreepath();
			Integer id = null;
			if (idFolder == null) {
				id = journalArticleFolderDto.getId();
			} else if(idFolder !=null && articleId== null) {
				JournalArticleProjection journalAriArticleProjection = findAllJournalArticleByFolderId(Integer.parseInt(idFolder));
				id = journalAriArticleProjection.getFolderId();
			}
			else if (articleId != null) {
				String content = StringPool.BLANK;
				JournalArticleProjection journalAriArticleProjection = findAllJournalArticleByIdAndFolderId(Integer.parseInt(idFolder),Integer.parseInt(articleId));
				id=journalAriArticleProjection.getFolderId();
				ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
				JournalArticle journalArticle = JournalArticleLocalServiceUtil.getArticle(journalAriArticleProjection.getId());
				JournalArticleDisplay journalArticleDisplay = JournalArticleLocalServiceUtil.getArticleDisplay(
						themeDisplay.getScopeGroupId(), journalArticle.getArticleId(), "", "", themeDisplay);
				content = journalArticleDisplay.getContent();
				renderRequest.setAttribute("content", content);
			}
			JournalFolder journalFolder = JournalFolderLocalServiceUtil.getFolder(id);
			renderRequest.setAttribute("journalFolder", journalFolder.getName());

			List<JournalArticleDto> listJournalArticleDtos = findAllJournalArticleDtoByFolderId(id);
			for (JournalArticleDto journalArticleDto : listJournalArticleDtos) {
				JournalArticleLocalizationDto journalArticleLocalizationDtos = findAllJournalArticleLocalizationDtoByArticlePk(
						journalArticleDto.getId());
				listJournalArticleLocalizationDtos.add(journalArticleLocalizationDtos);
			}

			renderRequest.setAttribute("listJournalArticleLocalizationDtos", listJournalArticleLocalizationDtos);
		} catch (

		Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}

	private JouranlArticleFolderDto findFolderNewsByTreepath() throws SQLException {
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"select max(jf.folderId) as folderId from journalFolder jf where jf.treepath like '/188890/189130/%' and jf.status='0'");
			ResultSet rs = statement.executeQuery();
			JouranlArticleFolderDto stateBudegetDataDto = new JouranlArticleFolderDto();
			while (rs.next()) {
				Integer id = rs.getInt("folderId");
				stateBudegetDataDto.setId(id);
			}
			return stateBudegetDataDto;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally {
			statement.close();
			con.close();
		}
	}

	private JournalArticleProjection findAllJournalArticleByIdAndFolderId(Integer folderId,Integer id) throws SQLException {
		try {
			JournalArticleProjection journalArticleProjection = new JournalArticleProjection();
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"select jf.folderId as folderId,jf.id_ as id from journalArticle jf where jf.folderId=? and jf.id_=? and jf.status='0'");
			statement.setInt(1, folderId);
			statement.setInt(2, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Integer idFolder = rs.getInt("folderId");
				Integer idJouranlArticle=rs.getInt("id");
				journalArticleProjection.setId(idJouranlArticle);
				journalArticleProjection.setFolderId(idFolder);
			}
			return journalArticleProjection;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return null;
		}finally {
			statement.close();
			con.close();
		}
	}
	
	private JournalArticleProjection findAllJournalArticleByFolderId(Integer folderId) throws SQLException {
		try {
			JournalArticleProjection journalArticleProjection = new JournalArticleProjection();
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"select jf.folderId as folderId,jf.id_ as id from journalArticle jf where jf.folderId=? and jf.status='0'");
			statement.setInt(1, folderId);
			
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Integer idFolder = rs.getInt("folderId");
				Integer idJouranlArticle=rs.getInt("id");
				journalArticleProjection.setId(idJouranlArticle);
				journalArticleProjection.setFolderId(idFolder);
			}
			return journalArticleProjection;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return null;
		}finally {
			statement.close();
			con.close();
		}
	}
	private List<JournalArticleDto> findAllJournalArticleDtoByFolderId(Integer id) throws SQLException {
		try {
			List<JournalArticleDto> listJournalArticleDtos = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT ja.resourceprimkey    AS resourceprimkey,\r\n"
					+ "                MAX(ja.version)       AS version, Max(ja.id_)as id FROM\r\n"
					+ "                journalarticle ja\r\n"
					+ "            INNER JOIN assetentry                 ae ON ja.resourceprimkey = ae.classpk\r\n"
					+ "            INNER JOIN assetentryassetcategoryrel  aeac ON aeac.assetentryid = ae.entryid\r\n"
					+ "            INNER JOIN assetcategory               ac ON ac.categoryid = aeac.assetcategoryid\r\n"
					+ "            INNER JOIN ddmstructure                ddm ON ddm.structurekey = ja.ddmstructurekey\r\n"
					+ "            WHERE ac.categoryid='189211' and  ja.folderId=? and ja.status='0'\r\n "
					+ "            GROUP BY ja.resourceprimkey order by max(ja.id_) desc");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				JournalArticleDto journalArticleDto = new JournalArticleDto();
				Integer version = rs.getInt("version");
				Integer resourcePrimkey = rs.getInt("resourcePrimkey");
				Integer idJournalArticle = rs.getInt("id");
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
			statement.close();
			con.close();
		}
	}

	private JournalArticleLocalizationDto findAllJournalArticleLocalizationDtoByArticlePk(Integer id)
			throws SQLException {
		try {
	
			JournalArticleLocalizationDto journalArticleLocalizationDto = new JournalArticleLocalizationDto();
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"select jal.title as name, ja.id_ as id,ja.folderid as folderId from journalarticle ja inner join journalarticlelocalization jal on ja.id_=jal.articlepk where jal.articlepk=? and ja.status='0' ");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				Integer idJournalArticle=rs.getInt("id");
				Integer folderId=rs.getInt("folderId");
				journalArticleLocalizationDto.setName(name);
				journalArticleLocalizationDto.setFolderId(folderId);
				journalArticleLocalizationDto.setId(idJournalArticle);
			}
			
			return journalArticleLocalizationDto;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally {
			statement.close();
			con.close();
		}
	}
}