<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="porlet"%>
<html>
<body>
	<div class="contentContainer">
	 <div class="head-cm">
      <ul style="padding-left: 0">
        <li><a href="#"></a>${asc.name}</li>
      </ul>
    </div>
        <c:forEach items="${listBlogsEntryDtos}" var="blog">
							  <div class="newsItem">
							        <div class="container">
							        
										<a href="#">
										<img src="/documents/${blog.groupId}/${blog.folderId}/${blog.titleDlFileEntry}/${blog.uuidDlFileEntry}" alt="" />
										</a>
										<h2>
										<a href="#">${blog.titleBlogsEntry}</a>
										</h2>
										<p class="des">${blog.description}</p>
							        </div>
							      </div>
		</c:forEach>
        </div>
</body>
</html>
<style>
      .contentContainer .newsItem {
      }
      .contentContainer .newsItem .container {
        clear: both;
        display: inline-block;
        padding-bottom: 20px;
        margin-bottom: 20px;
        width: 100%;
        border-bottom: 1px dotted #ccc;
      }
      .contentContainer .newsItem img {
        float: left;
        margin-right: 10px;
        width: 40%;
      }
      .contentContainer .newsItem h2 a,
      .contentContainer .newsItem h2 a:link,
      .contentContainer .newsItem h2 a:visited {
        color: #404041;
        font: 700 18px/23px Roboto, Arial, Tahoma;
        text-decoration: none;
        text-align: justify;
        display: block;
      }
      .contentContainer .newsItem h2 a:hover {
        color: #be1e2d;
        text-decoration: none;
      }
      .contentContainer .newsItem p.des {
        color: #333;
        font: 14px/18px "Roboto", Arial, Helvetica, sans-serif;
        padding-top: 10px;
        text-align: justify;
      }
      .contentContainer .newsItem p.date span {
        background: rgba(0, 0, 0, 0) url("../images/icon-time.png") no-repeat
          scroll 0 4px;
        padding: 3px 5px 3px 20px;
      }
      .head-cm {
        width: 100%;
        border-bottom: 1px solid #d71921;
        margin-bottom: 20px;
      }
      .head-cm ul li:first-child {
        background: url("http://bocongan.gov.vn/Publishing_Resources/web/portal/images/bg-chuyenmuc.png")
          no-repeat right top;
      }
      .head-cm ul {
        display: inline-flex;
        list-style: none;
      }
      .head-cm ul li:first-child a {
        color: #fff;
        font: 700 16px/35px Roboto, Arial, Tahoma;
        text-transform: uppercase;
        border-right: none;
        padding: 0 30px 0 20px;
        text-decoration: none;
      }
    </style>