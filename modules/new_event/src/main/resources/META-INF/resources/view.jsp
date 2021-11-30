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
						                        <img src="/documents/${blog.groupId}/${blog.folderId}/${blog.titleDlFileEntry}/${blog.uuidDlFileEntry}"></a>

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
					                            <img src="/documents/${listBlog.groupId}/${listBlog.folderId}/${listBlog.titleDlFileEntry}/${listBlog.uuidDlFileEntry}">${listBlog.titleBlogsEntry}
					                            
					                        </a>
					                       
					                    </li>
								</ul>
					</c:forEach> 
            </div>
            
        </div>
</body>

</html>
<style>
            .ltstop2 {overflow: auto;}
            .ltstop2 .top2 { width: 100%; display: flex; }
            .info{width: 50%; padding-right: 15px;}
            a{text-decoration: none;}
            .ltstop2 .top2 .info a { color: #404041; font: 700 18px/24px Roboto,Arial,Tahoma; display: block; padding: 10px 0 5px 0; text-align:justify; }
            .ltstop2 .top2 .info p { font: 400 13px/18px Roboto,Arial,Tahoma; text-align: justify; }
            .ltstop2 .top2:last-child { float: right !important; }
            .ltstop2 .top2 a img { width: 100%; margin:0 auto;}
            .ltstop4 { margin-top: 20px; border-bottom: 1px dotted #acacad; overflow: auto; }
            .ltstop4 ul{list-style: none;}
            .ltstop4 ul li { width: 50%; float: left; margin-bottom: 20px; min-height:80px; padding-right: 15px;}
            .ltstop4 ul li a { font: 700 15px/18px Roboto,Arial,Tahoma; color: #333; display: block; text-align: justify; }
            .ltstop4 ul li img { float: left; width: 20%; margin-right: 15px; }
            @media screen and (max-width: 1024px) {
            .info, .ltstop4 ul li
			  {
			    width: 100%; 
			  }
			  .ltstop2 .top2 { width: 100%; display: block !important; }
			}
            
            
        </style>