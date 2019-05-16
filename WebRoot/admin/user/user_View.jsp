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
		<title>查看</title>
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
        </script>
	</head>

	<body>
		<div class="x-body">
			<form class="layui-form layui-form-pane">
				  
				<div class="layui-form-item">
					<label for="L_title" class="layui-form-label">
					 姓名
					</label>
					<div class="layui-input-block">
						<input type="text" value="${user.name}" id="name" name="name"
							class="layui-input" readonly>
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_title" class="layui-form-label">
						性别
					</label>
					<div class="layui-input-block">
						<input type="text" value="${user.sex}" id="sex" name="sex"
							class="layui-input" readonly>
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_title" class="layui-form-label">
						年龄
					</label>
					<div class="layui-input-block">
						<input type="text" value="${user.age}" id="age" name="age"
							class="layui-input" readonly>
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_title" class="layui-form-label">
						住址
					</label>
					<div class="layui-input-block">
						<input type="text" value="${user.address}" id="address"
							name="address" class="layui-input" readonly>
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_title" class="layui-form-label">
						学历
					</label>
					<div class="layui-input-block">
						<input type="text" value="${user.xueli}" id="xueli" name="xueli"
							class="layui-input" readonly>
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_title" class="layui-form-label">
						联系电话
					</label>
					<div class="layui-input-block">
						<input type="text" value="${user.dianhua}" id="dianhua"
							name="dianhua" class="layui-input" readonly>
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_title" class="layui-form-label">
						简历
					</label>
					<div class="layui-input-block">
					<c:if test="${(user.filepath!=null)&&(user.filepath!='')}">
					    <a href="<%=path%>/upload/${user.filepath}" target="_blank">查看简历</a>
					</c:if>
					<c:if test="${user.filepath=='null'}">
					           未上传
					</c:if>
					<c:if test="${user.filepath==''}">
					           未上传
					</c:if>
						 
					</div>
				</div>
			</form>
		</div>
	</body>
</html>