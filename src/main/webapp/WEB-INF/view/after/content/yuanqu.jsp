<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tags/page.tld" prefix="pageTag" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/kuangjia.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/shui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/content.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/js/themes/default/default.css" />
<script type="text/javascript" src="<c:url value="/asset/common/js/jquery-1.8.3.min.js"/>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/js/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/layer/layer.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/js/tools.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/asset/common/scripts/vali/css/vali.css"/>"/>
<script type="text/javascript"  src="<c:url value="/asset/common/scripts/vali/vali.js"/>"/></script>
</head>
<body>
<div class="box">
    <p class="title"><a target="_self">园区管理</a> >> <span>园区管理概况</span></p>

    <div class="daolu_head">
        <button class="daolu_baochun" id="saveBtn">保存</button>
    </div>
<div class="content">
     <dl class="forms">
        <input id="id" name="id" value="1" type="hidden"/>
        <dd id="org_dd">
            <textarea class="lingyu_text" name="content" id="content" style="float:left;width:98%; height:400px;margin-top:0px; ">${obj.content}
            </textarea>
        </dd>
     </dl>
</div>
</div>
</body>
<script src="${pageContext.request.contextPath}/asset/common/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/after/js/zhengqitong.js"></script>
<script type="text/javascript">
	$(function(){
		$("#saveBtn").click(function(){
			var u = {
					"id":$("#id").val(),
					"content":$("#content").val()
			};
			$.ajax({
				url:"${pageContext.request.contextPath}/after/content/save",
				data:u,
				dataType:'json',
				type:'post',
				success:function(data){
					var resp = null;
					if(data instanceof  Object){
						resp = data;
					}else{
						resp = eval("(" + data + ")");
					}
					if(resp.status == "1"){
						layer.alert("保存成功!",1,function(){
							location=location;
						});
					}else{
						layer.alert(resp.msg);
					}
				}
			});
		});
		
});

var editor = null;
KindEditor.ready(function(K) {
		editor = K.create('#content', {
	        resizeType : 1,
	        allowPreviewEmoticons : false,
	        allowImageUpload : true,
	        readonlyMode : false,
	        uploadJson:"${pageContext.request.contextPath}/public/upload/",
	        afterBlur : function() {
	            this.sync();
	        }
	    });
	  var logoEditor = K.editor({
	        allowFileManager : true,
	        fieldName : 'imgFile',
	        uploadJson:"${pageContext.request.contextPath}/public/upload/",
	        afterBlur : function() {
	            this.sync();
	        },
	        afterUpload : function(url, data, name) {//返回的url
	            if (data != null && data.error === 0) {
	                var _img = $("#logoimg");
	                _img.attr("src",url);
	                _img.show();
	                $('#logos').val(data.imageId);
	            } else {
	                alert("文件上传错误");
	            }
	        }
	    });
	    K('#logo').click(function() {
	    	logoEditor.loadPlugin('image', function() {
	    		logoEditor.plugin.imageDialog({
	                     showRemote : false,
	                     clickFn : function(url, title, width, height,
	                             border, align) {
	                    	 logoEditor.hideDialog();
	                     }
	                 });
	             });
	   });
	});

</script>
</html>