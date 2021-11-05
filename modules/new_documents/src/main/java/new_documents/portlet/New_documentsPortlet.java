package new_documents.portlet;

import new_documents.constants.New_documentsPortletKeys;

import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author java03
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=New_documents",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + New_documentsPortletKeys.NEW_DOCUMENTS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class New_documentsPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		
		try {
			
			AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(134236);
			DynamicQuery queryAssetentryAssetCategory = DynamicQueryFactoryUtil
					.forClass(AssetEntryAssetCategoryRel.class);
			Long categoryId = assetCategory.getCategoryId();
			Property categoryProperty = PropertyFactoryUtil.forName("assetCategoryId");
			queryAssetentryAssetCategory.add(categoryProperty.eq(categoryId));
			queryAssetentryAssetCategory.addOrder(OrderFactoryUtil.desc("assetEntryAssetCategoryRelId"));
			List<AssetEntryAssetCategoryRel> listAssetEntryAssetCategoryRel = AssetEntryAssetCategoryRelLocalServiceUtil
					.dynamicQuery(queryAssetentryAssetCategory);
			List<BlogsEntry> listBlogsEntries=new ArrayList<>();
			List<DLFileEntry> listDlFileEntry=new ArrayList<>();
			int i = 0;
			for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel2 : listAssetEntryAssetCategoryRel) {
				AssetEntry assetEntry = AssetEntryLocalServiceUtil
						.getEntry(assetEntryAssetCategoryRel2.getAssetEntryId());
				if (assetEntry.getClassNameId() == 31201) {
					i++;
					if (i <= 3) {

						BlogsEntry blogsEntry = BlogsEntryLocalServiceUtil.getEntry(assetEntry.getClassPK());
							String timestamp=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(blogsEntry.getModifiedDate());
							renderRequest.setAttribute("time", timestamp);
							if (blogsEntry.getSmallImageFileEntryId() > 0) {
								DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
										.getFileEntry(blogsEntry.getSmallImageFileEntryId());
								listDlFileEntry.add(dlFileEntry);		
							}
						listBlogsEntries.add(blogsEntry);
						
					} else {
						break;
					}
				}
			}
			renderRequest.setAttribute("smallImage", listDlFileEntry);
			renderRequest.setAttribute("listBlogs", listBlogsEntries);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}