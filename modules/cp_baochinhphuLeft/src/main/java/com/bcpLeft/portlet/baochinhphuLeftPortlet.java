package com.bcpLeft.portlet;

import com.bcpLeft.constants.baochinhphuLeftPortletKeys;
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
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

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
		"javax.portlet.display-name=baochinhphuLeft",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + baochinhphuLeftPortletKeys.BAOCHINHPHULEFT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class baochinhphuLeftPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
// Hiển thị các mục bên phải
			// get banner
			DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(184105);
			renderRequest.setAttribute("srcBanner", "/documents/" + dlFileEntry.getGroupId() + "/"
					+ dlFileEntry.getFolderId() + "/" + dlFileEntry.getTitle() + "/" + dlFileEntry.getUuid());
			// get Name cua Category: Các bài phát biểu của Thủ tướng
			AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(156433);
			renderRequest.setAttribute("NameCategory", assetCategory.getName());
			// lấy các bài viết liên quan tới Category, top 4
			List<AssetEntryAssetCategoryRel> assetCategoryRel = AssetEntryAssetCategoryRelLocalServiceUtil
					.getAssetEntryAssetCategoryRelsByAssetCategoryId(156433).stream().sorted((s1, s2) -> (int)s1.getAssetEntryId() - (int)s2.getAssetEntryId()).limit(4)
					.collect(Collectors.toList());
			long i = 0;
			for (AssetEntryAssetCategoryRel a : assetCategoryRel) {
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(a.getAssetEntryId());
				if (assetEntry.getMimeType().equals("text/html")) {
					i++;
					BlogsEntry blogsEntry = BlogsEntryLocalServiceUtil.getBlogsEntry(assetEntry.getClassPK());
					if (blogsEntry.getTitle().length() > 70) {
						renderRequest.setAttribute("titleBlog" + i,
								blogsEntry.getTitle().substring(0, 70).concat("..."));
					} else {
						renderRequest.setAttribute("titleBlog" + i, blogsEntry.getTitle());
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}