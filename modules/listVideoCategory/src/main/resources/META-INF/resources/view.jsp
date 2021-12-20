<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="head-cm">
	<ul>
		<li><a href="/media/thu-vien-video.html">${fileFolderDto.name}</a></li>
	</ul>
</div>
<div>

	<div class="video-media">
		<c:forEach items="${listDlFileEntryDtos}" var="dlFileEntry">
			<div>
				<a href="${url}/video?uuid=${dlFileEntry.uuidDlFileEntry}"> <video>
						<source src="${dlFileEntry.src}" type="${dlFileEntry.mimeType}">
					</video>
					<p>${dlFileEntry.title}</p>
				</a>
			</div>
		</c:forEach>
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
							<li><a
								href="${url}/the-loai-video?uuid=${uuid}&page=${i}">${i}</a>
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
</div>

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