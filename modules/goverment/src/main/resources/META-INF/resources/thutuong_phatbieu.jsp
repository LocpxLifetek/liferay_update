
<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<div width="100%" height="100%">
	<div class="bn_lanhdao">
		<h6 front-size="4px">Các bài phát biểu của Thủ tướng</h6>
	</div>
	<div class="lanhdao_content">
		<c:forEach items="${listBlogsEntryDtos}" var="u">
			<div style="width: 100%" style="display:flex">
				<div style="width: 100%" align="justify">
					<a padding="3px" class="pb"> ${u.titleBlogsEntry}</a>
				</div>
			</div>
		</c:forEach>
	</div>
</div>