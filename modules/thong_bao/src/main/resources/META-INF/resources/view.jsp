<%@ include file="/init.jsp" %>
<div width="100%" height="100%">
  <div class="bn_lanhdao">
	<h6 front-size="4px">Th�ng c�o b�o ch�</h6>
  </div>
  <div class="lanhdao_content">
  	<c:forEach items="${listBlogsEntryDtos}" var="u">
      <div  style="width:100%">
		<div style="width:100%" align="justify">
		<a padding="5px" class="pb"> ${u.titleBlogsEntry}</a></div>
      </div>
    </c:forEach>
  </div> 
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
</style>