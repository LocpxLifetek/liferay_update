<%@page import="com.liferay.blogs.model.BlogsEntry"%>
<%@page import="com.liferay.asset.kernel.model.AssetCategory"%>
<%@page import="com.liferay.asset.kernel.model.AssetEntry"%>
<%@page
	import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil"%>
<%@page import="com.liferay.document.library.kernel.model.DLFileEntry"%>
<%@ include file="/init.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
	
%>

<div class="cp_baochinhphuLeft">
	<div class="">
		<img src="${srcBanner}">
	</div>
	<div class="nameCg">
		<h4>${NameCategory}</h4>
	</div>
	<div class="blogs">
		<a href="">${titleBlog1}</a>
	</div>
	<div class="blogs">
		<a href="">${titleBlog2}</a>
	</div>
	<div class="blogs">
		<a href="">${titleBlog3}</a>
	</div>
	<div class="blogs">
		<a href="">${titleBlog4}</a>
	</div>
	<div class="blogs">
		<a href=""></a>
	</div>

	<div style="border: 1px solid rgb(221, 217, 217);">
		<div class="nameOther">
			<a href="" style="color: white">Chuyên đề</a>
		</div>
		<div class="chuyende">
			<ul>
				<li><a href="">Thủ tướng Phạm Minh Chính tham dự Hội nghị
						COP-26, thăm Anh, Pháp</a></li>
				<li><a href="">Thích ứng an toàn, linh hoạt, kiểm soát hiệu
						quả dịch COVID-19</a></li>
				<li><a href="">Hỗ trợ người lao động, người sử dụng lao
						động theo Nghị quyết 68</a></li>
				<li><a href="">Phục hồi nền kinh tế</a></li>
				<li><a href="">Giải ngân vốn đầu tư công</a></li>
			</ul>
		</div>
		<div style="height: 30px;"></div>

		<div>
			<h4>Đọc nhiều</h4>
		</div>
		<ul class="Docnhieu">

			<li><a href="">ATNĐ đi vào Khánh Hòa; cảnh báo mưa lớn,
					lũ...</a></li>
			<li><a href="">Khẩn trương tập huấn Nghị quyết 128 và Hướng
					dẫn...</a></li>
			<li><a href="">Bộ Y tế cần sớm ban hành Thông tư quy...</a></li>
			<li><a href="">Sớm mở lại du lịch quốc tế, bảo đảm an...</a></li>
			<li><a href="">Khẩn trương nghiên cứu, điều chỉnh quy định
					để Bộ...</a></li>
			<li><a href="">Bùng phát ổ dịch phức tạp, Nam Định cấp
					bách...</a></li>
			<li><a href="">TỔNG THUẬT: Thủ tướng Chính phủ gặp mặt, biểu
					dương...</a></li>
			<li><a href="">Nội dung Tọa đàm "Nghị quyết 128 - Hướng
					tới...</a></li>
			<li><a href="">Phát hiện hàng chục ca COVID-19 chưa rõ nguồn
					lây,...</a></li>
			<li><a href="">Thủ tướng yêu cầu lãnh đạo Bộ Y tế trực...</a></li>
		</ul>
		
		
		<div class="nameOther">
                    <a href="" style="color: white;">Tin từ Canhtranhquocgia.vn</a>
                </div>
                <div><img
                        src="https://nangluccanhtranh.chinhphu.vn/Uploaded/buithuhuong/2021_11_09/thong%20ke.jpg?maxwidth=300"
                        width="20%">
                    <strong>
                        <a href="">
                            Điều chỉnh Luật Thống kê để kịp thời phản ánh bối cảnh mới
                        </a>
                    </strong>
                    <ul>
                        <li>
                            <a href="">
                                Chính sách tài khóa được điều hành linh hoạt
                            </a>
                        </li>
                        <li>
                            <a href="">
                                Khơi thông các nguồn lực cho phát triển công nghiệp bền vững
                            </a>
                        </li>
                        <li>
                            <a href="">
                                Điện năng và bình ổn thị trường vật tư nông nghiệp được quan tâm
                            </a>
                        </li>
                    </ul>
                </div>


                <div class="nameOther">
                    <a href="" style="color: white;">Tin từ tiengchuong.vn</a>
                </div>
                <div><img src="https://tiengchuong.chinhphu.vn/Image.aspx?id=36305&ts=300&lm=637720572610700000"
                        width="20%">
                    <strong>
                        <a href="">
                            Hải quân Ecuador thu giữ khoảng một tấn cocaine
                        </a>
                    </strong>
                    <ul>
                        <li>
                            <a href="">
                                Những 'bóng hồng' trên mặt trận đấu tranh phòng, chống tội phạm
                            </a>
                        </li>
                        <li>
                            <a href="">
                                Triệt xóa nhiều đường dây ma túy tại biên giới
                            </a>
                        </li>
                        <li>
                            <a href="">
                                Ai Cập tiêu diệt băng nhóm tội phạm nguy hiểm
                            </a>
                        </li>
                    </ul>
                </div>




                <div class="nameOther">
                    <a href="" style="color: white;">Tin từ thanglong.chinhphu.vn</a>
                </div>
                <div><img src="https://thanglong.chinhphu.vn/Uploads/images/benh%20nhan%20covid.jpg" width="20%">
                    <strong>
                        <a href="">
                            Ngày 9/11, Hà Nội có 222 ca mắc COVID-19, cao nhất đợt dịch thứ 4 đến nay
                        </a>
                    </strong>
                    <ul>
                        <li>
                            <a href="">
                                Những tuyến đường sắt đô thị nào đang được Hà Nội triển khai?
                            </a>
                        </li>
                        <li>
                            <a href="">
                                Phát triển chuỗi: Bảo đảm an toàn nông lâm thủy sản
                            </a>
                        </li>
                        <li>
                            <a href="">
                                Diễn đàn thường niên ‘Văn hóa với doanh nghiệp’ diễn ra vào tháng 12/2021
                            </a>
                        </li>
                    </ul>
                </div>


                <div class="nameOther">
                    <a href="" style="color: white;">Tin từ hochiminhcity.vn</a>
                </div>
                <div><img
                        src="https://tphcm.chinhphu.vn/Uploads/images/2017/h%E1%BB%8Dp%20b%C3%A1o%20d%E1%BB%8Bch%20ng%C3%A0y%208%2011.jpg"
                        width="20%">
                    <strong>
                        <a href="">
                            Dịch bệnh COVID-19 tại TPHCM vẫn phức tạp
                        </a>
                    </strong>
                    <ul>
                        <li>
                            <a href="">
                                Cấp độ dịch COVID-19 của TPHCM ở mức 2
                            </a>
                        </li>
                        <li>
                            <a href="">
                                Bí Thư Thành ủy Nguyễn Văn Nên làm việc với huyện Hóc Môn
                            </a>
                        </li>
                        <li>
                            <a href="">
                                TPHCM tuyên dương 350 cháu ngoan Bác Hồ năm 2021
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="nameOther">
                    <a href="" style="color: white;">GIẢI ĐÁP CHÍNH SÁCH ONLINE</a>
                </div>
                    <ul>
                        <li>
                            <a href="">
                                Bảo hiểm xã hội - Bảo hiểm y tế - Bảo hiểm thất nghiệp
                            </a>
                        </li>
                        <li>
                            <a href="">
                                Những 'bóng hồng' trên mặt trận đấu tranh phòng, chống tội phạm
                            </a>
                        </li>
                        <li>
                            <a href="">
                                Chính sách với người có công
                            </a>
                        </li>
                        <li>
                            <a href="">
                                Giáo dục - Đào tạo - Y tế
                            </a>
                        </li>
                        <li>
                            <a href="">
                                Giao thông - Xây dựng - Tài nguyên - Môi trường
                            </a>
                        </li>
                        <li>
                            <a href="">
                                Tài chính - Ngân hàng - Đầu tư
                            </a>
                        </li>
                        <li>
                            <a href="">
                                Lao động - Tiền lương
                            </a>
                        </li>
                    </ul>

                
                
	</div>
</div>