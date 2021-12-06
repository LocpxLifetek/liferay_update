<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="countViewBlogs">

	<div class="fixed">
		<hr
			style="color: red; background: red; border: 0; height: 3px; border-radius: 10px;">
		<p
			style="color: #666666 !important; text-transform: uppercase; font-size: 14px;">Tin
			nổi bật</p>
		<div class="imageViewBlogs">
			<img
				src="${blogs.srcImage}">
			<a href="/web/lifetek/tintuc?id=${blogs.uuid}">${blogs.title}</a>
			<hr>
		</div>
	</div>

	<div>

		<c:forEach items="${listBlogsEntry}" var="blog">
			<div>
				<a href="/web/lifetek/tintuc/?id=${blog.uuid}">${blog.title}</a>
			</div>
			<hr>
		</c:forEach>
	</div>
</div>
