<%@ include file="/init.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui"%>
<%@ taglib prefix="liferay-comment" uri="http://liferay.com/tld/comment"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="detailBoCongAn">
	

		<div class="menu-submenu">

			<div class="box-dieuhuong">
				<div class="trangchu">
					<a href="/">Trang chủ</a> <img
						src="http://bocongan.gov.vn/Publishing_Resources/web/portal/images/i-right.png">
				</div>
				<div class="trangchu">
					<a href="tintucsukien">Tin tức sự kiện</a> <img
						src="http://bocongan.gov.vn/Publishing_Resources/web/portal/images/i-right.png">
				</div>
				<div class="trangchu">
					<a href="/web/lifetek/theloaitintucsukien?id=${listBlogsEntryDto.uuidCategory}"
						style="color: red !important;">${listBlogsEntryDto.nameCategory}</a>
				</div>
			</div>
		</div>
		<br>
		<div class="detailBlogs">
			<div class="title">
				<h2>${blogs.title}</h2>
				<span style="color: #000000; opacity: 0.6;"><fmt:formatDate
						value="${listBlogsEntryDto.modifiedDate}" pattern="dd/MM/yyyy HH:mm:ss" /></span>
				<hr>
			</div>
			<div class="description">
				<h4 style="font-weight: bold;">${listBlogsEntryDto.description}</h4>
			</div>
			<div class="contentBlogs">${listBlogsEntryDto.content}</div>
		</div>
	

</div>