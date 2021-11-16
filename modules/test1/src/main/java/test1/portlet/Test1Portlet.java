package test1.portlet;

import test1.constants.Test1PortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

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
		"javax.portlet.display-name=Test1",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + Test1PortletKeys.TEST1,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-public-render-parameter=ipcMessage"
	},
	service = Portlet.class
)
public class Test1Portlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		String ipcMessage=ParamUtil.getString(renderRequest, "ipcMessage","");
		renderRequest.setAttribute("ipcMessage", ipcMessage);
		super.doView(renderRequest, renderResponse);
	}
	
}