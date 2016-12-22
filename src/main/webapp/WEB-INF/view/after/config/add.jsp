<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>政企通管理系统</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/layout.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/pop.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/common/scripts/layer/layer.min.js"></script>
<link rel="stylesheet" href="<c:url value="/asset/common/scripts/vali/css/vali.css"/>"/>
<script type="text/javascript"  src="<c:url value="/asset/common/scripts/vali/vali.js"/>"/></script>
</head>

<body>
<div class="topfixed">
<div class="current"><s></s><a href="#">首页</a> >> <a href="#">系统管理</a> >> <span>新增配置</span></div>
<div class="btnbox">
<input type="button" value="保 存" class="btn_pink"/>
<input type="button" value="返回" class="btn_grey" onclick="location='${pageContext.request.contextPath}/after/config/list'"/>
<span>带<b>"*"</b> 的为必填项</span>
</div>
</div>
${valis}
<!-- FORM 属性添加提交 -->
<form action="<c:choose><c:when test="${not empty(config.configKey) }">${pageContext.request.contextPath}/after/config/update</c:when><c:otherwise>${pageContext.request.contextPath}/after/config/add</c:otherwise></c:choose>" method="post" id="configFrom">
	<div class="content">
	     <dl class="forms">
	        <dd><label for="configKey" style="margin-left: 4px;"><span>*</span>属性key：</label><input title="属性key" require="require"
	         value="${config.configKey}" id="configKey" <c:if test="${not empty(config.configKey)}">readonly="readonly" </c:if> name="configKey" type="text" autofocus/>
	        <c:out value="${valis.configKey}"></c:out>
	        </dd>
	        <dd><label for="usertype"><span>*</span>属性类型：</label>
	        <select style="width:232px" name="configType"  title="属性类型" require="require">
	          <option style="display:none; opacity:0" value="">-- 请选择 --</option>
	          <option value="String" <c:if test="${config.configType=='String'}">selected="selected"</c:if>>String</option>
	          <option value="Json" <c:if test="${config.configType=='Json'}">selected="selected"</c:if>>Json</option>
	          <option value="Array" <c:if test="${config.configType=='Array'}">selected="selected"</c:if>>Array</option>
	        </select>
	        <c:out value="${valis.configType}"></c:out>
	        </dd>
	        <dd>
	        <label for=" "><span>*</span>属性值：</label>
	        <textarea id=" " type="text" name="configValue"  title="属性值" require="require"  style="padding: 6px;border: 1px solid #cccac7;" rows="10" cols="60">${config.configValue}</textarea>
	        <c:out value="${valis.configValue}"></c:out>
	        </dd>
	        <dd><label for=" "><span></span>描述：</label>
	        	<textarea style="padding: 6px;border: 1px solid #cccac7;" rows="10" cols="60" name="configDis">${config.configDis}</textarea>
	        </dd>
	     </dl>
	</div>
</form>
<script type="text/javascript">
	$(function(){
		$(".btn_pink").bind("click",function(){
			//提交
			if($("#configFrom").vali('validate')){
				$("#configFrom").submit();
			}
			
		});
	});
</script>
</body>
</html>