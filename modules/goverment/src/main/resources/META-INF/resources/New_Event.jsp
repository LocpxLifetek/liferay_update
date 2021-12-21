<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<body>
	
	<div class="box-left-home">
    
            
             <div class="ltstop2">
                 
                <div class="top2">
                    <c:forEach items="${listBlogs2}" var="blog">
						                    <div class="info">
							                    <a>
						                        <img src="${blog.src}"></a>

						                        <h2><a>${blog.titleBlogsEntry}</a></h2>
						                        <p>${blog.description}</p>
						                    </div>
					</c:forEach>
                </div>
            </div>
            <div class="ltstop4">
                    <c:forEach items="${listBlogs6}" var="listBlog">
								<ul>
								
										<li style="min-height: 100px;">
                        
					                        <a>
					                            <img src="${listBlog.src}">${listBlog.titleBlogsEntry}
					                            
					                        </a>
					                       
					                    </li>
								</ul>
					</c:forEach> 
            </div>
            
        </div>
</body>

</html>
