<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="menu_1">
	<div class="box-dieuhuong">
		<div class="trangchu">
			<a href="/"
				style="color: #333;
						    font-size: 13px;
						    padding: 0 12px 0 0px;
						    background: url('<%=request.getContextPath()%>/images/portal/_res/img/i-right.png') no-repeat right center;">Trang
				chủ</a>
		</div>
		<div class="trangchu">
			<a href="#"
				style="font-size: 13px; color: #0a4298 !important; padding: 0px 0px 0px 10px;">Tìm
				kiếm</a>
		</div>
	</div>
	<div class="listDirectOperation">
		<div class="listDirectOperationRight">
			<c:choose>
				<c:when test="${not empty error}">
					<div class="title">
						<h1>Không tìm thấy kết quả phù hợp</h1>
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach items="${listSearchBlogsDto}" var="blog">

						<div class="listDirectOperationGrid">
							<div class="ldo">
								<img src="${blog.src}" class="soup-image"
									style="width: 30% !important"> <a
									href="${url}/chitiettintuc?uuid=${blog.uuid}">${blog.title}</a>
								<span
									style="font: 400 12px Roboto, Arial, Tahoma; padding-left: 25px;"><fmt:formatDate
										value="${blog.modifiedDate}" pattern="MM/dd/yyyy" /> </span> <br>
								<p>${blog.description}</p>
							</div>
						</div>

					</c:forEach>
					<div id="wrapper">
						<ul id="pagination">
							<c:if test="${currentPage != 1}">
								<li><a
									href="${url}/search?keyword=${keyword}&page=${currentPage - 1}">«</a></li>
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
											<li><a href="${url}/search?keyword=${keyword}&page=${i}">${i}</a>
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
									href="${url}/theloaitintucsukien?keyword=${keyword}&page=${currentPage + 1}">»</a>
								</li>
							</c:if>

						</ul>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>