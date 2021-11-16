package pageable.portlet;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import pageable.constants.PageablePortletKeys;

/**
 * @author java05
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Pageable", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + PageablePortletKeys.PAGEABLE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class PageablePortlet extends MVCPortlet {
	private PreparedStatement statement;
	java.sql.Connection con = null;
	public void pageableBlogs(int pagesize,int size) throws IOException, PortletException, SQLException {
		try {
			con = DataAccess.getConnection();
			statement=con.prepareStatement("select be.TITLE, be.CONTENT from assetCAtegory ac inner join assetentryassetcategoryrel aeac on aeac.assetcategoryid=ac.categoryId inner join assetEntry ae on ae.entryId=aeac.assetentryid inner join BlogsEntry be on be.entryId=ae.classpk  where ac.categoryId='113410' and be.status=0 and ae.classnameid='31201' order by be.modifieddate desc OFFSET (?-1)*? ROWS FETCH NEXT ? ROWS ONLY");
			statement.setInt(1, pagesize);
			statement.setInt(2, size);
			statement.setInt(3, size);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				String entryId = rs.getString("title");
				System.out.println("title: " + entryId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			statement.close();
			con.close();
		}
		
	}

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			pageableBlogs(2,5);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}

}