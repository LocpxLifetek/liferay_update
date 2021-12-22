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

import goverment.constants.*;
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
			"javax.portlet.display-name=Table_category_photo",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/Table_category_photo.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.TABLE_CATEGORY_PHOTO,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class Table_category_photo extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		try {
			Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);
			ThemeDisplay themeDisplay=(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String url = new UrlCurrentPorlet().urlCurrentPorlet(themeDisplay.getURLCurrent(),
					themeDisplay.getLayoutFriendlyURL(layout));
			renderRequest.setAttribute("url", url);
			CategoryDto categoryName=new PhotoSql().categoryDto();
			List<CategoryDto> listCategory=new PhotoSql().findCategoryByParent(categoryName.getId());
			List<cpattachmentfileentryDto> listCpa= new ArrayList<>();
			List<DlfileEntryDto> listDlefile= new ArrayList<>();
			for (CategoryDto categoryDto : listCategory) {
				cpattachmentfileentryDto cpaAttach=new PhotoSql().findCpattachByCategory(categoryDto.getId());
				listCpa.add(cpaAttach);
			}	
			
			for (cpattachmentfileentryDto cpas : listCpa) {
				if(cpas.getId() !=null ) {
					
					DlfileEntryDto dlfile= new PhotoSql().findDlFileEntryByCpa(cpas.getId());
					listDlefile.add(dlfile);
				}
			}
			renderRequest.setAttribute("categoryName", categoryName);
			renderRequest.setAttribute("listDlefile", listDlefile);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}

}