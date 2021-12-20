package MessageBorads.portlet.action;

import com.liferay.asset.kernel.exception.AssetCategoryException;
import com.liferay.asset.kernel.exception.AssetTagException;
import com.liferay.captcha.configuration.CaptchaConfiguration;
import com.liferay.captcha.util.CaptchaUtil;
import com.liferay.document.library.kernel.antivirus.AntivirusScannerException;
import com.liferay.document.library.kernel.exception.DuplicateFileEntryException;
import com.liferay.document.library.kernel.exception.FileExtensionException;
import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.message.boards.constants.MBCategoryConstants;
import com.liferay.message.boards.constants.MBMessageConstants;
import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.message.boards.exception.LockedThreadException;
import com.liferay.message.boards.exception.MessageBodyException;
import com.liferay.message.boards.exception.MessageSubjectException;
import com.liferay.message.boards.exception.NoSuchMessageException;
import com.liferay.message.boards.exception.RequiredMessageException;
import com.liferay.message.boards.model.MBCategory;
import com.liferay.message.boards.model.MBMessage;
import com.liferay.message.boards.service.MBCategoryService;
import com.liferay.message.boards.service.MBMessageLocalService;
import com.liferay.message.boards.service.MBMessageService;
import com.liferay.message.boards.service.MBThreadLocalService;
import com.liferay.message.boards.service.MBThreadService;
import com.liferay.message.boards.settings.MBGroupServiceSettings;
import com.liferay.portal.kernel.captcha.CaptchaConfigurationException;
import com.liferay.portal.kernel.captcha.CaptchaException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.LiferayActionResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepository;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.sanitizer.SanitizerException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.upload.LiferayFileItemException;
import com.liferay.portal.kernel.upload.UploadException;
import com.liferay.portal.kernel.upload.UploadRequestSizeException;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.upload.UniqueFileNameProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import MessageBorads.message.boards.web.internal.upload.format.MBMessageFormatUploadHandler;
import MessageBorads.message.boards.web.internal.upload.format.MBMessageFormatUploadHandlerProvider;
import MessageBorads.message.boards.web.internal.util.MBAttachmentFileEntryReference;
import MessageBorads.message.boards.web.internal.util.MBAttachmentFileEntryUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Daniel Sanz
 * @author Shuyang Zhou
 */
@Component(
	property = {
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS,
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS_ADMIN,
		"mvc.command.name=/message_boards/edit_message"
	},
	service = MVCActionCommand.class
)
public class EditMessageMVCActionCommand extends BaseMVCActionCommand {

	protected void addAnswer(ActionRequest actionRequest) throws Exception {
		long messageId = ParamUtil.getLong(actionRequest, "messageId");

		_mbMessageService.updateAnswer(messageId, true, false);
	}

	protected void deleteAnswer(ActionRequest actionRequest) throws Exception {
		long messageId = ParamUtil.getLong(actionRequest, "messageId");

		_mbMessageService.updateAnswer(messageId, false, false);
	}

	protected void deleteMessage(ActionRequest actionRequest) throws Exception {
		long messageId = ParamUtil.getLong(actionRequest, "messageId");

		_mbMessageService.deleteMessage(messageId);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		MBMessage message = null;

		try {
			UploadException uploadException =
				(UploadException)actionRequest.getAttribute(
					WebKeys.UPLOAD_EXCEPTION);

			if (uploadException != null) {
				Throwable throwable = uploadException.getCause();

				if (uploadException.isExceededFileSizeLimit()) {
					throw new FileSizeException(throwable);
				}

				if (uploadException.isExceededLiferayFileItemSizeLimit()) {
					throw new LiferayFileItemException(throwable);
				}

				if (uploadException.isExceededUploadRequestSizeLimit()) {
					throw new UploadRequestSizeException(throwable);
				}

				throw new PortalException(throwable);
			}
			else if (cmd.equals(Constants.ADD) ||
					 cmd.equals(Constants.UPDATE)) {

				message = TransactionInvokerUtil.invoke(
					_transactionConfig,
					() -> updateMessage(actionRequest, actionResponse));
			}
			else if (cmd.equals(Constants.ADD_ANSWER)) {
				addAnswer(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteMessage(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE_ANSWER)) {
				deleteAnswer(actionRequest);
			}
			else if (cmd.equals(Constants.LOCK)) {
				lockThreads(actionRequest);
			}
			else if (cmd.equals(Constants.SUBSCRIBE)) {
				subscribeMessage(actionRequest);
			}
			else if (cmd.equals(Constants.UNLOCK)) {
				unlockThreads(actionRequest);
			}
			else if (cmd.equals(Constants.UNSUBSCRIBE)) {
				unsubscribeMessage(actionRequest);
			}

			if (Validator.isNotNull(cmd)) {
				WindowState windowState = actionRequest.getWindowState();

				if (!windowState.equals(LiferayWindowState.POP_UP)) {
					String redirect = getRedirect(
						actionRequest, actionResponse, message);

					sendRedirect(actionRequest, actionResponse, redirect);
				}
				else {
					String redirect = _portal.escapeRedirect(
						ParamUtil.getString(actionRequest, "redirect"));

					if (Validator.isNotNull(redirect)) {
						actionResponse.sendRedirect(redirect);
					}
				}
			}
		}
		catch (NoSuchMessageException | PrincipalException |
			   RequiredMessageException exception) {

			SessionErrors.add(actionRequest, exception.getClass());

			actionResponse.setRenderParameter(
				"mvcPath", "/message_boards/error.jsp");
		}
		catch (AntivirusScannerException | CaptchaException |
			   DuplicateFileEntryException | FileExtensionException |
			   FileNameException | FileSizeException |
			   LiferayFileItemException | LockedThreadException |
			   MessageBodyException | MessageSubjectException |
			   SanitizerException | UploadRequestSizeException exception) {

			if (exception instanceof AntivirusScannerException) {
				SessionErrors.add(
					actionRequest, exception.getClass(), exception);
			}
			else {
				SessionErrors.add(actionRequest, exception.getClass());
			}
		}
		catch (AssetCategoryException | AssetTagException exception) {
			SessionErrors.add(actionRequest, exception.getClass(), exception);
		}
		catch (Exception exception) {
			Throwable throwable = exception.getCause();

			if (throwable instanceof SanitizerException) {
				SessionErrors.add(actionRequest, SanitizerException.class);
			}
			else {
				throw exception;
			}
		}
		catch (Throwable throwable) {
			_log.error("Unable to process action", throwable);

			actionResponse.setRenderParameter(
				"mvcPath", "/message_boards/error.jsp");

			hideDefaultSuccessMessage(actionRequest);
		}
	}

	protected CaptchaConfiguration getCaptchaConfiguration()
		throws CaptchaConfigurationException {

		try {
			return _configurationProvider.getSystemConfiguration(
				CaptchaConfiguration.class);
		}
		catch (Exception exception) {
			throw new CaptchaConfigurationException(exception);
		}
	}

	protected String getRedirect(
		ActionRequest actionRequest, ActionResponse actionResponse,
		MBMessage message) {

		if (message == null) {
			return ParamUtil.getString(actionRequest, "redirect");
		}

		int workflowAction = ParamUtil.getInteger(
			actionRequest, "workflowAction", WorkflowConstants.ACTION_PUBLISH);

		if (workflowAction == WorkflowConstants.ACTION_SAVE_DRAFT) {
			return getSaveAndContinueRedirect(
				actionRequest, actionResponse, message);
		}

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		if (Validator.isNotNull(portletResource)) {
			return ParamUtil.getString(actionRequest, "redirect");
		}

		LiferayActionResponse liferayActionResponse =
			(LiferayActionResponse)actionResponse;

		PortletURL portletURL = liferayActionResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/message_boards/view_message");
		portletURL.setParameter(
			"messageId", String.valueOf(message.getMessageId()));

		return portletURL.toString();
	}

	protected String getSaveAndContinueRedirect(
		ActionRequest actionRequest, ActionResponse actionResponse,
		MBMessage message) {

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		boolean preview = ParamUtil.getBoolean(actionRequest, "preview");

		LiferayActionResponse liferayActionResponse =
			(LiferayActionResponse)actionResponse;

		PortletURL portletURL = liferayActionResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/message_boards/edit_message");
		portletURL.setParameter("redirect", redirect);
		portletURL.setParameter(
			"portletResource",
			ParamUtil.getString(actionRequest, "portletResource"));
		portletURL.setParameter(
			"messageId", String.valueOf(message.getMessageId()));
		portletURL.setParameter("preview", String.valueOf(preview));

		return portletURL.toString();
	}

	protected void lockThreads(ActionRequest actionRequest) throws Exception {
		long threadId = ParamUtil.getLong(actionRequest, "threadId");

		if (threadId > 0) {
			_mbThreadService.lockThread(threadId);
		}
		else {
			long[] threadIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "threadIds"), 0L);

			for (long curThreadId : threadIds) {
				_mbThreadService.lockThread(curThreadId);
			}
		}
	}

	protected void subscribeMessage(ActionRequest actionRequest)
		throws Exception {

		long messageId = ParamUtil.getLong(actionRequest, "messageId");

		_mbMessageService.subscribeMessage(messageId);
	}

	protected void unlockThreads(ActionRequest actionRequest) throws Exception {
		long threadId = ParamUtil.getLong(actionRequest, "threadId");

		if (threadId > 0) {
			_mbThreadService.unlockThread(threadId);
		}
		else {
			long[] threadIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "threadIds"), 0L);

			for (long curThreadId : threadIds) {
				_mbThreadService.unlockThread(curThreadId);
			}
		}
	}

	protected void unsubscribeMessage(ActionRequest actionRequest)
		throws Exception {

		long messageId = ParamUtil.getLong(actionRequest, "messageId");

		_mbMessageService.unsubscribeMessage(messageId);
	}

	protected MBMessage updateMessage(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long messageId = ParamUtil.getLong(actionRequest, "messageId");
		long categoryId = ParamUtil.getLong(actionRequest, "mbCategoryId");
		long threadId = ParamUtil.getLong(actionRequest, "threadId");
		long parentMessageId = ParamUtil.getLong(
			actionRequest, "parentMessageId");
		String subject = ParamUtil.getString(actionRequest, "subject");
		String body = ParamUtil.getString(actionRequest, "body");

		MBGroupServiceSettings mbGroupServiceSettings =
			MBGroupServiceSettings.getInstance(themeDisplay.getSiteGroupId());

		List<ObjectValuePair<String, InputStream>> inputStreamOVPs =
			new ArrayList<>(5);

		try {
			List<FileEntry> tempFileEntries = _populateInputStreamOVPs(
				actionRequest, messageId, inputStreamOVPs);

			boolean question = ParamUtil.getBoolean(actionRequest, "question");

			if (categoryId != MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {
				MBCategory category = _mbCategoryService.getCategory(
					categoryId);

				String displayStyle = category.getDisplayStyle();

				if (displayStyle.equals("question")) {
					question = true;
				}
			}

			double priority = ParamUtil.getDouble(actionRequest, "priority");
			boolean allowPingbacks = ParamUtil.getBoolean(
				actionRequest, "allowPingbacks");

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				MBMessage.class.getName(), actionRequest);

			boolean preview = ParamUtil.getBoolean(actionRequest, "preview");

			serviceContext.setAttribute("preview", preview);

			MBMessage message = null;

			if (messageId <= 0) {
				CaptchaConfiguration captchaConfiguration =
					getCaptchaConfiguration();

				if (captchaConfiguration.
						messageBoardsEditMessageCaptchaEnabled()) {

					CaptchaUtil.check(actionRequest);
				}

				boolean anonymous = ParamUtil.getBoolean(
					actionRequest, "anonymous");

				if (threadId <= 0) {

					// Post new thread

					message = _mbMessageService.addMessage(
						themeDisplay.getScopeGroupId(), categoryId, subject,
						body, mbGroupServiceSettings.getMessageFormat(),
						inputStreamOVPs, anonymous, priority, allowPingbacks,
						serviceContext);

					if (question) {
						_mbThreadLocalService.updateQuestion(
							message.getThreadId(), true);
					}
				}
				else {

					// Post reply

					message = _mbMessageService.addMessage(
						parentMessageId, subject, body,
						mbGroupServiceSettings.getMessageFormat(),
						inputStreamOVPs, anonymous, priority, allowPingbacks,
						serviceContext);
				}

				MBMessageFormatUploadHandler formatHandler =
					_formatHandlerProvider.provide(message.getFormat());

				if (formatHandler != null) {
					List<FileEntry> tempMBAttachmentFileEntries =
						MBAttachmentFileEntryUtil.
							getTempMBAttachmentFileEntries(message.getBody());

					if (!tempMBAttachmentFileEntries.isEmpty()) {
						body = _addBodyAttachmentTempFiles(
							tempMBAttachmentFileEntries, themeDisplay,
							message.getBody(), message, formatHandler);

						message.setBody(body);

						_mbMessageLocalService.updateMBMessage(message);
					}
				}
			}
			else {
				message = _mbMessageService.getMessage(messageId);

				MBMessageFormatUploadHandler formatHandler =
					_formatHandlerProvider.provide(message.getFormat());

				if (formatHandler != null) {
					List<FileEntry> tempMBAttachmentFileEntries =
						MBAttachmentFileEntryUtil.
							getTempMBAttachmentFileEntries(body);

					if (!tempMBAttachmentFileEntries.isEmpty()) {
						body = _addBodyAttachmentTempFiles(
							tempMBAttachmentFileEntries, themeDisplay, body,
							message, formatHandler);
					}
				}

				// Update message

				message = _mbMessageService.updateMessage(
					messageId, subject, body, inputStreamOVPs, priority,
					allowPingbacks, serviceContext);

				if (message.isRoot()) {
					_mbThreadLocalService.updateQuestion(
						message.getThreadId(), question);
				}
			}

			PermissionChecker permissionChecker =
				themeDisplay.getPermissionChecker();

			boolean subscribe = ParamUtil.getBoolean(
				actionRequest, "subscribe");

			if (!preview && subscribe &&
				_messageModelResourcePermission.contains(
					permissionChecker, message, ActionKeys.SUBSCRIBE)) {

				_mbMessageService.subscribeMessage(message.getMessageId());
			}

			for (FileEntry tempFileEntry : tempFileEntries) {
				TempFileEntryUtil.deleteTempFileEntry(
					tempFileEntry.getFileEntryId());
			}

			return message;
		}
		finally {
			for (ObjectValuePair<String, InputStream> inputStreamOVP :
					inputStreamOVPs) {

				try (InputStream inputStream = inputStreamOVP.getValue()) {
				}
				catch (IOException ioException) {
					if (_log.isWarnEnabled()) {
						_log.warn(ioException, ioException);
					}
				}
			}
		}
	}

	private String _addBodyAttachmentTempFiles(
			List<FileEntry> tempMBAttachmentFileEntries,
			ThemeDisplay themeDisplay, String body, MBMessage message,
			MBMessageFormatUploadHandler formatHandler)
		throws PortalException {

		Folder folder = message.addAttachmentsFolder();

		List<MBAttachmentFileEntryReference> mbAttachmentFileEntryReferences =
			MBAttachmentFileEntryUtil.addMBAttachmentFileEntries(
				message.getGroupId(), themeDisplay.getUserId(),
				message.getMessageId(), folder.getFolderId(),
				tempMBAttachmentFileEntries,
				fileName -> _uniqueFileNameProvider.provide(
					fileName,
					curFileName -> _hasFileEntry(
						message.getGroupId(), folder.getFolderId(),
						curFileName)));

		for (FileEntry tempMBAttachment : tempMBAttachmentFileEntries) {
			PortletFileRepositoryUtil.deletePortletFileEntry(
				tempMBAttachment.getFileEntryId());
		}

		return formatHandler.replaceImageReferences(
			body, mbAttachmentFileEntryReferences);
	}

	private boolean _hasFileEntry(
		long groupId, long folderId, String fileName) {

		FileEntry fileEntry = _portletFileRepository.fetchPortletFileEntry(
			groupId, folderId, fileName);

		if (fileEntry == null) {
			return false;
		}

		return true;
	}

	private List<FileEntry> _populateInputStreamOVPs(
			ActionRequest actionRequest, long messageId,
			List<ObjectValuePair<String, InputStream>> inputStreamOVPs)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String[] selectedFileNames = ParamUtil.getParameterValues(
			actionRequest, "selectedFileName");

		List<FileEntry> tempFileEntries = new ArrayList<>(
			selectedFileNames.length);

		for (String selectedFileName : selectedFileNames) {
			FileEntry tempFileEntry = TempFileEntryUtil.getTempFileEntry(
				themeDisplay.getScopeGroupId(), themeDisplay.getUserId(),
				MBMessageConstants.TEMP_FOLDER_NAME, selectedFileName);

			tempFileEntries.add(tempFileEntry);

			String originalSelectedFileName =
				TempFileEntryUtil.getOriginalTempFileName(
					tempFileEntry.getFileName());

			String uniqueFileName = originalSelectedFileName;

			if (messageId > 0) {
				MBMessage message = _mbMessageService.getMessage(messageId);

				uniqueFileName = DLUtil.getUniqueFileName(
					tempFileEntry.getGroupId(),
					message.getAttachmentsFolderId(), originalSelectedFileName);
			}

			ObjectValuePair<String, InputStream> inputStreamOVP =
				new ObjectValuePair<>(
					uniqueFileName, tempFileEntry.getContentStream());

			inputStreamOVPs.add(inputStreamOVP);
		}

		return tempFileEntries;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditMessageMVCActionCommand.class);

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private MBMessageFormatUploadHandlerProvider _formatHandlerProvider;

	@Reference
	private MBCategoryService _mbCategoryService;

	@Reference
	private MBMessageLocalService _mbMessageLocalService;

	@Reference
	private MBMessageService _mbMessageService;

	@Reference
	private MBThreadLocalService _mbThreadLocalService;

	@Reference
	private MBThreadService _mbThreadService;

	@Reference(
		target = "(model.class.name=com.liferay.message.boards.model.MBMessage)"
	)
	private ModelResourcePermission<MBMessage> _messageModelResourcePermission;

	@Reference
	private Portal _portal;

	@Reference
	private PortletFileRepository _portletFileRepository;

	@Reference
	private UniqueFileNameProvider _uniqueFileNameProvider;

}