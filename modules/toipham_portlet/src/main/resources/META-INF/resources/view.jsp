<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<div class="box-canhgiac">
                <div class="img_toipham">
                    <img width="100%" src="http://bocongan.gov.vn/Publishing_Resources/images/box-canhgiac.png">
                </div>  
                <div class="lts-cmanh">
                    <c:forEach items="${listBlogsEntryDtos}" var="u">
				      <ul  style="width:100%">
						<li>
							${u.titleBlogsEntry}
						</li>
				      </ul>
				    </c:forEach>
				    <a href="/canh-bao-toi-pham-104.html" title="Xem thêm" class="viewall">Xem thêm &gt;&gt;</a>
                </div>
       </div>
       <style>
            .lts-cmanh ul li {text-align:justify; list-style: none;}
            .box-canhgiac{border: 1px solid #ddd;}
            .lts-cmanh {border-bottom: 4px solid #d71920; }
            a{text-decoration: none;}
            .lts-cmanh ul li a { color: #333; font: 400 15px/18px Roboto,Arial,Tahoma; display:block;text-align:justify; }
            .viewall { font: 400 13px Roboto,Arial,Tahoma; font-style: normal; color: #be1e2d; font-style: italic; text-align: right; display: block; }
            .lts-cmanh ul li { background: url('http://bocongan.gov.vn/Publishing_Resources/web/portal/images/i-bul.png') no-repeat left 18px; border-bottom: 1px dotted #ccc; padding: 10px 0 10px 15px; }
            @media screen and (max-width: 974px) {
            	.box-canhgiac{
            		display: none;
            	}
			}
        </style>