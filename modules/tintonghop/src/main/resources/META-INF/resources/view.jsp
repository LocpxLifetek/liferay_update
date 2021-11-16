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
      	margin-left: 20px;
    	margin-right: 5px;   	
      }
      .section-content .tin_th a:hover{
          color: #09f;
      }
    </style>
  </head>
  <body>
    <div class="section section-black border">
      <div class="section-title">Thông tin tổng hợp</div>
      <div class="section-content">
        <ul class="tin_th">
        	<li><a href="#">Thông tin doanh nghiệp</a></li>
        	<li><a href="#">Thông báo của tòa án</a></li>
        	<li><a href="#">Thông báo cưỡng chế thi hành án dân sự</a></li>
        	<li><a href="#">Thông báo đấu giá tài sản</a></li>
        	<li><a href="#">Thông báo tìm chủ sở hữu</a></li>
        	<li><a href="#">Thông báo tuyển sinh-tuyển dụng</a></li>
        	<li><a href="#">Thông tin khác</a></li>
        	<li><a href="#">Trợ giúp pháp lý</a></li>

        </ul>
      </div>
    </div>
  </body>
</html>
