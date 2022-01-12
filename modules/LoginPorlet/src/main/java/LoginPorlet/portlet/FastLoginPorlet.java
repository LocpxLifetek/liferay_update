package LoginPorlet.portlet;

import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import LoginPorlet.constants.LoginPorletPortletKeys;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.icon=/icons/login.png",
			"com.liferay.portlet.instanceable=true",
			"com.liferay.portlet.preferences-owned-by-group=true",
			"com.liferay.portlet.private-request-attributes=false",
			"com.liferay.portlet.private-session-attributes=false",
			"com.liferay.portlet.render-weight=50",
			"com.liferay.portlet.single-page-application=false",
			"com.liferay.portlet.use-default-template=true",
			"javax.portlet.display-name=Fast Sign In",
			"com.liferay.portlet.struts-path=plugins_admin",
			"javax.portlet.expiration-cache=0",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.config-template=/configuration.jsp",
			"javax.portlet.init-param.template-path=/META-INF/resources/",
			"javax.portlet.init-param.view-template=/login.jsp",
			"javax.portlet.name=" + LoginPorletPortletKeys.FAST_LOGIN,
			"javax.portlet.portlet-mode=text/html;config",
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class FastLoginPorlet extends MVCPortlet{
	protected void setRelease(Release release) {
	}
}
