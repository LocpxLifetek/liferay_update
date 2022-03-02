<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="redirec" style="padding-bottom: 10px;">
					<a href="${url}"
					style="color: #0a4298 !important;
						    font-size: 13px;
						    padding: 0 12px 0 0px;">TRANG CHỦ</a>
				</div>

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
	<p>VĂN BẢN LIÊN QUAN</p>
</div>
<c:forEach items="${listJournalArticleLocazationDto}"
	var="journalArticleLocazation">
	<div class="doc_detail_others_item"
		style="display: block;text-align: justify;">
		<a
			href="${url}/chi-tiet-van-ban-phap-quy?class_id=${journalArticleLocazation.id}">${journalArticleLocazation.title}</a>
	</div>
</c:forEach>
<div id="wrapper">
	<ul id="pagination">
		<c:if test="${currentPage != 1}">
			<li><a
				href="${url}/chi-tiet-van-ban-phap-quy?page=${currentPage - 1}">«</a></li>
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
							href="${url}/chi-tiet-van-ban-phap-quy?page=${i}">${i}</a>
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
				href="${url}/chi-tiet-van-ban-phap-quy?page=${currentPage + 1}">»</a></li>
		</c:if>

	</ul>
</div>
<style>
		table.doc_detail_attr_table {border-spacing:0px;border:#7994CB 1px solid;border-collapse:collapse;width:100%;}
		table.doc_detail_attr_table td{border:#7994CB 1px solid;padding:2px;margin:0px;vertical-align:middle;font-family:'Arial';font-size:10pt;}
		.doc_detail_others {font-size:10pt;font-weight:bold;}
		.doc_detail_others_item{font-family:'Arial';font-size:9pt;margin-left:0px;margin-top:4px;margin-bottom:4px;margin-right:3px;}
		.doc_detail_others_item a{
			text-decoration:none;
			color:black;
		}
		.doc_detail_others_item a:hover{
			color:#038bd4;
		}
		.doc_detail_others_item a{
			background: url("<%= request.getContextPath()%>/images/portal/_themes/ThanhHoa_orange/img/hv-module-tvt.PNG") center left no-repeat;
        	color: #222;
        	padding-left: 15px;
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