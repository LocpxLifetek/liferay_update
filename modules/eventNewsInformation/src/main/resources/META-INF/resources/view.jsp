<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="menu_1">
	<div class="head-cm">
		<ul>
			<li><a
				href="/web/lifetek/theloaitintucsukien?uuid=${category4.uuid}">${category4.name}</a></li>
		</ul>
		<a href="/tin-tuc-su-kien/thong-tin-doi-ngoai-20.html" class="xemthem">Xem
			thêm</a>
	</div>
	<div class="policeActivitie">
		<div class="policeActivitieLeft">
			<img
				src="/documents/${smallImagePoliceActivities.groupId}/${smallImagePoliceActivities.folderId}/${smallImagePoliceActivities.title}/${smallImagePoliceActivities.uuid}">
			<a href="/web/lifetek/chitiettintuc?id=${policeActivities.uuid}"
				style="font-size: 16px">${policeActivities.title}</a>

			<p>${policeActivities.description}</p>
		</div>
		<div class="directOperationRight">
			<c:forEach items="${listBlogsEntriesPoliceActivities}" var="blog">
				<c:choose>
					<c:when test="${blog.smallImageFileEntryId > 0}">
						<c:forEach items="${listDlFileEntryPoliceActivities}" var="si">
							<c:if test="${blog.smallImageFileEntryId == si.fileEntryId}">
								<div class="directOperationGrid">
									<img
										src="/documents/${si.groupId}/${si.folderId}/${si.title}/${si.uuid}"
										class="soup-image" style="width: 30% !important"> <a
										href="/web/lifetek/chitiettintuc?id=${blog.uuid}">${blog.title}</a>
								</div>
							</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div>
							<a href="/web/lifetek/chitiettintuc?id=${blog.uuid}">${blog.title}</a>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
	</div>
</div>

<div class="menu_1">
	<div class="head-cm">
		<ul>
			<li><a
				href="/web/lifetek/theloaitintucsukien?uuid=${category.uuid}">${category.name}</a></li>
		</ul>
		<a href="/tin-tuc-su-kien/thong-tin-doi-ngoai-20.html" class="xemthem">Xem
			thêm</a>
	</div>
	<div class="directOperator">
		<div class="directOperationLeft">
			<a href="/web/lifetek/chitiettintuc?id=${directOperator.uuid}"
				style="font-size: 16px">${directOperator.title}</a> <img
				src="/documents/${smallImageDirectOperator.groupId}/${smallImageDirectOperator.folderId}/${smallImageDirectOperator.title}/${smallImageDirectOperator.uuid}"
				class="soup-image">

			<p>${directOperator.description}</p>
		</div>
		<div class="directOperationRight">
			<c:forEach items="${listBlogsEntriesDirectOperator}" var="blog">
				<c:choose>
					<c:when test="${blog.smallImageFileEntryId > 0}">
						<c:forEach items="${listDlFileEntryDirectOperator}" var="si">
							<c:if test="${blog.smallImageFileEntryId == si.fileEntryId}">
								<div class="directOperationGrid">
									<img
										src="/documents/${si.groupId}/${si.folderId}/${si.title}/${si.uuid}"
										class="soup-image" style="width: 30% !important"> <a
										href="/web/lifetek/chitiettintuc?id=${blog.uuid}">${blog.title}</a>
								</div>
							</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div>
							<a href="/web/lifetek/chitiettintuc?id=${blog.uuid}">${blog.title}</a>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
	</div>
</div>

<div class="menu_1">
	<div class="head-cm">
		<ul>
			<li><a
				href="/web/lifetek/theloaitintucsukien?uuid=${category1.uuid}">${category1.name}</a></li>
		</ul>
		<a href="/tin-tuc-su-kien/thong-tin-doi-ngoai-20.html" class="xemthem">Xem
			thêm</a>
	</div>
	<div class="directOperator">
		<div class="directOperationLeft">

			<a href="/web/lifetek/chitiettintuc?id=${foreignInformation.uuid}"
				style="font-size: 16px">${foreignInformation.title}</a> <img
				src="/documents/${smallImageForeignInformation.groupId}/${smallImageForeignInformation.folderId}/${smallImageForeignInformation.title}/${smallImageForeignInformation.uuid}"
				class="soup-image">

			<p>${foreignInformation.description}</p>
		</div>
		<div>
			<c:forEach items="${listBlogsEntriesForeignInformation}" var="blog">
				<div class="title">
					<a href="/web/lifetek/chitiettintuc?id=${blog.uuid}"> <img
						src="http://chinhphu.vn/templates/govportal/tinhthanh/images/icon_1.jpg">
						${blog.title}
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

<div class="menu_1">
	<div class="head-cm">
		<ul>
			<li><a
				href="/web/lifetek/theloaitintucsukien?uuid=${category2.uuid}">${category2.name}</a></li>
		</ul>
		<a href="/tin-tuc-su-kien/thong-tin-doi-ngoai-20.html" class="xemthem">Xem
			thêm</a>
	</div>
	<div class="directOperator">
		<div class="directOperationLeft">

			<a href="/web/lifetek/chitiettintuc?id=${securityNews.uuid}"
				style="font-size: 16px">${securityNews.title}</a> <img
				src="/documents/${smallImageSecurityNews.groupId}/${smallImageSecurityNews.folderId}/${smallImageSecurityNews.title}/${smallImageSecurityNews.uuid}"
				class="soup-image">

			<p>${securityNews.description}</p>
		</div>
		<div>
			<c:forEach items="${listBlogsEntriesSecurityNews}" var="blog">
				<div class="title">
					<a href="/web/lifetek/chitiettintuc?id=${blog.uuid}"> <img
						src="http://chinhphu.vn/templates/govportal/tinhthanh/images/icon_1.jpg">
						${blog.title}
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

<div class="menu_1">
	<div class="head-cm">
		<ul>
			<li><a
				href="/web/lifetek/theloaitintucsukien?uuid=${category3.uuid}">${category3.name}</a></li>
		</ul>
		<a href="/tin-tuc-su-kien/thong-tin-doi-ngoai-20.html" class="xemthem">Xem
			thêm</a>
	</div>
	<div class="directOperator">
		<div class="directOperationLeft">

			<a href="/web/lifetek/chitiettintuc?id=${goodPeople.uuid}"
				style="font-size: 16px">${goodPeople.title}</a> <img
				src="/documents/${smallImageGoodPeople.groupId}/${smallImageGoodPeople.folderId}/${smallImageGoodPeople.title}/${smallImageGoodPeople.uuid}"
				class="soup-image">

			<p>${goodPeople.description}</p>
		</div>
		<div>
			<c:forEach items="${listBlogsEntriesGoodPeople}" var="blog">
				<div class="title">
					<a href="/web/lifetek/chitiettintuc?id=${blog.uuid}"> <img
						src="http://chinhphu.vn/templates/govportal/tinhthanh/images/icon_1.jpg">
						${blog.title}
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

<div class="menu_1">
	<div class="head-cm">
		<ul>
			<li><a
				href="/web/lifetek/theloaitintucsukien?uuid=${category5.uuid}">${category5.name}</a></li>
		</ul>
		<a href="/tin-tuc-su-kien/thong-tin-doi-ngoai-20.html" class="xemthem">Xem
			thêm</a>
	</div>
	<div class="directOperator">
		<div class="directOperationLeft">
			<a href="/web/lifetek/chitiettintuc?id=${policeActivities.uuid}"
				style="font-size: 16px">${policeActivities.title}</a> <img
				src="/documents/${smallImagePoliceActivities.groupId}/${smallImagePoliceActivities.folderId}/${smallImagePoliceActivities.title}/${smallImagePoliceActivities.uuid}"
				class="soup-image">

			<p>${policeActivities.description}</p>
		</div>
		<div >
			<c:forEach items="${listBlogsEntriesSocialActivities}" var="blog">
				<div class="title">
					<a href="/web/lifetek/chitiettintuc?id=${blog.uuid}"> <img
						src="http://chinhphu.vn/templates/govportal/tinhthanh/images/icon_1.jpg">
						${blog.title}
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
</div>