package LoginPorlet.servlet.taglib.include;

import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerList;
import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerListFactory;
import com.liferay.taglib.include.PageInclude;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * @author Shuyang Zhou
 */
public class NavigationPageIncludeUtil {

	public static void includePost(PageContext pageContext)
		throws JspException {

		for (PageInclude pageInclude : _postPageIncludes) {
			pageInclude.include(pageContext);
		}
	}

	public static void includePre(PageContext pageContext) throws JspException {
		for (PageInclude pageInclude : _prePageIncludes) {
			pageInclude.include(pageContext);
		}
	}

	private static final ServiceTrackerList<PageInclude, PageInclude>
		_postPageIncludes;
	private static final ServiceTrackerList<PageInclude, PageInclude>
		_prePageIncludes;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			NavigationPageIncludeUtil.class);

		BundleContext bundleContext = bundle.getBundleContext();

		_postPageIncludes = ServiceTrackerListFactory.open(
			bundleContext, PageInclude.class,
			"(login.web.navigation.position=post)");
		_prePageIncludes = ServiceTrackerListFactory.open(
			bundleContext, PageInclude.class,
			"(login.web.navigation.position=pre)");
	}

}