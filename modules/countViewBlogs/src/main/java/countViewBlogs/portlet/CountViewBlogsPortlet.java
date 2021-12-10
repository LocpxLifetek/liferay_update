package countViewBlogs.portlet;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
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

import org.osgi.service.component.annotations.Component;

import countViewBlogs.constants.CountViewBlogsPortletKeys;
import countViewBlogs.dto.BlogsEntryDtos;

/**
 * @author java05
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CountViewBlogs", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CountViewBlogsPortletKeys.COUNTVIEWBLOGS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class CountViewBlogsPortlet extends MVCPortlet {

	private List<BlogsEntryDtos> findBlogsCountView(long groupIdCategory) {

		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<BlogsEntryDtos> listBlogsEntryDtos = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT\r\n" + "    be.title          AS title,\r\n"
					+ "    be.description    AS description,\r\n" + "    be.uuid_          AS uuid,\r\n"
					+ "    de.groupid        AS groupid,\r\n" + "    de.folderid       AS folderid,\r\n"
					+ "    de.title          AS titledlfile,\r\n" + "    de.uuid_          AS uuiddlfile\r\n"
					+ "FROM\r\n" + "         blogsentry be\r\n"
					+ "    INNER JOIN assetentry      ae ON be.entryid = ae.classpk\r\n"
					+ "    INNER JOIN viewcountentry  vc ON vc.classpk = ae.entryid\r\n"
					+ "     INNER JOIN dlfileentry                 de ON de.fileentryid = be.smallimagefileentryid\r\n"
					+ "WHERE\r\n" + "        ae.classnameid = '31201'\r\n" + "    AND be.groupid = ?\r\n"
					+ "    AND be.status = '0'\r\n" + "ORDER BY\r\n"
					+ "    vc.viewcount DESC OFFSET 0 ROWS FETCH NEXT 6 ROWS ONLY");
			statement.setLong(1, groupIdCategory);
			rs = statement.executeQuery();
			while (rs.next()) {
				BlogsEntryDtos blogsEntryDto = new BlogsEntryDtos();
				String title = rs.getString("title");
				String description = rs.getString("description");
				String uuid = rs.getString("uuid");
				Integer groupId = rs.getInt("groupId");
				Integer folderId = rs.getInt("folderId");
				String titleDlfile = rs.getString("titledlfile");
				String uuidDlfile = rs.getString("uuiddlfile");
				blogsEntryDto.setUuid(uuid);
				blogsEntryDto.setDescription(description);
				blogsEntryDto.setTitle(title);
				String src = "/documents" + "/" + groupId + "/" + folderId + "/" + titleDlfile + "/" + uuidDlfile;
				blogsEntryDto.setSrcImage(src);
				listBlogsEntryDtos.add(blogsEntryDto);
			}
			return listBlogsEntryDtos;
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
			int i = 0;
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			List<BlogsEntryDtos> listBlogsEntryDtos = findBlogsCountView(themeDisplay.getScopeGroupId());
			List<BlogsEntryDtos> manyBlog = new ArrayList<>();
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
			for (BlogsEntryDtos blogs : listBlogsEntryDtos) {
				i++;
				if (i == 1) {
					renderRequest.setAttribute("blogs", blogs);
				} else {
					manyBlog.add(blogs);
				}

			}
			renderRequest.setAttribute("listBlogsEntry", manyBlog);
		} catch (Exception e) {
			e.printStackTrace();

		}
		super.doView(renderRequest, renderResponse);
	}

}