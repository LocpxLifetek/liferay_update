<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<style>
body {
	margin: 0;
	padding: 0;
}

.header {
	background: #2b85d9;
	background-repeat: no-repeat;
	background-size: auto 100%;
	background-position: top left;
	position: relative;
	min-height: 150px;
}

@media ( min-width : 768px) {
	.header {
		min-height: 170px;
	}
}

.header-fixed * {
	transition: all ease 0.3s;
}

.banner-band {
	position: relative;
	z-index: 1;
}

@media ( min-width : 768px) {
	.container {
		width: 730px;
	}
}

@media ( min-width : 992px) {
	.container {
		width: 950px;
	}
}

@media ( min-width : 1200px) {
	.container {
		width: 1220px;
	}
}

.container.banner-band>img {
	min-height: 127px;
	display: inline-block;
	margin: 0;
	vertical-align: top;
	padding: 20px 0;
}

.brand-text {
	text-transform: uppercase;
	padding: 10px 0 10px 10px;
	text-shadow: 0px 1px 1px rgb(0 0 0/ 35%);
	display: inline-block;
	text-align: center;
}

.brand-text h1 {
	font-size: 30px;
	font-family: Helvet, Roboto, sans-serif, arial;
	font-weight: 600;
	color: #fff;
	margin-top: 30px;
}

.brand-text h2 {
	font-size: 38px;
	font-family: Avo, Roboto, sans-serif, arial;
	font-weight: 600;
	color: #fd9b08;
	text-shadow: 0px 1px 0px #fff, 1px 0px 0px #fff, -1px 0px 0px #fff, 0px
		-1px 0px #fff, 0px 1px 0px #fff;
	margin-top: 15px;
}

h3, .h3 {
	font-size: 24px;
}

h1, .h1, h2, .h2, h3, .h3 {
	margin-top: 20px;
	margin-bottom: 10px;
}

.container {
	margin-right: auto;
	margin-left: auto;
	padding-left: 10px;
	padding-right: 10px;
}

.header-fixed * {
	transition: all ease 0.3s;
}
/* header-menu.css */
ul {
	list-style-type: none;
	padding-left: 0px;
}

.header-menu-wrapper {
	position: relative;
}

.header-menu-wrapper, .header-menu {
	display: block;
	width: 100%;
	background-color: #1e62ca;
	margin: 0px;
}

.header-menu-item {
	float: left;
	background-color: #1e62ca;
	position: static;
}

.header-menu-item>.title-menu {
	font-size: 15px;
	text-transform: uppercase;
	text-decoration: none;
	color: #ffffff;
	display: block;
	padding: 14px;
	font-weight: bold;
	font-family: Avo, Roboto, sans-serif, arial;
    font-weight: 600;
}

.header-menu-item:hover>.title-menu {
	color: #ffffff;
	cursor: pointer;
}

.header-menu-item:hover, .header-menu-item.active {
	background: linear-gradient(#2464b0, #185090, #204f86);
}

.header-menu-item:hover .header-menu-sub-wrapper, .header-menu-item:hover .header-menu-triangle-wrapper
	{
	display: block;
}

.header-menu-item.active .header-menu-sub-wrapper, .header-menu-item.active .header-menu-triangle-wrapper
	{
	display: block;
}

.header-menu-item, .header-menu-sub-item {
	display: inline-block;
}

.header-menu-sub {
	padding-top: 0px;
	padding-bottom: 0px;
}

.header-menu-sub-wrapper {
	display: none;
	position: absolute;
	width: 100%;
	top: 100%;
	left: 0;
	background-color: #f0f6fc;
}

.header-menu-sub-item>.title-menu {
	font-family: Arial;
	font-size: 13px;
	text-transform: uppercase;
	text-decoration: none;
	color: #444444;
	padding-top: 10px;
	padding-bottom: 10px;
	display: inline-block;
}

.header-menu-sub-item










:not





 





(
:first-child





 





)
{
padding-left










:





 





15
px










;
}
.header-menu-sub-item:hover .title-menu, .header-menu-sub-item.sub-active .title-menu
	{
	color: #0083f3;
}

.header-menu-triangle-wrapper {
	display: none;
	position: relative;
	width: 100%;
}

.header-menu-triangle {
	width: 10px;
	top: 0px;
	left: 50%;
	margin-left: -6px;
	border-left: 7px solid transparent;
	border-right: 7px solid transparent;
	border-top: 7px solid #0083f3;
	position: absolute;
	z-index: 1;
}

.header-bottom {
	background-color: #f0f6fc;
	height: 35px;
	padding-left: 20px;
	/* padding-right: 20px; */
	z-index: 1;
}

.header-bottom span.lbl {
	text-align: right;
	color: #0a4298;
	line-height: 32px;
	font-weight: 700;
	display: block;
	max-width: 97px;
	position: absolute;
}

.header-bottom .list-news-header {
	font-size: 15px;
	padding-left: 70px;
	padding-right: 20px;
}

.header-bottom .list-news-header marquee {
	margin-top: 3px;
}

.header-bottom .list-news-header a {
	color: rgb(0, 0, 0);
}

.header-bottom .list-news-header a:hover {
	color: rgb(88, 46, 5);
}

.header-bottom .list-news-header a:focus {
	color: rgb(88, 46, 5);
}

.header-bottom .list-news-header span {
	margin: 0px 14px;
	display: inline-block;
}

a {
	text-decoration: none;
}

.banner-carousel {
	opacity: 1;
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	width: auto;
	left: 35%;
	text-align: right;
	overflow: hidden;
}

.tandan-div-banner {
	height: 100% !important;
	margin-bottom: 1px;
}

.tandan-div-banner>a>img {
	max-width: 100%;
	height: 100%;
}

element.style {
	right: -30%;
	top: 16px;
}
/* btn search */
.header-menu-search-wrapper {
	bottom: 0px;
	padding: 10px 0px;
}

.header-menu-search-wrapper {
	width: 150px;
	float: right;
	padding: 10px 0px;
	position: absolute;
	right: 15px;
	margin-top: -26px;
	padding-right: 5px;
}

.header-menu-search-box {
	font-family: Arial;
	width: 100%;
	padding: 7px 24px 7px 10px;
	background-color: #f5f5f6;
	color: #5f5858;
	border: 1px solid #e0e1e2;
	border-radius: 3px;
	font-size: 13px;
}

button, input {
	overflow: visible;
}

.header-menu-search-wrapper .icon-search {
	position: absolute;
	top: 20px;
	right: 5px;
}

.icon-search {
	background:
		url('<%=request.getContextPath()%>/images/portal/images/search.png')
		no-repeat left top;
	width: 16px;
	height: 16px;
	display: block;
	border: none;
	outline: 0;
}

button, input {
	overflow: visible;
}
</style>
</head>
<body>
	<div class="header-fixed">
		<div class="header"
			style="background-image: url('<%=request.getContextPath()%>/images/portal/_res/img/bg-header-min.jpg');">
			<div class="container banner-band">
				<img
					src="<%=request.getContextPath()%>/images/portal/_res/img/quochuy.png" />
				<div class="brand-text">
					<h1>BỘ CÔNG AN</h1>
					<h2>CỤC CÔNG NGHỆ THÔNG TIN</h2>
					<h3>DEPARTMENT OF INFORMATION TECHNOLOGY</h3>
				</div>
			</div>
			<div class="banner-carousel">
				<div class="tandan-div-banner">
					<a href=""><img
						src="<%=request.getContextPath()%>/images/portal/Banner/banner_1-min.png"
						alt=""></a>
				</div>
			</div>
			<!-- btn search -->
			<portlet:resourceURL var="resourceURL" />
			<portlet:resourceURL var="getBlogs">
				<portlet:param name="<%=Constants.CMD%>" value="get_blogs" />
			</portlet:resourceURL>
			<div class="div-input" style="position: absolute;top: 0;left: 75%; transform: translateX(5%);z-index: 99;">
				<aui:input id="myInputNode" name="myInputNode" label=""
					helpMessage="Type blogs in Input Box"
					placeholder="Từ khóa tìm kiếm" />
			</div>
			<!-- Menu -->
			<div class="header-menu-wrapper hidden-xs"
				style="background: linear-gradient(#2464b0, #185090, #204f86); box-shadow: inset 0px 1px 0px rgb(255 255 255/ 35%); border-top: 1px solid #204f86;">
				<div
					style="height: 47.219px; z-index: 1; margin: auto; width: 1250px">
					<ul class="header-menu"
						style="justify-content: space-between; display: flex">
						<div>
							<li class="active header-menu-item"><a class="title-menu" href="${url}">
									<span>Giới thiệu</span>
							</a></li>

							<li class="header-menu-item"><a href="${urlPrivate}/lich-cong-tac" class="title-menu"> <span>Lịch
										công tác</span>
							</a></li>
							<li class="header-menu-item"><a class="title-menu"> <span>Thư
										điện tử</span>
							</a></li>
							<li class="header-menu-item"><a class="title-menu"> <span>Điều
										hành tác nghiệp</span>
							</a></li>
							<li class="header-menu-item"><a href="/group/h05/forums" class="title-menu"> <span>Diễn
										đàn</span>
							</a></li>
						</div>

						<li class="active header-menu-item">
								<c:choose>
									<c:when test="${not empty login}"><a href="${login}" style="text-decoration: none;" class="title-menu"><span>Đăng nhập</span></a></c:when>
									<c:otherwise>
										<a href="${logout}" style="text-decoration: none;" class="title-menu"><span>Đăng xuất</span></a>
									</c:otherwise>
								</c:choose>
						</li>


					</ul>
				</div>
				<div class="header-bottom" style="z-index: 100">
					<div style="margin: auto; width: 1200px">
						<span class="lbl">Tin mới: </span>
						<div class="list-news-header">
							<section class="portlet"
								id="portlet_NoticationPut_NoticationPutPortlet">
								<div class="portlet-content">
									<div class="portlet-content-container">
										<div class="portlet-body" style="padding-top: 3px">
											<marquee onmouseover="this.stop()" onmouseout="this.start()">
												<a
													href="https://h05.bca/web/cuch05/tin-chi-tiet/-/chi-tiet/bai-hoc-kinh-nghiem-ve-an-toan-thong-tin-tu-ai-dich-covid-19-699-39.html">
													Bài học kinh nghiệm về an toàn thông tin từ đại dịch
													Covid-19 </a> <a
													href="https://h05.bca/web/cuch05/tin-chi-tiet/-/chi-tiet/youtube-bi-cao-buoc-truyen-tai-thong-tin-sai-lech-698-39.html">
													YouTube bị cáo buộc truyền tải thông tin sai lệch </a> <a
													href="https://h05.bca/web/cuch05/tin-chi-tiet/-/chi-tiet/trung-quoc-yeu-cau-cac-ung-dung-co-anh-huong-phai-uoc-anh-gia-bao-mat-691-39.html">
													Trung Quốc yêu cầu các ứng dụng “có ảnh hưởng” phải được
													đánh giá bảo mật </a> <span>*</span> <a
													href="https://h05.bca/web/cuch05/tin-chi-tiet/-/chi-tiet/-ong-loat-canh-bao-nan-tin-nhan-lua-ao-mao-danh-ngan-hang-bhxh-690-39.html">
													Đồng loạt cảnh báo nạn tin nhắn lừa đảo mạo danh ngân hàng,
													BHXH</a> <span>*</span> <a
													href="https://h05.bca/web/cuch05/tin-chi-tiet/-/chi-tiet/nhung-van-e-at-ra-trong-cong-tac-am-bao-an-ninh-phi-truyen-thong-trong-thoi-ai-4.0-692-39.html">
													Những vấn đề đặt ra trong công tác đảm bảo an ninh phi
													truyền thống trong thời đại 4.0 </a> <span>*</span>
											</marquee>
										</div>
									</div>
								</div>
							</section>
						</div>
					</div>
				</div>
			</div>
			<!-- END Menu -->
		</div>
		<script
				src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

			<script>
AUI().use('autocomplete-list','aui-base','aui-io-request','autocomplete-filters','autocomplete-highlighters',function (A) {
var testData="";
new A.AutoCompleteList({
allowBrowserAutocomplete: 'false',
activateFirstItem: 'false',
inputNode: '#<portlet:namespace />myInputNode',
resultTextLocator:'main',
render: 'false',
resultHighlighter: 'phraseMatch',
resultFilters:['phraseMatch'],
source:function(){			
			var inputValue=A.one("#<portlet:namespace />myInputNode").get('value');	
			$(".div-input input").on('keyup',function(e){
				if(inputValue){
					
					if (e.key === 'Enter' || e.keyCode === 13) {
						window.location.href = "${url}"+"/search?keyword="+inputValue;
				    }
				}
			});
			$("#_ctl00_ctl43_ctl00_cmd").on('click',function(){
				window.location.href = "${url}"+"/"+inputValue;
			});
			var myAjaxRequest=A.io.request('<%=getBlogs.toString()%>',
														{
															dataType : 'json',
															method : 'POST',
															data : {
																<portlet:namespace />titleBlogs : inputValue
															},
															autoLoad : false,
															sync : false,
															on : {
																success : function() {
																	var html = "";
																	var data = this
																			.get('responseData');
																	testData = data;

																}
															}
														});
										myAjaxRequest.start();
										return testData;
									},
								});
					});
</script>
</body>
</html>
