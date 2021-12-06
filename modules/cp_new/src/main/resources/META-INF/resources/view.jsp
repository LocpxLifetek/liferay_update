<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="cp_new_wrapper">
	<div class="cp_new_item1">
		<a href="http://media.chinhphu.vn/"> <strong>
				${assetCategory1.getName()} </strong>
		</a>
	</div>
	<div class="cp_new_item2">
		<a
			href="http://media.chinhphu.vn/video/chuyen-muc-ban-tin-chinh-phu-tuan-qua-23">
			<strong> ${assetCategory2.getName()} </strong>
		</a>
	</div>
	
	
	
	<div class="cp_new_item"></div>
	
	<div class="cp_new_item3">
		<img src="${imgTop1}"
			style="display: inline-block; float: left; margin-right: 1.2rem; margin-left: 20px"
			width="123px" height="88px"> <strong> <a
			href="${LINK_CP_NEW}${top1_1.getFileEntryId()}">
				${top1_1.getTitle().replace("mp4", "")}</a></strong>
	</div>
	<div class="cp_new_item4">
		<img src="${imgTop2}"
			style="display: inline-block; float: left; margin-right: 1.2rem; margin-left: 20px"
			width="123px" height="88px"> <strong> <a
			href="${LINK_CP_NEW}${top1_2.getFileEntryId()}">
				${top1_2.getTitle().replace("mp4", "")}</a></strong>
	</div>


	<div class="cp_new_item"></div>
	
	
	<div class="cp_new_item5">
		<c:forEach items="${listOutput1}" var="new1">
			<p>
				<img src="${srcImgdaudong}" width="4px" height="5px"> <strong>
					<a href="${LINK_CP_NEW}${new1.getFileEntryId()}">
						${new1.getTitle().replace("mp4", "")} </a>
				</strong>
			</p>
		</c:forEach>
	</div>

	<div class="cp_new_item6">
		<c:forEach items="${listOutput2}" var="new2">
			<p>
				<img src="${srcImgdaudong}" width="4px" height="5px"> <strong>
					<a href="${LINK_CP_NEW}${new2.getFileEntryId()}">
						${new2.getTitle().replace("mp4", "")}</a>
				</strong>
			</p>
		</c:forEach>
	</div>
</div>
