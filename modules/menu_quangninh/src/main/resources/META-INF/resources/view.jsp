<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
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
          $("#cssmenu_qn").menumaker({
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
      .header_menu_qn {
        position: relative;
        width: 100%;
        height: 40px;
      }
      nav {
        position: relative;
        width: 100%;
        margin: 0 auto;
      }
      #cssmenu_qn,
      #cssmenu_qn ul,
      #cssmenu_qn ul li,
      #cssmenu_qn ul li a,
      #cssmenu_qn #head-mobile {
        border: 0;
        list-style: none;
        line-height: 1;
        display: block;
        position: relative;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        margin-bottom: 0 !important;
      }
      #cssmenu_qn:after,
      #cssmenu_qn > ul:after {
        content: ".";
        display: block;
        clear: both;
        visibility: hidden;
        line-height: 0;
        height: 0;
      }

      .container_menu {
        font-family: sans-serif;
        background: #0375bc;
      }
      @media (min-width: 1200px) {
        #cssmenu_qn {
          width: 1200px;
        }
      }
      #cssmenu_qn > ul > li {
        float: left;
        width: 12.5%;
        text-align: center;
        padding: 2.5px 0;
        border-right: 1px solid rgba(255, 255, 255, 0.15);
      }
      #cssmenu_qn > ul > li > a.sub_menu {
        text-transform: uppercase;
        padding: 0;
        text-decoration: none;
        font: 12px/35px Roboto, Arial, Tahoma;
        color: #fff;
      }
      #cssmenu_qn > ul > li:hover > a,
      #cssmenu_qn > ul > li > a:hover,
      #cssmenu_qn > ul > li.active > a,
      #cssmenu_qn ul li.has-sub a:hover,
      #cssmenu_qn ul ul li.has-sub a:hover,
      #cssmenu_qn ul li.has-sub ul li.has-sub ul li a:hover {
        background: #09f;
      }
      #cssmenu_qn > ul > li.has-sub > a {
        padding-right: 13px;
      }

      #cssmenu_qn ul ul {
        position: absolute;
        left: -9999px;
      }
      #cssmenu_qn ul ul li {
        height: 0;
        -webkit-transition: all 0.25s ease;
        -ms-transition: all 0.25s ease;
        background: white;
        transition: all 0.25s ease;
        z-index: 999;
      }
      #cssmenu_qn li:hover > ul {
        left: auto;
      }
      #cssmenu_qn li:hover > ul > li {
        height: 35px;
      }
      #cssmenu_qn ul ul ul {
        margin-left: 100%;
        top: 0;
      }
      #cssmenu_qn ul ul li a {
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
      #cssmenu_qn ul ul li:last-child > a,
      #cssmenu_qn ul ul li.last-item > a {
        border-bottom: 0;
      }
      #cssmenu_qn ul ul ul li.active a {
        border-left: 1px solid #333;
      }
      #cssmenu_qn > ul > li.has-sub > ul > li.active > a,
      #cssmenu_qn > ul ul > li.has-sub > ul > li.active > a {
        border-top: 1px solid #333;
      }

      @media screen and (max-width: 1012px) {
        nav {
          width: 100%;
        }
        #cssmenu_qn {
          width: 100%;
          float: left;
        }
        #cssmenu_qn ul {
          width: 100%;
          display: none;
        }
        #cssmenu_qn ul li {
          width: 100%;
          border-top: 1px solid #444;
          background: #0375bc;
        }
        #cssmenu_qn ul ul li,
        #cssmenu_qn ul ul ul li {
          z-index: 999;
        }
        /* #cssmenu_qn ul li:hover {
          background: #09f;
        } */
        #cssmenu_qn ul ul li,
        #cssmenu_qn li:hover > ul > li {
          height: auto;
        }
        #cssmenu_qn ul li a,
        #cssmenu_qn ul ul li a {
          width: 100%;
          border-bottom: 0;
        }
        #cssmenu_qn > ul > li {
          float: none;
        }
        #cssmenu_qn ul ul li a {
          padding-left: 25px;
        }
        /* #cssmenu_qn ul ul li {
          background: #0375bc !important;
        } */

        #cssmenu_qn ul ul ul li a {
          padding-left: 35px;
        }
        #cssmenu_qn ul ul li a {
          color: #ddd;
          background: none;
        }
        #cssmenu_qn ul ul li:hover > a,
        #cssmenu_qn ul ul li.active > a {
          color: #fff;
        }
        #cssmenu_qn ul ul,
        #cssmenu_qn ul ul ul {
          position: relative;
          left: 0;
          width: 100%;
          margin: 0;
          text-align: left;
        }
        #cssmenu_qn > ul > li.has-sub > a:after,
        #cssmenu_qn > ul > li.has-sub > a:before,
        #cssmenu_qn ul ul > li.has-sub > a:after,
        #cssmenu_qn ul ul > li.has-sub > a:before {
          display: none;
        }
        #cssmenu_qn #head-mobile {
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
        #cssmenu_qn .submenu-button {
          position: absolute;
          z-index: 99;
          right: 0;
          top: 0;
          display: block;
          height: 40px;
          width: 46px;
          cursor: pointer;
        }
        #cssmenu_qn .submenu-button.submenu-opened {
          background: #262626;
        }
        #cssmenu_qn ul ul .submenu-button {
          height: 34px;
          width: 34px;
        }
        #cssmenu_qn .submenu-button:after {
          position: absolute;
          top: 22px;
          right: 19px;
          width: 8px;
          height: 2px;
          display: block;
          background: black;
          content: "";
        }
        #cssmenu_qn ul ul .submenu-button:after {
          top: 15px;
          right: 13px;
        }
        #cssmenu_qn .submenu-button.submenu-opened:after {
          background: #fff;
        }
        #cssmenu_qn .submenu-button:before {
          position: absolute;
          top: 19px;
          right: 22px;
          display: block;
          width: 2px;
          height: 8px;
          background: black;
          content: "";
        }
        #cssmenu_qn ul ul .submenu-button:before {
          top: 12px;
          right: 16px;
        }
        #cssmenu_qn .submenu-button.submenu-opened:before {
          display: none;
        }
        #cssmenu_qn ul ul ul li.active a {
          border-left: none;
        }
        #cssmenu_qn > ul > li.has-sub > ul > li.active > a,
        #cssmenu_qn > ul ul > li.has-sub > ul > li.active > a {
          border-top: none;
        }
        #cssmenu_qn > ul > li.has-sub > ul > li > a,
        #cssmenu_qn ul ul ul li a {
          color: black;
        }
      }
    </style>
  </head>
  <body>
    <header class="header_menu_qn" id="header_qn_menu">
      <div class="container_menu">
        <nav id="cssmenu_qn">
          <div id="head-mobile"></div>
          <div class="button"></div>
          <ul>
            <li>
              <a href="#" class="sub_menu">TRANG CH???</a>
            </li>
            <li class="has-sub">
              <span class="submenu-button"></span>
              <a href="#" class="sub_menu">T???NG QUAN</a>
              <ul>
                <li><a href="#">T???ng quan t??i nguy??n thi??n nhi??n</a></li>
                <li><a href="#">??i???u ki???n TNXH</a></li>
                <li><a href="#">Di t??ch l???ch s??? v??n h??a</a></li>
                <li><a href="#">T??i nguy??n thi??n nhi??n</a></li>
                <li><a href="#">C?? s??? h??? t???ng</a></li>
                <li><a href="#">????n v??? h??nh ch??nh</a></li>
                <li><a href="#">Qu?? tr??nh h??nh th??nh v?? phat tri???n</a></li>
              </ul>
            </li>
            <li class="has-sub">
              <span class="submenu-button"></span>
              <a href="#" class="sub_menu">T??? CH???C B??? M??Y</a>
              <ul>
                <li class="has-sub">
                  <span class="submenu-button"></span>
                  <a href="#" class="sub_menu">T???nh ???y</a>
                  <ul>
                    <li><a href="#">Th?????ng tr???c T???nh ???y</a></li>
                    <li><a href="#">Ban th?????ng v??? T???nh ???y</a></li>
                    <li><a href="#">Ban ch???p h??nh ?????ng b??? t???nh</a></li>
                    <li><a href="#">???y ban nh??n d??n T???nh</a></li>
                    <li class="has-sub">
                      <span class="submenu-button"></span>
                      <a href="#" class="sub_menu">C??c ban ?????ng</a>
                      <ul>
                        <li><a href="#">Ban T??? ch???c</a></li>
                        <li><a href="#">Ban Tuy??n gi??o</a></li>
                        <li><a href="#">???y ban ki???m tra T???nh ???y</a></li>
                        <li><a href="#">Ban D??n v???n</a></li>
                        <li><a href="#">C??c ban ?????ng</a></li>
                        <li><a href="#">Ban n???i ch??nh</a></li>
                      </ul>
                    </li>
                    <li><a href="#">V??n ph??ng T???nh ???y</a></li>
                    <li><a href="#">Ng??nh- T??? ch???c</a></li>
                    <li><a href="#">Trung t??m truy???n th??ng T???nh </a></li>
                  </ul>
                </li>
                <li class="has-sub">
                  <span class="submenu-button"></span>
                  <a href="#" class="sub_menu"
                    >??o??n ?????i Bi???u Qu???c H???i T???nh Qu???ng Ninh</a
                  >
                  <ul>
                    <li><a href="#">??o??n ??BQH v?? ??BQH</a></li>
                    <li><a href="#">V??n ph??ng ??o??n ??BQH v?? H??ND</a></li>
                  </ul>
                </li>
                <li class="has-sub">
                  <span class="submenu-button"></span>
                  <a href="#" class="sub_menu">H???i ????ng nh??n d??n T???nh</a>
                  <ul>
                    <li><a href="#">?????i bi???u H??ND</a></li>
                    <li><a href="#">Th?????ng tr???c H??ND</a></li>
                    <li><a href="#">Ban kinh t??? - Ng??n s??ch</a></li>
                    <li><a href="#">Ban V??n h??a - X?? h???i</a></li>
                    <li><a href="#">Ban ph??p ch???</a></li>
                    <li><a href="#">V??n ph??ng ??o??n ??BQH v?? H??ND</a></li>
                  </ul>
                </li>
                <li class="has-sub">
                  <span class="submenu-button"></span>
                  <a href="#" class="sub_menu">???y ban nh??n d??n T???nh</a>
                  <ul>
                    <li class="has-sub">
                      <span class="submenu-button"></span>
                      <a href="#" class="sub_menu">???y ban nh??n d??n</a>
                      <ul>
                        <li><a href="#">Gi???i thi???u chung UBND</a></li>
                        <li><a href="#">T??? ch???c b??? m??y</a></li>
                      </ul>
                    </li>
                    <li class="has-sub">
                      <span class="submenu-button"></span>
                      <a href="#" class="sub_menu">V??n ph??ng UBND</a>
                      <ul>
                        <li><a href="#">Gi???i thi???u chung</a></li>
                        <li><a href="#">Ch???c n??ng nhi???m v???</a></li>
                        <li><a href="#">T??? ch???c b??? m??y</a></li>
                      </ul>
                    </li>
                  </ul>
                </li>
                <li><a href="#">C??c s???</a></li>
                <li><a href="#">Ban, ????n v??? s??? nghi???p</a></li>
                <li><a href="#">Ng??nh- T??? ch???c</a></li>
                <li><a href="#">C??c Huy???n - Th??? x?? - TP</a></li>
              </ul>
            </li>
            <li class="has-sub">
              <span class="submenu-button"></span>
              <a href="#" class="sub_menu">TIN T???C</a>
              <ul>
                <li><a href="#">Tin ho???t ?????ng trong t???nh</a></li>
              </ul>
            </li>
            <li><a href="#" class="sub_menu">D???CH V??? C??NG</a></li>
            <li class="has-sub">
              <span class="submenu-button"></span>
              <a href="#" class="sub_menu">V??N B???N</a>
              <ul>
                <li><a href="#">C??ng b??o</a></li>
                <li><a href="#">V??n B???n Ch??? ?????o ??i???u H??nh</a></li>
                <li class="has-sub">
                  <span class="submenu-button"></span>
                  <a href="#" class="sub_menu">Khi???u n???i T??? c??o</a>
                  <ul>
                    <li><a href="#">TP H??? Long</a></li>
                    <li><a href="#">TP C???m Ph???</a></li>
                    <li><a href="#">TP U??ng B??</a></li>
                    <li><a href="#">Th??? x?? ????ng Tri???u</a></li>
                    <li><a href="#">Th??? x?? Qu???ng Y??n</a></li>
                    <li><a href="#">Huy???n Ba Ch???</a></li>
                    <li><a href="#">Huy???n B??nh Li??u</a></li>
                    <li><a href="#">huy???n C?? T??</a></li>
                    <li><a href="#">Guy???n ?????m H??</a></li>
                    <li><a href="#">Huy???n H???i H??</a></li>
                    <li><a href="#">Huy???n Ho??nh B???</a></li>
                    <li><a href="#">TP M??ng C??i</a></li>
                    <li><a href="#">Huy???n V??n ?????n</a></li>
                    <li><a href="#">Huy???n Ti??n Y??n</a></li>
                    <li><a href="#">????n v??? kh??c</a></li>
                    <li><a href="#">V??n b???n kh??c</a></li>
                    <li><a href="#">V??n b???n t??? 01/2017</a></li>
                    <li class="has-sub">
                      <span class="submenu-button"></span>
                      <a href="#" class="sub_menu">V??n b???n t??? 2011 ?????n 2016</a>
                      <ul>
                        <li><a href="#">Gi???i quy???t khi???u n???i t??? c??o</a></li>
                        <li><a href="#">V??n b???n li??n quan</a></li>
                        <li><a href="#">Th??ng b??o k???t qu??? ti???p d??n</a></li>
                      </ul>
                    </li>
                  </ul>
                </li>
                <li><a href="#">V??n b???n ph??p quy</a></li>
                <li><a href="#">V??n b???n kh??c</a></li>
              </ul>
            </li>
            <li><a href="#" class="sub_menu">DU L???CH</a></li>
            <li><a href="#" class="sub_menu">DOANH NGHI???P</a></li>
          </ul>
        </nav>
      </div>
    </header>
  </body>
</html>
