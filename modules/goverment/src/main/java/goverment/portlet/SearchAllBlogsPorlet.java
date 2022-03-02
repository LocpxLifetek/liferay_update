package goverment.portlet;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import goverment.constants.GovermentPortletKeys;
import goverment.dto.SearchBlogsDto;
import goverment.sql.SearchBlogsSql;
import goverment.url.UrlCurrentPorlet;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Search All Blogs",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/searchAllBlogs.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.SEARCHALLBLOGS,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class SearchAllBlogsPorlet extends MVCPortlet{
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			String error=null;
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String keyword = PortalUtil.getOriginalServletRequest(request).getParameter("keyword");
			String pageDetail = PortalUtil.getOriginalServletRequest(request).getParameter("page");
			Integer page=Integer.parseInt(pageDetail == null ? "1" : pageDetail);
			Integer size=10;
			Integer count=new SearchBlogsSql().countViewBlogsSerach(keyword, themeDisplay.getScopeGroupId());
			int result = (int) Math.ceil((float) count / size);
			Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);
			String url = new UrlCurrentPorlet().urlCurrentPorlet(PortalUtil.getLayoutFriendlyURL(layout, themeDisplay),
					themeDisplay.getLayoutFriendlyURL(layout),themeDisplay.getCDNBaseURL());
			renderRequest.setAttribute("url", url);
			List<SearchBlogsDto> listSearchBlogsDto=new SearchBlogsSql().searchBlogsSql(keyword, themeDisplay.getScopeGroupId(), page, size);
			if(listSearchBlogsDto.isEmpty() || keyword == null) {
				error="no information";
				renderRequest.setAttribute("error", error);
			}
			renderRequest.setAttribute("listSearchBlogsDto", listSearchBlogsDto);
			renderRequest.setAttribute("currentPage", page);
			renderRequest.setAttribute("totalPage", result);
			renderRequest.setAttribute("keyword", keyword);
			renderRequest.setAttribute("count", count);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}
