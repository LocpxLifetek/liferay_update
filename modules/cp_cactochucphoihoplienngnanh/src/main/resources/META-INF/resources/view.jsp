<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="cp_cactochucphoihoplienngnanh">${assetCategory.getName()}</div>
<div style="border: rgb(207, 205, 205) solid 1px;">
	<ul>
		<c:forEach items="${articleDisplays}" var="article">
			<li><a style="color: black;"
				href="http://localhost:8080/web/lifetek/chinhphu/gioithieubanchidao?name=${name}&categoryId=${assetCategory.getCategoryId()}&priKey=${article.getResourcePrimKey()}">
					${article.getTitle()} </a></li>
		</c:forEach>
	</ul>
</div>