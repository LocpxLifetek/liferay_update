<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="countViewEvent">
	<div class=countViewEvent_Right">
		<div class="countViewE">
			<img
				src="${countViewDto.srcImage}">
			<a href="${url}/chitiettintuc?id=${countViewDto.uuid}">${countViewDto.title}</a>
			<p>${countViewDto.description}</p>
		</div>
	</div>
	<div class="countViewEvent_Left">
		<div class="title-new-hot">
			<p
				style="color: #d71920 !important; text-transform: uppercase; font-size: 16px; text-align: left;font-weight:bold;">Tin
				nổi bật</p>
		</div>
		<c:forEach items="${listCountViewDtos}" var="blog">
			<div class="title-infor">
				<a href="${url}/chitiettintuc?id=${blog.uuid}"> <img
					src="http://chinhphu.vn/templates/govportal/tinhthanh/images/icon_1.jpg">
					${blog.title}
				</a>
			</div>
		</c:forEach>
	</div>
</div>