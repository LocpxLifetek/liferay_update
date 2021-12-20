<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/init.jsp"%>
<%
	Calendar cal = Calendar.getInstance();
	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	String thu = "";
	switch (dayOfWeek) {
	case 1:
		thu = "chủ nhật";
		break;
	case 2:
		thu = "hai";
		break;
	case 3:
		thu = "ba";
		break;
	case 4:
		thu = "tư";
		break;
	case 5:
		thu = "năm";
		break;
	case 6:
		thu = "sáu";
		break;
	case 7:
		thu = "bảy";
		break;
	default:
		break;
	}
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	String currentDate = sdf.format(date);
%>

<div class="currentDate" style="width: 100%">

	<c:if test="<%=dayOfWeek == 1%>">
		<p><%=thu%>,
			<%=currentDate%></p>
	</c:if>
	<c:if test="<%=dayOfWeek > 1%>">

		<p>
			Thứ
			<%=thu%>,
			<%=currentDate%></p>
	</c:if>

</div>
