<%@ include file="/init.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui"%>
<%@ taglib prefix="liferay-comment" uri="http://liferay.com/tld/comment"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="detailBoCongAn">
	<div class="menu-submenu">
		<c:if test="${not empty assetCategory2}">
			<c:choose>
				<c:when test="${not empty assetCategory3}">
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
							<a href="/web/lifetek/theloaitintucsukien?id=${assetCategory2.uuid}">${assetCategory2.name}</a> <img
								src="http://bocongan.gov.vn/Publishing_Resources/web/portal/images/i-right.png">
						</div>
						<div class="trangchu">
							<a href="#" style="color:red !important;">${assetCategory3.name}</a>
						</div>
					</div>
				</c:when>
				<c:otherwise>
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
							<a href="/web/lifetek/theloaitintucsukien?id=${assetCategory2.uuid}" style="color:red !important;">${assetCategory2.name}</a>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
		<c:if test="${not empty assetCategory}">
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
					<a href="/web/lifetek/theloaitintucsukien?id=${assetCategory.uuid}" style="color:red !important;">${assetCategory.name}</a>
				</div>
			</div>
		</c:if>
	</div>
</div>
<div class="detailBlogs">
	<div class="title">
		<h2>${blogsEntry.title}</h2>
		<span style="color:#000000;opacity: 0.6;">(${time})</span>
		<hr>
	</div>
	<div class="description">
		<h4 style=" font-weight: bold;">${blogsEntry.description}</h4>
	</div>
	<div class="contentBlogs">
		${blogsEntry.content}
	</div>
</div>