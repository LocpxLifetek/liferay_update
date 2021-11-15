package countViewBlogs.portlet;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import countViewBlogs.constants.CountViewBlogsPortletKeys;

/**
 * @author java05
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CountViewBlogs",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CountViewBlogsPortletKeys.COUNTVIEWBLOGS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CountViewBlogsPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			int i=0;
			List<AssetEntry> listAssetEntry=AssetEntryLocalServiceUtil.getTopViewedEntries("com.liferay.blogs.model.BlogsEntry", false, 0, 6);
			List<BlogsEntry> listBlogsEntry=new ArrayList<>();
			List<BlogsEntry> manyBlog=new ArrayList<>();
			for (AssetEntry assetEntry : listAssetEntry) {
				
				BlogsEntry blogsEntry=BlogsEntryLocalServiceUtil.getBlogsEntry(assetEntry.getClassPK());

				listBlogsEntry.add(blogsEntry);
			}
			for (BlogsEntry blogs : listBlogsEntry) {
				i++;
				if(i==1) {
					if (blogs.getSmallImageFileEntryId() > 0) {
						DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
								.getFileEntry(blogs.getSmallImageFileEntryId());
						renderRequest.setAttribute("blogOne", blogs);
						renderRequest.setAttribute("smallImage", dlFileEntry);
					}
				}else {
					manyBlog.add(blogs);
				}
					
			}
			renderRequest.setAttribute("listBlogsEntry", manyBlog);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		super.doView(renderRequest, renderResponse);
	}
	
}