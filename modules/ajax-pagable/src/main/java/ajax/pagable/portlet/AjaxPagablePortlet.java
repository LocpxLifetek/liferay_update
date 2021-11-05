package ajax.pagable.portlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import ajax.pagable.constants.AjaxPagablePortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Dell
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=AjaxPagable", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + AjaxPagablePortletKeys.AJAXPAGABLE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class AjaxPagablePortlet extends MVCPortlet {

	/**
	 * This is what the AJAX request hits.
	 */
	public void viewdoView() throws IOException, PortletException, java.io.IOException {
		RenderRequest renderRequest = null;
		RenderResponse renderResponse;
		try {
			BlogsEntry blogEntry = BlogsEntryLocalServiceUtil.getBlogsEntry(65412);
			renderRequest.setAttribute("blogEntry", blogEntry);
			if (blogEntry == null) {
				System.out.println("null");

			}
			System.out.println(blogEntry.getTitle());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {

		System.out.println("inside resource method for testing ajax call using resource url");
//
//		current_page: trang hiện tại
//		limit: số records hiển thị trên mỗi trang
		
		String startPageParam = resourceRequest.getParameter("start");
		String limitstartPageParam = resourceRequest.getParameter("limit");
		
		System.out.println("====serveResource========");
		
		JSONObject jsonUser = null;
		JSONArray usersJsonArray = JSONFactoryUtil.createJSONArray();
		
		
		int limit = Integer.parseInt(limitstartPageParam);
		int start = Integer.parseInt(startPageParam);
		int end = start * limit-1;
		
		List<BlogsEntry> catList = BlogsEntryLocalServiceUtil.getBlogsEntries(start, end);
		
		int totalRecord = BlogsEntryLocalServiceUtil.getBlogsEntriesCount();
		int totalPage = totalRecord%limit;

    	
		jsonUser = JSONFactoryUtil.createJSONObject();
		jsonUser.put("total_record", totalRecord);
		jsonUser.put("total_page", totalPage);
		jsonUser.put("limit", limit);
		jsonUser.put("start", start);
		
		usersJsonArray.put(jsonUser);
		
		for (BlogsEntry userObj : catList) {
			jsonUser = JSONFactoryUtil.createJSONObject();
			jsonUser.put("id", userObj.getTitle());
			jsonUser.put("name", userObj.getTitle());
			usersJsonArray.put(jsonUser);
		}
		PrintWriter out = resourceResponse.getWriter();
		System.out.println(usersJsonArray.toString());
		out.print(usersJsonArray.toString());


	}

}