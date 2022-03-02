+<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<div>
	<a href="${url}/tin-anh?uuid=${category.uuid}" title="${category.name}">
        <img class="promotional_photo" src="${dlfile.src}" alt="${category.name}">
    </a>
</div>