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
        input[type=month]{
            width: 200px;
            height: 25px;
            border-radius: 3px;
            border: 1px solid #a0a0a0;
        }
    </style>
</head>
<body>
<div class="box">
    <p class="title"><a target="_self">企业管理</a> ><span>企业发展</span></p>
    <!--功能按键-->
    <div class="daolu_head">
        <button class="add-but tianjia" href="qiyefazhan_xiangqing.html"><a>添加</a></button>
        <button class="xiugai">修改</button>
        <button class="delete_but">删除</button>
        <button class="chakan_but">查询</button>
    </div>
    <!--内容-->
    <div class="body">
        <table>
            <tr class="table_title">
                <td class="xuanzhe"><input type="checkbox" class="quanxuan"></td>
                <td>企业名称</td>
                <td>统计时间</td>
                <td>企业产值<br>(千元）</td>
                <td>企业销售<br>((千元）</td>
                <td>企业利润<br>(千元）</td>
                <td>企业税收<br>(千元）</td>
                <td>用水数据<br>(千元）</td>
                <td>用电数据<br>(千元）</td>
                <td>用气数据<br>(千元）</td>
                <td>固定资产投资数据<br>(千元)</td>
                <td>出口创汇<br>(千元）</td>
            </tr>
            <tbody>
            <tr>
                <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
                <td>迈迪克斯</td>
                <td>2012-12</td>
                <td>120</td>
                <td>110</td>
                <td>119</td>
                <td>500</td>
                <td>500</td>
                <td>300</td>
                <td>800</td>
                <td>150</td>
                <td>1200</td>
            </tr>
            <tr>
                <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
                <td>迈迪克斯</td>
                <td>2012-12</td>
                <td>120</td>
                <td>110</td>
                <td>119</td>
                <td>500</td>
                <td>500</td>
                <td>300</td>
                <td>800</td>
                <td>150</td>
                <td>1200</td>
            </tr>
            <tr>
                <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
                <td>迈迪克斯</td>
                <td>2012-12</td>
                <td>120</td>
                <td>110</td>
                <td>119</td>
                <td>500</td>
                <td>500</td>
                <td>300</td>
                <td>800</td>
                <td>150</td>
                <td>1200</td>
            </tr>
            <tr>
                <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
                <td>迈迪克斯</td>
                <td>2012-12</td>
                <td>120</td>
                <td>110</td>
                <td>119</td>
                <td>500</td>
                <td>500</td>
                <td>300</td>
                <td>800</td>
                <td>150</td>
                <td>1200</td>
            </tr>
            <tr>
                <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
                <td>迈迪克斯</td>
                <td>2012-12</td>
                <td>120</td>
                <td>110</td>
                <td>119</td>
                <td>500</td>
                <td>500</td>
                <td>300</td>
                <td>800</td>
                <td>150</td>
                <td>1200</td>
            </tr>
            <tr>
                <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
                <td>迈迪克斯</td>
                <td>2012-12</td>
                <td>120</td>
                <td>110</td>
                <td>119</td>
                <td>500</td>
                <td>500</td>
                <td>300</td>
                <td>800</td>
                <td>150</td>
                <td>1200</td>
            </tr>
            <tr>
                <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
                <td>迈迪克斯</td>
                <td>2012-12</td>
                <td>120</td>
                <td>110</td>
                <td>119</td>
                <td>500</td>
                <td>500</td>
                <td>300</td>
                <td>800</td>
                <td>150</td>
                <td>1200</td>
            </tr>
            <tr>
                <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
                <td>迈迪克斯</td>
                <td>2012-12</td>
                <td>120</td>
                <td>110</td>
                <td>119</td>
                <td>500</td>
                <td>500</td>
                <td>300</td>
                <td>800</td>
                <td>150</td>
                <td>1200</td>
            </tr>
            <tr>
                <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
                <td>迈迪克斯</td>
                <td>2012-12</td>
                <td>120</td>
                <td>110</td>
                <td>119</td>
                <td>500</td>
                <td>500</td>
                <td>300</td>
                <td>800</td>
                <td>150</td>
                <td>1200</td>
            </tr>
            <tr>
                <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
                <td>迈迪克斯</td>
                <td>2012-12</td>
                <td>120</td>
                <td>110</td>
                <td>119</td>
                <td>500</td>
                <td>500</td>
                <td>300</td>
                <td>800</td>
                <td>150</td>
                <td>1200</td>
            </tr>
            <tr>
                <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
                <td>迈迪克斯</td>
                <td>2012-12</td>
                <td>120</td>
                <td>110</td>
                <td>119</td>
                <td>500</td>
                <td>500</td>
                <td>300</td>
                <td>800</td>
                <td>150</td>
                <td>1200</td>
            </tr>
            <tr>
                <td class="xuanzhe"><input type="checkbox" class="input_xuanzhong"></td>
                <td>迈迪克斯</td>
                <td>2012-12</td>
                <td>120</td>
                <td>110</td>
                <td>119</td>
                <td>500</td>
                <td>500</td>
                <td>300</td>
                <td>800</td>
                <td>150</td>
                <td>1200</td>
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