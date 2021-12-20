package MessageBorads.portlet.action;

import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.message.boards.model.MBBan;
import com.liferay.message.boards.service.MBBanService;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael Young
 */
@Component(
	property = {
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS,
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS_ADMIN,
		"mvc.command.name=/message_boards/ban_user"
	},
	service = MVCActionCommand.class
)
public class BanUserMVCActionCommand extends BaseMVCActionCommand {

	protected void banUser(ActionRequest actionRequest) throws Exception {
		long banUserId = ParamUtil.getLong(actionRequest, "banUserId");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			MBBan.class.getName(), actionRequest);

		_mbBanService.addBan(banUserId, serviceContext);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals("ban")) {
				banUser(actionRequest);
			}
			else if (cmd.equals("unban")) {
				unbanUser(actionRequest);
			}
		}
		catch (PrincipalException principalException) {
			SessionErrors.add(actionRequest, principalException.getClass());

			actionResponse.setRenderParameter(
				"mvcPath", "/message_boards/error.jsp");
		}
	}

	protected void unbanUser(ActionRequest actionRequest) throws Exception {
		long[] banUserIds = null;

		long banUserId = ParamUtil.getLong(actionRequest, "banUserId");

		if (banUserId > 0) {
			banUserIds = new long[] {banUserId};
		}
		else {
			banUserIds = ParamUtil.getLongValues(actionRequest, "rowIds");
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			MBBan.class.getName(), actionRequest);

		for (long curBanUserId : banUserIds) {
			_mbBanService.deleteBan(curBanUserId, serviceContext);
		}
	}

	@Reference
	private MBBanService _mbBanService;

}
