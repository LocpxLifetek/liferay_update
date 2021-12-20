package ajax.get.portlet;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import ajax.get.constants.AjaxGetPortletKeys;

/**
 * @author Dell
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=AjaxGet", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + AjaxGetPortletKeys.AJAXGET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class AjaxGetPortlet extends MVCPortlet {
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
	    //get person id using getparameter 
//		String name  = ParamUtil.getString(resourceRequest, "name");
//		String emailId = ParamUtil.getString(resourceRequest, "email");
		
//		System.out.println(name);
//		String param = ParamUtil.get(resourceRequest, "name", StringPool.BLANK);
//
//		System.out.println("Parameter is ==>" + param);
		String countryName = ParamUtil.getString(resourceRequest, "countryName");
		System.out.println("Parameter is ==> " + countryName);
		JSONArray usersJsonArray = JSONFactoryUtil.createJSONArray();

		JSONObject jsonUser = null;

	    List<BlogsEntry> userList = BlogsEntryLocalServiceUtil.getBlogsEntries(0, 6);
		for (BlogsEntry userObj : userList) {
			jsonUser = JSONFactoryUtil.createJSONObject();
			jsonUser.put("id", userObj.getUuid());
			jsonUser.put("name", userObj.getTitle());
		
		usersJsonArray.put(jsonUser);
		}

//	    json.put(blogsEntries);
	    resourceResponse.getWriter().write(usersJsonArray.toString());
	}

	public List<BlogsEntry> doGetBlogsEntries(int a, int b) {
		List<BlogsEntry> blogsEntries = new ArrayList<BlogsEntry>();
		try {
			blogsEntries = BlogsEntryLocalServiceUtil.getBlogsEntries(a, b);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return blogsEntries;

	}

	public void doViewVB() throws IOException, PortletException, java.io.IOException {
		RenderRequest renderRequest = null;
		RenderResponse renderResponse = null;
		List<BlogsEntry> blogEntrise = new ArrayList<BlogsEntry>();
		try {
			blogEntrise = BlogsEntryLocalServiceUtil.getBlogsEntries(1, 6);
			renderRequest.setAttribute("userList", blogEntrise);
			System.out.println(blogEntrise);
		} finally {
			// TODO: handle finally clause
		}
		super.doView(renderRequest, renderResponse);
	}

}