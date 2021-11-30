package com.cp_chucnangnhiemvu.portlet;

import com.cp_chucnangnhiemvu.constants.cp_chucnangnhiemvuPortletKeys;
import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleDisplay;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
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

/**
 * @author java05
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=cp_chucnangnhiemvu", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + cp_chucnangnhiemvuPortletKeys.CP_CHUCNANGNHIEMVU,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class cp_chucnangnhiemvuPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {	
			List<JournalArticleDisplay> outs = new ArrayList<>();
			// lấy danh sách chức năng nhiệm vụ theo category
			List<AssetEntryAssetCategoryRel> entryCategory = AssetEntryAssetCategoryRelLocalServiceUtil
					.getAssetEntryAssetCategoryRelsByAssetCategoryId(204365);
			for (AssetEntryAssetCategoryRel a : entryCategory) {
				long assetEntryId = a.getAssetEntryId();
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(assetEntryId);
				long classPk = assetEntry.getClassPK();
				JournalArticle journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(classPk);
				
				ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
				String articleId = journalArticle.getArticleId();
				JournalArticleDisplay articleDisplay = JournalArticleLocalServiceUtil
						.getArticleDisplay(themeDisplay.getScopeGroupId(), articleId, "", "", themeDisplay);
				outs.add(articleDisplay);
				
			}
			renderRequest.setAttribute("journalArticles", outs);
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}