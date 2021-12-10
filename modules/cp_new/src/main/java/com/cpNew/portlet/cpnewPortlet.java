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
		"javax.portlet.display-name=cpnew", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + cpnewPortletKeys.CPNEW,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class cpnewPortlet extends MVCPortlet {

	private final String LINK_CP_NEW = "/web/lifetek/media.chinhphu?id=";

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			// img đầu dòng các mục
			String srcImgdaudong = "http://chinhphu.vn/templates/govportal/chinhphu/images/icon3.jpg";
			renderRequest.setAttribute("srcImgdaudong", srcImgdaudong);
			List<Integer> categoryIds = Arrays.asList(72134, 71438);
			int i = 0;
			for (Integer id : categoryIds) {
				i++;
				AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(id);
				renderRequest.setAttribute("assetCategory" + i, assetCategory);
				List<AssetEntryAssetCategoryRel> assetCategoryRels = AssetEntryAssetCategoryRelLocalServiceUtil
						.getAssetEntryAssetCategoryRelsByAssetCategoryId(id);
				// list output
				List<DLFileEntry> dlFileEntries = new ArrayList<>();
				for (AssetEntryAssetCategoryRel a : assetCategoryRels) {
					// lay assetEntryId trong AssetEntryAssetCategoryRel
					long assetEntryId = a.getAssetEntryId();

					AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(assetEntryId);
					// lấy DlFileEntryId theo ClassPk
					if (assetEntry.getMimeType().equals("video/mp4")) {
						long classPk = assetEntry.getClassPK();
						DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(classPk);
						dlFileEntries.add(dlFileEntry);
					}
				}
				List<DLFileEntry> listOutput = dlFileEntries.stream()
						.sorted((s1, s2) -> (int) s2.getFileEntryId() - (int) s1.getFileEntryId()).limit(5)
						.collect(Collectors.toList());

				// **
				DLFileEntry dlFileEntry = listOutput.get(0);
				renderRequest.setAttribute("top1_" + i, dlFileEntry);
				for (AssetEntryAssetCategoryRel l : assetCategoryRels) {
					long assetEntryId = l.getAssetEntryId();

					AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(assetEntryId);
					if (dlFileEntry.getFileEntryId() == assetEntry.getClassPK()) {
						long assEntryId = assetEntry.getEntryId();
						List<AssetLink> assLinks = AssetLinkLocalServiceUtil.getLinks(assEntryId);
						if (assLinks.isEmpty() || assLinks == null) {
							// ảnh báo lỗi
							DLFileEntry imgTtdpt = DLFileEntryLocalServiceUtil.getDLFileEntry(573793);
							renderRequest.setAttribute("imgTop" + i, "/documents/" + imgTtdpt.getGroupId() + "/"
									+ imgTtdpt.getFolderId() + "/" + imgTtdpt.getTitle() + "/" + imgTtdpt.getUuid());
						} else {
							// lay link anh
							if (assEntryId == assLinks.get(0).getEntryId1()) {
								// id cua Anh
								long dlImage = assLinks.get(0).getEntryId2();
								AssetEntry asAnh = AssetEntryLocalServiceUtil.getAssetEntry(dlImage);
								DLFileEntry imgTtdpt = DLFileEntryLocalServiceUtil.getDLFileEntry(asAnh.getClassPK());
								renderRequest.setAttribute("imgTop" + i,
										"/documents/" + imgTtdpt.getGroupId() + "/" + imgTtdpt.getFolderId() + "/"
												+ imgTtdpt.getTitle() + "/" + imgTtdpt.getUuid());
							}
							if (assEntryId == assLinks.get(0).getEntryId2()) {
								// id cua Anh
								long dlImage = assLinks.get(0).getEntryId1();
								AssetEntry asAnh = AssetEntryLocalServiceUtil.getAssetEntry(dlImage);
								DLFileEntry imgTtdpt = DLFileEntryLocalServiceUtil.getDLFileEntry(asAnh.getClassPK());
								renderRequest.setAttribute("imgTop" + i,
										"/documents/" + imgTtdpt.getGroupId() + "/" + imgTtdpt.getFolderId() + "/"
												+ imgTtdpt.getTitle() + "/" + imgTtdpt.getUuid());
							}
						}
					}
				}
				listOutput.remove(dlFileEntry);
				renderRequest.setAttribute("listOutput" + i, listOutput);
				// **

			}

			renderRequest.setAttribute("LINK_CP_NEW", LINK_CP_NEW);
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}
