package com.thongtindientu.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.thongtindientu.constants.cp_thongtindientuPortletKeys;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author java05
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=cp_thongtindientu", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + cp_thongtindientuPortletKeys.CP_THONGTINDIENTU,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class cp_thongtindientuPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat spdf = new SimpleDateFormat("dd/MM/yyyy");
			@SuppressWarnings("deprecation")
			int day = timestamp.getDay();
			switch (day) {
			case 0:
				renderRequest.setAttribute("homnay", "Sunday, " + spdf.format(timestamp));
				break;
			case 1:
				renderRequest.setAttribute("homnay", "Monday, " + spdf.format(timestamp));
				break;
			case 2:
				renderRequest.setAttribute("homnay", "Tuesday, " + spdf.format(timestamp));
				break;
			case 3:
				renderRequest.setAttribute("homnay", "Wednesday, " + spdf.format(timestamp));
				break;
			case 4:
				renderRequest.setAttribute("homnay", "Thursday, " + spdf.format(timestamp));
				break;
			case 5:
				renderRequest.setAttribute("homnay", "Friday, " + spdf.format(timestamp));
				break;
			case 6:
				renderRequest.setAttribute("homnay", "Saturday, " + spdf.format(timestamp));
				break;
			default:
				break;
			}
		} catch (Exception e) {
		}
		super.doView(renderRequest, renderResponse);
		
	}
}