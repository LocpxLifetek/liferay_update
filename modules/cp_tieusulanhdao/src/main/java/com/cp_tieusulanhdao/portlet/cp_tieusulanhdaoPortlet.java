package com.cp_tieusulanhdao.portlet;

import com.cp_tieusulanhdao.constants.cp_tieusulanhdaoPortletKeys;
import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleDisplay;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
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
		"javax.portlet.display-name=cp_tieusulanhdao", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + cp_tieusulanhdaoPortletKeys.CP_TIEUSULANHDAO,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class cp_tieusulanhdaoPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			String content = StringPool.BLANK; //
			// "http://localhost:8080/web/lifetek/chinhphu/tieusulanhdao?categoryId=198798&id=199303";
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			// id trong http dùng để check id đó có chủ để là categoryId=198798 (Tiểu sử)
			int id = Integer.parseInt(PortalUtil.getOriginalServletRequest(request).getParameter("id"));
			int categoryId = Integer.parseInt(PortalUtil.getOriginalServletRequest(request).getParameter("categoryId"));
			List<AssetEntryAssetCategoryRel> entryCategory = AssetEntryAssetCategoryRelLocalServiceUtil
					.getAssetEntryAssetCategoryRelsByAssetCategoryId(categoryId);
			for (AssetEntryAssetCategoryRel a : entryCategory) {
				long assetEntryId = a.getAssetEntryId();
				AssetEntry assetE = AssetEntryLocalServiceUtil.getAssetEntry(assetEntryId);
				long classPk = assetE.getClassPK(); // classPk = latestArticle
				JournalArticle journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(classPk);

				ThemeDisplay themeDisplay1 = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
				String articleId = journalArticle.getArticleId();
				JournalArticleDisplay articleDisplay = JournalArticleLocalServiceUtil
						.getArticleDisplay(themeDisplay1.getScopeGroupId(), articleId, "", "", themeDisplay1);
				content = articleDisplay.getContent();
				if (journalArticle.getResourcePrimKey() == id) {
					renderRequest.setAttribute("webContent", content);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}