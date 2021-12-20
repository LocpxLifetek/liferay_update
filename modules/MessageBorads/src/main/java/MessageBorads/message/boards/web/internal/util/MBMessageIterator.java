package MessageBorads.message.boards.web.internal.util;

import com.liferay.message.boards.model.MBMessage;

import java.util.Iterator;
import java.util.List;

/**
 * @author Sergio González
 */
public class MBMessageIterator implements Iterator<MBMessage> {

	public MBMessageIterator(List<MBMessage> messages, int from, int to) {
		_messages = messages;
		_from = from;
		_to = to;
	}

	public int getIndexPage() {
		return _from;
	}

	@Override
	public boolean hasNext() {
		if (_from < _to) {
			return true;
		}

		return false;
	}

	@Override
	public MBMessage next() {
		MBMessage message = _messages.get(_from);

		_from++;

		return message;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	private int _from;
	private final List<MBMessage> _messages;
	private final int _to;

}