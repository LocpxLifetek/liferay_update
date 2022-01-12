package LoginPorlet.upgrade;

import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

import LoginPorlet.upgrade.v1_0_0.UpgradePortletId;

/**
 * @author Raymond Augé
 * @author Peter Fellwock
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class LoginWebUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.0", "1.0.0", new DummyUpgradeStep());

		registry.register("0.0.1", "1.0.0", new UpgradePortletId());
	}

}
