<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>

<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/slick.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/slick-theme.min.css">

</head>
<style>
.link-goverment {
	text-transform: uppercase;
	font: 700 16px/30px Roboto, Arial, Tahoma;
	color: #d71920;
	text-transform: uppercase;
	border-bottom: 1px solid #ddd;
}
</style>

<body>
	<div>
		<div class="link-goverment">Liên kết</div>
		<br>
		<div class="link-portal">
			<c:forEach items="${listDlFileEntryDtos}" var="dlFileEntryDto">
				<div class="link-portal-goverment">
					<a href="${dlFileEntryDto.src}"> <img
						src="/documents/${dlFileEntryDto.groupId}/${dlFileEntryDto.folderId}/${dlFileEntryDto.title}/${dlFileEntryDto.uuidCategory}"
						alt="">

					</a>
				</div>
			</c:forEach>
		</div>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/slick.min.js"></script>
	<script type="text/javascript">
		$('.link-portal').slick({
			slidesToShow : 4,
			slidesToScroll : 1,
			autoplay : true,
			autoplaySpeed : 2000,
		});
	</script>

</body>

</html>