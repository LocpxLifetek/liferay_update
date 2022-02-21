package goverment.portlet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import goverment.constants.GovermentPortletKeys;
import goverment.dto.AssetEntryAssetCategoryRelDto;
import goverment.dto.DlFileEntryVideoDto;
import goverment.dto.DlFileVideoDto;
import goverment.dto.FieldValuesInDdmContentDto;
import goverment.dto.FileEntryDlFileDto;
import goverment.dto.ValueDlFileDto;
import goverment.sql.AssetEntryAssetCategoryRelSql;
import goverment.sql.DlFileEntrySql;
import goverment.url.UrlCurrentPorlet;

@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=VideoMedia", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/videoMedia.jsp",
		"javax.portlet.name=" + GovermentPortletKeys.VIDEOMEDIA, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class VideoMediaPorlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);
			String url = new UrlCurrentPorlet().urlCurrentPorlet(themeDisplay.getURLCurrent(),
					themeDisplay.getLayoutFriendlyURL(layout),themeDisplay.getCDNBaseURL());
			renderRequest.setAttribute("url", url);
			AssetCategory assetCategory=AssetCategoryLocalServiceUtil.getAssetCategoryByUuidAndGroupId("18c83fb3-4c8d-9aeb-6553-e0a51edd3e0f", themeDisplay.getScopeGroupId());
			DDMStructure ddmStructure= DDMStructureLocalServiceUtil.getDDMStructureByUuidAndGroupId("4098419d-5793-b989-21c2-c267a13ea5e4", themeDisplay.getScopeGroupId());
			Map<AssetCategory,List<DlFileVideoDto>> maps=new LinkedHashMap<>();
			List<AssetCategory> parentAssetCategory=AssetCategoryLocalServiceUtil.getChildCategories(assetCategory.getCategoryId());
			for (AssetCategory assetCategory2 : parentAssetCategory) {
				List<DlFileVideoDto> listDlFileVideoDtos=new ArrayList<>();
				List<AssetEntryAssetCategoryRelDto> listAssetEntryAssetCategoryRels=new AssetEntryAssetCategoryRelSql().listAssetEntryAssetCategoryRelDtos(assetCategory2.getCategoryId(), 0, 3);
				for (AssetEntryAssetCategoryRelDto assetEntryAssetCategoryRels : listAssetEntryAssetCategoryRels) {
					AssetEntry assetEntry=AssetEntryLocalServiceUtil.getAssetEntry(assetEntryAssetCategoryRels.getAssetEntryId());
					FileEntryDlFileDto fileEntryDlFileDtos=new DlFileEntrySql().findDlFileByGroupIdAndExtension(themeDisplay.getScopeGroupId(),assetEntry.getClassPK());
					DLFileVersion dlFileVersion=DLFileVersionLocalServiceUtil.getLatestFileVersion(fileEntryDlFileDtos.getUserId(), fileEntryDlFileDtos.getFileEntryId());
					DLFileEntryMetadata dlFileEntryMetaData=DLFileEntryMetadataLocalServiceUtil.getFileEntryMetadata(ddmStructure.getStructureId(), dlFileVersion.getFileVersionId());
//					DlFileEntryMetaDataDto dlFileEntryMetaDataDto=new DlFileEntrySql().fileDlfileEntryMetaDataByFileVersionIdAndFileEntryId(fileEntryDlFileDtos.getFileEntryId(), dlFileVersion.getFileVersionId());
					DDMContent ddmContent=DDMContentLocalServiceUtil.getContent(dlFileEntryMetaData.getDDMStorageId());
					//convert Xml - json
					ObjectMapper objectMapper = new ObjectMapper();
					DlFileEntryVideoDto dlFileEntryVideoDtos=objectMapper.readValue(ddmContent.getData(), new TypeReference<DlFileEntryVideoDto>() {});
					List<FieldValuesInDdmContentDto> listFieldValuesInDdmContentDtos = dlFileEntryVideoDtos.getFieldValues();
					for (FieldValuesInDdmContentDto fieldValuesInDdmContentDtos : listFieldValuesInDdmContentDtos) {
						ValueDlFileDto value = fieldValuesInDdmContentDtos.getValue();
						JSONObject jsonObject = JSONFactoryUtil.createJSONObject(value.getVi_VN());
						DlFileVideoDto dlFileVideoDto = new DlFileVideoDto();
						DLFileEntry dlFileEntryImage = DLFileEntryLocalServiceUtil
								.getDLFileEntry(jsonObject.getLong("classPK"));
						dlFileVideoDto.setSrc("/documents" + "/" + dlFileEntryImage.getGroupId() + "/"
								+ dlFileEntryImage.getFolderId() + "/" + dlFileEntryImage.getFileName() + "/"
								+ dlFileEntryImage.getUuid());
						String titleDlFile = null;
						if (fileEntryDlFileDtos.getTitle().contains(".mp4")) {
							titleDlFile = fileEntryDlFileDtos.getTitle().replace(".mp4", "");
						} else {
							titleDlFile = fileEntryDlFileDtos.getTitle();
						}
						dlFileVideoDto.setTitle(titleDlFile);
						dlFileVideoDto.setUuid(fileEntryDlFileDtos.getUuid());
						listDlFileVideoDtos.add(dlFileVideoDto);
					}
				}
				maps.put(assetCategory2, listDlFileVideoDtos);
			}
			renderRequest.setAttribute("maps", maps);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}
