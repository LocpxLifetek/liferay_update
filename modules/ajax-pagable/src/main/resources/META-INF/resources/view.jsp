<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<portlet:defineObjects />

<p style="color: green;">Ajax using Alloy UI in Liferay</p>


<portlet:resourceURL var="saveDataUrl" />

<div id="userContent">
	<table id="usersTable" border="1">
	</table>
</div>
<div id="wait"
	style="display: none; width: 69px; height: 89px; border: 1px solid black; position: absolute; top: 50%; left: 50%; padding: 2px;">
	<img src='http://www.w3schools.com/jquery/demo_wait.gif' width="64"
		height="64" /><br>Loading..
</div>

<!-- 
total_record: tổng số records
current_page: trang hiện tại
limit: số records hiển thị trên mỗi trang
start: record bắt đầu trong câu lệnh SQL
 -->
 <table id="myTable">
	<tr>
		<th>Zipcode</th>
		<th>City</th>

	</tr>
</table>

<aui:form>
	<aui:input type="text" name="current_page" id="current_page" />
	<aui:input type="text" name="limit" id="limit" />

	<aui:button type="button" name="saveButton" value="Ajax Test"
		onclick="save();" />
</aui:form>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<aui:script>
	var total_record = 1;
	var total_page = 1;
	var limit = 1;
	var start = 1;
function save(){
    AUI().use('aui-base','aui-io-request', function(A){
       
        var current_page=A.one("#<portlet:namespace />current_page").get('value');
        var limit=A.one("#<portlet:namespace />limit").get('value');
        let current_page_te = "2";

        A.io.request('<%=saveDataUrl%>',{
            dataType: 'json',
            method: 'POST',
            data: { <portlet:namespace />start: start,
                     <portlet:namespace />limit: limit},
            on: {
            success: function() {
                var data=this.get('responseData');
				for (var i = 0; i < data.length; i++) {
					if(i==0){
						var total_record = data[i].total_record;
						var total_page = data[i].total_page;
						var limit = data[i].limit;
						var start = data[i].start;
						alert(total_record+' '+total_page);
					}else{
								
						var row = $('<tr><td>' + data[i].name + '</td><td>'
							+ data[i].id + '</td></tr>');

						$('#myTable').append(row);}
					}
             
        		}
            }
        });
   
    }); 
}
 


</aui:script>

