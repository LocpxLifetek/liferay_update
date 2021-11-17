<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="cp_chucnangnhiemvu">CHỨC NĂNG NHIỆM VỤ</div>
<div style="border: rgb(207, 205, 205) solid 1px;">
	<ul>
		<c:forEach items="${journalArticles}" var="article">
			<li><a
				style="color:black;"
				href="http://localhost:8080/web/lifetek/chinhphu/noidungchucnangnhiemvu?id=${article.getResourcePrimKey()}">
					${article.getTitle()} </a></li>

		</c:forEach>
	</ul>
</div>