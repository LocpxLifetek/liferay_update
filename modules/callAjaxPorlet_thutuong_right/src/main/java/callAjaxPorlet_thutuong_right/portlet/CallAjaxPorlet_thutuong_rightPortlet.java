package callAjaxPorlet_thutuong_right.portlet;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import callAjaxPorlet_thutuong_right.constants.CallAjaxPorlet_thutuong_rightPortletKeys;
import callAjaxPorlet_thutuong_right.dto.BlogsEntryDto;

/**
 * @author Dell
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CallAjaxPorlet_thutuong_right",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CallAjaxPorlet_thutuong_rightPortletKeys.CALLAJAXPORLET_THUTUONG_RIGHT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CallAjaxPorlet_thutuong_rightPortlet extends MVCPortlet {
	
	private List<BlogsEntryDto> findAllBlogsByIdCategory(long groupId) throws SQLException {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {

			List<BlogsEntryDto> listBlogsEntryDto = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT be.entryid  AS entryid,be.title AS titleblogsentry, be.description AS descriptiondlfileentry FROM assetcategory ac INNER JOIN assetentryassetcategoryrel  aeac ON ac.categoryid = aeac.assetcategoryid INNER JOIN assetentry ae ON aeac.assetentryid = ae.entryid INNER JOIN blogsentry be ON ae.classpk = be.entryid WHERE ac.categoryid = '72065'  AND be.status = '0' and be.groupId=? ORDER BY be.modifieddate DESC OFFSET 0 ROWS FETCH NEXT 2 ROWS ONLY");
			statement.setLong(1, groupId);
			rs = statement.executeQuery();
			while (rs.next()) {
				BlogsEntryDto blogsEntryDto = new BlogsEntryDto();
				Integer entryId = rs.getInt("entryid");
				String titleBlogsEntry = rs.getString("titleblogsentry");
				String description = rs.getString("descriptiondlfileentry");
				blogsEntryDto.setDescription(description);
				blogsEntryDto.setEntryId(entryId);
				blogsEntryDto.setTitleBlogsEntry(titleBlogsEntry);
				listBlogsEntryDto.add(blogsEntryDto);
			}
			return listBlogsEntryDto;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally {
			statement.close();
			con.close();
			rs.close();

		}
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		try {
			JSONObject jsonObject=null;
			JSONArray jsonArray=JSONFactoryUtil.createJSONArray();
			ThemeDisplay themeDisplay=(ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			resourceResponse.setContentType("text/html;charset=UTF-8");
			List<BlogsEntryDto> listBlogsEntryDto=findAllBlogsByIdCategory(themeDisplay.getScopeGroupId());
			for (BlogsEntryDto blogsEntryDto : listBlogsEntryDto) {
				jsonObject=JSONFactoryUtil.createJSONObject();
				jsonObject.put("entryId", blogsEntryDto.getEntryId());
				jsonObject.put("description", blogsEntryDto.getDescription());
				jsonObject.put("titleBlogsEntry", blogsEntryDto.getTitleBlogsEntry());
				jsonArray.put(jsonObject);
			}
			PrintWriter out=resourceResponse.getWriter();
			out.print(jsonArray.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		super.serveResource(resourceRequest, resourceResponse);
	}
}