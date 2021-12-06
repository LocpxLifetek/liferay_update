<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page
	import="com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil"%>
<%@page import="com.liferay.asset.kernel.model.AssetEntry"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/init.jsp"%>


<portlet:resourceURL var="resourceURL" />



	<div class="grid">
	
	</div>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<aui:script>
	AUI().use('aui-base', 'aui-io-request', function(A) {
		
		//aui ajax call
		A.io.request('${resourceURL}', {
			dataType : 'json',
			method : 'POST',
			
			on : {
				success : function() {
					var html="";
					var data = this.get('responseData');
					
					A.Array.each(data,function(obj,index){
						html+='<div class="hotNews"><img src="'+obj.srcImage+'" class="soup-image1" /><a href="/web/lifetek/tintuc?id='+obj.uuidBlogsEntry+'">'+obj.titleBlogsEntry+'</a></div>';
						$(".grid").html(html);
					});
					
				}
			}
		})

	});
</aui:script>