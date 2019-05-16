<%@ page language="java" import="java.util.*,com.myweb.domain.Admin"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	 
	String path = request.getContextPath();
	
	Admin admin=new Admin();
	
	if(session.getAttribute("admin")!=null)
	{
	   admin=(Admin)session.getAttribute("admin");
	  
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<title></title>
		<meta charset="utf-8" />
		<meta name="renderer" content="webkit" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta name="viewport"
			content="width=device-width, initial-scale=1, maximum-scale=1" />
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="format-detection" content="telephone=no" />
		<link rel="stylesheet" href="<%=path%>/css/x-admin.css" media="all" />
		<link rel="stylesheet" href="<%=path%>/css/pag.css" media="all" />
		<script src="<%=path%>/lib/layui/layui.js" charset="utf-8"></script>
		<script src="<%=path%>/js/x-layui.js" charset="utf-8"></script>
		<script src="<%=path%>/js/jquery2.js" charset="utf-8"></script>
		<script src="<%=path%>/js/js.js" charset="utf-8"></script>
		
		<script type="text/javascript" src="<%=path%>/alert/popwin.js"></script>   
		<script>
		     //layui初始化
             layui.use(['laydate','element','laypage','layer'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element();//面包导航
              laypage = layui.laypage;//分页
              layer = layui.layer;//弹出层
            });
            
               
		    //判断输入的页码是否为数字
		   function checkRate(aa) {
		　　var re = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/ 
		　　if (!re.test(aa)) {
		　　　 　//alert("请输入数字");
			   layer.msg('请输入数字!',{icon: 5,time:1000});
		　　　　return false;
		　　}
		    return true;
		    }
		
		   //获得标识并提醒             
			function noteAlert() {
				var alertNote = "${alertNote}";
				 //alert(alertNote);
				if (alertNote == "1") {
					alert('操作成功!');
				} else if (alertNote == "0") {
				  alert('操作失败，请联系管理员!'); 
				}
			}
			//页面初始化时加载
			$(function() { 
		 	   noteAlert();
			});
         </script>
	</head>
	<body>
		<div class="x-nav">
			<span class="layui-breadcrumb"> <a><cite>首页</cite> </a> <a><cite>修改密码</cite>
			</a> </span>
		</div>
		<div class="x-body">
			<form id="form1" class="layui-form"
				action="<%=path%>/AdminServlet?action=grxxsave" method="post">

				<div class="layui-form-item">
					<label for="L_email" class="layui-form-label">
						账号
					</label>
					<div class="layui-input-inline">
						<%=admin.getLoginname()%>
					</div>
					 
				</div>
				<div class="layui-form-item">
					<label for="L_email" class="layui-form-label">
						密码
					</label>
					<div class="layui-input-inline">
						<input type="text" id="loginpsw" name="loginpsw"
							value="<%=admin.getLoginpsw()%>" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">
						<span class="x-red">*</span>
					</div>
				</div>
				  
				<div class="layui-form-item">
					<label for="L_repass" class="layui-form-label">
					</label>
					<button class="layui-btn" onClick="comitForm()">
						修改
					</button>
				</div>
			</form>
		</div>
	</body>
</html>
 