<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String targetpath = request.getAttribute("targetpath").toString();
	String message = request.getAttribute("message").toString();
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<html>
<script language="javascript">
    <% if(message != null) { %>
		alert("<%=message%>");
	<% } %>
	<% if(targetpath != null) { %>
		document.location.href = "<%=path%><%=targetpath%>";
	<% } else {%>
		window.history.back();
	<% } %>
</script>
<body> 
</body> 
</html>
