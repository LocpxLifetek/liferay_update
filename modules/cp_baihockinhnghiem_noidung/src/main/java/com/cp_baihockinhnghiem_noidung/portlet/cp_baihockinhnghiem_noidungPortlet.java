package com.cp_baihockinhnghiem_noidung.portlet;

import com.cp_baihockinhnghiem_noidung.constants.cp_baihockinhnghiem_noidungPortletKeys;
import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleDisplay;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
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

/**
 * @author java05
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=cp_baihockinhnghiem_noidung",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + cp_baihockinhnghiem_noidungPortletKeys.CP_BAIHOCKINHNGHIEM_NOIDUNG,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class cp_baihockinhnghiem_noidungPortlet extends MVCPortlet {
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			int id = Integer.parseInt(PortalUtil.getOriginalServletRequest(request).getParameter("id"));
			int categoryId = Integer.parseInt(PortalUtil.getOriginalServletRequest(request).getParameter("categoryId"));
			
			if (categoryId == 221207 || categoryId == 221210) {
				AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(categoryId);
				renderRequest.setAttribute("assetCategory", assetCategory);
				
				List<AssetEntryAssetCategoryRel> assetCategoryRels = AssetEntryAssetCategoryRelLocalServiceUtil
						.getAssetEntryAssetCategoryRelsByAssetCategoryId(categoryId);
				List<JournalArticleDisplay> journalArticleDisplays = new ArrayList<>();
				for (AssetEntryAssetCategoryRel a : assetCategoryRels) {
					long entryId = a.getAssetEntryId();
					AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(entryId);
					long classPk = assetEntry.getClassPK();
					JournalArticle article = JournalArticleLocalServiceUtil.getLatestArticle(classPk);

					ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
					JournalArticleDisplay articleDisplay = JournalArticleLocalServiceUtil
							.getArticleDisplay(themeDisplay.getScopeGroupId(), article.getArticleId(), "", "", themeDisplay);
					journalArticleDisplays.add(articleDisplay);
					if (article.getResourcePrimKey() == id) {
						renderRequest.setAttribute("articleDisplay", articleDisplay);
					}
				}
				renderRequest.setAttribute("journalArticleDisplays", journalArticleDisplays);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}