package MessageBorads.message.boards.web.internal.util;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.theme.ThemeDisplay;

/**
 * @author Sergio González
 */
public class MBRSSUtil {

	public static String getRSSURL(
		long plid, long categoryId, long threadId, long userId,
		ThemeDisplay themeDisplay) {

		StringBundler sb = new StringBundler(10);

		sb.append(themeDisplay.getPortalURL());
		sb.append(themeDisplay.getPathMain());
		sb.append("/message_boards/rss?plid=");
		sb.append(plid);

		if (categoryId > 0) {
			sb.append("&mbCategoryId=");
			sb.append(categoryId);
		}
		else {
			sb.append("&groupId=");
			sb.append(themeDisplay.getScopeGroupId());
		}

		if (threadId > 0) {
			sb.append("&threadId=");
			sb.append(threadId);
		}

		if (userId > 0) {
			sb.append("&userId=");
			sb.append(userId);
		}

		return sb.toString();
	}

}