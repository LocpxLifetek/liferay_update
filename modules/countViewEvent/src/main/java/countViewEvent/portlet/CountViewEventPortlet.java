package countViewEvent.portlet;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import countViewEvent.constants.CountViewEventPortletKeys;
import countViewEvent.dto.CountViewDto;

/**
 * @author java05
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CountViewEvent", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CountViewEventPortletKeys.COUNTVIEWEVENT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class CountViewEventPortlet extends MVCPortlet {
	private List<CountViewDto> countViewEvent(String currentDate, String fromDate,long groupIdCategory) {
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<CountViewDto> listCountViewDtos = new ArrayList<>();

			con = DataAccess.getConnection();
			statement = con.prepareStatement("SELECT\r\n" + "    be.title          AS title,\r\n"
					+ "    be.description    AS description,\r\n" + "    be.uuid_          AS uuid,\r\n"
					+ "    de.groupid        AS groupid,\r\n" + "    de.folderid       AS folderid,\r\n"
					+ "    de.title          AS titledlfile,\r\n" + "    de.uuid_          AS uuiddlfile\r\n"
					+ "FROM\r\n" + "         assetcategory ac\r\n"
					+ "    INNER JOIN assetentryassetcategoryrel  aeac ON ac.categoryid = aeac.assetcategoryid\r\n"
					+ "    INNER JOIN assetentry                  ae ON ae.entryid = aeac.assetentryid\r\n"
					+ "    INNER JOIN viewcountentry              vc ON vc.classpk = ae.entryid\r\n"
					+ "    INNER JOIN blogsentry                  be ON be.entryid = ae.classpk\r\n"
					+ "    INNER JOIN dlfileentry                 de ON de.fileentryid = be.smallimagefileentryid\r\n"
					+ "WHERE\r\n" + "        ae.classnameid = '31201'\r\n" + "    AND ac.groupid=? and upper(REGEXP_REPLACE(ac.name,'[^a-z_A-Z ]')) like upper('Tin tc s kin')\r\n"
					+ "    AND be.status = '0'\r\n"
					+ "    AND be.modifieddate BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD')\r\n"
					+ "ORDER BY\r\n" + "    vc.viewcount DESC\r\n" + "OFFSET 0 ROWS FETCH NEXT 8 ROWS ONLY");
			statement.setLong(1, groupIdCategory);
			statement.setString(2, fromDate);
			statement.setString(3, currentDate);
			rs = statement.executeQuery();
			while (rs.next()) {
				CountViewDto countViewDto = new CountViewDto();
				String title = rs.getString("title");
				String description = rs.getString("description");
				String uuid = rs.getString("uuid");
				Integer groupId = rs.getInt("groupId");
				Integer folderId = rs.getInt("folderId");
				String titleDlfile = rs.getString("titledlfile");
				String uuidDlfile = rs.getString("uuiddlfile");
				countViewDto.setUuid(uuid);
				countViewDto.setDescription(description);
				countViewDto.setTitle(title);
				String src = "/documents" + "/" + groupId + "/" + folderId + "/" + titleDlfile + "/" + uuidDlfile;
				countViewDto.setSrcImage(src);
				listCountViewDtos.add(countViewDto);
			}
			return listCountViewDtos;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					/* Ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					/* Ignored */}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					/* Ignored */}
			}
		}
	}

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date currentDate = calendar.getTime();
			String current = format.format(currentDate);
			calendar.add(Calendar.DATE, -30);
			Date fromDate = calendar.getTime();
			String from = format.format(fromDate);
			List<CountViewDto> listCountViewDtos=countViewEvent(current, from,themeDisplay.getScopeGroupId());
			int i=0;
			
			List<CountViewDto> listManyCountViewDtos=new ArrayList<>();
			for (CountViewDto countViewDto : listCountViewDtos) {
				i++;
				if(i==1) {
					renderRequest.setAttribute("countViewDto", countViewDto);
				}else {
					listManyCountViewDtos.add(countViewDto);
				}
			}
			renderRequest.setAttribute("listBlogsEntry", listManyCountViewDtos);
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}

}