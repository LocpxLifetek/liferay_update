<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.menu_1{
    width: 97%;
}
.listDirectOperationRight {
    display: grid;
}

.ldo a{
    color: black !important;
    text-decoration: none;
    font-size: 16px;
    font-weight: bold;
    text-align: justify;
    display: block;
}
.listDirectOperationGrid{
    border-bottom: 1px dotted #ccc;
    padding: 15px 0px;
}
.ldo a:hover{
    color:red !important;
}
p{
    text-align: justify;
    display: block;
}
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
.soup-image {
	float:left;
	margin-right:2%;
	border: 4px double rgb(212, 211, 211);
}
#wrapper {
    margin: 0 auto;
    display: block;
    width: 100%;
  }
  
  #pagination {
    margin: 0;
    padding: 0;
    text-align: center
  }
  #pagination li {
    display: inline
  }
  #pagination li a {
    display: inline-block;
    text-decoration: none;
    padding: 5px 10px;
    color: #000
  }
  
  /* Active and Hoverable Pagination */
  #pagination li a {
    border-radius: 5px;
    -webkit-transition: background-color 0.3s;
    transition: background-color 0.3s
      
  }
  #pagination li a.active {
    background-color: #4caf50;
    color: #fff
  }
  #pagination li a:hover:not(.active) {
    background-color: #ddd;
  } 
</style>
<div class="menu_1">
	<div class="head-cm">
		<ul>
			<li><a href="#">${categoryDto.name}</a></li>
		</ul>
	</div>
	<div class="listDirectOperation">
		<div class="listDirectOperationRight">
					<c:forEach items="${listBlogs}" var="blog">

						<div class="listDirectOperationGrid">
							<div class="ldo">
								<img src="${blog.src}" class="soup-image"
									style="width: 30% !important"> <a
									href="${url}/detail?id=${blog.uuidBlogsEntry}">${blog.titleBlogsEntry}</a>
								<span style="color: #d71920; font-size: 12px;">(<fmt:formatDate
										value="${blog.modifiedDate}" pattern="MM/dd/yyyy" />)
								</span> <br>
								<p>${blog.description}</p>
							</div>
						</div>

					</c:forEach>
		</div>
	</div>
</div>





