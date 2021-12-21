package informationNews.portlet;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import informationNews.constants.InformationNewsPortletKeys;
import informationNews.dto.BlogsEntryDto;

/**
 * @author java05
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=InformationNews",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + InformationNewsPortletKeys.INFORMATIONNEWS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class InformationNewsPortlet extends MVCPortlet {
	
	
	private BlogsEntryDto findAllBlogsEntryByUUID(String uuid,long groupId) throws SQLException {
		PreparedStatement statement=null;
		Connection con = null;
		ResultSet rs=null;
		try {
			
			BlogsEntryDto blogsEntryDto=new BlogsEntryDto();
			con=DataAccess.getConnection();
			statement=con.prepareStatement("SELECT be.title as title,be.entryid as entryId, be.description as description,be.content as content,be.modifieddate as modifiedDate,be.companyid as companyId,be.userid as userId from BlogsEntry be where be.status='0' and be.uuid_=? and be.groupId=?");
			statement.setString(1, uuid);
			statement.setLong(2, groupId);
			 rs=statement.executeQuery();
			while(rs.next()) {
				String title=rs.getString("title");
				String description=rs.getString("description");
				String content=rs.getString("content");
				Timestamp modifiedDate=rs.getTimestamp("modifiedDate");
				long entryId=rs.getLong("entryId");
				long companyId=rs.getLong("companyId");
				long userid=rs.getLong("userId");
				blogsEntryDto.setEntryId(entryId);
				blogsEntryDto.setCompanyId(companyId);
				blogsEntryDto.setUserId(userid);
				blogsEntryDto.setModifiedDate(modifiedDate);
				blogsEntryDto.setContent(content);
				blogsEntryDto.setDescription(description);
				blogsEntryDto.setTitle(title);
			}
			
			return blogsEntryDto;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
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
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			
			List<AssetTag> listAssetTag=AssetTagLocalServiceUtil.getAssetEntryAssetTags(843207);
			for (AssetTag assetTag : listAssetTag) {
				System.out.println(assetTag.getName());
			}
			String uuid =  PortalUtil.getOriginalServletRequest(request).getParameter("id");
			BlogsEntryDto blogsEntryDto=findAllBlogsEntryByUUID(uuid,themeDisplay.getScopeGroupId());
			AssetEntry assetEntry=AssetEntryLocalServiceUtil.incrementViewCounter(blogsEntryDto.getCompanyId(), blogsEntryDto.getUserId(), BlogsEntry.class.getName(), blogsEntryDto.getEntryId());
			renderRequest.setAttribute("blogsEntry", blogsEntryDto);	
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
	
}