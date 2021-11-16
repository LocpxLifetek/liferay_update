package hotNews.portlet;

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
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import hotNews.constants.HotNewsPortletKeys;

/**
 * @author java05
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=HotNews", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + HotNewsPortletKeys.HOTNEWS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class HotNewsPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		try {

			AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(52271);

			List<BlogsEntry> blogsList = new ArrayList<>();
			List<DLFileEntry> dlFileEntryList = new ArrayList<>();
			DynamicQuery queryAssetentryAssetCategory = DynamicQueryFactoryUtil
					.forClass(AssetEntryAssetCategoryRel.class);
			Long categoryId = assetCategory.getCategoryId();
			Property categoryProperty = PropertyFactoryUtil.forName("assetCategoryId");
			queryAssetentryAssetCategory.add(categoryProperty.eq(categoryId));
			queryAssetentryAssetCategory.addOrder(OrderFactoryUtil.desc("assetEntryAssetCategoryRelId"));
			queryAssetentryAssetCategory.setLimit(0, 10);
			List<AssetEntryAssetCategoryRel> listAssetEntryAssetCategoryRel = AssetEntryAssetCategoryRelLocalServiceUtil
					.dynamicQuery(queryAssetentryAssetCategory);
			int i = 0;
			for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel2 : listAssetEntryAssetCategoryRel) {
				AssetEntry assetEntry = AssetEntryLocalServiceUtil
						.getEntry(assetEntryAssetCategoryRel2.getAssetEntryId());
				if (assetEntry.getClassNameId() == 31201) {
					BlogsEntry blogsEntry = BlogsEntryLocalServiceUtil.getEntry(assetEntry.getClassPK());
					if (blogsEntry.getStatus() == 0) {

						i++;
						if (i <= 4) {

							if (blogsEntry.getSmallImageFileEntryId() > 0) {
								DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
										.getFileEntry(blogsEntry.getSmallImageFileEntryId());
								dlFileEntryList.add(dlFileEntry);
							}
							blogsList.add(blogsEntry);
						} else {
							break;
						}
					}
				}
			}

			renderRequest.setAttribute("smallImage", dlFileEntryList);
			renderRequest.setAttribute("blogsList", blogsList);

		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		super.doView(renderRequest, renderResponse);

	}

}