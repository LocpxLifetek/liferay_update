package Album_new.portlet;

import Album_new.constants.Album_newPortletKeys;
import Album_new.dto.CategoryDto;
import Album_new.dto.DlfileEntryDto;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;

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

/**
 * @author java03
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Album_new",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + Album_newPortletKeys.ALBUM_NEW,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class Album_newPortlet extends MVCPortlet {
	private CategoryDto findCategoryByParent(Integer id){
		PreparedStatement  statement = null;
		Connection con = null;
		ResultSet rs = null;
		CategoryDto category= new CategoryDto();
		try {
			con = DataAccess.getConnection();
			statement= con.prepareStatement("select ac.categoryid as CategoryId, ac.groupid as groupId, ac.name as name from assetcategory ac where ac.parentcategoryid=? order by ac.modifieddate desc OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY");
			statement.setInt(1, id);
			rs = statement.executeQuery();
			while (rs.next()) {
				Integer categoryId = rs.getInt("CategoryId");
				Integer groupId= rs.getInt("groupId");
				String name= rs.getString("name");
				
				category.setId(categoryId);
				category.setGroupId(groupId);
				category.setName(name);
				
			}
			return category;
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
	private List<DlfileEntryDto> findAllDLfileEntryDtos(Integer id) throws SQLException {
		PreparedStatement statement=null;
		Connection con=null;
		ResultSet rs=null;
		try {
			
			List<DlfileEntryDto> listDlFileEntry = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("\r\n" + 
					"SELECT \r\n" + 
					"					    dl.groupid     AS groupid, \r\n" + 
					"                       dl.folderid    AS folderid,\r\n" + 
					"					    dl.uuid_       AS uuid,\r\n" + 
					"					    dl.filename    AS filename,\r\n" + 
					"					    dl.title       AS title\r\n" + 
					"					FROM\r\n" + 
					"					         assetcategory ac\r\n" + 
					"					    INNER JOIN assetentryassetcategoryrel  aeac ON ac.categoryid = aeac.assetcategoryid\r\n" + 
					"					    INNER JOIN assetentry                  ae ON aeac.assetentryid = ae.entryid \r\n" + 
					"					    INNER JOIN dlfileentry                 dl ON dl.fileentryid = ae.classpk\r\n" + 
					"					WHERE\r\n" + 
					"					      ac.categoryid=?\r\n" + 
					"					ORDER BY\r\n" + 
					"					    dl.modifieddate DESC OFFSET 0 ROWS FETCH NEXT 9 ROWS only");
			statement.setInt(1, id);
			rs=statement.executeQuery();
			
			while(rs.next()) {
				DlfileEntryDto dlFileEntryDto=new DlfileEntryDto();
				String uuid= rs.getString("uuid");
				Integer groupId = rs.getInt("groupid");
				Integer folderId = rs.getInt("folderid");
				String fileName = rs.getString("fileName");
				String title = rs.getString("title");
				String src="/documents/" + groupId + "/" + folderId + "/" + fileName + "/" + uuid;
				
				dlFileEntryDto.setSrc(src);
				dlFileEntryDto.setTitle(title);
				
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
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String uuid1 =  PortalUtil.getOriginalServletRequest(request).getParameter("id");
			
			CategoryDto categoryDto= findCategoryByParent(Integer.parseInt(uuid1));
			List<DlfileEntryDto> dLfileEntryDtos=findAllDLfileEntryDtos(categoryDto.getId());
			renderRequest.setAttribute("categoryDto", categoryDto);
			renderRequest.setAttribute("dLfileEntryDtos", dLfileEntryDtos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}

	
}