<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="box-left-home">
	<div class="head-cm">
		<ul>
			<li><a href="${url}/thu-vien-video">Video</a></li>
		</ul>
		<a href="/media/thu-vien-video.html" class="xemthem">Xem thêm</a>
	</div>
	<div class="box-top3">
		<ul>
			<c:forEach items="${listDlFileVideoDtos}" var="dlFileVideoDtos">
				<li>
					<div style="position: relative">
						<a href="${url}/video?uuid=${dlFileVideoDtos.uuid}"> <img
							src="${dlFileVideoDtos.src}">
						</a> <a
							href="${url}/video?uuid=${dlFileVideoDtos.uuid}"
							class="iconplay"></a>
					</div>
					<h3>
						<a
							href="${url}/video?uuid=${dlFileVideoDtos.uuid}">${dlFileVideoDtos.title}</a>
					</h3>

				</li>

			</c:forEach>
		</ul>
	</div>
</div>