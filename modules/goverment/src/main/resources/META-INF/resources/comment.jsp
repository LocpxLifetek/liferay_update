<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.blogs.model.BlogsEntry"%>
<%@ include file="/init.jsp"%>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix="liferay-comment" uri="http://liferay.com/tld/comment" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<liferay-ui:panel-container extended="<%=false%>"
  id="guestbookCollaborationPanelContainer" persistState="<%=true%>">
  <liferay-ui:panel collapsible="<%=true%>" extended="<%=true%>"
    id="guestbookCollaborationPanel" persistState="<%=true%>" title="BÌNH LUẬN">
    <portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />
    <liferay-comment:discussion className="<%=BlogsEntry.class.getName()%>"
      classPK="${blogsEntry.entryId}"
      formName="fm2"
      ratingsEnabled="<%=true%>" redirect="<%= PortalUtil.getCurrentURL(request)%>"
      userId="${blogsEntry.userId}" />

  </liferay-ui:panel>
</liferay-ui:panel-container>