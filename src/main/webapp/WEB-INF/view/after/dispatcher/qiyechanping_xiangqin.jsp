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

    <p class="title"><a class="dangqian"target="_self" href="qiyechanping.html">企业产品管理</a> > <span>企业产品管理详情</span></p>
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
                    <td><textarea></textarea></td>
                </tr>

                   <tr>
                       <td>图片
                           <input type="file" style="display: none" class="zongpinimg_url">
                       </td>
                       <td class="zong_kuang">
                           <div class='daolu_kuangimg'style='border: 1px solid #cccccc'>
                               <span class='shangchuan-but shangchuan-zongpingbut'>+</span>
                           </div>
                       </td>
                   </tr>
            </table>
        </div>
    </form>
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
    tupian(".zongping",".zong_kuang",".shangchuan-zongpingbut",".zongpinimg_url","zongping","shangchuan-zongpingbut");
</script>
</html>