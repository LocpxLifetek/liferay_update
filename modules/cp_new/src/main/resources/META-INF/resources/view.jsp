<%@page
	import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil"%>
<%@page import="com.liferay.document.library.kernel.model.DLFileEntry"%>
<%@page
	import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"%>
<%@page import="com.liferay.asset.kernel.model.AssetCategory"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
	
%>

<div class="wrapper">
	<div class="item1">
		<a href="http://media.chinhphu.vn/"> <strong>
				${assetCategory1.name} </strong>
		</a>
	</div>
	<div class="item2">
		<a
			href="http://media.chinhphu.vn/video/chuyen-muc-ban-tin-chinh-phu-tuan-qua-23">
			<strong> ${assetCategory2.name} </strong>
		</a>
	</div>
	<div class="item"></div>
	<div class="item3">
		<img src="${imgSrcTtdptTop1}"
			style="display: inline-block; float: left; margin-right: 1.2rem; margin-left: 20px"
			width="123px" height="88px"> <strong> <a
			href="${hrefttdpt1}"> ${ttdpt1.title} </a></strong>
	</div>
	<div class="item4">
		<img src="${imgSrcBtcptqTop1}"
			style="display: inline-block; float: left; margin-right: 1.2rem; margin-left: 20px"
			width="123px" height="88px"> <strong> <a
			href="${hrefbtcptq1}"> ${btcptq1.title} </a></strong>
	</div>
	<div class="item"></div>
	<div class="item5">
		<p>
			<img src="${srcImgdaudong}" width="4px" height="5px"> <strong>
				<a href="${hrefttdpt2}"> ${ttdpt2.title} </a>
			</strong>
		</p>
		<p>
			<img src="${srcImgdaudong}" width="4px" height="5px"> <strong>
				<a href="${hrefttdpt3}"> ${ttdpt3.title} </a>
			</strong>
		</p>
		<p>
			<img src="${srcImgdaudong}" width="4px" height="5px"> <strong>
				<a href="${hrefttdpt4}"> ${ttdpt4.title} </a>
			</strong>
		</p>
		<p>
			<img src="${srcImgdaudong}" width="4px" height="5px"> <strong>
				<a href="${hrefttdpt5}"> ${ttdpt5.title} </a>
			</strong>
		</p>
	</div>

	<div class="item6">
		<p>
			<img src="${srcImgdaudong}" width="4px" height="5px"> <strong>
				<a href="${hrefbtcptq2}"> ${btcptq2.title} </a>
			</strong>
		</p>
		<p>
			<img src="${srcImgdaudong}" width="4px" height="5px"> <strong>
				<a href="${hrefbtcptq3}"> ${btcptq3.title} </a>
			</strong>
		</p>
		<p>
			<img src="${srcImgdaudong}" width="4px" height="5px"> <strong>
				<a href="${hrefbtcptq4}"> ${btcptq4.title} </a>
			</strong>
		</p>
		<p>
			<img src="${srcImgdaudong}" width="4px" height="5px"> <strong>
				<a href="${hrefbtcptq5}"> ${btcptq5.title} </a>
			</strong>
		</p>
	</div>
</div>

