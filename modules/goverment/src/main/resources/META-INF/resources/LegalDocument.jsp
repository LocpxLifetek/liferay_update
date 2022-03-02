<%@ include file="/init.jsp" %>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="tableGoverment" cellspacing="0" cellpadding="5"
	bordercolor="#ccc" border="1" style="border-collapse: collapse;">
	<tbody>
		<tr>
			<td class="doc_list_title_date">Ngày Ban Hành</td>
			<td class="doc_list_title_substract">Trích Yếu</td>
			<td class="doc_list_title_notation">Số/KýHiệu</td>
		</tr>

		<c:forEach items="${maps}" var="mapGoverment">
			<tr>
				<c:forEach items="${mapGoverment}" var="map">
					<c:forEach items="${map.value}" var="valueGoverment">					
						<c:choose>
							<c:when test="${valueGoverment.key == 'TríchYếu'}">
								<td style="padding: 3px;"><a
									href="${url}/chi-tiet-van-ban-phap-quy?class_id=${valueGoverment.id}">${valueGoverment.value}</a></td>

							</c:when>
							<c:otherwise>
								<td style="text-align: center;">${valueGoverment.value}</td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
				</c:forEach>
			</tr>
		</c:forEach>
	</tbody>

</table>
<div id="wrapper">
	<ul id="pagination">
		<c:if test="${currentPage != 1}">
			<li><a
				href="${url}/van-ban-phap-quy?page=${currentPage - 1}">«</a></li>
		</c:if>
		<c:if test="${currentPage gt 3}">
			<li><span>...</span></li>
		</c:if>
		<c:forEach begin="1" end="${totalPage}" var="i">
			<c:choose>
				<c:when test="${currentPage eq i}">
					<li><a href="#">${i}</a>
					<li>
				</c:when>
				<c:otherwise>
					<c:if test="${(currentPage-3) lt i and i lt (currentPage + 3)}">
						<li><a
							href="${url}/van-ban-phap-quy?page=${i}">${i}</a>
						</li>
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${currentPage lt (totalPage-3)}">
			<li><span>...</span></li>
		</c:if>
		<c:if test="${currentPage lt totalPage}">
			<li><a
				href="${url}/van-ban-phap-quy?page=${currentPage + 1}">»</a></li>
		</c:if>

	</ul>
</div>
<style>
	.doc_list_title_notation{width:120px;background:#316db5;text-align:center;font-weight:bold; font-size: 13px !important; text-transform: uppercase;color: #fff !important;}
.doc_list_title_date{width:100px;background:#316db5;text-align:center;font-weight:bold; font-size: 13px !important; text-transform: uppercase;color: #fff !important;}
.doc_list_title_substract{width:570px;background:#316db5;text-align:center;font-weight:bold; font-size: 13px !important; text-transform: uppercase;color: #fff !important;}

.tableGoverment {
    width: 100%;
    font-size: 11pt !important;
    font-family: Arial;
}
table.tableGoverment tr td a {
	text-decoration:none !important;
	color: #333;

}
table.tableGoverment tr td a:hover{
	color:#038bd4;
}
#wrapper {
    margin: 0 auto;
    display: block;
    width: 100%;
  }
  
  #pagination {
    margin: 0;
    padding: 0;
    text-align: center
  }
  #pagination li {
    display: inline
  }
  #pagination li a {
    display: inline-block;
    text-decoration: none;
    padding: 5px 10px;
    color: #000
  }
  
  /* Active and Hoverable Pagination */
  #pagination li a {
    border-radius: 5px;
    -webkit-transition: background-color 0.3s;
    transition: background-color 0.3s
      
  }
  #pagination li a.active {
    background-color: #4caf50;
    color: #fff
  }
  #pagination li a:hover:not(.active) {
    background-color: #ddd;
  } 
</style>