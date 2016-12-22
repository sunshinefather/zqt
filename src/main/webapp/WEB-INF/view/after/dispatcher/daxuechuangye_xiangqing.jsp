<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/kuangjia.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/shui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/content.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/xiangqing.css">
</head>
<body>
<div class="box">
    <p class="title"><a target="_self">企业管理</a> > <a target="_top" href="daxuechuangye.html" class="dangqian">大学生创业政策管理</a> > 大学生创业政策管理详情
    </p>

    <div class="daolu_head">
        <button class="daolu_baochun">保存</button>
        <button class="fanhui"><a href="#" onClick="javascript :history.back(-1);">返回</a></button>
    </div>
    <form class="xieyi_form">
        <div class="xieyi_form_kuang">
            <table>
                <tr>
                    <td>名称</td>
                    <td><input type="text"></td>
                </tr>
                <tr>
                    <td>内容</td>
                    <td><textarea></textarea></td>
                </tr>
                <tr>
                    <td>附件</td>
                    <td><input type="file" class="wenjian_file"> <span class="delete_file" title="删除文件">×</span></td>
                </tr>
            </table>
        </div>
    </form>
</div>
</body>
<script src="${pageContext.request.contextPath}/asset/common/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/after/js/zhengqitong.js"></script>

</html>