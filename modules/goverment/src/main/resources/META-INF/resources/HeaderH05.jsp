<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="header-fixed">
        <div class="header">
            <div class="container banner-band">
                <img src="<%=request.getContextPath()%>/images/portal/_res/img/quochuy.png" />
                <div class="brand-text">
                    <h1>BỘ CÔNG AN</h1>
                    <h2>CỤC CÔNG NGHỆ THÔNG TIN</h2>
                    <h3>DEPARTMENT OF INFORMATION TECHNOLOGY</h3>
                </div>
                <div class="tandan-div-nav-top">
                    <img alt="" title="" src="<%=request.getContextPath()%>/images/portal/_themes/ThanhHoa_orange/img/ic-sitemap.png">
                    <a href='javascript:void(0)'
                        onclick="window.location.href=_spPageContextInfo.webServerRelativeUrl+'Pages/sitemap.aspx'">Đăng
                        nhập</a>
                    <!-- <img alt="" title="" src="portal/_themes/ThanhHoa_orange/img/ic-flag-en.jpg">
                    <a href="en-us">English</a> -->
                </div>
            </div>
            <div class="banner-carousel">
                <div class="tandan-div-banner">
                    <a href=""><img src="<%=request.getContextPath()%>/images/portal/Banner/banner_1-min.png" alt=""></a>
                </div>
            </div>
        </div>
        <!-- Menu -->
        <div class="tandan-div-nav">
            <div class="" style="margin: auto; width: 95%">
                <div id='ctl00_PlaceHolderGlobalNavigation_ctl00_ctl00' class="tandan-nav-lop menu-lv1">
                    <ul class="tandan-ul-nav left">
                        <li><a class="parent" href='portal'><span>Trang chủ</span></a></li>

                        <li><a class="parent" href='portal/pages/Gioi-thieu.aspx'><span>Giới thiệu</span></a></li>

                        <li><a class="parent" href='portal/Pages/To-chuc-bo-may.aspx'><span>Lịch công tác</span></a>
                        </li>

                        <li> <a class="parent" href='portal/Pages/home-new/chinh-quyen.aspx'> <span>Thư điện
                                    tử</span> </a>
                        </li>

                        <li><a class="parent" href='portal/Pages/home-new/cong-dan.aspx'><span>Điều hành tác
                                    nghiệp</span></a>
                        </li>

                        <li><a class="parent" href='portal/Pages/home-new/doanh-nghiep.aspx'><span>Diễn
                                    đàn</span></a>
                        </li>
                    </ul>
                </div>

            </div>
        </div>
        <!-- END Menu -->
    </div>