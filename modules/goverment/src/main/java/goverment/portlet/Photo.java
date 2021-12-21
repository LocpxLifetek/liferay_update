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
import goverment.dto.CategoryDto;
import goverment.dto.DlfileEntryDto;
import goverment.dto.cpattachmentfileentryDto;
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
			String url = new UrlCurrentPorlet().urlCurrentPorlet(themDisplay.getURLCurrent(),
					themDisplay.getLayoutFriendlyURL(layout));
			renderRequest.setAttribute("url", url);
			CategoryDto category= new PhotoSql().categoryDto();
			List<CategoryDto> listCategoryDtos=new PhotoSql().findCategoryByParent(621347);
			List<cpattachmentfileentryDto> listCpa= new ArrayList<>();
			List<DlfileEntryDto> listDlefile= new ArrayList<>();
			for (CategoryDto categoryDto : listCategoryDtos) {
				cpattachmentfileentryDto cpaAttach= new PhotoSql().findCpattachByCategory(categoryDto.getId());
				listCpa.add(cpaAttach);
			}	
			
			for (cpattachmentfileentryDto cpas : listCpa) {
				if(cpas.getId() !=null && listDlefile.size()<3) {
					
					DlfileEntryDto dlfile= new PhotoSql().findDlFileEntryByCpa(cpas.getId());
					listDlefile.add(dlfile);
				}
			}
			renderRequest.setAttribute("category", category);
			renderRequest.setAttribute("listDlefile", listDlefile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}

}
