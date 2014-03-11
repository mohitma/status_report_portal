<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert your Status</title>
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
<script>
	function clear_errors() {
		jQuery("div#viewPreviousStatus_div").find("span[id$='_error']").each(
				function() {
					jQuery(this).css("display", "none");
				});
	}

	function validate_fields() {
		clear_errors();
		var result = true;
		var date = document.forms["viewPreviousStatus"]["previousdate"].value;
		if (date == null || date == "") {
			document.getElementById('previousdate_error').style.display = "block";
			alert("Date should not be null");
			// document.viewPreviousStatus.previousdate.focus();
			result = false;
		}
		return result;
	}

	$(function() {
		$("#previousdate").datepicker({
			maxDate : -1
		});
	});
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
Welcome,<%= session.getAttribute("userName")  %>&nbsp;&nbsp;
<%-- <s:property value="#session['userName']" />&nbsp;&nbsp; --%>

 <a href="<%=request.getContextPath()%>/listRecords.action">Insert
			Records</a> | 
			<a href='ViewPreviousStatus.jsp' class="styleone">View Status</a> |
		 	<a href='Logout.jsp'>Logout</a>
	</s:div>
	
	<s:if test="%{#session['userName'] == 'admin'}">
	<br><br>
	<s:include value="AdminModule.jsp"></s:include>
	</s:if>

	<div class="box" align="left" id="viewPreviousStatus_div">
		<s:form id="viewPreviousStatus" action="viewPreviousDailyStatus"
			namespace="/" method="post" cssClass="forms">
			<s:actionmessage />
			<s:fielderror></s:fielderror>
			<p>
				<s:textfield id="previousdate" key="label.previousdate"
					name="previousdate" readonly="" required="true"
					onblur="dateValidation(this.value);"></s:textfield>

				<span id="previousdate_error" class="fielderror"
					style="display: none;"> <s:text name="previousdate.required"></s:text></span>
			</p>
			<s:submit id="submitSave" name="Submit" key="label.show"
				onclick="return validate_fields();" align="center" cssClass="green"
				disabled="false" />
		</s:form>
	</div>
	<s:if test="statusList.size() > 0">
		<div class="box2">
			<table class="sample" cellpadding="5px" width="100%">
				<tr class="even">
					<th>No.</th>
					<th>Role</th>
					<th>Task / Assignment / Ticket ID</th>
					<th>Planned/Unplanned</th>
					<th>Work done today</th>
					<th>Impediments</th>
					<th>Comments</th>
					<th>Status</th>
					<th>Plan for the next day</th>


				</tr>
				<s:iterator value="statusList" status="userStatus">

					<tr
						class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">
						<td><s:property value="d_Id" /></td>
						<td><s:property value="role" /></td>
						<td><s:property value="task" /></td>
						<td><s:property value="plan_unplan" /></td>
						<td><s:property value="work_done_today" /></td>
						<td><s:property value="impediments" /></td>
						<td><s:property value="comments" /></td>
						<td><s:property value="status" /></td>
						<td><s:property value="plan" /></td>
					</tr>

				</s:iterator>
			</table>
		</div>
	</s:if>
	<s:elseif test="statusList.size() == 0">
		<h2>Sorry, Records are not Available. Please try Again !!!!</h2>
	</s:elseif>
</body>
</html>