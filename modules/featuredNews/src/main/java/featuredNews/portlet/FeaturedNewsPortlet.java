package featuredNews.portlet;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

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

import featuredNews.constants.FeaturedNewsPortletKeys;
import featuredNews.dto.BlogsEntryDto;

/**
 * @author java05
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=FeaturedNews", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + FeaturedNewsPortletKeys.FEATUREDNEWS, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
@Transactional(isolation = Isolation.PORTAL, rollbackFor = { PortalException.class, SystemException.class })
public class FeaturedNewsPortlet extends MVCPortlet {
	private PreparedStatement statement;
	public Connection con=null;

	private List<BlogsEntryDto> findAllBlogsByIdCategory() throws SQLException {
		try {
			
			List<BlogsEntryDto> listBlogsEntryDto = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT be.uuid_  AS uuidblogsentry,be.entryid  AS entryid,be.title AS titleblogsentry, be.description AS descriptiondlfileentry,be.modifieddate AS modifieddate,dl.fileentryid AS fileentryid,dl.groupid  AS groupid,dl.folderid AS folderid, dl.title AS titledlfileentry, dl.uuid_ AS uuiddlfileentry FROM assetcategory ac INNER JOIN assetentryassetcategoryrel  aeac ON ac.categoryid = aeac.assetcategoryid INNER JOIN assetentry ae ON aeac.assetentryid = ae.entryid INNER JOIN blogsentry be ON ae.classpk = be.entryid INNER JOIN dlfileentry dl ON dl.fileentryid = be.smallimagefileentryid WHERE ac.categoryid = '87918'  AND ae.classnameid = '31201'  AND be.status = '0' ORDER BY be.modifieddate DESC OFFSET 0 ROWS FETCH NEXT 7 ROWS ONLY");
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				BlogsEntryDto blogsEntryDto=new BlogsEntryDto();
				String uuidBlogsEntry=rs.getString("uuidblogsentry");
				Integer entryId=rs.getInt("entryid");
				String titleBlogsEntry=rs.getString("titleblogsentry");
				String description=rs.getString("descriptiondlfileentry");
				Timestamp modifiedDate=rs.getTimestamp("modifieddate");
				Integer fileEntryId=rs.getInt("fileentryid");
				Integer groupId=rs.getInt("groupid");
				Integer folderId=rs.getInt("folderid");
				String titleDlFileEntry=rs.getString("titledlfileentry");
				String uuidDlFileEntry=rs.getString("uuiddlfileentry");
				blogsEntryDto.setDescription(description);
				blogsEntryDto.setEntryId(entryId);
				blogsEntryDto.setFileEntryId(fileEntryId);
				blogsEntryDto.setFolderId(folderId);
				blogsEntryDto.setGroupId(groupId);
				blogsEntryDto.setTitleBlogsEntry(titleBlogsEntry);
				blogsEntryDto.setTitleDlFileEntry(titleDlFileEntry);
				blogsEntryDto.setUuidBlogsEntry(uuidBlogsEntry);
				blogsEntryDto.setUuidDlFileEntry(uuidDlFileEntry);
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
		}
	}

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		try {	
			List<BlogsEntryDto> listBlogsNoImage=new ArrayList<>();
			List<BlogsEntryDto> listBlogsEntryDtos=findAllBlogsByIdCategory();
			int i=0;
			for (BlogsEntryDto blogsEntryDto : listBlogsEntryDtos) {
				i++;
				if(i==1) {
					renderRequest.setAttribute("blogsEntryDto", blogsEntryDto);
				}else {
					listBlogsNoImage.add(blogsEntryDto);
				}
			}
			renderRequest.setAttribute("listBlogsNoImage", listBlogsNoImage);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}

}