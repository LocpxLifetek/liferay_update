<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="porlet" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<body>
<div class="albums_anh">
            <div class="head-albums_anh">
                <a style="color: #404041;" href="${url}/album-anh">Chuyên mục ảnh</a>
                <div class="linered"></div>
            </div>
            <div class="lts-cmanh">
                
                <div class="top1">
                    <a href="#">
                        <img style="max-width:100%; min-width:20%;" src="${list.src}">
                    </a>
                    <div><a href="${url}/album_anh?id=${list.uuidCategory}">${list.title}</a></div>
                </div>
                
                <ul>
                    
                    <c:forEach items="${listDlfileNoImage}" var="listDlfileNoImage">
                        <li><a class="album_bottom" href="${url}/album_anh?id=${listDlfileNoImage.uuidCategory}">${listDlfileNoImage.title}</a></li>
                    </c:forEach>
                
                </ul>   
            </div>
        </div>
</body>

</html>