
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="porlet"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/jquery.fancybox.min.css">
<style>
.head-cm ul li:first-child {
	background:
		url("<%=request.getContextPath()%>/images/portal/images/bg-chuyenmuc.png")
		no-repeat right top;
}
.container {
	margin: 0 auto;
	max-width: 68rem;
	width: 100%;
}
.main .container {
	display: grid;
	grid-template-columns: repeat(3, 1fr);
	grid-gap: 1rem;
	justify-content: center;
	align-items: center;
}
.main .card {
	background: #ffffff;
	box-shadow: none !important;
	color: #333333;
	border-radius: 2px;
}
.main .card a{
color: #333;
    font: 400 15px/18px Roboto, Arial, Tahoma;
}
.main .card-image {
	background: #ffffff;
	display: block;
	padding-top: 70%;
	position: relative;
	width: 100%;
}
.main .card-image img {
	display: block;
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 80%;
	object-fit: cover;
}
@media only screen and (max-width: 600px) {
	.main .container {
		display: grid;
		grid-template-columns: 1fr;
		grid-gap: 1rem;
	}
}
</style>

</head>
<body>
	<!-- partial:index.partial.html -->
	<div class="head-cm">
		<ul style="margin: 0; padding: 0">
			<li><a href="#">DANH SÁCH ẢNH</a></li>
		</ul>
	</div>
	<div class="info-album">
		<h1>${dlfileEntry.title}</h1>
	</div>
	<main class="main">
	<div class="container">
		<c:forEach items="${listDlfile}" var="listDlfile">
			<div class="card">
				<div class="card-image">
					<a href="${listDlfile.src}" data-fancybox="gallery"
						data-caption="${listDlfile.title}"> <img
						src="${listDlfile.src}" alt="${listDlfile.title}">${listDlfile.title}
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
	</main>
	<!-- partial -->
	<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.fancybox.min.js"></script>
	<script>
		// Fancybox Config
		$('[data-fancybox="gallery"]').fancybox(
				{
					buttons : [ "slideShow", "thumbs", "zoom", "fullScreen",
							"share", "close" ],
					loop : false,
					protect : true
				});
	</script>

</body>
</html>