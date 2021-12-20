package MessageBorads.portlet;

import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.BasePortletLayoutFinder;
import com.liferay.portal.kernel.portlet.PortletLayoutFinder;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(immediate = true, property = { "model.class.name=com.liferay.message.boards.model.MBCategory",
		"model.class.name=com.liferay.message.boards.model.MBMessage",
		"model.class.name=com.liferay.message.boards.model.MBThread" }, service = PortletLayoutFinder.class)
public class MBPortletLayoutFinder extends BasePortletLayoutFinder {

	@Override
	public Result find(ThemeDisplay themeDisplay, long groupId) throws PortalException {

		Layout layout = themeDisplay.getLayout();

		if (layout.isTypeControlPanel()) {
			return new ResultImpl(themeDisplay.getPlid(), MBPortletKeys.MESSAGE_BOARDS_ADMIN, true);
		}
		
		return super.find(themeDisplay, groupId);
	}

	@Override
	protected String[] getPortletIds() {
		return _PORTLET_IDS;
	}

	// Order is important. See LPS-23770.

	private static final String[] _PORTLET_IDS = { MBPortletKeys.MESSAGE_BOARDS_ADMIN, MBPortletKeys.MESSAGE_BOARDS };

}
