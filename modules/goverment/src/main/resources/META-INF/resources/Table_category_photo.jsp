<%@ include file="/init.jsp" %>

<div class="chidao-dieuhanh">
            <div class="head-chidaodieuhanh">
                ${categoryName.name}
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
				      <c:forEach items="${listCategory}" var="listCategory">
						<li class="i-ngang" style="background: url('<%= request.getContextPath()%>/images/portal/_themes/ThanhHoa_orange/img/hv-module-tvt.PNG') no-repeat 7px 13px !important;
">
                        	<a href="${url}/album-anh?uuid=${listCategory.uuid}"
						       title="${listCategory.name}"
						       class="">${listCategory.name}</a>
                    	</li>
                    	</c:forEach>
				      </ul>
            </div>
        </div>
