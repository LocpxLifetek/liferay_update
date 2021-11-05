<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="porlet"%>
<html>
<body>
	<div class="vanbanduthaoitem">
		<c:forEach items="${listBlogs}" var="blog">
			<c:choose>
				<c:when test="${blog.smallImageFileEntryId > 0}">
					<c:forEach items="${smallImage}" var="si">
						<c:if test="${blog.smallImageFileEntryId == si.fileEntryId}">
							<div class="vanban">
								<div>
									<a class="vbdt-image"><img
										src="/documents/${si.groupId}/${si.folderId}/${si.title}/${si.uuid}"></a>
								</div>
								<div>
									<div class="sTitle">
										<div class="title">
											<a>${blog.title}</a>
										</div>
										<div class="vbdt-info">
											<div class="desc">${blog.description}</div>
										</div>
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</c:when>
			</c:choose>
		</c:forEach>
	</div>
</body>
</html>