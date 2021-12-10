package featuredNews.portlet;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;

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
import featuredNews.dto.FeatureNewsCache;

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
		
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		WebCacheItem wci = new FeatureNewsCache();
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String key = FeaturedNewsPortletKeys.FEATUREDNEWS + "," + String.valueOf(themeDisplay.getScopeGroupId());
			List<BlogsEntryDto> listBlogsNoImage = new ArrayList<>();
			List<BlogsEntryDto> listBlogsEntryDtos = (List<BlogsEntryDto>) WebCachePoolUtil.get(key, wci);
			Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);
			
			String urlCurrent=themeDisplay.getURLCurrent();
			String layoutUrl =themeDisplay.getLayoutFriendlyURL(layout);
			String[] url=urlCurrent.split(layoutUrl);
			String urlSite=null;
			int j=0;
			for (String string : url) {
				j++;
				if(j==1) {
					urlSite=string;
				}
			}
			renderRequest.setAttribute("url", urlSite);	
			int i = 0;
			for (BlogsEntryDto blogsEntryDto : listBlogsEntryDtos) {
				i++;
				if (i == 1) {
					renderRequest.setAttribute("blogsEntryDto", blogsEntryDto);
				} else {
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