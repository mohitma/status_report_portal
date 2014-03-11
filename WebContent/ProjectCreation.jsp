<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Creation Form</title>

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
<div class="box2" align="left">
<s:form id="projectCreationForm" action="createProj" namespace="/" method="post"
			cssClass="forms">
		<h3 align="center">Project Creation Form</h3>
		<s:textfield id="projectcode" key="label.projectcode" name="projectCode"/>
		<s:textfield id="projectname" key="label.projectname" name="projectName"/> 
 	 	<s:textfield id="projectmanager" key="label.projectmanager" name="projectManager"/> 	 
		 <s:submit name="Submit" key="label.create" align="center"
					cssClass="green" />
				</s:form> 
		</div>
</body>
</html>