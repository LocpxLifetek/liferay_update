<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="init.jsp"%>
<portlet:resourceURL var="resourceURL" />
<portlet:resourceURL var="getBlogs">
	<portlet:param name="<%=Constants.CMD%>" value="get_blogs" />
</portlet:resourceURL>


<aui:input id="myInputNode" name="myInputNode" label="Search" helpMessage="Type blogs in Input Box" />
<div class="grid" id="text" style="display:block; transition: all 2s;"></div>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<script>
AUI().use('autocomplete-list','aui-base','aui-io-request','autocomplete-filters','autocomplete-highlighters',function (A) {
var testData="";
new A.AutoCompleteList({
allowBrowserAutocomplete: 'false',
activateFirstItem: 'false',
inputNode: '#<portlet:namespace />myInputNode',
resultTextLocator:'main',
render: 'false',
resultHighlighter: 'phraseMatch',
resultFilters:['phraseMatch'],
source:function(){			
			var inputValue=A.one("#<portlet:namespace />myInputNode").get('value');	
			$("#_Searchs_GovermentPortlet_INSTANCE_daht_myInputNode").on('keyup',function(e){
				if(inputValue){
					
					if (e.key === 'Enter' || e.keyCode === 13) {
						window.location.href = "${url}"+"/"+inputValue;
				    }
				}
			});
			var myAjaxRequest=A.io.request('<%=getBlogs.toString()%>',{
	  						    dataType: 'json',
	  							method:'POST',
	  							data:{
		  							<portlet:namespace />titleBlogs:inputValue
	  							},
	  							autoLoad:false,
	  							sync:false,
	  						    on: {
		   			 				 success:function(){
		   			 				 	var html="";
		   			 					var data=this.get('responseData');
		   			 					testData=data;
		   			 					if(testData.length >0){
			   			 					A.Array.each(data,function(obj,index){
												html+='<div class="hotNews"><img src="'+obj.src+'" class="soup-image1" /><a href="/web/lifetek/tintuc?uuid='+obj.uuid+'">'+obj.title+'</a></div>';
												$(".grid").html(html);
												
											});
			   			 				}else{
			   			 					$(".grid").html('<h3>Không tìm thấy bài viết phù hợp</h3>');
			   			 				}
			   			 				
		   			 				}
	   			 				}
							});
			myAjaxRequest.start();
			return testData;},
	});
});

</script>
