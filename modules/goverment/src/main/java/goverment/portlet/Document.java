package goverment.portlet;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
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
			"javax.portlet.display-name=Document",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/Document.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.DOCUMENT,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class Document extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		try {
			Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);
			ThemeDisplay themDisplay=(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String url = new UrlCurrentPorlet().urlCurrentPorlet(PortalUtil.getLayoutFriendlyURL(layout, themDisplay),
					themDisplay.getLayoutFriendlyURL(layout),themDisplay.getCDNBaseURL());
			renderRequest.setAttribute("url", url);
			CategoryDto category= new AssetCategorySql().findCategoryByUuid("a9aec0c3-59fd-0eff-bf32-bd2cb61fa2ad",themDisplay.getScopeGroupId());
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
