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
			"javax.portlet.display-name=Photo",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/Photo.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.PHOTO,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class Photo extends MVCPortlet {
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);
			ThemeDisplay themDisplay=(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String url = new UrlCurrentPorlet().urlCurrentPorlet(themDisplay.getURLCurrent(),
					themDisplay.getLayoutFriendlyURL(layout));
			renderRequest.setAttribute("url", url);
			CategoryDto category= new AssetCategorySql().findCategoryByUuid("5bfa37e4-1270-ba14-d6b5-f9c7a8a6b780",themDisplay.getScopeGroupId());
			List<CategoryDto> listCategoryDtos=new AssetCategorySql().findCategoryByParentCategory(category.getId());
			List<CpattachmentfileentryDto> listCpa= new ArrayList<>();
			List<DlFileEntryDto> listDlefile= new ArrayList<>();
			for (CategoryDto categoryDto : listCategoryDtos) {
				CpattachmentfileentryDto cpaAttach= new PhotoSql().findCpattachByCategory(categoryDto.getId(), themDisplay.getScopeGroupId());
				listCpa.add(cpaAttach);
			}	
			
			for (CpattachmentfileentryDto cpas : listCpa) {
				if(cpas.getFlIdCpa()!= null && listDlefile.size()<3) {
					
					DlFileEntryDto dlfile= new PhotoSql().findDlFileEntryByCpa(cpas.getFlIdCpa());
					listDlefile.add(dlfile);
				}
			}
			renderRequest.setAttribute("category", category);
			renderRequest.setAttribute("listDlefile", listDlefile);
		} catch (Exception e) {
			
		}
		super.doView(renderRequest, renderResponse);
	}

}
