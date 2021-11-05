<%@page
	import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"%>
<%@page import="com.liferay.asset.kernel.model.AssetCategory"%>
<%@page contentType="text/html; charset=UTF-8"%>


<%
	
%>

<table class="cp_new">
	<tr>
		<th><a style="color: black"
			href="http://chinhphu.vn/portal/page/portal/chinhphu/congdan/traloicongdan">${category1.name}</a></th>
		<th><a style="color: black"
			href="http://chinhphu.vn/portal/page/portal/chinhphu/chidaodieuhanh">${category2.name}</a></th>
		<th><a style="color: black"
			href="http://chinhphu.vn/portal/page/portal/chinhphu/sukien">${category3.name}</a></th>
		<th><a style="color: black"
			href="http://chinhphu.vn/portal/page/portal/chinhphu/doingoai">${category4.name}</a></th>
	</tr>
	<tr>
		<td>
			<table class="cp_newCon">
				<tr>
					<td><img src="${imgSrcTlcddnTop1}" width="51px" height="51px"></td>
					<td><a href="">${tlcddn1.title}</a></td>
				</tr>
			</table>
		</td>
		<td>
			<table class="cp_newCon">
				<tr>
					<td><img src="${imgSrcCddhTop1}" width="51px" height="51px"></td>
					<td><a href="">${cddh1.title}</a></td>
				</tr>
			</table>
		</td>
		<td>
			<table class="cp_newCon">
				<tr>
					<td><img src="${imgSrcSukienTop1}" width="51px" height="51px"></td>
					<td><a href="">${sukien1.title}</a></td>
				</tr>
			</table>
		</td>
		<td>
			<table class="cp_newCon">
				<tr>
					<td><img src="${imgSrcDoingoaiTop1}" width="51px"
						height="51px"></td>
					<td><a href="">${doingoai1.title}</a></td>
				</tr>
			</table>
		</td>

	</tr>
	<tr style="text-align: left;">
		<td><a href="">${tlcddn2.title}</a></td>
		<td><a href="">${cddh2.title}</a></td>
		<td><a href="">${sukien2.title}</a></td>
		<td><a href="">${doingoai2.title}</a></td>
	</tr>
</table>