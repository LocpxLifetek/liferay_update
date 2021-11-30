package com.cp_baihockinhnghiem.portlet;

import com.cp_baihockinhnghiem.constants.cp_baihockinhnghiemPortletKeys;
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
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author java05
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=cp_baihockinhnghiem", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + cp_baihockinhnghiemPortletKeys.CP_BAIHOCKINHNGHIEM,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class cp_baihockinhnghiemPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			List<Integer> categoryIds = Arrays.asList(221207, 221210);
			int i = 0;
			for (Integer c : categoryIds) {
				i++;
				AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(c);
				renderRequest.setAttribute("assetCategory" + i, assetCategory);

				List<AssetEntryAssetCategoryRel> assetCategoryReles = AssetEntryAssetCategoryRelLocalServiceUtil
						.getAssetEntryAssetCategoryRelsByAssetCategoryId(c);
				List<JournalArticleDisplay> journalArticleDisplays = new ArrayList<>();
				for (AssetEntryAssetCategoryRel a : assetCategoryReles) {
					long entryId = a.getAssetEntryId();
					AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(entryId);
					long prikey = assetEntry.getClassPK();
					JournalArticle journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(prikey);
					
					ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
					String articleId = journalArticle.getArticleId();
					JournalArticleDisplay articleDisplay = JournalArticleLocalServiceUtil
							.getArticleDisplay(themeDisplay.getScopeGroupId(), articleId, "", "", themeDisplay);
					
					journalArticleDisplays.add(articleDisplay);
				}
				renderRequest.setAttribute("journalArticleDisplays" + i, journalArticleDisplays);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}