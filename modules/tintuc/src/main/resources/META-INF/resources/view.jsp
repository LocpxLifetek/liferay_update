<%@ include file="/init.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui"%>
<%@ taglib prefix="liferay-comment" uri="http://liferay.com/tld/comment"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="tintuc_sukien">
	<div class="title_ttsk">
		<h3>Tin tức - sự kiện</h3>
		<div class="linered"></div>
	</div>

	<div class="tintuc_sukien_top">
		<ul class="ttsk">
			<c:forEach items="${listAssetCategory}" var="assetCategory">
				<c:choose>
					<c:when test="${listAssetCategory3.size() > 0 }">
						<li><a href="#" title="${assetCategory.name}">${assetCategory.name}</a>
							<ul class="parentCategory">

								<c:forEach items="${listAssetCategory3}" var="assetCategory3">
									<c:if
										test="${assetCategory.id == assetCategory3.parentCategoryId}">
										<li><a href="#" title="${assetCategory3.name}">${assetCategory3.name}</a></li>
									</c:if>
								</c:forEach>
							</ul></li>
					</c:when>
					<c:otherwise>
						<li><a href="#" title="${assetCategory.name}">${assetCategory.name}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
	</div>
</div>