<%@ include file="/init.jsp"%>

<div class="grid">

	<c:forEach items="${listBlogsEntryDtos}" var="blog">
		<div class="hotNews">
			<img
				src="${blog.src}"
				class="soup-image1"> <a
				href="${url}/tintuc?uuid=${blog.uuidBlogsEntry}">${blog.titleBlogsEntry}</a>
		</div>
	</c:forEach>
</div>