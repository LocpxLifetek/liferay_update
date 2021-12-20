package Deatil_Photo_Categories.portlet;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
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

import Deatil_Photo_Categories.constants.Deatil_Photo_CategoriesPortletKeys;

/**
 * @author java03
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Deatil_Photo_Categories",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + Deatil_Photo_CategoriesPortletKeys.DEATIL_PHOTO_CATEGORIES,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class Deatil_Photo_CategoriesPortlet extends MVCPortlet {
	private DLfileEntryDto dlfile(String uuid) throws SQLException {
		PreparedStatement statement=null;
		Connection con=null;
		ResultSet rs=null;
		DLfileEntryDto dlfileDto= new DLfileEntryDto();
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT\r\n" + 
					"    df.title AS title,\r\n" + 
					"    df.fileentryid       AS fileentryid\r\n" + 
					"FROM\r\n" + 
					"    assetcategory ac \r\n" + 
					"    INNER JOIN cpattachmentfileentry cp ON ac.categoryid = cp.classpk\r\n" + 
					"    INNER JOIN dlfileentry df ON df.fileentryid = cp.fileentryid\r\n" + 
					"WHERE\r\n" + 
					"    ac.uuid_ = ?");
			statement.setString(1, uuid);
			rs=statement.executeQuery();
			while(rs.next()) {
				String title= rs.getString("title");
				Integer id= rs.getInt("fileentryid");
				dlfileDto.setId(id);
				dlfileDto.setTitle(title);
			}
			return dlfileDto;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			if(statement != null) {
				statement.close();
			}
			if(con != null) {
				con.close();
			}
			if(rs != null) {
				rs.close();
			}
		}
	}
	private List<DLfileEntryDto> findAllDLfileEntryDtos(String uuid) throws SQLException {
		PreparedStatement statement=null;
		Connection con=null;
		ResultSet rs=null;
		try {
			
			List<DLfileEntryDto> listDlFileEntry = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("\r\n" + 
					"SELECT\r\n" + 
					"    dl.groupid     AS groupid,\r\n" + 
					"    dl.folderid    AS folderid,\r\n" + 
					"    dl.uuid_       AS uuid,\r\n" + 
					"    dl.fileentryid       AS fileentryid,\r\n" + 
					"    dl.filename    AS filename,\r\n" + 
					"    dl.title       AS title\r\n" + 
					"FROM\r\n" + 
					"         assetcategory ac\r\n" + 
					"    INNER JOIN assetentryassetcategoryrel  aeac ON ac.categoryid = aeac.assetcategoryid\r\n" + 
					"    INNER JOIN assetentry                  ae ON aeac.assetentryid = ae.entryid\r\n" + 
					"    INNER JOIN dlfileentry                 dl ON dl.fileentryid = ae.classpk\r\n" + 
					"WHERE\r\n" + 
					"      ac.uuid_ =?\r\n" + 
					"ORDER BY\r\n" + 
					"    dl.modifieddate DESC OFFSET 0 ROWS FETCH NEXT 9 ROWS only");
			statement.setString(1, uuid);
			rs=statement.executeQuery();
			
			while(rs.next()) {
				DLfileEntryDto dlFileEntryDto=new DLfileEntryDto();
				String uuid1= rs.getString("uuid");
				Integer groupId = rs.getInt("groupid");
				Integer folderId = rs.getInt("folderid");
				String fileName = rs.getString("fileName");
				String title = rs.getString("title");
				Integer id= rs.getInt("fileentryid");
				String src="/documents/" + groupId + "/" + folderId + "/" + fileName + "/" + uuid1;
				
				
				dlFileEntryDto.setSrc(src);
				dlFileEntryDto.setTitle(title);
				dlFileEntryDto.setId(id);
				
				listDlFileEntry.add(dlFileEntryDto);
				
			}
			return listDlFileEntry;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			if(statement != null) {
				statement.close();
			}
			if(con != null) {
				con.close();
			}
			if(rs != null) {
				rs.close();
			}
		}
	}

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		try {
			Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);
			ThemeDisplay themDisplay=(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String urlCurrent=themDisplay.getURLCurrent();
			String layoutUrl =themDisplay.getLayoutFriendlyURL(layout);
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
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String uuid =  PortalUtil.getOriginalServletRequest(request).getParameter("id");
			DLfileEntryDto dlfileEntry= dlfile(uuid);
			renderRequest.setAttribute("dlfileEntry", dlfileEntry);
			List<DLfileEntryDto> dLfileEntryDtos=findAllDLfileEntryDtos(uuid);
			List<DLfileEntryDto> listDlfile= new ArrayList<>();
			AssetEntry assetEntry=AssetEntryLocalServiceUtil.getEntry("com.liferay.document.library.kernel.model.DLFileEntry", dlfileEntry.getId());
			AssetRenderer<?> assetRender=assetEntry.getAssetRenderer();
			String docUrl=assetRender.getURLDownload(themDisplay);
			for (DLfileEntryDto listDlf : dLfileEntryDtos) {
				listDlf.setUrl(docUrl);
				listDlfile.add(listDlf);
			}
			renderRequest.setAttribute("listDlfile", listDlfile);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}