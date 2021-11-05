<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<body>
        <div class="contentContainer">
        <c:forEach items="${blogs}" var="blog">
						<c:choose>
							<c:when test="${blog.smallImageFileEntryId > 0}">
								<c:forEach items="${smallImage}" var="si">
									<c:if test="${blog.smallImageFileEntryId == si.fileEntryId}">
						                   <div class="canboinfo">
										        <div class="anhdaidien">
										          <a
										            href="#"
										            ><img
										              src="/documents/${si.groupId}/${si.folderId}/${si.title}/${si.uuid}"
										              alt=""
										          /></a>
										        </div>
										        <div class="info_lanhdao">
										          <div class="label_lanhdao">
										            <a
										              href="#"
										              ><strong>${blog.title}</strong></a
										            >
										          </div>
										        </div>
										   </div>
									</c:if>
								</c:forEach>
							</c:when>
						</c:choose>
					</c:forEach>
    </div>
</body>

</html>
<style>
      .contentContainer h2.chucdanh {
        background: #ddd;
        font: 700 20px/35px Roboto, Arial, Tahoma;
        padding: 0 20px;
        margin-bottom: 10px;
        color: #404041;
      }
      .canboinfo {
        width: 100%;
        display: inline-block;
        margin-bottom: 30px;
      }
      .canboinfo .anhdaidien {
        width: 30%;
        float: left;
        margin-bottom: 20px;
        text-align: justify;
      }
      .canboinfo .info_lanhdao {
        text-align: justify;
        font: 400 14px/22px "Roboto";
      }
      .canboinfo .anhdaidien img {
        width: 100%;
      }
      .canboinfo .info_lanhdao .label_lanhdao {
        margin-bottom: 5px;
      }
      .canboinfo .info_lanhdao .label_lanhdao:first-child {
        margin-bottom: 10px;
      }
      .canboinfo .info_lanhdao .label_lanhdao:first-child a {
        color: #333;
        text-decoration: none;
      }
      .canboinfo .info_lanhdao .label_lanhdao:first-child a:hover {
        color: #be1e2d;
      }
        </style>