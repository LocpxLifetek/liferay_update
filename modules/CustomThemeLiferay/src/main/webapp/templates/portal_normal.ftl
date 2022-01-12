<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${html_title}</title>
	<meta charset="UTF-8">
	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<div class="container-fluid" id="wrapper">
	<header id="banner" role="banner">
		<div id="heading">
			<div aria-level="1" class="site-title" role="heading">
				<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
					<img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" width="${site_logo_width}" />
				</a>

				<#if show_site_name>
					<span class="site-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
						${site_name}
					</span>
				</#if>
			</div>
		</div>	
	</header>

	<section id="content">
		<h2 class="hide-accessible" role="heading" aria-level="1">${the_title}</h2>

		<#if selectable>
			<@liferay_util["include"] page=content_include />
		<#else>
			${portletDisplay.recycle()}

			${portletDisplay.setTitle(the_title)}

			<@liferay_theme["wrap-portlet"] page="portlet.ftl">
				<@liferay_util["include"] page=content_include />
			</@>
		</#if>
	</section>

	<footer id="footer" role="contentinfo">
		<div class="footer_cp">
            <div class="footer_top_cp">
                <a href="#" class="menu_bottom_cp">Về đầu trang</a> 
                <img height="20" align="absmiddle" width="21" src="	http://chinhphu.vn/templates/govportal/tinhthanh/images/top.jpg">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <div class="footer_menu_cp">
                <div class="kengang"></div>
                <div class="menu_left_cp">
                    <ul>
                        <li><a href="#">Trang chủ</a></li>
                        <li><a href="#">Báo điện tử Chính phủ</a></li>
                        <li><a href="#">Trang Đa phương tiện</a></li>
                    </ul>
                </div>
                <div class="menu_right_cp">
                    <ul>
                        <li><a href="#">Giới thiệu Cổng TTĐT Chính phủ</a></li>
                        <img hspace="8" height="12" width="1" src="	http://chinhphu.vn/templates/govportal/chinhphu/images/line_menu_bottom.jpg">
                        <li><a href="#">Sơ đồ Cổng thông tin</a></li>
                        <img hspace="8" height="12" width="1" src="	http://chinhphu.vn/templates/govportal/chinhphu/images/line_menu_bottom.jpg">
                        <li><a href="#"> Liên hệ</a></li>
                        <img hspace="8" height="12" width="1" src="	http://chinhphu.vn/templates/govportal/chinhphu/images/line_menu_bottom.jpg">
                        <li><a href="#">English</a></li>
                        <img hspace="8" height="12" width="1" src="	http://chinhphu.vn/templates/govportal/chinhphu/images/line_menu_bottom.jpg">
                        <li><a href="#">中文</a></li>
                    </ul>
                </div>
            </div>
            <div class="footer_bottom_cp">
                <strong>© CỔNG THÔNG TIN ĐIỆN 
                    TỬ CHÍNH PHỦ</strong><br>
                
                    Tổng Giám đốc: Nguyễn Hồng Sâm<br>
                    Trụ sở: 16 Lê Hồng Phong - Ba Đình - Hà Nội.<br>
                    Điện thoại: Văn phòng: 080 43162; Fax: 080.48924; Email: <a href="mailto:thongtinchinhphu@chinhphu.vn" style="font-family:Arial; font-size:11px; text-decoration:underline;"> 
                    thongtinchinhphu@chinhphu.vn</a>.<br>
                Bản quyền thuộc Cổng Thông tin điện tử Chính phủ.<br>
                    Ghi rõ nguồn 'Cổng Thông tin điện tử Chính phủ' hoặc '<a href="http://www.chinhphu.vn" style="font-family:Arial; font-size:11px; text-decoration:underline;">www.chinhphu.vn</a>' 
                    khi phát hành lại thông tin từ các nguồn này.
            </div>
        </div>
	</footer>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>