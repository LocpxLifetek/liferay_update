package goverment.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import goverment.constants.GovermentPortletKeys;
@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Footer_H05",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/footerH05.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.FOOTERH05,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class FooterH05Porlet extends MVCPortlet  {

}
