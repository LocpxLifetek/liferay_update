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
			"javax.portlet.display-name=New_documents",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/new_document.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.NEWDOCUMENT,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class New_document extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		try {	
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);

			String url = new UrlCurrentPorlet().urlCurrentPorlet(themeDisplay.getURLCurrent(),
					themeDisplay.getLayoutFriendlyURL(layout));
			renderRequest.setAttribute("url", url);
			List<BlogsEntryDto> listBlogsEntryDtos=new BlogEntrySql().findAllBlogsByIdCategory(134236,3);
			List<BlogsEntryDto> listBlog = new ArrayList<>();
			int i=0;
			for (BlogsEntryDto listblogsEntryDto : listBlogsEntryDtos) {
				i++;
				if(i<=3) {
					listBlog.add(listblogsEntryDto);
					renderRequest.setAttribute("listBlog", listBlog);
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
