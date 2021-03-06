package MyCustomLoginPorlet.portlet;

import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManagerUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		property = {
			"javax.portlet.name=MyCustomLoginPorletPortlet",
			"mvc.command.name=/login/login"
		},
		service = MVCActionCommand.class
	)
public class MyLoginMVCActionCommand extends BaseMVCActionCommand{

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		// TODO Auto-generated method stub
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
				WebKeys.THEME_DISPLAY);
			
			HttpServletRequest request = PortalUtil.getOriginalServletRequest(
				PortalUtil.getHttpServletRequest(actionRequest));
			
			HttpServletResponse response = PortalUtil.getHttpServletResponse(
				actionResponse);

			String login = ParamUtil.getString(actionRequest, "login");
			String password = actionRequest.getParameter("password");
			boolean rememberMe = ParamUtil.getBoolean(actionRequest, "rememberMe");
			String authType = CompanyConstants.AUTH_TYPE_EA;
			
			AuthenticatedSessionManagerUtil.login(
				request, response, login, password, rememberMe, authType);
			
			actionResponse.sendRedirect(themeDisplay.getPathMain());
	}

}
