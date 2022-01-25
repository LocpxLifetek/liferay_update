<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<style>
    .box-left-home {
        overflow: auto;
        margin-bottom: 20px;
        border-bottom: 1px dotted #666;
    }

    .head-cm .xemthem {
        float: right;
        font: 400 13px Roboto, Arial, Tahoma;
        color: #be1e2d;
        display: block;
        padding-top: 12px;
    }
	 .head-cm ul li:first-child {
        background:url("<%=request.getContextPath()%>/images/portal/_res/img/bg-chuyenmuc.png") no-repeat right top;
    }
    .head-cm ul li:last-child a {
        border-right: none;
    }
    .box-top3 { overflow: hidden; }
    .box-top3 ul { }
        .box-top3 ul li { width: 30.71428571428571%; margin-right: 3.8%; float: left; position: relative; }
		.box-top3 ul li img {width:100%; height:125px;}
            .box-top3 ul li .bg-video { background: url('<%=request.getContextPath()%>/images/portal/_res/img/i-play-video.png') no-repeat bottom left; }
            .box-top3 ul li:nth-child(3n + 3) { margin-right: 0; }
            .box-top3 ul li a { text-align:justify; font: 700 14px/19px Roboto,Arial,Tahoma; color: #58595B; display: block; }
			.box-top3 ul li h3 a {padding: 10px 0;}
                .box-top3 ul li a.iconplay { width: 45px; height: 45px; position: absolute; left: 0; bottom: 0px; background: url(<%=request.getContextPath()%>/images/portal/_res/img/i-play-video.png) no-repeat; float: left; }
    
</style>

<div class="box-left-home">
        <div class="head-cm" style="margin-bottom:20px;">
            <ul>
                <li><a href="/media/thu-vien-video.html">Video</a></li>
            </ul>
            <a href="/media/thu-vien-video.html" class="xemthem">Xem thêm</a>
        </div>
        <div class="box-top3">
            <ul>
				<c:forEach items="${listDlFileVideoDtos}" var="dlfile">
                <li>
                    <div style="position: relative">
                        <a href="#">
                            <img src="${dlfile.src}">
                        </a>
                        <a href="#"
                            class="iconplay"></a>
                    </div>
                    <h3><a href="#">${dlfile.title}</a></h3>

                </li> 
				</c:forEach>
            </ul>
        </div>
    </div>