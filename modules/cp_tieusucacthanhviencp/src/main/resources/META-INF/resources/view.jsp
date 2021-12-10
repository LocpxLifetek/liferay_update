<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="cp_tstvcp_wrapper">
	<div class="cp_tstvcp_item1">
		<div class="cp_tstvcp">${tieusutvcp.getName()}</div>
		<div style="border: rgb(207, 205, 205) solid 1px;">
			<ul>
				<li><a href="${LINK_TTCPCNK}" style="color: black;">${tieusu1.getTitle()}</a></li>
				<c:forEach items="${displayTieusu}" var="d1">
					<li><a style="color: black;"
						href="${LINK_TVCPQCTK}${d1.getResourcePrimKey()}">
							${d1.getTitle()} </a></li>
				</c:forEach>
			</ul>
		</div>
	</div>


	<div class="cp_tstvcp_item2">
		<div class="cp_tstvcp">${chinhphuqctk.getName()}</div>
		<div style="border: rgb(207, 205, 205) solid 1px;">
			<ul>
				<c:forEach items="${displayChinhphu}" var="d2">
					<li><a style="color: black;"
						href="${LINK_TVCPQCTK}${d2.getResourcePrimKey()}">
							${d2.getTitle()} </a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
