<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>

<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Báo chính phủ</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/all.min.css">
</head>

<body>
    <style>
        * {
            margin: 0;
            padding: 0;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 12px;
        }

        .menu_header_bcp {
            position: relative;
        }

        a {
            color: #333333;
            text-decoration: none;
        }

        .menu {
            background: #ffc65a;
            display: flex;
            justify-content: space-around;
            width: 100%;
        }

        input[type="text"] {
            width: 150px;
            box-sizing: border-box;
            border: none;
            border-radius: 4px;
            outline: none;
            padding: 12px 14px;

            transition: 0.6s ease-in-out;
        }

        input[type="text"]:focus {
            width: 250px;
            background-color: white;
        }

        .menu-right {
            display: flex;

        }

        .menu-info {
            display: flex;
            padding: 10px;
        }

        .brick::after {
            content: "|";
        }

        .brick {
            margin: 5px;
        }

        .inputSearch {
            padding: 2px;
        }

        .bodyInformation {
            position: absolute;
            left: 50%;
            transform: translateX(-50%);

        }

        nav {
            position: absolute;
            top:97%;
            left:0%;
            background: #484848;
            /*tô màu cho menu cha*/
            border-radius: 5px;
            width: 100%;
            
        }

        nav ul {
            display: flex;
            width: 100%;
        }

        nav>ul li {
            list-style: none;
            /* bỏ dấu chấm mặc định của li */
            /* kẻ đường bên trái và bên phải của li */
            border-right: 1px solid #ccc;
            border-left: 1px solid #333;
        }

        nav>ul li a {
            display: block;
            padding: 0 15px;
            line-height: 30px;
            color: #f1f2f3;
            text-decoration: none;
        }

        nav ul li:first-child {
            border-left: none;
            /* bỏ đường kè trái của phần tử đầu tiên */
        }

        nav>ul li:first-child a {
            border-bottom-left-radius: 5px;
            border-top-left-radius: 5px;
        }

        /* Khi hover đến li, tô màu cho thẻ a */
        nav ul li:hover>a {
            background: red;
            opacity: .7;
            color: yellow;
        }

        /*menu con*/
        /*Ẩn các menu con cấp 1,2,3*/
        nav li ul {
            display: none;
            width: 70%;
position: absolute;
        }

        nav li>ul li {
            width: 50%;
            border: none;
            border-bottom: 1px solid #ccc;
            background: #999;
            text-align: left;
        }

        nav li>ul li:first-child a {
            border-bottom-left-radius: 0px;
            border-top-left-radius: 0px;
        }

        nav li>ul li:last-child {
            border-bottom-left-radius: 5px;
            border-bottom-right-radius: 5px;
        }

        nav li>ul li:last-child a {
            border-bottom-left-radius: 5px;
            border-bottom-right-radius: 5px;
        }

        /*khi hover thì hiện menu con*/
        nav li:hover>ul {
            display: block;
        }

        /*Hiển thị menu con cấp 2,3,4 bên cạnh phải*/
        nav>ul li>ul li>ul {
            margin-left: 352px;
            margin-top: -50px;
        }
    </style>
    <header class="menu_header_bcp">
        <div class="menu">
            <div class="left-menu">
                <p style="padding:10px;">Thứ tư, 13/10/2021</p>
            </div>
            <div class="menu-right">
                <div class="inputSearch">
                    <form>
                        <input type="text" name="search" placeholder="Tìm kiếm">
                    </form>
                </div>
                <div class="menu-info">
                    <div>
                        <a href="#">Cổng thông tin điện tử chính phủ</a><span class="brick"></span>
                    </div>
                    <div>
                        <a href="#">Thư điện tử</a><span class="brick"></span>
                    </div>
                    <div>
                        <a href="#">English</a><span class="brick"></span>
                    </div>
                    <div>
                        <a href="#">中文</a>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <div class="bodyInformation">
        <div class="imageGoverment" width:="100%">
            <img src="https://baochinhphu.vn/App_Themes/Default/Images/banner_tiengviet.png" alt="" width:="100%">
            <nav>
                <ul>
                    <li><a href="#" title="Trang chủ"><i class="fas fa-home"></i> Trang chủ</a></li>
                    <li><a href="#" title="Chính Trị">Chính Trị</a>
                        <ul>
                            <li><a href="#" title="Thời sự">Thời sự</a></li>
                            <li><a href="#" title="Đối ngoại">Đối ngoại</a></li>
                            <li>
                                <a href="#" title="Nhân sự">Nhân sự</a>
                            </li>
                        </ul>
                    </li>
                    <li><a href="#" title="Kinh Tế">Kinh Tế</a>
                        <!-- menu con sổ xuống cấp 1 -->
                        <ul>
<li><a href="#" title="Tài chính">Tài chính</a></li>
                            <li><a href="#" title="Thị Trường">Thị Trường</a></li>
                            <li>
                                <a href="#" title="Doanh nghiệp">Doanh nghiệp</a>
                                <!-- menu con sổ ngang cấp 2 -->
                            </li>
                            <li><a href="#" title="Hội nhập">Hội nhập</a></li>
                        </ul>
                    </li>
                    <li><a href="#" title="Văn hóa">Văn hóa</a>
                        <ul>
                            <li><a href="#" title="Thể thao">Thể thao</a></li>
                            <li><a href="#" title="Du lịch">Du lịch</a></li>
                        </ul>
                    </li>
                    <li><a href="#" title="Xã hội">Xã hội</a>
                        <ul>
                            <li><a href="#" title="Pháp luật">Pháp luật</a></li>
                            <li><a href="#" title="Sức khỏe">Sức khỏe</a></li>
                            <li><a href="#" title="Đời sống">Đời sống</a></li>
                            <li><a href="#" title="Bảo hiểm xã hội">Bảo hiểm xã hội</a></li>
                            <li><a href="#" title="Người tốt - Việc tôt">Người tốt - Việc tôt</a></li>
                            
                        </ul>
                    </li>
                    <li><a href="#" title="Văn hóa">Khoa giáo</a>
                        <ul>
                            <li><a href="#" title="Giáo dục">Giáo dục</a></li>
                            <li><a href="#" title="Khoa học - Công nghệ">Khoa học - Công nghệ</a></li>
                            <li><a href="#" title="Biển Việt Nam">Biển Việt Nam</a></li>
                        </ul>
                    </li>
                    <li><a href="#" title="Văn hóa">Quốc tế</a>
                        <ul>
                            <li><a href="#" title="Việt Nam - ASEAN">Việt Nam - ASEAN</a></li>
                            
                        </ul>
                    </li>
                    <li><a href="#" title="Ý kiến bạn đọc">Ý kiến bạn đọc</a></li>
                    <li><a href="#" title="RSS">RSS</a></li>
                    
                </ul>
            </nav>
        </div>
    </div>
</body>

</html>