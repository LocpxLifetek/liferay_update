package informationNews.portlet;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import informationNews.constants.InformationNewsPortletKeys;

/**
 * @author java05
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=InformationNews",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + InformationNewsPortletKeys.INFORMATIONNEWS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class InformationNewsPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String uuid =  PortalUtil.getOriginalServletRequest(request).getParameter("id");
			DynamicQuery queryBlogs=DynamicQueryFactoryUtil.forClass(BlogsEntry.class);
			String uuidBlogs=uuid;
			Property blogsProperty=PropertyFactoryUtil.forName("uuid");
			queryBlogs.add(blogsProperty.eq(uuidBlogs));
			List<BlogsEntry> listBlogsEntry=BlogsEntryLocalServiceUtil.dynamicQuery(queryBlogs);
			for (BlogsEntry blogsEntry : listBlogsEntry) {
				String timestamp=new SimpleDateFormat("HH:mm:ss, MM/dd/yyyy").format(blogsEntry.getModifiedDate());
				renderRequest.setAttribute("time", timestamp);

				AssetEntry assetEntry=AssetEntryLocalServiceUtil.incrementViewCounter(37704, blogsEntry.getUserId(), "com.liferay.blogs.model.BlogsEntry", blogsEntry.getEntryId());
				String currentUrl=PortalUtil.getCurrentURL((renderRequest));
				renderRequest.setAttribute("currentUrl", currentUrl);
				renderRequest.setAttribute("blogsEntry", blogsEntry);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
	
}