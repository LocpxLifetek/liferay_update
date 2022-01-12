<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <style>
      .new-feature {
        bottom: 0;
        margin: 0;
        width: 100%;
        background-color: #fee5ad;
        display: inline-flex;
        overflow: hidden;
        white-space: nowrap;
      }

      .text {
        padding: 5px 5px;
        vertical-align: middle;
        font-size: 15px;
        margin: 0;
        width: 100%;
        animation: marquee 10s linear infinite;
        display: inline-block;
        padding-right: 10px;
      }
      .news {
        padding: 5px 5px;
        color: #b80002;
        font-size: 17px;
      }
      .text a {
        text-decoration: none;
        color: red;
      }
    </style>
  </head>
  <body>
    <div class="new-feature">
      <div class="news">
        <span><b>Tin mới:</b></span>
      </div>
      <p class="text">
        <marquee onmouseover="this.stop()" onmouseout="this.start()"
          ><a
            href="https://h05.bca/web/cuch05/tin-chi-tiet/-/chi-tiet/-ong-loat-canh-bao-nan-tin-nhan-lua-ao-mao-danh-ngan-hang-bhxh-690-39.html"
            >Đồng loạt cảnh báo nạn tin nhắn lừa đảo mạo danh ngân hàng, BHXH</a
          >
          <a
            href="https://h05.bca/web/cuch05/tin-chi-tiet/-/chi-tiet/nhung-van-e-at-ra-trong-cong-tac-am-bao-an-ninh-phi-truyen-thong-trong-thoi-ai-4.0-692-39.html"
            >Những vấn đề đặt ra trong công tác đảm bảo an ninh phi truyền thống
            trong thời đại 4.0</a
          >
          <a
            href="https://h05.bca/web/cuch05/tin-chi-tiet/-/chi-tiet/10-giai-phap-an-ninh-mang-cho-nam-2022-689-39.html"
            >10 giải pháp an ninh mạng cho năm 2022</a
          >
          <span>*</span>
          <a
            href="https://h05.bca/web/cuch05/tin-chi-tiet/-/chi-tiet/xu-huong-an-toan-thong-tin-tai-viet-nam-nam-2022-688-39.html"
            >Xu hướng an toàn thông tin tại Việt Nam năm 2022</a
          >
          <span>*</span>
          <a
            href="https://h05.bca/web/cuch05/tin-chi-tiet/-/chi-tiet/startup-nen-co-chien-luoc-an-toan-thong-tin-dai-han-661-39.html"
            >Startup nên có chiến lược an toàn thông tin dài hạn</a
          >
          <span>*</span>
        </marquee>
      </p>
    </div>
  </body>
</html>
