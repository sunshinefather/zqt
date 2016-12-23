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
	var str1="${obj.industryCategory}";
	if(str1){
		var initck=str1.split(",");
		for(_i in initck){
			var $c=$("input[name='pname'][value='"+initck[_i]+"']");
			if($c){
				$c.attr("checked","checked");
			}
		}
	}
	$("#send").click(function() {
		if(!$("#myform").vali('validate')){
			return false;
		}
		var p=[];
		$("input[name='pname']").each(function(){
			if($(this).attr("checked")){
				p.push($(this).val());
			}
		});
		if(p.length <= 0){
		}else{
			$("#industryCategory").val(p.join());
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
    <c:if test="${not empty obj.hospitalId}">
    <input type="button" value="基本信息" class="btn_grey" onclick="alert('当前页面就是基本信息');"/>
    <input type="button" value="协议情况" class="btn_grey" onclick="window.open('${pageContext.request.contextPath}/after/agreement/list?hospitalId=${obj.hospitalId}')"/>
    <input type="button" value="扶持信息" class="btn_grey" onclick="window.open('${pageContext.request.contextPath}/after/obtainSupport/list?hospitalId=${obj.hospitalId}')"/>
    <input type="button" value="产品信息" class="btn_grey" onclick="window.open('${pageContext.request.contextPath}/after/product/list?hospitalId=${obj.hospitalId}')"/>
    </c:if>
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
          <dd class="ysn" style="">
           <label for="height">法人代表联系电话：</label>
           <input style="width:200px;" type="text" name="contactPhone" id="contactPhone" value="${obj.contactPhone}"/>
          </dd>
         <em class="clear"></em>
          <dd class="ysn" style="">
           <label for="height">固定联系人姓名：</label>
           <input style="width:200px;" type="text" name="contact2" id="contact2" value="${obj.contact2}"/>
          </dd>
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
          <em class="clear"></em>
         <dd class="ysn">
         <label for="height">税务登记证号：</label>
          <input style="width:200px;" type="text" id="shuiwuDJ" name="shuiwuDJ"  value="${obj.shuiwuDJ}"/>
          </dd>
         <dd class="ysn">
         <label for="height">组织机构代码：</label>
          <input style="width:200px;" type="text" id="orgNum" name="orgNum"  value="${obj.orgNum}"/>
          </dd>
          <em class="clear"></em>
         <dd class="ysn">
         <label for="height">办公地址：</label>
          <input style="width:200px;" type="text" id="officeAdd" name="officeAdd"  value="${obj.officeAdd}"/>
          </dd>
         <em class="clear"></em>
         <dd class="ysn">
         <label for="height">行业分类：</label>
         <input type="hidden" name="industryCategory" id="industryCategory" value="${obj.industryCategory}"/> 
         	  <span style="width: 100%">
			  <c:forEach items="${listcaty}" var="c" varStatus="st">
			   <span style="padding: 0px 5px;"><input type="checkbox" value="${c }" name="pname" id="p${st.index}" style="width:15px;"/><label for="p${st.index}" style="width:auto;">${c }</label></span>
			  </c:forEach>
			</span>
          </dd>
         <em class="clear"></em>
         <dd class="ysn">
         <label for="height">管理分类：</label>
           <select id="manageCategorry" name="manageCategorry">
            <option value="1" <c:if test="${obj.manageCategorry eq 1}">selected="selected"</c:if>>归上</option>
            <option value="2" <c:if test="${empty obj.manageCategorry}">selected="selected"</c:if> <c:if test="${obj.manageCategorry eq 2}">selected="selected"</c:if>>归下</option>
            <option></option>
           </select>
          </dd>
         <em class="clear"></em>
         <dd class="ysn">
         <label for="height" style="margin-right: 20px;">国土证1</label>
                            办理时间:<input style="width:100px;" type="text" id="guotut1" name="guotut1"  value="${obj.guotut1}" class="Wdate" onclick="WdatePicker({autoPickDate:true,dateFmt:'yyyy-MM-dd'})"/>
                            面积:<input style="width:100px;" type="text" id="guotumj1" name="guotumj1"  value="${obj.guotumj1}"/>平方米&nbsp;&nbsp;&nbsp;&nbsp;
                            土地证号:<input style="width:200px;" type="text" id="guotuNum1" name="guotuNum1"  value="${obj.guotuNum1}"/>
          </dd>
         <em class="clear"></em>
         <dd class="ysn">
         <label for="height" style="margin-right: 20px;">国土证2</label>
                            办理时间:<input style="width:100px;" type="text" id="guotut2" name="guotut2"  value="${obj.guotut2}" class="Wdate" onclick="WdatePicker({autoPickDate:true,dateFmt:'yyyy-MM-dd'})"/>
                            面积:<input style="width:100px;" type="text" id="guotumj2" name="guotumj2"  value="${obj.guotumj2}"/>平方米&nbsp;&nbsp;&nbsp;&nbsp;
                            土地证号:<input style="width:200px;" type="text" id="guotuNum2" name="guotuNum2"  value="${obj.guotuNum2}"/>
          </dd>
         <em class="clear"></em>
         <dd class="ysn">
         <label for="height" style="margin-right: 20px;">国土证3</label>
                            办理时间:<input style="width:100px;" type="text" id="guotut3" name="guotut3"  value="${obj.guotut3}" class="Wdate" onclick="WdatePicker({autoPickDate:true,dateFmt:'yyyy-MM-dd'})"/>
                            面积:<input style="width:100px;" type="text" id="guotumj3" name="guotumj3"  value="${obj.guotumj3}"/>平方米&nbsp;&nbsp;&nbsp;&nbsp;
                            土地证号:<input style="width:200px;" type="text" id="guotuNum3" name="guotuNum3"  value="${obj.guotuNum3}"/>
          </dd>
         <em class="clear"></em>
         <dd class="ysn">
         <label for="height" style="margin-right: 20px;">国土证4</label>
                            办理时间:<input style="width:100px;" type="text" id="guotut4" name="guotut4"  value="${obj.guotut4}" class="Wdate" onclick="WdatePicker({autoPickDate:true,dateFmt:'yyyy-MM-dd'})"/>
                            面积:<input style="width:100px;" type="text" id="guotumj4" name="guotumj4"  value="${obj.guotumj4}"/>平方米&nbsp;&nbsp;&nbsp;&nbsp;
                            土地证号:<input style="width:200px;" type="text" id="guotuNum4" name="guotuNum4"  value="${obj.guotuNum4}"/>
          </dd>
         <em class="clear"></em>
         <dd class="ysn">
         <label for="height" style="margin-right: 20px;">国土证5</label>
                            办理时间:<input style="width:100px;" type="text" id="guotut5" name="guotut5"  value="${obj.guotut5}" class="Wdate" onclick="WdatePicker({autoPickDate:true,dateFmt:'yyyy-MM-dd'})"/>
                            面积:<input style="width:100px;" type="text" id="guotumj5" name="guotumj5"  value="${obj.guotumj5}"/>平方米&nbsp;&nbsp;&nbsp;&nbsp;
                            土地证号:<input style="width:200px;" type="text" id="guotuNum5" name="guotuNum5"  value="${obj.guotuNum5}"/>
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