<%@ include file="/init.jsp"%>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="box-left-home">
	<c:forEach items="${maps}" var="map">

		<div class="head-cm">
			<ul>
				<li><a href="${url}/the-loai-video?uuid=${map.key.uuid}">${map.key.name}</a></li>
			</ul>
			<a href="${url}/the-loai-video?uuid=${map.key.uuid}" class="xemthem">Xem
				thêm</a>
		</div>
		<div class="box-top3">
			<ul>
				<c:forEach items="${map.value}" var="dlFileEntry">
					<li>
						<div style="position: relative">
							<a
								href="${url}/video?uuid=${dlFileEntry.uuid}">
								<img
								src="${dlFileEntry.src}">
							</a> <a
								href="${url}/video?uuid=${dlFileEntry.uuid}"
								class="iconplay"></a>
						</div>
						<h3>
							<a
								href="${url}/video?uuid=${dlFileEntry.uuid}">Bộ
								${dlFileEntry.title}</a>
						</h3>

					</li>
				</c:forEach>
			</ul>
		</div>

	</c:forEach>
</div>