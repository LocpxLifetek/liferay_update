package goverment.portlet;

import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
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
import goverment.url.UrlCurrentPorlet;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=InformationNews",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/information.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.INFORMATIONNEWS,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class InformationNewsPorlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String uuid =  PortalUtil.getOriginalServletRequest(request).getParameter("uuid");
			String url = new UrlCurrentPorlet().urlCurrentPorlet(PortalUtil.getLayoutFriendlyURL(layout, themeDisplay),
					themeDisplay.getLayoutFriendlyURL(layout),themeDisplay.getCDNBaseURL());
			renderRequest.setAttribute("url", url);
			BlogsEntry blogsEntry=BlogsEntryLocalServiceUtil.getBlogsEntryByUuidAndGroupId(uuid, themeDisplay.getScopeGroupId());
			renderRequest.setAttribute("blogsEntry", blogsEntry);
			AssetEntry incrementView=AssetEntryLocalServiceUtil.incrementViewCounter(themeDisplay.getCompanyId(), themeDisplay.getUserId(), BlogsEntry.class.getName(), blogsEntry.getEntryId());
			AssetEntry assetEntry=AssetEntryLocalServiceUtil.getEntry(BlogsEntry.class.getName(), blogsEntry.getEntryId());
			List<AssetEntryAssetCategoryRel> listAssetEntryAssetCategory=AssetEntryAssetCategoryRelLocalServiceUtil.getAssetEntryAssetCategoryRelsByAssetEntryId(assetEntry.getEntryId());
			List<AssetCategory> listAssetCategory=new ArrayList<>();
			for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel : listAssetEntryAssetCategory) {
				AssetCategory assetCategory=AssetCategoryLocalServiceUtil.getAssetCategory(assetEntryAssetCategoryRel.getAssetCategoryId());
				listAssetCategory.add(assetCategory);
				
			}
			for (AssetCategory assetCategory : listAssetCategory) {
				if(assetCategory.getParentCategoryId()>0) {
					AssetCategory assetCategory2=AssetCategoryLocalServiceUtil.getAssetCategory(assetCategory.getCategoryId());
					renderRequest.setAttribute("assetCategory2", assetCategory2);
				}else {					
					renderRequest.setAttribute("assetCategory", assetCategory);
				}
			}
			List<AssetTag> listAssetTag=AssetTagLocalServiceUtil.getAssetEntryAssetTags(assetEntry.getEntryId());
			
			renderRequest.setAttribute("listAssetTag", listAssetTag);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		super.doView(renderRequest, renderResponse);
	}
}
