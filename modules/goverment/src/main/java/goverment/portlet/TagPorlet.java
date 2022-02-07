package goverment.portlet;

import com.liferay.asset.kernel.model.AssetEntries_AssetTagsTable;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
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
import goverment.sql.TagsBlogSql;
import goverment.url.UrlCurrentPorlet;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=Tag Blogs",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/tag.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.TAGGOVERMENT,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class TagPorlet extends MVCPortlet{

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String uuid =  PortalUtil.getOriginalServletRequest(request).getParameter("uuid");
			String pageDetail = PortalUtil.getOriginalServletRequest(request).getParameter("page");
			String url = new UrlCurrentPorlet().urlCurrentPorlet(themeDisplay.getURLCurrent(),
					themeDisplay.getLayoutFriendlyURL(layout));
			renderRequest.setAttribute("url", url);
			AssetTag assetTag=AssetTagLocalServiceUtil.getAssetTagByUuidAndGroupId(uuid, themeDisplay.getScopeGroupId());
			Integer resultDlFile=new TagsBlogSql().countViewTags(themeDisplay.getScopeGroupId(), assetTag.getTagId());
			Integer page=Integer.parseInt(pageDetail == null ? "1" : pageDetail);
			Integer size=8;
			int result = (int) Math.ceil((float) resultDlFile/ size);
			List<BlogsEntryDto> listBlogsEntryDtos=new TagsBlogSql().findAllBlogsByTagsId(assetTag.getTagId(), themeDisplay.getScopeGroupId(), page, size);
			renderRequest.setAttribute("listBlogsEntryDtos", listBlogsEntryDtos);
			renderRequest.setAttribute("assetTag", assetTag);
			renderRequest.setAttribute("currentPage", page);
			renderRequest.setAttribute("totalPage", result);
			renderRequest.setAttribute("uuid", uuid);
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}	
	
}
