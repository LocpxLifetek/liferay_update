package goverment.portlet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import goverment.constants.GovermentPortletKeys;
import goverment.dto.CountViewVideoDto;
import goverment.dto.DlFileEntryVideoDto;
import goverment.dto.DlFileVideoDto;
import goverment.dto.FieldValuesInDdmContentDto;
import goverment.dto.ValueDlFileDto;
import goverment.sql.CommonSqlBlogEntry;
import goverment.url.UrlCurrentPorlet;

@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CountViewVideoMedia", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/countViewVideoMedia.jsp",
		"javax.portlet.name=" + GovermentPortletKeys.COUNTVIEWVIDEOMEDIA,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class CountViewVideoMediaPorlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);
			String url = new UrlCurrentPorlet().urlCurrentPorlet(themeDisplay.getURLCurrent(),
					themeDisplay.getLayoutFriendlyURL(layout));
			renderRequest.setAttribute("url", url);
			List<CountViewVideoDto> listCountViewVideoDto = new CommonSqlBlogEntry()
					.getViewCountVideo(themeDisplay.getScopeGroupId());
			List<DlFileVideoDto> listDlFileVideoDtos=new ArrayList<>();
			List<DlFileVideoDto> listDlFileVideoTitleDtos=new ArrayList<>();
			DDMStructure ddmStructure= DDMStructureLocalServiceUtil.getDDMStructureByUuidAndGroupId("4098419d-5793-b989-21c2-c267a13ea5e4", themeDisplay.getScopeGroupId());
			for (CountViewVideoDto countViewVideoDto : listCountViewVideoDto) {
				DLFileVersion dlFileVersion=DLFileVersionLocalServiceUtil.getLatestFileVersion(countViewVideoDto.getUserId(), countViewVideoDto.getFileEntryId());
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
					if (countViewVideoDto.getTitle().contains(".mp4")) {
						titleDlFile = countViewVideoDto.getTitle().replace(".mp4", "");
					} else {
						titleDlFile = countViewVideoDto.getTitle();
					}
					dlFileVideoDto.setTitle(titleDlFile);
					dlFileVideoDto.setUuid(countViewVideoDto.getUuid());
					listDlFileVideoDtos.add(dlFileVideoDto);
				}
			}
			int i=0;
			for (DlFileVideoDto dlFileVideoDto : listDlFileVideoDtos) {
				i++;
				if(i==1) {
					renderRequest.setAttribute("dlFileVideoDto", dlFileVideoDto);
				}else {
					listDlFileVideoTitleDtos.add(dlFileVideoDto);
				}
			}
			renderRequest.setAttribute("listDlFileVideoTitleDtos", listDlFileVideoTitleDtos);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}
