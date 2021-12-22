<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
        
<div class="lts-bovoicongdan">
	<ul>
  		<c:forEach items="${listBlogsEntryDtos}" var="blog">   
            <li>
                <h2>
                    <a>${blog.titleBlogsEntry}</a></h2>
                    <div style="text-align: justify;">
	                    <p><span style="font-size:14px;">${blog.content}</span>
	                    
	                   </p>
					 </div>
                    <a class="viewall">Xem thêm &gt;&gt;</a>
            </li>
    	</c:forEach>
    	</ul>
  </div>
