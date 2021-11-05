<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<div width="100%" height="100%">
  <div class="bn_lanhdao">
	<h6 front-size="4px">Lãnh đạo- chỉ đạo</h6>
  </div>
  <div class="lanhdao_content">
  	<c:forEach items="${listBlog}" var="u">
      <div  style="width:100%">
		<div style="width:100%" align="justify">
		<a padding="5px" class="pb"> ${u.content}</a></div>
      </div>
    </c:forEach>
  </div> 
  <a class="xemtiep" href="/portal/page/portal/chinhphu/trangchu/cacbaiphatbieucuaThutuong" target="">Xem tiếp &gt;&gt;</a>
</div>
<style>
	.bn_lanhdao{
		width: 100%;
		background-color: rgb(1, 21, 82);;
	}
	h6{
		text-align: center;
		color: white;
		padding: 10px 0 10px 0;
	}
	.xemtiep{
		text-align: right;
	}
</style>