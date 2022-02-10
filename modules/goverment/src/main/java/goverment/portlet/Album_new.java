package goverment.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import goverment.constants.GovermentPortletKeys;
import goverment.dto.CategoryDto;
import goverment.dto.DlFileEntryDto;
import goverment.sql.AssetCategorySql;
import goverment.sql.PhotoSql;
@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Album_new",
			"com.liferay.portlet.header-portlet-css=/META-INF/resources/css/main.css",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/META-INF/resources/Album_new.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.ALBUM_NEW,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class Album_new extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);

			String uuid =  PortalUtil.getOriginalServletRequest(request).getParameter("uuid");
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			CategoryDto category= new AssetCategorySql().findCategoryByUuid(uuid,themeDisplay.getScopeGroupId());
			CategoryDto categoryDto= new PhotoSql().findCategoryByParentId(category.getId());
			
			List<DlFileEntryDto> dLfileEntryDtos=new PhotoSql().findAllDLfileEntryDtos(categoryDto.getUuid(), 1, 10);

			renderRequest.setAttribute("categoryDto", categoryDto);
			renderRequest.setAttribute("dLfileEntryDtos", dLfileEntryDtos);
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		super.doView(renderRequest, renderResponse);
	}
}
