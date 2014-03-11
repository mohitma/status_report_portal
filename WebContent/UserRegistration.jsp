<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Form</title>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/demos/style.css">
<script language="JavaScript" type="text/javascript"
	src="js/jquery-1.6.2.js"></script>
<script language="JavaScript" type="text/javascript"
	src="js/ui/jquery.ui.core.js"></script>
<script language="JavaScript" type="text/javascript"
	src="js/ui/jquery.ui.widget.js"></script>
<script language="JavaScript" type="text/javascript"
	src="js/ui/jquery.ui.datepicker.js"></script>
<style type="text/css">
@import url(css/myStyle.css);

@import url(css/themes/base/jquery.ui.all.css);
</style>

</head>
<body>

	<%-- <s:div cssClass="linkstop" cssStyle="float:right">
	Welcome  <s:property value="#session['userName']" />, &nbsp;&nbsp; 
	<a href="<%=request.getContextPath()%>/listRecords.action" class="anchorS">Insert
			Records</a> | <a href='ViewPreviousStatus.jsp'>View Status</a> |
	<a href='DownloadReports.jsp' class="blog">Download</a> |
	<a href='Logout.jsp' class="top-nav">Logout</a>

	</s:div> --%>
 <%
			String userName = request.getParameter("userName");
			session.setAttribute("userName", userName);
		%>
	<div class="box2" align="left">
		<h3 align="center">User Registration Form</h3>
		<s:form id="regForm" action="regUser" namespace="/" method="post"
			cssClass="forms">
			<s:fielderror />
			<s:actionmessage />
		 <%--  <%
			String d_Id = request.getParameter("d_Id");
			session.setAttribute("d_Id", d_Id);
		%>	 --%>
		 <s:textfield id="userId" key="label.userId" name="userId"/> 
 	 	 <s:textfield id="firstName" key="label.firstname" name="firstName"/> 
 	 	 <s:textfield id="lastName" key="label.lastname" name="lastName"/>	 
 	 	 <s:textfield id="userName" key="label.username" name="userName"/>	 
 	 	 <s:password id="password" name="password" key="label.password" />
		 <s:select id="userType" key="label.usertype" name="userType" list="{'Normal','Admin'}"
					headerKey="" headerValue="Select" cssClass="select" />
		
		 <s:submit name="Submit" key="label.reg" align="center"
					cssClass="green" /> 
		
		</s:form>
	</div>
</body>
</html>