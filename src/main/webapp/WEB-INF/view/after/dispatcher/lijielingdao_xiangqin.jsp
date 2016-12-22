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
    <p class="title"><a class="dangqian"target="_self" href="lijielingdao.html">历届领导班子管理</a> > <span>历届领导班子管理详情</span></p>
    <div class="daolu_head">
        <button class="daolu_baochun">保存</button>
        <button class="fanhui"><a>返回</a></button>
    </div>
    <form class="xieyi_form">
        <div class="xieyi_form_kuang">
            <table>
                <tr>
                    <td>名称</td>
                    <td><input type="text"></td>
                </tr>
                <tr>
                    <td>介绍</td>
                    <td><input type="text"> </td>
                </tr>
            </table>
        </div>
    </form>
</div>
</body>
<script src="${pageContext.request.contextPath}/asset/common/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/after/js/zhengqitong.js"></script>
</html>