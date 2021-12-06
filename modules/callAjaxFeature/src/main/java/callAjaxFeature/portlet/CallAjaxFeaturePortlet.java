package callAjaxFeature.portlet;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import callAjaxFeature.constants.CallAjaxFeaturePortletKeys;
import callAjaxFeature.dto.BlogsEntryDto;

/**
 * @author Dell
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CallAjaxFeature", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CallAjaxFeaturePortletKeys.CALLAJAXFEATURE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class CallAjaxFeaturePortlet extends MVCPortlet {
	private List<BlogsEntryDto> findAllBlogsByIdCategory(long groupIdCategory) throws SQLException {
		List<BlogsEntryDto> listBlogsEntryDto = new ArrayList<>();
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {

			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"SELECT be.uuid_  AS uuidblogsentry,be.entryid  AS entryid,be.title AS titleblogsentry, be.description AS descriptiondlfileentry,be.modifieddate AS modifieddate,dl.fileentryid AS fileentryid,dl.groupid  AS groupid,dl.folderid AS folderid, dl.title AS titledlfileentry, dl.uuid_ AS uuiddlfileentry FROM assetcategory ac INNER JOIN assetentryassetcategoryrel  aeac ON ac.categoryid = aeac.assetcategoryid INNER JOIN assetentry ae ON aeac.assetentryid = ae.entryid INNER JOIN blogsentry be ON ae.classpk = be.entryid INNER JOIN dlfileentry dl ON dl.fileentryid = be.smallimagefileentryid WHERE upper(REGEXP_REPLACE(ac.name,'[^a-z_A-Z ]')) = upper('TIN TC NI BT')  AND ae.classnameid = '31201'  AND be.status = '0' and ac.groupId=? ORDER BY be.modifieddate DESC OFFSET 0 ROWS FETCH NEXT 7 ROWS ONLY");
			statement.setLong(1, groupIdCategory);
			rs = statement.executeQuery();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/YYYY");

			while (rs.next()) {
				BlogsEntryDto blogsEntryDto = new BlogsEntryDto();
				String uuidBlogsEntry = rs.getString("uuidblogsentry");
				Integer entryId = rs.getInt("entryid");
				String titleBlogsEntry = rs.getString("titleblogsentry");
				String description = rs.getString("descriptiondlfileentry");
				Date modifiedDate = rs.getDate("modifieddate");
				String date = simpleDateFormat.format(modifiedDate);
				Integer fileEntryId = rs.getInt("fileentryid");
				Integer groupId = rs.getInt("groupid");
				Integer folderId = rs.getInt("folderid");
				String titleDlFileEntry = rs.getString("titledlfileentry");
				String uuidDlFileEntry = rs.getString("uuiddlfileentry");
				blogsEntryDto.setDescription(description != null ? description : "");
				blogsEntryDto.setEntryId(entryId);
				blogsEntryDto.setFileEntryId(fileEntryId);
				blogsEntryDto.setTitleBlogsEntry(titleBlogsEntry);
				blogsEntryDto.setUuidBlogsEntry(uuidBlogsEntry);
				String src = "/documents/" + groupId + "/" + folderId + "/" + titleDlFileEntry + "/" + uuidDlFileEntry;
				blogsEntryDto.setSrcImage(src);
				blogsEntryDto.setModifiedDate(date);
				listBlogsEntryDto.add(blogsEntryDto);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			rs.close();
			statement.close();
			con.close();

		}
		return listBlogsEntryDto;
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		try {
			
			JSONObject jsonUser = null;
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			resourceResponse.setContentType("text/html;charset=UTF-8");
			List<BlogsEntryDto> listBlogsEntryDto = findAllBlogsByIdCategory(themeDisplay.getScopeGroupId());
			for (BlogsEntryDto blogsEntryDto : listBlogsEntryDto) {
				jsonUser = JSONFactoryUtil.createJSONObject();
				jsonUser.put("uuidBlogsEntry", blogsEntryDto.getUuidBlogsEntry());
				jsonUser.put("entryId", blogsEntryDto.getEntryId());
				jsonUser.put("titleBlogsEntry", blogsEntryDto.getTitleBlogsEntry());
				jsonUser.put("description", blogsEntryDto.getDescription());
				jsonUser.put("modifiedDate", blogsEntryDto.getModifiedDate());
				jsonUser.put("fileEntryId", blogsEntryDto.getFileEntryId());
				jsonUser.put("srcImage", blogsEntryDto.getSrcImage());
				jsonArray.put(jsonUser);
			}
			PrintWriter out = resourceResponse.getWriter();
			out.print(jsonArray.toString());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.serveResource(resourceRequest, resourceResponse);
	}
}