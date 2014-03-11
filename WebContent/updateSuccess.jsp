<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<s:head theme="ajax" debug="true" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Status</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">

<style type="text/css">
@import url(css/myStyle.css);
@import url(css/themes/base/jquery.ui.all.css);
</style>
</head>

<body>
	<s:div cssClass="linkstop" cssStyle="float:right">
	Welcome, <%=session.getAttribute("userName")%>  
<%-- <s:property value="userName"></s:property>&nbsp;&nbsp; --%>
<!-- <a href='ViewPreviousStatus.jsp'>Previous Status</a> &nbsp;&nbsp; <a href='Logout.jsp'>Logout</a> -->
&nbsp;&nbsp; <a href='Logout.jsp'>Logout</a>
	</s:div>

	<h2>Congratulations, <%= session.getAttribute( "userName" ) %>!	</h2>
	<h3> Successfully Updates your Records. </h3>
	<a href='Home.jsp'>Home Page</a>
</body>
</html>