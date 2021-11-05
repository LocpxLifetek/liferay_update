package tes.p.portlet;

import com.liferay.adaptive.media.exception.AMRuntimeException.IOException;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;


import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import tes.p.portlet.ResultDao;
import tes.p.constants.TesPPortletKeys;

/**
 * @author Dell
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=TesP", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + TesPPortletKeys.TESP,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class TesPPortlet extends MVCPortlet {
	public List<ResultDao> getBlogs(long assetCategoryId, int start, int limit)
			throws IOException, PortletException, SQLException {
		java.sql.Connection con = null;
		java.sql.PreparedStatement st = null;

		List<ResultDao> blogEntrise = new ArrayList<ResultDao>();

		try {
			con = DataAccess.getConnection();

			String sql = "(SELECT \n"
					+ "   COUNT(*) OVER() as totalrows ,bs.uuid_ as uuidbs,bs.title,bs.content,bs.description,bs.createdate,bs.smallimagefileentryid,dl.groupid,dl.folderid,dl.title as titledl,dl.uuid_ as uuiddl \n"
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
				resultDao.setUuidBs(rs.getString("uuidbs"));
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
			throws IOException, PortletException, java.io.IOException {
		List<ResultDao> blogEntriseRight = new ArrayList<ResultDao>();
		List<ResultDao> blogEntriseLeft = new ArrayList<ResultDao>();
		ResultDao blogEntryLeft = null;
		ResultDao blogEntryRight = null;
		try {

			long assetCategoryIdLeft = 73185;
			long assetCategoryIdRight = 73188;

			blogEntriseLeft = getBlogs((long) assetCategoryIdLeft, 0, 5);
			blogEntriseRight = getBlogs((long) assetCategoryIdRight, 0, 5);

			if (blogEntriseLeft.size() > 0) {
				blogEntryLeft = blogEntriseLeft.get(0);
				blogEntriseLeft.remove(0);
			}
			if (blogEntriseRight.size() > 0) {
				blogEntryRight = blogEntriseRight.get(0);
				blogEntriseRight.remove(0);

			}

			renderRequest.setAttribute("blogEntriseLeft", blogEntriseLeft);
			renderRequest.setAttribute("blogEntriseRight", blogEntriseRight);
			renderRequest.setAttribute("blogEntryLeft", blogEntryLeft);
			renderRequest.setAttribute("blogEntryRight", blogEntryRight);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}

}