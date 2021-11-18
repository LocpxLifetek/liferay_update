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

		<c:forEach items="${listBlogsEntryDtos}" var="blog">

			<div class="hotNews">
				<img
					src="/documents/${blog.groupId}/${blog.folderId}/${blog.titleDlFileEntry}/${blog.uuidDlFileEntry}"
					class="soup-image1"> <a
					href="/web/lifetek/tintuc?id=${blog.uuidBlogsEntry}">${blog.titleBlogsEntry}</a>
			</div>



		</c:forEach>
	</div>
</body>
