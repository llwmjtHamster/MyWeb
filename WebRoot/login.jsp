<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery2.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>
<script language="javascript" src="js/js.js"></script>
		<script type="text/javascript">
	function loginSys() {
		var loginName = document.getElementById("loginname").value;

		var password = document.getElementById("loginpassword").value;
		if (loginName == "" || loginName == null) {
			alert("登陆账号不能为空!");
			return false;
		}
		if (password == "" || password == null) {
			alert("登陆密码不能为空!");
			return false;
		}
		this.form1.submit();
	}
	 
</script>
</head>

<body style="background-color:#1c77ac; background-image: url(images/light.png) background-repeat:no-repeat; background-position:center top; overflow:hidden;">
<div id="mainBody">
  <div id="cloud1" class="cloud"></div>
  <div id="cloud2" class="cloud"></div>
</div>
<div class="logintop"> <span>欢迎使用东软智能电子工程学院社团宣传后台管理</span>
  <ul>
    <li><a href="#"></a></li>
  </ul>
</div>
<div class="loginbody"> <span class="systemlogo"></span>
<form action="<%=path%>/LoginServlet"  method="post" id="form1">
  <div class="loginbox">
    <ul>
      <li>
        <input name="loginname"  id="loginname" type="text" class="loginuser" value="admin" onclick="JavaScript:this.value=''"/>
      </li>
      <li>
        <input name="loginpassword" id="loginpassword" type="password" class="loginpwd" value="密码" onclick="JavaScript:this.value=''"/>
      </li>
     
      <li>
        <input   type="button" class="loginbtn" value="登录"  onclick="return loginSys()"  />
      </li>
    </ul>
  </div>
  </form>
</div>
<div class="loginbm">版权所有： xxx Copyright 2019 - 2025.</div>
</body>
</html>
<script type="text/javascript">
	window.onload = function() {	 
		var alertNote = "${alertNote}";
		  if (alertNote == "0") {
			alert("登陆失败，请检查用户名和密码是否正确!");
		}
	} 
</script>


