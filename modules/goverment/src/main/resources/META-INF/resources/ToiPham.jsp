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