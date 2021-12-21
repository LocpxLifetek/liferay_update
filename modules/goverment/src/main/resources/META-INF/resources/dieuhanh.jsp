<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="chidao-dieuhanh">
            <div class="head-chidaodieuhanh">
                Chỉ đạo điều hành
                <div class="linered"></div>
            </div>
            <div class="lts-chidaodieuhanh">
                <c:forEach items="${listBlogsEntryDtos}" var="blog">
				      <ul>
						<li>
                        	<a href="/diem-tin-interpol/tin-tuc/tin-tuc-su-kien/chi-dao-dieu-hanh/bo-truong-to-lam-gui-thu-khen-toan-the-can-bo-chien-si-cong-an-xa-thi-tran-d24-t30338.html">${blog.titleBlogsEntry}</a>
                    	</li>
				      </ul>
				 </c:forEach>
                <a href="/tin-tuc-su-kien/chi-dao-dieu-hanh-24.html" title="Xem thêm" class="viewall">Xem thêm &gt;&gt;</a>
                
            </div>
        </div>