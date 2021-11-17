package com.cp_cactochucphoihoplienngnanh.portlet;

import com.cp_cactochucphoihoplienngnanh.constants.cp_cactochucphoihoplienngnanhPortletKeys;
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
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=cp_cactochucphoihoplienngnanh", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + cp_cactochucphoihoplienngnanhPortletKeys.CP_CACTOCHUCPHOIHOPLIENNGNANH,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class cp_cactochucphoihoplienngnanhPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			// http://localhost:8080/web/lifetek/chinhphu/cactochucphoihopliennganh?name=****&categoryId=******
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			int categoryId = Integer.parseInt(PortalUtil.getOriginalServletRequest(request).getParameter("categoryId"));
			String name = PortalUtil.getOriginalServletRequest(request).getParameter("name");
			// categoryId 207389
			List<JournalArticleDisplay> articleDisplays = new ArrayList<>();
			if (name.equals("cbcd") || name.equals("chd") || name.equals("cub")) {
				AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(categoryId);
				
				List<AssetEntryAssetCategoryRel> entryCategory = AssetEntryAssetCategoryRelLocalServiceUtil
						.getAssetEntryAssetCategoryRelsByAssetCategoryId(categoryId);

				for (AssetEntryAssetCategoryRel a : entryCategory) {
					long assetEntryId = a.getAssetEntryId();
					AssetEntry assetE = AssetEntryLocalServiceUtil.getAssetEntry(assetEntryId);
					long classPk = assetE.getClassPK(); // classPk = latestArticle
					JournalArticle journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(classPk);

					ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
					String articleId = journalArticle.getArticleId();
					JournalArticleDisplay articleDisplay = JournalArticleLocalServiceUtil
							.getArticleDisplay(themeDisplay.getScopeGroupId(), articleId, "", "", themeDisplay);
					articleDisplays.add(articleDisplay);
				}
				renderRequest.setAttribute("assetCategory", assetCategory);
				renderRequest.setAttribute("name", name);
			}
			renderRequest.setAttribute("articleDisplays", articleDisplays);
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}