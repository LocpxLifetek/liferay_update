package callAjaxMenu.portlet;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import callAjaxMenu.constants.CallAjaxMenuPortletKeys;
import callAjaxMenu.dto.PorletCategoryDto;

/**
 * @author Dell
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CallAjaxMenu",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CallAjaxMenuPortletKeys.CALLAJAXMENU,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CallAjaxMenuPortlet extends MVCPortlet {
	private List<PorletCategoryDto> findAllCategoryMenu(long groupId)
			throws IOException, PortletException, SQLException {
		PreparedStatement statement = null;
		java.sql.Connection con = null;
		ResultSet rs = null;
		try {
			List<PorletCategoryDto> listPorletCategoryDto = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"select c.NAME as name from AssetCategory c inner join assetvocabulary av on c.vocabularyid=av.vocabularyid where c.groupId=? and upper(REGEXP_REPLACE(av.name,'[^a-z_A-Z ]')) = upper('menu')");
			statement.setLong(1, groupId);
			rs = statement.executeQuery();
			while (rs.next()) {
				PorletCategoryDto porletCategoryDto = new PorletCategoryDto();
				String name = rs.getString("name");
				String slug = toSlug(name);
				porletCategoryDto.setSlug(slug);
				porletCategoryDto.setName(name);
				listPorletCategoryDto.add(porletCategoryDto);
			}
			return listPorletCategoryDto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			rs.close();
			statement.close();
			con.close();

		}
	}
	
	private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
	private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

	public static String toSlug(String input) {
		String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
		String normalized = java.text.Normalizer.normalize(nowhitespace, Form.NFD);
		String slug = NONLATIN.matcher(normalized).replaceAll("");
		return slug.toLowerCase(Locale.ENGLISH);
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		try {
			JSONObject jsonUser=null;
			JSONArray jsonArray=JSONFactoryUtil.createJSONArray();
			resourceResponse.setContentType("text/html;charset=UTF-8");
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			List<PorletCategoryDto> listPorletCategoryDtos = findAllCategoryMenu(themeDisplay.getScopeGroupId());
			for (PorletCategoryDto porletCategoryDto : listPorletCategoryDtos) {
				jsonUser=JSONFactoryUtil.createJSONObject();
				jsonUser.put("name", porletCategoryDto.getName());
				jsonUser.put("slug", porletCategoryDto.getSlug());
				jsonArray.put(jsonUser);
			}
			PrintWriter out=resourceResponse.getWriter();
			out.print(jsonArray.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.serveResource(resourceRequest, resourceResponse);
	}
	
	
}