package MessageBorads.portlet.action;

import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import org.osgi.service.component.annotations.Component;

/**
 * @author Sergio González
 */
@Component(
	property = {
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS_ADMIN,
		"mvc.command.name=/message_boards_admin/view_banned_users"
	},
	service = MVCRenderCommand.class
)
public class MBAdminViewBannedUsersMVCRenderCommand
	extends BaseViewMVCRenderCommand {

	public MBAdminViewBannedUsersMVCRenderCommand() {
		super("/message_boards_admin/view_banned_users.jsp");
	}

}
