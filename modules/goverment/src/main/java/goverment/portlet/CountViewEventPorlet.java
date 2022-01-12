package goverment.portlet;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import goverment.constants.GovermentPortletKeys;
import goverment.dto.CountViewDto;
import goverment.sql.CommonSqlBlogEntry;
import goverment.url.UrlCurrentPorlet;

@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CountViewEvent", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/countViewEvent.jsp",
		"javax.portlet.name=" + GovermentPortletKeys.COUNTVIEWEVENT, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class CountViewEventPorlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);
			String url = new UrlCurrentPorlet().urlCurrentPorlet(themeDisplay.getURLCurrent(),
					themeDisplay.getLayoutFriendlyURL(layout));
			renderRequest.setAttribute("url", url);

			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date currentDate = calendar.getTime();
			String current = format.format(currentDate);
			calendar.add(Calendar.DATE, -30);
			Date fromDate = calendar.getTime();
			String from = format.format(fromDate);
			List<CountViewDto> listCountViewDto = new CommonSqlBlogEntry().countViewEvent(current, from,
					themeDisplay.getScopeGroupId());
			CountViewDto countViewDto = listCountViewDto.get(0);
			renderRequest.setAttribute("countViewDto", countViewDto);
			List<CountViewDto> listCountViewDtos=new ArrayList<>();
			if(listCountViewDto.size()>0) {
				
				for (int i = 1; i < listCountViewDto.size(); i++) {
					CountViewDto countView=listCountViewDto.get(i);
					listCountViewDtos.add(countView);
				}
			}
			renderRequest.setAttribute("listCountViewDtos", listCountViewDtos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}
