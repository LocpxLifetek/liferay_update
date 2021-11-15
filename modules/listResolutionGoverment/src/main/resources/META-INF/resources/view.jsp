<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="tableGoverment" cellspacing="0" cellpadding="5"
	bordercolor="#ccc" border="1" style="border-collapse: collapse;">
	<tbody>
		<tr>
			<td class="doc_list_title_date">Ngày Ban Hành</td>
			<td class="doc_list_title_substract">Trích Yếu</td>
			<td class="doc_list_title_notation">Số/KýHiệu</td>
		</tr>

		<c:forEach items="${maps}" var="mapGoverment">
			<tr>
				<c:forEach items="${mapGoverment}" var="map">
					<c:forEach items="${map.value}" var="valueGoverment">					
						<c:choose>
							<c:when test="${valueGoverment.key == 'TríchYếu'}">
								<td style="padding: 3px;"><a
									href="/web/lifetek/chi-tiết-nghị-quyết-chính-phủ?class_id=${valueGoverment.id}">${valueGoverment.value}</a></td>

							</c:when>
							<c:otherwise>
								<td style="text-align: center;">${valueGoverment.value}</td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
				</c:forEach>
			</tr>
		</c:forEach>
	</tbody>

</table>
<div id="wrapper">
	<ul id="pagination">
		<c:if test="${currentPage != 1}">
			<li><a
				href="/web/lifetek/nghị-quyết-của-chính-phủ?page=${currentPage - 1}">«</a></li>
		</c:if>
		<c:if test="${currentPage gt 3}">
			<li><span>...</span></li>
		</c:if>
		<c:forEach begin="1" end="${totalPage}" var="i">
			<c:choose>
				<c:when test="${currentPage eq i}">
					<li><a href="#">${i}</a>
					<li>
				</c:when>
				<c:otherwise>
					<c:if test="${(currentPage-3) lt i and i lt (currentPage + 3)}">
						<li><a
							href="/web/lifetek/nghị-quyết-của-chính-phủ?page=${i}">${i}</a>
						</li>
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${currentPage lt (totalPage-3)}">
			<li><span>...</span></li>
		</c:if>
		<c:if test="${currentPage lt totalPage}">
			<li><a
				href="/web/lifetek/nghị-quyết-của-chính-phủ?page=${currentPage + 1}">»</a></li>
		</c:if>

	</ul>
</div>