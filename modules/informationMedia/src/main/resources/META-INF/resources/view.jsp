<%@ include file="/init.jsp"%>

<div class="chidao-dieuhanh">
	<div class="head-chidaodieuhanh">
		Tin media
		<div class="linered"></div>
	</div>
	<div class="lts-cmanh">
		<ul>
			<c:forEach items="${listdlFolder}" var="dlFolder">

				<li><a href="#" id="thu-vien-anh" title="${dlFolder.name}">${dlFolder.name}</a></li>
			</c:forEach>

		</ul>
	</div>
</div>