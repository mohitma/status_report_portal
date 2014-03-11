<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Module</title>
</head>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>

<script language="JavaScript" type="text/javascript"
	src="js/jquery-1.6.2.js"></script>
<script language="JavaScript" type="text/javascript"
	src="js/ui/jquery.ui.core.js"></script>
<script language="JavaScript" type="text/javascript"
	src="js/ui/jquery.ui.widget.js"></script>
<script language="JavaScript" type="text/javascript"
	src="js/ui/jquery.ui.datepicker.js"></script>
<script language="JavaScript" type="text/javascript"
	src="js/ui/jquery.validate.min.js"></script>
<script language="JavaScript" type="text/javascript"
	src="js/ui/jquery.validate.additional-methods.min.js"></script>
<script language="JavaScript" type="text/javascript"
	src="js/ui/jquery.form-validator.min.js"></script>

<style type="text/css">
@import url(css/myStyle.css);
@import url(css/themes/base/jquery.ui.all.css);
</style>
</head>

<body>
	<s:div cssClass="linkstop" cssStyle="float:right">	 
	
	<a href='UserRegistration.jsp'>New User</a> |	
	<s:url id="create" action="listProjectUser"></s:url>
		<s:a href="%{create}">Create Project</s:a> |
    <!-- <a href='ProjectCreation.jsp'>Create Project</a> | -->
	<a href='UserProjectAssoc.jsp'>Assign Project</a> |
	<a href='DownloadReports.jsp'>Download</a> 
<!-- 	<a href='Logout.jsp'>Logout</a> -->
	</s:div>
</body>
</html>
