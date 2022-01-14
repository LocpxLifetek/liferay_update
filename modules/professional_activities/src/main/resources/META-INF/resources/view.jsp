<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>

    <style>
      body {
        margin: 0;
        padding: 0;
      }
      .panel {
        margin-bottom: 10px;
        background-color: rgb(255, 255, 255);
        border-collapse: collapse;
        border: rgb(212, 211, 211) 1px solid;
      }
      .panel-heading {
        background-color: #b80002;
        text-align: center;
      }
      .panel-heading a {
        text-decoration: none;
        color: white;
      }
      .panel-heading .title.h4 {
        font-size: 16px;
      }
      .panel-heading .title {
        color: rgb(255, 255, 255);
        display: inline-block;
        position: relative;
      }

      .title {
        text-transform: uppercase;
        line-height: 24px;
        font-family: "Roboto Condensed", sans-serif;
        font-weight: bold;
        margin-bottom: 0px;
        margin: 3px 0 !important;
      }
      .panel-body a {
        text-decoration: none;
        color: black;
      }
      .lists {
        list-style: none;
        padding-left: 0px;
        margin-bottom: 0px;
      }
      .panel .panel-body .lists li {
        font-size: 15px;
      }
      .lists li:not(:last-child) {
        padding-bottom: 9px;
        margin-bottom: 5px;
        border-bottom-color: rgb(0, 0, 0);
        border-bottom-width: 1px;
        border-bottom-style: dotted;
      }
      .lists li {
        line-height: 24px;
        padding-left: 10px;
        font-family: "Roboto Condensed", sans-serif;
      }
      .lists li a::before {
        font-family: "FontAwesome";
        font-size: 14px;
        margin-right: 5px;
        vertical-align: 2px;
        display: inline-block;
        content: "\f101";
      }
    </style>
  
  
    <aside class="aside-left">
      <div class="panel">
        <div class="panel-heading">
          <h3 class="title h4">
            <a href="https://h05.bca/web/cuch05/hoat-dong-chuyen-mon">
              HOẠT ĐỘNG CHUYÊN MÔN</a>
          </h3>
        </div>
        <div class="panel-body">
          <ul class="lists">
            <li>
              <a href="https://h05.bca/web/cuch05/tham-muu-tong-hop"><b> Tham mưu - Tổng hợp</b></a>
            </li>
            <li>
              <a href="https://h05.bca/web/cuch05/quan-ly-cntt"><b> Quản lý Công nghệ thông tin</b></a>
            </li>
            <li>
              <a href="https://h05.bca/web/cuch05/quan-ly-mang-may-tinh-dien-rong-nganh"><b> Quản lý Mạng diện rộng Ngành</b></a>
            </li>
            <li>
              <a href="https://h05.bca/web/cuch05/cntt-phia-nam"><b> Công nghệ thông tin phía Nam</b></a>
            </li>
            <li>
              <a href="https://h05.bca/web/cuch05/ky-thuat-cntt-va-attt-so"><b> Kỹ thuật CNTT và ATTT số</b></a>
            </li>
            <li>
              <a href="https://h05.bca/web/cuch05/quan-ly-csdl-dung-chung"><b>Quản lý CSDL dùng chung</b></a>
            </li>
            <li>
              <a href="https://h05.bca/web/cuch05/ky-thuat-kiem-soat-an-ninh"><b> Kỹ thuật kiểm soát an ninh</b></a>
            </li>
            <li>
              <a href="https://h05.bca/web/cuch05/nghien-cuu-phat-trien-ung-dung-cntt"><b> Nghiên cứu phát triển ứng dụng</b></a>
            </li>
          </ul>
        </div>
      </div>
    </aside>
  

