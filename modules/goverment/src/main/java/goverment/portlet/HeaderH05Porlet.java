package goverment.portlet;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import goverment.constants.GovermentPortletKeys;
import goverment.dto.SearchBlogsDto;
import goverment.sql.SearchBlogsSql;
import goverment.url.UrlCurrentPorlet;
@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Header_H05",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/HeaderH05.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.HEADERGOVERMENT,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class HeaderH05Porlet extends MVCPortlet {
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		String cmd = ParamUtil.getString(resourceRequest, Constants.CMD);
		if (cmd.equals("get_blogs")) {
			getBlogs(resourceRequest, resourceResponse);
		}
		super.serveResource(resourceRequest, resourceResponse);
	}

	private void getBlogs(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException {
		// TODO Auto-generated method stub
		try {
			JSONArray usersJSONArray = JSONFactoryUtil.createJSONArray();
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String titleBlogs = ParamUtil.getString(resourceRequest, "titleBlogs");

			JSONObject blogJSON = null;
			List<SearchBlogsDto> listSearchBlogsDto = new SearchBlogsSql().searchBlogsSql(titleBlogs,
					themeDisplay.getScopeGroupId(),1,1);
			
			for (SearchBlogsDto searchBlogsDto : listSearchBlogsDto) {
				blogJSON = JSONFactoryUtil.createJSONObject();
				blogJSON.put("title", searchBlogsDto.getTitle());
				blogJSON.put("uuid", searchBlogsDto.getUuid());
				blogJSON.put("src", searchBlogsDto.getSrc());
				blogJSON.put("main", searchBlogsDto.getMain());
				usersJSONArray.put(blogJSON);
			}
			PrintWriter out = resourceResponse.getWriter();
			out.println(usersJSONArray.toString());
		} catch (Exception e) {
			// TODO: handle exception
			
		}
	}
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);
			
			String url = new UrlCurrentPorlet().urlCurrentPorlet(themeDisplay.getURLCurrent(),
					themeDisplay.getLayoutFriendlyURL(layout),themeDisplay.getCDNBaseURL());
			renderRequest.setAttribute("url", url);
			String urlPrivate="";
			if(url.contains("web")) {
				
				urlPrivate=url.replace("web", "group");
			}else {
				urlPrivate=themeDisplay.getCDNBaseURL()+"group";
			}
			renderRequest.setAttribute("urlPrivate", urlPrivate);
			String siginIn=themeDisplay.getURLSignIn()+"&"+"redirect="+themeDisplay.getURLCurrent();
			String siginOut=themeDisplay.getURLSignOut()+"?"+"referer="+themeDisplay.getURLCurrent();
			if(themeDisplay.getRealUserId() == themeDisplay.getDefaultUserId()) {
				renderRequest.setAttribute("login", siginIn);
			}else {
				renderRequest.setAttribute("logout", siginOut);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}
