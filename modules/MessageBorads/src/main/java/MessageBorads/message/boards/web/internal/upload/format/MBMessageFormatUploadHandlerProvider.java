package MessageBorads.message.boards.web.internal.upload.format;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Alejandro Tardín
 */
@Component(service = MBMessageFormatUploadHandlerProvider.class)
public class MBMessageFormatUploadHandlerProvider {

	public MBMessageFormatUploadHandler provide(String format) {
		return _serviceTrackerMap.getService(format);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, MBMessageFormatUploadHandler.class, "format");
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private ServiceTrackerMap<String, MBMessageFormatUploadHandler>
		_serviceTrackerMap;

}
