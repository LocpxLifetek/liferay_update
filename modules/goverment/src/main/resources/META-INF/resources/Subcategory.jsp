<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>

      .head-cm ul li:first-child {
        background: url('<%= request.getContextPath()%>/images/portal/images/bg-chuyenmuc.png')
          no-repeat right top;
      }

</style>
<div class="menu_1">
	<div class="head-cm">
		<ul>
			<li><a href="#">${categoryDto.name}</a></li>
		</ul>
	</div>
	<div class="listDirectOperation">
		<div class="listDirectOperationRight">
					<c:forEach items="${listBlogs}" var="blog">

						<div class="listDirectOperationGrid">
							<div class="ldo">
							<a href="${url}/chitiettintuc?uuid=${blog.uuidBlogsEntry}">
								<img src="${blog.src}" class="soup-image"
									style="width: 30% !important"> </a>
									<a href="${url}/chitiettintuc?uuid=${blog.uuidBlogsEntry}">${blog.titleBlogsEntry}</a>
								<span style="color: black; font-size: 12px;">(<fmt:formatDate
										value="${blog.modifiedDate}" pattern="MM/dd/yyyy" />)
								</span> <br>
								<p>${blog.description}</p>
							</div>
						</div>

					</c:forEach>
		</div>
	</div>
</div>
<div id="wrapper">
	<ul id="pagination">
		<c:if test="${currentPage != 1}">
			<li><a
				href="${url}/detail?uuid=${uuid}&page=${currentPage - 1}">«</a></li>
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
						<li><a href="${url}/detail?uuid=${uuid}&page=${i}">${i}</a>
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
				href="${url}/detail?uuid=${uuid}&page=${currentPage + 1}">»</a>
			</li>
		</c:if>

	</ul>
</div>





