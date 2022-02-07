<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="porlet"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <style>
     .head-cm ul li:first-child {
        background: url("<%= request.getContextPath()%>/images/portal/images/bg-chuyenmuc.png")
          no-repeat right top;
      }
      .fix3n {
        border-bottom: 1px solid #ddd;
      }

      .box-top3 {
        overflow: hidden;
      }
      .fixalbumdetail .box-top3 li {
        min-height: 265px;
      }

      .thumb {
        width: 215px;
        height: 125px;
        background-image: none;
        background-repeat: no-repeat;
        background-position: center center;
        background-size: cover;
      }
      .box-top3 ul li h3 a {
        padding: 10px 0;
      }
    </style>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.fancybox.min.css">
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
    <div class="box-top3 fix3n">
    <c:forEach items="${listDlfile}" var="listDlfile">
      <ul>
        <li>
          <a
            data-fancybox="gallery"
            data-caption="${listDlfile.title}"
          >
            <div
              class="thumb"
              style="background-image: url('${listDlfile.src}')"
            ></div>
          </a>
          <h3>
            <a href="#"
              >${listDlfile.title}</a
            >
          </h3>
        </li>
      </ul>
      </c:forEach>
    </div>
  </body>
</html>