<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <style>
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
      .TD-ul-module-menu-left a {
        background: url("<%= request.getContextPath()%>/images/portal/_themes/ThanhHoa_orange/img/hv-module-tvt.PNG") center left no-repeat;
        color: #222;
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
