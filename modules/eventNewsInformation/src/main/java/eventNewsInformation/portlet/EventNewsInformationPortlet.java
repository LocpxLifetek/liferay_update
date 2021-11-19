package eventNewsInformation.portlet;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import eventNewsInformation.constants.EventNewsInformationPortletKeys;
import eventNewsInformation.dto.AssetCategoryDto;
import eventNewsInformation.dto.BlogsEntryDto;
import eventNewsInformation.dto.CategoryDto;

/**
 * @author java05
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=EventNewsInformation", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EventNewsInformationPortletKeys.EVENTNEWSINFORMATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class EventNewsInformationPortlet extends MVCPortlet {
	private PreparedStatement statement;
	private Connection con=null;
	
	private List<AssetCategoryDto> findAssetCategoryByParentCategory() throws SQLException{
		try {
			List<AssetCategoryDto> listAssetCategoryDto=new ArrayList<>();
			con=DataAccess.getConnection();
			statement=con.prepareStatement("select ac.categoryid as categoryId,ac.uuid_ as uuid, ac.name as name from assetCategory ac where ac.parentcategoryid='108693'");
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				AssetCategoryDto assetCategoryDto=new AssetCategoryDto();
				Integer categoryId=rs.getInt("categoryId");
				String nameCategory=rs.getString("name");
				String uuid=rs.getString("uuid");
				assetCategoryDto.setUuid(uuid);
				assetCategoryDto.setId(categoryId);
				assetCategoryDto.setName(nameCategory);
				listAssetCategoryDto.add(assetCategoryDto);
			}
			return listAssetCategoryDto;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			statement.close();
			con.close();
		}
	}
	
	private List<BlogsEntryDto> findAllBlogsEntryById(Integer id) throws SQLException{
		try {
			List<BlogsEntryDto> listBlogsEntryDtos=new ArrayList<>();
			con=DataAccess.getConnection();
			statement=con.prepareStatement("SELECT be.uuid_  AS uuidblogsentry,be.entryid  AS entryid,be.title AS titleblogsentry, be.description AS descriptiondlfileentry,be.modifieddate AS modifieddate,dl.fileentryid AS fileentryid,dl.groupid  AS groupid,dl.folderid AS folderid, dl.title AS titledlfileentry, dl.uuid_ AS uuiddlfileentry FROM assetcategory ac INNER JOIN assetentryassetcategoryrel  aeac ON ac.categoryid = aeac.assetcategoryid INNER JOIN assetentry ae ON aeac.assetentryid = ae.entryid INNER JOIN blogsentry be ON ae.classpk = be.entryid INNER JOIN dlfileentry dl ON dl.fileentryid = be.smallimagefileentryid WHERE ac.categoryid =?  AND ae.classnameid = '31201'  AND be.status = '0' ORDER BY be.modifieddate DESC OFFSET 0 ROWS FETCH NEXT 4 ROWS ONLY");
			statement.setInt(1, id);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				BlogsEntryDto blogsEntryDto=new BlogsEntryDto();
				String uuidBlogsEntry=rs.getString("uuidblogsentry");
				Integer entryId=rs.getInt("entryid");
				String titleBlogsEntry=rs.getString("titleblogsentry");
				String description=rs.getString("descriptiondlfileentry");
				Timestamp modifiedDate=rs.getTimestamp("modifieddate");
				Integer fileEntryId=rs.getInt("fileentryid");
				Integer groupId=rs.getInt("groupid");
				Integer folderId=rs.getInt("folderid");
				String titleDlFileEntry=rs.getString("titledlfileentry");
				String uuidDlFileEntry=rs.getString("uuiddlfileentry");
				blogsEntryDto.setDescription(description);
				blogsEntryDto.setEntryId(entryId);
				blogsEntryDto.setFileEntryId(fileEntryId);
				blogsEntryDto.setFolderId(folderId);
				blogsEntryDto.setGroupId(groupId);
				blogsEntryDto.setTitleBlogsEntry(titleBlogsEntry);
				blogsEntryDto.setTitleDlFileEntry(titleDlFileEntry);
				blogsEntryDto.setUuidBlogsEntry(uuidBlogsEntry);
				blogsEntryDto.setUuidDlFileEntry(uuidDlFileEntry);
				blogsEntryDto.setModifiedDate(modifiedDate);
				listBlogsEntryDtos.add(blogsEntryDto);
			}
			return listBlogsEntryDtos;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			statement.close();
			con.close();
		}
	}
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			
			List<AssetCategoryDto> listAssetCategoryDto=findAssetCategoryByParentCategory();
			Map<List<AssetCategoryDto>,List<BlogsEntryDto>> maps=new LinkedHashMap<>();
			for (AssetCategoryDto assetCategoryDto : listAssetCategoryDto) {
				List<AssetCategoryDto> listAssetCategory=new ArrayList<>();
				List<BlogsEntryDto> listBlogsEntryDtoMap=new ArrayList<>();
				listAssetCategory.add(assetCategoryDto);
				List<BlogsEntryDto> listBlogsEntryDtos=findAllBlogsEntryById(assetCategoryDto.getId());
				for (BlogsEntryDto blogsEntryDto : listBlogsEntryDtos) {
					listBlogsEntryDtoMap.add(blogsEntryDto);
				}
				maps.put(listAssetCategory, listBlogsEntryDtoMap); 		
			}
			renderRequest.setAttribute("maps", maps);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}