<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" buffer="1094kb"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show your Status</title>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script language="JavaScript" type="text/javascript"
	src="js/jquery-1.6.2.js">
	
</script>
<script language="JavaScript" type="text/javascript"
	src="js/ui/jquery.ui.core.js">
	
</script>
<script language="JavaScript" type="text/javascript"
	src="js/ui/jquery.ui.widget.js">
	
</script>
<script language="JavaScript" type="text/javascript"
	src="js/ui/jquery.ui.datepicker.js">
	
</script>
<script>
	$(function() {
		$("#todaydate").datepicker({
			maxDate : -1+0
		});
	});
</script>

<style type="text/css">
@import url(css/myStyle.css);

@import url(css/themes/base/jquery.ui.all.css);
</style>
</head>


<body>
	<s:div cssClass="linkstop" cssStyle="float:right">
Welcome, <s:property value="#session['userName']"/>&nbsp;&nbsp;
<a href='Home.jsp'>Home</a> &nbsp;&nbsp; 
<a href='Logout.jsp'>Logout</a>
	</s:div>
	<s:actionmessage/>
<s:if test="statusList.size() > 0">
	<div class="box2">
		<table class="sample" cellpadding="5px" width="100%">
			<tr class="even">
				<th>Role</th>
				<th>Task / Assignment / Ticket ID</th>
				<th>Work done today</th>
				<th>Impediments</th>
				<th>Comments</th>
				<th>Status</th>
				<th>Plan for the next day</th>


			</tr>
			<s:iterator value="statusList" status="userStatus">

				<tr
					class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">					
					<td><s:property value="role" /></td>
					<td><s:property value="task" /></td>
					<td><s:property value="work_done_today" /></td>
					<td><s:property value="impediments" /></td>
					<td><s:property value="comments" /></td>
					<td><s:property value="status" /></td>
					<td><s:property value="plan" /></td>
				</tr>

			</s:iterator>
		</table>
	</div>
	<s:div cssStyle="float:right"><a href="ViewPreviousStatus.jsp">back</a></s:div>
</s:if> 
	<s:else>
<h2>Records are not Available. Please Insert your Records First !!!!</h2>
<a href ="ViewPreviousStatus.jsp">back</a>
</s:else>


</body>
</html>