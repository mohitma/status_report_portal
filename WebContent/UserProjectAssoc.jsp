<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User and Project association form</title>

<style type="text/css">
@import url(css/myStyle.css);
@import url(css/themes/base/jquery.ui.all.css);
</style>

</head>
<body>


	<div class="box2" align="left">
	<h3 align="center">User and Project association form</h3>		
		<s:form id="userProjectAssocForm" action="" namespace="/" method="post"
			cssClass="forms">
			<s:fielderror />
			<s:actionmessage />
 	 	 <s:select key="label.projectname" name="projectName" list="{'aa','sf'}"
						headerValue="Select" headerKey="" cssClass="select" />
		<s:select key="label.resource" name="resource" list="{'aa','sf'}"
						headerValue="Select" headerKey="" cssClass="select" />

		 <s:submit name="Submit" key="label.assign" align="center"
					cssClass="green" /> 
		
		</s:form>
	</div>
</body>
</html>