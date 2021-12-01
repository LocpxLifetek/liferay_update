<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="porlet"%>
<html>
<body>
<div class="vanbanduthao">
	<h3 class="title">Giới thiệu văn bản mới</h3>
	<div class="vanbanduthaoitem">
		<c:forEach items="${listBlogsEntryDtos}" var="blog">
			
							<div class="vanban">
								<div>
									<a class="vbdt-image"><img
										src="/documents/${blog.groupId}/${blog.folderId}/${blog.titleDlFileEntry}/${blog.uuidDlFileEntry}"></a>
								</div>
								<div>
									<div class="sTitle">
										<div class="title">
											<a>${blog.titleBlogsEntry}</a>
										</div>
										<div class="vbdt-info">
											<div class="desc">${blog.description}</div>
										</div>
									</div>
								</div>
								</div>
		</c:forEach>
	</div>
</div>
</body>
</html>