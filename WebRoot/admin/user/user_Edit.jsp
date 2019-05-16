<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>编辑</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport"
			content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		<link rel="stylesheet" href="<%=path%>/css/x-admin.css" media="all">
		<script src="<%=path%>/lib/layui/layui.js" charset="utf-8"></script>
		<script src="<%=path%>/js/x-layui.js" charset="utf-8"></script>
		<script src="<%=path%>/js/jquery2.js" charset="utf-8"></script>
		<script src="<%=path%>/js/js.js" charset="utf-8"></script>
		<script>
         //初始化layui
          layui.use(['form','layer','upload'], function(){
                $ = layui.jquery;
              var form = layui.form()
              ,layer = layui.layer;
            });
           
            
		//获得标识并提醒             
		function noteAlert() {
			var alertNote = "${alertNote}";
			if (alertNote == "1") {
				alert('操作成功!');
	              parent.location.reload(); // 父页面刷新
	                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	                parent.layer.close(index);
	            
			} else if (alertNote == "0") {
			  alert('操作失败，请联系管理员!'); 
			}
		}
		$(function() { 
	 	   noteAlert();
		});
        </script>
	</head>

	<body>
		<div class="x-body">
			<form id="form1" class="layui-form"
				action="<%=path%>/UserServlet?action=editSave" method="post">
				<input type="hidden" name="id" id="id"
					value='<c:out value="${user.id}"></c:out>' />
				<input type="hidden" name="filepath" id="filepath"
					   value='<c:out value="${user.filepath}"></c:out>' />
				<input type="hidden" name="loginname" id="loginname"
					   value='<c:out value="${user.loginname}"></c:out>' />

				<div class="layui-form-item">
					<label for="L_email" class="layui-form-label">
						账号
					</label>
					<div class="layui-input-inline">
						${user.loginname}
					</div>
					 
				</div>
				<div class="layui-form-item">
					<label for="L_email" class="layui-form-label">
						密码
					</label>
					<div class="layui-input-inline">
						<input type="text" value="${user.loginpw}" id="loginpw" lay-verify="required"
							name="loginpw" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">
						<span class="x-red">*</span>
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_email" class="layui-form-label">
						姓名:
					</label>
					<div class="layui-input-inline">
						<input type="text" value="${user.name}" id="name" name="name" lay-verify="required"
							   class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">
						<span class="x-red">*</span>
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_email" class="layui-form-label">
						身份证号:
					</label>
					<div class="layui-input-inline">
						<input type="text" value="${user.idnum}" id="idnum" name="name" lay-verify="required"
							   class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">
						<span class="x-red">*</span>
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_email" class="layui-form-label">
						性别
					</label>
					<div class="layui-input-inline">
						<input type="text" value="${user.sex}" id="sex" name="sex" lay-verify="required"
							class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">
						<span class="x-red">*</span>
					</div>
				</div>

				<div class="layui-form-item">
					<label for="L_email" class="layui-form-label">
						住址
					</label>
					<div class="layui-input-inline">
						<input type="text" value="${user.address}" id="address" lay-verify="required"
							name="address" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">
						<span class="x-red">*</span>
					</div>
				</div>

				<div class="layui-form-item">
					<label for="L_email" class="layui-form-label">
						电话
					</label>
					<div class="layui-input-inline">
						<input type="text" value="${user.dianhua}" id="dianhua" lay-verify="required"
							name="dianhua" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">
						<span class="x-red">*</span>
					</div>
				</div>
				 

				<div class="layui-form-item">
					  <button class="layui-btn" lay-filter="tijiao" lay-submit="">更新</button>
				</div>
			</form>
		</div>
	</body>
</html>