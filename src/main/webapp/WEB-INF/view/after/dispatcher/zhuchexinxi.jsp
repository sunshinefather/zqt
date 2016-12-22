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
    <p class="title"><a target="_self">企业信息管理</a> >> <span>注册信息</span></p>
    <!--功能按键-->
    <div class="daolu_head">
        <button class="add-but tianjia"href="zhuchexinxi_xiangqin.html">添加</button>
        <button class="xiugai">修改</button>
        <button class="delete_but">删除</button>
        <button class="chakan_but">查询</button>
    </div>
    <!--内容-->
    <div class="body">
        <table>
            <tr class="table_title">
                <td class="xuanzhe"><input type="checkbox" class="quanxuan"></td>
                <td class="names">法人代表</td>
                <td>营业执照号</td>
                <td>注册地址</td>
                <td>注册资本（元）</td>
                <td>注册时间</td>
            </tr>
            <tbody>
            <tr>
                <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
                <td class="names">小明</td>
                <td>9090980</td>
                <td>四川省成都市高新区XX路xxx号xx栋xxx楼xxxx</td>
                <td>100000</td>
                <td>2016-12-12 00:00:00</td>
            </tr>
            <tr>
                <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
                <td class="names">小明</td>
                <td>9090980</td>
                <td>四川省成都市高新区XX路xxx号xx栋xxx楼xxxx</td>
                <td>100000</td>
                <td>2016-12-12 00:00:00</td>
            </tr><tr>
            <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
            <td class="names">小明</td>
            <td>9090980</td>
            <td>四川省成都市高新区XX路xxx号xx栋xxx楼xxxx</td>
            <td>100000</td>
            <td>2016-12-12 00:00:00</td>
        </tr><tr>
            <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
            <td class="names">小明</td>
            <td>9090980</td>
            <td>四川省成都市高新区XX路xxx号xx栋xxx楼xxxx</td>
            <td>100000</td>
            <td>2016-12-12 00:00:00</td>
        </tr><tr>
            <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
            <td class="names">小明</td>
            <td>9090980</td>
            <td>四川省成都市高新区XX路xxx号xx栋xxx楼xxxx</td>
            <td>100000</td>
            <td>2016-12-12 00:00:00</td>
        </tr><tr>
            <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
            <td class="names">小明</td>
            <td>9090980</td>
            <td>四川省成都市高新区XX路xxx号xx栋xxx楼xxxx</td>
            <td>100000</td>
            <td>2016-12-12 00:00:00</td>
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