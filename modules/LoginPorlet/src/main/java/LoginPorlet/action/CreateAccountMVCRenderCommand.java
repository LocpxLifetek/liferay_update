package LoginPorlet.action;

import com.liferay.login.web.constants.LoginPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Peter Fellwock
 */
@Component(
	property = {
		"javax.portlet.name=" + LoginPortletKeys.FAST_LOGIN,
		"javax.portlet.name=" + LoginPortletKeys.LOGIN,
		"mvc.command.name=/login/create_account"
	},
	service = MVCRenderCommand.class
)
public class CreateAccountMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		return "/create_account.jsp";
	}

}
