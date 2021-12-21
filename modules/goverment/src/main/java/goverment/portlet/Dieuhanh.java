package goverment.portlet;

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

import goverment.constants.*;
import goverment.dto.BlogsEntryDto;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Dieu_hanh",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/dieuhanh.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.DIEUHANH,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class Dieuhanh extends MVCPortlet {
	private List<BlogsEntryDto> findAllBlogsByIdCategory() throws SQLException {
		PreparedStatement statement=null;
		Connection con=null;
		ResultSet rs=null;
		try {
			
			List<BlogsEntryDto> listBlogsEntryDto = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT be.uuid_  AS uuidblogsentry,be.entryid  AS entryid, be.title AS titleblogsentry, be.description AS descriptiondlfileentry,be.modifieddate AS modifieddate,dl.fileentryid AS fileentryid,dl.groupid  AS groupid,dl.folderid AS folderid, dl.title AS titledlfileentry, dl.uuid_ AS uuiddlfileentry FROM assetcategory ac INNER JOIN assetentryassetcategoryrel  aeac ON ac.categoryid = aeac.assetcategoryid INNER JOIN assetentry ae ON aeac.assetentryid = ae.entryid INNER JOIN blogsentry be ON ae.classpk = be.entryid INNER JOIN dlfileentry dl ON dl.fileentryid = be.smallimagefileentryid WHERE ac.categoryid = '108700'  AND ae.classnameid = '31201'  AND be.status = '0' ORDER BY be.modifieddate DESC OFFSET 0 ROWS FETCH NEXT 2 ROWS ONLY");
			rs=statement.executeQuery();
			while(rs.next()) {
				BlogsEntryDto blogsEntryDto=new BlogsEntryDto();
				String uuidBlogsEntry=rs.getString("uuidblogsentry");
				Integer entryId=rs.getInt("entryid");
				String titleBlogsEntry=rs.getString("titleblogsentry");
				String description=rs.getString("descriptiondlfileentry");
				Timestamp modifiedDate=rs.getTimestamp("modifieddate");
				Integer fileEntryId=rs.getInt("fileentryid");
				blogsEntryDto.setDescription(description);
				blogsEntryDto.setEntryId(entryId);
				blogsEntryDto.setFileEntryId(fileEntryId);
				blogsEntryDto.setTitleBlogsEntry(titleBlogsEntry);
				blogsEntryDto.setUuidBlogsEntry(uuidBlogsEntry);
				blogsEntryDto.setModifiedDate(modifiedDate);
				listBlogsEntryDto.add(blogsEntryDto);
			}
			return listBlogsEntryDto;
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
			List<BlogsEntryDto> listBlogsEntryDtos=findAllBlogsByIdCategory();
			
			renderRequest.setAttribute("listBlogsEntryDtos", listBlogsEntryDtos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}
