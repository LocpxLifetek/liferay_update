<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.blogs.model.BlogsEntry"%>
<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui"%>
<%@ taglib prefix="liferay-comment" uri="http://liferay.com/tld/comment"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.head-cm ul li:first-child {
	background:
		url("<%=request.getContextPath()%>/images/portal/_res/img/bg-chuyenmuc.png")
		no-repeat right top;
}
</style>
<div class="detailBlogs">
	<div class="menu-submenu">

		<div class="box-dieuhuong">
			<div class="trangchu">
				<a href="${url}"
					style="color: #333;
						    font-size: 13px;
						    padding: 0 12px 0 0px;
						    background: url('<%=request.getContextPath()%>/images/portal/_res/img/i-right.png') no-repeat right center;">Trang
					chủ</a>
			</div>
			<c:choose>
				<c:when test="${not empty assetCategory2}">
					<div class="trangchu">
						<a href="${url}/tintuc?uuid=${assetCategory.uuid}"
							style="color: #333;
						    font-size: 13px;
						    padding: 0 12px 0 10px;
						    background: url('<%=request.getContextPath()%>/images/portal/_res/img/i-right.png') no-repeat right center;">${assetCategory.name}</a>
					</div>

					<div class="trangchu">
						<a href="${url}/detail?uuid=${assetCategory2.uuid}"
							style="font-size: 13px; color: #0a4298 !important; padding: 0px 0px 0px 10px;">${assetCategory2.name}</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="trangchu">
						<a href="${url}/tintuc?uuid=${assetCategory.uuid}"
							style="font-size: 13px; color: #0a4298 !important; padding: 0px 0px 0px 10px;">${assetCategory.name}</a>
					</div>
				</c:otherwise>
			</c:choose>

		</div>
	</div>
	<div class="head-cm" style="margin-top: 10px">
		<c:choose>
			<c:when test="${not empty assetCategory2}">
				<ul>
					<li><a href="${url}/detail?uuid=${assetCategory2.uuid}">${assetCategory2.name}</a></li>
				</ul>
			</c:when>
			<c:otherwise>
				<ul>
					<li><a href="${url}/tintuc?uuid=${assetCategory.uuid}">${assetCategory.name}</a></li>
				</ul>
			</c:otherwise>
		</c:choose>

	</div>
	<div class="title">
		<h1>${blogsEntry.title}</h1>
		<span
			style="font: 400 12px Roboto,Arial,Tahoma;
   				 background: url('<%=request.getContextPath()%>/images/portal/_res/img/i-time.png') no-repeat left center;padding-left: 25px;"><fmt:formatDate
				value="${blogsEntry.modifiedDate}" pattern="dd/MM/yyyy" /> </span>
		<div style="border-bottom: 1px solid #333;"></div>
	</div>
	<div class="description">
		<h4
			style="font-weight: bold; text-align: justify !important; display: block;">${blogsEntry.description}</h4>
	</div>
	<div class="contentBlogs">${blogsEntry.content}</div>
</div>

<c:if test="${not empty listAssetTag}">
	<div class="tags"
		style="display: inline-block;
    padding: 10px 0 0;
    background: url('<%=request.getContextPath()%>/images/portal/_res/img/tags.png') no-repeat left 4px;
    padding-left: 30px;">
		<c:forEach items="${listAssetTag}" var="assetTag">
			<ul style="display: flex">

				<li><a href="${url}/tag?uuid=${assetTag.uuid}">
						${assetTag.name},</a></li>

			</ul>
		</c:forEach>
	</div>
</c:if>
