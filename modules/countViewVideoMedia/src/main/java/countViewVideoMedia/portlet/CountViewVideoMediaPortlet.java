package countViewVideoMedia.portlet;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import countViewVideoMedia.constants.CountViewVideoMediaPortletKeys;
import countViewVideoMedia.dto.CountViewVideoDto;

/**
 * @author Dell
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CountViewVideoMedia",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CountViewVideoMediaPortletKeys.COUNTVIEWVIDEOMEDIA,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CountViewVideoMediaPortlet extends MVCPortlet {
	private List<CountViewVideoDto> getViewCountVideo(long groupIdDlfileEntry) throws SQLException{
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<CountViewVideoDto> listCountViewVideoDto=new ArrayList<>();
			con=DataAccess.getConnection();
			statement=con.prepareStatement("select df.groupid as groupId,df.folderid as folderId,df.filename as fileName,df.extension as extension,df.uuid_ as uuid,df.title as title,df.mimetype as mimeType from viewcountentry vc inner join assetEntry ae on vc.classpk=ae.classpk inner join dlfileentry df on df.fileentryid=ae.classpk where ae.mimetype like concat('video','%') and vc.classnameid='20011' and df.groupId=? order  by vc.viewcount desc OFFSET 0 ROWS FETCH NEXT 5 ROWS ONLY");
			statement.setLong(1, groupIdDlfileEntry);
			rs=statement.executeQuery();
			while(rs.next()) {
				CountViewVideoDto countViewVideoDto=new CountViewVideoDto();
				Integer groupId = rs.getInt("groupId");
				Integer folderId = rs.getInt("folderId");
				String fileName = rs.getString("fileName");
				String uuid = rs.getString("uuid");
				String title = rs.getString("title");
				String mimeType = rs.getString("mimeType");
				String extension = rs.getString("extension");
				String titleDlFileEntry = null;
				if (title.contains(extension)) {
					titleDlFileEntry = title.replace(extension, "");
				} else {
					titleDlFileEntry = title;
				}
				String src = "/documents/" + groupId + "/" + folderId + "/" + fileName + "/" + uuid;
				countViewVideoDto.setSrc(src);
				countViewVideoDto.setTitle(titleDlFileEntry);
				countViewVideoDto.setUuid(uuid);
				countViewVideoDto.setMimeType(mimeType);
				listCountViewVideoDto.add(countViewVideoDto);
			}
			return listCountViewVideoDto;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			rs.close();
			statement.close();
			con.close();

		}

	}
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			Layout layout = (Layout) renderRequest.getAttribute(WebKeys.LAYOUT);

			String urlCurrent = themeDisplay.getURLCurrent();
			String layoutUrl = themeDisplay.getLayoutFriendlyURL(layout);
			String[] url = urlCurrent.split(layoutUrl);
			String urlSite = null;
			int i=0;
			for (String string : url) {
				i++;
				if(i==1) {
					urlSite=string;
				}
			}
			renderRequest.setAttribute("url", urlSite);
			int j=0;
			List<CountViewVideoDto> lisCountViewVideoDtos=new ArrayList<>();
			List<CountViewVideoDto> listCountViewVideoDtos=getViewCountVideo(themeDisplay.getScopeGroupId());
			for (CountViewVideoDto countViewVideoDto : listCountViewVideoDtos) {
				j++;
				if(j==1) {
					renderRequest.setAttribute("countViewVideoDto", countViewVideoDto);
				}else {
					lisCountViewVideoDtos.add(countViewVideoDto);
				}
			}
			renderRequest.setAttribute("listCountViewVideoDtos", lisCountViewVideoDtos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
	
	
}