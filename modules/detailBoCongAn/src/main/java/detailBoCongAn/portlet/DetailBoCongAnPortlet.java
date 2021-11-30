package detailBoCongAn.portlet;

import detailBoCongAn.constants.DetailBoCongAnPortletKeys;

import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.vulcan.pagination.Page;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author java05
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=DetailBoCongAn",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + DetailBoCongAnPortletKeys.DETAILBOCONGAN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class DetailBoCongAnPortlet extends MVCPortlet {

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
				System.out.println("assetEntry: "+assetEntry.getViewCount());
				List<AssetEntryAssetCategoryRel> listAssetEntryAssetCategoryRel =AssetEntryAssetCategoryRelLocalServiceUtil.getAssetEntryAssetCategoryRelsByAssetEntryId(assetEntry.getEntryId());
				for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel : listAssetEntryAssetCategoryRel) {
					if(assetEntryAssetCategoryRel.getAssetCategoryId() == 108693 || assetEntryAssetCategoryRel.getAssetCategoryId()==114318 || assetEntryAssetCategoryRel.getAssetCategoryId()==114321) {
						continue;
					}else if(assetEntryAssetCategoryRel.getAssetCategoryId() == 113410){
						DynamicQuery dynamicAssetCategory=DynamicQueryFactoryUtil.forClass(AssetCategory.class);
						long categoryId=assetEntryAssetCategoryRel.getAssetCategoryId();
						Property parentCategoryProperty = PropertyFactoryUtil.forName("parentCategoryId");
						dynamicAssetCategory.add(parentCategoryProperty.eq(categoryId));
						dynamicAssetCategory.addOrder(OrderFactoryUtil.desc("modifiedDate"));
						List<AssetCategory> listAssetCategory = AssetCategoryLocalServiceUtil.dynamicQuery(dynamicAssetCategory);
						AssetCategory assetCategory2=AssetCategoryLocalServiceUtil.getAssetCategory(113410);
						renderRequest.setAttribute("assetCategory2", assetCategory2);
						if(listAssetCategory.size()>0) {							
							for (AssetCategory assetCategory : listAssetCategory) {
								DynamicQuery dynamicQueryAssetCategoryAssetEntryRel=DynamicQueryFactoryUtil.forClass(AssetEntryAssetCategoryRel.class);
								long assetCategoryId=assetCategory.getCategoryId();
								long assetEntryId=assetEntryAssetCategoryRel.getAssetEntryId();
								Property propertyAssetCategoyId = PropertyFactoryUtil.forName("assetCategoryId");
								Property propertyAssetEntryId=PropertyFactoryUtil.forName("assetEntryId");
								dynamicQueryAssetCategoryAssetEntryRel.add(propertyAssetCategoyId.eq(assetCategoryId));
								dynamicQueryAssetCategoryAssetEntryRel.add(propertyAssetEntryId.eq(assetEntryId));
								List<AssetEntryAssetCategoryRel> listAssetEntryAssetCategoryRels2=AssetEntryAssetCategoryRelLocalServiceUtil.dynamicQuery(dynamicQueryAssetCategoryAssetEntryRel);
								if(listAssetEntryAssetCategoryRels2.size()>0) {
									for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel2 : listAssetEntryAssetCategoryRels2) {
										AssetCategory assetCategory3=AssetCategoryLocalServiceUtil.getAssetCategory(assetEntryAssetCategoryRel2.getAssetCategoryId());
										renderRequest.setAttribute("assetCategory3", assetCategory3);
									}
								}
							}
						}
					}else {
						AssetCategory assetCategory=AssetCategoryLocalServiceUtil.getAssetCategory(assetEntryAssetCategoryRel.getAssetCategoryId());
						renderRequest.setAttribute("assetCategory", assetCategory);
					}
				}
				
				renderRequest.setAttribute("blogsEntry", blogsEntry);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		super.doView(renderRequest, renderResponse);
	}
	
}