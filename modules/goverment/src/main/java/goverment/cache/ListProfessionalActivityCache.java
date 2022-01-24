package goverment.cache;

import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;

import goverment.sql.FeatureNewsSql;

public class ListProfessionalActivityCache implements WebCacheItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public Object convert(String key) throws WebCacheException {
		String[] keyValue=key.split("\\,");
		int i=0;
		String groupId=null;
		for (String string : keyValue) {
			i++;
			if(i==2) {
				groupId=string;
			}
		}
		long groupIdCategory = Long.parseLong(groupId);
		return new FeatureNewsSql().findAllBlogsByIdCategory(groupIdCategory,"521e8b35-2c5a-db70-f646-81c0c3912ee5");
	}

	@Override
	public long getRefreshTime() {
		// TODO Auto-generated method stub
		return _REFRESH_TIME;
	}
	private static final long _REFRESH_TIME = Time.SECOND * 10;
}
