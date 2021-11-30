package com.cpNew.portlet;

import com.cpNew.constants.cpnewPortletKeys;
import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetLinkLocalServiceUtil;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
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

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			renderRequest.setAttribute("LINK_CP_NEW", LINK_CP_NEW);
			AssetCategory assetCategory1 = AssetCategoryLocalServiceUtil.getAssetCategory(72134);
			renderRequest.setAttribute("assetCategory1", assetCategory1);
			AssetCategory assetCategory2 = AssetCategoryLocalServiceUtil.getAssetCategory(71438);
			renderRequest.setAttribute("assetCategory2", assetCategory2);

			// img đầu dòng các mục
			String srcImgdaudong = "http://chinhphu.vn/templates/govportal/chinhphu/images/icon3.jpg";
			renderRequest.setAttribute("srcImgdaudong", srcImgdaudong);
			// img Banr tin chinh phu
//			renderRequest.setAttribute("srcImgBtcptq",
//					"http://daphuongtien.chinhphu.vn/file?path=/content/data/random/102021/CPTQ%20ava_9c38564d_bae176d1.jpg");
//
//			BlogsEntry blogTest = BlogsEntryLocalServiceUtil.getBlogsEntry(140411);
//			renderRequest.setAttribute("testImg", blogTest.getSmallImageFileEntryId());

			// danh sach top 5 muc Thông tin đa phương tiện
			// lay danh sach blogs theo categoryId
			List<AssetEntryAssetCategoryRel> assetCategoryRels = AssetEntryAssetCategoryRelLocalServiceUtil
					.getAssetEntryAssetCategoryRelsByAssetCategoryId(72134);
			List<DLFileEntry> dlFileEntriesLeft = new ArrayList<>();
			for (AssetEntryAssetCategoryRel a : assetCategoryRels) {
				// lay assetEntryId trong AssetEntryAssetCategoryRel
				long entryId = a.getAssetEntryId();
				// lay assetEntry theo id vua lay
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(entryId);
				// lay classPk cua DlFileEntry
				long fileId = assetEntry.getClassPK();
				DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileId);
				dlFileEntriesLeft.add(dlFileEntry);
			}
			
			List<DLFileEntry> dlFileEntriesLeftNew = dlFileEntriesLeft.stream().sorted().limit(5).collect(Collectors.toList());
			long entryIdTop1_1 = dlFileEntriesLeftNew.get(0).getFileEntryId();
			
			
//			List<AssetLink> assetLink1 = AssetLinkLocalServiceUtil.getLinks(entryIdTop1_1);
//			if (entryIdTop1_1 == assetLink1.get(0).getEntryId1()) {
//				// id cua Anh
//				long dlImage1 = assetLink1.get(0).getEntryId2();
//				DLFileEntry imgTtdpt1 = DLFileEntryLocalServiceUtil.getDLFileEntry(dlImage1);
//				renderRequest.setAttribute("imgTtdpt1", "/documents/" + imgTtdpt1.getGroupId() + imgTtdpt1.getFolderId()
//							+ imgTtdpt1.getTitle() + imgTtdpt1.getUuid());
//			}
//			if (entryIdTop1_1 == assetLink1.get(0).getEntryId2()) {
//				// id cua Anh
//				long dlImage1 = assetLink1.get(0).getEntryId1();
//				DLFileEntry imgTtdpt1 = DLFileEntryLocalServiceUtil.getDLFileEntry(dlImage1);
//				renderRequest.setAttribute("imgTtdpt1", "/documents/" + imgTtdpt1.getGroupId() + imgTtdpt1.getFolderId()
//				+ imgTtdpt1.getTitle() + imgTtdpt1.getUuid());
//			}
			
			
			
			
			
			
	
		
			// gan gia tri cho list cua 4 phan tu con lai
//			blogsEntriesnew1.remove(0);
//			renderRequest.setAttribute("blogsEntriesnew1", blogsEntriesnew1);
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
			long entryIdTop1_2 = blogsEntriesnew2.get(0).getEntryId();
			List<AssetLink> assetLink2 = AssetLinkLocalServiceUtil.getLinks(entryIdTop1_2);
			if (entryIdTop1_2 == assetLink2.get(0).getEntryId1()) {
				// id cua Anh
				long dlImage1 = assetLink2.get(0).getEntryId2();
				DLFileEntry imgBtcptq1 = DLFileEntryLocalServiceUtil.getDLFileEntry(dlImage1);
				renderRequest.setAttribute("imgBtcptq1", "/documents/" + imgBtcptq1.getGroupId() + imgBtcptq1.getFolderId()
				+ imgBtcptq1.getTitle() + imgBtcptq1.getUuid());
			}

			if (entryIdTop1_2 == assetLink2.get(0).getEntryId2()) {
				// id cua Anh
				long dlImage1 = assetLink2.get(0).getEntryId1();
				DLFileEntry imgBtcptq1 = DLFileEntryLocalServiceUtil.getDLFileEntry(dlImage1);
				renderRequest.setAttribute("imgBtcptq1", "/documents/" + imgBtcptq1.getGroupId() + imgBtcptq1.getFolderId()
				+ imgBtcptq1.getTitle() + imgBtcptq1.getUuid());
			}
			
			blogsEntriesnew2.remove(0);
			renderRequest.setAttribute("blogsEntriesnew2", blogsEntriesnew2);
		} catch (PortalException e) {
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}
