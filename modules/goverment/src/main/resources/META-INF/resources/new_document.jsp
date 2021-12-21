<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="porlet"%>
<html>
<body>
	<div class="vanbanduthaoitem">
		<c:forEach items="${listBlog}" var="blog">
			<div class="vanban">
				<div>
					<a class="vbdt-image"><img
						src="/documents/${blog.src}"></a>
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
</body>
</html>