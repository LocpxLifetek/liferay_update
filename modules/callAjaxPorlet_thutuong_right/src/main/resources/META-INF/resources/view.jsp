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
<div width="100%" height="100%">
  <div class="bn_lanhdao">
	<h6 front-size="4px">Các bài phát biểu của Thủ tướng</h6>
  </div>
 <div class="lanhdao_content">
		
	</div>
</div>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<aui:script>
	AUI().use('aui-base','aui-io-request',function(A){
		
		//aui ajax call
		A.io.request('${resourceURL}',{
			dataType : 'json',
			method : 'POST',
			on : {
				success : function(){
					var html = "";
					var data=this.get('responseData');
					A.Array.each(data,function(obj,index){
						html+='<div style="width:100%" style="display:flex"><div style="width: 100%" align="justify"><a padding="3px" class="pb">'+ obj.titleBlogsEntry +'</a></div></div>'
						$(".lanhdao_content").html(html);
					});
				}
			}
		})
	});
	
</aui:script>

