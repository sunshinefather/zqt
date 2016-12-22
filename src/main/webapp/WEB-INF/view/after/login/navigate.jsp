<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
	<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
	<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
	<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>政企通管理系统</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="${pageContext.request.contextPath}/asset/common/css/font-awesome.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/asset/common/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/asset/common/css/templatemo-style.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/asset/common/js/jquery-1.11.1.min.js"></script> 
</head>
    <body>
        <!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
        
        <div class="site-bg"></div>
        <div class="site-bg-overlay"></div>
		<div class="copyrights"></div>
		
        <div class="container" id="page-content">
			<div  style="width: 600px; height: auto; position: absolute; left: 50%; top: 50%; margin-left: -300px; margin-top: 150px;">
				<nav id="nav">
					<ul class="main-menu">
						<li style="float:left;display:inline">
							<a class="show-1 active homebutton" href="${pageContext.request.contextPath}/forward?service=after/rfid/index"><i class="fa fa-truck"></i>发 卡</a>
						</li>
						<li style="float:left;display:inline;margin-left:40px;">
							<a class="show-2 aboutbutton" href="${pageContext.request.contextPath}/forward?service=after/barcode/index"><i class="fa fa-print"></i>送纸厂</a>
						</li>
						<li style="float:left;display:inline;margin-left:40px;">
							<a class="show-3 projectbutton" href="${pageContext.request.contextPath}/index"><i class="fa fa-desktop"></i>系统管理</a>
						</li>
					</ul>
				</nav>
			</div>
        </div>
    </body>
</html>