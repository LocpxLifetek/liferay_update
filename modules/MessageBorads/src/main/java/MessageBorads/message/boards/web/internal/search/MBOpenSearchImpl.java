package MessageBorads.message.boards.web.internal.search;

import com.liferay.message.boards.model.MBMessage;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.HitsOpenSearchImpl;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.OpenSearch;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(service = OpenSearch.class)
public class MBOpenSearchImpl extends HitsOpenSearchImpl {

	public static final String TITLE = "Liferay Message Boards Search: ";

	@Override
	public String getClassName() {
		return MBMessage.class.getName();
	}

	@Override
	public Indexer<MBMessage> getIndexer() {
		return IndexerRegistryUtil.getIndexer(MBMessage.class);
	}

	@Override
	public String getSearchPath() {
		return StringPool.BLANK;
	}

	@Override
	public String getTitle(String keywords) {
		return TITLE + keywords;
	}

}
