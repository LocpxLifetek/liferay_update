package com.cpNew.portlet;

import com.cpNew.constants.cpnewPortletKeys;
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
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.util.ArrayList;
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
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=cpnew", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + cpnewPortletKeys.CPNEW,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class cpnewPortlet extends MVCPortlet {
	
	private final String LINK_CP_NEW = "http://portal.lifetek.vn/web/lifetek/media.chinhphu?id=";
	@SuppressWarnings("deprecation")
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			AssetCategory assetCategory1 = AssetCategoryLocalServiceUtil.getAssetCategory(72134);
			renderRequest.setAttribute("assetCategory1", assetCategory1);
			AssetCategory assetCategory2 = AssetCategoryLocalServiceUtil.getAssetCategory(71438);
			renderRequest.setAttribute("assetCategory2", assetCategory2);
			// img Banr tin chinh phu
			renderRequest.setAttribute("srcImgBtcptq",
					"http://daphuongtien.chinhphu.vn/file?path=/content/data/random/102021/CPTQ%20ava_9c38564d_bae176d1.jpg");

			BlogsEntry blogTest = BlogsEntryLocalServiceUtil.getBlogsEntry(140411);
			renderRequest.setAttribute("testImg", blogTest.getSmallImageFileEntryId());
			// img đầu dòng các mục
			String srcImgdaudong = "http://chinhphu.vn/templates/govportal/chinhphu/images/icon3.jpg";
			renderRequest.setAttribute("srcImgdaudong", srcImgdaudong);

			// danh sach top 5 muc Thông tin đa phương tiện
			// lay danh sach blogs theo categoryId
			List<AssetEntryAssetCategoryRel> assetCategoryRels = AssetEntryAssetCategoryRelLocalServiceUtil
					.getAssetEntryAssetCategoryRelsByAssetCategoryId(72134);
			List<BlogsEntry> blogsEntries1 = new ArrayList<>();
			for (AssetEntryAssetCategoryRel a : assetCategoryRels) {
				// lay assetEntryId trong AssetEntryAssetCategoryRel
				long entryId = a.getAssetEntryId();
				// lay assetEntry theo id vua lay
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(entryId);
				// lay classPk cua AssetEntry
				long blogsentryId = assetEntry.getClassPK();
				// lay BlogsEntryId theo classPk
				if (blogsentryId >= 1000) {
					BlogsEntry blogsEntry = BlogsEntryLocalServiceUtil.getBlogsEntry(blogsentryId);
					blogsEntries1.add(blogsEntry);
				}
			}
			// BlogsEntry1-5 theo blogsEntries giới hạn 5 phần tử và mới nhất
			// Thông tin đa phương tiện(ttdpt)
			List<BlogsEntry> blogsEntriesnew1 = blogsEntries1.stream().sorted().limit(5).collect(Collectors.toList());
			for (int i = 0; i < blogsEntriesnew1.size(); i++) {
				renderRequest.setAttribute("ttdpt" + (1 + i), blogsEntriesnew1.get(i));

				DynamicQuery query = DynamicQueryFactoryUtil.forClass(DLFileEntry.class);
				Property propertiyFileName = PropertyFactoryUtil.forName("fileName");
				String titltBlog = blogsEntriesnew1.get(i).getTitle().replaceAll("/", "-");
				query.add(propertiyFileName.like(titltBlog + "%"));

				List<DLFileEntry> dlFileEntries = DLFileEntryLocalServiceUtil.dynamicQuery(query);
				for (DLFileEntry d : dlFileEntries) {
					renderRequest.setAttribute("hrefttdpt" + (i + 1),
							LINK_CP_NEW + d.getFileEntryId());
				}
			}

			// image tin thứ nhất của Thông tin đa phương tiện(ttdpt)
			long fileEntryIdttdpt = blogsEntriesnew1.get(0).getSmallImageFileEntryId();
			// tạo src của ảnh theo thông tin trong database
			DLFileEntry imageTop1Ttdpt = DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryIdttdpt);
			renderRequest.setAttribute("imgSrcTtdptTop1", "/documents/" + imageTop1Ttdpt.getGroupId() + "/" + imageTop1Ttdpt.getFolderId()
					+ "/" + imageTop1Ttdpt.getTitle() + "/" + imageTop1Ttdpt.getUuid());
//=====================================================================================
			// danh sach top 5 muc Bản tin chính phủ tuần qua
			// lay danh sach blogs theo categoryId
			List<AssetEntryAssetCategoryRel> assetCategoryRels2 = AssetEntryAssetCategoryRelLocalServiceUtil
					.getAssetEntryAssetCategoryRelsByAssetCategoryId(71438);
			List<BlogsEntry> blogsEntries2 = new ArrayList<>();
			for (AssetEntryAssetCategoryRel a : assetCategoryRels2) {
				// lay assetEntryId trong AssetEntryAssetCategoryRel
				long entryId = a.getAssetEntryId();
				// lay assetEntry theo id vua lay
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(entryId);
				// lay classPk cua AssetEntry
				long blogsentryId = assetEntry.getClassPK();
				// lay BlogsEntryId theo classPk
				BlogsEntry blogsEntry = BlogsEntryLocalServiceUtil.getBlogsEntry(blogsentryId);
				blogsEntries2.add(blogsEntry);

			}
			// BlogsEntry1-5 theo blogsEntries giới hạn 5 phần tử và mới nhất
			// Bản tin chính phủ tuần qua(btcptq)
			List<BlogsEntry> blogsEntriesnew2 = blogsEntries2.stream().sorted().limit(5).collect(Collectors.toList());
			for (int i = 0; i < blogsEntriesnew2.size(); i++) {
				renderRequest.setAttribute("btcptq" + (1 + i), blogsEntriesnew2.get(i));

				DynamicQuery query2 = DynamicQueryFactoryUtil.forClass(DLFileEntry.class);
				Property propertiyFileName2 = PropertyFactoryUtil.forName("fileName");
				String titltBlog2 = blogsEntriesnew2.get(i).getTitle().replaceAll("/", "-");
				query2.add(propertiyFileName2.like(titltBlog2 + "%"));
				List<DLFileEntry> dlFileEntries2 = DLFileEntryLocalServiceUtil.dynamicQuery(query2);
				for (DLFileEntry d : dlFileEntries2) {
					renderRequest.setAttribute("hrefbtcptq" + (i + 1),
							LINK_CP_NEW + d.getFileEntryId());
				}
			}
			// image tin thứ nhất của Bản tin chính phủ tuần qua (btcptq)
			long fileEntryIdBtcptq = blogsEntriesnew2.get(0).getSmallImageFileEntryId();
			// tạo src của ảnh theo thông tin trong database
			DLFileEntry imageTop1Btcptq = DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryIdBtcptq);
			renderRequest.setAttribute("imgSrcBtcptqTop1", "/documents/" + imageTop1Btcptq.getGroupId() + "/" + imageTop1Btcptq.getFolderId()
					+ "/" + imageTop1Btcptq.getTitle() + "/" + imageTop1Btcptq.getUuid());

		} catch (PortalException e) {
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}
