package MessageBorads.message.boards.web.internal.notifications;

import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.portal.kernel.notifications.BaseModelUserNotificationHandler;
import com.liferay.portal.kernel.notifications.UserNotificationHandler;

import org.osgi.service.component.annotations.Component;

/**
 * @author Sergio González
 * @author Roberto Díaz
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS,
	service = UserNotificationHandler.class
)
public class MBUserNotificationHandler
	extends BaseModelUserNotificationHandler {

	public MBUserNotificationHandler() {
		setPortletId(MBPortletKeys.MESSAGE_BOARDS);
	}

}
