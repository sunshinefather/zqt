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
    <p class="title"><a target="_self">企业管理</a> > <a target="_top" href="qiyefazhan.html" class="dangqian">企业发展</a> > 企业发展详情
    </p>

    <div class="daolu_head">
        <button class="daolu_baochun">保存</button>
        <button class="fanhui"><a>返回</a></button>
    </div>
    <form class="xieyi_form">
        <div class="xieyi_form_kuang">
            <table>
                <tr>
                    <td>企业名称</td>
                    <td><input type="text" readonly="readonly"value="迈迪克斯"></td>
                </tr>
                <tr>
                    <td>统计时间</td>
                    <td><input type="month"></td>
                </tr>
                <tr>
                    <td>企业产值</td>
                    <td><input type="text"> 千元</td>
                </tr>
                <tr>
                    <td>企业销售</td>
                    <td><input type="text"> 千元</td>
                </tr>

                <tr>
                    <td>企业利润</td>
                    <td><input type="text"> 千元</td>
                </tr>
                <tr>
                    <td>企业税收</td>
                    <td><input type="text"> 千元</td>
                </tr>
                <tr>
                <td>用水数据</td>
                <td><input type="text"> 千元</td>
            </tr>
                <tr>
                    <td>用电数据</td>
                    <td><input type="text"> 千元</td>
                </tr>
                <tr>
                    <td>固定资产投资数据</td>
                    <td><input type="text"> 千元</td>
                </tr>
                <tr>
                    <td>用气数据</td>
                    <td><input type="text"> 千元</td>
                </tr>
                <tr>
                    <td>出口创汇</td>
                    <td><input type="text"> 千元</td>
                </tr>
            </table>
        </div>
    </form>
    <div class="fangda_div">
        <div class="fangda_guan">×</div>
        <img src="../../img/Z%7BUQS2VB1EBVD~(L~%25T)R%25J.png" class="famgda_img">
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/asset/common/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/after/js/zhengqitong.js"></script>
<script>
    $(".donggong_time").on("change", function () {
        if ($(this).val() > $(".touchan_time").val()) {
            $(".touchan_time").val($(this).val())
        }
    });
    $(".touchan_time").on("change", function () {
        if ($(this).val() < $(".donggong_time").val()) {
            $(".donggong_time").val($(this).val())
        }
    });
    function tupian(deleClass,tdClass,shangchuanButClass,inputClass,deleclass,shangchuan) {
        $(document).on("click",deleClass, function () {
            $(tdClass).html("<div class='daolu_kuangimg'style='border: 1px solid #cccccc'><span class='shangchuan-but "+shangchuan+"'>+</span></div>")
        });
        $(document).on("click", shangchuanButClass, function () {
            $(inputClass).click();
        });
        $(document).on("change", inputClass, function () {
            var files = this.files;
            var mingurl = window.URL.createObjectURL(files[0]);
            $(tdClass).html("<div class='daolu_kuangimg'><span class='dele_imgs "+deleclass+"'><img src='../../img/shanchu.png' alt='删除图片'></span><img 'alt='鸟瞰图' src='" + mingurl + "'class='daolu_imgs'/></div>");
            $(this).val("")
        });
    }

    tupian(".niaolan",".niao_kuang",".shangchuan-niaobut",".niaoimg_url","niaolan","shangchuan-niaobut");
    tupian(".zongping",".zong_kuang",".shangchuan-zongpingbut",".zongpinimg_url","zongping","shangchuan-zongpingbut");
</script>
</html>