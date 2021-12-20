package goverment.cache;

import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;

import org.osgi.service.component.annotations.Reference;

import goverment.sql.FeatureNewsSql;

public class FeatureCache implements WebCacheItem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public Object convert(String key) throws WebCacheException {
		// TODO Auto-generated method stub
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
		return new FeatureNewsSql().findAllBlogsByIdCategory(groupIdCategory);
	}

	@Override
	public long getRefreshTime() {
		// TODO Auto-generated method stub
		return _REFRESH_TIME;
	}
	private static final long _REFRESH_TIME = Time.SECOND * 30;
}
