package goverment.portlet;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import goverment.constants.GovermentPortletKeys;
import goverment.dto.BlogsEntryDto;
import goverment.sql.CommonSqlBlogEntry;
import goverment.url.UrlCurrentPorlet;

@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CountViewBlogs", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/countViewBlogs.jsp",
		"javax.portlet.name=" + GovermentPortletKeys.COUNTVIEWBLOGS, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class CountViewBlogsPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			int i = 0;
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			List<BlogsEntryDto> listBlogsEntryDtos = new CommonSqlBlogEntry()
					.findBlogsCountView(themeDisplay.getScopeGroupId());
			List<BlogsEntryDto> manyBlog = new ArrayList<>();
			Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);
			String url = new UrlCurrentPorlet().urlCurrentPorlet(themeDisplay.getURLCurrent(),
					themeDisplay.getLayoutFriendlyURL(layout));
			renderRequest.setAttribute("url", url);
			for (BlogsEntryDto blogs : listBlogsEntryDtos) {
				i++;
				if (i == 1) {
					renderRequest.setAttribute("blogs", blogs);
				} else {
					manyBlog.add(blogs);
				}

			}
			renderRequest.setAttribute("listBlogsEntry", manyBlog);
		} catch (Exception e) {
			e.printStackTrace();

		}
		super.doView(renderRequest, renderResponse);
	}
}
