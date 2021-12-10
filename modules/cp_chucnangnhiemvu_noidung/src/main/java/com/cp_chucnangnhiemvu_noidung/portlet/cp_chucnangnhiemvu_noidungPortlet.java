package com.cp_chucnangnhiemvu_noidung.portlet;

import com.cp_chucnangnhiemvu_noidung.constants.cp_chucnangnhiemvu_noidungPortletKeys;
import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleDisplay;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
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
		"javax.portlet.display-name=cp_chucnangnhiemvu_noidung", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + cp_chucnangnhiemvu_noidungPortletKeys.CP_CHUCNANGNHIEMVU_NOIDUNG,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class cp_chucnangnhiemvu_noidungPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			// http://portal.lifetek.vn/web/lifetek/chinh-phu/noidungchucnangnhiemvu?id=******
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			// id trong http dung de lay ra webcontent
			int id = Integer.parseInt(PortalUtil.getOriginalServletRequest(request).getParameter("id"));
			// lấy category : Chức năng nhiệm vụ
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
				// lấy nội dung cần hiển thị
				String content = articleDisplay.getContent();
				if (journalArticle.getResourcePrimKey() == id) {
					renderRequest.setAttribute("content", content);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}