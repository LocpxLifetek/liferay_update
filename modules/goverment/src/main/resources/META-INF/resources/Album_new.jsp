<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="https://cdn.rawgit.com/Pagawa/PgwSlideshow/master/pgwslideshow_light.min.css">
<link rel="stylesheet" href="https://cdn.rawgit.com/Pagawa/PgwSlideshow/master/pgwslideshow.min.css">
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

  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://cdn.rawgit.com/Pagawa/PgwSlideshow/master/pgwslideshow.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $(".pgwSlideshow").pgwSlideshow({
      autoSlide: true
    });
});
</script>

</body>
</html>
