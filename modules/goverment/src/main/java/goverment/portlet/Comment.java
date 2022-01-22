package goverment.portlet;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import goverment.constants.GovermentPortletKeys;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Comments",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/comment.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.COMMENTGOVERMENT,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class Comment extends MVCPortlet  {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String uuid =  PortalUtil.getOriginalServletRequest(request).getParameter("uuid");
			BlogsEntry blogsEntry=BlogsEntryLocalServiceUtil.getBlogsEntryByUuidAndGroupId(uuid, themeDisplay.getScopeGroupId());
			renderRequest.setAttribute("blogsEntry", blogsEntry);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}
