package directOperation.portlet;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
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

import directOperation.constants.DirectOperationPortletKeys;
import directOperation.dto.CategoryDto;

/**
 * @author java05
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=DirectOperation", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + DirectOperationPortletKeys.DIRECTOPERATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class DirectOperationPortlet extends MVCPortlet {
	

	public Integer countBlogByCategory(String categoryId) throws SQLException {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"select count(*) as count from assetCAtegory ac inner join assetentryassetcategoryrel aeac on aeac.assetcategoryid=ac.categoryId inner join assetEntry ae on ae.entryId=aeac.assetentryid inner join BlogsEntry be on be.entryId=ae.classpk inner join DLFILEENTRY de on be.smallImageFileEntryId=de.fileEntryId  where ac.uuid_=? and be.status='0' order by be.modifieddate desc");
			statement.setString(1, categoryId);
			rs = statement.executeQuery();
			int countBlog=0;
			while (rs.next()) {
				countBlog = rs.getInt("count");
			}
			return countBlog;
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

	public List<BlogsEntryDto> pageableBlogsEntry(String uuid, Integer page, Integer size,long groupId) throws SQLException {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"select be.DESCRIPTION,be.TITLE as titleBlog,be.MODIFIEDDATE,be.UUID_ as uuidBlogs,de.GROUPID,de.FOLDERID,de.title as titleDlfile,de.UUID_ as uuidImage from assetCAtegory ac inner join assetentryassetcategoryrel aeac on aeac.assetcategoryid=ac.categoryId inner join assetEntry ae on ae.entryId=aeac.assetentryid inner join BlogsEntry be on be.entryId=ae.classpk inner join DLFILEENTRY de on be.smallImageFileEntryId=de.fileEntryId  where ac.uuid_=? and be.status='0' and be.groupId=? order by be.modifieddate desc OFFSET (?-1)*? ROWS FETCH NEXT ? ROWS ONLY");
			statement.setString(1, uuid);
			statement.setLong(2, groupId);		
			statement.setInt(3, page);
			statement.setInt(4, size);
			statement.setInt(5, size);
			rs = statement.executeQuery();
			List<BlogsEntryDto> listBlogsEntryDto = new ArrayList<>();
			while (rs.next()) {
				BlogsEntryDto blogsEntryDto = new BlogsEntryDto();
				Date modifiedDate = rs.getDate("MODIFIEDDATE");
				blogsEntryDto.setModifiedDate(modifiedDate);
				String description = rs.getString("DESCRIPTION");
				blogsEntryDto.setDescription(description);
				String titleBlog = rs.getString("titleBlog");
				blogsEntryDto.setTitleBlogs(titleBlog);
				String uuidBlogs = rs.getString("uuidBlogs");
				blogsEntryDto.setUuidBlogs(uuidBlogs);
				String groupIdImage = rs.getString("GROUPID");
				String folderImage = rs.getString("FOLDERID");
				String uuidImage = rs.getString("uuidImage");
				String titleImage = rs.getString("titleDlfile");
				String srcImage = "/documents" + "/" + groupIdImage + "/" + folderImage + "/" + titleImage
						+ "/" + uuidImage;
				blogsEntryDto.setSrcImage(srcImage);
				listBlogsEntryDto.add(blogsEntryDto);
			}
			return listBlogsEntryDto;
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
	
	public CategoryDto findCategoryByUuid(String uuid,long groupId) {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement("Select ac.uuid_ as uuid, ac.name as name from assetCategory ac where ac.uuid_=? and ac.groupId=?");
			statement.setString(1, uuid);
			statement.setLong(2, groupId);
			rs =statement.executeQuery();
			CategoryDto categoryDto=new CategoryDto();
			while(rs.next()) {
				String uuidCategory=rs.getString("uuid");
				String name =rs.getString("name");
				categoryDto.setName(name);
				categoryDto.setUuid(uuidCategory);
			}
			return categoryDto;
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

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String error = null;
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String uuid = PortalUtil.getOriginalServletRequest(request).getParameter("uuid");
			String pageDetail = PortalUtil.getOriginalServletRequest(request).getParameter("page");
			String uuidCategory=(uuid == null ? "ff2977fb-16c1-17fa-b087-87636b71184b" : uuid);
			Integer page=Integer.parseInt(pageDetail == null ? "1" : pageDetail);
			Integer size=10;
			Integer count=countBlogByCategory(uuidCategory);
			int result = (int) Math.ceil((float) count / size);
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
			if(page<0 && page>result) {
				error="Error !!!";
				renderRequest.setAttribute("error", error);
			}
			List<BlogsEntryDto> listBlogEntryDtos=pageableBlogsEntry(uuidCategory,page,size,themeDisplay.getScopeGroupId());
			CategoryDto categoryDto=findCategoryByUuid(uuidCategory,themeDisplay.getScopeGroupId());
			renderRequest.setAttribute("assetCategory", categoryDto);
			renderRequest.setAttribute("listBlogEntryDtos", listBlogEntryDtos);
			renderRequest.setAttribute("currentPage", page);
			renderRequest.setAttribute("totalPage", result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}

}