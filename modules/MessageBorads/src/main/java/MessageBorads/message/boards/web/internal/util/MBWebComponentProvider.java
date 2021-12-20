package MessageBorads.message.boards.web.internal.util;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import MessageBorads.message.boards.web.internal.display.context.MBDisplayContextProvider;

/**
 * @author Iván Zaera
 */
@Component(immediate = true, service = {})
public class MBWebComponentProvider {

	public static MBWebComponentProvider getMBWebComponentProvider() {
		return _mbWebComponentProvider;
	}

	public MBDisplayContextProvider getMBDisplayContextProvider() {
		return _mbDisplayContextProvider;
	}

	@Reference(unbind = "-")
	public void setMBDisplayContextProvider(
		MBDisplayContextProvider mbDisplayContextProvider) {

		_mbDisplayContextProvider = mbDisplayContextProvider;
	}

	@Activate
	protected void activate() {
		_mbWebComponentProvider = this;
	}

	@Deactivate
	protected void deactivate() {
		_mbWebComponentProvider = null;
	}

	private static MBWebComponentProvider _mbWebComponentProvider;

	private MBDisplayContextProvider _mbDisplayContextProvider;

}
