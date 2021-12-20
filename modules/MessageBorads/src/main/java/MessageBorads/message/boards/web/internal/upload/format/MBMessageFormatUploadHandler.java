package MessageBorads.message.boards.web.internal.upload.format;

import java.util.List;

import MessageBorads.message.boards.web.internal.util.MBAttachmentFileEntryReference;

/**
 * @author Alejandro Tardín
 */
public interface MBMessageFormatUploadHandler {

	public String replaceImageReferences(
		String body,
		List<MBAttachmentFileEntryReference> mbAttachmentFileEntryReferences);

}
