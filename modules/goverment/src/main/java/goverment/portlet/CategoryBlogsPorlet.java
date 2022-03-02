package goverment.portlet;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.model.Layout;
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
import goverment.dto.BlogsEntryDto;
import goverment.dto.CategoryDto;
import goverment.sql.AssetCategorySql;
import goverment.sql.BlogEntrySql;
import goverment.url.UrlCurrentPorlet;
@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Category Blogs",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/CategoryBlogs.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.CATEGORYBLOGS,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class CategoryBlogsPorlet extends MVCPortlet{
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String uuid =  PortalUtil.getOriginalServletRequest(request).getParameter("uuid");
			String pageDetail = PortalUtil.getOriginalServletRequest(request).getParameter("page");
			String url = new UrlCurrentPorlet().urlCurrentPorlet(PortalUtil.getLayoutFriendlyURL(layout, themeDisplay),
					themeDisplay.getLayoutFriendlyURL(layout),themeDisplay.getCDNBaseURL());
			renderRequest.setAttribute("url", url);
			CategoryDto parentCategory= new AssetCategorySql().findCategoryByUuid(uuid, themeDisplay.getScopeGroupId());
			Integer resultDlFile=new BlogEntrySql().countViewBlogsByCategory(uuid, themeDisplay.getScopeGroupId());
			Integer page=Integer.parseInt(pageDetail == null ? "1" : pageDetail);
			Integer size=8;
			int result = (int) Math.ceil((float) resultDlFile/ size);
			List<BlogsEntryDto> listBlogs= new BlogEntrySql().findAllBlogsByIdCategory(uuid,parentCategory.getGroupId(),page,size);
			AssetCategory categoryDto=AssetCategoryLocalServiceUtil.getAssetCategoryByUuidAndGroupId(uuid, themeDisplay.getScopeGroupId());
			renderRequest.setAttribute("listBlogs", listBlogs);
			renderRequest.setAttribute("categoryDto", categoryDto);
			renderRequest.setAttribute("currentPage", page);
			renderRequest.setAttribute("totalPage", result);
			renderRequest.setAttribute("uuid", uuid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}
