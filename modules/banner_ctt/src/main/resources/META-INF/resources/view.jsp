<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <style>
    	body {
        margin: 0px;
      }
      .banner_left,
      .banner_right {
        width: auto;
        height: 150px;
      }
      .header-banner {
        background-color: rgb(215, 3, 17);
        height: 150px;
      }
      .header-top {
        position: relative;
        min-height: 50px;
      }
      .header-top .box-search {
        top: 5px;
        right: 10px;
        display: block;
        position: absolute;
        z-index: 2;
      }
      .bg-search {
        background: url("<%= request.getContextPath()%>/image/bg-search.png") no-repeat;
        border: none !important;
        width: 280px;
        padding: 5px 48px 5px 10px !important;
        line-height: 27px;
        border-radius: 25px 25px;
      }
      .header-top .header-flag {
        top: 0px;
        right: 0px;
        bottom: 0px;
        position: absolute;
        padding-right: 10%;
      }
      .box-search input#btnSearch {
        outline: none !important;
        min-width: 20px;
        height: 33px;
        padding: 0;
        margin: 0px 10px 0px -50px;
        width: 50px;
        background: none;
        border: none;
        cursor: pointer;
        color: transparent;
      }
      @media screen and (max-width: 1700px) {
        .header-banner,
        .container_banner,
        .banner_left,
        .banner_right {
          height: 120px;
        }
        .header-top .header-flag{
        	padding-right:20%;
        }
      }
      @media only screen and (max-width: 1500px) {
        .box-search {
          display: none !important;
        }
        .header-banner,
        .container_banner,
        .banner_left,
        .banner_right {
          height: 100px;
        }
        .header-top .header-flag{
        	padding-right:15%;
        }
      }
      @media only screen and (max-width: 1200px) {
        .header-top .box-search {
          display: block;
        }
        .header-banner,
        .container_banner,
        .banner_left,
        .banner_right {
          height: 70px;
        }
      }
       @media only screen and (max-width: 850px) {
        .header-top .box-search {
          display: block;
        }
        .banner_right {
          display: none;
        }
      }
    </style>
  </head>
  <body>
    <div class="header-banner">
      <div class="container_banner">
        <div class="header-top">
          <img class="banner_left" alt="" src="<%= request.getContextPath()%>/image/banner.jpg"/>
          <div class="header-flag">
            <img class="banner_right" alt="" src="<%= request.getContextPath()%>/image/flag.gif" />
          </div>
          <div class="box-search">
            <input
              type="text"
              name="isearch"
              id="isearch"
              class="bg-search"
              placeholder="Tìm kiếm"
            />
            <input
              type="button"
              name="btnSearch"
              id="btnSearch"
              value="Tìm kiếm"
            />
          </div>
        </div>
      </div>
    </div>
  </body>
</html>

