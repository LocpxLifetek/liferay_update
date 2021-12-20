<%@ include file="/init.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui"%>
<%@ taglib prefix="liferay-comment" uri="http://liferay.com/tld/comment"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>

<head>
<title>Video</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick-theme.min.css">
</head>

<body>

	<div class="videoList">
		<c:choose>
			<c:when test="${not empty error}">
				<div>
					<img
						src="https://www.totolink.vn/public/uploads/img_post/truy-tim-nguyen-nhan-va-cach-sua-chua-loi-tra-cuu-404-not-found-1.jpg"
						style="width: 100%">
				</div>
			</c:when>
			<c:otherwise>
				<div class="video-main" data-slick='{"slidesToShow": 4, "slidesToScroll": 4}'>
					<video class="myVideo" controls>
						<source
							src="/documents/${dlFileEntry.groupId}/${dlFileEntry.folderId}/${dlFileEntry.fileName}/${dlFileEntry.uuid}"
							type="${dlFileEntry.mimeType}">
					</video>
					<h3>${title}</h3>
				</div>
				<c:if test="${not empty dlFolder }">
					<div class="head-cm">
						<ul>
							<li><a href="${url}/the-loai-video?uuid=${dlFolder.uuid}">${dlFolder.name}</a></li>
						</ul>
					</div>
					<c:if test="${ not empty listDlFileEntryDtos}">
						<div class="video-category">
							<c:forEach items="${listDlFileEntryDtos}" var="dlFileEntry">
								<div class="video-category-goverment">
									<a href="${url}/video?uuid=${dlFileEntry.uuid}"> <video>
											<source src="${dlFileEntry.src}"
												type="${dlFileEntry.mimeType}">
										</video>
										<h7>${dlFileEntry.title}</h7>
									</a>
								</div>
							</c:forEach>
						</div>
					</c:if>
				</c:if>
			</c:otherwise>
		</c:choose>
	</div>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick.min.js"></script>


	<script type="text/javascript">
	$('.video-category').slick({
		  dots: true,
		  infinite: false,
		  speed: 300,
		  slidesToShow: 4,
		  slidesToScroll: 4,
		  responsive: [
		    {
		      breakpoint: 1024,
		      settings: {
		        slidesToShow: 3,
		        slidesToScroll: 3,
		        infinite: true,
		        dots: true
		      }
		    },
		    {
		      breakpoint: 600,
		      settings: {
		        slidesToShow: 2,
		        slidesToScroll: 2
		      }
		    },
		    {
		      breakpoint: 480,
		      settings: {
		        slidesToShow: 1,
		        slidesToScroll: 1
		      }
		    }
		    // You can unslick at a given breakpoint now by adding:
		    // settings: "unslick"
		    // instead of a settings object
		  ]
		});
	</script>

</body>

</html>
