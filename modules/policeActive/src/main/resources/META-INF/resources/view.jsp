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
				<a href="/theloaitintucsukien?uuid=${assetCategory.uuid}">Hoạt
					động của lực lượng Công an</a> <img
					src="http://bocongan.gov.vn/Publishing_Resources/web/portal/images/i-right.png">
			</div>
			<div class="trangchu">
				<a href="#" style="color: red !important;">${assetCategory2.name}</a>
			</div>
		</div>

	</div>
</div>
<div class="menu_1">
	<div class="head-cm">
		<ul>
			<li><a href="/tin-tuc-su-kien/thong-tin-doi-ngoai-20.html">${assetCategory2.name}</a></li>
		</ul>
	</div>
	<div class="listDirectOperation">
		<div class="listDirectOperationRight">
			<c:forEach items="${listBlogsEntries}" var="blog">
				<c:choose>
					<c:when test="${blog.smallImageFileEntryId > 0}">
						<c:forEach items="${listDLFileEntries}" var="si">
							<c:if test="${blog.smallImageFileEntryId == si.fileEntryId}">
								<div class="listDirectOperationGrid">
									<div class="ldo">
										<img
											src="/documents/${si.groupId}/${si.folderId}/${si.title}/${si.uuid}"
											class="soup-image" style="width: 30% !important"> <a
											href="/web/lifetek/chitiettintuc?id=${blog.uuid}">${blog.title}</a>
										<span style="color: #d71920; font-size: 12px;">(<fmt:formatDate
												value="${blog.modifiedDate}" pattern="MM/dd/yyyy" />)
										</span> <br>
										<p>${blog.description}</p>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div>
							<a href="/web/lifetek/chitiettintuc?id=${blog.uuid}">${blog.title}</a>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
	</div>
</div>