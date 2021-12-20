<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

   <div class="head-cm">
      <ul style="margin: 0; padding: 0">
        <li><a href="${url}/thuvienanh?id=${category.id}">${category.name}</a></li>
      </ul>
    </div>
<div class="box-top3">
      <ul>
      <c:forEach items="${listDlefile}" var="listDlefile">
        <li>
          <a
            href="${url}/album_anh?id=${listDlefile.uuidCategory}"
          >
            <img
              class="img-album-new"
              src="${listDlefile.src}"
            />
          </a>
          <h3>
            <a
              href="${url}/album_anh?id=${listDlefile.uuidCategory}">${listDlefile.title}</a
            >
          </h3>
        </li>
        </li>
        </c:forEach>
      </ul>
    </div>
<style>
 .head-cm {
        width: 100%;
        border-bottom: 1px solid #d71921;
        margin-bottom: 20px;
      }
      .head-cm ul {
        display: inline-flex;
        list-style: none;
      }
      .head-cm ul li:first-child {
        background: url("	http://bocongan.gov.vn/Publishing_Resources/web/portal/images/bg-chuyenmuc.png")
          no-repeat right top;
      }
      .head-cm ul li:first-child a {
        color: #fff;
        font: 700 16px/35px Roboto, Arial, Tahoma;
        text-transform: uppercase;
        border-right: none;
        padding: 0 30px 0 20px;
      }
      .head-cm ul li a {
        color: #404041;
        font: 400 13px/35px Roboto, Arial, Tahoma;
        padding: 0 10px;
        border-right: 1px solid #ddd;
        text-decoration: none;
      }

		.box-top3 li a {
        text-align: justify;
        font: 700 14px/19px Roboto, Arial, Tahoma;
        display: block;
      }

      .box-top3 li img {
        width: 100%;
        height: 200px;
      }



    
      .box-top3 ul {
        list-style: none;
        margin: 0;
        padding: 0;
      }

      .box-top3 ul li {
        width: 30%;
        margin-right: 2.8%;
        float: left;
        position: relative;
      }

      .box-top3 ul li a {
        text-align: justify;
        font: 700 14px/19px Roboto, Arial, Tahoma;
        color: #58595b;
        display: block;
      }

      .box-top3 ul li img {
        width: 100%;
        height: 125px;
      }
</style>