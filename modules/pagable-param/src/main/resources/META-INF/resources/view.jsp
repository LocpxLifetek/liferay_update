<%@ include file="/init.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<p>
	<b><liferay-ui:message key="pagableparam.caption" /></b>
</p>


<%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
<table border="1" cellpadding="5" cellspacing="5">
	<tr>
		<c:forEach begin="1" end="${total_page}" var="i">
			<c:choose>
				<c:when test="${current_page eq i }">
					<td>${i}</td>
				</c:when>
				<c:otherwise>
					<c:if test="${(current_page-3) lt i and i lt (current_page + 3)}">
						<td><a
							href="http://localhost:8080/en/web/lifetek/new-page?page=${i}">${i}</a></td>
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</tr>
</table>

<div class="vanban">
	<h3 class="title">Giới thiệu văn bản mới</h3>
	<c:forEach items="${blogEntrise}" var="blogEntry">

		<div class="vanbanduthaoitem ">

			<a
				href="/van-ban/van-ban-moi/bo-cong-an-lay-y-kien-ve-du-thao-thong-tu-quy-dinh-dich-vu-duoc-khai-thac-su-dung-thong-tin-trong-co-so-du-lieu-quoc-gia-ve-dan-cu-558.html"
				class="vbdt-image"> <img
				src="http://172.16.100.75:8080/documents/${blogEntry.groupId}/${blogEntry.folderId}/${blogEntry.titleDl}/${blogEntry.uuidDl}"
				alt=""></a>

			<div class="sTitle">
				<div class="title">
					<a
						href="/van-ban/van-ban-moi/bo-cong-an-lay-y-kien-ve-du-thao-thong-tu-quy-dinh-dich-vu-duoc-khai-thac-su-dung-thong-tin-trong-co-so-du-lieu-quoc-gia-ve-dan-cu-558.html">Bộ
						${blogEntry.title}</a>
						<span>(07/07/2021) </span>
				</div>
				<div class="vbdt-info">
					<div class="desc">${blogEntry.description}</div>

				</div>
			</div>
		</div>

	</c:forEach>

</div>

<div class="paging">
	<table border="1" cellpadding="5" cellspacing="5">
		<tr>

			<%--For displaying Previous link except for the 1st page --%>
			<c:if test="${current_page != 1}">
				<td><a
					href="view.jsp?page=1">First</a></td>
			</c:if>

			<%--For displaying Previous link except for the 1st page --%>
			<c:if test="${current_page != 1}">
				<td><a
					href="view.jsp?page=${current_page - 1}">Previous</a></td>
			</c:if>

			<c:if test="${current_page gt 3}">
				<td><span>...</span></td>
			</c:if>
			<c:forEach begin="1" end="${total_page}" var="i">
				<c:choose>
					<c:when test="${current_page eq i }">
						<td>${i}</td>
					</c:when>
					<c:otherwise>

						<c:if test="${(current_page-3) lt i and i lt (current_page + 3)}">
							<td><a
								href="view.jsp?page=${i}">${i}</a></td>
						</c:if>

					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${current_page lt (total_page-3)}">
				<td><span>...</span></td>
			</c:if>
			<%--For displaying Next link --%>
			<c:if test="${current_page lt total_page}">
				<td><a
					href="view.jsp?page=${current_page + 1}">Next</a></td>
			</c:if>
			<%--For displaying Next link --%>
			<c:if test="${current_page lt total_page}">
				<td><a
					href="view.jsp?page=${total_page}">End</a></td>
			</c:if>
		</tr>
	</table>
</div>
