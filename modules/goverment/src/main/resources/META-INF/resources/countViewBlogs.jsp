<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="countViewBlogs">

	<div class="fixed">
		<hr
			style="color: red; background: red; border: 0; height: 3px; border-radius: 10px;">
		<p
			style="color: #666666 !important; text-transform: uppercase; font-size: 14px;">Tin
			nổi bật</p>
		<div class="imageViewBlogs">
			<img
				src="${blogs.src}">
			<a href="${url}/tintuc?id=${blogs.uuidBlogsEntry}">${blogs.titleBlogsEntry}</a>
			<hr>
		</div>
	</div>

	<div>

		<c:forEach items="${listBlogsEntry}" var="blog">
			<div>
				<a href="${url}/tintuc/?id=${blog.uuidBlogsEntry}">${blog.titleBlogsEntry}</a>
			</div>
			<hr>
		</c:forEach>
	</div>
</div>