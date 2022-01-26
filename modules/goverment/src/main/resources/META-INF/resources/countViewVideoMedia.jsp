<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.top1 a.iconplay {
	width: 45px;
	height: 45px;
	position: absolute;
	left: 0;
	bottom: 8px;
	background:
		url(<%=request.getContextPath()%>/images/portal/_res/img/i-play-video.png)
		no-repeat;
	float: left;
}

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
		Video xem nhiều nhất
		<div class="linered"></div>
	</div>
	<div class="lts-cmanh">

		<div class="top1">
			<div style="position: relative">
				<a
					href="${url}/video?uuid=${dlFileVideoDto.uuid}">
					<img
					src="${dlFileVideoDto.src}"
					alt="">
				</a> <a
					href="${url}/video?uuid=${dlFileVideoDto.uuid}"
					class="iconplay"></a>
			</div>
			<div>
				<a
					href="${url}/video?uuid=${dlFileVideoDto.uuid}">${dlFileVideoDto.title}</a>
			</div>
		</div>

		<ul>
			<c:forEach items="${listDlFileVideoTitleDtos}" var="dlFileTitle">
			<li><a
				href="${url}/video?uuid=${dlFileTitle.uuid}"
				title="">${dlFileTitle.title}</a></li>
			</c:forEach>


		</ul>

	</div>
</div>
