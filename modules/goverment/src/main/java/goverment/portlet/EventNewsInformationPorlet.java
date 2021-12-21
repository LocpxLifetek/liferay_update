package goverment.portlet;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.model.Layout;
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
import goverment.dto.CategoryDto;
import goverment.sql.AssetCategorySql;
import goverment.url.UrlCurrentPorlet;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=eventNewsInformation",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/eventNews.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.EVENTNEWSINFORMATION,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class EventNewsInformationPorlet extends MVCPortlet{
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);
			String url = new UrlCurrentPorlet().urlCurrentPorlet(themeDisplay.getURLCurrent(),
					themeDisplay.getLayoutFriendlyURL(layout));
			renderRequest.setAttribute("url", url);
			CategoryDto categoryDto=new AssetCategorySql().findCategoryByVocabulartDto(themeDisplay.getScopeGroupId());
			List<AssetCategory> listAssetCategory=AssetCategoryLocalServiceUtil.getChildCategories(categoryDto.getId());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}
