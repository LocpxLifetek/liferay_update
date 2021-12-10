	package portlet_thutuong_right.portlet;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

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

import portlet_thutuong_right.constants.Portlet_thutuong_rightPortletKeys;


/**
 * @author java03
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Portlet_thutuong_right",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + Portlet_thutuong_rightPortletKeys.PORTLET_THUTUONG_RIGHT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class Portlet_thutuong_rightPortlet extends MVCPortlet{


	private List<BlogsEntryDto> findAllBlogsByIdCategory() throws SQLException {
		PreparedStatement statement=null;
		Connection con=null;
		ResultSet rs=null;
		try {
			
			List<BlogsEntryDto> listBlogsEntryDto = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT be.entryid  AS entryid,be.title AS titleblogsentry, be.description AS descriptiondlfileentry,be.modifieddate AS modifieddate FROM assetcategory ac INNER JOIN assetentryassetcategoryrel  aeac ON ac.categoryid = aeac.assetcategoryid INNER JOIN assetentry ae ON aeac.assetentryid = ae.entryid INNER JOIN blogsentry be ON ae.classpk = be.entryid WHERE ac.categoryid = '72065'  AND ae.classnameid = '31201'  AND be.status = '0' ORDER BY be.modifieddate DESC OFFSET 0 ROWS FETCH NEXT 2 ROWS ONLY");
			rs=statement.executeQuery();
			while(rs.next()) {
				BlogsEntryDto blogsEntryDto=new BlogsEntryDto();
				Integer entryId=rs.getInt("entryid");
				String titleBlogsEntry=rs.getString("titleblogsentry");
				String description=rs.getString("descriptiondlfileentry");
				Timestamp modifiedDate=rs.getTimestamp("modifieddate");
				blogsEntryDto.setDescription(description);
				blogsEntryDto.setEntryId(entryId);
				blogsEntryDto.setTitleBlogsEntry(titleBlogsEntry);
				blogsEntryDto.setModifiedDate(modifiedDate);
				listBlogsEntryDto.add(blogsEntryDto);
			}
			return listBlogsEntryDto;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
				statement.close();
				con.close();
			
			
				rs.close();
			
		}
	}

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		try {	
			List<BlogsEntryDto> listBlogsEntryDtos=findAllBlogsByIdCategory();
			renderRequest.setAttribute("listBlogsEntryDtos", listBlogsEntryDtos);	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}

	