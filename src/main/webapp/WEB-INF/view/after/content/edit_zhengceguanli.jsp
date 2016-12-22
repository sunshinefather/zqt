<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="/WEB-INF/tags/page.tld" prefix="pageTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>编辑</title>
<meta name="description" content="政企通管理系统" />
<meta name="keywords" content="政企通管理系统" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/layout.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/pop.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/js/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/scripts/vali/css/vali.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/js/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/after/js/wu-style.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/vali/vali.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/js/tools.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div class="topfixed">
	<div class="current"><s></s><a href="${pageContext.request.contextPath}/after/content/zhengce">政策管理</a> >> <span>编辑</span></div>
	<div class="btnbox">
		<input type="button" value="保 存" class="btn_pink" id="saveUserBtn"/>
		<input type="button" value="返回" class="btn_grey" onclick="location='javascript:history.go(-1)'"/>
		<span>带<b>"*"</b> 的为必填项</span>
	</div>
</div>
<div class="content">
     <dl class="forms">
         <input id="id" name="id" value="${obj.id }" type="hidden"/>
         <input id="categoryId" name="categoryId" value="3" type="hidden"/>
        <dd><label for="title"><span>*</span>标题：</label><input id="title" name="title" value="${obj.title }" type="text" style="width: 300px;" require="require"  autofocus/></dd>
        <dd><label for="str1"><span>*</span>时间：</label><input id="str1" name="str1" value="${obj.str1 }" type="text" style="width: 150px;" class="Wdate" onclick="WdatePicker({autoPickDate:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" require="require"/></dd>
        <dd><label for="str2">附件：</label>
	        <span id="fujianArea">
		        <c:forEach items="${obj.atts}" var="att" varStatus="status">
		             <a style="margin: 0px 3px;color:blue;" href="${att.attUrl}" target="new">${att.attName}</a>
		             <input id="attsattName${status.index}" name="atts[${status.index}].attName" value="${att.attName}" type="hidden"/>
		             <input id="attsattUrl${status.index}" name="atts[${status.index}].attUrl" value="${att.attUrl}" type="hidden"/>
		        </c:forEach>
	        </span>
             <a class="setMusic" href="javascript:void(0);">添加附件</a>
        </dd>
        <dd><label for="content"><span>*</span>政策内容：</label>
        
        <textarea class="lingyu_text" require="require" name="content" id="content" style="float:left;width:98%; height:400px;margin-top:0px; ">
        ${obj.content}
        </textarea>
        </dd>
     </dl>
</div>
<script type="text/javascript">
	var groupMap = new HashMap();
	var group_index = "";
	var fujianct=${fn:length(obj.atts)};
	$(function(){
		$("#saveUserBtn").click(function(){
			var f = $("dl.forms");
			if(!f.vali('validate'))return;
			var u = {
					"content":$("#content").val()
			};
		f.find("input,select:enabled").each(function(index, element) {
					var $name = $(element).attr('name');
					if($name)
						u[$name] = $(element).val();
			});
			$.ajax({
				url:'${pageContext.request.contextPath}/after/content/save',
				data:u,
				dataType:'json',
				type:'post',
				success:function(resp){
					if (resp.status == 1){
						location="${pageContext.request.contextPath}/after/content/zhengce";
					}else{
						layer.alert("保存失败！");
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
		        uploadJson:"${pageContext.request.contextPath}/public/upload/",
		        formatUploadUrl : true,
		        afterUpload : function(url, data, name) {//返回的url
		        console.log(data);
		            if (data != null && data.error === 0) {
		                $("#fujianArea").data("originalName",data.originalName);
		                $("#fujianArea").data("url",data.url);
		                //$('#logos').val(data.imageId);
		            } else {
		                alert("文件上传错误");
		            }
		        }
		    });
		  
		    K('.setMusic').click(function() {
		    	logoEditor.loadPlugin('insertfile', function() {
		    		logoEditor.plugin.fileDialog({
			    			fileUrl : K('#url').val(),
							clickFn : function(url, title,data) {
								$("#fujianArea").append('<a style="margin: 0px 3px;color:blue;" href="'+$("#fujianArea").data("url")+'" target="new">'+$("#fujianArea").data("originalName")+'</a>');
								$("#fujianArea").append('<input id="attsattName'+fujianct+'" name="atts['+fujianct+'].attName" value="'+$("#fujianArea").data("originalName")+'" type="hidden"/>');
								$("#fujianArea").append('<input id="attsattUrl'+fujianct+'" name="atts['+fujianct+'].attUrl" value="'+$("#fujianArea").data("url")+'" type="hidden"/>');
								fujianct+=1;
								logoEditor.hideDialog();
							}
		                 });
		             });
		   });
		  var logoEditor1 = K.editor({
		        uploadJson:"${pageContext.request.contextPath}/public/upload/",
		        formatUploadUrl : true,
		        afterUpload : function(url, data, name) {//返回的url
		        console.log(data);
		            if (data != null && data.error === 0) {
		                $("#fileName1").data("originalName",data.originalName);
		                $("#fileName1").data("url",data.url);
		                //$('#logos').val(data.imageId);
		                $("#str2").val(data.originalName+"|"+data.url);
		            } else {
		                alert("文件上传错误");
		            }
		        }
		    });
		  
		    K('#setMusic1').click(function() {
		    	logoEditor1.loadPlugin('insertfile', function() {
		    		logoEditor1.plugin.fileDialog({
			    			fileUrl : K('#url').val(),
							clickFn : function(url, title,data) {
								$("#fileName1").text($("#fileName1").data("originalName"));
								$("#fileName1").attr("href",$("#fileName1").data("url"));
								logoEditor1.hideDialog();
							}
		                 });
		             });
		   });
		});
</script>
</body>
</html>