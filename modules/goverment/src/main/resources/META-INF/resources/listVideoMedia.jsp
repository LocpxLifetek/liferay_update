<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<style>
    
	 .head-cm ul li:first-child {
        background:url("<%=request.getContextPath()%>/images/portal/_res/img/bg-chuyenmuc.png") no-repeat right top;
    }
    .box-top3 ul li .bg-video { background: url('<%=request.getContextPath()%>/images/portal/_res/img/i-play-video.png') no-repeat bottom left; }
     .box-top3 ul li a.iconplay { width: 45px; height: 45px; position: absolute; left: 0; bottom: 0px; background: url(<%=request.getContextPath()%>/images/portal/_res/img/i-play-video.png) no-repeat; float: left; }
    
</style>

<div class="box-left-home">
        <div class="head-cm" style="margin-bottom:20px;">
            <ul>
                <li><a href="${url}/thu-vien-video">Video</a></li>
            </ul>
            <a href="${url}/thu-vien-video" class="xemthem">Xem thêm</a>
        </div>
        <div class="box-top3">
            <ul>
				<c:forEach items="${listDlFileVideoDtos}" var="dlfile">
                <li>
                    <div style="position: relative">
                        <a href="${url}/video?uuid=${dlfile.uuid}">
                            <img src="${dlfile.src}">
                        </a>
                        <a href="${url}/video?uuid=${dlfile.uuid}"
                            class="iconplay"></a>
                    </div>
                    <h3><a href="${url}/video?uuid=${dlfile.uuid}">${dlfile.title}</a></h3>

                </li> 
				</c:forEach>
            </ul>
        </div>
    </div>