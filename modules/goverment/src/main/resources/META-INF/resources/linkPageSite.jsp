<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<style>
.hn {
	
}

.hn ul {
	display: grid;
	grid-template-columns: 50% 50%;
	list-style: none;
}

.hn ul li a {
	padding-left: 13px;
	text-align: justify;
	cursor: pointer;
	color: #111 !important;
	font-weight: 400;
	text-decoration: none;
}

.hn ul li:hover {
	color: #004175;
}
</style>
<div class="hn block border has-icon">
	<div class="title">
		<a href='#'>Tỉnh - huyện - xã</a>
	</div>
	<div class="body" style="margin-top:5px;">
		<ul>
			<c:forEach items="${listGroupSiteDtos}" var="groupSiteDtos">
				<li><a href='${groupSiteDtos.src}'
					style="background:url('<%=request.getContextPath()%>/images/portal/_themes/ThanhHoa_orange/img/hv-module-tvt.PNG') no-repeat top left; background-position-y: 5px;">${groupSiteDtos.name}</a></li>
			</c:forEach>

		</ul>
	</div>
</div>