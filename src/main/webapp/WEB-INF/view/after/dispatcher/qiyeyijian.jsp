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
    <style>
        .yijian{
            margin: 0 10px;
        }
        p{
            margin: 5px 0;
        }
        .yijian_time{
            color: #8f8f8f;
        }
        li{
            padding-left: 20px;
        }
        .yijian_wenti_name{
            color: #0f7fe2;
        }
        textarea{
            width: 90%;
            height: 20px;
            padding-left: 10px;
            border-radius: 3px;
            line-height: 20px;
        }
        .huifu button{
            display: none;
            float: right;
            margin-right: 30px;
            margin-top: 0;
        }
        .yijian{
            border: solid #c1c8d2;
            border-width: 1px 0;
            line-height: 1.33em;
            padding: 8px 5px 8px 0;
            cursor: default;

        }
        .huifu{
            text-align: center;
        }

    </style>
</head>
<body>
<div class="box">
    <p class="title"><a target="_self">园区管理</a> >> <span>政企互动</span> >> <span>企业意见建议</span></p>
<!--功能按键-->
    <div class="daolu_head">
        <button class="add-but">添加</button>
        <button class="daolu_baochun">保存</button>
        <button class="delete_but">删除</button>
        <button class="chakan_but">查询</button>
    </div>
<!--内容-->
    <div class="body">
        <div class="tuandui_title">企业意见建议</div>
        <div class="yijian">
            <p class="yijian_wenti"> <span class="yijian_wenti_name">游戏人生</span> : <span class="yijian_wenti_content">还是皮帽子啊！</span><br>
                <span class="yijian_time">2012-12-12 00:00</span>
            </p>
            <ul class="yijian_huifu">
                <li><p class="yijian_wenti"><span class="yijian_wenti_name">风的眼泪</span> 回复 <span class="yijian_wenti_name">游戏人生</span> : <span class="yijian_wenti_content">还是皮帽子啊！</span><span>附件</span><br>
                    <span class="yijian_time">2012-12-12 00:00</span>
                </p>
                </li>
                <li><p class="yijian_wenti"><span class="yijian_wenti_name">风的眼泪</span> 回复 <span class="yijian_wenti_name">游戏人生</span> : <span class="yijian_wenti_content">还是皮帽子啊！</span><span>附件</span><br>
                    <span class="yijian_time">2012-12-12 00:00</span>
                </p>
                </li> <li><p class="yijian_wenti"><span class="yijian_wenti_name">风的眼泪</span> 回复 <span class="yijian_wenti_name">游戏人生</span> : <span class="yijian_wenti_content">还是皮帽子啊！</span><span>附件</span><br>
                <span class="yijian_time">2012-12-12 00:00</span>
            </p>
            </li>
            </ul>
            <div class="huifu">
                <textarea class="huifu_cont" placeholder="请输入回复内容" ></textarea>
                <p style="clear: both"></p>
                <button class="huifu_but">回复</button>
                <button class="quxiao_but">取消</button>
                <p style="clear: both"></p>
            </div>
        </div>
        <div class="yijian">
            <p class="yijian_wenti"> <span class="yijian_wenti_name">游戏人生</span> : <span class="yijian_wenti_content">还是皮帽子啊！</span><br>
                <span class="yijian_time">2012-12-12 00:00</span>
            </p>
            <ul class="yijian_huifu">
                <li><p class="yijian_wenti"><span class="yijian_wenti_name">风的眼泪</span> 回复 <span class="yijian_wenti_name">游戏人生</span> : <span class="yijian_wenti_content">还是皮帽子啊！</span><span>附件</span><br>
                    <span class="yijian_time">2012-12-12 00:00</span>
                </p>
                </li>
                <li><p class="yijian_wenti"><span class="yijian_wenti_name">风的眼泪</span> 回复 <span class="yijian_wenti_name">游戏人生</span> : <span class="yijian_wenti_content">还是皮帽子啊！</span><span>附件</span><br>
                    <span class="yijian_time">2012-12-12 00:00</span>
                </p>
                </li> <li><p class="yijian_wenti"><span class="yijian_wenti_name">风的眼泪</span> 回复 <span class="yijian_wenti_name">游戏人生</span> : <span class="yijian_wenti_content">还是皮帽子啊！</span><span>附件</span><br>
                <span class="yijian_time">2012-12-12 00:00</span>
            </p>
            </li>
            </ul>
            <div class="huifu">
                <textarea class="huifu_cont" placeholder="请输入回复内容" ></textarea>
                <p style="clear: both"></p>
                <button class="huifu_but">回复</button>
                <button class="quxiao_but">取消</button>
                <p style="clear: both"></p>
            </div>
        </div>
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
<script>
    $(function(){
        $(document).on("focus",".huifu_cont",function(){
            $(this).animate({
                height:"80px"
            },100,function(){
                $(this).siblings("button").css("display","block");
            });
        });
        $(document).on("click",".huifu_but",function(){
            $(this).css("display","none");
            $(this).siblings("button").css("display","none");
            $(this).siblings(".huifu_cont").animate({
                height:"20px"
            },100).val("");
        });
        $(document).on("click",".quxiao_but",function(){
            $(this).css("display","none");
            $(this).siblings("button").css("display","none");
            $(this).siblings(".huifu_cont").animate({
                height:"20px"
            },100).val("");
        })
    })

</script>
</html>