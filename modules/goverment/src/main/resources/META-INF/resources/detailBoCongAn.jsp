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
				<a href="${url}/tintucsukien">${assetCategory.name}</a> <img
					src="http://bocongan.gov.vn/Publishing_Resources/web/portal/images/i-right.png">
			</div>
			<c:if test="${not empty listAssetCategory}">
				<c:forEach items="${listAssetCategory}" var="assetCategory">
					<div class="trangchu">
						<a href="${url}/theloaitintucsukien?id=${assetCategory.uuid}"
							style="color: red !important;">${assetCategory.name}</a> <img
							src="http://bocongan.gov.vn/Publishing_Resources/web/portal/images/i-right.png">
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${not empty asset}">
				<div class="trangchu">
					<a href="${url}/theloaitintucsukien?id=${asset.uuid}"
						style="color: red !important;">${asset.name}</a>
				</div>
			</c:if>
		</div>
	</div>
	<div class="head-cm">
		<c:if test="${not empty assetCategory2 }">

			<ul>
				<li><a href="${url}/tintucsukien">${assetCategory2.name}</a></li>
			</ul>
		</c:if>
		<c:if test="${not empty asset}">
			<ul>
				<li><a href="${url}/theloaitintucsukien?id=${assetCategory.uuid}">${asset.name}</a></li>
			</ul>
		</c:if>
	</div>
	<div class="detailBlogs">
		<div class="title">
			<h2>${blogsEntry.title}</h2>
			<span style="color: #000000; opacity: 0.6;"><fmt:formatDate
					value="${blogsEntry.modifiedDate}" pattern="dd/MM/yyyy HH:mm:ss" /></span>
			<hr>
		</div>
		<div class="description">
			<h4 style="font-weight: bold;">${blogsEntry.description}</h4>
		</div>
		<div class="contentBlogs">${blogsEntry.content}</div>
	</div>


</div>