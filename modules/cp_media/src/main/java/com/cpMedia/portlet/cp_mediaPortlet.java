package com.cpMedia.portlet;

import com.cpMedia.constants.cp_mediaPortletKeys;
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
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author java05
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
//		"com.liferay.portlet.header-portlet-css=/css/video-js.css",
//		"com.liferay.portlet.header-portlet-javascript=/js/video.min.js", 
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=cp_media",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + cp_mediaPortletKeys.CP_MEDIA, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class cp_mediaPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			// http://localhost:8080/web/lifetek/media.chinhphu?id=******
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			int id = Integer.parseInt(PortalUtil.getOriginalServletRequest(request).getParameter("id"));
			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(id);
			String src = String.format("/documents/%s/%s/%s/%s", fileEntry.getGroupId(), fileEntry.getFolderId(),
					fileEntry.getTitle(), fileEntry.getUuid());
			renderRequest.setAttribute("src", src);
			renderRequest.setAttribute("fileName", fileEntry.getFileName());
			// title: Video cung chu de
			AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(147329);
			renderRequest.setAttribute("NameCategory", assetCategory.getName());
			// Lấy danh sách video cung chu de
			List<AssetEntryAssetCategoryRel> aeac = AssetEntryAssetCategoryRelLocalServiceUtil
					.getAssetEntryAssetCategoryRelsByAssetCategoryId(147329).stream().limit(7).collect(Collectors.toList());
//			List<AssetEntryAssetCategoryRel> listLeft = aeac.stream().sorted().limit(6).collect(Collectors.toList());
			int i = 0;
			for (AssetEntryAssetCategoryRel a : aeac) {
				// get assetEntryId
				long assetEntryId = a.getAssetEntryId();
				// get classPK(fileEntryId) trong AssetEntry bằng assetEntryId
				long fileEntryId = AssetEntryLocalServiceUtil.getAssetEntry(assetEntryId).getClassPK();
				// add DLFileEntry to List; dlFileEntryId trùng với id thì k add to List()
				if (fileEntryId != id) {
					// lấy ảnh tương ứng với video
					// get AssetLink
					List<AssetLink> assetLinks = AssetLinkLocalServiceUtil.getLinks(assetEntryId);
					DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId);
					if (!assetLinks.isEmpty()) {
						i++;
						renderRequest.setAttribute("srcVideo" + i,
								"http://localhost:8080/web/lifetek/media.chinhphu?id=" + dlFileEntry.getFileEntryId());
						renderRequest.setAttribute("title" + i, dlFileEntry.getTitle().replace(".mp4", ""));

						if (assetLinks.get(0).getEntryId1() == assetEntryId) {
							AssetEntry asAnh = AssetEntryLocalServiceUtil
									.getAssetEntry(assetLinks.get(0).getEntryId2());
							DLFileEntry dlAnh = DLFileEntryLocalServiceUtil.getDLFileEntry(asAnh.getClassPK());
							renderRequest.setAttribute("srcAnh" + i, "/documents/" + dlAnh.getGroupId() + "/"
									+ dlAnh.getFolderId() + "/" + dlAnh.getTitle() + "/" + dlAnh.getUuid());
						}
						if (assetLinks.get(0).getEntryId2() == assetEntryId) {
							AssetEntry asAnh = AssetEntryLocalServiceUtil
									.getAssetEntry(assetLinks.get(0).getEntryId1());
							DLFileEntry dlAnh = DLFileEntryLocalServiceUtil.getDLFileEntry(asAnh.getClassPK());
							renderRequest.setAttribute("srcAnh" + i, "/documents/" + dlAnh.getGroupId() + "/"
									+ dlAnh.getFolderId() + "/" + dlAnh.getTitle() + "/" + dlAnh.getUuid());
						}
					}
				}
				
			}
		} catch (PortalException e) {
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}