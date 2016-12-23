<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tags/page.tld" prefix="pageTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<title>企业产品信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description" content="企业产品信息" />
<meta name="keywords" content="企业产品信息" />
<link rel="stylesheet" href="<c:url value="/asset/common/css/layout.css"/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/asset/common/css/module.css"/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/asset/common/scripts/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/combo.select.css"/>
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
	$('#hospitalId1').comboSelect();
	$("#send").click(function() {
		if(!$("#myform").vali('validate')){
			return false;
		}
		var u = {"introduce":$("#introduce").val()};
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
						location = "${pageContext.request.contextPath}/after/product/list";
					});
				} else {
					layer.alert('操作失败!'+ (resp.msg == null ? "": resp.msg));
				}
			}
		});
	});
});
var editor = null;
KindEditor.ready(function(K) {			
	  var logoEditor = K.editor({
	        uploadJson:"${pageContext.request.contextPath}/public/upload/",
	        formatUploadUrl : true,
	        afterUpload : function(url, data, name) {//返回的url
	        console.log(data);
	            if (data != null && data.error === 0) {
	                $("#fileName").data("url",data.url);
	                //$('#logos').val(data.imageId);
	                $("#logo").val(data.url);
	            } else {
	                alert("文件上传错误");
	            }
	        }
	    });
	  
	    K('#setMusic').click(function() {
	    	logoEditor.loadPlugin('image', function() {
	    		logoEditor.plugin.imageDialog({
	    			    showRemote : false,
						clickFn : function(url, title,data) {
							$("#fileNameUrl").attr("href",$("#fileName").data("url"));
							$("#fileName").attr("src",$("#fileName").data("url"));
							logoEditor.hideDialog();
						}
	                 });
	             });
	   });
	});
</script>
</head>

<body>
<div class="topfixed">
  <div class="current"><s></s><a href="#">企业管理</a> >> <a onclick="history.back()">企业产品管理</a> >><span>编辑</span></div>

  <div class="btnbox">
    <input type="button" value="保 存" class="btn_pink" id="send"  />
    <input type="button" value="取 消" class="btn_grey" onclick="history.back()"/>
    <span>带<b>"*"</b> 的为必填项</span>
  </div>
</div>
<div class="content">
<form action="${pageContext.request.contextPath}/after/product/save" method="post" id="myform">
  <input type="hidden"  name="id" value="${obj.id}" id="id"/>
  <div class="">
    <div class="right_bk" style="width:98%;">
      <div>
        <dl class="cont">
          <dd class="ysn" style="">
           <label for="hospitalId"><b>*</b>企业名称：</label>
           <select name="hospitalId" id="hospitalId1" style="display: inline-block;width: 250px;">
				<c:forEach items="${orgList}" var="c">
            		<option value="${c.hospitalId }" <c:if test="${obj.hospitalId eq c.hospitalId}">selected="selected"</c:if>>${c.hospitalName }</option>
            	</c:forEach>
		   </select>
          </dd>
         <em class="clear"></em>
          <dd class="ysn" style="">
           <label for="name"><b>*</b>产品名称：</label>
           <input require="require" style="width:300px;" type="text" name="name" id="name" value="${obj.name}"/>
          </dd>
        <em class="clear"></em>
          <dd class="ysn" style="min-height: 170px;">
           <label for="introduce"><b>*</b>产品介绍：</label>
           <textarea rows="10" cols="80" id="introduce" require="require" name="introduce">${obj.introduce}</textarea>
          </dd>
                    <em class="clear"></em>
          <dd class="ysn" style="min-height: 200px;"><label for="str2">产品图片：</label>
             <a id="fileNameUrl" href="${obj.logo}" target="new"><img src="${obj.logo}" id="fileName" width="250" height="150"/></a>
             <input id="logo" name="logo" value="${obj.logo}" type="hidden"/>
             <a id="setMusic" href="javascript:void(0);">选择图片</a>
        </dd>
        </dl>
      </div>
    </div>
  </div>
  </form>
</div>
</body>
</html>
