package videoDetail.portlet;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
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

import videoDetail.constants.VideoDetailPortletKeys;
import videoDetail.dto.DlFileEntryDto;

/**
 * @author Dell
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=VideoDetail", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + VideoDetailPortletKeys.VIDEODETAIL,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class VideoDetailPortlet extends MVCPortlet {
	private List<DlFileEntryDto> findDlFileEntryDto(long folderIdDlFileEntry, long groupIdDlFileEntry,String uuidDlFileEntry) throws SQLException {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<DlFileEntryDto> listDlFileEntryDtos = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"select dl.groupid as groupId,dl.folderid as folderId,dl.extension as extension,dl.filename as fileName,dl.uuid_ as uuid,dl.title as title,dl.mimetype as mimeType from dlFileEntry dl where dl.folderId=? and dl.groupId=? and dl.uuid_ !=? ORDER BY dl.modifieddate DESC OFFSET 0 ROWS FETCH NEXT 6 ROWS ONLY");
			statement.setLong(1, folderIdDlFileEntry);
			statement.setLong(2, groupIdDlFileEntry);
			statement.setString(3, uuidDlFileEntry);
			rs=statement.executeQuery();
			while(rs.next()) {
				DlFileEntryDto dlFileEntryDto=new DlFileEntryDto();
				Integer groupId = rs.getInt("groupId");
				Integer folderId = rs.getInt("folderId");
				String fileName = rs.getString("fileName");
				String uuid = rs.getString("uuid");
				String title = rs.getString("title");
				String mimeType = rs.getString("mimeType");
				String extension = rs.getString("extension");
				String titleDlFileEntry = null;
				if (title.contains(extension)) {
					titleDlFileEntry = title.replace(extension, "");
				} else {
					titleDlFileEntry = title;
				}
				String src = "/documents/" + groupId + "/" + folderId + "/" + fileName + "/" + uuid;
				dlFileEntryDto.setSrc(src);
				dlFileEntryDto.setTitle(titleDlFileEntry);
				dlFileEntryDto.setUuid(uuid);
				dlFileEntryDto.setMimeType(mimeType);
				listDlFileEntryDtos.add(dlFileEntryDto);
			}
			return listDlFileEntryDtos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}finally {
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
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String uuid = PortalUtil.getOriginalServletRequest(request).getParameter("uuid");
			String error=null;

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
			if(uuid==null) {
				error="NOT FOUND 404!!!";
			}
			else {
				
				DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntryByUuidAndGroupId(uuid,
						themeDisplay.getScopeGroupId());
				
				if(dlFileEntry == null) {
					error=themeDisplay.getURLCurrent();
				}else {
					String title=null;
					if(dlFileEntry.getTitle().contains(dlFileEntry.getExtension())) {
						title=dlFileEntry.getTitle().replace(dlFileEntry.getExtension(), "");
					}
					else {
						title=dlFileEntry.getTitle();
					}
					List<DlFileEntryDto> listDlFileEntryDtos=findDlFileEntryDto(dlFileEntry.getFolderId(), themeDisplay.getScopeGroupId(),uuid);
					DLFolder dlFolder=DLFolderLocalServiceUtil.getFolder(dlFileEntry.getFolderId());
					AssetEntry assetEntry=AssetEntryLocalServiceUtil.incrementViewCounter(themeDisplay.getCompanyId(), themeDisplay.getUserId(), DLFileEntry.class.getName(), dlFileEntry.getFileEntryId());

					renderRequest.setAttribute("listDlFileEntryDtos", listDlFileEntryDtos);
					renderRequest.setAttribute("dlFolder", dlFolder);
					renderRequest.setAttribute("title", title);
				}
				renderRequest.setAttribute("dlFileEntry", dlFileEntry);
			}
			renderRequest.setAttribute("error", error);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		super.doView(renderRequest, renderResponse);
	}
}