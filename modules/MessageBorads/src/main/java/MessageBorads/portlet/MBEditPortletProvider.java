package MessageBorads.portlet;

import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.portal.kernel.portlet.BasePortletProvider;
import com.liferay.portal.kernel.portlet.EditPortletProvider;
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
		"model.class.name=com.liferay.message.boards.model.MBThread",
		"service.ranking:Integer=100"
	},
	service = {EditPortletProvider.class, ViewPortletProvider.class}
)
public class MBEditPortletProvider
	extends BasePortletProvider
	implements EditPortletProvider, ViewPortletProvider {

	@Override
	public String getPortletName() {
		return MBPortletKeys.MESSAGE_BOARDS;
	}

}
