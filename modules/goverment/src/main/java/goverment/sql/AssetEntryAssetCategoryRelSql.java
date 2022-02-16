package goverment.sql;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import goverment.dto.AssetEntryAssetCategoryRelDto;

public class AssetEntryAssetCategoryRelSql {
	public List<AssetEntryAssetCategoryRelDto> listAssetEntryAssetCategoryRelDtos(long entryId,Integer page, Integer size) throws SQLException{
		PreparedStatement statement = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			List<AssetEntryAssetCategoryRelDto> listAssetEntryAssetCategoryRelDtos=new ArrayList<>();
			con = DataAccess.getConnection();
			statement = con.prepareStatement("select aeac.assetentryid as entryId from AssetEntryAssetCategoryRel aeac where aeac.assetcategoryid=? order by aeac.assetentryassetcategoryrelid desc OFFSET (?-1)*? ROWS FETCH NEXT ? ROWS ONLY");
			
			statement.setLong(1, entryId);
			statement.setInt(2, page);
			statement.setInt(3, size);
			statement.setInt(4, size);
			rs = statement.executeQuery();
			while (rs.next()) {
				AssetEntryAssetCategoryRelDto assetEntryAssetCategoryRelDto=new AssetEntryAssetCategoryRelDto();
				long assetEntryId=rs.getLong("entryId");
				assetEntryAssetCategoryRelDto.setAssetEntryId(assetEntryId);
				listAssetEntryAssetCategoryRelDtos.add(assetEntryAssetCategoryRelDto);
			}
			return listAssetEntryAssetCategoryRelDtos;
		} catch (Exception e) {
			// TODO: handle exception
						e.printStackTrace();
						return null;
		}finally {
			if (statement != null) {
				statement.close();
			}
			if (con != null) {
				con.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
	}
}
