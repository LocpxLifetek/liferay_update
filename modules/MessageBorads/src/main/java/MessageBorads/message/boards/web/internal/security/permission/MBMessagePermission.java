package MessageBorads.message.boards.web.internal.security.permission;

import com.liferay.message.boards.model.MBMessage;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component(immediate = true, service = {})
public class MBMessagePermission {

	public static boolean contains(
			PermissionChecker permissionChecker, long messageId,
			String actionId)
		throws PortalException {

		return _messageModelResourcePermission.contains(
			permissionChecker, messageId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, MBMessage message,
			String actionId)
		throws PortalException {

		return _messageModelResourcePermission.contains(
			permissionChecker, message, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.message.boards.model.MBMessage)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<MBMessage> modelResourcePermission) {

		_messageModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<MBMessage>
		_messageModelResourcePermission;

}
