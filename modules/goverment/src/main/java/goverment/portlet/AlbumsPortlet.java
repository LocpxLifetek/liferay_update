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
<<<<<<< HEAD
import goverment.dto.CategoryDto;
import goverment.dto.DlFileEntryDto;
import goverment.dto.cpattachmentfileentryDto;
=======
import goverment.dto.CpattachmentfileentryDto;
>>>>>>> 9d9f12a3e40a55ad899df6b15c3fdd8d602dea18
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
			Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);

			ThemeDisplay themDisplay=(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String url = new UrlCurrentPorlet().urlCurrentPorlet(themDisplay.getURLCurrent(),
					themDisplay.getLayoutFriendlyURL(layout));

			renderRequest.setAttribute("url", url);
<<<<<<< HEAD
			CategoryDto categoryName= new PhotoSql().categoryDto();
			List<CategoryDto> listCategory= new PhotoSql().findCategoryByParent(categoryName.getId());
			List<cpattachmentfileentryDto> listCpa= new ArrayList<>();
			List<DlFileEntryDto> listDlfileNoImage= new ArrayList<>();
			List<DlFileEntryDto> listDlfImage= new ArrayList<>();
			for (CategoryDto categoryDto : listCategory) {
				cpattachmentfileentryDto cpaAttach= new PhotoSql().findCpattachByCategory(categoryDto.getId());;
				listCpa.add(cpaAttach);
			}	
			
			for (cpattachmentfileentryDto cpas : listCpa) {
				if(cpas.getId() !=null ) {			
					DlFileEntryDto dlfile= new PhotoSql().findDlFileEntryByCpa(cpas.getId());
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
=======
			AssetCategory assetCategory=AssetCategoryLocalServiceUtil.getAssetCategoryByUuidAndGroupId("9d9a6b62-d2ed-9324-7b70-a524a67c5c64", themDisplay.getScopeGroupId());
			List<AssetCategory> listAssetCategory=AssetCategoryLocalServiceUtil.getChildCategories(assetCategory.getCategoryId());
			int i=0;
			List<CpattachmentfileentryDto> listCpattachmentfileentryDtos=new ArrayList<>();
			for (AssetCategory assetCategory2 : listAssetCategory) {
				i++;
				CpattachmentfileentryDto cpa=new PhotoSql().findCpattachByCategory(assetCategory2.getCategoryId());
				if(i==1) {
					renderRequest.setAttribute("cpa", cpa);
				}else {
					listCpattachmentfileentryDtos.add(cpa);
>>>>>>> 9d9f12a3e40a55ad899df6b15c3fdd8d602dea18
				}
			}
			renderRequest.setAttribute("listCpattachmentfileentryDtos", listCpattachmentfileentryDtos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}
