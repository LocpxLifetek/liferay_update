<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
     <script>
      (function ($) {
        $.fn.menumaker = function (options) {
          var cssmenu = $(this),
            settings = $.extend(
              {
                format: "dropdown",
                sticky: false,
              },
              options
            );
          return this.each(function () {
            $(this)
              .find(".button")
              .on("click", function () {
                $(this).toggleClass("menu-opened");
                var mainmenu = $(this).next("ul");
                if (mainmenu.hasClass("open")) {
                  mainmenu.slideToggle().removeClass("open");
                } else {
                  mainmenu.slideToggle().addClass("open");
                  if (settings.format === "dropdown") {
                    mainmenu.find("ul").show();
                  }
                }
              });
            cssmenu.find("li ul").parent().addClass("has-sub");
            multiTg = function () {
              cssmenu
                .find(".has-sub")
                .prepend('<span class="submenu-button"></span>');
              cssmenu.find(".submenu-button").on("click", function () {
                $(this).toggleClass("submenu-opened");
                if ($(this).siblings("ul").hasClass("open")) {
                  $(this).siblings("ul").removeClass("open").slideToggle();
                } else {
                  $(this).siblings("ul").addClass("open").slideToggle();
                }
              });
            };
            if (settings.format === "multitoggle") multiTg();
            else cssmenu.addClass("dropdown");
            if (settings.sticky === true) cssmenu.css("position", "fixed");
            resizeFix = function () {
              var mediasize = 1000;
              if ($(window).width() > mediasize) {
                cssmenu.find("ul").show();
              }
              if ($(window).width() <= mediasize) {
                cssmenu.find("ul").hide().removeClass("open");
              }
            };
            resizeFix();
            return $(window).on("resize", resizeFix);
          });
        };
      })(jQuery);

      (function ($) {
        $(document).ready(function () {
          $("#cssmenu").menumaker({
            format: "multitoggle",
          });
        });
      })(jQuery);
    </script>
    <style>
      * {
        margin: 0;
        padding: 0;
        text-decoration: none;
      }
      .header_menu_bca {
        position: relative;
        width: 100%;
        height: 40px;
      }
      nav {
        position: relative;
        width: 100%;
        margin: 0 auto;
      }
      #cssmenu,
      #cssmenu ul,
      #cssmenu ul li,
      #cssmenu ul li a,
      #cssmenu #head-mobile {
        border: 0;
        list-style: none;
        line-height: 1;
        display: block;
        position: relative;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
      }
      #cssmenu:after,
      #cssmenu > ul:after {
        content: ".";
        display: block;
        clear: both;
        visibility: hidden;
        line-height: 0;
        height: 0;
      }
      #cssmenu #head-mobile {
        display: none;
      }
      #cssmenu {
        font-family: sans-serif;
        background:#d71920;
      }
      #cssmenu > ul > li {
        float: left;
        width: 15.8%;
        text-align: center;
        padding: 2.5px 0;
      }
      #cssmenu > ul > li > a.sub_menu {
        text-transform: uppercase;
        padding: 0 25px;
        text-decoration: none;
        font: 13px/35px Roboto, Arial, Tahoma;
        color: #fff;
      }
      #cssmenu > ul > li:hover > a,
      #cssmenu > ul > li > a:hover,
      #cssmenu > ul > li.active > a {
        background: white;
        color: #d71920;
      }
      #cssmenu > ul > li.has-sub > a {
        padding-right: 30px;
      }

      #cssmenu ul ul {
        position: absolute;
        left: -9999px;
      }
      #cssmenu ul ul li {
        height: 0;
        -webkit-transition: all 0.25s ease;
        -ms-transition: all 0.25s ease;
        background: white;
        transition: all 0.25s ease;
        z-index:999;
      }
      #cssmenu li:hover > ul {
        left: auto;
      }
      #cssmenu li:hover > ul > li {
        height: 35px;
      }
      #cssmenu ul ul ul {
        margin-left: 100%;
        top: 0;
      }
      #cssmenu ul ul li a {
        border-bottom: 1px solid rgba(150, 150, 150, 0.15);
        padding: 11px 15px;
        width: 177px;
        font-size: 12px;
        text-decoration: none;
        text-align: left;
        color: black;
        font-weight: 400;
        background: #ffffff;
        box-shadow: 0 3px 3px #222;
      }
      #cssmenu ul ul li a:hover {
        color: red;
      }
      #cssmenu ul ul li:last-child > a,
      #cssmenu ul ul li.last-item > a {
        border-bottom: 0;
      }
      #cssmenu ul ul li.has-sub:hover,
      #cssmenu ul li.has-sub ul li.has-sub ul li:hover {
        background: white;
      }
      #cssmenu ul ul ul li.active a {
        border-left: 1px solid #333;
      }
      #cssmenu > ul > li.has-sub > ul > li.active > a,
      #cssmenu > ul ul > li.has-sub > ul > li.active > a {
        border-top: 1px solid #333;
      }

      @media screen and (max-width: 1012px) {
        nav {
          width: 100%;
        }
        #cssmenu {
          width: 100%;
/*           z-index: 999; */
          float: left;
        }
        #cssmenu ul {
          width: 100%;
          display: none;
        }
        #cssmenu ul li {
          width: 100%;
          border-top: 1px solid #444;
        }
        #cssmenu ul ul li,
        #cssmenu ul ul ul li {
          z-index: 999;
        }
        #cssmenu ul li:hover {
          background: white;
        }
        #cssmenu ul ul li,
        #cssmenu li:hover > ul > li {
          height: auto;
        }
        #cssmenu ul li a,
        #cssmenu ul ul li a {
          width: 100%;
          border-bottom: 0;
        }
        #cssmenu > ul > li {
          float: none;
        }
        #cssmenu ul ul li a {
          padding-left: 25px;
        }
        #cssmenu ul ul li {
          background: white !important;
        }

        #cssmenu ul ul ul li a {
          padding-left: 35px;
        }
        #cssmenu ul ul li a {
          color: #ddd;
          background: none;
        }
        #cssmenu ul ul li:hover > a,
        #cssmenu ul ul li.active > a {
          color: #fff;
        }
        #cssmenu ul ul,
        #cssmenu ul ul ul {
          position: relative;
          left: 0;
          width: 100%;
          margin: 0;
          text-align: left;
        }
        #cssmenu > ul > li.has-sub > a:after,
        #cssmenu > ul > li.has-sub > a:before,
        #cssmenu ul ul > li.has-sub > a:after,
        #cssmenu ul ul > li.has-sub > a:before {
          display: none;
        }
        #cssmenu #head-mobile {
          display: block;
          padding: 23px;
          color: #ddd;
          font-size: 12px;
          font-weight: 700;
        }
        .button {
          width: 55px;
          height: 46px;
          position: absolute;
          right: 0;
          top: 0;
          cursor: pointer;
          z-index: 12399994;
        }
        .button:after {
          position: absolute;
          top: 22px;
          right: 20px;
          display: block;
          height: 4px;
          width: 20px;
          border-top: 2px solid black;
          border-bottom: 2px solid black;
          content: "";
        }
        .button:before {
          -webkit-transition: all 0.3s ease;
          -ms-transition: all 0.3s ease;
          transition: all 0.3s ease;
          position: absolute;
          top: 16px;
          right: 20px;
          display: block;
          height: 2px;
          width: 20px;
          background: black;
          content: "";
        }
        .button.menu-opened:after {
          -webkit-transition: all 0.3s ease;
          -ms-transition: all 0.3s ease;
          transition: all 0.3s ease;
          top: 23px;
          border: 0;
          height: 2px;
          width: 19px;
          background: black;
          -webkit-transform: rotate(45deg);
          -moz-transform: rotate(45deg);
          -ms-transform: rotate(45deg);
          -o-transform: rotate(45deg);
          transform: rotate(45deg);
        }
        .button.menu-opened:before {
          top: 23px;
          background: black;
          width: 19px;
          -webkit-transform: rotate(-45deg);
          -moz-transform: rotate(-45deg);
          -ms-transform: rotate(-45deg);
          -o-transform: rotate(-45deg);
          transform: rotate(-45deg);
        }
        #cssmenu .submenu-button {
          position: absolute;
          z-index: 99;
          right: 0;
          top: 0;
          display: block;
          height: 40px;
          width: 46px;
          cursor: pointer;
        }
        #cssmenu .submenu-button.submenu-opened {
          background: #262626;
        }
        #cssmenu ul ul .submenu-button {
          height: 34px;
          width: 34px;
        }
        #cssmenu .submenu-button:after {
          position: absolute;
          top: 22px;
          right: 19px;
          width: 8px;
          height: 2px;
          display: block;
          background: black;
          content: "";
        }
        #cssmenu ul ul .submenu-button:after {
          top: 15px;
          right: 13px;
        }
        #cssmenu .submenu-button.submenu-opened:after {
          background: #fff;
        }
        #cssmenu .submenu-button:before {
          position: absolute;
          top: 19px;
          right: 22px;
          display: block;
          width: 2px;
          height: 8px;
          background: black;
          content: "";
        }
        #cssmenu ul ul .submenu-button:before {
          top: 12px;
          right: 16px;
        }
        #cssmenu .submenu-button.submenu-opened:before {
          display: none;
        }
        #cssmenu ul ul ul li.active a {
          border-left: none;
        }
        #cssmenu > ul > li.has-sub > ul > li.active > a,
        #cssmenu > ul ul > li.has-sub > ul > li.active > a {
          border-top: none;
        }
        #cssmenu > ul > li.has-sub > ul > li > a,
        #cssmenu ul ul ul li a {
          color: black;
        }

        #cssmenu > ul > li.has-sub > ul > li > a:hover,
        #cssmenu ul ul ul li a:hover {
          color: red;
        }
       
      }
    </style>
     <header class="header_menu_bca" id="header_bca_menu">
      <nav id="cssmenu">
        <div id="head-mobile"></div>
        <div class="button"></div>
        <ul>
          <li class="active" style="
              width: 5% !important;
              height: 35px;
              background: url(http://bocongan.gov.vn/Publishing_Resources/web/portal/images/bg-right-menu.png);
              padding-top: 2px !important;
              padding-left: 5px;
            ">
            <a href="/web/lifetek/bộ-công-an">
              <img style="height: 30px !important" src="http://bocongan.gov.vn/Publishing_Resources/images/i-home.png">
            </a>
          </li>
          <li class="has-sub"><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span>
            <a href="#" class="sub_menu">GIỚI THIỆU</a>
            <ul>
              <li class="has-sub"><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span>
                <a href="#">Lãnh đạo bộ</a>
                <ul>
                  <li><a>Lãnh đạo bộ đương nhiệm</a></li>
                  <li><a>Lãnh đạo bộ qua các thời kì</a></li>
                </ul>
              </li>
              <li><a href="/web/lifetek/chuc-nang-nhiem-vu">Chức năng nhiệm vụ</a></li>
              <li><a href="#">Lịch sử phát triển</a></li>
            </ul>
          </li>
          <li class="has-sub"><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span>
            <a href="/web/lifetek/tintucsukien" class="sub_menu">TIN TỨC SỰ KIỆN</a>
            <ul>
              <li class="has-sub"><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span>
                <a href="/web/lifetek/theloaitintucsukien?uuid=ff2977fb-16c1-17fa-b087-87636b71184b">Hoạt động của lục lượng Công an</a>
                <ul>
                  <li><a href="/web/lifetek/hoat-dong-cua-luc-luong-cong-an?id=a72e0327-4ee1-66fe-a008-80ae45ffc1d7">Hoạt động của bộ</a></li>
                  <li><a href="/web/lifetek/hoat-dong-cua-luc-luong-cong-an?id=93a35c83-8e7b-f508-feb3-95260956bcc4
">Hoạt động của địa phương</a></li>
                </ul>
              </li>
              <li><a href="/web/lifetek/theloaitintucsukien?uuid=57351404-fe30-1b2e-fc25-ebaadee89ffc">Chỉ đạo điều hành</a></li>
              <li><a href="/web/lifetek/theloaitintucsukien?uuid=4b01b6ae-ec99-4f78-3425-a8af80057147">Thông tin đối ngoại</a></li>
              <li><a href="/web/lifetek/theloaitintucsukien?uuid=382bb95f-6d90-cea6-1846-14f78e563926">Tin an ninh trật tự</a></li>
              <li><a href="/web/lifetek/theloaitintucsukien?uuid=ef36fa14-b414-28fa-c73f-5b2aa4546829">Người tốt việc tốt</a></li>
              <li><a href="#">Hoạt động xã hội</a></li>
            </ul>
          </li>
          <li class="has-sub"><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span>
            <a href="#" class="sub_menu">VĂN BẢN</a>
            <ul>
              <li><a href="#">Hệ thống văn bản</a></li>
              <li><a href="/web/lifetek/giới-thiệu-văn-bản-mới">Giới thiệu, hướng dẫn văn bản QPPL</a></li>
            </ul>
          </li>
          <li><a href="#" class="sub_menu">DỊCH VỤ CÔNG</a></li>
          <li><a href="#" class="sub_menu">BỘ VỚI CÔNG DÂN</a></li>
          <li class="has-sub"><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span><span class="submenu-button"></span>
            <a href="/web/lifetek/thông-tin-thống-kê" class="sub_menu">THỐNG KÊ</a>
            <ul>
              <li><a href="#">Phòng, chống tội phạm và VPPL</a></li>
              <li><a href="#">Quản lý hành chính về trật tự xã hội</a></li>
              <li><a href="#">Quản lý xuất, nhập cảnh</a></li>
            </ul>
          </li>
        </ul>
      </nav>
    </header>