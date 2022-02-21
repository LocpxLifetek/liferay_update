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
import goverment.sql.BlogEntrySql;
import goverment.url.UrlCurrentPorlet;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=New_event",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/New_Event.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.NEWEVENT,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class New_event extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		try {	
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);

			String url = new UrlCurrentPorlet().urlCurrentPorlet(themeDisplay.getURLCurrent(),
					themeDisplay.getLayoutFriendlyURL(layout),themeDisplay.getCDNBaseURL());
			renderRequest.setAttribute("url", url);
			List<BlogsEntryDto> listBlogs2=new ArrayList<>();
			List<BlogsEntryDto> listBlogs6=new ArrayList<>();

			List<BlogsEntryDto> listBlogsEntryDtos= new BlogEntrySql().findAllBlogsByIdCategory("4fb47075-a67c-9f2d-f175-d41304e90ad3",themeDisplay.getScopeGroupId(),1,8);
			int i=0;
			for (BlogsEntryDto blogsEntryDto : listBlogsEntryDtos) {
				i++;
				if(i<=2) {
					listBlogs2.add(blogsEntryDto);
					renderRequest.setAttribute("listBlogs2", listBlogs2);
				}else if(i>2 && i<=8) {
					listBlogs6.add(blogsEntryDto);
					renderRequest.setAttribute("listBlogs6", listBlogs6);
				}
				else {
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}

}
