<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/after/js/wu-style.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/vali/vali.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/scripts/vali/css/vali.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/js/tools.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/js/themes/default/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/js/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/lang/zh_CN.js"></script>

</head>
<body>
<div class="topfixed">
	<div class="current"><s></s><a href="${pageContext.request.contextPath}/after/content/banzi">历届领导班子管理</a> >> <span>编辑</span></div>
	<div class="btnbox">
		<input type="button" value="保 存" class="btn_pink" id="saveUserBtn"/>
		<input type="button" value="返回" class="btn_grey" onclick="location='javascript:history.go(-1)'"/>
		<span>带<b>"*"</b> 的为必填项</span>
	</div>
</div>
<div class="content">
     <dl class="forms">
         <input id="id" name="id" value="${obj.id }" type="hidden"/>
         <input id="categoryId" name="categoryId" value="1" type="hidden"/>
        <dd><label for="title"><span>*</span>历届班子：</label><input id="title" name="title" value="${obj.title }" type="text" style="width: 100px;" require="require"  autofocus/></dd>
        <dd><label for="summary"><span>*</span>介绍：</label>
        <textarea rows="10" cols="100" id="summary" name="summary" require="require" >${obj.summary }</textarea>
        </dd>
     </dl>
</div>
<script type="text/javascript">
	var groupMap = new HashMap();
	var group_index = "";
	$(function(){
		$("#saveUserBtn").click(function(){
			var f = $("dl.forms");
			if(!f.vali('validate'))return;
			var u = {};
		f.find("input,textarea,select:enabled").each(function(index, element) {
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
						location="${pageContext.request.contextPath}/after/content/banzi";
					}else{
						layer.alert("保存失败！");
					}
				}
			});
		});
	});
</script>
</body>
</html>