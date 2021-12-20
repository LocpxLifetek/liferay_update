package table_category_photo.portlet;

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

import table_category_photo.constants.Table_category_photoPortletKeys;
import table_category_photo.dto.CategoryDto;
import table_category_photo.dto.DlfileEntryDto;
import table_category_photo.dto.cpattachmentfileentryDto;





/**
 * @author java03
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Table_category_photo",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + Table_category_photoPortletKeys.TABLE_CATEGORY_PHOTO,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class Table_category_photoPortlet extends MVCPortlet {
	private CategoryDto categoryDto()
	{
		PreparedStatement  statement = null;
		Connection con = null;
		ResultSet rs = null;
		CategoryDto category= new CategoryDto();
		try {
			con = DataAccess.getConnection();
			statement= con.prepareStatement("SELECT ac.name as name , ac.categoryid as categoryId FROM assetcategory ac WHERE ac.categoryid='621347'");
			rs = statement.executeQuery();
			while (rs.next()) {
				String name= rs.getString("name");
				Integer categoryId= rs.getInt("categoryId");
				category.setCategoryName(name);
				category.setId(categoryId);
			}
			return category;
		}
			catch (Exception e) {
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
	private List<CategoryDto> findCategoryByParent(Integer parentId){
		PreparedStatement  statement = null;
		Connection con = null;
		ResultSet rs = null;
		List<CategoryDto> listCategory= new ArrayList<>();
		try {
			con = DataAccess.getConnection();
			statement= con.prepareStatement("select ac.categoryid as CategoryId, ac.groupid as groupId from assetcategory ac where ac.parentcategoryid=?");
			statement.setInt(1, parentId);
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
		table_category_photo.dto.cpattachmentfileentryDto cpa= new cpattachmentfileentryDto();
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
	private DlfileEntryDto findDlFileEntryByCpa(Integer fileEntryId) {
		PreparedStatement  statement = null;
		Connection con = null;
		ResultSet rs = null;
		DlfileEntryDto dlfileEntryDto = new DlfileEntryDto();
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"SELECT\r\n" + 
					"    ac.uuid_       AS uuidcategory,\r\n" + 
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
				String title = rs.getString("title");
				dlfileEntryDto.setUuidCategory(uuidCategory);
				dlfileEntryDto.setTitle(title);
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
			CategoryDto categoryName= categoryDto();
			List<CategoryDto> listCategory= findCategoryByParent(categoryName.getId());
			List<cpattachmentfileentryDto> listCpa= new ArrayList<>();
			List<DlfileEntryDto> listDlefile= new ArrayList<>();
			for (CategoryDto categoryDto : listCategory) {
				cpattachmentfileentryDto cpaAttach= findCpattachByCategory(categoryDto.getId());
				listCpa.add(cpaAttach);
			}	
			
			for (cpattachmentfileentryDto cpas : listCpa) {
				if(cpas.getId() !=null ) {
					
					DlfileEntryDto dlfile= findDlFileEntryByCpa(cpas.getId());
					listDlefile.add(dlfile);
				}
			}
			renderRequest.setAttribute("categoryName", categoryName);
			renderRequest.setAttribute("listDlefile", listDlefile);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}