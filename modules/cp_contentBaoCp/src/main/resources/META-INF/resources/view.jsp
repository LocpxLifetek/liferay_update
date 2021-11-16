<%@page import="com.liferay.blogs.service.BlogsEntryLocalServiceUtil"%>
<%@page import="com.liferay.blogs.model.BlogsEntry"%>
<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="cp_contentBaocp">
	<div class="baocp_title">
		<h2>${title}</h2>
	</div>
	<div class="baocp">
		<div class="baocp_item1">
			<div>
			<h3> TIN ĐỌC NHIỀU</h3>
			</div>
			<div>Định danh, xác thực điện tử trên nền tảng Cơ sở dữ liệu quốc gia về dân cư</div>
			<br>
			<div>Chính phủ đồng ý chủ trương về lộ trình thí điểm đón khách du lịch quốc tế</div>
			<br>
			<div>Thủ tướng chỉ đạo tiếp tục tăng cường thực hiện các biện pháp phòng, chống dịch COVID-19</div>
			<br>
			<div>Thúc đẩy tiến độ cấp phép hồ sơ liên quan đến vaccine, thuốc, trang thiết bị, vật tư y tế</div>
		</div>
		<div class="baocp_item2">${content}</div>
	</div>
</div>