<header-portlet-javascript>
   http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js
</header-portlet-javascript>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@page import="com.liferay.blogs.model.BlogsEntry"%>
<%@page import="com.liferay.blogs.service.BlogsEntryLocalServiceUtil"%>
<%@page import="ajax.get.portlet.AjaxGetPortlet"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>

<portlet:defineObjects />
<portlet:resourceURL var="resourceURL">
</portlet:resourceURL>
<style>
table {
	width: 50%;
}

th {
	background: #f1f1f1;
	font-weight: bold;
	padding: 6px;
}

td {
	background: #f9f9f9;
	padding: 6px;
}
</style>
    <p onclick="callServeResource1()">This is resourceURL
	callServeResource1</p>


<table id="myTable">
	<tr>
		<th>Zipcode</th>
		<th>City</th>

	</tr>
</table>

<form id="testAjaxForm" action="">
    <input type="text" name="<portlet:namespace />param2">
    <input type="button" value="Submit" onclick="callServeResource1()">

</form>
  
<script type="text/javascript">
	function callServeResource1() {
     
		$.ajax({
			url : '${resourceURL}',
			data : {
				
				countryName:"ak"

			},
            form:{
                id:'testAjaxForm'
            },//person id to sent
			type : 'POST',
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				alert(data);
				for (var i = 0; i < data.length; i++) {
					var row = $('<tr><td>' + data[i].name + '</td><td>'
							+ data[i].id + '</td></tr>');

					$('#myTable').append(row);
				}
			}
		});
	}

	function ajaxCall(){
	    AUI().use('aui-io-request', function(A){
	        A.io.request('${resourceURL}', {
	               method: 'post',
	               data: {
	                   <portlet:namespace />sampleParam: 'value2',
	               },
	               form:{
	                   id:'testAjaxForm'
	               },
	               on: {
	                       success: function() {
	                        alert(this.get('responseData'));
	                   }
	              }
	        });
	    });
	}
</script>
