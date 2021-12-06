<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="porlet"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<portlet:resourceURL var="resourceURL" />

<div class="featured-news">

	<div class="feature" style="width: 100%">
		
	</div>
	<div class="listFeature" style="margin-left: 5px;">
	
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
					var html= "";
					var listHtml="";
					var data= this.get('responseData');
					
					A.Array.each(data, function(obj,index){
						if(index == 0){
							html+='<div class="gird2"><h2><a href="/web/liftek/tintuc?id='+obj.uuidBlogsEntry+'">'+obj.titleBlogsEntry+'</a></h2><span style="color: #d71920; font-size: 12px;">'+ obj.modifiedDate +'</span></div><div class="grid1"><div><img src="'+obj.srcImage+'" class="soup-image"><p>'+ obj.description +'</p></div></div>';
							$(".feature").html(html);	
						}else{
							listHtml+='<div class="title1" style="padding: 25px 0px 0px 10px;"><img src="http://chinhphu.vn/templates/govportal/tinhthanh/images/icon_1.jpg" style="padding: 0px 5px 0px 0px;"><a href="/web/lifetek/tintuc?id='+obj.uuidBlogsEntry+'">'+obj.titleBlogsEntry+'</a></div>';
							$(".listFeature").html(listHtml);
						}
					});
				}
				
			}
		})
	});
</aui:script>