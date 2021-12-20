<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="https://cdn.rawgit.com/Pagawa/PgwSlideshow/master/pgwslideshow_light.min.css">
<link rel="stylesheet" href="https://cdn.rawgit.com/Pagawa/PgwSlideshow/master/pgwslideshow.min.css">
<style>
	 .head-cm {
        width: 100%;
        border-bottom: 1px solid #d71921;
        margin-bottom: 20px;
      }
      .head-cm ul {
        display: inline-flex;
        list-style: none;
      }
      .head-cm ul li:first-child {
        background: url("	http://bocongan.gov.vn/Publishing_Resources/web/portal/images/bg-chuyenmuc.png")
          no-repeat right top;
      }
      .head-cm ul li:first-child a {
        color: #fff;
        font: 700 16px/35px Roboto, Arial, Tahoma;
        text-transform: uppercase;
        border-right: none;
        padding: 0 30px 0 20px;
      }
      .head-cm ul li a {
        color: #404041;
        font: 400 13px/35px Roboto, Arial, Tahoma;
        padding: 0 10px;
        border-right: 1px solid #ddd;
        text-decoration: none;
      }
      .info-album { overflow: auto; margin-bottom: 20px; border-bottom: 1px solid #ddd; padding-bottom: 20px; }
</style>

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
