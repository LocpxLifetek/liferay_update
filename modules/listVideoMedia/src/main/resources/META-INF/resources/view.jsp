<%@ include file="/init.jsp"%>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach items="${listDlFileEntryAndNameFolder}" var="mapDlFileEntry">
	<c:forEach items="${mapDlFileEntry}" var="listDlFileEntry">
		<div class="head-cm">
			<ul>
				<li><a href="${url}/the-loai-video?uuid=${listDlFileEntry.key.uuid}">${listDlFileEntry.key.name}</a></li>
			</ul>
			<a href="${url}/the-loai-video?uuid=${listDlFileEntry.key.uuid}" class="xemthem">Xem thêm</a>
		</div>
		<div class="video-media">
			<c:forEach items="${listDlFileEntry.value}" var="dlFileEntry">
				<div>
					<a href="${url}/video?uuid=${dlFileEntry.uuidDlFileEntry}"> <video>
							<source
								src="${dlFileEntry.src}"
								type="${dlFileEntry.mimeType}">
						</video>
						<p>${dlFileEntry.title}</p>
					</a>
				</div>
			</c:forEach>
		</div>
	</c:forEach>
</c:forEach>

<script>
const videos = document.querySelectorAll("video");

videos.forEach(video =>{

    function startPreview() {
      video.muted = true;
      video.currentTime = 1;
      video.playbackRate = 0.5;
      video.play();
    }
    
    function stopPreview() {
      video.currentTime = 0;
      video.playbackRate = 1;
      video.pause();
    }
    
    let previewTimeout = null;
    
    video.addEventListener("mouseenter", () => {
      startPreview();
      previewTimeout = setTimeout(stopPreview, 4000);
    });
    
    video.addEventListener("mouseleave", () => {
      clearTimeout(previewTimeout);
      previewTimeout = null;
      stopPreview();
    });
})
</script>