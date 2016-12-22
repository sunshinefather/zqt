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
    <p class="title"><span>企业信息管理</span> >
        <a class="dangqian" target="_self" href="jibenxinxi.html">基本信息</a> > <span>基本信息详情</span></p>

    <div class="daolu_head">
        <button class="daolu_baochun">保存</button>
        <button class="fanhui"><a href="#" onClick="javascript :history.back(-1);">返回</a></button>
    </div>
    <form class="xieyi_form">
        <div class="xieyi_form_kuang">
            <table>
                <tr>
                    <td>企业名称</td>
                    <td><input type="text"></td>
                </tr>
                <tr>
                    <td>行业类别</td>
                    <td><input type="text"></td>
                </tr>
                <tr>
                    <td>注册时间</td>
                    <td><input type="date"></td>
                </tr>
                <tr>
                    <td>注册资本</td>
                    <td><input type="text"></td>
                </tr>
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
                <tr>
                    <td>注册地址</td>
                    <td><input type="text"></td>
                </tr>
                <tr>
                    <td>营业执照号</td>
                    <td><input type="text"></td>
                </tr>
                <tr>
                    <td>是否高新企业</td>
                    <td><select>
                        <option>是</option>
                        <option>否</option>
                    </select></td>
                </tr>
                <tr>
                    <td>高新企业获得时间</td>
                    <td><input type="date" class="donggong_time"></td>
                </tr>
                <tr>
                    <td>高新企业获得时间</td>
                    <td><input type="date" class="touchan_time"></td>
                </tr>
                <tr>
                    <td>用工人数</td>
                    <td><input type="text"></td>
                </tr>
                <tr>
                    <td>技术专利</td>
                    <td><input type="text"></td>
                </tr>
                <tr>
                    <td>内容</td>
                    <td><textarea></textarea></td>
                </tr>
            </table>
        </div>
        <div>
            富文本
        </div>
    </form>

    <div class="fangda_div">
        <div class="fangda_guan">×</div>
        <img src="../../../img/Z%7BUQS2VB1EBVD~(L~%25T)R%25J.png" class="famgda_img">
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/asset/common/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/after/js/zhengqitong.js"></script>
<script>
    $(".donggong_time").on("change",function(){
        if($(this).val()>$(".touchan_time").val()){
            $(".touchan_time").val($(this).val())
        }
    });
    $(".touchan_time").on("change",function(){
        if($(this).val()<$(".donggong_time").val()){
            $(".donggong_time").val($(this).val())
        }
    })
</script>
</html>