package Photo.portlet;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import Photo.constants.PhotoPortletKeys;

/**
 * @author java03
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Photo",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + PhotoPortletKeys.PHOTO,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PhotoPortlet extends MVCPortlet {
	private CategoryDto findIdCategory() {
		PreparedStatement  statement = null;
		Connection con = null;
		ResultSet rs = null;
		CategoryDto categoryDto= new CategoryDto();
		try {
			con = DataAccess.getConnection();
			statement= con.prepareStatement("select ac.categoryid as categoryId, ac.name as name from assetcategory ac where ac.categoryid='621347'");
			rs = statement.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("categoryId");
				String name= rs.getString("name");
				categoryDto.setName(name);
				categoryDto.setId(id);
				
			}
			return categoryDto;
		}  catch (Exception e) {
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
	private List<CategoryDto> findCategoryByParent(){
		PreparedStatement  statement = null;
		Connection con = null;
		ResultSet rs = null;
		List<CategoryDto> listCategory= new ArrayList<>();
		try {
			con = DataAccess.getConnection();
			statement= con.prepareStatement("select ac.categoryid as CategoryId, ac.groupid as groupId from assetcategory ac where ac.parentcategoryid='621347'");
			rs = statement.executeQuery();
			while (rs.next()) {
				CategoryDto categoryDto= new CategoryDto();
				Integer id = rs.getInt("CategoryId");
				Integer groupId= rs.getInt("groupId");
				
				categoryDto.setId(id);
				categoryDto.setGroupId(groupId);
				listCategory.add(categoryDto);
				
			}
			return listCategory;
		}  catch (Exception e) {
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
	private cpattachmentfileentryDto findCpattachByCategory(Integer categoryid) {
		PreparedStatement  statement = null;
		Connection con = null;
		ResultSet rs = null;
		cpattachmentfileentryDto cpa= new cpattachmentfileentryDto();
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT\r\n" + 
					"    cp.fileentryid AS fileentryid\r\n" + 
					"FROM\r\n" + 
					"         cpattachmentfileentry cp\r\n" + 
					"    INNER JOIN assetcategory ac ON ac.categoryid = cp.classpk\r\n" + 
					"WHERE\r\n" + 
					"    ac.categoryid = ?\r\n" + 
					"    ORDER BY\r\n" + 
					"    cp.modifieddate DESC\r\n" + 
					"OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY");
			statement.setInt(1, categoryid);
			rs= statement.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("fileentryid");
				cpa.setId(id);
			}
			return cpa;
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
	private DLfileEntryDto findDlFileEntryByCpa(Integer fileEntryId) {
		PreparedStatement  statement = null;
		Connection con = null;
		ResultSet rs = null;
		DLfileEntryDto dlfileEntryDto = new DLfileEntryDto();
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"SELECT\r\n" + 
					"    ac.name        AS namecategory,\r\n" + 
					"    ac.uuid_       AS uuidcategory,\r\n" + 
					"    df.groupid     AS groupid,\r\n" + 
					"    df.fileentryid     AS fileentryid,\r\n" + 
					"    df.folderid    AS folderid,\r\n" + 
					"    df.uuid_       AS uuid,\r\n" + 
					"    df.filename    AS fileName,\r\n" + 
					"    df.title       AS title,\r\n" + 
					"    df.modifieddate AS modifiedDate\r\n" +
					"FROM\r\n" + 
					"         assetcategory ac\r\n" + 
					"    INNER JOIN cpattachmentfileentry  cp ON ac.categoryid = cp.classpk\r\n" + 
					"    INNER JOIN dlfileentry            df ON df.fileentryid = cp.fileentryid\r\n" + 
					"WHERE\r\n" + 
					"    cp.fileentryid = ?\r\n" + 
					"ORDER BY\r\n" + 
					"    df.modifieddate DESC\r\n" + 
					"OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY");
			statement.setInt(1, fileEntryId);
			rs = statement.executeQuery();
			while (rs.next()) {
				Timestamp modifiedDate=rs.getTimestamp("modifiedDate");
				String uuidCategory = rs.getString("uuidcategory");
				String nameCategory = rs.getString("namecategory");
				Integer groupId = rs.getInt("groupid");
				Integer folderId = rs.getInt("folderid");
				Integer id= rs.getInt("fileentryid");
				String fileName = rs.getString("fileName");
				String uuid = rs.getString("uuid");
				String title = rs.getString("title");
				String src="/documents/" + groupId + "/" + folderId + "/" + fileName + "/" + uuid;
				dlfileEntryDto.setName(nameCategory);
				dlfileEntryDto.setId(id);
				dlfileEntryDto.setUuidCategory(uuidCategory);
				dlfileEntryDto.setTitle(title);
				dlfileEntryDto.setSrc(src);
				dlfileEntryDto.setFileName(fileName);
				dlfileEntryDto.setModifiedDate(modifiedDate);
			}
			return  dlfileEntryDto;
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
			CategoryDto category= findIdCategory();
			List<CategoryDto> listCategoryDtos= findCategoryByParent();
			List<cpattachmentfileentryDto> listCpa= new ArrayList<>();
			List<DLfileEntryDto> listDlefile= new ArrayList<>();
			for (CategoryDto categoryDto : listCategoryDtos) {
				cpattachmentfileentryDto cpaAttach= findCpattachByCategory(categoryDto.getId());
				listCpa.add(cpaAttach);
			}	
			
			for (cpattachmentfileentryDto cpas : listCpa) {
				if(cpas.getId() !=null && listDlefile.size()<3) {
					
					DLfileEntryDto dlfile= findDlFileEntryByCpa(cpas.getId());
					listDlefile.add(dlfile);
				}
			}
			renderRequest.setAttribute("category", category);
			renderRequest.setAttribute("listDlefile", listDlefile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}

}