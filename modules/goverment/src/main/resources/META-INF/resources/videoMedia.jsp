<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<style>
.box-left-home {
	overflow: auto;
	margin-bottom: 20px;
	border-bottom: 1px dotted #666;
}



.head-cm ul li:first-child {
	background:
		url("<%=request.getContextPath()%>/images/portal/_res/img/bg-chuyenmuc.png")
		no-repeat right top;
}


.box-top3 ul li .bg-video {
	background:
		url('<%=request.getContextPath()%>/images/portal/_res/img/i-play-video.png')
		no-repeat bottom left;
}



.box-top3 ul li a.iconplay {
	width: 45px;
	height: 45px;
	position: absolute;
	left: 0;
	bottom: 0px;
	background: url(<%=request.getContextPath()%>/images/portal/_res/img/i-play-video.png)
		no-repeat;
	float: left;
}
</style>
<div id="ctl00_ctl30_g_a0b6803f_9ec0_4a8e_902b_a8aabc1bb32d">
	<c:forEach items="${maps}" var="map">

		<div class="box-left-home fixalbumdetail">
			<div class="head-cm" style="margin-bottom: 20px;">
				<ul>
					<li><a href="${url}/the-loai-video?uuid=${map.key.uuid}">${map.key.name}</a></li>

				</ul>
				<a
					href="${url}/the-loai-video?uuid=${map.key.uuid}"
					class="xemthem">Xem thêm</a>
			</div>
			<div class="box-top3 video-other">
				<c:forEach items="${map.value}" var="dlFileEntry">
					<ul>

						<li>
							<div style="position: relative">
								<a href="${url}/chi-tiet-video?uuid=${dlFileEntry.uuid}"> <img
									src="${dlFileEntry.src}">
								</a> <a href="${url}/chi-tiet-video?uuid=${dlFileEntry.uuid}"
									class="iconplay"></a>
							</div>
							<h3>
								<a href="${url}/chi-tiet-video?uuid=${dlFileEntry.uuid}">${dlFileEntry.title}</a>
							</h3>
						</li>

					</ul>

				</c:forEach>
			</div>
		</div>
	</c:forEach>

</div>