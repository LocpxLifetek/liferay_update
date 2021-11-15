package featuredNews.portlet;

import com.liferay.account.exception.NoSuchEntryException;
import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.publisher.constants.AssetPublisherPortletKeys;
import com.liferay.asset.publisher.util.AssetPublisherHelper;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleLocalization;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.persistence.JournalArticleLocalizationUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.uuid.PortalUUID;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import featuredNews.constants.FeaturedNewsPortletKeys;

/**
 * @author java05
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=FeaturedNews", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + FeaturedNewsPortletKeys.FEATUREDNEWS, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
@Transactional(isolation = Isolation.PORTAL, rollbackFor = { PortalException.class, SystemException.class })
public class FeaturedNewsPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		try {

			AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(87918);
			DynamicQuery queryAssetentryAssetCategory = DynamicQueryFactoryUtil
					.forClass(AssetEntryAssetCategoryRel.class);
			Long categoryId = assetCategory.getCategoryId();
			Property categoryProperty = PropertyFactoryUtil.forName("assetCategoryId");
			queryAssetentryAssetCategory.add(categoryProperty.eq(categoryId));
			queryAssetentryAssetCategory.addOrder(OrderFactoryUtil.desc("assetEntryAssetCategoryRelId"));
			queryAssetentryAssetCategory.setLimit(0, 10);
			List<AssetEntryAssetCategoryRel> listAssetEntryAssetCategoryRel = AssetEntryAssetCategoryRelLocalServiceUtil
					.dynamicQuery(queryAssetentryAssetCategory);
			List<BlogsEntry> listBlogsEntries = new ArrayList<>();

			int i = 0;
			for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel2 : listAssetEntryAssetCategoryRel) {
				AssetEntry assetEntry = AssetEntryLocalServiceUtil
						.getEntry(assetEntryAssetCategoryRel2.getAssetEntryId());
				if (assetEntry.getClassNameId() == 31201) {
					BlogsEntry blogsEntry = BlogsEntryLocalServiceUtil.getEntry(assetEntry.getClassPK());
					if (blogsEntry.getStatus() == 0) {
						i++;
						if (i == 1) {

							String timestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
									.format(blogsEntry.getModifiedDate());
							renderRequest.setAttribute("time", timestamp);
							if (blogsEntry.getSmallImageFileEntryId() > 0) {
								DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil
										.getFileEntry(blogsEntry.getSmallImageFileEntryId());
								renderRequest.setAttribute("si", dlFileEntry);
							}
							renderRequest.setAttribute("blogs", blogsEntry);
						} else if (i > 1 && i <= 7) {
							listBlogsEntries.add(blogsEntry);
						} else {
							break;
						}
					}else {
						continue;
					}

				}
			}
			renderRequest.setAttribute("listBlogs", listBlogsEntries);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}

}