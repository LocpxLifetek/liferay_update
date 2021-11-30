<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>





<c:forEach items="${maps}" var="map">
	<div class="menu_1">
		<c:forEach items="${map.key}" var="mapKey">

			<div class="head-cm">
				<ul>
					<li><a
						href="/web/lifetek/theloaitintucsukien?uuid=${mapKey.uuid}">${mapKey.name}</a></li>
				</ul>
				<a href="/tin-tuc-su-kien/thong-tin-doi-ngoai-20.html"
					class="xemthem">Xem thêm</a>
			</div>
		</c:forEach>
		<div class="directOperator">
			<c:forEach items="${map.value}" var="mapValue">
				<div class="directOperationLeft">
					<a href="/web/lifetek/chitiettintuc?id=${mapValue.uuidBlogsEntry}"
						style="font-size: 16px">${mapValue.titleBlogsEntry}</a> <img
						src="/documents/${mapValue.groupId}/${mapValue.folderId}/${mapValue.titleDlFileEntry}/${mapValue.uuidDlFileEntry}"
						class="soup-image">

					<p>${mapValue.description}</p>
				</div>
			</c:forEach>
		</div>
	</div>
</c:forEach>

