<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>

	<div class="head-cm">
		<ul>
			<li><a href="${url}/thu-vien-video">Video</a></li>
		</ul>
		<a href="${url}/thu-vien-video" class="xemthem">Xem thêm</a>
	</div>
	<div class="video-media">
		<c:forEach items="${listDlFileEntryDtos}" var="dlFile">
			<div>
				<a href="${url}/video?uuid=${dlFile.uuidDlFileEntry}" class="abc">
					<video>
						<source src="${dlFile.src}" type="${dlFile.mimeType}">
					</video>
					<p>${dlFile.title}</p>
				</a>
			</div>
		</c:forEach>
	</div>



<script>
const videos = document.querySelector(".video-media").querySelectorAll("video");

videos.forEach(video =>{

    function startPreview() {
      video.muted = true;
      video.currentTime = 2;
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