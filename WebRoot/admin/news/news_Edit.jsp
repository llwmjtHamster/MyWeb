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
				<script type="text/javascript" src="<%=path%>/ckeditor/ckeditor.js"></script> 
				
		         <script type="text/javascript" src="<%=path%>/alert/popwin.js"></script>   
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
            <form id="form1" class="layui-form" action="<%=path%>/NewsServlet?action=editSave"
			method="post">
			  <input type="hidden" name="id" id="id" value='<c:out value="${news.id}"></c:out>'/>
			      <div class="layui-form-item">
							<label for="L_email" class="layui-form-label">
								标题</label>
							<div class="layui-input-inline">
							<input type="text"  id="title" name="title"   value="${news.title}" lay-verify="required"
							 class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">
							<span class="x-red">*</span>
							</div>
							</div>  <div class="layui-form-item">
							<label for="L_email" class="layui-form-label">
								缩略图</label>
							<div class="layui-input-inline">
							<input type="text"  id="imgpath" name="imgpath"  value="${news.imgpath}" lay-verify="required"
							 class="layui-input">	<input type="button" value="选择图片" onclick="openDLG()"/> 
							</div>
							<div class="layui-form-mid layui-word-aux">
							<span class="x-red">*</span>
							</div>
							</div>  
							<div class="layui-form-item">
							<label for="L_email" class="layui-form-label">
								内容</label>
							<div class="layui-input-inline" style="width:800px;hieght:600px" >
								<textarea id="content" name="content" > ${news.content}</textarea>
							</div>
							 
							 	
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                    </label>
                   <button class="layui-btn" lay-filter="tijiao" lay-submit="">    更新</button>
                                                    
                     
                </div>
            </form>
        </div>
        
    </body>
</html>
<script>
 window.onload = function() {
		CKEDITOR.replace("content");	 
	  }
	 function openDLG() {
	   popWin.showWin("400","150","添加缩略图","<%=path%>/common/imageUpload.jsp");
  
	}

 window.onload = function() {
	 CKEDITOR.replace('content',{filebrowserUploadUrl : '${path}/ckeditor/uploader?Type=File',
		 filebrowserImageUploadUrl : '${path}/ckeditor/uploader?Type=Image',
		 filebrowserFlashUploadUrl : '${path}/ckeditor/uploader?Type=Flash'
	 });
 }
</script>