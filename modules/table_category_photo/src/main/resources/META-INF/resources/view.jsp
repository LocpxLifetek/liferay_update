<%@ include file="/init.jsp" %>

<div class="chidao-dieuhanh">
            <div class="head-chidaodieuhanh">
                ${categoryName.categoryName}
                <div class="linered"></div>
            </div>
            <div class="lts-chidaodieuhanh">
				      <ul>
				      <c:forEach items="${listDlefile}" var="listDlefile">
						<li>
                        	<a href="${url}/album_anh?id=${listDlefile.uuidCategory}"
						       title="${listDlefile.title}"
						       class="">${listDlefile.title}</a>
                    	</li>
                    	</c:forEach>
				      </ul>
                
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
        </style>
