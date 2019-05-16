<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>
    </title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <link rel="stylesheet" href="<%=path%>/css/x-admin.css" media="all"/>
    <link rel="stylesheet" href="<%=path%>/css/pag.css" media="all"/>
    <script src="<%=path%>/lib/layui/layui.js" charset="utf-8"></script>
    <script src="<%=path%>/js/x-layui.js" charset="utf-8"></script>
    <script src="<%=path%>/js/jquery2.js" charset="utf-8"></script>
    <script src="<%=path%>/js/js.js" charset="utf-8"></script>
    <script>
        //layui初始化
        layui.use(['laydate', 'element', 'laypage', 'layer'], function () {
            $ = layui.jquery;//jquery
            lement = layui.element();//面包导航
            laypage = layui.laypage;//分页
            layer = layui.layer;//弹出层
        });

        //添加
        function add() {
            var url = "<%=path%>/admin/news/news_Add.jsp";
            x_admin_show("添加", url, 1000, 660);
        }
        //查看
        function view(id) {
            var url = "<%=path%>/NewsServlet?action=view&id=" + id;
            x_admin_show("查看", url, 1000, 660);
        }
        //删除
        function del(obj, id) {
            layer.confirm('确认要删除吗？', function (index) {
                location.href = "<%=path%>/NewsServlet?action=delete&ids=" + id;
            });
        }
        //批量删除
        function plsc() {
            layer.confirm('确认要删除吗？', function (index) {
                var ids;
                $("input[name='selected']:checked").each(function () { // 遍历选中的checkbox
                    if (ids != null) {
                        ids = ids + "," + $(this).val();
                    }
                    else {
                        ids = $(this).val();  // 获取checkbox所在行的顺序
                    }

                });
                if (ids == null) {
                    layer.msg('请选择删除项!', {icon: 5, time: 1000});
                }
                else {
                    location.href = "<%=path%>/NewsServlet?action=delete&ids=" + ids;
                }

            });
        }

        //编辑
        function edit(id) {
            var url = "<%=path%>/NewsServlet?action=editinit&id=" + id;
            x_admin_show("编辑", url, 1000, 660);
        }
        $('.tablelist tbody tr:odd').addClass('odd');

        //翻页js
        function skip(topage) {
            location.href = "<%=path%>/NewsServlet?action=page&toPage=" + topage;
        }
        //跳转js
        function skip1() {
            if (checkRate($('#jumpnum').val())) {
                location.href = "<%=path%>/NewsServlet?action=page&toPage=" + $('#jumpnum').val();
            }
        }
        //判断输入的页码是否为数字
        function checkRate(aa) {
            var re = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
            if (!re.test(aa)) {
                //alert("请输入数字");
                layer.msg('请输入数字!', {icon: 5, time: 1000});
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
        $(function () {
            noteAlert();
        });
    </script>
</head>
<body>
<div class="x-nav"><span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>图片新闻</cite></a> </span></div>
<div class="x-body">
    <div class="tools">
        <ul class="toolbar">
            <li onclick="plsc()"><span><img src="<%=path%>/images/t03.png"/></span>批量删除</li>
            <li onclick="add()"><span><img src="<%=path%>/images/t01.png"/></span>添加</li>
        </ul>
        <span class="x-right" style="line-height:25px">共有<font style="font-weight:bolder;color:red">&nbsp;${page.total}&nbsp;</font>条数据，共<font
                style="font-weight:bolder;color:red">&nbsp;${page.pages}&nbsp;</font>页，当前是第<font
                style="font-weight:bolder;color:red">&nbsp;${page.pageNumber}&nbsp;</font>页</span></xblock>
    </div>
    <table class="tablelist">
        <thead>
        <tr>
            <th>
                <input onclick="selectAll()" type="checkbox" name="controlAll" style="" id="controlAll"/>
            </th>

            <th>
                序号
            </th>
            <th>图片新闻标题</th>
            <th>图片新闻缩略图</th>

            <th>创建时间</th>


            <th>
                操作
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="list" items="${page.list}" varStatus="status">
            <tr>
                <td>
                    <input type="checkbox" value="${list.ID}" name="selected">
                </td>

                <td>
                        ${ status.index + 1}
                </td>
                <td>${list.TITLE}</td>
                <td><img src="<%=path%>/upload/${list.IMGPATH}" alt="" width="56" height="56"/></td>

                <td>${list.CREATETIME}</td>


                <td class="td-manage">

                    <a title="编辑" href="javascript:;" onclick="edit('${list.ID}')"
                       class="ml-5" style="text-decoration:none">
                        <i class="layui-icon">&#xe642;</i>
                    </a>

                    <a title="删除" href="javascript:;" onclick="del(this,' ${list.ID}')"
                       style="text-decoration:none">
                        <i class="layui-icon">&#xe640;</i>
                    </a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <div class="page" v-show="show">
        <div class="pagelist"><span class="jump" onclick="skip('1')">首页</span>
            <c:if test="${page.pageNumber>1}">
                <span class="jump" onclick="skip('${page.pageNumber-1}')">上一页</span>
            </c:if>
            <c:if test="${page.pageNumber<page.pages}">
                <span class="jump" onclick="skip('${page.pageNumber + 1}')">下一页</span>
            </c:if>
            <span class="jump" onclick="skip('${page.pages}')">尾页</span><span class="jumppoint">跳转到：</span> <span
                    class="jumpinp">
     <input type="text" id="jumpnum" name="jumpnum"/>
    </span> <span class="jump gobtn" onclick="skip1()">GO</span></div>
    </div>
</div>
</body>
</html>