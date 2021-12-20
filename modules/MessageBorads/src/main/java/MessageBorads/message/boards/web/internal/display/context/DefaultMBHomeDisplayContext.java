package MessageBorads.message.boards.web.internal.display.context;

import com.liferay.message.boards.display.context.MBHomeDisplayContext;
import com.liferay.message.boards.model.MBCategory;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MessageBorads.message.boards.web.internal.display.context.util.MBRequestHelper;

/**
 * @author Iván Zaera
 */
public class DefaultMBHomeDisplayContext implements MBHomeDisplayContext {

	public DefaultMBHomeDisplayContext(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		_mbRequestHelper = new MBRequestHelper(httpServletRequest);
	}

	@Override
	public String getTitle() {
		MBCategory category = _mbRequestHelper.getCategory();

		if (category == null) {
			return "add-category";
		}

		return LanguageUtil.format(
			_mbRequestHelper.getRequest(), "edit-x", category.getName(), false);
	}

	@Override
	public UUID getUuid() {
		return _UUID;
	}

	private static final UUID _UUID = UUID.fromString(
		"478C53D5-EB19-4387-A95F-4475746D3E17");

	private final MBRequestHelper _mbRequestHelper;

}
