<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="cp_tvcp_wrapper">
	<div class="cp_tvcp_item1">
		<div class="cp_tvcp">${articleDisplay.getTitle()}</div>
		<div style="border: rgb(207, 205, 205) solid 1px;">
			${articleDisplay.getContent()}</div>
	</div>
	<div class="cp_tvcp_item2">
		<div class="cp_tvcp">${assetCategory.getName()}</div>
		<div style="border: rgb(207, 205, 205) solid 1px;">
			<ul>
				<c:forEach items="${articleDisplays}" var="article">
					<li><a style="color: black;"
						href="http://localhost:8080/web/lifetek/chinhphu/thanhvienchinhphuquacacthoiky?priKey=${article.getResourcePrimKey()}">
							${article.getTitle()} </a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>