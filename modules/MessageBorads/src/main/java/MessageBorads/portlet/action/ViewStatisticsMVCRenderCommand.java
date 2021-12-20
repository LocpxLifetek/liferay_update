package MessageBorads.portlet.action;

import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import org.osgi.service.component.annotations.Component;

/**
 * @author Sergio González
 */
@Component(
	property = {
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS,
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS_ADMIN,
		"mvc.command.name=/message_boards/view_statistics"
	},
	service = MVCRenderCommand.class
)
public class ViewStatisticsMVCRenderCommand extends BaseViewMVCRenderCommand {

	public ViewStatisticsMVCRenderCommand() {
		super("/message_boards/view_statistics.jsp");
	}

}
