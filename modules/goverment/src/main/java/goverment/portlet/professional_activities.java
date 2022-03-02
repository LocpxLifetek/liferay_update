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

import org.osgi.service.component.annotations.Component;

import goverment.constants.GovermentPortletKeys;
import goverment.dto.CategoryDto;
import goverment.sql.AssetCategorySql;
import goverment.url.UrlCurrentPorlet;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Profestional",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/professional_activities.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.PROFESSIONAL_ACTIVITIES,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class professional_activities extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);
			String url = new UrlCurrentPorlet().urlCurrentPorlet(PortalUtil.getLayoutFriendlyURL(layout, themeDisplay),
					themeDisplay.getLayoutFriendlyURL(layout),themeDisplay.getCDNBaseURL());
			renderRequest.setAttribute("url", url);
			CategoryDto categoryDto= new AssetCategorySql().findCategoryByUuid("521e8b35-2c5a-db70-f646-81c0c3912ee5",themeDisplay.getScopeGroupId());
			List<CategoryDto> listCategoryDtos= new AssetCategorySql().findCategoryByParentCategory(categoryDto.getId());
			renderRequest.setAttribute("categoryDto", categoryDto);
			renderRequest.setAttribute("listCategoryDtos", listCategoryDtos);
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		super.doView(renderRequest, renderResponse);
	}
}
