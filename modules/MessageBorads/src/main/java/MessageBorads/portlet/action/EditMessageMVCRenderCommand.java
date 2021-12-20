package MessageBorads.portlet.action;

import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.message.boards.model.MBMessage;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	property = {
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS,
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS_ADMIN,
		"mvc.command.name=/message_boards/edit_message"
	},
	service = MVCRenderCommand.class
)
public class EditMessageMVCRenderCommand extends GetMessageMVCRenderCommand {

	@Override
	protected void checkPermissions(
			PermissionChecker permissionChecker, MBMessage message)
		throws PortalException {

		_mbMessageModelResourcePermission.check(
			permissionChecker, message, ActionKeys.UPDATE);
	}

	@Override
	protected String getPath() {
		return "/message_boards/edit_message.jsp";
	}

	@Reference(
		target = "(model.class.name=com.liferay.message.boards.model.MBMessage)"
	)
	private volatile ModelResourcePermission<MBMessage>
		_mbMessageModelResourcePermission;

}
