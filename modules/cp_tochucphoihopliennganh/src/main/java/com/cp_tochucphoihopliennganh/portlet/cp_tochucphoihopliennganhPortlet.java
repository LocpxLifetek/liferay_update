package com.cp_tochucphoihopliennganh.portlet;

import com.cp_tochucphoihopliennganh.constants.cp_tochucphoihopliennganhPortletKeys;
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
		"javax.portlet.display-name=cp_tochucphoihopliennganh",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + cp_tochucphoihopliennganhPortletKeys.CP_TOCHUCPHOIHOPLIENNGANH,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class cp_tochucphoihopliennganhPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			// webcontent id = 207400
			JournalArticle journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(207400);
			
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String articleId = journalArticle.getArticleId();
			JournalArticleDisplay articleDisplay = JournalArticleLocalServiceUtil
					.getArticleDisplay(themeDisplay.getScopeGroupId(), articleId, "", "", themeDisplay);
			
			renderRequest.setAttribute("articleDisplay", articleDisplay);
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}