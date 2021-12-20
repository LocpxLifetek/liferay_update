package MessageBorads.message.boards.web.internal.upload;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ambrín Chaudhary
 */
@Component(service = TempImageMBUploadFileEntryHandler.class)
public class TempImageMBUploadFileEntryHandler
	extends BaseMBUploadFileEntryHandler {

	@Override
	protected String getParameterName() {
		return "imageSelectorFileName";
	}

}
