<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>职务编辑</title>
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
</head>
<body>
<div class="topfixed">
	<div class="current"><s></s><a href="#">首页</a> >> <a href="#">系统管理</a> >> <span>职务编辑</span></div>
	<div class="btnbox">
		<input type="button" value="保 存" class="btn_pink" id="saveBtn"/>
		<input type="button" value="取 消" class="btn_grey" onclick="location='javascript:history.go(-1)'"/>
		<span>带<b>"*"</b> 的为必填项</span>
	</div>
</div>
<div class="content">
     <dl class="forms">
        <dd><label for="officeName" style="width: 84px;"><span>*</span>职务名称：</label><input id="officeName" name="officeName" value="${obj.officeName }" type="text" require="require" title="职务名称"  maxlength="60" autofocus/></dd>
        <dd id="org_dd" style="display: none">
        	<label for="org">所属医院：</label>
        	<input id="org"  readonly="readonly" type="text"  value="<c:choose><c:when test="${not empty(obj.organization) }">${obj.organization.hospitalName }</c:when><c:otherwise>${SESSIONUSER.extUser.organization.hospitalName }</c:otherwise></c:choose>"/>
        	<input type="hidden"  name="organizationId" value="<c:choose><c:when test="${not empty(obj.organization) }">${obj.organization.hospitalId }</c:when><c:otherwise>${SESSIONUSER.extUser.organization.hospitalId }</c:otherwise></c:choose>"/>
        </dd>
     </dl>
</div>
<script type="text/javascript">
	$(function(){
		$("#saveBtn").click(function(){
			var f = $("dl.forms");
			if(!f.vali('validate'))return;
			var u = {
					officeId:'${obj.officeId}'
			};
			var backlist = [];
			f.find("input,select").each(function(index, element) {
				if ($.inArray($(element).attr('id'), backlist) == -1) {
					var $name = $(element).attr('name');
					if($name)
						u[$name] = $(element).val();
				}
			});
			
			$.ajax({
				url:u.officeId?'${pageContext.request.contextPath}/after/office/office-update':'${pageContext.request.contextPath}/after/office/office-save',
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
					if(resp.result){
						location.href="${pageContext.request.contextPath}/after/office/office-list";
					}else{
						layer.alert(resp.message);
					}
				}
			});
		});
		
});
</script>
</body>
</html>