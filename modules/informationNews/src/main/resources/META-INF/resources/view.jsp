<%@page import="com.liferay.blogs.model.BlogsEntry"%>
<%@ include file="/init.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui"%>
<%@ taglib prefix="liferay-comment" uri="http://liferay.com/tld/comment"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="detailBlogs">
	<div class="title">
		<h2>${blogsEntry.title}</h2>
		<span style="color: #d71920; font-size: 12px;">(<fmt:formatDate
						value="${blogsEntry.modifiedDate}" pattern="MM/dd/yyyy" />)
				</span>
		<hr>
	</div>
	<div class="description">
		<h4 style=" font-weight: bold;">${blogsEntry.description}</h4>
	</div>
	<div class="contentBlogs">
		${blogsEntry.content}
	</div>
</div>
