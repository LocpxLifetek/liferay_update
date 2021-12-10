package porletCategory.portlet;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
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

import org.osgi.service.component.annotations.Component;

import dto.PorletCategoryDto;
import porletCategory.constants.PorletCategoryPortletKeys;

/**
 * @author java05
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=PorletCategory", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + PorletCategoryPortletKeys.PORLETCATEGORY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class PorletCategoryPortlet extends MVCPortlet {

	private List<PorletCategoryDto> findAllCategoryMenu()
			throws IOException, PortletException, SQLException {
		PreparedStatement statement = null;
		java.sql.Connection con = null;
		ResultSet rs = null;
		try {
			List<PorletCategoryDto> listPorletCategoryDto = new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"select c.NAME as name from AssetCategory c inner join assetvocabulary av on c.vocabularyid=av.vocabularyid where  upper(REGEXP_REPLACE(av.name,'[^a-z_A-Z ]')) = upper('menu')");
			
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
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			
			List<PorletCategoryDto> listPorletCategoryDtos = findAllCategoryMenu();
			renderRequest.setAttribute("listPorletCategoryDtos", listPorletCategoryDtos);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}

}