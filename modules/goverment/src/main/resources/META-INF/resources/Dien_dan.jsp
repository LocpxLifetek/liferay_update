<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <style>
      body {
        margin: 0;
        padding: 0;
      }
      .banner-diendan {
        width: 100%;
        height: 100px;
        background-image: url("<%= request.getContextPath()%>/images/portal/_res/img/thiet-ke-web-forum-01.png");
        background-repeat: no-repeat;
        background-size: 200px;
      }
    </style>
  </head>
  <body>
    <div class="banner-diendan"></div>
  </body>
</html>
