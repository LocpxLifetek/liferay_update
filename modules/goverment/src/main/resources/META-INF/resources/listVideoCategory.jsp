<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="box-left-home">
	<div class="head-cm">
		<ul>
			<li><a href="${url}/thu-vien-video">${dlFolder.name}</a></li>
		</ul>
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