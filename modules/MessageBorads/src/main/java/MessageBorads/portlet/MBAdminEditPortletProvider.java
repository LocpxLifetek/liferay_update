package MessageBorads.portlet;

import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.portal.kernel.portlet.BasePortletProvider;
import com.liferay.portal.kernel.portlet.EditPortletProvider;
import com.liferay.portal.kernel.portlet.ManagePortletProvider;
import com.liferay.portal.kernel.portlet.ViewPortletProvider;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(
	immediate = true,
	property = {
		"model.class.name=com.liferay.message.boards.model.MBCategory",
		"model.class.name=com.liferay.message.boards.model.MBDiscussion",
		"model.class.name=com.liferay.message.boards.model.MBMessage",
		"model.class.name=com.liferay.message.boards.model.MBThread"
	},
	service = {
		EditPortletProvider.class, ManagePortletProvider.class,
		ViewPortletProvider.class
	}
)
public class MBAdminEditPortletProvider
	extends BasePortletProvider
	implements EditPortletProvider, ManagePortletProvider, ViewPortletProvider {

	@Override
	public String getPortletName() {
		return MBPortletKeys.MESSAGE_BOARDS_ADMIN;
	}

}
