package LoginPorlet.upgrade.v1_0_0;

import com.liferay.login.web.constants.LoginPortletKeys;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;

/**
 * @author Peter Fellwock
 */
public class UpgradePortletId extends BaseUpgradePortletId {

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {
			{"58", LoginPortletKeys.LOGIN}, {"164", LoginPortletKeys.FAST_LOGIN}
		};
	}

}