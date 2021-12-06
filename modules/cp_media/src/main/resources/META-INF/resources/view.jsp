<%@page
	import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil"%>
<%@page import="com.liferay.document.library.kernel.model.DLFileEntry"%>
<%@ include file="/init.jsp"%>

<%
	
%>
<div class="videoWrapper">
	<div class="vd_item1">
		<video class="cp_media" controls preload="auto" autoplay="autoplay"
			width="100%" data-setup="{}">
			<source src="${srcVideo}" type="video/mp4">
		</video>
		<h3>${fileName}</h3>
	</div>
	
	<div class="vd_item2 vdWrapper_Con">
		<div class="vd_item">
			<h2>${NameCategory}</h2>
		</div>
		<div class="vd_item1">
			<img class="cp_media" src="${srcAnh1}" width="100%">
		</div>
		<div class="vd_item2">
			<strong><a href="${srcVideo1}">${title1}</a></strong>
		</div>

		<div class="vd_item1">
			<img class="cp_media" src="${srcAnh2}" width="100%">
		</div>
		<div class="vd_item2">
			<strong><a href="${srcVideo2}">${title2}</a></strong>
		</div>

		<div class="vd_item1">
			<img class="cp_media" src="${srcAnh3}" width="100%">
		</div>
		<div class="vd_item2">
			<strong><a href="${srcVideo3}">${title3}</a></strong>
		</div>

		<div class="vd_item1">
			<img class="cp_media" src="${srcAnh4}" width="100%">
		</div>
		<div class="vd_item2">
			<strong><a href="${srcVideo4}">${title4}</a></strong>
		</div>

		<div class="vd_item1">
			<img class="cp_media" src="${srcAnh5}" width="100%">
		</div>
		<div class="vd_item2">
			<strong><a href="${srcVideo5}">${title5}</a></strong>
		</div>

		<div class="vd_item1">
			<img class="cp_media" src="${srcAnh6}" width="100%">
		</div>
		<div class="vd_item2">
			<strong><a href="${srcVideo6}">${title6}</a></strong>
		</div>
	</div>
</div>

