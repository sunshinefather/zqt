<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tags/page.tld" prefix="pageTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<title>掌宇通系统管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description" content="掌宇通系统管理" />
<meta name="keywords" content="掌宇通系统管理" />
<link rel="stylesheet" href="<c:url value="/asset/common/css/layout.css"/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/asset/common/css/module.css"/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/asset/common/scripts/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/js/themes/default/default.css" />
<script type="text/javascript" src="<c:url value="/asset/common/js/jquery-1.8.3.min.js"/>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/js/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/layer/layer.js"/>"></script>
<script src="<c:url value="/asset/common/scripts/zTree_v3/js/jquery.ztree.core-3.5.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/js/tools.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/asset/common/scripts/vali/css/vali.css"/>"/>
<script type="text/javascript"  src="<c:url value="/asset/common/scripts/vali/vali.js"/>"/></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
b{
color:red;
}
.cont label {
width: 120px;
}
</style>
<script type="text/javascript">
$(function(){
	$("#send").click(function() {
		if(!$("#myform").vali('validate')){
			return false;
		}
		$("#myform").submit();
	});
});

KindEditor.ready(function(K) {
	var editor = K.create('#content', {
        resizeType : 1,
        allowPreviewEmoticons : false,
        allowImageUpload : true,
        readonlyMode : false,
        uploadJson:"${pageContext.request.contextPath}/public/upload/",
        afterBlur : function() {
            this.sync();
        }
    });
});
</script>
</head>

<body>
<div class="topfixed">
  <div class="current"><s></s><a href="#">企业信息管理</a> >> <a onclick="history.back()">基本信息</a> >><span>编辑</span></div>

  <div class="btnbox">
    <input type="button" value="保 存" class="btn_pink" id="send"  />
    <input type="button" value="取 消" class="btn_grey" onclick="history.back()"/>
    <span>带<b>"*"</b> 的为必填项</span>
  </div>
</div>
<div class="content">
<form action="${pageContext.request.contextPath}/after/hospital/save" method="post" id="myform">
<input type="hidden"  name="hospitalId" value="${obj.hospitalId}" id="hospitalId"/>
  <div class="">
    <div class="right_bk" style="width:98%;">
      <div>
        <dl class="cont">
          <dd class="ysn" style="">
           <label for="height"><b>*</b>企业名称：</label>
           <input style="width:250px;" type="text" name="hospitalName" id="hospitalName" require="require" value="${obj.hospitalName}"/>
          </dd>
         <em class="clear"></em>
          <dd class="ysn" style="">
           <label for="height">行业类别：</label>
           <input style="width:200px;" type="text" name="hospitalType" id="hospitalType" value="${obj.hospitalType}"/>
          </dd>
         <em class="clear"></em>
          <dd class="ysn" style="">
           <label for="height">注册时间：</label>
           <input style="width:100px;" type="text" name="createDate" id="createDate" value="${obj.createDate}" class="Wdate" onclick="WdatePicker({autoPickDate:true,dateFmt:'yyyy-MM-dd'})"/>
          </dd>
         <em class="clear"></em>
          <dd class="ysn" style="">
           <label for="height">注册资本：</label>
           <input style="width:200px;" type="number" name="registeredCapital" id="registeredCapital" value="${obj.registeredCapital}"/>万元
          </dd>
         <em class="clear"></em>
          <dd class="ysn" style="">
           <label for="height">法人代表姓名：</label>
           <input style="width:200px;" type="text" name="contact" id="contact" value="${obj.contact}"/>
          </dd>
         <em class="clear"></em>
          <dd class="ysn" style="">
           <label for="height">法人代表联系电话：</label>
           <input style="width:200px;" type="text" name="contactPhone" id="contactPhone" value="${obj.contactPhone}"/>
          </dd>
         <em class="clear"></em>
          <dd class="ysn" style="">
           <label for="height">固定联系人姓名：</label>
           <input style="width:200px;" type="text" name="contact2" id="contact2" value="${obj.contact2}"/>
          </dd>
         <em class="clear"></em>
          <dd class="ysn" style="">
           <label for="height">固定联系人电话：</label>
           <input style="width:200px;" type="text" name="contactPhone2" id="contactPhone2" value="${obj.contactPhone2}"/>
          </dd>
          <em class="clear"></em>
         <dd class="ysn">
         <label for="height">注册地址：</label>
          <input style="width:229px;" type="text" id="address" name="address"  value="${obj.address}"/>
          </dd>
           <em class="clear"></em>
         <dd class="ysn">
         <label for="height">营业执照号：</label>
          <input style="width:229px;" type="text" id="bln" name="bln"  value="${obj.bln}"/>
          </dd>
          <em class="clear"></em>
           <dd class="ysn">
           <label for="contactPhone">是否高新企业：</label>
             <select name="isHighSalary" id="isHighSalary">
               <option value="否" <c:if test="${obj.isHighSalary eq '否'}">selected="selected"</c:if>>否</option>
               <option value="是" <c:if test="${obj.isHighSalary eq '是'}">selected="selected"</c:if>>是</option>
             </select>
          </dd>
          <em class="clear"></em>
          <dd class="ysn">
           <label for="height">高新企业获得时间：</label>
           <input style="width:100px;" type="text" name="highSalaryStart" id="highSalaryStart" value="${obj.highSalaryStart}" class="Wdate" onclick="WdatePicker({autoPickDate:true,dateFmt:'yyyy-MM-dd'})"/>
          </dd>
          <em class="clear"></em>
          <dd class="ysn">
           <label for="height">高新企业结束时间：</label>
           <input style="width:100px;" type="text" name="highSalaryEnd" id="highSalaryEnd" value="${obj.highSalaryEnd}" class="Wdate" onclick="WdatePicker({autoPickDate:true,dateFmt:'yyyy-MM-dd'})"/>
          </dd>
         <em class="clear"></em>
         <dd class="ysn">
         <label for="height">用工人数：</label>
          <input style="width:200px;" type="number" id="peoples" name="peoples"  value="${obj.peoples}"/>人
          </dd>
          <em class="clear"></em>
         <dd class="ysn">
         <label for="height">技术专利：</label>
          <input style="width:200px;" type="text" id="technicalPatent" name="technicalPatent"  value="${obj.technicalPatent}"/>
          </dd>
        </dl>
      </div>
    </div>
  </div>
  <em class="clear"></em>
   <ul class="tab">
   	<span style="color:#FF3368;margin-left:10px;"><strong>企业简介</strong></span>
  </ul>
  <div style="display:block; padding: 5px;" class="tabcont" >
       <textarea class="lingyu_text" name="introduction" id="content" style="float:left;width:98%; height:210px;margin-top:0px; ">${obj.introduction}</textarea>
  </div>
  </form>
</div>
</body>
</html>
