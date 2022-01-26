<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<style>
.chidao-dieuhanh {
	border: 1px solid #ddd;
	overflow: auto;
}


.lts-chidaodieuhanh ul li {
	background:
		url(<%=request.getContextPath()%>/images/portal/_themes/ThanhHoa_orange/img/hv-module-tvt.PNG)
		no-repeat;
	padding-left: 25px;
	margin-bottom: 15px;
}

.lts-cmanh ul li {
	background:
		url(<%=request.getContextPath()%>/images/portal/_themes/ThanhHoa_orange/img/hv-module-tvt.PNG)
		no-repeat left center;
	border-bottom: 1px dotted #ccc;
	padding: 10px 0 10px 15px;
}
</style>
<div class="chidao-dieuhanh" style="background: rgba(226, 229, 245, 0.4);">
	<div class="head-chidaodieuhanh">
		Xem nhiều
		<div class="linered"></div>
	</div>
	<div class="lts-cmanh">

		<div class="top1">
			<div style="position: relative">
				<a
					href="${url}/chitiettintuc?uuid=${blogs.uuidBlogsEntry}">
					<img
					src="${blogs.src}"
					alt="">
				</a>
			</div>
			<div>
				<a
					href="${url}/chitiettintuc?uuid=${blogs.uuidBlogsEntry}">${blogs.titleBlogsEntry}</a>
			</div>
		</div>

		<ul>
			<c:forEach items="${listBlogsEntry}" var="blog">
			<li><a
				href="${url}/chitiettintuc/?uuid=${blog.uuidBlogsEntry}"
				title="">${blog.titleBlogsEntry}</a></li>
			</c:forEach>


		</ul>

	</div>
</div>