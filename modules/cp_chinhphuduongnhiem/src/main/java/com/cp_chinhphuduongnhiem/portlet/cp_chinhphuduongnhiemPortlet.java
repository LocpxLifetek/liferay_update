package com.cp_chinhphuduongnhiem.portlet;

import com.cp_chinhphuduongnhiem.constants.cp_chinhphuduongnhiemPortletKeys;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleDisplay;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

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
		"javax.portlet.display-name=cp_chinhphuduongnhiem",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + cp_chinhphuduongnhiemPortletKeys.CP_CHINHPHUDUONGNHIEM,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class cp_chinhphuduongnhiemPortlet extends MVCPortlet {
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
//			String content = "";
			JournalArticle journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(189280);
			
			ThemeDisplay themeDisplay1 = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String articleId = journalArticle.getArticleId();
			JournalArticleDisplay articleDisplay = JournalArticleLocalServiceUtil
					.getArticleDisplay(themeDisplay1.getScopeGroupId(), articleId, "", "", themeDisplay1);
			String content = articleDisplay.getContent();
			
			renderRequest.setAttribute("webContent", content);
		} catch (Exception e) {
			
		}
		super.doView(renderRequest, renderResponse);
	}
}