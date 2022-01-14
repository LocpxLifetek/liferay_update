<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  </head>
  <body>
    <div>
      <nav class="navbar_menu">
        <div class="sitenavigation">
          <span class="menu-icon">
            <a href="#" class="menu example5"><span></span></a>
            <div id="hamburger">
              <span></span>
              <span></span>
              <span></span>
            </div>
          </span>
          <ul>
            <li><a href="#">GIỚI THIỆU</a></li>
            <li><a href="#">LỊCH CÔNG TÁC</a></li>
            <li><a href="#">THƯ ĐIỆN TỬ</a></li>
            <li><a href="#">ĐIỀU HÀNH TÁC NGHIỆP</a></li>
          </ul>
          <div class="btn_dn">
            <ul>
              <li><a href="#">Đăng nhập</a></li>
            </ul>
          </div>
        </div>
      </nav>
    </div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script>
      // on document ready
      $(document).ready(function () {
        // show/hide the mobile menu based on class added to container
        $(".menu-icon").click(function () {
          $(this).parent().toggleClass("is-tapped");
          $("#hamburger").toggleClass("open");
        });

        // handle touch device events on drop down, first tap adds class, second navigates
        $(".touch .sitenavigation li.nav-dropdown > a").on(
          "touchend",
          function (e) {
            if ($(".menu-icon").is(":hidden")) {
              var parent = $(this).parent();
              $(this).find(".clicked").removeClass("clicked");
              if (parent.hasClass("clicked")) {
                window.location.href = $(this).attr("href");
              } else {
                $(this).addClass("linkclicked");

                // close other open menus at this level
                $(this)
                  .parent()
                  .parent()
                  .find(".clicked")
                  .removeClass("clicked");

                parent.addClass("clicked");
                e.preventDefault();
              }
            }
          }
        );

        // handle the expansion of mobile menu drop down nesting
        $(".sitenavigation li.nav-dropdown").click(function (event) {
          if (event.stopPropagation) {
            event.stopPropagation();
          } else {
            event.cancelBubble = true;
          }

          if ($(".menu-icon").is(":visible")) {
            $(this).find("> ul").toggle();
            $(this).toggleClass("expanded");
          }
        });

        // prevent links for propagating click/tap events that may trigger hiding/unhiding
        $(
          ".sitenavigation a.nav-dropdown, .sitenavigation li.nav-dropdown a"
        ).click(function (event) {
          if (event.stopPropagation) {
            event.stopPropagation();
          } else {
            event.cancelBubble = true;
          }
        });

        // javascript fade in and out of dropdown menu
        $(".no-touch .sitenavigation li").hover(
          function () {
            if (!$(".menu-icon").is(":visible")) {
              $(this).find("> ul").fadeIn(100);
            }
          },
          function () {
            if (!$(".menu-icon").is(":visible")) {
              $(this).find("> ul").fadeOut(100);
            }
          }
        );
      });
    </script>
  </body>
</html>
