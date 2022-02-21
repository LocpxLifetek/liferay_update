package goverment.portlet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
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
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import goverment.constants.GovermentPortletKeys;
import goverment.dto.DlFileEntryDto;
import goverment.dto.DlFileEntryVideoDto;
import goverment.dto.DlFileVideoDto;
import goverment.dto.FieldValuesInDdmContentDto;
import goverment.dto.ValueDlFileDto;
import goverment.dto.VideoDto;
import goverment.dto.VideoLatestDto;
import goverment.sql.DlFileEntrySql;
import goverment.url.UrlCurrentPorlet;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=VideoDetail",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/videoDetail.jsp",
			"javax.portlet.name=" + GovermentPortletKeys.VIDEODETAIL,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class VideoDetailPorlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);
			String url = new UrlCurrentPorlet().urlCurrentPorlet(themeDisplay.getURLCurrent(),
					themeDisplay.getLayoutFriendlyURL(layout),themeDisplay.getCDNBaseURL());
			renderRequest.setAttribute("url", url);
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String uuid = PortalUtil.getOriginalServletRequest(request).getParameter("uuid");
			DDMStructure ddmStructure= DDMStructureLocalServiceUtil.getDDMStructureByUuidAndGroupId("4098419d-5793-b989-21c2-c267a13ea5e4", themeDisplay.getScopeGroupId());
			//video
			DLFileEntry dlFile=DLFileEntryLocalServiceUtil.getFileEntryByUuidAndGroupId(uuid, themeDisplay.getScopeGroupId());
			AssetEntry assetEntry=AssetEntryLocalServiceUtil.incrementViewCounter(themeDisplay.getCompanyId(), themeDisplay.getUserId(), DLFileEntry.class.getName(), dlFile.getFileEntryId());
			String src="/documents/"+dlFile.getGroupId()+"/"+dlFile.getFolderId()+"/"+dlFile.getFileName()+"/"+dlFile.getUuid();
			if(dlFile.getTitle().contains(".mp4")) {
				
				renderRequest.setAttribute("title", dlFile.getTitle().replace(".mp4", ""));
			}else {
				renderRequest.setAttribute("title", dlFile.getTitle());
			}
			renderRequest.setAttribute("src", src);
			renderRequest.setAttribute("mimetype", dlFile.getMimeType());
			
			//video moi nhat
			List<VideoLatestDto> listVideoLatesDto=new DlFileEntrySql().fileVideoLatestDto(uuid, themeDisplay.getScopeGroupId());
			List<DlFileVideoDto> listDlFileVideoDtos=new ArrayList<>();
			for (VideoLatestDto videoLatestDto : listVideoLatesDto) {
				DLFileVersion dlFileVersion=DLFileVersionLocalServiceUtil.getLatestFileVersion(videoLatestDto.getUserId(), videoLatestDto.getFileEntryId());
				DLFileEntryMetadata dlFileEntryMetaData=DLFileEntryMetadataLocalServiceUtil.getFileEntryMetadata(ddmStructure.getStructureId(), dlFileVersion.getFileVersionId());
				DDMContent ddmContent=DDMContentLocalServiceUtil.getContent(dlFileEntryMetaData.getDDMStorageId());
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
					if (videoLatestDto.getTitle().contains(".mp4")) {
						titleDlFile = videoLatestDto.getTitle().replace(".mp4", "");
					} else {
						titleDlFile = videoLatestDto.getTitle();
					}
					dlFileVideoDto.setTitle(titleDlFile);
					dlFileVideoDto.setUuid(videoLatestDto.getUuid());
					listDlFileVideoDtos.add(dlFileVideoDto);
				}
			}
			renderRequest.setAttribute("listDlFileVideoDtos", listDlFileVideoDtos);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}
