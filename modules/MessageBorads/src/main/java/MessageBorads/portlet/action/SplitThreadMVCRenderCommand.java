package MessageBorads.portlet.action;

import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(
	property = {
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS,
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS_ADMIN,
		"mvc.command.name=/message_boards/split_thread"
	},
	service = MVCRenderCommand.class
)
public class SplitThreadMVCRenderCommand extends GetMessageMVCRenderCommand {

	@Override
	protected String getPath() {
		return "/message_boards/split_thread.jsp";
	}

}
