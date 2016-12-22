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
</head>
<body>
<div class="box">
    <p class="title"><span>企业信息管理</span> >>
        <a class="dangqian"target="_self" href="lianxixinxi.html">联系信息</a> >> <span>添加信息</span></p>
    <div class="daolu_head">
        <button class="daolu_baochun">保存</button>
        <button class="fanhui"><a href="#" onClick="javascript :history.back(-1);">返回</a></button>
    </div>
    <form class="xieyi_form">
        <div class="xieyi_form_kuang">
            <table>
                <tr>
                    <td>法人代表姓名</td>
                    <td><input type="text"></td>
                </tr>
                <tr>
                    <td>法人代表联系电话</td>
                    <td><input type="text"></td>
                </tr>
                <tr>
                    <td>固定联系人姓名</td>
                    <td><input type="text"></td>
                </tr>
                <tr>
                    <td>固定联系人电话</td>
                    <td><input type="text"></td>
                </tr>
            </table>
        </div>
    </form>
</div>
</body>
<script src="${pageContext.request.contextPath}/asset/common/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/after/js/zhengqitong.js"></script>
</html>