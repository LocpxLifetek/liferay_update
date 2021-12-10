<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<c:choose>
	<c:when test="${not empty articleId}">
		<table cellspacing="0" cellpadding="0" border="0" width="100%">
			<tbody>
				<tr>
					<td>
						<table cellspacing="0" cellpadding="4" width="100%"
							style="border-collapse: collapse; border: 1px solid rgb(251, 223, 184);">
							<tbody>
								<tr>
									<td>
										<p class="title" style="text-transform: uppercase;">${journalFolder}</p>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td height="1"></td>
				</tr>
				<tr>
					<td>
						<table cellspacing="0" cellpadding="0" width="100%"
							style="border-collapse: collapse; border: 1px solid rgb(212, 211, 211);">
							<tbody>
								<tr>
									<td width="100%" valign="top"
										style="background-image: url(https://chinhphu.vn/templates/govportal/chinhphu/images/bgr_page.jpg);">
										<table cellspacing="0" cellpadding="7" border="0"
											align="center" width="100%">

											<tbody>
												<tr>
													<td align="justify"><font face="Arial"> <span
															style="font-size: 9pt;">${content}</span>
													</font></td>
												</tr>


											</tbody>
										</table>
									</td>
								</tr>

							</tbody>
						</table>
					</td>
				</tr>

			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<table cellspacing="0" cellpadding="0" border="0" width="100%">
			<tbody>
				<tr>
					<td>
						<table cellspacing="0" cellpadding="4" width="100%"
							style="border-collapse: collapse; border: 1px solid rgb(251, 223, 184);">
							<tbody>
								<tr>
									<td>
										<p class="title" style="text-transform: uppercase;">
											${journalFolder}</p>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td height="1"></td>
				</tr>
				<tr>
					<td>
						<table cellspacing="0" cellpadding="0" width="100%"
							style="border-collapse: collapse; border: 1px solid rgb(212, 211, 211);">
							<tbody>
								<tr>
									<td valign="top"
										style="background-image: url(https://chinhphu.vn/templates/govportal/chinhphu/images/bgr_page.jpg);">
										<table cellspacing="0" cellpadding="5" border="0"
											align="center" width="94%">
											<tbody>
												<tr>
													<td><c:forEach
															items="${listJournalArticleLocalizationDtos}"
															var="journalArticleLocalizationDtos">

															<table cellpadding="2" cellspacing="0" border="0">
																<tbody>
																	<tr>
																		<td valign="top"><img hspace="5" height="3"
																			align="top" width="3" vspace="6"
																			src="http://chinhphu.vn/templates/govportal/chinhphu/images/icon.jpg" style="margin:10px">
																		</td>
																		<td><a class="tinmoi"
																			href="${url}/số-liệu-ngân-sách-nhà-nước?idFolder=${journalArticleLocalizationDtos.folderId}&articleId=${journalArticleLocalizationDtos.id}">
																				${journalArticleLocalizationDtos.name} </a></td>
																	</tr>
																</tbody>
															</table>
														</c:forEach></td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>