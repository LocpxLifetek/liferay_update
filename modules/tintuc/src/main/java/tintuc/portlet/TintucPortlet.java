package tintuc.portlet;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

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

import tintuc.constants.TintucPortletKeys;
import tintuc.dto.CategoryDto;

/**
 * @author java05
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Tintuc", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + TintucPortletKeys.TINTUC,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class TintucPortlet extends MVCPortlet {
	
	private List<CategoryDto> findAllCategoryByParentCategory(){
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<CategoryDto> listCategoryDto=new ArrayList<>();
			con=DataAccess.getConnection();
			statement=con.prepareStatement("select ac.categoryid as id, ac.name as name from assetcategory ac where  ac.treepath like '/108693/%' and ac.parentcategoryid='108693' order by ac.modifieddate desc");
			rs=statement.executeQuery();
			while(rs.next()) {
				CategoryDto categoryDto=new CategoryDto();
				Integer id=rs.getInt("id");
				String name=rs.getString("name");
				categoryDto.setId(id);
				categoryDto.setName(name);
				listCategoryDto.add(categoryDto);
			}
			return listCategoryDto;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
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
			
			List<CategoryDto> listCategoryDto=findAllCategoryByParentCategory();
			List<AssetCategory> listAssetCategory1=new ArrayList<>();
			for (CategoryDto categoryDto : listCategoryDto) {
				List<AssetCategory> listAssetCategory= AssetCategoryLocalServiceUtil.getChildCategories(categoryDto.getId());
				if(listAssetCategory.size() >0) {
					for (AssetCategory assetCategory : listAssetCategory) {
						listAssetCategory1.add(assetCategory);
					}
				}
			}
			renderRequest.setAttribute("listAssetCategory3", listAssetCategory1);
			renderRequest.setAttribute("listAssetCategory", listCategoryDto);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		super.doView(renderRequest, renderResponse);
	}
}