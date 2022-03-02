<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

				<div class="redirec" style="padding-bottom: 10px;">
					<a href="${url}"
					style="color: #333;
						    font-size: 13px;
						    padding: 0 12px 0 0px;
						    background: url('<%=request.getContextPath()%>/images/portal/_res/img/i-right.png') no-repeat right center;">Trang
					chủ</a>
			<c:choose>
				<c:when test="${empty category.uuid}">
					<a href="${url}/media"
					style="color: #0a4298 !important;
						    font-size: 13px;
						    padding: 0 12px 0 0px;
						   ">Media</a>
				</c:when>
				<c:otherwise>
						   <a href="${url}/media"
					style="color: #333 !important;
						    font-size: 13px;
						    padding: 0 12px 0 0px;
						    background: url('<%=request.getContextPath()%>/images/portal/_res/img/i-right.png') no-repeat right center;
						   ">Media</a>
				<a style="color: #0a4298 !important;
						    font-size: 13px;
						    padding: 0 12px 0 0px;
						   ">${category.name}</a>
				</c:otherwise>
			</c:choose>
				</div>