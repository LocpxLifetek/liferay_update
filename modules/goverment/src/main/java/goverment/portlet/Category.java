package goverment.portlet;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import goverment.constants.GovermentPortletKeys;
import goverment.dto.BlogsEntryDto;
import goverment.dto.CategoryDto;
import goverment.sql.AssetCategorySql;
import goverment.sql.BlogEntrySql;
import goverment.url.UrlCurrentPorlet;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Category",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/Category.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.CATEGORY,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class Category extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String uuid =  PortalUtil.getOriginalServletRequest(request).getParameter("uuid");
			String url = new UrlCurrentPorlet().urlCurrentPorlet(themeDisplay.getURLCurrent(),
					themeDisplay.getLayoutFriendlyURL(layout));
			renderRequest.setAttribute("url", url);
			CategoryDto category= new AssetCategorySql().findCategoryByUuid(uuid, themeDisplay.getScopeGroupId());
			CategoryDto categoryDto= new AssetCategorySql().findCategoryById(category.getParentCategoryId());
			renderRequest.setAttribute("categoryDto", categoryDto);
//			CategoryDto category2= new AssetCategorySql().findCategoryByUuid(categoryDto.getUuid());
			List<BlogsEntryDto> listBlog= new BlogEntrySql().findAllBlogsByCategory(categoryDto.getUuid(), 5, categoryDto.getGroupId());
			List<BlogsEntryDto> listBlogEntryDto= new ArrayList<>();
			int i = 0;
			for (BlogsEntryDto blogsEntryDto : listBlog) {
				i++;
				if (i == 1) {
					renderRequest.setAttribute("blogsEntryDto", blogsEntryDto);
				}
				if (i >= 1) {
					listBlogEntryDto.add(blogsEntryDto);
				}
			}
			renderRequest.setAttribute("listBlogEntryDto", listBlogEntryDto);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}
