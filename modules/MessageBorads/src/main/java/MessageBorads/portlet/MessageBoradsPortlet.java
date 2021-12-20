package MessageBorads.portlet;

import com.liferay.asset.constants.AssetWebKeys;
import com.liferay.asset.util.AssetHelper;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.trash.TrashHelper;
import com.liferay.trash.util.TrashWebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import MessageBorads.constants.MessageBoradsPortletKeys;

/**
 * @author Dell
 */
@Component(
	immediate = true,
	property = {
			"com.liferay.portlet.add-default-resource=true",
			"com.liferay.portlet.application-type=full-page-application",
			"com.liferay.portlet.application-type=widget",
			"com.liferay.portlet.css-class-wrapper=portlet-message-boards",
			"com.liferay.portlet.display-category=category.collaboration",
			"com.liferay.portlet.header-portlet-css=/message_boards/css/main.css",
			"com.liferay.portlet.icon=/message_boards/icons/message_boards.png",
			"com.liferay.portlet.preferences-owned-by-group=true",
			"com.liferay.portlet.private-request-attributes=false",
			"com.liferay.portlet.private-session-attributes=false",
			"com.liferay.portlet.render-weight=50",
			"com.liferay.portlet.scopeable=true",
			"com.liferay.portlet.struts-path=message_boards",
			"com.liferay.portlet.use-default-template=true",
			"javax.portlet.display-name=Message Boards",
			"javax.portlet.expiration-cache=0",
			"javax.portlet.init-param.always-display-default-configuration-icons=true",
			"javax.portlet.init-param.portlet-title-based-navigation=false",
			"javax.portlet.init-param.template-path=/META-INF/resources/",
			"javax.portlet.name=" + MessageBoradsPortletKeys.MESSAGEBORADS,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user",
			"javax.portlet.supported-public-render-parameter=tag"
	},
	service = Portlet.class
)
public class MessageBoradsPortlet extends MVCPortlet {
	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(AssetWebKeys.ASSET_HELPER, _assetHelper);
		renderRequest.setAttribute(TrashWebKeys.TRASH_HELPER, _trashHelper);

		super.render(renderRequest, renderResponse);
	}

	@Reference(
		target = "(&(release.bundle.symbolic.name=com.liferay.message.boards.web)(&(release.schema.version>=1.0.0)(!(release.schema.version>=2.0.0))))",
		unbind = "-"
	)
	protected void setRelease(Release release) {
	}

	@Reference
	private AssetHelper _assetHelper;

	@Reference
	private TrashHelper _trashHelper;
}