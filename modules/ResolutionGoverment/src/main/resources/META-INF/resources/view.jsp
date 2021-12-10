<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>



<p style="font-weight:bold; font-size:14px; text-align: justify;">Nghị quyết số ${map.get("Số Ký Hiệu")} của ${map.get("Cơ Quan Ban Hành")}: ${map.get("Trích Yếu") } </p> 
	
<table class="doc_detail_attr_table">
	<tbody>
		<c:forEach items="${map}" var="mapGoverment">
			<tr>
				<td style="font-weight: bold;">${mapGoverment.key}</td>
				<td style="text-align: justify;">${mapGoverment.value}</td>
			</tr>
		</c:forEach>

	</tbody>
</table>
<br>
<div class="doc_detail_others">
	<img
		src="http://chinhphu.vn/templates/vanbanphapquy/images/icons/others.gif" />
	<p>Văn bản khác</p>
</div>
<c:forEach items="${listJournalArticleLocazationDto}"
	var="journalArticleLocazation">
	<div class="doc_detail_others_item"
		style="display: block;text-align: justify; background: url(http://chinhphu.vn/templates/vanbanphapquy/images/icons/item.gif) no-repeat scroll 0px 2px; padding-left: 15px;">
		<a
			href="${url}/chi-tiết-nghị-quyết-chính-phủ?class_id=${journalArticleLocazation.id}">${journalArticleLocazation.title}</a>
	</div>
</c:forEach>