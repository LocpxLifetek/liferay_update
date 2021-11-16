package tintuc.portlet;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import tintuc.constants.TintucPortletKeys;

/**
 * @author java05
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Tintuc", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + TintucPortletKeys.TINTUC,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class TintucPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(108693);
			DynamicQuery dynamicQueryCategory = DynamicQueryFactoryUtil.forClass(AssetCategory.class);
			long categoryId = assetCategory.getCategoryId();
			Property parentCategoryProperty = PropertyFactoryUtil.forName("parentCategoryId");
			dynamicQueryCategory.add(parentCategoryProperty.eq(categoryId));
			dynamicQueryCategory.addOrder(OrderFactoryUtil.desc("modifiedDate"));
			List<AssetCategory> listAssetCategory = AssetCategoryLocalServiceUtil.dynamicQuery(dynamicQueryCategory);
			List<AssetCategory> listAssetCategory3 = new ArrayList<>();
			for (AssetCategory assetCategory2 : listAssetCategory) {
				List<AssetCategory> listAssetCategory2 = AssetCategoryLocalServiceUtil
						.getChildCategories(assetCategory2.getCategoryId());
				if (listAssetCategory2.size() > 0) {
					for (AssetCategory assetCategory3 : listAssetCategory2) {
						listAssetCategory3.add(assetCategory3);
					}
				}
			}
			renderRequest.setAttribute("listAssetCategory", listAssetCategory);
			renderRequest.setAttribute("listAssetCategory3", listAssetCategory3);
			super.doView(renderRequest, renderResponse);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}