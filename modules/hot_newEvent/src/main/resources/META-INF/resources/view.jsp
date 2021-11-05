<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
    <div class="slider-new">
     <div id="featured">
        <div class="left" style="width: 50%">
          <div id="fragment-0" class="ui-tabs-panel lefts" style="display: block;">
            <div class="box-img">
                <div class="tit">
                        <a>
                        <img id="daylaanhreview" src="" alt="" />
                        <div id="daylatieude"></div>
                        <div id="des"></div>
                        </a>
                </div>
            </div>
          </div>
        </div>
        <div class="right" style="width: 50%">
          <div class="ct">
            <ul id="cal-tabs" class="ui-tabs-nav rights">
            <c:forEach items="${listBlogs}" var="listblog">
						<c:choose>
							<c:when test="${listblog.smallImageFileEntryId > 0}">
								<c:forEach items="${smallImage}" var="si">
									<c:if test="${listblog.smallImageFileEntryId == si.fileEntryId}">
										<li class="ui-tabs-nav-item" id="nav-fragment-1">
						                  <a><img
						                    src="/documents/${si.groupId}/${si.folderId}/${si.title}/${si.uuid}"/>
						                  ${listblog.title}
						                </a>
						              </li>
									</c:if>
								</c:forEach>
							</c:when>
						</c:choose>
					</c:forEach>
            </ul>
          </div>
        </div>
 <script>
        var containerContent = document.querySelector("#daylatieude");
        var reviewImage = document.querySelector("#daylaanhreview");
        var postItem = document.querySelectorAll(".ui-tabs-nav-item");
        postItem.forEach((item) => {
          item.addEventListener("mouseover", () => {
            var src = item.querySelector("img").src;
            var content = item.innerText;
            reviewImage.src = src;
            containerContent.innerText = content;
          });
        });
      </script>
      </div>
      <style>
      #featured {
        position: relative;
        min-height: 600px;
        overflow: hidden;
        background: #fff;
      }
      #featured ul.ui-tabs-nav {
        position: absolute;
        top: 0;
        left: 63.04131054131054%;
        list-style: none;
        padding: 0;
        margin: 0;
        width: 36.75213675213675%;
        overflow: auto;
        overflow-x: auto;
        overflow-x: hidden;
      }
      #featured ul.ui-tabs-nav li {
        color: #666;
        float: right;
        margin-bottom: 7px;
        padding-bottom: 7px;
        width: 100%;
      }
      #featured ul.ui-tabs-nav li img {
        float: left;
        padding-right: 10px;
        width: 40%;
      }
      #featured ul.ui-tabs-nav li span {
        font: normal 13px/18px "Roboto", Arial, Verdana;
        display: block;
      }
      #featured li.ui-tabs-nav-item a p {
        font-size: 15px;
        color: #1b75bb;
      }
      #featured li.ui-tabs-nav-item a {
        font-family: Roboto, Arial, Tahoma;
        text-align: justify;
        display: block;
        height: auto;
        text-decoration: none;
        color: #58585b;
        background: #fff;
        outline: none;
        font-size: 14px;
      }
      #featured li.ui-tabs-nav-item a:hover {
        background: #fff;
        font-weight: bold;
        color: #be1e2d;
      }
      #featured li.ui-tabs-nav-item:last-child a {
        border-bottom: none;
      }
      #featured li.ui-tabs-selected span,
      #featured li.ui-tabs-active span {
        font: 700 13px Roboto, Arial, Tahoma !important;
      }
      #featured ul.ui-tabs-nav li.ui-tabs-selected a,
      #featured ul.ui-tabs-nav li.ui-tabs-active a {
        background: #fff;
        font-weight: bold;
      }
      #featured .ui-tabs-panel {
        width: 90%;
        position: relative;
        text-align: justify;
      }
      #featured .ui-tabs-panel img {
        width: 90%;
        margin: 0 auto;
        margin-bottom: 5px;
      }
      #featured .ui-tabs-panel .info {
        bottom: 0;
        left: 0;
        height: 70px; /*background: url('images/transparent-bg.png');*/
      }
      #featured .ui-tabs-panel .info a.hideshow {
        position: absolute;
        font-size: 15px;
        font-family: "Roboto", Arial, Verdana;
        color: #f0f0f0;
        right: 10px;
        top: -20px;
        line-height: 20px;
        margin: 0;
        outline: none;
      }
      #featured .info h2 {
        font: 700 18px/24px "Roboto", Arial, Verdana;
        padding: 5px 0;
        margin: 0;
        font-weight: normal;
        overflow: hidden;
      }
      #featured .info p {
        font-family: "Roboto", Arial, Verdana;
        font-size: 13px;
        line-height: 18px;
        color: #58585b;
        text-align: justify;
      }
      #featured .info a {
        text-decoration: none;
        color: #404041;
        font-weight: bold;
      }
      #featured .info a:hover {
        text-decoration: none;
      }
      #featured .ui-tabs-hide {
        display: none;
      }
      #featured ul.ui-tabs-nav li:last-child {
        margin: 0;
        padding: 0;
        border-bottom: none;
      }
      .fix-slider {
        text-align: center;
      }

      .box-img .tit a {
        font: 700 18px/24px "Roboto", Arial, Verdana;
        text-decoration: none;
        color: #404041;
      }
      .des .ct {
        text-align: justify;
        font: 400 13px/18px Roboto, Arial, Tahoma;
        margin-top: 10px;
      }
    </style>
            
                