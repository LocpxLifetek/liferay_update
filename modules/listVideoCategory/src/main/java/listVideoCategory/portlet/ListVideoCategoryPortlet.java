package listVideoCategory.portlet;

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

import listVideoCategory.constants.ListVideoCategoryPortletKeys;
import listVideoCategory.dto.DlFileEntryDto;
import listVideoCategory.dto.DlFileFolderDto;

/**
 * @author Dell
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=ListVideoCategory",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ListVideoCategoryPortletKeys.LISTVIDEOCATEGORY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ListVideoCategoryPortlet extends MVCPortlet {
	private List<DlFileEntryDto> findDlFileEntry(String uuidDlFileEntry, long groupIdDlFileEntry, Integer page, Integer size) throws SQLException{
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<DlFileEntryDto> listDlFileEntryDto = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"select dl.groupid as groupId,dl.folderid as folderId,dl.extension as extension,dl.filename as fileName,dl.uuid_ as uuid,dl.title as title,dl.mimetype as mimeType from dlfileentry dl inner join dlfolder df on dl.folderid=df.folderid where df.uuid_=? and dl.groupid=? ORDER BY dl.modifieddate DESC OFFSET (?-1)*? ROWS FETCH NEXT ? ROWS ONLY");
			statement.setString(1, uuidDlFileEntry);
			statement.setLong(2, groupIdDlFileEntry);
			statement.setInt(3, page);
			statement.setInt(4, size);
			statement.setInt(5, size);
			rs = statement.executeQuery();
			while (rs.next()) {
				DlFileEntryDto dlFileEntryDto = new DlFileEntryDto();
				Integer groupId = rs.getInt("groupId");
				Integer folderId = rs.getInt("folderId");
				String fileName = rs.getString("fileName");
				String uuid = rs.getString("uuid");
				String title = rs.getString("title");
				String extension = rs.getString("extension");
				String mimeType = rs.getString("mimeType");
				dlFileEntryDto.setMimeType(mimeType);
				String titleDlFileEntry = null;
				if (title.contains(extension)) {
					titleDlFileEntry = title.replace(extension, "");
				} else {
					titleDlFileEntry = title;
				}
				String src = "/documents/" + groupId + "/" + folderId + "/" + fileName + "/" + uuid;
				dlFileEntryDto.setSrc(src);
				dlFileEntryDto.setTitle(titleDlFileEntry);
				dlFileEntryDto.setUuidDlFileEntry(uuid);
				dlFileEntryDto.setExtension(extension);
				listDlFileEntryDto.add(dlFileEntryDto);
			}
			return listDlFileEntryDto;
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
	
	private DlFileFolderDto findDlFileFolderDtoByUuid(String uuid,long groupId) throws SQLException {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con=DataAccess.getConnection();
			statement=con.prepareStatement("select df.name as name,(SELECT COUNT(*) FROM DlFileEntry  dl1 inner join dlfolder df1 on dl1.folderid=df1.folderid WHERE df1.uuid_=? and dl1.groupid=?) as count from dlfolder df where df.uuid_=? and df.groupId=?");
			statement.setString(1, uuid);
			statement.setLong(2, groupId);
			statement.setString(3, uuid);
			statement.setLong(4, groupId);
			rs=statement.executeQuery();
			DlFileFolderDto dlFileFolderDto=new DlFileFolderDto();
			while(rs.next()) {
				String name=rs.getString("name");
				Integer count=rs.getInt("count");
				dlFileFolderDto.setCount(count);
				dlFileFolderDto.setName(name);
			}
			return dlFileFolderDto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			                                       
		}finally {
			rs.close();
			statement.close();
			con.close();

		}
	}
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
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
			String uuid = PortalUtil.getOriginalServletRequest(request).getParameter("uuid");
			String pageDetail = PortalUtil.getOriginalServletRequest(request).getParameter("page");
			String uuidCategory=(uuid == null ? "b7f719b5-27b3-729c-e7b0-b7d15f23d7c4" : uuid);
			DlFileFolderDto fileFolderDto=findDlFileFolderDtoByUuid(uuidCategory, themeDisplay.getScopeGroupId());
			Integer page=Integer.parseInt(pageDetail == null ? "1" : pageDetail);
			Integer size=6;
			int result = (int) Math.ceil((float) fileFolderDto.getCount() / size);
			List<DlFileEntryDto> listDlFileEntryDtos=findDlFileEntry(uuidCategory, themeDisplay.getScopeGroupId(), page, size);
			renderRequest.setAttribute("fileFolderDto", fileFolderDto);
			renderRequest.setAttribute("listDlFileEntryDtos", listDlFileEntryDtos);
			renderRequest.setAttribute("currentPage", page);
			renderRequest.setAttribute("totalPage", result);
			renderRequest.setAttribute("uuid", uuidCategory);
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}