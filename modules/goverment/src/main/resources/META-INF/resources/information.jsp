<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="detailBlogs">
	<div class="title">
		<h2>${blogsEntry.title}</h2>
		<span style="color: #d71920; font-size: 12px;">(<fmt:formatDate
				value="${blogsEntry.modifiedDate}" pattern="dd/MM/yyyy" />)
		</span>
		<hr>
	</div>
	<div class="description">
		<h4 style="font-weight: bold;">${blogsEntry.description}</h4>
	</div>
	<div class="contentBlogs">${blogsEntry.content}</div>
</div>

<c:if test="${not empty listAssetTag}">

	<div class="tags-text">

		<p class="keywords">
			<span class="name">Từ khóa: </span>
			<c:forEach items="${listAssetTag}" var="assetTag">
			
			 <span class="word"> <a
				href="/tags?uuid=${assetTag.uuid}"> ${assetTag.name}</a>
			</span>
			,
			</c:forEach>
		</p>
	</div>
</c:if>