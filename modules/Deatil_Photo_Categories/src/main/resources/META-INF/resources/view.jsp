<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="porlet"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <link
	rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.3.5/jquery.fancybox.min.css"
    />

    <script>
 // Fancybox Config
    $('[data-fancybox="gallery"]').fancybox({
      buttons: ["slideShow", "thumbs", "zoom", "fullScreen", "share", "close"],
      loop: false,
      protect: true,
    });

    </script>
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.3.5/jquery.fancybox.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/2.0.1/TweenMax.min.js"></script>
  </body>
</html>