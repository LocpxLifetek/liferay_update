<%@ include file="/init.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui"%>
<%@ taglib prefix="liferay-comment" uri="http://liferay.com/tld/comment"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<body>

	<div class="videoList">

		<div class="video-main">
			<video class="myVideo" controls>
				<source src="${src}" type="${mimeType}">
			</video>
			<h3>${title}</h3>
		</div>

		<c:forEach items="${maps}" var="map">
			<div class="head-cm">
				<ul>
					<li><a href="${url}/the-loai-video?uuid=${map.key.uuid}">${map.key.name}</a></li>
				</ul>
			</div>
			<div class="box-top3 detailVideo">
				<ul>

					<c:forEach items="${map.value}" var="valueDlFile">
						<li>
							<div style="position: relative">
								<a href="${url}/video?uuid=${valueDlFile.uuid}"> <img
									src="${valueDlFile.src}">
								</a> <a href="${url}/video?uuid=${valueDlFile.uuid}"
									class="iconplay"></a>
							</div>
							<h3>
								<a href="${url}/video?uuid=${valueDlFile.uuid}">${valueDlFile.title}</a>
							</h3>

						</li>
					</c:forEach>
				</ul>
			</div>
		</c:forEach>
	</div>
</body>

