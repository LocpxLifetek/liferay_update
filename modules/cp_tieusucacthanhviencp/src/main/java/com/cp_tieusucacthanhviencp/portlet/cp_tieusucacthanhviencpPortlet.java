package com.cp_tieusucacthanhviencp.portlet;

import com.cp_tieusucacthanhviencp.constants.cp_tieusucacthanhviencpPortletKeys;
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
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=cp_tieusucacthanhviencp", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + cp_tieusucacthanhviencpPortletKeys.CP_TIEUSUCACTHANHVIENCP,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class cp_tieusucacthanhviencpPortlet extends MVCPortlet {
	private final String LINK_TTCPCNK = "http://portal.lifetek.vn/web/lifetek/chinh-phu/thutuongchinhphucacnhiemky";
	private final String LINK_TVCPQCTK = "http://portal.lifetek.vn/web/lifetek/chinh-phu/thanhvienchinhphuquacacthoiky?priKey=";
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			renderRequest.setAttribute("LINK_TTCPCNK", LINK_TTCPCNK);
			renderRequest.setAttribute("LINK_TVCPQCTK", LINK_TVCPQCTK);
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
				if (articleDisplay.getTitle().contains(String.valueOf(1945))) {
					renderRequest.setAttribute("tieusu1", articleDisplay);
				}else {
				articleDisplaysTs.add(articleDisplay);
				}
			}
			renderRequest.setAttribute("displayTieusu", articleDisplaysTs);
			
			
			
			// 211761 CHÃ�NH PHá»¦ QUA CÃ�C THá»œI Ká»²
			AssetCategory chinhphuqctk = AssetCategoryLocalServiceUtil.getAssetCategory(211761);
			renderRequest.setAttribute("chinhphuqctk", chinhphuqctk);
			List<AssetEntryAssetCategoryRel> assetChinhphu = AssetEntryAssetCategoryRelLocalServiceUtil
					.getAssetEntryAssetCategoryRelsByAssetCategoryId(211761);
			List<JournalArticleDisplay> articleDisplaysCp = new ArrayList<>();
			for (AssetEntryAssetCategoryRel cp : assetChinhphu) {
				long asssetEntryId = cp.getAssetEntryId();
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(asssetEntryId);
				long classPk = assetEntry.getClassPK();
				JournalArticle article = JournalArticleLocalServiceUtil.getLatestArticle(classPk);

				ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
				String articleId = article.getArticleId();
				JournalArticleDisplay articleDisplay = JournalArticleLocalServiceUtil
						.getArticleDisplay(themeDisplay.getScopeGroupId(), articleId, "", "", themeDisplay);
				articleDisplaysCp.add(articleDisplay);
			}
			renderRequest.setAttribute("displayChinhphu", articleDisplaysCp);
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}