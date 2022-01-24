<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <style>
    .head-cm ul li a {
    color: #404041;
    font: 400 13px/35px Roboto, Arial, Tahoma;
    padding: 0 15px;
    border-right: 1px solid #ddd;
}

.head-cm ul li:first-child {
    background: url('<%= request.getContextPath()%>/images/portal/images/bg-chuyenmuc.png') no-repeat right top;
}

.head-cm ul li:first-child a {
    color: #FFF;
    font: 700 16px/35px Roboto, Arial, Tahoma;
    text-transform: uppercase;
    border-right: none;
    padding: 0 30px 0 20px;
}

.head-cm ul li:last-child a {
    border-right: none;
}
.head-cm ul {
    display: inline-flex;
    list-style: none;
    position: relative;
    top: 12px;
    left: 0;
    transform: translate(-12px, 0);
}
.head-cm {
    width: 100%;
    border-bottom: 1px solid #0a4298;
    position: relative;
    margin-bottom: 20px;
}
      .box-body {
        padding: 12px;
      }
      @media only screen and (min-width: 992px) {
        .box-body {
          padding-right: 17px;
          padding-left: 17px;
        }
      }
      .row {
        margin-right: -5px;
        margin-left: -5px;
      }
      .row {
        margin-right: -15px;
        margin-left: -15px;
        display: flex;
        flex-wrap: wrap;
      }
      @media (min-width: 992px) {
        .col-lg-12 {
          flex: 0 0 100%;
          max-width: 100%;
        }
      }
      .col-lg-12 {
        width: 100%;
        position: relative;
      }
      .post {
        text-align: justify;
        overflow: hidden;
        position: relative;
      }
      .mb-2 {
        margin-bottom: 0.5rem !important;
      }
      article {
        display: block;
      }

      .post-title.h3 {
        font-size: 19px;
      }

      .post-title {
        line-height: 1.2;
        font-family: "Roboto Condensed", sans-serif;
        font-size: 16px;
        font-weight: bold;
        margin-bottom: 0px;
      }
      .col-6 {
        padding-right: 5px;
        padding-left: 5px;
      }
      .col-6 {
        flex: 0 0 50%;
        max-width: 50%;
      }
      .col-6 {
        width: 100%;
        padding-right: 15px;
        padding-left: 15px;
        position: relative;
      }
      .article-sub-news {
        margin-bottom: 14px;
      }
      .article-sub-news .figure {
        padding-top: 65%;
      }
      .hover a {
        display: block;
        position: relative;
      }
      .figure {
        padding: 59% 0px 0px;
        width: 100%;
        overflow: hidden;
        display: block;
        position: relative;
      }
      .hover img {
        width: 100%;
      }

      .figure img {
        transition: 700ms ease-out;
        left: 0px;
        top: 0px;
        width: 100%;
        right: 0px;
        bottom: 0px;
        display: block;
        position: absolute;
        min-height: 100%;
      }
      img {
        width: auto;
        height: auto;
        max-width: 100%;
        vertical-align: middle;
      }
    </style>
  </head>
  <body>
 	<div class="head-cm">
		<ul>
			<li><a href="#">${categoryDto.name}</a></li>
		</ul>
	</div>
    <div class="box-body">
      <div class="row">
        <div class="col-md-6 col-lg-12">
          <article class="post mb-2">
            <div class="post-thumb hover">
              <a
                class="figure"
                href="https://h05.bca/web/cuch05/tin-chi-tiet/-/chi-tiet/bai-hoc-kinh-nghiem-ve-an-toan-thong-tin-tu-ai-dich-covid-19-699-39.html"
                ><img
                  alt=""
                  src="${blogsEntryDto.src}"
              /></a>
            </div>
            <div class="post-text">
              <h3 class="post-title h3">
                <a
                  href="https://h05.bca/web/cuch05/tin-chi-tiet/-/chi-tiet/bai-hoc-kinh-nghiem-ve-an-toan-thong-tin-tu-ai-dich-covid-19-699-39.html"
                  >${blogsEntryDto.titleBlogsEntry}</a
                >
              </h3>
              <span class="post-ago">(15:10 17/01/2022) </span>
              <div class="post-desc">
                ${blogsEntryDto.description}
              </div>
            </div>
          </article>
        </div>
        <div class="col-md-6 col-lg-12">
          <div class="row">
          <c:forEach items="${listBlogEntryDto}" var="blog">
            <div class="col-6">
              <article class="post article-sub-news">
                <div class="post-thumb hover">
                  <a
                    class="figure"
                    href="https://h05.bca/web/cuch05/tin-chi-tiet/-/chi-tiet/youtube-bi-cao-buoc-truyen-tai-thong-tin-sai-lech-698-39.html"
                    ><img
                      alt=""
                      src="${blog.src}"
                  /></a>
                </div>
                <div class="post-text">
                  <h3 class="post-title">
                    <a
                      title=""
                      href="https://h05.bca/web/cuch05/tin-chi-tiet/-/chi-tiet/youtube-bi-cao-buoc-truyen-tai-thong-tin-sai-lech-698-39.html"
                      >${blog.titleBlogsEntry}</a
                    >
                  </h3>
                </div>
              </article>
            </div>
            </c:forEach>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>