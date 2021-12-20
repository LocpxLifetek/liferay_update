package MessageBorads.message.boards.web.internal.upload;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(service = TempAttachmentMBUploadFileEntryHandler.class)
public class TempAttachmentMBUploadFileEntryHandler
	extends BaseMBUploadFileEntryHandler {

	@Override
	protected String getParameterName() {
		return "file";
	}

}
