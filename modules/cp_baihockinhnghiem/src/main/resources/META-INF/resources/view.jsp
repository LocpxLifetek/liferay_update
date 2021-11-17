<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="cp_bhkn">
	<div class="cp_baihockinhnghiem">${assetCategory1.getName()}</div>
	<div style="border: white solid 1px;"></div>
	<div style="border: rgb(207, 205, 205) solid 1px;">
		<ul>
			<c:forEach items="${journalArticleDisplays1}" var="article1">
				<li><a style="color: black;"
					href="http://portal.lifetek.vn/web/lifetek/chinhphu/tulieu?id=${article1.getResourcePrimKey()}&categoryId=${assetCategory1.getCategoryId()}">
						${article1.getTitle()} </a></li>

			</c:forEach>
		</ul>
	</div>
	<div style="border: white solid 1px;height: 5px;"></div>
	<div class="cp_baihockinhnghiem">${assetCategory2.getName()}</div>
	<div style="border: white solid 1px;"></div>
	<div style="border: rgb(207, 205, 205) solid 1px;">
		<ul>
			<c:forEach items="${journalArticleDisplays2}" var="article2">
				<li><a style="color: black;"
					href="http://portal.lifetek.vn/web/lifetek/chinhphu/tulieu?id=${article2.getResourcePrimKey()}&categoryId=${assetCategory2.getCategoryId()}">
						${article2.getTitle()} </a></li>

			</c:forEach>
		</ul>
	</div>
</div>