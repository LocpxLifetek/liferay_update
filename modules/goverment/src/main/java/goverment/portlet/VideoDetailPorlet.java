package goverment.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import goverment.constants.GovermentPortletKeys;

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
//	@Override
//	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
//			throws IOException, PortletException {
//		try {
//			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
//			Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);
//			String url = new UrlCurrentPorlet().urlCurrentPorlet(themeDisplay.getURLCurrent(),
//					themeDisplay.getLayoutFriendlyURL(layout));
//			renderRequest.setAttribute("url", url);
//			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
//			String uuid = PortalUtil.getOriginalServletRequest(request).getParameter("uuid");
//			//video
//			DLFileEntry dlFile=DLFileEntryLocalServiceUtil.getFileEntryByUuidAndGroupId(uuid, themeDisplay.getScopeGroupId());
//			AssetEntry assetEntry=AssetEntryLocalServiceUtil.incrementViewCounter(themeDisplay.getCompanyId(), themeDisplay.getUserId(), DLFileEntry.class.getName(), dlFile.getFileEntryId());
//			String src="/documents/"+dlFile.getGroupId()+"/"+dlFile.getFolderId()+"/"+dlFile.getUuid();
//			if(dlFile.getTitle().contains(".mp4")) {
//				
//				renderRequest.setAttribute("title", dlFile.getTitle().replace(".mp4", ""));
//			}else {
//				renderRequest.setAttribute("title", dlFile.getTitle());
//			}
//			renderRequest.setAttribute("src", src);
//			renderRequest.setAttribute("mimetype", dlFile.getMimeType());
//			//video tuong tu
//			Map<DLFolder,List<DlFileVideoDto>> maps=new LinkedHashMap<>();
//			if(dlFile != null) {
//				DLFolder dlFolderEntry=DLFolderLocalServiceUtil.getDLFolder(dlFile.getFolderId());
//				List<DlFileEntryDto> listDlFileEntry = new DlFileEntrySql().findDlFileEntryByFolderId(dlFolderEntry.getGroupId(), dlFolderEntry.getFolderId(),dlFile.getFileEntryId(),3);
//				List<DlFileVideoDto> listDlFileVideoDtos=new ArrayList<>();
//				if (listDlFileEntry.isEmpty() == false) {
//					for (DlFileEntryDto dlFileEntry : listDlFileEntry) {
//						
//						ObjectMapper objectMapper = new ObjectMapper();
//						VideoDto videoDto = new DlFileEntrySql().findListVideoDtoByFolder(dlFileEntry.getGroupId(),
//								dlFileEntry.getId());
//						if (videoDto.getData() != null) {
//							DLFileEntry dlFileEntryVideo = DLFileEntryLocalServiceUtil
//									.getDLFileEntry(videoDto.getFileEntryId());
//							DlFileEntryVideoDto dlFileEntryVideoDtos = objectMapper.readValue(videoDto.getData(),
//									new TypeReference<DlFileEntryVideoDto>() {
//									});
//							List<FieldValuesInDdmContentDto> listFieldValuesInDdmContentDtos = dlFileEntryVideoDtos
//									.getFieldValues();
//							for (FieldValuesInDdmContentDto fieldValuesInDdmContentDtos : listFieldValuesInDdmContentDtos) {
//								ValueDlFileDto value = fieldValuesInDdmContentDtos.getValue();
//								JSONObject jsonObject = JSONFactoryUtil.createJSONObject(value.getVi_VN());
//								DlFileVideoDto dlFileVideoDto = new DlFileVideoDto();
//								DLFileEntry dlFileEntryImage = DLFileEntryLocalServiceUtil
//										.getDLFileEntry(jsonObject.getLong("classPK"));
//
//								dlFileVideoDto.setSrc("/documents" + "/" + dlFileEntryImage.getGroupId() + "/"
//										+ dlFileEntryImage.getFolderId() + "/" + dlFileEntryImage.getFileName() + "/"
//										+ dlFileEntryImage.getUuid());
//								String titleDlFile = null;
//								if (dlFileEntryVideo.getTitle().contains(".mp4")) {
//									titleDlFile = dlFileEntryVideo.getTitle().replace(".mp4", "");
//								} else {
//									titleDlFile = dlFileEntryVideo.getTitle();
//								}
//								dlFileVideoDto.setTitle(titleDlFile);
//								dlFileVideoDto.setUuid(dlFileEntryVideo.getUuid());
//								listDlFileVideoDtos.add(dlFileVideoDto);
//							}
//
//						}
//					}
//					if (listDlFileVideoDtos.isEmpty() == false) {
//						maps.put(dlFolderEntry, listDlFileVideoDtos);
//					}
//				}
//				
//			}
//			renderRequest.setAttribute("maps", maps);
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: handle exception
//		}
//		super.doView(renderRequest, renderResponse);
//	}
}
