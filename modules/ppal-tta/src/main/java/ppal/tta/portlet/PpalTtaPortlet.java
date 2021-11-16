package ppal.tta.portlet;

import ppal.tta.constants.PpalTtaPortletKeys;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author java05
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=PpalTta", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + PpalTtaPortletKeys.PPALTTA,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class PpalTtaPortlet extends MVCPortlet {

    
    @Override
    public void serveResource(ResourceRequest resourceRequest,
            ResourceResponse resourceResponse) throws IOException,
            PortletException {
        
        String param = ParamUtil.get(resourceRequest, "param2", StringPool.BLANK);
        
//        _log.info("Parameter is ==>" + param);
        
        resourceResponse.setContentType("text/html");
        PrintWriter out = resourceResponse.getWriter();
        out.print("You have entered ==>"+param);
//        _log.info("Ajax call is performed");
        out.flush();
        super.serveResource(resourceRequest, resourceResponse);
    }
    
	public void arrayOfJSONUserObjects(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		JSONArray usersJsonArray = JSONFactoryUtil.createJSONArray();
		try {
//			logger.info("====serveResource========");
	
			JSONObject jsonUser = null;
List<BlogsEntry> blogsEntries = BlogsEntryLocalServiceUtil.getBlogsEntries(0, BlogsEntryLocalServiceUtil.getBlogsEntriesCount());

			List<User> userList = UserLocalServiceUtil.getUsers(1, UserLocalServiceUtil.getUsersCount());
			for (User userObj : userList) {
				jsonUser = JSONFactoryUtil.createJSONObject();
				jsonUser.put("userId", userObj.getUserId());
				jsonUser.put("screeName", userObj.getScreenName());
				jsonUser.put("firstName", userObj.getFirstName());
				jsonUser.put("lastName", userObj.getLastName());
				jsonUser.put("email", userObj.getEmailAddress());
				jsonUser.put("jobTitle", userObj.getJobTitle());
				usersJsonArray.put(jsonUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JSONObject tableData = JSONFactoryUtil.createJSONObject();
		tableData.put("data", usersJsonArray);
//			logger.info("tableData:"+tableData.toString());
		ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse), tableData.toString());
	}
}