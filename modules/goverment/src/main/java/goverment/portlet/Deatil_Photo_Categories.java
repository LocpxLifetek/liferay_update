package goverment.portlet;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
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
import goverment.dto.DlFileEntryDto;
import goverment.sql.BlogEntrySql;
import goverment.sql.PhotoSql;
import goverment.url.UrlCurrentPorlet;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Detail_Photo_Categories",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/DetailPhoto.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.DEATIL_PHOTO_CATEGORIES,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class Deatil_Photo_Categories extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		try {
			Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);
			ThemeDisplay themDisplay=(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String url = new UrlCurrentPorlet().urlCurrentPorlet(PortalUtil.getLayoutFriendlyURL(layout, themDisplay),
					themDisplay.getLayoutFriendlyURL(layout),themDisplay.getCDNBaseURL());
			renderRequest.setAttribute("url", url);
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String pageDetail = PortalUtil.getOriginalServletRequest(request).getParameter("page");
			String uuid =  PortalUtil.getOriginalServletRequest(request).getParameter("uuid");
			DlFileEntryDto dlfileEntry= new PhotoSql().dlfile(uuid);
			renderRequest.setAttribute("dlfileEntry", dlfileEntry);
			Integer resultDlFile=new PhotoSql().countDlfileByCategory(uuid, themDisplay.getScopeGroupId());
			Integer page=Integer.parseInt(pageDetail == null ? "1" : pageDetail);
			Integer size=9;
			int result = (int) Math.ceil((float) resultDlFile/ size);
			List<DlFileEntryDto> dLfileEntryDtos=new PhotoSql().findAllDLfileEntryDtos(uuid, page, size);
			List<DlFileEntryDto> listDlfile= new ArrayList<>();
			AssetEntry assetEntry=AssetEntryLocalServiceUtil.getEntry("com.liferay.document.library.kernel.model.DLFileEntry", dlfileEntry.getId());
			AssetRenderer<?> assetRender=assetEntry.getAssetRenderer();
			String docUrl=assetRender.getURLDownload(themDisplay);
			for (DlFileEntryDto listDlf : dLfileEntryDtos) {
				listDlf.setUrl(docUrl);
				listDlfile.add(listDlf);
			}
			renderRequest.setAttribute("listDlfile", listDlfile);
			renderRequest.setAttribute("currentPage", page);
			renderRequest.setAttribute("totalPage", result);
			renderRequest.setAttribute("uuid", uuid);
			renderRequest.setAttribute("url", url);
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}

}
