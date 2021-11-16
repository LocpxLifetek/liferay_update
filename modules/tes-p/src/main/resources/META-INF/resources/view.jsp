<%@ include file="/init.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<div class="container-chinhphu">
	
	<div class="item-left" style="padding: 8px 8px 8px 8px">
		<div
			style="width: 100%; height: 38; display: flex; background: #fce4b7">
			<div style="width: 100%" align="justify">
				<img width="28" height="28"
					src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/Emblem_of_Vietnam.svg/1024px-Emblem_of_Vietnam.svg.png">
				<a class="title_box">Chính phủ với các Bộ, Cơ quan Chính phủ</a>
			</div>
		</div>
		<div class="news-left">

			<div class="hotNews">
				<div>
					<img hspace="10" height="80" align="left" width="115" vspace="10"
						src="http://172.16.100.75:8080/documents/${blogEntryLeft.groupId}/${blogEntryLeft.folderId}/${blogEntryLeft.titleDl}/${blogEntryLeft.uuidDl}"
						style="border: 4px double rgb(212, 211, 211);">
				</div>
				<div>
					<a class="tinmoi">${blogEntryLeft.title}</a>
				</div>
			</div>

		</div>
		<table class="table-ta" width="100%">
			<br></br>
			<br></br>
			<c:forEach items="${blogEntriseLeft}" var="u">
				<div style="width: 100%" style="display:flex">
					<div style="width: 100%" align="justify">
						<img
							src="http://chinhphu.vn/templates/govportal/chinhphu/images/icon3.jpg">
						<a class="tinmoi-ta" href="/web/lifetek/tintuc/?id=${u.uuidBs}"
							target="_blank"> ${u.title}</a>
					</div>
					<!-- <td>${u.title}</td> -->
				</div>
			</c:forEach>

		</table>
	</div>
	<div class="item-right" style="padding: 8px 8px 8px 8px">
		<div
			style="width: 100%; height: 38; display: flex; background: #fce4b7">
			<div style="width: 100%" align="justify">
				<img width="28" height="28"
					src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/Emblem_of_Vietnam.svg/1024px-Emblem_of_Vietnam.svg.png">
				<a class="title_box">Chính phủ với các Tỉnh, Thành phố</a>
			</div>
		</div>
		<div class="news-right">


			<div class="hotNews">
				<div>
					<img hspace="10" height="80" align="left" width="115" vspace="10"
						src="http://172.16.100.75:8080/documents/${blogEntryRight.groupId}/${blogEntryRight.folderId}/${blogEntryRight.titleDl}/${blogEntryRight.uuidDl}"
						style="border: 4px double rgb(212, 211, 211);">
				</div>
				<div>
					<a class="tinmoi">${blogEntryRight.title}</a>
				</div>
			</div>


		</div>

		<table class="table-ta" width="100%">
			<br></br>
			<br></br>
			<c:forEach items="${blogEntriseRight}" var="u">
				<div style="width: 100%" style="display:flex">
					<div style="width: 100%" align="justify">
						<img
							src="http://chinhphu.vn/templates/govportal/chinhphu/images/icon3.jpg">
						<a class="tinmoi-ta" href="/web/lifetek/tintuc/?id=${u.uuidBs}"
							target="_blank"> ${u.title}</a>
					</div>
				</div>
			</c:forEach>

		</table>
	</div>
</div>





