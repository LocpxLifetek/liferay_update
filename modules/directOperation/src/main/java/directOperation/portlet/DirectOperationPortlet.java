package directOperation.portlet;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import directOperation.constants.DirectOperationPortletKeys;

/**
 * @author java05
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=DirectOperation", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + DirectOperationPortletKeys.DIRECTOPERATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class DirectOperationPortlet extends MVCPortlet {
	private PreparedStatement statement;
	private java.sql.Connection con;

	public Integer countBlogByCategory(String categoryId) {
		try {
			con = DataAccess.getConnection();
			statement = con.prepareStatement(
					"select count(*) as count from assetCAtegory ac inner join assetentryassetcategoryrel aeac on aeac.assetcategoryid=ac.categoryId inner join assetEntry ae on ae.entryId=aeac.assetentryid inner join BlogsEntry be on be.entryId=ae.classpk inner join DLFILEENTRY de on be.smallImageFileEntryId=de.fileEntryId  where ac.uuid_=? and be.status=0 and ae.classnameid='31201' order by be.modifieddate desc");
			statement.setString(1, categoryId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int countBlog = rs.getInt("count");
				return countBlog;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			String error = null;

			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String uuid = PortalUtil.getOriginalServletRequest(request).getParameter("uuid");
			String pageDetail = PortalUtil.getOriginalServletRequest(request).getParameter("page");

			if (uuid == null) {
				uuid = "ff2977fb-16c1-17fa-b087-87636b71184b";
				int count = countBlogByCategory(uuid);
				int result = (int) Math.ceil((float) count / 10);
				if (pageDetail == null) {
					pageDetail = "1";

					AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategoryByUuidAndGroupId(uuid,
							37732);
					renderRequest.setAttribute("assetCategory", assetCategory);
					if (Integer.parseInt(pageDetail) < 1 || Integer.parseInt(pageDetail) > result) {
						error = "404 Error !!!!";
						renderRequest.setAttribute("error", error);
					} else {

						con = DataAccess.getConnection();
						String categoryUUid = uuid;
						int page = Integer.parseInt(pageDetail);
						int size = 10;

						statement = con.prepareStatement(
								"select be.DESCRIPTION,be.TITLE as titleBlog,be.MODIFIEDDATE,be.UUID_ as uuidBlogs,de.GROUPID,de.FOLDERID,de.title as titleDlfile,de.UUID_ as uuidImage from assetCAtegory ac inner join assetentryassetcategoryrel aeac on aeac.assetcategoryid=ac.categoryId inner join assetEntry ae on ae.entryId=aeac.assetentryid inner join BlogsEntry be on be.entryId=ae.classpk inner join DLFILEENTRY de on be.smallImageFileEntryId=de.fileEntryId  where ac.uuid_=? and be.status=0 and ae.classnameid='31201' order by be.modifieddate desc OFFSET (?-1)*? ROWS FETCH NEXT ? ROWS ONLY");
						statement.setString(1, categoryUUid);
						statement.setInt(2, page);
						statement.setInt(3, size);
						statement.setInt(4, size);
						ResultSet rs = statement.executeQuery();
						List<BlogsEntryDto> listBlogsEntryDto = new ArrayList<>();
						while (rs.next()) {
							BlogsEntryDto blogsEntryDto = new BlogsEntryDto();
							Date modifiedDate = rs.getDate("MODIFIEDDATE");
							blogsEntryDto.setModifiedDate(modifiedDate);
							String description = rs.getString("DESCRIPTION");
							blogsEntryDto.setDescription(description);
							String titleBlog = rs.getString("titleBlog");
							blogsEntryDto.setTitleBlogs(titleBlog);
							String uuidBlogs = rs.getString("uuidBlogs");
							blogsEntryDto.setUuidBlogs(uuidBlogs);
							String groupIdImage = rs.getString("GROUPID");
							String folderImage = rs.getString("FOLDERID");
							String uuidImage = rs.getString("uuidImage");
							String titleImage = rs.getString("titleDlfile");
							String srcImage = "/documents" + "/" + groupIdImage + "/" + folderImage + "/" + titleImage
									+ "/" + uuidImage;
							blogsEntryDto.setSrcImage(srcImage);
							listBlogsEntryDto.add(blogsEntryDto);
						}

						renderRequest.setAttribute("uuid", uuid);
						renderRequest.setAttribute("currentPage", page);
						renderRequest.setAttribute("totalPage", result);
						renderRequest.setAttribute("listBlogsEntryDto", listBlogsEntryDto);
					}
				}
			} else {
				int count = countBlogByCategory(uuid);
				int result = (int) Math.ceil((float) count / 10);

				AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategoryByUuidAndGroupId(uuid,
						37732);
				renderRequest.setAttribute("assetCategory", assetCategory);
				if (Integer.parseInt(pageDetail) < 1 || Integer.parseInt(pageDetail) > result) {
					error = "404 Error !!!!";
					renderRequest.setAttribute("error", error);
				} else {

					con = DataAccess.getConnection();
					String categoryUUid = uuid;
					int page = Integer.parseInt(pageDetail);
					int size = 10;

					statement = con.prepareStatement(
							"select be.DESCRIPTION,be.TITLE as titleBlog,be.MODIFIEDDATE,be.UUID_ as uuidBlogs,de.GROUPID,de.FOLDERID,de.title as titleDlfile,de.UUID_ as uuidImage from assetCAtegory ac inner join assetentryassetcategoryrel aeac on aeac.assetcategoryid=ac.categoryId inner join assetEntry ae on ae.entryId=aeac.assetentryid inner join BlogsEntry be on be.entryId=ae.classpk inner join DLFILEENTRY de on be.smallImageFileEntryId=de.fileEntryId  where ac.uuid_=? and be.status=0 and ae.classnameid='31201' order by be.modifieddate desc OFFSET (?-1)*? ROWS FETCH NEXT ? ROWS ONLY");
					statement.setString(1, categoryUUid);
					statement.setInt(2, page);
					statement.setInt(3, size);
					statement.setInt(4, size);
					ResultSet rs = statement.executeQuery();
					List<BlogsEntryDto> listBlogsEntryDto = new ArrayList<>();
					while (rs.next()) {
						BlogsEntryDto blogsEntryDto = new BlogsEntryDto();
						Date modifiedDate = rs.getDate("MODIFIEDDATE");
						blogsEntryDto.setModifiedDate(modifiedDate);
						String description = rs.getString("DESCRIPTION");
						blogsEntryDto.setDescription(description);
						String titleBlog = rs.getString("titleBlog");
						blogsEntryDto.setTitleBlogs(titleBlog);
						String uuidBlogs = rs.getString("uuidBlogs");
						blogsEntryDto.setUuidBlogs(uuidBlogs);
						String groupIdImage = rs.getString("GROUPID");
						String folderImage = rs.getString("FOLDERID");
						String uuidImage = rs.getString("uuidImage");
						String titleImage = rs.getString("titleDlfile");
						String srcImage = "/documents" + "/" + groupIdImage + "/" + folderImage + "/" + titleImage + "/"
								+ uuidImage;
						blogsEntryDto.setSrcImage(srcImage);
						listBlogsEntryDto.add(blogsEntryDto);
					}

					renderRequest.setAttribute("uuid", uuid);
					renderRequest.setAttribute("currentPage", page);
					renderRequest.setAttribute("totalPage", result);
					renderRequest.setAttribute("listBlogsEntryDto", listBlogsEntryDto);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}

}