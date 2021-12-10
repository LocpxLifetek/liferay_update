package detailBoCongAn.portlet;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.blogs.model.BlogsEntry;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import detailBoCongAn.constants.DetailBoCongAnPortletKeys;
import detailBoCongAn.dto.BlogsEntryDto;

/**
 * @author java05
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=DetailBoCongAn", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + DetailBoCongAnPortletKeys.DETAILBOCONGAN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class DetailBoCongAnPortlet extends MVCPortlet {
	private BlogsEntryDto findBlogByCategory(String uuid, long groupIdCategory) {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
//			List<BlogsEntryDto> listBlogsEntryDto = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"select be.uuid_ as uuid,be.entryId as entryId, be.companyId as companyId, be.userId as userId,be.title as title,be.content as content,be.description as description,be.modifieddate as modifiedDate,be.groupId as groupId,ac.name as name,ac.parentcategoryid as parentCategoryId,ac.uuid_ as uuidCategory,ac.categoryId as categoryId from blogsentry be inner join assetEntry ae on be.entryId=ae.classpk inner join assetentryassetcategoryrel aeac on ae.entryId=aeac.assetentryid inner join assetCategory ac on aeac.assetcategoryid=ac.categoryid where be.status='0' and be.uuid_=? and  ac.parentcategoryid !=0 and  ae.classnameid='31201' and be.groupId=? and  ac.treepath like '/108693/%' order by aeac.assetentryassetcategoryrelid desc");
			statement.setString(1, uuid);
			statement.setLong(2, groupIdCategory);
			rs = statement.executeQuery();
			BlogsEntryDto blogsEntryDto = new BlogsEntryDto();
			while (rs.next()) {
				String uuidBlogs = rs.getString("uuid");
				String content = rs.getString("content");
				String description = rs.getString("description");
				String title = rs.getString("title");
				String nameCategory = rs.getString("name");
				String uuidCategory = rs.getString("uuidCategory");
				Timestamp modifiedDate = rs.getTimestamp("modifiedDate");
				Integer parentCategoryId = rs.getInt("parentCategoryId");
				Integer categoryId = rs.getInt("categoryId");
				Integer groupId = rs.getInt("groupId");
				Integer entryId = rs.getInt("entryId");
				Integer companyId = rs.getInt("companyId");
				Integer userId = rs.getInt("userId");
				blogsEntryDto.setEntryId(entryId);
				blogsEntryDto.setCompanyId(companyId);
				blogsEntryDto.setUserId(userId);
				blogsEntryDto.setTitle(title);
				blogsEntryDto.setUuidCategory(uuidCategory);
				blogsEntryDto.setCategoryId(categoryId);
				blogsEntryDto.setGroupId(groupId);
				blogsEntryDto.setContent(content);
				blogsEntryDto.setDescription(description);
				blogsEntryDto.setModifiedDate(modifiedDate);
				blogsEntryDto.setNameCategory(nameCategory);
				blogsEntryDto.setParentCategoryId(parentCategoryId);
				blogsEntryDto.setUuid(uuidBlogs);
//				listBlogsEntryDto.add(blogsEntryDto);

			}
			return blogsEntryDto;

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

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);
			
			String urlCurrent=themeDisplay.getURLCurrent();
			String layoutUrl =themeDisplay.getLayoutFriendlyURL(layout);
			String[] url=urlCurrent.split(layoutUrl);
			String urlSite=null;
			int i=0;
			for (String string : url) {
				i++;
				if(i==1) {
					urlSite=string;
				}
			}
			renderRequest.setAttribute("url", urlSite);	
			
			String uuid = PortalUtil.getOriginalServletRequest(request).getParameter("id");
			BlogsEntryDto blogsEntryDtos = findBlogByCategory(uuid, themeDisplay.getScopeGroupId());
			AssetEntry assetEntry = AssetEntryLocalServiceUtil.incrementViewCounter(blogsEntryDtos.getCompanyId(),
					blogsEntryDtos.getUserId(), BlogsEntry.class.getName(), blogsEntryDtos.getEntryId());

			renderRequest.setAttribute("listBlogsEntryDto", blogsEntryDtos);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		super.doView(renderRequest, renderResponse);
	}

}