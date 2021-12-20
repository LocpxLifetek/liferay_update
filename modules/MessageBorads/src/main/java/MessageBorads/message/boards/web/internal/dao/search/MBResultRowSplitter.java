package MessageBorads.message.boards.web.internal.dao.search;

import com.liferay.message.boards.model.MBCategory;
import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.dao.search.ResultRowSplitter;
import com.liferay.portal.kernel.dao.search.ResultRowSplitterEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergio González
 */
public class MBResultRowSplitter implements ResultRowSplitter {

	@Override
	public List<ResultRowSplitterEntry> split(List<ResultRow> resultRows) {
		List<ResultRowSplitterEntry> resultRowSplitterEntries =
			new ArrayList<>();

		List<ResultRow> categoryResultRows = new ArrayList<>();
		List<ResultRow> threadResultRows = new ArrayList<>();

		for (ResultRow resultRow : resultRows) {
			Object object = resultRow.getObject();

			if (object instanceof MBCategory) {
				categoryResultRows.add(resultRow);
			}
			else {
				threadResultRows.add(resultRow);
			}
		}

		if (!categoryResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry("categories", categoryResultRows));
		}

		if (!threadResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry("threads", threadResultRows));
		}

		return resultRowSplitterEntries;
	}

}
