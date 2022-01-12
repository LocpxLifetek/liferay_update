<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/pgwslideshow.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/pgwslideshow_light.min.css">
</head>
<body>
 <div class="head-cm">
      <ul style="margin: 0; padding: 0">
        <li><a href="#">ALBUM MỚI NHẤT</a></li>
      </ul>
    </div>
    <div class="info-album">
        <h1>${categoryDto.name}</h1>
    </div>
<div class="slides">
    <ul class="pgwSlideshow">
    	<c:forEach items="${dLfileEntryDtos}" var="dLfileEntryDtos">
        	<li>
        		<img src="${dLfileEntryDtos.src}" data-description="${dLfileEntryDtos.title}">
        	</li>
        </c:forEach>   
            </ul>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/pgwslideshow.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/pgwslideshow.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $(".pgwSlideshow").pgwSlideshow({
      autoSlide: true
    });
});
</script>

</body>
</html>

