package MessageBorads.portlet.action;

import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS_ADMIN,
		"mvc.command.name=/", "mvc.command.name=/message_boards/view",
		"mvc.command.name=/message_boards/view_category",
		"mvc.command.name=/message_boards/view_recent_posts",
		"mvc.command.name=/message_boards_admin/search"
	},
	service = MVCRenderCommand.class
)
public class MBAdminViewMVCRenderCommand extends BaseViewMVCRenderCommand {

	public MBAdminViewMVCRenderCommand() {
		super("/message_boards_admin/view.jsp");
	}

}
