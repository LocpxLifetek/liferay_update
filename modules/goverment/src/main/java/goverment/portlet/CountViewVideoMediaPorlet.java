package goverment.portlet;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
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
import goverment.dto.CountViewVideoDto;
import goverment.sql.CommonSqlBlogEntry;
import goverment.url.UrlCurrentPorlet;

@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CountViewVideoMedia", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/countViewVideoMedia.jsp",
		"javax.portlet.name=" + GovermentPortletKeys.COUNTVIEWVIDEOMEDIA,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class CountViewVideoMediaPorlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);
			String url = new UrlCurrentPorlet().urlCurrentPorlet(themeDisplay.getURLCurrent(),
					themeDisplay.getLayoutFriendlyURL(layout));
			renderRequest.setAttribute("url", url);
			List<CountViewVideoDto> listCountViewVideoDto = new CommonSqlBlogEntry()
					.getViewCountVideo(themeDisplay.getScopeGroupId());
			CountViewVideoDto countViewVideoDto = listCountViewVideoDto.get(0);
			renderRequest.setAttribute("countViewVideoDto", countViewVideoDto);
			List<CountViewVideoDto> listCountViewVideoDtos = new ArrayList<>();
			if (listCountViewVideoDto.size() > 0) {

				for (int i = 1; i < listCountViewVideoDto.size(); i++) {
					CountViewVideoDto countView = listCountViewVideoDto.get(i);
					listCountViewVideoDtos.add(countView);
				}
			}
			renderRequest.setAttribute("listCountViewDtos", listCountViewVideoDtos);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}
