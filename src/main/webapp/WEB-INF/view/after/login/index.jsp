<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>政企通管理系统</title>
<link href="${pageContext.request.contextPath}/asset/common/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/asset/common/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/asset/common/css/bootstrap-theme-wfy.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/asset/common/css/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/asset/common/css/iocn.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/asset/common/css/login.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/asset/common/image/favicon.ico" type="image/x-icon" rel="shortcut icon">

<!-- JavaScript --> 
<script src="${pageContext.request.contextPath}/asset/common/js/jquery-1.11.1.min.js"></script> 
<script src="${pageContext.request.contextPath}/asset/common/js/bootstrap.min.js"></script>
<!--checkbox-style-->
<script src="${pageContext.request.contextPath}/asset/common/js/icheck.min.js"></script>
<!--form表单验证-->
<script src="${pageContext.request.contextPath}/asset/common/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/common/js/login.js"></script>

</head>
<body class="login">
  <div class="content">
    <div class="content-top"></div><p class="bt">政企通管理系统</p>
    <p class="form_title"><span>用户登录</span></p>  
    <div style="color: red;margin-left: 32px;margin-top: -15px;">${fn:substring(SPRING_SECURITY_LAST_EXCEPTION.message,0,30)}</div>
	<!--LoginFormBegin-->
		<form class="form-vertical login-form" action="${pageContext.request.contextPath}/authenticationlogin" method="post">    
			<input type="hidden" name="PEER" id="PEER" value="0"/>
			<div class="control_group">
				<label class="control-label">用户名</label>
				<div class="controls">
                    <span class="input-icon"><i class="iocn icon-user"></i></span>
					<input class="m-wrap" type="text" placeholder="用户名" name="userName"/>
				</div>
            </div>
			<div class="control_group">
				<label class="control-label">密码</label>
				<div class="controls">
                    <span class="input-icon"><i class="iocn icon-lock"></i></span>
					<input class="m-wrap" type="password" placeholder="密码" name="password"/>
				</div>
            </div>
            <div class="control_group">
			      <label class="control-label">验证码</label>
			      <div class="controls">
	                    <span class="input-icon"><i class="iocn icon-lock"></i></span>
						<input class="m-wrap" type="text" style="width: 40%" id="validateCode" name="validateCode"  placeholder="验证码" />
					     <a href="javascript:void(0);" onclick="newverifypic();"><img src="${pageContext.request.contextPath}/public/code" border="0" id="checkcode" align="top" title="重新获取验证码"/></a>
				  </div>
	   		</div> 
            <div class="checktip">
           <!-- 
            <label class="remAutoLogin" for="remAutoLogin" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="为了您的信息安全，请不要在公用计算机上使用此功能">
            	<input id="remAutoLogin" type="checkbox" name="rememberMe" value="true"/>
				十天内免登录
			</label>
			 -->
            <span><a href="#"></a></span>
            </div>
			<div class="form-actions">
				<button type="submit" class="btn btn-lg btn-success">登 录<i  class="iocn icon-login-lg right"></i></button>            
			</div>
		</form>
        </div>
        
        <div class="copyright">Copyright © 2015-2020.All Rights Reserved</div>
  
</body>
<script type="text/javascript">
if (window!=top){
	top.location = window.location;
}

function newverifypic(){
	$("#checkcode").attr('src','${pageContext.request.contextPath}/public/code?rd='+Math.floor(Math.random()*10+1));
}
</script>
</html>