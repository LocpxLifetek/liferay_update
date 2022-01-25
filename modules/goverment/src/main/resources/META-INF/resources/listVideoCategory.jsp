<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<style>
.head-cm ul li:first-child {
	background:
		url("<%=request.getContextPath()%>/images/portal/_res/img/bg-chuyenmuc.png")
		no-repeat right top;
}

.box-top4 ul li a.iconplay {
	width: 45px;
	height: 45px;
	position: absolute;
	left: 0;
	bottom: 0px;
	background: url(<%=request.getContextPath()%>/images/portal/_res/img/i-play-video.png)
		no-repeat;
	float: left;
}

.box-top4 li img {
	width: 100%;
	height: 150px;
}

.box-top4 ul {
	list-style: none;
	margin: 0;
	padding: 0;
}

.box-top4 ul li a {
	text-align: justify;
	font: 700 14px/19px Roboto, Arial, Tahoma;
	color: #58595b;
	display: block;
}

.box-top4 ul {
	display: grid;
	grid-template-columns: 32% 32% 32%;
	gap: 5px 15px;
}
</style>
<div class="box-left-home">
	<div class="box-dieuhuong">
		<div class="trangchu">
			<a href="/"
				style="color: #333;
						    font-size: 13px;
						    padding: 0 12px 0 0px;
						    background: url('<%=request.getContextPath()%>/images/portal/_res/img/i-right.png') no-repeat right center;">Trang
				chủ</a>
		</div>
		<div class="trangchu">
						<a href="${url}/thu-vien-video"
							style="color: #333;
						    font-size: 13px;
						    padding: 0 12px 0 10px;
						    background: url('<%=request.getContextPath()%>/images/portal/_res/img/i-right.png') no-repeat right center;">Media</a>
		</div>
		<div class="trangchu">
						<a href="${url}/the-loai-video?id=${assetCategory.uuid}"
							style="font-size: 13px; color: #0a4298 !important; padding: 0px 0px 0px 10px;">${assetCategory.name}</a>
					</div>
	</div>
	<div class="head-cm" style="margin-bottom: 20px;margin-top:20px">
		<ul>
			<li><a href="${url}/the-loai-video?id=${assetCategory.uuid}">${assetCategory.name}</a></li>
		</ul>
	</div>
	<div class="box-top4">
		<ul>
			<c:forEach items="${listDlFileVideoDtos}" var="dlFileVideoDtos">
				<li>
					<div style="position: relative">
						<a href="${url}/video?uuid=${dlFileVideoDtos.uuid}"> <img
							src="${dlFileVideoDtos.src}">
						</a> <a href="${url}/video?uuid=${dlFileVideoDtos.uuid}"
							class="iconplay"></a>
					</div>
					<h3>
						<a href="${url}/video?uuid=${dlFileVideoDtos.uuid}">${dlFileVideoDtos.title}</a>
					</h3>

				</li>

			</c:forEach>
		</ul>
	</div>
</div>
<div id="wrapper">
	<ul id="pagination">
		<c:if test="${currentPage != 1}">
			<li><a
				href="${url}/the-loai-video?uuid=${uuid}&page=${currentPage - 1}">«</a></li>
		</c:if>
		<c:if test="${currentPage gt 3}">
			<li><span>...</span></li>
		</c:if>
		<c:forEach begin="1" end="${totalPage}" var="i">
			<c:choose>
				<c:when test="${currentPage eq i}">
					<li><a href="#">${i}</a>
					<li>
				</c:when>
				<c:otherwise>
					<c:if test="${(currentPage-3) lt i and i lt (currentPage + 3)}">
						<li><a href="${url}/the-loai-video?uuid=${uuid}&page=${i}">${i}</a>
						</li>
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${currentPage lt (totalPage-3)}">
			<li><span>...</span></li>
		</c:if>
		<c:if test="${currentPage lt totalPage}">
			<li><a
				href="${url}/the-loai-video?uuid=${uuid}&page=${currentPage + 1}">»</a>
			</li>
		</c:if>

	</ul>
</div>