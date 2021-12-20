package MessageBorads.message.boards.web.internal.ratings.definition;

import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.ratings.kernel.RatingsType;
import com.liferay.ratings.kernel.definition.PortletRatingsDefinition;

import org.osgi.service.component.annotations.Component;

/**
 * @author Roberto Díaz
 */
@Component(
	property = "model.class.name=com.liferay.message.boards.model.MBMessage",
	service = PortletRatingsDefinition.class
)
public class MBPortletRatingsDefinition implements PortletRatingsDefinition {

	@Override
	public RatingsType getDefaultRatingsType() {
		return RatingsType.THUMBS;
	}

	@Override
	public String getPortletId() {
		return MBPortletKeys.MESSAGE_BOARDS;
	}

}
