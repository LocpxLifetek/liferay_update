package com.cp_thutuongchinhphucacnhiemky.portlet;

import com.cp_thutuongchinhphucacnhiemky.constants.cp_thutuongchinhphucacnhiemkyPortletKeys;
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
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

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
		"javax.portlet.display-name=cp_thutuongchinhphucacnhiemky",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + cp_thutuongchinhphucacnhiemkyPortletKeys.CP_THUTUONGCHINHPHUCACNHIEMKY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class cp_thutuongchinhphucacnhiemkyPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			AssetCategory tieusutvcp = AssetCategoryLocalServiceUtil.getAssetCategory(216220);
			renderRequest.setAttribute("tieusutvcp", tieusutvcp);
			List<AssetEntryAssetCategoryRel> assetTieusu = AssetEntryAssetCategoryRelLocalServiceUtil
					.getAssetEntryAssetCategoryRelsByAssetCategoryId(216220);
			List<JournalArticleDisplay> articleDisplaysTs = new ArrayList<>();
			for (AssetEntryAssetCategoryRel a : assetTieusu) {
				long asssetEntryId = a.getAssetEntryId();
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(asssetEntryId);
				long classPk = assetEntry.getClassPK();
				JournalArticle article = JournalArticleLocalServiceUtil.getLatestArticle(classPk);

				ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
				String articleId = article.getArticleId();
				JournalArticleDisplay articleDisplay = JournalArticleLocalServiceUtil
						.getArticleDisplay(themeDisplay.getScopeGroupId(), articleId, "", "", themeDisplay);
//				articleDisplaysTs.add(articleDisplay);
				if (articleDisplay.getTitle().contains(String.valueOf(1945))) {
					renderRequest.setAttribute("tieusu1", articleDisplay);
				}else {
				articleDisplaysTs.add(articleDisplay);
				}
			}
			renderRequest.setAttribute("displayTieusu", articleDisplaysTs);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}