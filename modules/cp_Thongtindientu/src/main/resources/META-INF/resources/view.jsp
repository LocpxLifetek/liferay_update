
<%@ include file="/init.jsp"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div>
	<img
		src="http://chinhphu.vn/templates/govportal/chinhphu/images/header_cp.jpg"
		alt="" width="100%">
	<table class="cp_Thongtindientu">
		<tr>
			<td><a href="">Trang chủ</a></td>
			<td><a href="">Giới thiệu Cổng TTĐT Chính phủ</a></td>
			<td><a href="">Báo điện tử Chính phủ</a></td>
			<td><a href="">Thư điện tử công vụ Chính phủ</a></td>
			<td><a href="">Chính phủ với Người ngoài nước</a></td>
			<td><a href="">Diễn đàn</a></td>
		</tr>
	</table>
</div>
<div>
	<marquee behavior="scroll" direction="left" scrollamount="3"
		width="100%" onmouseover="this.stop()" onmouseout="this.start()"
		style="background-color: yellow; color: red;">
		<span> <strong> 
			<a href="" style="text-decoration: none;"> Thủ tướng Phạm Minh Chính tham
					dự Hội nghị COP-26, thăm Anh, Pháp </a> 
			<a href="" style="text-decoration: none;"> Thích ứng an toàn, linh hoạt,
					kiểm soát hiệu quả dịch COVID-19 </a> 
			<a href="" style="text-decoration: none;"> Hỗ trợ người lao động, người sử
					dụng lao động theo Nghị quyết 68</a> 
			<a href="" style="text-decoration: none;"> Thông tin cập nhật dịch nCoV</a>
		</strong>
		</span>
	</marquee>
	<div class="cp_ngaygio">${homnay}</div>

</div>