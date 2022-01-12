package goverment.portlet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import goverment.constants.GovermentPortletKeys;
import goverment.dto.DlFileEntryVideoDto;
import goverment.dto.DlFileVideoDto;
import goverment.dto.FieldValuesInDdmContentDto;
import goverment.dto.ValueDlFileDto;
import goverment.dto.VideoDto;
import goverment.sql.DlFileEntrySql;
import goverment.url.UrlCurrentPorlet;

@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=ListVideoCategory", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/listVideoCategory.jsp",
		"javax.portlet.name=" + GovermentPortletKeys.LISTVIDEOCATEGORY, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class ListVideoCategory extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			Layout layout = (Layout)renderRequest.getAttribute(WebKeys.LAYOUT);
			String url = new UrlCurrentPorlet().urlCurrentPorlet(themeDisplay.getURLCurrent(),
					themeDisplay.getLayoutFriendlyURL(layout));
			renderRequest.setAttribute("url", url);
			String uuid = PortalUtil.getOriginalServletRequest(request).getParameter("uuid");
			String pageDetail = PortalUtil.getOriginalServletRequest(request).getParameter("page");
			DLFolder dlFolder=DLFolderLocalServiceUtil.getDLFolderByUuidAndGroupId(uuid, themeDisplay.getScopeGroupId());
			Integer resultDlFile=new DlFileEntrySql().countVideoImage(dlFolder.getGroupId(), dlFolder.getFolderId());
		
			Integer page=Integer.parseInt(pageDetail == null ? "1" : pageDetail);
			Integer size=6;
			int result = (int) Math.ceil((float) resultDlFile/ size);
			List<DlFileVideoDto> listDlFileVideoDtos = new ArrayList<>();
			List<VideoDto> listVideoDtos=new DlFileEntrySql().findListVideoDtoByContentAndFolder(dlFolder.getGroupId(), dlFolder.getFolderId(), page, size);
			for (VideoDto videoDto : listVideoDtos) {
				ObjectMapper objectMapper = new ObjectMapper();
				DLFileEntry dlFileEntryVideo=DLFileEntryLocalServiceUtil.getDLFileEntry(videoDto.getFileEntryId());
				DlFileEntryVideoDto dlFileEntryVideoDtos = objectMapper.readValue(videoDto.getData(),
						new TypeReference<DlFileEntryVideoDto>() {
						});
				
				List<FieldValuesInDdmContentDto> listFieldValuesInDdmContentDtos = dlFileEntryVideoDtos
						.getFieldValues();
				for (FieldValuesInDdmContentDto fieldValuesInDdmContentDtos : listFieldValuesInDdmContentDtos) {
					ValueDlFileDto value = fieldValuesInDdmContentDtos.getValue();
					JSONObject jsonObject = JSONFactoryUtil.createJSONObject(value.getVi_VN());
					DlFileVideoDto dlFileVideoDto = new DlFileVideoDto();
					DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(jsonObject.getLong("classPK"));

					dlFileVideoDto
							.setSrc("/documents" + "/" + dlFileEntry.getGroupId() + "/" + dlFileEntry.getFolderId()
									+ "/" + dlFileEntry.getFileName() + "/" + dlFileEntry.getUuid());
					String titleDlFile=null;
					if(dlFileEntryVideo.getTitle().contains(".mp4")) {
						titleDlFile=dlFileEntryVideo.getTitle().replace(".mp4", "");
					}else {
						titleDlFile=dlFileEntryVideo.getTitle();
					}
					dlFileVideoDto.setTitle(titleDlFile);
					dlFileVideoDto.setUuid(dlFileEntryVideo.getUuid());
					listDlFileVideoDtos.add(dlFileVideoDto);
				}

			}
			renderRequest.setAttribute("listDlFileVideoDtos", listDlFileVideoDtos);
			renderRequest.setAttribute("dlFolder", dlFolder);
			renderRequest.setAttribute("currentPage", page);
			renderRequest.setAttribute("totalPage", result);
			renderRequest.setAttribute("uuid", uuid);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		super.doView(renderRequest, renderResponse);
	}
}
