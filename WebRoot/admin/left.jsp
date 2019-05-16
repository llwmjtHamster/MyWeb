<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/public.css"/>
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/public.js"></script></head>

<body id="bg">
	<!-- 左边节点 -->
	<div class="container">

		<div class="leftsidebar_box">
			<a href="main.jsp" target="main">
            <div class="line">
		    <img class="icon1" src="<%=path%>/images/coin01.png" />
            <img class="icon2" src="<%=path%>/images/coin02.png" />&nbsp;&nbsp;首页
			</div>
            </a>


			<dl class="system_log">
				<dt>
					<img class="icon1" src="<%=path%>/images/coin07.png" />
                    <img class="icon2" src="<%=path%>/images/coin08.png" />
                                                    系统菜单
                    <img class="icon3" src="<%=path%>/images/coin19.png" />
                    <img class="icon4" src="<%=path%>/images/coin20.png" />
				</dt>
				<dd>
					<img class="coin11" src="../images/coin111.png" />
					<img class="coin22" src="../images/coin222.png" />
					<a href="<%=path%>/NewsServlet?action=page" target="main" class="cks">图片新闻</a>
					<img class="icon5" src="../images/coin21.png" />
				</dd>
				<dd>
					<img class="coin11" src="../images/coin111.png" />
					<img class="coin22" src="../images/coin222.png" />
					<a href="<%=path%>/SPNewsServlet?action=page" target="main" class="cks">视频新闻</a>
					<img class="icon5" src="../images/coin21.png" />
				</dd>
				<dd>
					<img class="coin11" src="../images/coin111.png" />
					<img class="coin22" src="../images/coin222.png" />
					<a href="<%=path%>/UserServlet?action=page" target="main" class="cks">社团会员管理</a>
					<img class="icon5" src="../images/coin21.png" />
				</dd>

				<dd>
				<img class="coin11" src="../images/coin111.png" />
				<img class="coin22" src="../images/coin222.png" />
				<a href="<%=path%>/admin/admin/grxx.jsp" target="main" class="cks">修改密码</a>
				<img class="icon5" src="../images/coin21.png" />
				</dd>
                
			</dl>
             
		</div>

	</div>
</body>
</html>