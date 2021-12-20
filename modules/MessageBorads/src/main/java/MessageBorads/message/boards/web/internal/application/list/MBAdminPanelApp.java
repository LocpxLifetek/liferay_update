package MessageBorads.message.boards.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=800",
		"panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT
	},
	service = PanelApp.class
)
public class MBAdminPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return MBPortletKeys.MESSAGE_BOARDS_ADMIN;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS_ADMIN + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}