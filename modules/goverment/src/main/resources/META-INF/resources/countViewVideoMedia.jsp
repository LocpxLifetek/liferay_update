<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="chidao-dieuhanh">
	<div class="head-chidaodieuhanh">
		Video xem nhiều nhất
		<div class="linered"></div>
	</div>
	<div class="lts-cmanh">

		<div class="top1">
			<div style="position: relative">
				<a href="${url}/video?uuid=${countViewVideoDto.uuid}"> <video>
						<source src="${countViewVideoDto.src}" type="${countViewVideoDto.mimeType}">
					</video>
				</a>
			</div>
			<div>
				<a
					href="${url}/video?uuid=${countViewVideoDto.uuid}">${countViewVideoDto.title}</a>
			</div>
		</div>

		<ul>
			<c:forEach items="${listCountViewDtos}" var="countView">

				<li><a
					href="${url}/video?uuid=${countView.uuid}"
					title="">${countView.title}</a></li>
			</c:forEach>
		</ul>

	</div>
</div>
