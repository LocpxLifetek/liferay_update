package portlet_thutuong_right.portlet;

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

import portlet_thutuong_right.constants.Portlet_thutuong_rightPortletKeys;

/**
 * @author java03
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Portlet_thutuong_right",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + Portlet_thutuong_rightPortletKeys.PORTLET_THUTUONG_RIGHT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class Portlet_thutuong_rightPortlet extends MVCPortlet{
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			AssetCategory assetCategory= AssetCategoryLocalServiceUtil.getAssetCategory(72065);
			List<BlogsEntry> listBlog= new ArrayList<BlogsEntry>();
			long assetCategoryId=assetCategory.getCategoryId();
			DynamicQuery queryAssetEntryAssetCategory=DynamicQueryFactoryUtil.forClass(AssetEntryAssetCategoryRel.class);
			Property assetEntryProperty=PropertyFactoryUtil.forName("assetCategoryId");
			queryAssetEntryAssetCategory.add(assetEntryProperty.eq(assetCategoryId));
			queryAssetEntryAssetCategory.addOrder(OrderFactoryUtil.desc("assetEntryId"));
			queryAssetEntryAssetCategory.setLimit(0, 2);
			List<AssetEntryAssetCategoryRel> listAssetEntryAssetCategoryRels=AssetEntryAssetCategoryRelLocalServiceUtil.dynamicQuery(queryAssetEntryAssetCategory);
			for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel : listAssetEntryAssetCategoryRels) {
				AssetEntry assetEntry= AssetEntryLocalServiceUtil.getEntry(assetEntryAssetCategoryRel.getAssetEntryId());
				BlogsEntry blogEntry= BlogsEntryLocalServiceUtil.getEntry(assetEntry.getClassPK());
				listBlog.add(blogEntry);
			}
			
			renderRequest.setAttribute("listBlog", listBlog);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}

	