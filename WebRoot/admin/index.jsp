<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.myweb.domain.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 
	 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>东软智能电子工程学院社团宣传后台管理</title>
	</head>
	<frameset rows="100,*" cols="*" scrolling="No" framespacing="0"
		frameborder="no" border="0">
	<frame src="<%=path%>/admin/head.jsp" name="headmenu" id="mainFrame" title="mainFrame">
	<!-- 引用头部 -->
	<!-- 引用左边和主体部分 -->
	<frameset rows="100*" cols="220,*" scrolling="No" framespacing="0"
		frameborder="no" border="0">
	<frame src="<%=path%>/admin/left.jsp" name="leftmenu" id="mainFrame" title="mainFrame">
	<frame src="<%=path%>/admin/main.jsp" name="main" scrolling="yes" noresize="noresize"
		id="rightFrame" title="rightFrame">
	</frameset>
	</frameset>
	<noframes></noframes>
</html>