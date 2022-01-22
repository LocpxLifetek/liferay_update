<%@ include file="/init.jsp" %>
<html>
<body>
	<div class="featured-news">
		<div style="width: 100%">
			<div class="grid2">
				<h2>
					<a href="${url}/tintuc?uuid=${blogsEntryDto.uuidBlogsEntry}">${blogsEntryDto.titleBlogsEntry}</a>
				</h2>
				<span style="color: #d71920; font-size: 12px;">(<fmt:formatDate
						value="${blogsEntryDto.modifiedDate}" pattern="MM/dd/yyyy" />)
				</span>
			</div>
			<div class="grid1">
				<div>
					<img
						src="${blogsEntryDto.src}"
						class="soup-image">
					<p>${blogsEntryDto.description}</p>
				</div>
			</div>
		</div>
		<div style="margin-left: 5px;">
			<c:forEach items="${listBlogsNoImage}" var="blog">
				<div class="title1" style="padding: 25px 0px 0px 10px;">
					<img
						src="http://chinhphu.vn/templates/govportal/tinhthanh/images/icon_1.jpg"><a
						href="${url}/tintuc?uuid=${blog.uuidBlogsEntry}"> ${blog.titleBlogsEntry}</a>
				</div>
			</c:forEach>
		</div>
	</div>
</body>

</html>