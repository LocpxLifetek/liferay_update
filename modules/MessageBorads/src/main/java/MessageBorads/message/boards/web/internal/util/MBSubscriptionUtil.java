package MessageBorads.message.boards.web.internal.util;

import com.liferay.message.boards.model.MBCategory;
import com.liferay.message.boards.model.MBThread;
import com.liferay.subscription.model.Subscription;
import com.liferay.subscription.service.SubscriptionLocalServiceUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Adolfo Pérez
 */
public class MBSubscriptionUtil {

	public static Set<Long> getCategorySubscriptionClassPKs(long userId) {
		List<Subscription> subscriptions =
			SubscriptionLocalServiceUtil.getUserSubscriptions(
				userId, MBCategory.class.getName());

		Set<Long> classPKs = new HashSet<>();

		for (Subscription subscription : subscriptions) {
			classPKs.add(subscription.getClassPK());
		}

		return classPKs;
	}

	public static Set<Long> getThreadSubscriptionClassPKs(long userId) {
		List<Subscription> subscriptions =
			SubscriptionLocalServiceUtil.getUserSubscriptions(
				userId, MBThread.class.getName());

		Set<Long> classPKs = new HashSet<>();

		for (Subscription subscription : subscriptions) {
			classPKs.add(subscription.getClassPK());
		}

		return classPKs;
	}

}