package goverment.portlet;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import goverment.config.ConvertXmlToJson;
import goverment.constants.GovermentPortletKeys;
import goverment.dto.JournalAricleDto;
import goverment.dto.JournalArticleLocazationDto;
import goverment.dto.JournalFolderDto;
import goverment.sql.JournalSql;
import goverment.url.UrlCurrentPorlet;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=DetailLegalDocument",
			"com.liferay.portlet.header-portlet-css=/META-INF/resources/css/main.css",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/META-INF/resources/DetailLegalDocument.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.DETAILLEGALDOCUMENT,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
		)
public class DetailLegalDocument extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			Map<String, String> map = new HashMap<>();
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String id = PortalUtil.getOriginalServletRequest(request).getParameter("class_id");
			long idJournalArticle = Long.parseLong(id);
			JournalArticle journalArticleObject = JournalArticleLocalServiceUtil.getArticle(idJournalArticle);
			JSONObject json = new ConvertXmlToJson().parseContent(journalArticleObject.getContent(),
					journalArticleObject.getDefaultLanguageId());
			Iterator<String> keys = json.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				String removeKeyUtf8 = key.replaceAll("[^\\p{ASCII}]", "");
				String valueGoverment = null;
				String value = json.get(key).toString();
				String keyValue = null;
				int upper = 0;
				if (value != null && value.isEmpty() == false) {
					for (int i = 0; i < key.length(); i++) {
						for (int j = i + 1; j <= i + 1; j++) {
							if ((key.charAt(i) >= 'A' && key.charAt(i) <= 'Z')
									&& (key.charAt(j) >= 'A' && key.charAt(j) <= 'Z')) {
								upper++;

							}
						}
					}
					if (upper == 0) {
						keyValue = Character.toUpperCase(key.charAt(0))
								+ key.substring(1).replaceAll("(?<!_)(?=[A-Z])", " ");
					} else {
						keyValue = key;
					}
					if (removeKeyUtf8.equals("NgyBanHnh")) {
						String[] parts = value.split("-");
						valueGoverment = parts[2] + "/" + parts[1] + "/" + parts[0];
					} else {
						valueGoverment = value;
					}
					map.put(keyValue, valueGoverment);
				}
			}
			
			try {
				ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
				Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);
				String pageDetail = PortalUtil.getOriginalServletRequest(request).getParameter("page");
				String url = new UrlCurrentPorlet().urlCurrentPorlet(PortalUtil.getLayoutFriendlyURL(layout, themeDisplay),
						themeDisplay.getLayoutFriendlyURL(layout),themeDisplay.getCDNBaseURL());
				renderRequest.setAttribute("url", url);
				JournalFolderDto journalFolder= new JournalSql().findFolderByArticalId(idJournalArticle, themeDisplay.getScopeGroupId());
				int count = new JournalSql().countJournal(journalFolder.getId(), themeDisplay.getScopeGroupId());
				int page = Integer.parseInt(pageDetail == null ? "1" : pageDetail);
				int size = 10;
				int result = (int) Math.ceil((float) count / size);
				List<JournalAricleDto> listJournalArticleDtos = new JournalSql().findAllJournalArticleAndDontId(page, size, journalFolder.getId(),themeDisplay.getScopeGroupId());
				List<JournalArticleLocazationDto> listJournalArticleLocazationDto = new ArrayList<>();
				for (JournalAricleDto journalArticleDto : listJournalArticleDtos) {
					JournalArticle journalArticle = JournalArticleLocalServiceUtil
							.getArticle(journalArticleDto.getId());
					JournalArticleLocazationDto journalArticleLocazationDto = new JournalSql().findAllJornalArticleByArticlePK(
							journalArticle.getId(), journalArticle.getId());
					listJournalArticleLocazationDto.add(journalArticleLocazationDto);
				}
				renderRequest.setAttribute("listJournalArticleLocazationDto", listJournalArticleLocazationDto);
				renderRequest.setAttribute("currentPage", page);
				renderRequest.setAttribute("totalPage", result);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			renderRequest.setAttribute("map", map);

		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}

}
