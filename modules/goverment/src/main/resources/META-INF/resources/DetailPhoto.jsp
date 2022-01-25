<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="porlet"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />

	 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.fancybox.min.css">
    <script>
 // Fancybox Config
    $('[data-fancybox="gallery"]').fancybox({
      buttons: ["slideShow", "thumbs", "zoom", "fullScreen", "share", "close"],
      loop: false,
      protect: true,
    });

    </script>
    <style>
    	.head-cm {
        width: 100%;
        border-bottom: 1px solid #0a4298;
        margin-bottom: 20px;
      }
      .head-cm ul {
        display: inline-flex;
        list-style: none;
      }
      .head-cm ul li:first-child {
        background: url('<%= request.getContextPath()%>/images/portal/images/bg-chuyenmuc.png')
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
    	
    </style>
  </head>
  <body>
    <div class="head-cm">
      <ul style="margin: 0; padding: 0">
        <li><a href="#">DANH SÁCH ẢNH</a></li>
      </ul>
    </div>
    <div class="info-album">
        <h1>${dlfileEntry.title}</h1>
    </div>
    <main class="main_photo">
		<div class="container_photo">
		<c:forEach items="${listDlfile}" var="listDlfile">
		  <div class="card_photo">
			<div class="card-image">
			  <a href="${listDlfile.src}" data-fancybox="gallery" data-caption="Caption Images 1">
				<img src="${listDlfile.src}" alt="Image Gallery">
				<div class="button"><a href="${listDlfile.url}"> Download </a></div>
				<p>${listDlfile.title}</p>
			  </a> 
			</div>
		  </div>
		  </c:forEach>
		</div>
	  </main>
	  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
	  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.fancybox.min.js"></script>
	  <script type="text/javascript" src="<%=request.getContextPath()%>/js/TweenMax.min.js"></script>

  </body>
</html>