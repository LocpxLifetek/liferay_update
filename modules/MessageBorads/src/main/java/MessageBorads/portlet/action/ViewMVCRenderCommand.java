package MessageBorads.portlet.action;

import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS,
		"mvc.command.name=/", "mvc.command.name=/message_boards/view",
		"mvc.command.name=/message_boards/view_category",
		"mvc.command.name=/message_boards/view_my_posts",
		"mvc.command.name=/message_boards/view_my_subscriptions",
		"mvc.command.name=/message_boards/view_recent_posts"
	},
	service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand extends BaseViewMVCRenderCommand {

	public ViewMVCRenderCommand() {
		super("/message_boards/view.jsp");
	}

}
