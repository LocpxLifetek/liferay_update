<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="porlet" %>
<html>
<body>
<div class="albums_anh">
            <div class="head-albums_anh">
                <a style="color: #404041;" href="/media.html">Chuyên mục ảnh</a>
                <div class="linered"></div>
            </div>
            <div class="lts-cmanh">
                
                <div class="top1">
                    <a href="#">
                        <img style="max-width:100%; min-width:20%;" src="/documents/${blogsEntryDto.groupId}/${blogsEntryDto.folderId}/${blogsEntryDto.titleDlFileEntry}/${blogsEntryDto.uuidDlFileEntry}">
                    </a>
                    <div><a href="/media/album-anh/bo-cong-an-to-chuc-hoi-nghi-so-ket-cong-tac-cong-an-6-thang-dau-nam-2021-179.html">${blogsEntryDto.titleBlogsEntry}</a></div>
                </div>
                
                <ul>
                    
                    <c:forEach items="${listBlogsNoImage}" var="blog">
                        <li><a class="album_bottom">${blog.titleBlogsEntry}</a></li>
                    </c:forEach>
                
                </ul>   
            </div>
        </div>
</body>

</html>
<style>
           .head-albums_anh { font: 700 18px/35px Roboto,Arial,Tahoma; color: #404041; text-transform: uppercase; text-align: center; margin: 15px 0 5px 0; }
           .albums_anh { background: #FFF url('http://bocongan.gov.vn/Publishing_Resources/web/portal/images/bg-right.png') repeat-x center top; border: 1px solid #ddd; overflow: auto; }
           .linered { border-top: 2px solid #d71920; width: 30%; margin: 0 auto; }
           .lts-cmanh { padding: 10px; border-bottom: 4px solid #d71920; }
           .lts-cmanh .top1 { background: #FFF; border: 1px solid #ccc; }
           .lts-cmanh .top1 div { padding: 8px 10px; }
           .lts-cmanh .top1 a:hover.album_bottom{color: red;}
           .lts-cmanh .top1 div a { color: #404041; font: 700 15px/18px Roboto,Arial,Tahoma; text-align: justify; display:block;}
           .lts-cmanh ul { list-style: none; }
           .lts-cmanh ul li { background: url('http://bocongan.gov.vn/Publishing_Resources/web/portal/images/i-bul.png') no-repeat left 18px; border-bottom: 1px dotted #ccc; padding: 10px 0 10px 15px; text-align: justify;}
           .lts-cmanh ul li a { color: #333; font: 400 15px/18px Roboto,Arial,Tahoma; display:block;text-align:justify; }
           a,.ms-link:visited{text-decoration:none;}
           @media screen and (max-width: 974px) {
            	.albums_anh{
            		display: none;
            	}
			}
        </style>