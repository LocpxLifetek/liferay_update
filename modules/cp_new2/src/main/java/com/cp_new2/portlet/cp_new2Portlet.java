package com.cp_new2.portlet;

import com.cp_new2.constants.cp_new2PortletKeys;
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
import java.util.ArrayList;
import java.util.Arrays;
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
		"javax.portlet.display-name=cp_new2", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + cp_new2PortletKeys.CP_NEW2,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class cp_new2Portlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			// danh sach id cua Category (cac category nay co dinh tren web tu trai sang
			// phai)
			List<Integer> categoryIds = Arrays.asList(104913, 104924, 104927, 104930);
			// lay category theo list id tren
			List<AssetCategory> assCategories = new ArrayList<>();
			for (int i = 0; i < categoryIds.size(); i++) {
				AssetCategory category = AssetCategoryLocalServiceUtil.getAssetCategory(categoryIds.get(i));
				renderRequest.setAttribute("category" + (i + 1), category);
				assCategories.add(category);
			}

			// get list BlogEntry theo Category
			List<AssetEntryAssetCategoryRel> assetCategoryRels = AssetEntryAssetCategoryRelLocalServiceUtil
					.getAssetEntryAssetCategoryRelsByAssetCategoryId(104913);
			List<BlogsEntry> blogsEntries1 = new ArrayList<>();
			for (AssetEntryAssetCategoryRel a : assetCategoryRels) {
				// lay assetEntryId trong AssetEntryAssetCategoryRel
				long entryId = a.getAssetEntryId();
				// lay assetEntry theo id vua lay
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(entryId);
				// lay classPk cua AssetEntry
				long blogsentryId = assetEntry.getClassPK();
				// lay BlogsEntryId theo classPk
				BlogsEntry blogsEntry = BlogsEntryLocalServiceUtil.getBlogsEntry(blogsentryId);
				blogsEntries1.add(blogsEntry);

			}
			// BlogsEntry1-2 theo blogsEntries giới hạn 2 phần tử và mới nhất, mặc định sắp
			// xếp theo id
			// Thông tin đa phương tiện(ttdpt)
			List<BlogsEntry> blogsEntriesnew1 = blogsEntries1.stream().sorted().limit(2).collect(Collectors.toList());
			for (int i = 0; i < blogsEntriesnew1.size(); i++) {
				renderRequest.setAttribute("tlcddn" + (1 + i), blogsEntriesnew1.get(i));
			}

			// lay image của Blog Top1
			long imgTop1tlcddn = blogsEntriesnew1.get(0).getSmallImageFileEntryId();

			DLFileEntry fileEntryTlcddn = DLFileEntryLocalServiceUtil.getDLFileEntry(imgTop1tlcddn);
			renderRequest.setAttribute("imgSrcTlcddnTop1",
					"/documents/" + fileEntryTlcddn.getGroupId() + "/" + fileEntryTlcddn.getFolderId() + "/"
							+ fileEntryTlcddn.getTitle() + "/" + fileEntryTlcddn.getUuid());

//			=====================================================================================
			// get list BlogEntry theo Category
			List<AssetEntryAssetCategoryRel> assetCategoryRels2 = AssetEntryAssetCategoryRelLocalServiceUtil
					.getAssetEntryAssetCategoryRelsByAssetCategoryId(104924);
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
			// BlogsEntry1-2 theo blogsEntries giới hạn 2 phần tử và mới nhất, mặc định sắp
			// xếp theo id
			// Thông tin đa phương tiện(ttdpt)
			List<BlogsEntry> blogsEntriesnew2 = blogsEntries2.stream().sorted().limit(2).collect(Collectors.toList());
			for (int i = 0; i < blogsEntriesnew2.size(); i++) {
				renderRequest.setAttribute("cddh" + (1 + i), blogsEntriesnew2.get(i));
			}
			// lay image của Blog Top1
			long imgTop1cddh = blogsEntriesnew2.get(0).getSmallImageFileEntryId();

			DLFileEntry fileEntryCddh = DLFileEntryLocalServiceUtil.getDLFileEntry(imgTop1cddh);
			renderRequest.setAttribute("imgSrcCddhTop1", "/documents/" + fileEntryCddh.getGroupId() + "/"
					+ fileEntryCddh.getFolderId() + "/" + fileEntryCddh.getTitle() + "/" + fileEntryCddh.getUuid());
//			=====================================================================================
			// get list BlogEntry theo Category
			List<AssetEntryAssetCategoryRel> assetCategoryRels3 = AssetEntryAssetCategoryRelLocalServiceUtil
					.getAssetEntryAssetCategoryRelsByAssetCategoryId(104927);
			List<BlogsEntry> blogsEntries3 = new ArrayList<>();
			for (AssetEntryAssetCategoryRel a : assetCategoryRels3) {
				// lay assetEntryId trong AssetEntryAssetCategoryRel
				long entryId = a.getAssetEntryId();
				// lay assetEntry theo id vua lay
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(entryId);
				// lay classPk cua AssetEntry
				long blogsentryId = assetEntry.getClassPK();
				// lay BlogsEntryId theo classPk
				BlogsEntry blogsEntry = BlogsEntryLocalServiceUtil.getBlogsEntry(blogsentryId);
				blogsEntries3.add(blogsEntry);

			}
			// BlogsEntry1-2 theo blogsEntries giới hạn 2 phần tử và mới nhất, mặc định sắp
			// xếp theo id
			// Thông tin đa phương tiện(ttdpt)
			List<BlogsEntry> blogsEntriesnew3 = blogsEntries3.stream().sorted().limit(2).collect(Collectors.toList());
			for (int i = 0; i < blogsEntriesnew3.size(); i++) {
				renderRequest.setAttribute("sukien" + (1 + i), blogsEntriesnew3.get(i));
			}

			// lay image của Blog Top1
			long imgTop1Sukien = blogsEntriesnew3.get(0).getSmallImageFileEntryId();

			DLFileEntry fileEntrySukien = DLFileEntryLocalServiceUtil.getDLFileEntry(imgTop1Sukien);
			renderRequest.setAttribute("imgSrcSukienTop1",
					"/documents/" + fileEntrySukien.getGroupId() + "/" + fileEntrySukien.getFolderId() + "/"
							+ fileEntrySukien.getTitle() + "/" + fileEntrySukien.getUuid());
//			=====================================================================================
			// get list BlogEntry theo Category
			List<AssetEntryAssetCategoryRel> assetCategoryRels4 = AssetEntryAssetCategoryRelLocalServiceUtil
					.getAssetEntryAssetCategoryRelsByAssetCategoryId(104930);
			List<BlogsEntry> blogsEntries4 = new ArrayList<>();
			for (AssetEntryAssetCategoryRel a : assetCategoryRels4) {
				// lay assetEntryId trong AssetEntryAssetCategoryRel
				long entryId = a.getAssetEntryId();
				// lay assetEntry theo id vua lay
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(entryId);
				// lay classPk cua AssetEntry
				long blogsentryId = assetEntry.getClassPK();
				// lay BlogsEntryId theo classPk
				BlogsEntry blogsEntry = BlogsEntryLocalServiceUtil.getBlogsEntry(blogsentryId);
				blogsEntries4.add(blogsEntry);

			}
			// BlogsEntry1-2 theo blogsEntries giới hạn 2 phần tử và mới nhất, mặc định sắp
			// xếp theo id
			// Thông tin đa phương tiện(ttdpt)
			List<BlogsEntry> blogsEntriesnew4 = blogsEntries4.stream().sorted().limit(2).collect(Collectors.toList());
			for (int i = 0; i < blogsEntriesnew4.size(); i++) {
				renderRequest.setAttribute("doingoai" + (1 + i), blogsEntriesnew4.get(i));
			}
			// lay image của Blog Top1
			long imgTop1Doingoai = blogsEntriesnew4.get(0).getSmallImageFileEntryId();
			DLFileEntry fileEntryDoingoai = DLFileEntryLocalServiceUtil.getDLFileEntry(imgTop1Doingoai);
			renderRequest.setAttribute("imgSrcDoingoaiTop1",
					"/documents/" + fileEntryDoingoai.getGroupId() + "/" + fileEntryDoingoai.getFolderId() + "/"
							+ fileEntryDoingoai.getTitle() + "/" + fileEntryDoingoai.getUuid());
//			=====================================================================================
		} catch (Exception e) {
		}
		super.doView(renderRequest, renderResponse);
	}
}