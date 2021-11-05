<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
        
<div class="lts-bovoicongdan">
	<ul>
  		<c:forEach items="${listBlogs}" var="blog">   
            <li>
                <h2>
                    <a>${blog.title}</a></h2>
                    <div style="text-align: justify;">
	                    <p><span style="font-size:14px;">${blog.content}</span>
	                    
	                   </p>
					 </div>
                    <a class="viewall">Xem thêm &gt;&gt;</a>
            </li>
    	</c:forEach>
    	</ul>
  </div>
  <style>
           .lts-bovoicongdan ul { overflow: auto; list-style-type: decimal; padding-left: 20px; font: 700 16px Roboto,Arial,Tahoma; color: #404041; }
           ul { list-style: none; margin: 0; padding: 0; }
           .lts-bovoicongdan ul li { padding-bottom: 20px; width: 46.89%; float: left; margin-right: 4%; border-right: 1px dotted #CCC; padding-right: 2%; }
           .lts-bovoicongdan ul li h2 a { font: 700 16px Roboto,Arial,Tahoma; color: #404041;text-align:justify;display:block; }    
           a{text-decoration: none;}
           .lts-bovoicongdan ul li:nth-child(2n) {border-right: none;padding-right: 0;margin-right: 0;}
           .viewall { font: 400 13px Roboto,Arial,Tahoma; font-style: normal; color: #be1e2d; font-style: italic; text-align: right; display: block; }
           .lts-bovoicongdan ul li p, .lts-bovoicongdan ul li div {color: #404041;font: 400 13px/18px Roboto,Arial,Tahoma;/* text-align: justify; */}
        </style>