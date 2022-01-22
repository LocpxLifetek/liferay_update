package goverment.portlet;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
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
import goverment.sql.FeatureNewsSql;
import goverment.url.UrlCurrentPorlet;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=List Propagate",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/listPropagate.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.LISTPROPAGATE,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class ListPropagatePorlet extends MVCPortlet{
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);
		
			String url=new UrlCurrentPorlet().urlCurrentPorlet(themeDisplay.getURLCurrent(),themeDisplay.getLayoutFriendlyURL(layout));
			renderRequest.setAttribute("url", url);
			List<BlogsEntryDto> listBlogsNoImage = new ArrayList<>();

			List<BlogsEntryDto> listBlogsEntryDtos = new FeatureNewsSql().findAllBlogsByIdCategory(themeDisplay.getScopeGroupId(), "37c878f2-e083-f438-c508-85403d72079d");
			int i = 0;
			for (BlogsEntryDto blogsEntryDto : listBlogsEntryDtos) {
				i++;
				if (i == 1) {
					renderRequest.setAttribute("blogsEntryDto", blogsEntryDto);
				} else {
					listBlogsNoImage.add(blogsEntryDto);
				}
			}
			renderRequest.setAttribute("listBlogsNoImage", listBlogsNoImage);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}
