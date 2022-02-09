<%@ include file="/init.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui"%>
<%@ taglib prefix="liferay-comment" uri="http://liferay.com/tld/comment"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	height: 170px;
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
<div class="videoList">

	<div class="video-main">
		<video class="myVideo" controls autoplay>
			<source src="${src}" type="${mimetype}">
		</video>
		<h3 style="text-align: justify;">${title}</h3>
	</div>
	<div class="head-cm" style="margin-bottom: 20px;margin-top:20px">
		<ul>
			<li><a href="#">Video mới nhất</a></li>
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