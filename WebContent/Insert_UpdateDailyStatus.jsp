<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<s:head theme="ajax" debug="true" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert your Status</title>
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
	/* 	$(function() {
	 $("#todaydate").datepicker({
	 maxDate : 0
	 });
	 }); */
	$(function() {
		$("#todaydate").datepicker({
			maxDate : 0
		});
		$('#todaydate').datepicker('setDate', new Date());
	});
</script>
<style type="text/css">
@import url(css/myStyle.css);

@import url(css/themes/base/jquery.ui.all.css);
</style>

</head>
<body>

	<s:div cssClass="linkstop" cssStyle="float:right">
	Welcome  <s:property value="#session['userName']" />, &nbsp;&nbsp;  
	<a href='Home.jsp'>Home</a> &nbsp;&nbsp;<a href='Logout.jsp'>Logout</a>
	</s:div>

	<div class="box" align="left">
		<s:hidden name="d_Id" /> 
		<s:if test="statusList.size() > 0">
			<s:form action="updateDailyStatus" namespace="/" method="post"
				cssClass="forms">
				<s:actionmessage />
				<s:hidden name="LoginAction.userName" />
				<%--   	Hello, <%=session.getAttribute("userName")%> --%>
				<s:iterator value="statusList" status="userStatus">
					<s:textfield id="todaydate" key="label.todaydate" name="todaydate"
						required="true" disabled="true" readonly="true">
					</s:textfield>
					<s:select key="label.role" name="role" list="{'Developer','QA'}"
						headerKey="" cssClass="select" required="*" value="%{role}" />
					<s:textfield name="task" key="label.task" size="54"
						cssClass="input-text" value="%{task}" />
						<s:select key="label.plan_unplan" name="plan_unplan" list="{'Planned','UnPlanned'}"
						headerKey="" cssClass="select" required="*" value="%{plan_unplan}" />
					<s:textarea name="work_done_today" key="label.workdonetoday"
						rows="3" cols="40" cssClass="input-text"
						value="%{work_done_today}"></s:textarea>
					<s:textarea name="impediments" key="label.impediments" rows="3"
						cols="40" cssClass="input-text" value="%{impediments}"></s:textarea>
					<s:textarea name="comments" key="label.comments" rows="3" cols="40"
						cssClass="input-text" value="%{comments}"></s:textarea>
					<s:select name="status"
						list="{'In Progress','On Hold','Completed'}" key="label.status"
						cssClass="label" value="%{status}" />
					<s:textarea name="plan" key="label.plan" rows="3" cols="40"
						cssClass="input-text" value="%{plan}"></s:textarea>
				</s:iterator>
				<s:submit name="Submit" key="label.update" align="center"
					cssClass="green" />
			</s:form>
		</s:if>
		<s:elseif test="statusList.size() == 0">

			<s:form action="addDailyStatus" namespace="/" method="post"
				cssClass="forms">
				<s:fielderror />
				<s:actionmessage />
		<%-- <s:hidden name="LoginAction.userName" />
	  	Hello, <%=session.getAttribute("userName")%> --%>  
	
 	 	 <s:textfield id="todaydate" key="label.todaydate" name="todaydate"
						required="true">	  </s:textfield>
		<s:select key="label.role" name="role" list="{'Developer','QA'}"
						headerKey="" headerValue="Select" cssClass="select" required="*" />
		<s:textfield name="task" key="label.task" size="54"
						cssClass="input-text" />
		<s:select key="label.plan_unplan" name="plan_unplan" list="{'Planned','UnPlanned'}"
						headerKey="" cssClass="select" required="*"/>
		<s:textarea name="work_done_today" key="label.workdonetoday" rows="3"
						cols="40" cssClass="input-text"></s:textarea>
		<s:textarea name="impediments" key="label.impediments" rows="3"
						cols="40" cssClass="input-text"></s:textarea>
		<s:textarea name="comments" key="label.comments" rows="3" cols="40"
						cssClass="input-text"></s:textarea>
		<s:select name="status" list="{'In Progress','On Hold','Completed'}"
						headerKey="" headerValue="Select Status" key="label.status"
						cssClass="label" />
		<s:textarea name="plan" key="label.plan" rows="3" cols="40"
						cssClass="input-text"></s:textarea>
		<s:submit name="Submit" key="label.save" align="center"
						cssClass="green" />
			</s:form>
		</s:elseif>
	</div>
	<%-- 	<div class="box" align="left">
		<s:form action="addDailyStatus" namespace="/" method="post"
			cssClass="forms">
			<s:fielderror/>
			<pre>
	<s:hidden name="LoginAction.userName" />
	  	Hello, <%=session.getAttribute("userName")%>  
	
 	 	 <s:textfield id="todaydate" key="label.todaydate" name="todaydate"
					required="true">	  </s:textfield>
		<s:select key="label.role" name="role" list="{'Developer','QA'}"
					headerKey="" headerValue="Select" cssClass="select" required="*" />
		<s:textfield name="task" key="label.task" size="54"
					cssClass="input-text" />
		<s:textarea name="work_done_today" key="label.workdonetoday" rows="3"
					cols="40" cssClass="input-text"></s:textarea>
		<s:textarea name="impediments" key="label.impediments" rows="3"
					cols="40" cssClass="input-text"></s:textarea>
		<s:textarea name="comments" key="label.comments" rows="3" cols="40"
					cssClass="input-text"></s:textarea>
	
		<s:select name="status" list="{'In Progress','On Hold','Completed'}"
					headerKey="" headerValue="Select Status" key="label.status"
					cssClass="label" />
		<s:textarea name="plan" key="label.plan" rows="3" cols="40"
					cssClass="input-text"></s:textarea>
		<s:submit name="Submit" key="label.save" align="center"
					cssClass="green" />
		</pre>
		</s:form>
	</div> --%>

</body>
</html>