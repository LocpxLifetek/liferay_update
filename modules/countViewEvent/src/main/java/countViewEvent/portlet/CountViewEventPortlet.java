package countViewEvent.portlet;

import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import countViewEvent.constants.CountViewEventPortletKeys;

/**
 * @author java05
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CountViewEvent",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CountViewEventPortletKeys.COUNTVIEWEVENT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CountViewEventPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			List<BlogsEntry> manyBlog=new ArrayList<>();
			Calendar calendar = Calendar.getInstance();
			Date currentDate = calendar.getTime();
			calendar.add(Calendar.DATE, -5);
			Date fromDate = calendar.getTime();
			List<BlogsEntry> listViewBlogsEntry=new ArrayList<>();
			List<AssetEntry> listAssetEntry = AssetEntryLocalServiceUtil.getTopViewedEntries(BlogsEntry.class.getName(),
					false, 0, 100);
			int i=0;
			for (AssetEntry assetEntry : listAssetEntry) {
				
				List<AssetEntryAssetCategoryRel> listAssetEntryAssetCategoryRel=AssetEntryAssetCategoryRelLocalServiceUtil.getAssetEntryAssetCategoryRelsByAssetCategoryId(108693);
				for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel : listAssetEntryAssetCategoryRel) {
					if(assetEntry.getEntryId()==assetEntryAssetCategoryRel.getAssetEntryId()) {
						Long entryId=assetEntry.getClassPK();
						int status =0;
						DynamicQuery dynamicQueryBlog = DynamicQueryFactoryUtil.forClass(BlogsEntry.class);
						Property statusDateProperty = PropertyFactoryUtil.forName("modifiedDate");
						Property statusEntryIdProperty = PropertyFactoryUtil.forName("entryId");
						Property statusBlog=PropertyFactoryUtil.forName("status");
						dynamicQueryBlog.add(statusEntryIdProperty.eq(entryId));
						dynamicQueryBlog.add(statusBlog.eq(status));
						dynamicQueryBlog.add(statusDateProperty.between(fromDate, currentDate));
						dynamicQueryBlog.setLimit(0, 8);
						List<BlogsEntry> listBlogEntry = BlogsEntryLocalServiceUtil.dynamicQuery(dynamicQueryBlog);
						for (BlogsEntry blogsEntry : listBlogEntry) {
							listViewBlogsEntry.add(blogsEntry);
						}
					}
				}
			}
			for (BlogsEntry viewBlog : listViewBlogsEntry) {
				
				System.out.println("viewBlog: "+viewBlog.getEntryId());
				i++;
				if(i==1) {
					if (viewBlog.getSmallImageFileEntryId() > 0) {
						DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
								.getFileEntry(viewBlog.getSmallImageFileEntryId());
						renderRequest.setAttribute("blogOne", viewBlog);
						renderRequest.setAttribute("smallImage", dlFileEntry);
					}
				}else if(i>1 && i<8){
					manyBlog.add(viewBlog);
				}else {
					break;
				}
			}
			renderRequest.setAttribute("listBlogsEntry", manyBlog);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
	
}