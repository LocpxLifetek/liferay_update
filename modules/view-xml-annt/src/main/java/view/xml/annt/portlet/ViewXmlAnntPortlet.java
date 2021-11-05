package view.xml.annt.portlet;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.message.boards.model.MBMessage;
import com.liferay.message.boards.service.MBMessageLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import view.xml.annt.constants.ViewXmlAnntPortletKeys;

/**
 * @author Dell
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=ViewXmlAnnt", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + ViewXmlAnntPortletKeys.VIEWXMLANNT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class ViewXmlAnntPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest1, RenderResponse renderResponse)
			throws IOException, PortletException, java.io.IOException {
		try {

			long idJournalArticle = 83453;
			JournalArticle journalArticle = JournalArticleLocalServiceUtil.getArticle(idJournalArticle);
			renderRequest1.setAttribute("journalArticle", journalArticle);


			String str = journalArticle.getContent();
//			MBMessage mBMessage= MBMessageLocalServiceUtil.gets 
			renderRequest1.setAttribute("journalArticleContent", str.toString());
		} catch (Exception e) {
			// TODO: handle exception

		}
		super.doView(renderRequest1, renderResponse);
	}
}