package goverment.portlet;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
import goverment.dto.JournalArticleIdAndValue;
import goverment.dto.JournalFolderDto;
import goverment.sql.JournalSql;
import goverment.url.UrlCurrentPorlet;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=LegalDocument",
			"com.liferay.portlet.header-portlet-css=/META-INF/resources/css/main.css",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/META-INF/resources/LegalDocument.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.LEGALDOCUMENT,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class LegalDocument extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);
			String pageDetail = PortalUtil.getOriginalServletRequest(request).getParameter("page");
			String url = new UrlCurrentPorlet().urlCurrentPorlet(PortalUtil.getLayoutFriendlyURL(layout, themeDisplay),
					themeDisplay.getLayoutFriendlyURL(layout),themeDisplay.getCDNBaseURL());
			renderRequest.setAttribute("url", url);
			JournalFolderDto journalFolder= new JournalSql().findFolderByUuid("2d5d4333-7065-d6f4-1847-e6eb8053d985" , themeDisplay.getScopeGroupId());
			int count = new JournalSql().countJournal(journalFolder.getId(), themeDisplay.getScopeGroupId());
			int page = Integer.parseInt(pageDetail == null ? "1" : pageDetail);
			int size = 1;
			int result = (int) Math.ceil((float) count / size);
			List<Map<String, List<JournalArticleIdAndValue>>> mapList = new ArrayList<>();
			List<JournalAricleDto> listJournalArticleDtos = new JournalSql().findAllJournalArticleDtoByFolderId(page, size,journalFolder.getId(), themeDisplay.getScopeGroupId());
			for (JournalAricleDto journalArticleDto : listJournalArticleDtos) {
				Map<String, List<JournalArticleIdAndValue>> map = new LinkedHashMap<>();
				List<JournalArticleIdAndValue> listJournalArticleIdAndValues = new ArrayList<>();

				JournalArticle journalArticleObject = JournalArticleLocalServiceUtil
						.getLatestArticle(journalArticleDto.getResourcePrimkey());
				if (journalArticleObject.getStatus() == 0) {

					long journalArticleId = journalArticleObject.getId();

					JSONObject json = new ConvertXmlToJson().parseContent(journalArticleObject.getContent(),
							journalArticleObject.getDefaultLanguageId());
					Iterator<String> keys = json.keySet().iterator();
					while (keys.hasNext()) {
						JournalArticleIdAndValue journalArticleIdAndValue = new JournalArticleIdAndValue();
						String key = keys.next();
						String removeKeyUtf8 = key.replaceAll("[^\\p{ASCII}]", "");
						if (removeKeyUtf8.equals("NgyBanHnh") || removeKeyUtf8.equals("TrchYu")
								|| removeKeyUtf8.equals("SKHiu")) {
							String value = json.get(key).toString();
							journalArticleIdAndValue.setId(journalArticleId);
							journalArticleIdAndValue.setKey(key);
							if (removeKeyUtf8.equals("NgyBanHnh")) {
								String[] parts = value.split("-");
								String date = parts[2] +"/"+ parts[1] +"/"+ parts[0];
								journalArticleIdAndValue.setValue(date);
							} else {
								journalArticleIdAndValue.setValue(value);
							}
							listJournalArticleIdAndValues.add(journalArticleIdAndValue);

						}
					}
					map.put("1", listJournalArticleIdAndValues);
					renderRequest.setAttribute("currentPage", page);
					renderRequest.setAttribute("totalPage", result);
					mapList.add(map);
				}
			}

			renderRequest.setAttribute("maps", mapList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}
