package com.contentBcp.portlet;

import com.contentBcp.constants.contentBaoCpPortletKeys;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

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
		"javax.portlet.display-name=contentBaoCp",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + contentBaoCpPortletKeys.CONTENTBAOCP,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class contentBaoCpPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			// http://172.16.100.70:8080/web/lifetek/cacbaiphatbieucuathutuong?id=******
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			int id = Integer.parseInt(PortalUtil.getOriginalServletRequest(request).getParameter("id"));
			
			BlogsEntry blogsEntry = BlogsEntryLocalServiceUtil.getBlogsEntry(id);
			renderRequest.setAttribute("title", blogsEntry.getTitle());
			renderRequest.setAttribute("content", blogsEntry.getContent());
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}