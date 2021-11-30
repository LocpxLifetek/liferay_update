<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="chidao-dieuhanh">
            <div class="head-chidaodieuhanh">
                Chỉ đạo điều hành
                <div class="linered"></div>
            </div>
            <div class="lts-chidaodieuhanh">
                <c:forEach items="${listBlogsNoImage}" var="blog">
				      <ul>
						<li>
                        	<a href="/diem-tin-interpol/tin-tuc/tin-tuc-su-kien/chi-dao-dieu-hanh/bo-truong-to-lam-gui-thu-khen-toan-the-can-bo-chien-si-cong-an-xa-thi-tran-d24-t30338.html">${blog.titleBlogsEntry}</a>
                    	</li>
				      </ul>
				 </c:forEach>
                <a href="/tin-tuc-su-kien/chi-dao-dieu-hanh-24.html" title="Xem thêm" class="viewall">Xem thêm &gt;&gt;</a>
                
            </div>
        </div>
        <style>
           .chidao-dieuhanh {background: #FFF url('http://bocongan.gov.vn/Publishing_Resources/web/portal/images/bg-right.png') repeat-x center top;border: 1px solid #ddd;overflow: auto;}
           .head-chidaodieuhanh { font: 700 18px/35px Roboto,Arial,Tahoma; color: #404041; text-transform: uppercase; text-align: center; margin: 15px 0 5px 0; }
           .linered { border-top: 2px solid #d71920; width: 30%; margin: 0 auto; }
           .lts-chidaodieuhanh { padding: 15px; border-bottom: 4px solid #d71920; }
           .lts-chidaodieuhanh ul { list-style: none; }
           .lts-chidaodieuhanh ul li { background: url('http://bocongan.gov.vn/Publishing_Resources/web/portal/images/i-v.png') no-repeat; padding-left: 25px; margin-bottom: 15px; }
           .lts-chidaodieuhanh ul li a { color: #333; font: 700 15px/18px Roboto,Arial,Tahoma; display: block; text-align: justify; text-decoration: none; }
           .lts-chidaodieuhanh ul li a:hover {color: red;}
           .viewall { font: 400 13px Roboto,Arial,Tahoma; font-style: normal; color: #be1e2d; font-style: italic; text-align: right; display: block; }
           @media screen and (max-width: 974px) {
            	.chidao-dieuhanh{
            		display: none;
            	}
			}
           
        </style>