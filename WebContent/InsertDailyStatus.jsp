<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert your Status</title>
<link rel="stylesheet"	href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/demos/style.css">
<script language="JavaScript" type="text/javascript" src="js/jquery-1.6.2.js"></script>
<script language="JavaScript" type="text/javascript" src="js/ui/jquery.ui.core.js"></script>
<script language="JavaScript" type="text/javascript" src="js/ui/jquery.ui.widget.js"></script>
<script language="JavaScript" type="text/javascript" src="js/ui/jquery.ui.datepicker.js"></script>
<script>
 
	$(function() {
		$("#todaydate").datepicker({
			maxDate : 0
		});
		$('#todaydate').datepicker('setDate', new Date());
	});
	addEvent(window,"load",onBodyLoad);
</script>
 <script>
function onBodyLoad(){
	document.getElementById("plan_unplan").value = '';
    document.InsertDailyStatusID.reset();
} 
function clear_form_elements(ele) {

    $(ele).find(':input').each(function() {
        switch(this.type) {
            case 'password':
            case 'select-multiple':
            case 'select-one':
            case 'text':
            case 'textarea':
                $(this).val('');
                break;
            case 'checkbox':
            case 'radio':
                this.checked = false;
        }
    });

}
  
//window.onload=onBodyLoad;

</script> 
<style type="text/css">
@import url(css/myStyle.css);
@import url(css/themes/base/jquery.ui.all.css);
</style>

</head>
<body>

 <%
			String userName = request.getParameter("userName");
			session.setAttribute("userName", userName);
		%>	 

	 <s:div cssClass="linkstop" cssStyle="float:right">
	Welcome  <s:property value="#session['userName']" />, &nbsp;&nbsp;
	<%= session.getAttribute("userName")  %> 
	<a href="<%=request.getContextPath()%>/listRecords.action" class="styleone">Insert
			Records</a> | <a href='ViewPreviousStatus.jsp'>View Status</a> |				
	<a href='Logout.jsp'>Logout</a>
	</s:div> 
	<s:if test="%{#session['userName'] == 'admin'}">
	<br><br>
	<s:include value="AdminModule.jsp"></s:include>
	</s:if>
	
	<div class="box" align="left">
		<s:form id="InsertDailyStatusID" action="addDailyStatus" namespace="/" method="post"
			cssClass="forms">
			<s:fielderror/>
			<s:actionmessage/>
			<pre>
	<%-- <s:hidden name="LoginAction.userName" /> --%>
		 <s:hidden name="d_Id" id="d_Id"></s:hidden>		 
		 
 	 	 <s:textfield id="todaydate" key="label.todaydate" name="todaydate">	  </s:textfield>
		<s:select key="label.role" name="role" list="{'Dev','QA','UI'}"
					headerKey="" headerValue="Select" cssClass="select"/>
		<s:textfield name="task" key="label.task" size="54"
					cssClass="input-text" />
		<s:select key="label.plan_unplan" name="plan_unplan" list="{'Planned','Unplanned'}"
						headerValue="Select" headerKey="" cssClass="select" />
		<s:textarea name="work_done_today" key="label.workdonetoday" rows="3"
					cols="40" cssClass="input-text"></s:textarea>
		<s:textarea name="impediments" key="label.impediments" rows="3"
					cols="40" cssClass="input-text"></s:textarea>
		<s:textarea name="comments" key="label.comments" rows="3" cols="40"
					cssClass="input-text" ></s:textarea>
		<s:select name="status" list="{'In Progress','On Hold','Completed'}"
					headerKey="" headerValue="Select Status" key="label.status"
					cssClass="label"/>
		<s:textarea name="plan" key="label.plan" rows="3" cols="40"
					cssClass="input-text"></s:textarea>
	<a href="<%= request.getContextPath() %>/listRecords.action" >Add More</a>
	<s:submit name="Submit" key="label.save" align="center"	cssClass="green" /> 
		</pre>
		</s:form>
	</div>
  
<%-- <s:if test="statusList.size() > 0"> --%>
	<div class="box2">
		<table class="sample" cellpadding="5px" width="100%">
			<tr class="even">
				<th>No</th>
				<th>Date</th>
				<th>Role</th>
				<th>Task / Assignment / Ticket ID</th>
				<th>Planned/Unplanned</th>
				<th>Work done today</th>
				<th>Impediments</th>
				<th>Comments</th>
				<th>Status</th>
				<th>Plan for the next day</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<s:iterator value="statusList" status="userStatus">

				<tr
					class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">	
					<td><s:property value="Id" /></td>	
					<td><s:property value="todaydate" /></td>		
					<td><s:property value="role" /></td>
					<td><s:property value="task" /></td>
					<td><s:property value="plan_unplan" /></td>
					<td><s:property value="work_done_today" /></td>
					<td><s:property value="impediments" /></td>
					<td><s:property value="comments" /></td>
					<td><s:property value="status" /></td>
					<td><s:property value="plan" /></td>
					<td><s:url id="editURL" action="editStatus">
					<s:param name="d_Id" value="%{d_Id}"></s:param>
				</s:url> <s:a href="%{editURL}">Edit</s:a></td>
				<td><s:url id="deleteURL" action="deleteStatus">
					<s:param name="d_Id" value="%{d_Id}"></s:param>
				</s:url> <s:a href="%{deleteURL}">Delete</s:a></td>
				</tr>

			</s:iterator>
		</table>
	</div>
<%--  </s:if> --%> 
</body>
</html>