package MessageBorads.message.boards.web.internal.display.context;

import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.message.boards.model.MBCategory;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Objects;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import MessageBorads.message.boards.web.internal.display.MBCategoryDisplay;
import MessageBorads.message.boards.web.internal.util.MBUtil;

/**
 * @author Adolfo Pérez
 */
public class MBViewStatisticsDisplayContext {

	public MBViewStatisticsDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;

		_themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public MBCategoryDisplay getMBCategoryDisplay() {
		return new MBCategoryDisplay(
			_themeDisplay.getScopeGroupId(), _getCategoryId());
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/message_boards/view_statistics");
		portletURL.setParameter(
			"mbCategoryId", String.valueOf(_getCategoryId()));

		return portletURL;
	}

	public boolean isMBAdmin() {
		PortletDisplay portletDisplay = _themeDisplay.getPortletDisplay();

		if (Objects.equals(
				portletDisplay.getPortletName(),
				MBPortletKeys.MESSAGE_BOARDS_ADMIN)) {

			return true;
		}

		return false;
	}

	private long _getCategoryId() {
		if (_categoryId != null) {
			return _categoryId;
		}

		if (!isMBAdmin()) {
			MBCategory category = (MBCategory)_renderRequest.getAttribute(
				WebKeys.MESSAGE_BOARDS_CATEGORY);

			_categoryId = MBUtil.getCategoryId(_renderRequest, category);
		}
		else {
			_categoryId = GetterUtil.getLong(
				_renderRequest.getAttribute("view.jsp-categoryId"));
		}

		return _categoryId;
	}

	private Long _categoryId;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final ThemeDisplay _themeDisplay;

}
