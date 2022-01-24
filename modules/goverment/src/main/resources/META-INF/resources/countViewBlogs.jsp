<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="countViewBlogs">

	<div class="fixed">
		<div
			style="font: 700 18px/35px Roboto, Arial, Tahoma; color: #404041; text-transform: uppercase; text-align: center; margin: 15px 0 5px 0;">Xem
			nhiều</div>
		<div class="linered"></div>
		<br>
		<div class="imageViewBlogs">
			<img src="${blogs.src}"> <a
				href="${url}/chitiettintuc?uuid=${blogs.uuidBlogsEntry}" style="padding:5px 0px 0px 0px;">${blogs.titleBlogsEntry}</a>
			<hr>
		</div>
	</div>

	<div class="blogView">

		<c:forEach items="${listBlogsEntry}" var="blog">
			<div style="border-bottom: 1px dotted #ccc;padding: 10px 0 10px 0px;">
				<a href="${url}/chitiettintuc/?uuid=${blog.uuidBlogsEntry}">${blog.titleBlogsEntry}</a>
			</div>
			
		</c:forEach>
	</div>
</div>