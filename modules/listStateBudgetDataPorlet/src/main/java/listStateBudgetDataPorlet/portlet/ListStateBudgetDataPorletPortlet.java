package listStateBudgetDataPorlet.portlet;

import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.model.Layout;
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

import listStateBudgetDataPorlet.constants.ListStateBudgetDataPorletPortletKeys;
import listStateBudgetDataPorlet.dto.JouranlArticleFolderDto;
import listStateBudgetDataPorlet.dto.JournalArticleDto;
import listStateBudgetDataPorlet.dto.JournalArticleLocalizationDto;
import listStateBudgetDataPorlet.dto.StateBudegetDataDto;

/**
 * @author Dell
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=ListStateBudgetDataPorlet", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ListStateBudgetDataPorletPortletKeys.LISTSTATEBUDGETDATAPORLET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class ListStateBudgetDataPorletPortlet extends MVCPortlet {
	

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);

			String urlCurrent = themeDisplay.getURLCurrent();
			String layoutUrl = themeDisplay.getLayoutFriendlyURL(layout);
			String[] url = urlCurrent.split(layoutUrl);
			String urlSite = null;
			int i = 0;
			for (String string : url) {
				i++;
				if (i == 1) {
					urlSite = string;
				}
			}
			renderRequest.setAttribute("url", urlSite);
			String idFolder = PortalUtil.getOriginalServletRequest(request).getParameter("idFolder");
			String articleId = PortalUtil.getOriginalServletRequest(request).getParameter("articleId");
			renderRequest.setAttribute("articleId", articleId);
			List<JournalArticleLocalizationDto> listJournalArticleLocalizationDtos = new ArrayList<>();
			if (idFolder != null) {

				List<JournalArticleDto> listJournalArticleDtos = findAllJournalArticleDtoByFolderId(
						Integer.parseInt(idFolder));
				JournalFolder journalFolder = JournalFolderLocalServiceUtil.getFolder(Long.parseLong(idFolder));
				renderRequest.setAttribute("journalFolder", journalFolder);
				for (JournalArticleDto journalArticleDto : listJournalArticleDtos) {
					JournalArticleLocalizationDto journalArticleLocalizationDto = findAllJournalArticleLocalizationDtoByArticlePk(
							journalArticleDto.getId());
					listJournalArticleLocalizationDtos.add(journalArticleLocalizationDto);
				}
			}
			renderRequest.setAttribute("listJournalArticleLocalizationDtos", listJournalArticleLocalizationDtos);
			List<StateBudegetDataDto> listBudegetDataDtos = findAllFolderIdByParentId(themeDisplay.getScopeGroupId());
			renderRequest.setAttribute("listBudegetDataDtos", listBudegetDataDtos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}

	private List<StateBudegetDataDto> findAllFolderIdByParentId(long groupId) throws SQLException {
		PreparedStatement statement=null;
		Connection con = null;
		ResultSet rs=null;
		try {
			List<StateBudegetDataDto> listBudegetDataDtos = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"select jf.name as name,jf.folderId as folderId from journalFolder jf where jf.treepath like '/188890/189130/%' and status='0' and jf.groupId=? and jf.parentfolderid='189130' order by jf.modifieddate desc");
			statement.setLong(1, groupId);
			rs = statement.executeQuery();
			while (rs.next()) {
				StateBudegetDataDto stateBudegetDataDto = new StateBudegetDataDto();
				String name = rs.getString("name");
				Integer folderId=rs.getInt("folderId");
				stateBudegetDataDto.setName(name);
				stateBudegetDataDto.setFolderId(folderId);
				listBudegetDataDtos.add(stateBudegetDataDto);
			}
			return listBudegetDataDtos;
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

	private List<JournalArticleDto> findAllJournalArticleDtoByFolderId(Integer id) throws SQLException {
		PreparedStatement statement=null;
		Connection con = null;
		ResultSet rs=null;
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
			 rs = statement.executeQuery();
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

	private JournalArticleLocalizationDto findAllJournalArticleLocalizationDtoByArticlePk(Integer id)
			throws SQLException {
		PreparedStatement statement=null;
		Connection con = null;
		ResultSet rs=null;
		try {
			
			JournalArticleLocalizationDto journalArticleLocalizationDto = new JournalArticleLocalizationDto();
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"select jal.title as name, ja.id_ as id,ja.folderid as folderId from journalarticle ja inner join journalarticlelocalization jal on ja.id_=jal.articlepk where jal.articlepk=? and ja.status='0' ");
			statement.setInt(1, id);
			 rs = statement.executeQuery();
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

	private JouranlArticleFolderDto findFolderNewsByTreepath() throws SQLException {
		PreparedStatement statement=null;
		Connection con = null;
		ResultSet rs=null;
		try {
			
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"select max(jf.folderId) as folderId from journalFolder jf where jf.treepath like '/188890/189130/%' and jf.status='0'");
			rs = statement.executeQuery();
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