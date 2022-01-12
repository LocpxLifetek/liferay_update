package goverment.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import goverment.constants.GovermentPortletKeys;
import goverment.dto.BlogsEntryDto;
import goverment.sql.BlogEntrySql;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Lanh_dao",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/lanhdao.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.LANHDAO,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class LanhDao extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		try {	
<<<<<<< HEAD
			List<BlogsEntryDto> listBlogsEntryDtos= new BlogEntrySql().findAllBlogsByCategory("5ff435b2-dd2c-b4a8-324f-23dc2d1475a9", 2);
=======
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			List<BlogsEntryDto> listBlogsEntryDtos= new BlogEntrySql().findAllBlogsByCategory("5ff435b2-dd2c-b4a8-324f-23dc2d1475a9", 2,themeDisplay.getScopeGroupId());
>>>>>>> 9d9f12a3e40a55ad899df6b15c3fdd8d602dea18
			renderRequest.setAttribute("listBlogsEntryDtos", listBlogsEntryDtos);	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}
