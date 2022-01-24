<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <style>
      .block.border {
        border: 1px solid #9abbe2;
        border-radius: 3px 3px 0 0;
        padding-bottom: 0px;
      }
      .block {
        padding: 1px;
        margin-bottom: 5px;
        height: auto;
      }
      .block > .title {
        background: #316db5;
        border-radius: 3px 3px 0 0;
        min-height: 33px;
        background-image: url("<%= request.getContextPath()%>/images/portal/_res/img/bg-head.svg");
        background-position: center right;
        background-size: auto 31px;
        background-repeat: no-repeat;
        height: auto;
        line-height: 19px;
        padding: 5px;
      }
      .block > .title.flat {
        background: none;
        color: #333;
        line-height: 20px;
      }
      .block .title a,
      .block .tandan-div-article-other p.tandan-p-article-other {
        text-decoration: none;
        color: #fff;
        text-transform: uppercase;
      }
      .td-slide-rotate {
        width: 100%;
        height: 146px;
      }
      .block > .title,
      .block .tandan-div-article-other p.tandan-p-article-other {
        font-size: 13px;
        text-align: left;
        font-weight: bold;
      }
      .TD-ul-module-menu-left {
        line-height: 18px;
        overflow: hidden;
        overflow-y: auto;
      }
      ul {
        padding: 0;
        margin: 0;
        list-style: none;
      }
      .TD-ul-module-menu-left li {
        border-bottom: 1px solid #e0e0e0;
        padding: 5px 0;
        padding-left: 5px;
      }

      .dropup,
      .dropdown {
        position: relative;
      }
      li {
        margin: 0;
        list-style: none;
      }
      dt {
        font-weight: bold;
      }

      dt,
      dd {
        line-height: 1.42857143;
      }
      .TD-ul-module-menu-left a {
        background: url("<%= request.getContextPath()%>/images/portal/_themes/ThanhHoa_orange/img/hv-module-tvt.PNG") center left no-repeat;
        color: #222;
      }

      .TD-ul-module-menu-left a {
        font-size: 13px;
        padding-left: 15px;
        text-decoration: none;
        font-family: Roboto;
        font-weight: 500;
      }
      .TD-ul-module-menu-left a:hover {
	    color: #c10600;
	    background: url("<%= request.getContextPath()%>/images/portal/_themes/ThanhHoa_orange/img/hv-module-tvt-hv.PNG") center left no-repeat;
}
    </style>
  </head>
  <body>
    <div class="block border has-icon" cellpadding="0" cellspacing="0">
      <div class="title">
        <a href="#">${categoryDto.name}</a>
      </div>

      <div class="body">
        <ul class="TD-ul-module-menu-left">
        <c:forEach items="${listCategoryDtos}" var="listCategoryDtos">
          <li class="dropdown">
            <dt
              id="1534-ddheader"
              class="upperdd"
              onmouseover="ddMenu('1534',1)"
              onmouseout="ddMenu('1534',-1)"
            >
              <a href="${url}/detail?uuid=${listCategoryDtos.uuid}"
                >${listCategoryDtos.name}</a
              >
            </dt>
            <dd
              id="1534-ddcontent"
              class="Menu-2c-tvt"
              onmouseover="cancelHide('1534')"
              onmouseout="ddMenu('1534',-1)"
              style="
                opacity: 0;
                display: none;
                left: 271px;
                height: auto;
                z-index: 1025;
                top: 0px;
              "
            ></dd>
          </li>
          </c:forEach>
        </ul>
      </div>
    </div>
  </body>
</html>
