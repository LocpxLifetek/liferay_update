<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="menu_1">
	<div class="head-cm">
		<ul>
			<li><a href="/tin-tuc-su-kien/thong-tin-doi-ngoai-20.html">${assetCategory.name}</a></li>
		</ul>
	</div>
	<div class="listDirectOperation">
		<div class="listDirectOperationRight">
			<c:choose>
				<c:when test="${not empty error}">
					<img src="https://www.totolink.vn/public/uploads/img_post/truy-tim-nguyen-nhan-va-cach-sua-chua-loi-tra-cuu-404-not-found-1.jpg" style="width:100%">
				</c:when>
				<c:otherwise>
					<c:forEach items="${listBlogsEntryDto}" var="blog">

						<div class="listDirectOperationGrid">
							<div class="ldo">
								<img src="${blog.srcImage}" class="soup-image"
									style="width: 30% !important"> <a
									href="/web/lifetek/chitiettintuc?id=${blog.uuidBlogs}">${blog.titleBlogs}</a>
								<span style="color: #d71920; font-size: 12px;">(<fmt:formatDate
										value="${blog.modifiedDate}" pattern="MM/dd/yyyy" />)
								</span> <br>
								<p>${blog.description}</p>
							</div>
						</div>

					</c:forEach>
					<div id="wrapper">
						<ul id="pagination">
							<c:if test="${currentPage != 1}">
								<li><a
									href="/web/lifetek/theloaitintucsukien?uuid=${uuid}&page=${currentPage - 1}">«</a></li>
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
										<c:if
											test="${(currentPage-3) lt i and i lt (currentPage + 3)}">
											<li><a
												href="/web/lifetek/theloaitintucsukien?uuid=${uuid}&page=${i}">${i}</a>
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
									href="/web/lifetek/theloaitintucsukien?uuid=${uuid}&page=${currentPage + 1}">»</a>
								</li>
							</c:if>

						</ul>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>





