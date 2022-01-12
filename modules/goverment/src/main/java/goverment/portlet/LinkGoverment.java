package goverment.portlet;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import goverment.config.ConvertXmlToJson;
import goverment.constants.GovermentPortletKeys;
import goverment.dto.DlFileEntryDto;
import goverment.dto.JournalAricleDto;
import goverment.sql.LinkSql;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=linkGoverment",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/linkGoverment.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.LINKGOVERMENT,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class LinkGoverment extends MVCPortlet{
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			List<DlFileEntryDto> listDlFileEntryDtos=new ArrayList<>();
			List<JournalAricleDto> listJournalAricleDtos = new LinkSql().findJournalArticle(themeDisplay.getScopeGroupId());
			for (JournalAricleDto journalAricleDto : listJournalAricleDtos) {

				JournalArticle journalArticle = JournalArticleLocalServiceUtil
						.getLatestArticle(journalAricleDto.getResourcePrimkey(), 0);
				JSONObject json = new ConvertXmlToJson().parseContent(journalArticle.getContent(), journalArticle.getDefaultLanguageId());
				Iterator<String> keys = json.keySet().iterator();
				while (keys.hasNext()) {
					String key = keys.next();
					String value = json.get(key).toString();
					JSONObject object = JSONFactoryUtil.createJSONObject(value);
					DlFileEntryDto dlFileEntryDto = new DlFileEntryDto();
					dlFileEntryDto.setClassPK(object.getInt("classPK"));
					dlFileEntryDto.setGroupId(object.getInt("groupId"));
					dlFileEntryDto.setType(object.getString("type"));
					dlFileEntryDto.setTitle(object.getString("title"));
					dlFileEntryDto.setUuidCategory(object.getString("uuid"));
					dlFileEntryDto.setFolderId(journalArticle.getFolderId());
					dlFileEntryDto.setSrc(journalArticle.getSmallImageURL());
					listDlFileEntryDtos.add(dlFileEntryDto);
					
				}

			}
			renderRequest.setAttribute("listDlFileEntryDtos", listDlFileEntryDtos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		super.doView(renderRequest, renderResponse);
	}
}