package com.controlMenu.portlet;

import com.controlMenu.constants.controlMenuPortletKeys;
import com.liferay.adaptive.media.exception.AMRuntimeException.IOException;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;


import serviceBuilder.model.Menus;
import serviceBuilder.service.MenusLocalServiceUtil;

/**
 * @author java05
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=controlMenu",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + controlMenuPortletKeys.CONTROLMENU,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class controlMenuPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException, java.io.IOException {
		HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
	    String articleId =  PortalUtil.getOriginalServletRequest(request).getParameter("articleId");
		
		List<Menus> menus = MenusLocalServiceUtil.getMenuses(0, 100);
		renderRequest.setAttribute("menuList", menus);
		renderRequest.setAttribute("articleId", articleId);
		super.doView(renderRequest, renderResponse);
	}
	
	public static void call() {
		try {
			Connection connection =   DataAccess.getConnection();
			CallableStatement stmt  = connection.prepareCall("{call test(?,?,?)}");
			stmt.setInt(1, 10158);
			stmt.setInt(2, 10159);
			stmt.setInt(3, 10156);

			boolean isResult = stmt.execute();

			if(isResult){
			 ResultSet rs1 = stmt.getResultSet();
			 while(rs1.next()){
				 System.out.println("UserId=>"+rs1.getString("userId"));
				 System.out.println("Company Id=>"+rs1.getString("companyId"));
			 }
			 stmt.getMoreResults(Statement.CLOSE_CURRENT_RESULT);
			 ResultSet rs2 = stmt.getResultSet();
			 while(rs2.next()){
				 System.out.println("ContactId=>"+rs2.getString("contactId"));
				 System.out.println("Email Address=>"+rs2.getString("emailAddress"));
			 }
			
			 stmt.getMoreResults(Statement.CLOSE_CURRENT_RESULT);
			 ResultSet rs3 = stmt.getResultSet();
			 while(rs3.next()){
				 System.out.println("Name =>"+rs3.getString("name"));
				 System.out.println("Legal Name =>"+rs3.getString("legalName"));
			 }

			}
			} catch (SQLException e) {
			   e.printStackTrace();
		}
	}
}