<%@ include file="/init.jsp" %>

<div class="chidao-dieuhanh">
            <div class="head-chidaodieuhanh">
                ${categoryName.categoryName}
                <div class="linered"></div>
            </div>
            	<div class="lts-cmanh tree-style">
            	 <script>
          var expandElement = function (element) {
            var liElement = $(element).parent();
            var ulElement = $(liElement).children("ul");
            var hasChild = ulElement.length != 0;
            if ($(ulElement).is(":visible")) {
              $(ulElement).hide();
              if (hasChild) {
                $(liElement).removeClass("i-doc");
                $(liElement).addClass("i-ngang");
              }
            } else {
              $(".tree-style li ul").each(function () {
                var isParent = false;
                $(this)
                  .find("li")
                  .each(function () {
                    if (this == $(liElement).get(0)) {
                      isParent = true;
                    }
                  });
                if (!isParent) {
                  $(this).hide();
                  $(this).parent().removeClass("i-doc");
                  $(this).parent().addClass("i-ngang");
                } else {
                  $(this).show();
                  $(this).parent().addClass("i-doc");
                  $(this).parent().removeClass("i-ngang");
                }
              });
              $(ulElement).show();
              if (hasChild) {
                $(liElement).removeClass("i-ngang");
                $(liElement).addClass("i-doc");
              }
            }
          };

          $(document).ready(function () {
            setExpandCollapse();

            $(".lts-cmanh a").removeClass("active");
            var currentUrl = location.href.toLowerCase(); //location.pathname.toLowerCase();
            var currentU = "";
            $(".lts-cmanh a").each(function () {
              var linkCheck = $(this).attr("href").toLowerCase();
              if (currentUrl.indexOf(linkCheck) > -1) {
                if (linkCheck.indexOf(currentU) > -1) currentU = linkCheck;
              }
            });
            $(".lts-cmanh a").each(function () {
              if ($(this).attr("href").toLowerCase() == currentU) {
                $(this).addClass("active");
                expandElement(this);
                //$(this).parent().parent().parent().parent().show();
              }
            });
          });
          var setExpandCollapse = function () {
            $(".tree-style > ul > li").each(function () {
              $(this).addClass("i-ngang");
              $(this)
                .find("li")
                .each(function () {
                  if ($(this).find("ul").length > 0) {
                    $(this).addClass("i-ngang");
                  }
                });
            });

            $(".tree-style li ul").each(function () {
              $(this).hide();
            });

            $(".tree-style a").click(function () {
              expandElement(this);
              var liElement = $(this).parent();
              var ulElement = $(liElement).children("ul");
              var hasChild = ulElement.length != 0;

              return !hasChild;
            });

            $(".tree-style a").dblclick(function () {
              var url = $(this).attr("href");
              document.location.href = url;
            });
          };
        </script>
				      <ul>
				      <c:forEach items="${listDlefile}" var="listDlefile">
						<li class="i-ngang">
                        	<a href="${url}/album_anh?id=${listDlefile.uuidCategory}"
						       title="${listDlefile.title}"
						       class="">${listDlefile.title}</a>
                    	</li>
                    	</c:forEach>
				      </ul>
            </div>
        </div>
        <style>
           .chidao-dieuhanh {background: #FFF url('http://bocongan.gov.vn/Publishing_Resources/web/portal/images/bg-right.png') repeat-x center top;border: 1px solid #ddd;overflow: auto;}
           .head-chidaodieuhanh { font: 700 18px/35px Roboto,Arial,Tahoma; color: #404041; text-transform: uppercase; text-align: center; margin: 15px 0 5px 0; }
           .linered { border-top: 2px solid #d71920; width: 30%; margin: 0 auto; }
           .lts-cmanh ul li a.active {
        font-weight: bold;
        color: #be1e2d;
      }
      .lts-cmanh ul li ul li:first-child {
        border-top: 1px dotted #ccc;
        margin-top: 10px;
      }
      .lts-cmanh ul li {
        background-position-y: 18px;
        border-bottom: 1px dotted #ccc;
        padding: 10px 0 10px 20px;
      }
      .i-ngang {
        background: url(http://bocongan.gov.vn/Publishing_Resources/web/media/images/i-ngang.png)
          no-repeat 7px 13px !important;
      }
      ul {
        list-style: none;
        margin: 0;
        padding: 0;
      }
      .lts-cmanh ul li:last-child {
        border-bottom: none;
      }
      .lts-cmanh ul li a {
        color: #333;
        font: 400 15px/18px Roboto, Arial, Tahoma;
        text-decoration: none;
      }
      .lts-cmanh ul li a.active {
        font-weight: bold;
        color: #be1e2d;
      }
      .lts-cmanh ul li a:hover {
        font-weight: bold;
        color: #be1e2d;
      }
        </style>
