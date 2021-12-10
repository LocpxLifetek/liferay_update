<%@ include file="/init.jsp"%>
<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="tab">
	<button class="tablinks active"
		onclick="openTab(event, 'foreignAffair')">Đối ngoại</button>
	<button class="tablinks" onclick="openTab(event, 'event')">Sự
		kiện</button>
	<button class="tablinks" onclick="openTab(event, 'directOperation')">Chỉ
		đạo điều hành</button>
	<button class="tablinks" onclick="openTab(event, 'answer')">Trả
		lời công dân doanh nghiệp</button>
</div>

<div id="foreignAffair" class="tab-content">
	<div class="media small-media">
		<div>
			<a
				href="${url}/tintuc?id=${foreignAffair.uuidBlogs}"
				class="media-left" title="${foreignAffair.titleBlogs}"> <img
				src="${foreignAffair.src}" class="media-object">
			</a>
		</div>
		<div class="media-body">
			<a
				href="${url}/tintuc?id=${foreignAffair.uuidBlogs}"
				class="media-title" title="${foreignAffair.titleBlogs}">${foreignAffair.titleBlogs}</a>
			<div class="datechuyenmuc">
				(
				<fmt:formatDate value="${foreignAffair.modifiedDate}"
					pattern="MM/dd/yyyy" />
				)
			</div>
		</div>
	</div>
	<div class="row">
		<ul class="bullet-rectangle">
			<c:forEach items="${listCategoryForeignAffair}"
				var="categoryForeignAffair">

				<li><a
					href="${url}/tintuc?id=${categoryForeignAffair.uuidBlogs}"
					title="${categoryForeignAffair.titleBlogs}">${categoryForeignAffair.titleBlogs}</a></li>
			</c:forEach>

		</ul>
	</div>
</div>

<div id="event" class="tab-content" style="display: none;">
	<div class="media small-media">

		<a
			href="${url}/tintuc?id=${event.uuidBlogs}"
			class="media-left" title="${event.titleBlogs}"> <img
			src="${event.src}" class="media-object">
		</a>
		<div class="media-body">
			<a
				href="${url}/tintuc?id=${event.uuidBlogs}"
				class="media-title" title="${event.titleBlogs}">${event.titleBlogs}</a>


			<div class="datechuyenmuc">
				(
				<fmt:formatDate value="${event.modifiedDate}" pattern="MM/dd/yyyy" />
				)
			</div>
		</div>

	</div>
	<div class="row">
		<ul class="bullet-rectangle">
			<c:forEach items="${listCategoryEvent}" var="categoryEvent">

				<li><a
					href="${url}/tintuc?id=${categoryEvent.uuidBlogs}"
					title="${categoryEvent.titleBlogs}">${categoryEvent.titleBlogs}</a></li>
			</c:forEach>
		</ul>
	</div>
</div>
<div id="directOperation" class="tab-content" style="display: none;">
	<div class="media small-media">

		<a
			href="${url}/tintuc?id=${directOperation.uuidBlogs}"
			class="media-left" title="${directOperation.titleBlogs}"> <img
			src="${directOperation.src}"
			class="media-object">
		</a>
		<div class="media-body">
			<a
				href="${url}/tintuc?id=${directOperation.uuidBlogs}"
				class="media-title" title="${directOperation.titleBlogs}">${directOperation.titleBlogs}</a>

			<div class="datechuyenmuc">(
				<fmt:formatDate value="${directOperation.modifiedDate}" pattern="MM/dd/yyyy" />
				)</div>
		</div>

	</div>
	<div class="row">
		<ul class="bullet-rectangle">
			<c:forEach items="${listCategoryDirectOperation}" var="categoryDirectOperation">
				<li><a
					href="${url}/tintuc?id=${categoryDirectOperation.uuidBlogs}"
					title="${categoryDirectOperation.titleBlogs}">${categoryDirectOperation.titleBlogs}</a></li>
			</c:forEach>
		</ul>
	</div>
</div>
<div id="answer" class="tab-content" style="display: none;">
	<div class="media small-media">
		<a
			href="${url}/tintuc?id=${answer.uuidBlogs}"
			class="media-left" title="${answer.titleBlogs}"> <img
			src="${answer.src}"
			class="media-object">
		</a>
		<div class="media-body">
			<a
				href="${url}/tintuc?id=${answer.uuidBlogs}"
				class="media-title" title="${answer.titleBlogs}">${answer.titleBlogs}</a>

			<div class="datechuyenmuc">(
				<fmt:formatDate value="${answer.modifiedDate}" pattern="MM/dd/yyyy" />
				)</div>
		</div>

	</div>
	<div class="row">
		<ul class="bullet-rectangle">
			<c:forEach items="${listCategoryAnswer}" var="categoryAnswer">

				<li><a
					href="${url}/tintuc?id=${categoryAnswer.uuidBlogs}"
					title="${categoryAnswer.titleBlogs}">${categoryAnswer.titleBlogs}</a></li>
			</c:forEach>
		</ul>
	</div>
</div>

<script>
function openTab(event,information){
    var i, tabcontent,tablinks;
    tabcontent=document.getElementsByClassName('tab-content');
    
    for(i=0;i<tabcontent.length;i++){
        tabcontent[i].style.display="none";
    }
    tablinks=document.getElementsByClassName('tablinks');
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(information).style.display = "block";
    event.currentTarget.className+=' active';
}
</script>