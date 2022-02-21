package goverment.portlet;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import goverment.cache.HotNewsCache;
import goverment.constants.GovermentPortletKeys;
import goverment.dto.BlogsEntryDto;
import goverment.url.UrlCurrentPorlet;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=HotNews",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/hotNews.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.HOTNEWS,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class HotNewsPorlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
	
		WebCacheItem wci = new HotNewsCache();
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);
			String key = GovermentPortletKeys.HOTNEWS + "," + String.valueOf(themeDisplay.getScopeGroupId());
			List<BlogsEntryDto> listBlogsEntryDtos = (List<BlogsEntryDto>) WebCachePoolUtil.get(key, wci);
			String url=new UrlCurrentPorlet().urlCurrentPorlet(themeDisplay.getURLCurrent(),themeDisplay.getLayoutFriendlyURL(layout),themeDisplay.getCDNBaseURL());
			renderRequest.setAttribute("url", url);
			renderRequest.setAttribute("listBlogsEntryDtos", listBlogsEntryDtos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		super.doView(renderRequest, renderResponse);

	}
}
