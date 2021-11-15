<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page
	import="com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil"%>
<%@page import="com.liferay.asset.kernel.model.AssetEntry"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/init.jsp"%>

<head>

</head>
<body>
	<div class="grid">

		<c:forEach items="${blogsList}" var="blog">
			<c:choose>
				<c:when test="${blog.smallImageFileEntryId > 0}">
					<c:forEach items="${smallImage}" var="si">
						<c:if test="${blog.smallImageFileEntryId == si.fileEntryId}">
							<div class="hotNews">
								<img
									src="/documents/${si.groupId}/${si.folderId}/${si.title}/${si.uuid}"
									class="soup-image1"> 
									<a href="/web/lifetek/tintuc?id=${blog.uuid}">${blog.title}</a>
							</div>
						</c:if>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div>
						<a href="/web/lifetek/tintuc?id=${blog.uuid}">${blog.title}</a>
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
</body>
