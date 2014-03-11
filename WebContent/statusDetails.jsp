<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>All Records:</h3>  
<s:iterator  value="list">  
<fieldset>  
<s:property value="id"/><br/>  
<s:property value="name"/><br/>  
<s:property value="password"/><br/>  
<s:property value="email"/><br/>  
</fieldset>  
</s:iterator> 
</body>
</html>