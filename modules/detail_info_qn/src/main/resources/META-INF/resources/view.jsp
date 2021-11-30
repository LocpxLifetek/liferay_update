<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="porlet" %>
<html>
<body>
	<div class="featured-news">
		<div style="width: 100%">
			<div class="grid2">
				<h2 >
					<a href="/web/lifetek/detail_page?id=${blogs.uuid}">${blogs.title}</a>
				</h2>
			</div>
			<div class="grid1">
				<div>
					<img
						src="/documents/${si.groupId}/${si.folderId}/${si.title}/${si.uuid}"
						class="soup-image">
					<p>${blogs.description}</p>
				</div>
			</div>

		</div>
		<div style="margin-left:5px;">
			<c:forEach items="${listBlogs}" var="blog">
				<div class="title">
					<a href="/web/lifetek/detail_page?id=${blog.uuid}"> ${blog.title}</a>
				</div>
			</c:forEach>
		</div>
	</div>
</body>

</html>