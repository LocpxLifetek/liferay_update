package listVideoMedia.portlet;

import com.liferay.document.library.kernel.model.DLFolder;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import listVideoMedia.constants.ListVideoMediaPortletKeys;
import listVideoMedia.dto.DlFileEntryDto;
import listVideoMedia.dto.DlFolder;
import listVideoMedia.dto.DlFolderDto;

/**
 * @author Dell
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=ListVideoMedia",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ListVideoMediaPortletKeys.LISTVIDEOMEDIA,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ListVideoMediaPortlet extends MVCPortlet {
	private List<DlFileEntryDto> findDlFileEntryByTreepath(long groupIdDlFileEntry,Integer folderIdDlfileEntry) throws SQLException{
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<DlFileEntryDto> listDlFileEntryDto=new ArrayList<>();
			con = DataAccess.getConnection();
			statement=con.prepareStatement("select dl.groupid as groupId,dl.folderid as folderId,dl.extension as extension,dl.filename as fileName,dl.uuid_ as uuid,dl.title as title,dl.mimetype as mimeType from dlfileentry dl inner join dlfolder df on dl.folderid=df.folderid where dl.folderid=? and dl.groupid=? ORDER BY dl.modifieddate DESC OFFSET 0 ROWS FETCH NEXT 3 ROWS ONLY");
			statement.setInt(1, folderIdDlfileEntry);
			statement.setLong(2, groupIdDlFileEntry);
			rs=statement.executeQuery();
			while(rs.next()) {
				DlFileEntryDto dlFileEntryDto=new DlFileEntryDto();
				Integer groupId=rs.getInt("groupId");
				Integer folderId=rs.getInt("folderId");
				String fileName=rs.getString("fileName");
				String uuid=rs.getString("uuid");
				String title=rs.getString("title");
				String extension = rs.getString("extension");
				String mimeType=rs.getString("mimeType");
				dlFileEntryDto.setMimeType(mimeType);
				String titleDlFileEntry=null;
				if(title.contains(extension)) {
					
					titleDlFileEntry=title.replace(extension, "");
				}else {
					titleDlFileEntry=title;
				}
				String src="/documents/" + groupId + "/" + folderId + "/" + fileName + "/" + uuid;
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
		}finally {
			rs.close();
			statement.close();
			con.close();

		}
	}
	
	private DlFolderDto findDlFolderDtoByName() throws SQLException {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			DlFolderDto dlFolderDto=new DlFolderDto();
			con = DataAccess.getConnection();
			statement=con.prepareStatement("select df1.folderid as id,df1.treepath as treepath from dlFolder df inner join dlFolder df1 on df.folderId=df1.parentfolderid where upper(REGEXP_REPLACE(df.name,'[^a-z_A-Z ]')) = upper('media') and upper(REGEXP_REPLACE(df1.name,'[^a-z_A-Z ]')) = upper('Video')");
			rs=statement.executeQuery();
			while(rs.next()) {
				Integer id=rs.getInt("id");
				String treePath=rs.getString("treepath");
				dlFolderDto.setFolderId(id);
				dlFolderDto.setTreePath(treePath);
			}
			return dlFolderDto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			rs.close();
			statement.close();
			con.close();

		}
	}
	
	private List<DlFolder> findFolderByParentFolder(Integer parentId,long groupId) throws SQLException{
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<DlFolder> listDlFolder=new ArrayList<>();
			con=DataAccess.getConnection();
			statement=con.prepareStatement("select df.uuid_ as uuid,df.name as name, df.folderId as folderId from dlFolder df where df.parentfolderid=? and df.groupId=? order by df.modifieddate desc");
			statement.setInt(1, parentId);
			statement.setLong(2, groupId);
			rs=statement.executeQuery();
			while(rs.next()) {
				DlFolder dlFolder=new DlFolder();
				String name=rs.getString("name");
				Integer folderId=rs.getInt("folderId");
				String uuid=rs.getString("uuid");
				dlFolder.setUuid(uuid);
				dlFolder.setFolderId(folderId);
				dlFolder.setName(name);
				listDlFolder.add(dlFolder);
			}
			return listDlFolder;
		} catch (Exception e) {
			// TODO: handle exception
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
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			DlFolderDto dlFolderDto=findDlFolderDtoByName();
			List<DlFolder> listDlFolders=findFolderByParentFolder(dlFolderDto.getFolderId(),themeDisplay.getScopeGroupId());
			
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
			List<Map<DlFolder,List<DlFileEntryDto>>> listDlFileEntryAndNameFolder=new ArrayList<>();
			for (DlFolder dlFolder : listDlFolders) {
//				DlFolder dlFolde=new DlFolder();
				Map<DlFolder,List<DlFileEntryDto>> mapDlFileEntry=new LinkedHashMap<>();
				List<DlFileEntryDto> listDlFileEntryDtos=findDlFileEntryByTreepath(themeDisplay.getScopeGroupId(), dlFolder.getFolderId());
				System.out.println("");
				if(listDlFileEntryDtos.isEmpty()==false) {
					
					mapDlFileEntry.put(dlFolder,listDlFileEntryDtos);
					listDlFileEntryAndNameFolder.add(mapDlFileEntry);
				}
			}
			renderRequest.setAttribute("listDlFileEntryAndNameFolder", listDlFileEntryAndNameFolder);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}