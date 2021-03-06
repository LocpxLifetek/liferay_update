package com.cp_chinhsachthanhtuu_noidung.portlet;

import com.cp_chinhsachthanhtuu_noidung.constants.cp_chinhsachthanhtuu_noidungPortletKeys;
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
		"javax.portlet.display-name=cp_chinhsachthanhtuu_noidung",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + cp_chinhsachthanhtuu_noidungPortletKeys.CP_CHINHSACHTHANHTUU_NOIDUNG,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class cp_chinhsachthanhtuu_noidungPortlet extends MVCPortlet {
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			// http://portal.lifetek.vn/web/lifetek/chinhphu/noidungchinhsachthanhtuu?categoryId=******&priKey=******
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			int categoryId = Integer.parseInt(PortalUtil.getOriginalServletRequest(request).getParameter("categoryId"));
			int priKey = Integer.parseInt(PortalUtil.getOriginalServletRequest(request).getParameter("priKey"));
			
			if (categoryId == 210502) {
			AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(210502);
			renderRequest.setAttribute("assetCategory", assetCategory);
			List<AssetEntryAssetCategoryRel> assetEntryAssetCategoryReles = AssetEntryAssetCategoryRelLocalServiceUtil.getAssetEntryAssetCategoryRelsByAssetCategoryId(210502);
			// list hien thi cac webcontent
			List<JournalArticleDisplay> articleDisplays = new ArrayList<>();
			
			for (AssetEntryAssetCategoryRel a : assetEntryAssetCategoryReles) {
				long asssetEntryId = a.getAssetEntryId();
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(asssetEntryId);
				long classPk = assetEntry.getClassPK();
				JournalArticle article = JournalArticleLocalServiceUtil.getLatestArticle(classPk);
				
				ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
				String articleId = article.getArticleId();
				JournalArticleDisplay articleDisplay = JournalArticleLocalServiceUtil
						.getArticleDisplay(themeDisplay.getScopeGroupId(), articleId, "", "", themeDisplay);
				articleDisplays.add(articleDisplay);
				if (articleDisplay.getResourcePrimKey() == priKey) {
					renderRequest.setAttribute("articleDisplay", articleDisplay);
				}
			}
			renderRequest.setAttribute("articleDisplays", articleDisplays);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}