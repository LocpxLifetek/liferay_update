<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="cp_cstt_wrapper">
	<div class="cp_cstt_item1">
		<div class="cp_cstt">${assetCategory.getName()}</div>
		<div style="border: rgb(207, 205, 205) solid 1px;">
			<h2>${articleDisplay.getTitle()}</h2>
			${articleDisplay.getContent()}
		</div>
	</div>
	<div class="cp_cstt_item2">
		<div class="cp_cstt">${assetCategory.getName()}</div>
		<div style="border: rgb(207, 205, 205) solid 1px;">
			<ul>
				<c:forEach items="${articleDisplays}" var="article">
					<li><a style="color: black;"
						href="${}${assetCategory.getCategoryId()}&priKey=${article.getResourcePrimKey()}">
							${article.getTitle()} </a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>