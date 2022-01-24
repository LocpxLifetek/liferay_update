<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="init.jsp"%>

<portlet:resourceURL var="resourceURL" />
<portlet:resourceURL var="getBlogs">
	<portlet:param name="<%=Constants.CMD%>" value="get_blogs" />
</portlet:resourceURL>

<div class="block border has-icon">
        <table class="s4-wpTopTable" cellpadding="0" cellspacing="0" width="100%">
            <tr>
                <td valign="top">
                    <div WebPartID="00000000-0000-0000-0000-000000000000" HasPers="true"
                        id="WebPartWPQ1" width="100%" class="noindex" OnlyForMePart="true"
                        allowDelete="false" style="">
                        <div>

                            <div class="tandan-div-search">
                                <div class="div-input"><aui:input id="myInputNode" name="myInputNode" label="" helpMessage="Type blogs in Input Box" placeholder="Từ khóa tìm kiếm"/></div>
                 
                            </div>
           

                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>
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
			$(".div-input input").on('keyup',function(e){
				if(inputValue){
					
					if (e.key === 'Enter' || e.keyCode === 13) {
						window.location.href = "${url}"+"/search?keyword="+inputValue;
				    }
				}
			});
			$("#_ctl00_ctl43_ctl00_cmd").on('click',function(){
				window.location.href = "${url}"+"/"+inputValue;
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

		   			 				}
	   			 				}
							});
			myAjaxRequest.start();
			return testData;},
	});
});

</script>
