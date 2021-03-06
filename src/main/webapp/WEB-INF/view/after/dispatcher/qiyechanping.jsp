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
    <p class="title"><a target="_self">企业管理</a> >> <span>企业产品管理</span></p>
<!--功能按键-->
    <div class="daolu_head">
        <button class="add-but tianjia" href="${pageContext.request.contextPath}/api/dispatcher/qiyechanping_xiangqin"><a>添加</a></button>
        <button class="xiugai">修改</button>
        <button class="delete_but">删除</button>
        <button class="chakan_but">查询</button>
    </div>
    <!--内容-->
    <div class="body">
        <table>
            <tr class="table_title">
                <td class="xuanzhe"><input type="checkbox" class="quanxuan"></td>
                <td >图片</td>
                <td>名称</td>
                <td>介绍</td>
            </tr>
            <tbody>
            <tr>
                <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
                <td><img src="${pageContext.request.contextPath}/asset/common/image/bg/lgbg_1.jpg"></td>
                <td>小红</td>
                <td>介绍，描述拉屎地方了撒娇了的风景拉萨的减肥了三</td>
            </tr>
            <tr>
                <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
                <td><img src="${pageContext.request.contextPath}/asset/common/image/bg/lgbg_1.jpg"></td>
                <td>小红</td>
                <td>介绍，描述拉屎地方了撒娇了的风景拉萨的减肥了三</td>
            </tr>
            <tr>
                <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
                <td><img src="${pageContext.request.contextPath}/asset/common/image/bg/lgbg_1.jpg"></td>
                <td>小红</td>
                <td>介绍，描述拉屎地方了撒娇了的风景拉萨的减肥了三</td>
            </tr> <tr>
                <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
                <td><img src="${pageContext.request.contextPath}/asset/common/image/bg/lgbg_1.jpg"></td>
                <td>小红</td>
                <td>介绍，描述拉屎地方了撒娇了的风景拉萨的减肥了三</td>
            </tr> <tr>
                <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
                <td><img src="${pageContext.request.contextPath}/asset/common/image/bg/lgbg_1.jpg"></td>
                <td>小红</td>
                <td>介绍，描述拉屎地方了撒娇了的风景拉萨的减肥了三</td>
            </tr>
            </tbody>
        </table>
    </div>
    <!--    查询框-->
    <div class="chaxun_cont">
        <div class="fangda_guan guanbi_but chaxun_guanbi_but">×</div>
        <p class="daolu_head chaxuun_title">查询</p>

        <form id="myform" class="chaxun_input">
            <table>
                <tr>
                    <td>姓名</td>
                    <td><input title="text"></td>
                </tr>
            </table>
        </form>
        <div class="daolu_head chaxuun_title chaxun_but_kuang">
            <button>查询</button>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/asset/common/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/after/js/zhengqitong.js"></script>
</html>