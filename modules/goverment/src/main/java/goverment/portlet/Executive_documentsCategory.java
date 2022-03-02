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

import org.osgi.service.component.annotations.Component;

import goverment.constants.GovermentPortletKeys;
import goverment.dto.BlogsEntryDto;
import goverment.dto.CategoryDto;
import goverment.sql.AssetCategorySql;
import goverment.sql.BlogEntrySql;
import goverment.sql.FeatureNewsSql;
import goverment.url.UrlCurrentPorlet;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Executive_documentsCategory",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/Executive_documentsCategory.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.LISTEXECUTIVE_DOCUMENT_CATEGORY,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class Executive_documentsCategory extends MVCPortlet{
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);
		
			String url=new UrlCurrentPorlet().urlCurrentPorlet(PortalUtil.getLayoutFriendlyURL(layout, themeDisplay),themeDisplay.getLayoutFriendlyURL(layout),themeDisplay.getCDNBaseURL());
			renderRequest.setAttribute("url", url);
			CategoryDto category= new AssetCategorySql().findCategoryByUuid("1c8ec7af-63b9-7e70-6e7a-f6eb5699f5ce",themeDisplay.getScopeGroupId());
			renderRequest.setAttribute("category", category);
			List<BlogsEntryDto> listBlogsEntryDtos = new FeatureNewsSql().findAllBlogsByIdCategory(themeDisplay.getScopeGroupId(),category.getUuid());
			renderRequest.setAttribute("listBlogsEntryDtos", listBlogsEntryDtos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}
