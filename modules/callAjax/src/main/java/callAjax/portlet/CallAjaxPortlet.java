package callAjax.portlet;

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

import callAjax.constants.CallAjaxPortletKeys;
import callAjax.dto.BlogsEntryDto;

/**
 * @author Dell
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CallAjax",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CallAjaxPortletKeys.CALLAJAX,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CallAjaxPortlet extends MVCPortlet {
	private List<BlogsEntryDto> findAllBlogsByIdCategory(long groupIdCategory) throws SQLException {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {

			List<BlogsEntryDto> listBlogsEntryDto = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"SELECT be.uuid_  AS uuidblogsentry,be.entryid  AS entryid,be.title AS titleblogsentry, be.description AS descriptiondlfileentry,dl.fileentryid AS fileentryid,dl.groupid  AS groupid,dl.folderid AS folderid, dl.title AS titledlfileentry, dl.uuid_ AS uuiddlfileentry FROM assetcategory ac INNER JOIN assetentryassetcategoryrel  aeac ON ac.categoryid = aeac.assetcategoryid INNER JOIN assetentry ae ON aeac.assetentryid = ae.entryid INNER JOIN blogsentry be ON ae.classpk = be.entryid INNER JOIN dlfileentry dl ON dl.fileentryid = be.smallimagefileentryid WHERE upper(REGEXP_REPLACE(ac.name,'[^a-z_A-Z ]')) = upper('TIN MI')  AND ae.classnameid = '31201'  AND be.status = '0' and be.groupId=? ORDER BY be.modifieddate DESC OFFSET 0 ROWS FETCH NEXT 4 ROWS ONLY");
			statement.setLong(1, groupIdCategory);
			rs = statement.executeQuery();
			while (rs.next()) {
				BlogsEntryDto blogsEntryDto = new BlogsEntryDto();
				String uuidBlogsEntry = rs.getString("uuidblogsentry");
				Integer entryId = rs.getInt("entryid");
				String titleBlogsEntry = rs.getString("titleblogsentry");
				String description = rs.getString("descriptiondlfileentry");
				Integer fileEntryId = rs.getInt("fileentryid");
				Integer groupId = rs.getInt("groupid");
				Integer folderId = rs.getInt("folderid");
				String titleDlFileEntry = rs.getString("titledlfileentry");
				String uuidDlFileEntry = rs.getString("uuiddlfileentry");
				blogsEntryDto.setDescription(description);
				blogsEntryDto.setEntryId(entryId);
				blogsEntryDto.setFileEntryId(fileEntryId);
				blogsEntryDto.setTitleBlogsEntry(titleBlogsEntry);
				blogsEntryDto.setUuidBlogsEntry(uuidBlogsEntry);
				String src="/documents/"+groupId+"/"+folderId+"/"+titleDlFileEntry+"/"+uuidDlFileEntry;
				blogsEntryDto.setSrcImage(src);
				listBlogsEntryDto.add(blogsEntryDto);
			}
			return listBlogsEntryDto;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally {
			rs.close();
			statement.close();
			con.close();
		}
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		try {
			
			JSONObject jsonUser=null;
			JSONArray usersJsonArray=JSONFactoryUtil.createJSONArray();
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			resourceResponse.setContentType("text/html;charset=UTF-8");
			List<BlogsEntryDto> listBlogsEntryDtos = findAllBlogsByIdCategory(themeDisplay.getScopeGroupId());
			for (BlogsEntryDto blogsEntryDto : listBlogsEntryDtos) {
				jsonUser=JSONFactoryUtil.createJSONObject();
				jsonUser.put("uuidBlogsEntry", blogsEntryDto.getUuidBlogsEntry());
				jsonUser.put("entryId",blogsEntryDto.getEntryId());
				jsonUser.put("titleBlogsEntry",blogsEntryDto.getTitleBlogsEntry());
				jsonUser.put("description",blogsEntryDto.getDescription());
				jsonUser.put("fileEntryId",blogsEntryDto.getFileEntryId());
				jsonUser.put("srcImage",blogsEntryDto.getSrcImage());
				usersJsonArray.put(jsonUser);	
			}
			PrintWriter out=resourceResponse.getWriter();
			out.print(usersJsonArray.toString());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		super.serveResource(resourceRequest, resourceResponse);
	}
	
	
}