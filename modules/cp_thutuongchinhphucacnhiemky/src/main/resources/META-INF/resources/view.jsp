<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="cp_tstvcp_wrapper">
	<div class="cp_tstvcp_item1">
		<div class="cp_tstvcp">${tieusu1.getTitle()}</div>
		<div style="border: rgb(207, 205, 205) solid 1px;">
			${tieusu1.getContent()}</div>
	</div>


	<div class="cp_tstvcp_item2">
		<div class="cp_tstvcp">${tieusutvcp.getName()}</div>
		<div style="border: rgb(207, 205, 205) solid 1px;">
			<ul>
				<li><a href="http://portal.lifetek.vn/web/lifetek/chinhphu/thutuongchinhphucacnhiemky" style="color: black;">${tieusu1.getTitle()}</a></li>
				<c:forEach items="${displayTieusu}" var="d1">
					<li><a style="color: black;"
						href="http://portal.lifetek.vn/web/lifetek/chinhphu/thanhvienchinhphuquacacthoiky?priKey=${d1.getResourcePrimKey()}">
							${d1.getTitle()} </a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>