<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Download Reports</title>
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
		jQuery("div#DownloadReport_div").find("span[id$='_error']").each(
				function() {
					jQuery(this).css("display", "none");
				});
	}

	function validate_Fields() {
		clear_errors();
		var result = false;
		var date = document.forms["DownloadReport"]["previousdate"].value;
		if (date == null || date == "") {
			document.getElementById('previousdate_error').style.display = "block";
			alert("Date should not be null");
			result = false;
		}
		return result;
	}

	$(function() {
		$("#previousdate").datepicker({
			maxDate : 0
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
	Welcome  <s:property value="#session['userName']" />, &nbsp;&nbsp; 
	<a href="<%=request.getContextPath()%>/listRecords.action">Insert
			Records</a> | <a href='ViewPreviousStatus.jsp'>View Status</a> |	
	<a href='Logout.jsp'>Logout</a>
	</s:div>
	<s:if test="%{#session['userName'] == 'admin'}">
		<br>
		<br>
		<s:include value="AdminModule.jsp"></s:include>
	</s:if>

	<div class="box" align="left" id="DownloadReport_div">
		<s:form id="DownloadReport" action="downloadReports" namespace="/"
			method="post" cssClass="forms">
			<s:actionmessage />
			<s:fielderror></s:fielderror>
			<p>
				<s:textfield id="previousdate" key="label.todaydate"
					name="previousdate" readonly="true" required="true"></s:textfield>
				<%-- <span id="previousdate_error" class="fielderror"
					style="display: none;"> <s:text name="previousdate.required"></s:text></span> --%>
			</p>
			<s:submit name="Submit" onclick="validate_Fields()" key="label.show"
				align="center" cssClass="green" />
		</s:form>
	</div>
</body>
</html>