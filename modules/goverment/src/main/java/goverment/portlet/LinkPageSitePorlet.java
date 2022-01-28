package goverment.portlet;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import goverment.constants.GovermentPortletKeys;
import goverment.dto.GroupSiteDto;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Link Page Site",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/linkPageSite.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.LINKPAGESSITE,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class LinkPageSitePorlet extends MVCPortlet{
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);
			Group group =GroupLocalServiceUtil.getGroup(themeDisplay.getScopeGroupId());
			List<GroupSiteDto> listGroupSiteDtos=new ArrayList<>();
			List<Group> listGroup=GroupLocalServiceUtil.getGroups(group.getCompanyId(), group.getGroupId(), true);
			for (Group group2 : listGroup) {
				String src=themeDisplay.getPathFriendlyURLPublic()+group2.getFriendlyURL();
				GroupSiteDto groupSiteDto=new GroupSiteDto();
				groupSiteDto.setName(group2.getGroupKey());
				groupSiteDto.setSrc(src);
				listGroupSiteDtos.add(groupSiteDto);
			}
			renderRequest.setAttribute("listGroupSiteDtos", listGroupSiteDtos);
			System.out.println(listGroupSiteDtos.toString());
			System.out.println("userId: "+themeDisplay.getUser());
			System.out.println("user: "+themeDisplay.getRealUserId());
			System.out.println("userId: "+themeDisplay.getDefaultUserId());
			System.out.println("abc: "+themeDisplay.getURLCurrent());
			System.out.println("abcd: "+themeDisplay.getCDNBaseURL());
			System.out.println("abce: "+themeDisplay.getPathFriendlyURLPublic());
			System.out.println("abcf: "+themeDisplay.getPathFriendlyURLPrivateGroup());
			System.out.println("abcg: "+themeDisplay.getPortalURL());
			System.out.println("abch: "+themeDisplay.getURLHome());
			System.out.println("abci: "+themeDisplay.getURLPortal());
			System.out.println("abci: "+themeDisplay.getURLSignIn());
			System.out.println("abci: "+themeDisplay.getURLSignOut());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}

}
