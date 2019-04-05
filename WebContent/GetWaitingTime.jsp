<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Metro Transit</title>
</head>
<script type="text/javascript">
	function formValidation() {
		var route, from, direction;

		route = document.forms["RouteForm"]["route"];
		from = document.forms["RouteForm"]["destination"];
		direction = document.forms["RouteForm"]["direction"];

		if (route.value == " ") {
			document.getElementById("routeError").innerHTML = "Please fill the detail";
			route.focus();
			return false;
		}else if (from.value == " ") {
			document.getElementById("errorDestination").innerHTML = "Please fill the detail";
			from.focus();
			return false;
		}else if (direction.value == " ") {
			document.getElementById("errorDirection").innerHTML = "Please fill the detail";
			direction.focus();
			return false;
		}
	}
</script>
<body>
	<form name="RouteForm" action="GetWaitingTime" method="post"
		onsubmit=" return formValidation()">
		<table>
			<tr>
				<td><label>Route:</label></td>
				<td><input type="text" name="route" required></td>
				<td><p id="routeError" /></td>
			</tr>
			<tr>
				<td><label>From:</label></td>
				<td><input type="text" name="destination" required /></td>
				<td><p id="errorDestination" /></td>
			</tr>
			<tr>
				<td><label>Direction:</label></td>
				<td><input type="text" name="direction" required /></td>
				<td><p id="errorDirection" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Get Waiting Time"></td>
			</tr>
		</table>
	</form>
	<%
						if (request.getAttribute("errorMsg") != null) {
					%>
	<p style="color: red; font-size: 90%"><%=request.getAttribute("errorMsg")%></p>
	<%
						} else {
					%>
	<br>
	<br>
	<%	
						}
					%>
	<%
						if (request.getAttribute("successMsg") != null) {
					%>
	<p style="color: black; font-size: 90%"><%=request.getAttribute("successMsg")%></p>
	<%
						} else {
					%>
	<br>
	<br>
	<%	
						}
					%>
</body>
</html>