package pagable.param.portlet;

import pagable.param.portlet.ResultDao;
import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import pagable.param.constants.PagableParamPortletKeys;

/**
 * @author Dodo
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=PagableParam", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + PagableParamPortletKeys.PAGABLEPARAM, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class PagableParamPortlet extends MVCPortlet {

	public List<ResultDao> getBlogs(long assetCategoryId, int start, int limit)
			throws IOException, PortletException, SQLException {
		java.sql.Connection con = null;
		java.sql.PreparedStatement st = null;

		List<ResultDao> blogEntrise = new ArrayList<ResultDao>();

		try {
			con = DataAccess.getConnection();

			String sql = "(SELECT \n"
					+ "   COUNT(*) OVER() as totalrows ,bs.title,bs.content,bs.description,bs.createdate,bs.smallimagefileentryid,dl.groupid,dl.folderid,dl.title as titledl,dl.uuid_ as uuiddl \n"
					+ "FROM \n" + "   blogsentry bs LEFT JOIN assetentry ae ON bs.entryid = ae.classpk \n"
					+ "   INNER JOIN assetentryassetcategoryrel  aacr ON ae.entryid = aacr.assetentryid \n"
					+ "   INNER JOIN  dlfileentry dl ON bs.smallimagefileentryid = dl.FILEENTRYID \n" + "WHERE \n"
					+ "   aacr.assetcategoryid = ? \n" + "	AND bs.status = 0 \n" + "ORDER BY \n"
					+ "	bs.createdate DESC) \n";

			String sqlImprove = "SELECT \n" + "   * \n" + "FROM \n" + sql + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY \n";

			st = con.prepareStatement(sqlImprove);
			st.setLong(1, assetCategoryId);
			st.setInt(2, start);
			st.setInt(3, limit);
			java.sql.ResultSet rs = st.executeQuery();

			while (rs.next()) {
				ResultDao resultDao = new ResultDao();
				resultDao.setTotalRows(rs.getInt("totalrows"));
				resultDao.setTitle(rs.getString("title"));
				resultDao.setContent(rs.getString("content"));
				resultDao.setDescription(rs.getString("description"));
				resultDao.setCreateDate(rs.getDate("createDate"));
				resultDao.setSmallImageFileEntryId(rs.getLong("smallImageFileEntryId"));

				resultDao.setGroupId(rs.getLong("groupid"));
				resultDao.setFolderId(rs.getLong("folderid"));
				resultDao.setTitleDl(rs.getString("titledl"));
				resultDao.setUuidDl(rs.getString("uuiddl"));

				blogEntrise.add(resultDao);
			}

		} catch (Exception e) {
			System.out.println("passes" + e.toString());
		} finally {
			st.close();
			con.close();
		}
		return blogEntrise;
	}

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String page = PortalUtil.getOriginalServletRequest(request).getParameter("page");

			int start = 1;
			int end = 1;
			int limit = 10;

			if (Integer.parseInt(page) == 1 || page == null) {
				start = 0;
				end = limit - 1;
			} else if (Integer.parseInt(page) > 1) {
				start = (Integer.parseInt(page) - 1) * limit;
				end = limit - 1;
			}

			long assetCategoryId = 113410;
			List<ResultDao> blogEntrise = new ArrayList<ResultDao>();
			blogEntrise = getBlogs((long) assetCategoryId, start, end);

			float total_record = (float) blogEntrise.get(0).getTotalRows();
			int total_page = (int) Math.ceil(total_record / limit);

			renderRequest.setAttribute("blogEntrise", blogEntrise);
			renderRequest.setAttribute("total_page", total_page);
			renderRequest.setAttribute("current_page", page);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}