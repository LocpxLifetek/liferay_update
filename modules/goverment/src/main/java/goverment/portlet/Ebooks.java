package goverment.portlet;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import goverment.constants.GovermentPortletKeys;
import goverment.dto.CategoryDto;
import goverment.dto.CpattachmentfileentryDto;
import goverment.dto.DlFileEntryDto;
import goverment.sql.AssetCategorySql;
import goverment.sql.PhotoSql;
import goverment.url.UrlCurrentPorlet;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Ebooks",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/Ebooks.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.EBOOKS,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class Ebooks extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		try {
			Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);
			ThemeDisplay themDisplay=(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String url = new UrlCurrentPorlet().urlCurrentPorlet(themDisplay.getURLCurrent(),
					themDisplay.getLayoutFriendlyURL(layout));
			renderRequest.setAttribute("url", url);
			CategoryDto category= new AssetCategorySql().findCategoryByUuid("82ab5b25-1666-55fc-b960-a3c5c544608b",themDisplay.getScopeGroupId());
			CpattachmentfileentryDto cpaAttach= new PhotoSql().findCpattachByCategory(category.getId(), themDisplay.getScopeGroupId());
			DlFileEntryDto dlfile= new PhotoSql().findDlFileEntryByCpa(cpaAttach.getFlIdCpa());
			renderRequest.setAttribute("dlfile", dlfile);
			renderRequest.setAttribute("category", category);
	
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}

}
