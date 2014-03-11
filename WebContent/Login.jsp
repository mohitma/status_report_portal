<%@ page language="java" contentType="text/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Status Report Portal</title>
<link href="<s:url value="/css/myStyle.css"/>" rel="stylesheet" type="text/css"/> 
</head>

<body>
	<h3 align="center">Status Report Portal</h3>
	<div class="box3" align="center">
		<s:form action="login" method="POST" cssClass="forms" namespace="/">
			 <div class="errors"> <s:actionerror /> </div>
			<s:actionmessage/>
		 	<s:fielderror /> 
			<s:textfield name="userName" key="label.username" />
			<s:password name="password" key="label.password" />
			<s:div cssStyle="float:right"> 
			<s:submit name="login" value="Login" cssClass="green" />
			</s:div>
		</s:form>
	</div>
</body>
</html>


<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<%
			String name = request.getParameter("userName");
			session.setAttribute("userName", name);
		%>	
<s:form action="login" namespace="/" method="post">
<div valign="center">
	<s:textfield name="userName" key="label.username" size="20"/>
	<s:password name="password" key="label.password"  size="20"/>
	<s:submit name="login-" key="label.login" align="center" />
</div>
</s:form>
</body>
</html> --%>