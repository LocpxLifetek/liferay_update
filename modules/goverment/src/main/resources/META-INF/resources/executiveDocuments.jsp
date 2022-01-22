<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<div class="TD-box-news ">
        <div class="TD-box-news-title" style="background-image: url(<%=request.getContextPath()%>/images/portal/_res/img/bg-head.svg);"><a
                href='portal/Pages/Hoat-dong-lanh-dao-UBND-tinh.aspx'>Văn bản điều hành</a></div>
        <div class="TD-box-news-main">
            <table cellspacing="0"
                id="ctl00_ctl34_g_1ef02479_6652_4c18_9f77_78ab31852301_ctl00_dform"
                style="border-collapse:collapse;">
                <tr>
                    <td colspan="2">
                        <div
                            class="tandan-div-module-news-top">
                            <div
                                class="tandan-div-module-default-left">
                                <a
                                    href='${url}/tintuc?uuid=${blogsEntryDto.uuidBlogsEntry}'><img
                                        src='${blogsEntryDto.src}'></a>
                            </div>
                            <div
                                class="tandan-div-module-default-right">
                                <p
                                    class="tandan-p-module-news-title">
                                    <a
                                        href='${url}/tintuc?uuid=${blogsEntryDto.uuidBlogsEntry}'>${blogsEntryDto.titleBlogsEntry}</a><span
                                        class="ngay">
                                        (<fmt:formatDate
											value="${blogsEntryDto.modifiedDate}" pattern="dd/MM/yyyy" />)</span>
                                </p>
                                <p
                                    class="tandan-p-module-news-summary">
                                    ${blogsEntryDto.description}</p>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
            <div class="tandan-div-module-news-bottom">
				<c:forEach items="${listBlogsNoImage}" var="blog">
				
	                <p class="tandan-p-module-news-second">
	                    <a
	                        href='${url}/tintuc?uuid=${blog.uuidBlogsEntry}' style="background:url('<%=request.getContextPath()%>/images/portal/_themes/ThanhHoa_orange/img/hv-more-news.jpg') no-repeat top left; background-position-y: 5px;">${blog.titleBlogsEntry}</a><span
	                        class="ngay">
	                        (<fmt:formatDate
								value="${blog.modifiedDate}" pattern="dd/MM/yyyy" />)</span>
	                </p>
				</c:forEach>
            </div>
        </div>
    </div>
