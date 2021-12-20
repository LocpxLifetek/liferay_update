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


@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Albums",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/view.jsp",
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
			Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);
			ThemeDisplay themDisplay=(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String urlCurrent=themDisplay.getURLCurrent();
			String layoutUrl =themDisplay.getLayoutFriendlyURL(layout);
			String[] url=urlCurrent.split(layoutUrl);
			String urlSite=null;
			int i=0;
			for (String string : url) {
				i++;
				if(i==1) {
					urlSite=string;
				}
			}
			renderRequest.setAttribute("url", urlSite);
			CategoryDto categoryName= new PhotoSql().categoryDto();
			List<CategoryDto> listCategory= new PhotoSql().findCategoryByParent(categoryName.getId());
			List<cpattachmentfileentryDto> listCpa= new ArrayList<>();
			List<DlfileEntryDto> listDlfileNoImage= new ArrayList<>();
			List<DlfileEntryDto> listDlfImage= new ArrayList<>();
			for (CategoryDto categoryDto : listCategory) {
				cpattachmentfileentryDto cpaAttach= new PhotoSql().findCpattachByCategory(categoryDto.getId());;
				listCpa.add(cpaAttach);
			}	
			
			for (cpattachmentfileentryDto cpas : listCpa) {
				if(cpas.getId() !=null ) {			
					DlfileEntryDto dlfile= new PhotoSql().findDlFileEntryByCpa(cpas.getId());
					listDlfImage.add(dlfile);
				}
			}
			int j=0;
			for(DlfileEntryDto list : listDlfImage) {
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
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}
