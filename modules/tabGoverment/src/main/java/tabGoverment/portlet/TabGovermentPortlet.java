package tabGoverment.portlet;

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
import java.util.Date;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import tabGoverment.constants.TabGovermentPortletKeys;
import tabGoverment.dto.CategoryBusinessDto;
import tabGoverment.dto.CategoryDto;

/**
 * @author Dell
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=TabGoverment", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + TabGovermentPortletKeys.TABGOVERMENT, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class TabGovermentPortlet extends MVCPortlet {

	private List<CategoryBusinessDto> findCategoryByName(String name, long groupIdCategory) throws SQLException {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<CategoryBusinessDto> listCategoryBusinessDto = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"SELECT be.uuid_  AS uuidblogsentry,be.modifiedDate as modifiedDate,be.title AS titleblogsentry, be.description AS descriptiondlfileentry,dl.groupid  AS groupid,dl.folderid AS folderid, dl.title AS titledlfileentry, dl.uuid_ AS uuiddlfileentry FROM assetcategory ac INNER JOIN assetentryassetcategoryrel  aeac ON ac.categoryid = aeac.assetcategoryid INNER JOIN assetentry ae ON aeac.assetentryid = ae.entryid INNER JOIN blogsentry be ON ae.classpk = be.entryid INNER JOIN dlfileentry dl ON dl.fileentryid = be.smallimagefileentryid WHERE upper(REGEXP_REPLACE(ac.name,'[^a-z_A-Z ]')) = upper(?)  AND ae.classnameid = '31201'  AND be.status = '0' and ac.groupId=? ORDER BY be.modifieddate DESC OFFSET 0 ROWS FETCH NEXT 6 ROWS ONLY");
			statement.setString(1, name);
			statement.setLong(2, groupIdCategory);
			rs = statement.executeQuery();
			while (rs.next()) {
				CategoryBusinessDto categoryBusinessDto = new CategoryBusinessDto();
				String uuidBlogsEntry = rs.getString("uuidblogsentry");
				String titleBlogsEntry = rs.getString("titleblogsentry");
				String description = rs.getString("descriptiondlfileentry");
				Integer groupId = rs.getInt("groupid");
				Integer folderId = rs.getInt("folderid");
				String titleDlFileEntry = rs.getString("titledlfileentry");
				String uuidDlFileEntry = rs.getString("uuiddlfileentry");
				Date modifiedDate=rs.getDate("modifiedDate");
//				/documents/${blog.groupId}/${blog.folderId}/${blog.titleDlFileEntry}/${blog.uuidDlFileEntry}
				String src = "/documents/" + groupId + "/" + folderId + "/" + titleDlFileEntry + "/" + uuidDlFileEntry;
				categoryBusinessDto.setModifiedDate(modifiedDate);
				categoryBusinessDto.setDescription(description);
				categoryBusinessDto.setUuidBlogs(uuidBlogsEntry);
				categoryBusinessDto.setTitleBlogs(titleBlogsEntry);
				categoryBusinessDto.setSrc(src);
				listCategoryBusinessDto.add(categoryBusinessDto);
			}
			return listCategoryBusinessDto;
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

	private List<CategoryDto> findCategory() throws SQLException {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<CategoryDto> listCategoryDto = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"SELECT upper(REGEXP_REPLACE(ac.name,'[^a-z_A-Z ]')) as name,ac.categoryid from AssetCategory ac inner join assetvocabulary av on ac.vocabularyid=av.vocabularyid where  (upper(REGEXP_REPLACE(ac.name,'[^a-z_A-Z ]'))='S KIN' or upper(REGEXP_REPLACE(ac.name,'[^a-z_A-Z ]'))= 'CH O IU HNH' or upper(REGEXP_REPLACE(ac.name,'[^a-z_A-Z ]'))= 'TR LI CNG DN  DOANH NGHIP' or upper(REGEXP_REPLACE(ac.name,'[^a-z_A-Z ]'))= 'I NGOI') and (upper(REGEXP_REPLACE(av.name,'[^a-z_A-Z ]'))='TIN T C' or ac.vocabularyid='52270') order by ac.modifieddate desc");
			rs = statement.executeQuery();
			while (rs.next()) {
				CategoryDto categoryDto = new CategoryDto();
				String name = rs.getString("name");
				categoryDto.setName(name);
				listCategoryDto.add(categoryDto);
			}
			return listCategoryDto;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		} finally {
			rs.close();
			statement.close();
			con.close();
		}
	}

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);

			String urlCurrent = themeDisplay.getURLCurrent();
			String layoutUrl = themeDisplay.getLayoutFriendlyURL(layout);
			String[] url = urlCurrent.split(layoutUrl);
			String urlSite = null;
			int j = 0;
			for (String string : url) {
				j++;
				if (j == 1) {
					urlSite = string;
				}
			}
			renderRequest.setAttribute("url", urlSite);
			List<CategoryDto> listCategoryDto = findCategory();
			List<CategoryBusinessDto> listCategoryForeignAffair = new ArrayList<>();
			List<CategoryBusinessDto> listCategoryEvent = new ArrayList<>();
			List<CategoryBusinessDto> listCategoryDirectOperation = new ArrayList<>();
			List<CategoryBusinessDto> listCategoryAnswer = new ArrayList<>();
			for (CategoryDto categoryDto : listCategoryDto) {
				List<CategoryBusinessDto> findCategoryByName = findCategoryByName(categoryDto.getName(),
						themeDisplay.getScopeGroupId());
				int i = 0;
				for (CategoryBusinessDto category : findCategoryByName) {
					if (categoryDto.getName().equals("I NGOI")) {
						i++;
						if (i == 1) {
							CategoryBusinessDto foreignAffair = new CategoryBusinessDto(category.getUuidBlogs(),
									category.getTitleBlogs(), category.getDescription(), category.getSrc(),category.getModifiedDate());
							renderRequest.setAttribute("foreignAffair", foreignAffair);

						} else {
							listCategoryForeignAffair.add(category);
						}
					} else if (categoryDto.getName().equals("S KIN")) {
						i++;
						if (i == 1) {
							CategoryBusinessDto event = new CategoryBusinessDto(category.getUuidBlogs(),
									category.getTitleBlogs(), category.getDescription(), category.getSrc(),category.getModifiedDate());
							renderRequest.setAttribute("event", event);

						} else {
							listCategoryEvent.add(category);
						}
					} else if (categoryDto.getName().equals("CH O IU HNH")) {
						i++;
						if (i == 1) {
							CategoryBusinessDto directOperation = new CategoryBusinessDto(category.getUuidBlogs(),
									category.getTitleBlogs(), category.getDescription(), category.getSrc(),category.getModifiedDate());
							renderRequest.setAttribute("directOperation", directOperation);

						} else {
							listCategoryDirectOperation.add(category);
						}
					} else if (categoryDto.getName().equals("TR LI CNG DN  DOANH NGHIP")) {
						i++;
						if (i == 1) {
							CategoryBusinessDto answer = new CategoryBusinessDto(category.getUuidBlogs(),
									category.getTitleBlogs(), category.getDescription(), category.getSrc(),category.getModifiedDate());
							renderRequest.setAttribute("answer", answer);
						} else {
							listCategoryAnswer.add(category);
						}
					}

				}
			}
			renderRequest.setAttribute("listCategoryForeignAffair", listCategoryForeignAffair);
			renderRequest.setAttribute("listCategoryEvent", listCategoryEvent);
			renderRequest.setAttribute("listCategoryDirectOperation", listCategoryDirectOperation);
			renderRequest.setAttribute("listCategoryAnswer", listCategoryAnswer);
			
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}