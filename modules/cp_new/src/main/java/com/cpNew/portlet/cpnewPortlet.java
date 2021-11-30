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
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;

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

			// categoryId Thông tin đa phương tiện - Bản tin chính phủ tuần qua
			List<Integer> cateIds = Arrays.asList(72134, 71438);
			int i = 0;
			for (Integer ca : cateIds) {
				i++;
				AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(ca);
				renderRequest.setAttribute("category" + i, assetCategory);
				List<AssetEntryAssetCategoryRel> assetCategoryRels = AssetEntryAssetCategoryRelLocalServiceUtil
						.getAssetEntryAssetCategoryRelsByAssetCategoryId(ca);

				List<DLFileEntry> fileEntries = new ArrayList<>();
				for (AssetEntryAssetCategoryRel a : assetCategoryRels) {
					long entryId = a.getAssetEntryId();
					AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(entryId);
					// check file is video???
					if (assetEntry.getMimeType().equals("video/mp4")) {
						// lấy fileId để lấy thông tin DLFileEntry(FileEntry)
						long fileId = assetEntry.getClassPK();
						DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(fileId);
						// add FileEntry vao List
						fileEntries.add(fileEntry);
					}
				}
				List<DLFileEntry> fileEntriesNew = fileEntries.stream()
						.sorted((s1, s2) -> (int) s2.getFileEntryId() - (int) s1.getFileEntryId()).limit(5)
						.collect(Collectors.toList());
				// lấy link ảnh cho mục đầu tiên
				DLFileEntry mucdau = fileEntriesNew.get(0);
				renderRequest.setAttribute("mucdau" + i, mucdau);

				for (AssetEntryAssetCategoryRel a2 : assetCategoryRels) {
					long entryId2 = a2.getAssetEntryId();
					AssetEntry assetEntry2 = AssetEntryLocalServiceUtil.getAssetEntry(entryId2);
					if (assetEntry2.getClassPK() == mucdau.getFileEntryId()) {
						AssetLink assetLink = AssetLinkLocalServiceUtil.getLinks(entryId2).get(0);
						if (assetLink.getEntryId1() == entryId2) {
							AssetEntry asAnh = AssetEntryLocalServiceUtil.getAssetEntry(assetLink.getEntryId2());
							FileEntry dlAnh = DLAppLocalServiceUtil.getFileEntry(asAnh.getClassPK());
							renderRequest.setAttribute("srcAnh" + i, "/documents/" + dlAnh.getGroupId() + "/"
									+ dlAnh.getFolderId() + "/" + dlAnh.getTitle() + "/" + dlAnh.getUuid());
						}
						if (assetLink.getEntryId2() == entryId2) {
							AssetEntry asAnh = AssetEntryLocalServiceUtil.getAssetEntry(assetLink.getEntryId1());
							FileEntry dlAnh = DLAppLocalServiceUtil.getFileEntry(asAnh.getClassPK());
							renderRequest.setAttribute("srcAnh" + i, "/documents/" + dlAnh.getGroupId() + "/"
									+ dlAnh.getFolderId() + "/" + dlAnh.getTitle() + "/" + dlAnh.getUuid());
						}
					}
				}
				fileEntriesNew.remove(mucdau);
				renderRequest.setAttribute("fileEntriesNew" + i, fileEntriesNew);
			}
			renderRequest.setAttribute("LINK_CP_NEW", LINK_CP_NEW);
		} catch (PortalException e) {
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}
