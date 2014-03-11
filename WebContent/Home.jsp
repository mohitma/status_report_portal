<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<style type="text/css">
@import url(css/myStyle.css);
@import url(css/themes/base/jquery.ui.all.css);
</style>

<%--  <style type="text/css">
A:link {background: #FFCC00; text-decoration: none}
A:visited {background: #FFCC00; text-decoration: none}
A:active {background: #FFCC00; text-decoration: none}
A:hover {background: #FFCC00; font-weight:bold; color: red;}
</style> --%> 
</head>
<s:div cssClass="styleone" cssStyle="float:right">
	Welcome  <s:property value="#session['userName']" />, &nbsp;&nbsp; 
	<a href="<%=request.getContextPath()%>/listRecords.action" class="styleone">Insert Records</a> | 
	<a href='ViewPreviousStatus.jsp'>View Status</a> |
			<s:if test="%{userName == 'admin'}">
		<a href='DownloadReports.jsp'>Download</a> |
		<a href='ProjectCreation.jsp'>Create Project</a> |
		<a href='UserProjectaAssoc.jsp'>Assign Project</a> |
	</s:if>
	<a href='Logout.jsp'>Logout</a>
</s:div>
</html>