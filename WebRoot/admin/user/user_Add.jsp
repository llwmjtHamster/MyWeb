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
        <title>
          添加
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=path%>/css/x-admin.css" media="all"/>
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
         //提交数据   
	     function comitForm() {
			//var xx = document.getElementById("xx").value; 
			//if (xx == "" || xx == null) {
				//alert("xxx不能为空!");
				//return false;
			//}
		    this.form1.submit();
	     }
		//获得标识并提醒             
		function noteAlert() {
			var alertNote = "${alertNote}";
			 //alert(alertNote);
			if (alertNote == "1") {
				alert('操作成功!');
	              parent.location.reload(); // 父页面刷新
	                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	                parent.layer.close(index);
	            
			} else if (alertNote == "0") {
			  alert('操作失败，请联系管理员!'); 
			}
		}
		//页面加载时运行
		$(function() { 
	 	    noteAlert();
		});
	
		</script>
    </head>
    
    <body>
        <div class="x-body">
            <form id="form1" class="layui-form" action="<%=path%>/UserServlet?action=add"
			method="post">
			      <div class="layui-form-item">
							<label for="L_email" class="layui-form-label">
							loginname</label>
							<div class="layui-input-inline">
							<input type="text"  id="loginname" name="loginname" 
							 class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">
							<span class="x-red">*</span>
							</div>
							</div>  <div class="layui-form-item">
							<label for="L_email" class="layui-form-label">
							loginpw</label>
							<div class="layui-input-inline">
							<input type="text"  id="loginpw" name="loginpw" 
							 class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">
							<span class="x-red">*</span>
							</div>
							</div>  <div class="layui-form-item">
							<label for="L_email" class="layui-form-label">
							name</label>
							<div class="layui-input-inline">
							<input type="text"  id="name" name="name" 
							 class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">
							<span class="x-red">*</span>
							</div>
							</div>  <div class="layui-form-item">
							<label for="L_email" class="layui-form-label">
							sex</label>
							<div class="layui-input-inline">
							<input type="text"  id="sex" name="sex" 
							 class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">
							<span class="x-red">*</span>
							</div>
							</div>  <div class="layui-form-item">
							<label for="L_email" class="layui-form-label">
							age</label>
							<div class="layui-input-inline">
							<input type="text"  id="age" name="age" 
							 class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">
							<span class="x-red">*</span>
							</div>
							</div>  <div class="layui-form-item">
							<label for="L_email" class="layui-form-label">
							address</label>
							<div class="layui-input-inline">
							<input type="text"  id="address" name="address" 
							 class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">
							<span class="x-red">*</span>
							</div>
							</div>  <div class="layui-form-item">
							<label for="L_email" class="layui-form-label">
							xueli</label>
							<div class="layui-input-inline">
							<input type="text"  id="xueli" name="xueli" 
							 class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">
							<span class="x-red">*</span>
							</div>
							</div>  <div class="layui-form-item">
							<label for="L_email" class="layui-form-label">
							dianhua</label>
							<div class="layui-input-inline">
							<input type="text"  id="dianhua" name="dianhua" 
							 class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">
							<span class="x-red">*</span>
							</div>
							</div>  <div class="layui-form-item">
							<label for="L_email" class="layui-form-label">
							del</label>
							<div class="layui-input-inline">
							<input type="text"  id="del" name="del" 
							 class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">
							<span class="x-red">*</span>
							</div>
							</div>		
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                    </label>
                    <button  class="layui-btn" onClick="comitForm()">
                                                       添加
                    </button>
                </div>
            </form>
        </div>
        
    </body>
</html>