<%@page import="com.liferay.blogs.model.BlogsEntry"%>
<%@ include file="/init.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui"%>
<%@ taglib prefix="liferay-comment" uri="http://liferay.com/tld/comment"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="detailBlogs">
	<div class="title">
		<h2>${blogsEntry.title}</h2>
		<span style="color:#000000;opacity: 0.6;">(${time})</span>
		<hr>
	</div>
	<div class="description">
		<h4 style=" font-weight: bold;">${blogsEntry.description}</h4>
	</div>
	<div class="contentBlogs">
		${blogsEntry.content}
	</div>
</div>
