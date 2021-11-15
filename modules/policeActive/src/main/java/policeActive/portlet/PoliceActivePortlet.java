package policeActive.portlet;

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
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import policeActive.constants.PoliceActivePortletKeys;

/**
 * @author java05
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=PoliceActive",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + PoliceActivePortletKeys.POLICEACTIVE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PoliceActivePortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			List<BlogsEntry> listBlogsEntries=new ArrayList<>();
			List<DLFileEntry> listDLFileEntries=new ArrayList<>();
			AssetCategory assetCategory=AssetCategoryLocalServiceUtil.getCategory(113410);
			renderRequest.setAttribute("assetCategory", assetCategory);
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String uuid =  PortalUtil.getOriginalServletRequest(request).getParameter("id");
			String categoryUuid=uuid;
			DynamicQuery assetCategoryDynamic=DynamicQueryFactoryUtil.forClass(AssetCategory.class);
			Property propertyCategory=PropertyFactoryUtil.forName("uuid");
			assetCategoryDynamic.add(propertyCategory.eq(categoryUuid));
			List<AssetCategory> listAssetCategory=AssetCategoryLocalServiceUtil.dynamicQuery(assetCategoryDynamic);
			for (AssetCategory assetCategory2 : listAssetCategory) {
				if(assetCategory2.getParentCategoryId() == assetCategory.getCategoryId()) {
					renderRequest.setAttribute("assetCategory2", assetCategory2);
					DynamicQuery assetEntryAssetCategoryRelDynamic=DynamicQueryFactoryUtil.forClass(AssetEntryAssetCategoryRel.class);
					Property propertyCategoryId=PropertyFactoryUtil.forName("assetCategoryId");
					assetEntryAssetCategoryRelDynamic.add(propertyCategoryId.eq(assetCategory2.getCategoryId()));
					assetEntryAssetCategoryRelDynamic.addOrder(OrderFactoryUtil.desc("assetEntryAssetCategoryRelId"));
					List<AssetEntryAssetCategoryRel> listAssetEntryAssetCategoryRel=AssetEntryAssetCategoryRelLocalServiceUtil.dynamicQuery(assetEntryAssetCategoryRelDynamic);
					for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel : listAssetEntryAssetCategoryRel) {
						AssetEntry assetEntry=AssetEntryLocalServiceUtil.getEntry(assetEntryAssetCategoryRel.getAssetEntryId());
						if(assetEntry.getClassNameId()==31201) {
							BlogsEntry blogsEntry=BlogsEntryLocalServiceUtil.getEntry(assetEntry.getClassPK());
							if(blogsEntry.getStatus()==0) {
								String timestamp=new SimpleDateFormat("MM/dd/yyyy").format(blogsEntry.getModifiedDate());
								renderRequest.setAttribute("time", timestamp);
								if (blogsEntry.getSmallImageFileEntryId() > 0) {
									DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
											.getFileEntry(blogsEntry.getSmallImageFileEntryId());
									listDLFileEntries.add(dlFileEntry);	
								}
								listBlogsEntries.add(blogsEntry);
							}
						}
						
					}
				}
			}
			renderRequest.setAttribute("listBlogsEntries", listBlogsEntries);
			renderRequest.setAttribute("listDLFileEntries", listDLFileEntries);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
	
}