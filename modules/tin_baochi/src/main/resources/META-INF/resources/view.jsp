<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <style>
      .border {
        border: 1px solid #ddd;
      }
      .section-title {
        font-weight: bold;
        color: #fff !important;
        background-color: #0070bb;
        background-image: 
          url("https://www.quangninh.gov.vn/Resources/app/img/tab-1.png");
        background-repeat: no-repeat;
        width: 100%;
        height: 35px;
        background-size: 100% 35px;
        text-align: center;
        padding-top: 10px;
      }
      .section-content .tin_th a{
          text-decoration: none;
          color: black;
      }
      .section-content .tin_th{
      	text-align: justify;
      	padding-left:0 !important; 	
      }
      .section-content .tin_th a:hover{
          color: #09f;
      }
    </style>
  </head>
  <body>
    <div class="section section-black border">
      <div class="section-title">Thông tin báo chí</div>
      <div class="section-content">
        <ul class="tin_th">
        	<li><a href="#">Danh sách người phát ngôn</a></li>
        	<li><a href="#">Điểm báo</a></li>
        	<li><a href="#">Phản hồi thông tin báo chí</a></li>
        	<li><a href="#">Thông cáo báo chí</a></li>
        </ul>
      </div>
    </div>
  </body>
</html>
