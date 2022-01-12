package LoginPorlet.action;

import com.liferay.login.web.constants.LoginPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.JavaConstants;

import javax.portlet.PortletConfig;
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
		"mvc.command.name=/login/create_anonymous_account"
	},
	service = MVCRenderCommand.class
)
public class CreateAnonymousAccountMVCRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		PortletConfig portletConfig = (PortletConfig)renderRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		String portletName = portletConfig.getPortletName();

		if (!portletName.equals(LoginPortletKeys.FAST_LOGIN)) {
			return "/login.jsp";
		}

		return "/create_anonymous_account.jsp";
	}

}