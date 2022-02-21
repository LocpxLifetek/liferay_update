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
			"javax.portlet.display-name=Albums",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/Albums.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.ALBUMS,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class AlbumsPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		try {	
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);
		
			String url=new UrlCurrentPorlet().urlCurrentPorlet(themeDisplay.getURLCurrent(),themeDisplay.getLayoutFriendlyURL(layout),themeDisplay.getCDNBaseURL());
			renderRequest.setAttribute("url", url);
			CategoryDto categoryName= new AssetCategorySql().findCategoryByUuid("5bfa37e4-1270-ba14-d6b5-f9c7a8a6b780", themeDisplay.getScopeGroupId());
			renderRequest.setAttribute("categoryName", categoryName);
			List<CategoryDto> listCategory= new AssetCategorySql().findCategoryByParentCategory(categoryName.getId());
			List<CpattachmentfileentryDto> listCpa= new ArrayList<>();
			List<DlFileEntryDto> listDlfileNoImage= new ArrayList<>();
			List<DlFileEntryDto> listDlfImage= new ArrayList<>();
			for (CategoryDto categoryDto : listCategory) {
				CpattachmentfileentryDto cpaAttach= new PhotoSql().findCpattachByCategory(categoryDto.getId(),themeDisplay.getScopeGroupId());;
				listCpa.add(cpaAttach);
			}	
			
			for (CpattachmentfileentryDto cpas : listCpa) {
				if(cpas.getFlIdCpa() !=null ) {			
					DlFileEntryDto dlfile= new PhotoSql().findDlFileEntryByCpa(cpas.getFlIdCpa());
					listDlfImage.add(dlfile);
				}
			}
			int j=0;
			for(DlFileEntryDto list : listDlfImage) {
				j++;
				if(j==1) {
					renderRequest.setAttribute("list", list);
				}
				else {
					listDlfileNoImage.add(list);
				}
			}
			renderRequest.setAttribute("listDlfileNoImage", listDlfileNoImage);
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		super.doView(renderRequest, renderResponse);
	}
}
