<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tags/page.tld" prefix="pageTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<title>企业协议约定</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description" content="企业协议约定" />
<meta name="keywords" content="企业协议约定" />
<link rel="stylesheet" href="<c:url value="/asset/common/css/layout.css"/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/asset/common/css/module.css"/>" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/combo.select.css"/>
<link rel="stylesheet" href="<c:url value="/asset/common/scripts/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/js/themes/default/default.css" />
<script type="text/javascript" src="<c:url value="/asset/common/js/jquery-1.8.3.min.js"/>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/js/jquery.combo.select.js"></script>
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
	$('#hospitalId').comboSelect();
	$("#send").click(function() {
		if(!$("#myform").vali('validate')){
			return false;
		}
		var u = {};
		$("#myform").find("input,select,textarea")
				.each(
						function(index, element) {
								var $name = $(element).attr('name');
								if ($name)
									u[$name] = $(element).val();
						});
		$.ajax({
			url : $("#myform").attr("action"),
			data : u,
			dataType : 'json',
			type : 'post',
			success : function(resp) {
				if (resp.code > 0) {
					layer.alert('操作成功!',1,function(){
						location = "${pageContext.request.contextPath}/after/agreement/list";
					});
				} else {
					layer.alert('操作失败!'+ (resp.msg == null ? "": resp.msg));
				}
			}
		});
	});
});
</script>
</head>

<body>
<div class="topfixed">
  <div class="current"><s></s><a href="#">企业管理</a> >> <a onclick="history.back()">企业协议约定</a> >><span>编辑</span></div>

  <div class="btnbox">
    <input type="button" value="保 存" class="btn_pink" id="send"  />
    <input type="button" value="取 消" class="btn_grey" onclick="history.back()"/>
    <span>带<b>"*"</b> 的为必填项</span>
  </div>
</div>
<div class="content">
<form action="${pageContext.request.contextPath}/after/agreement/save" method="post" id="myform">
  <input type="hidden"  name="id" value="${obj.id}" id="id"/>
  <div class="">
    <div class="right_bk" style="width:98%;">
      <div>
        <dl class="cont">
          <dd class="ysn" style="">
           <label for="hospitalId"><b>*</b>企业名称：</label>
           <select name="hospitalId" id="hospitalId" style="display: inline-block;width: 250px;">
				<c:forEach items="${orgList}" var="c">
            		<option value="${c.hospitalId }" <c:if test="${obj.hospitalId eq c.hospitalId}">selected="selected"</c:if>>${c.hospitalName }</option>
            	</c:forEach>
		   </select>
          </dd>
         <em class="clear"></em>
          <dd class="ysn" style="">
           <label for="signedTime"><b>*</b>签订时间：</label>
           <input require="require" style="width:100px;" type="text" name="signedTime" id="signedTime" value="${obj.signedTime}" class="Wdate" onclick="WdatePicker({autoPickDate:true,dateFmt:'yyyy-MM-dd'})"/>
          </dd>
         <em class="clear"></em>
          <dd class="ysn" style="">
           <label for="signedBody"><b>*</b>签约主体：</label>
           <input require="require" style="width:200px;" name="signedBody" id="signedBody" value="${obj.signedBody}"/>
          </dd>
         <em class="clear"></em>
          <dd class="ysn" style="">
           <label for="invest"><b>*</b>总投资：</label>
           <input require="require" type="number" style="width:200px;" name="invest" id="invest" value="${obj.invest}"/>  元
          </dd>
         <em class="clear"></em>
          <dd class="ysn" style="">
           <label for="userArea"><b>*</b>用地面积：</label>
           <input require="require" type="number" style="width:200px;" name="userArea" id="userArea" value="${obj.userArea}"/>  m<sup>2</sup>
          </dd>
         <em class="clear"></em>
          <dd class="ysn" style="">
           <label for="productionContent"><b>*</b>生产内容：</label>
           <input require="require" style="width:400px;" name="productionContent" id="productionContent" value="${obj.productionContent}"/>
          </dd>
          <em class="clear"></em>
          <dd class="ysn" style="">
           <label for="dgDate"><b>*</b>动工时间：</label>
           <input require="require" style="width:100px;" type="text" name="dgDate" id="dgDate" value="${obj.dgDate}" class="Wdate" onclick="WdatePicker({autoPickDate:true,dateFmt:'yyyy-MM-dd'})"/>
          </dd>
          <em class="clear"></em>
          <dd class="ysn" style="">
           <label for="tcDate"><b>*</b>投产时间：</label>
           <input require="require" style="width:100px;" type="text" name="tcDate" id="tcDate" value="${obj.tcDate}" class="Wdate" onclick="WdatePicker({autoPickDate:true,dateFmt:'yyyy-MM-dd'})"/>
          </dd>
          <em class="clear"></em>
          <dd class="ysn" style="">
           <label for="specialSupport">特殊扶持：</label>
           <input style="width:400px;" name="specialSupport" id="specialSupport" value="${obj.specialSupport}"/>
          </dd>
        </dl>
      </div>
    </div>
  </div>
  </form>
</div>
</body>
</html>
