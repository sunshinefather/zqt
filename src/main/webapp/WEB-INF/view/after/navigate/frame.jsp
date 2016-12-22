<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tags/page.tld" prefix="pageTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<title>西南航空港经济开发区政企通管理系统</title>
<link href="${pageContext.request.contextPath}/asset/common/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/asset/common/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/asset/common/css/bootstrap-theme-wfy.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/asset/common/css/animate.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/asset/common/css/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/asset/common/css/iocn.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/asset/common/js/jquery-1.11.1.min.js" type="text/javascript" ></script> 
<!-- bootstrap --> 
<script src="${pageContext.request.contextPath}/asset/common/js/bootstrap.min.js"></script> 
<script src="${pageContext.request.contextPath}/asset/common/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/after/js/menu.js" type="text/javascript" ></script> 
<script src="${pageContext.request.contextPath}/asset/common/scripts/layer/layer.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/asset/common/js/tools.js" type="text/javascript"></script>
</head>
<body>

<!-- Fixed navbar -->
<header id="header" class="navbar navbar-inverse headroom" role="navigation">
	<div class="navbar-brand">
        <span class="logo"></span>
         <p>西南航空港经济开发区政企通管理系统</p>
        <span class="sublog1o">${SESSIONUSER.extUser.organization.hospitalName }</span>
        
  	</div>
  <div class="navbar-collapse collapse">
    <ul class="nav navbar-nav navbar-right">
 
      <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown">当前用户：${SESSIONUSER.extUser.fullName}&nbsp; <b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a href="javascript:void(0);" id="pwd">修改密码</a></li>
          <li class="divider"></li>
          <li><a href="${pageContext.request.contextPath}/logout">安全退出</a></li>
        </ul>
      </li>
    </ul>
  </div>
</header>
<section class="page-container"> 
 <nav class="page-sidebar nav-collapse">
<!--MENU -->
	<ul class="page-sidebar-menu" id="menuTree" url="${pageContext.request.contextPath}/after/resource/jsons/${SESSIONUSER.id }?resourceType=1&project=UserPlatform">
	    <li><div class="sidebar-toggler"></div></li>
	</ul>
<!--/MENU -->
	<div class="version">版本号：v1.0.151028</div>
	</nav>
<article class="page-content">  <!-- ${pageContext.request.contextPath}/home -->
       <iframe src="" frameborder="0" id="frame" name="frame"></iframe>
</article>
</section >
<!-- /container --> 
<script type="text/javascript">
	$(function(){
		var title="";
		if("1"=="${changePwd}"){
			title="<span style='color:red;'>你的密码为默认密码请  </span>";
		}
		//修改密码弹出框
		$('#pwd').on('click', function(){
			$.layer({
				type : 2,
				shade : [0.3 , '#ccc' , true],
				shadeClose : false,
				fadeIn: 300,
				title : title+'修改密码',
				offset : ['120px' , '50%'],
				closeBtn : [1 , true],
				border : [0],
				area : ['680px' , '350px'],
				iframe:{src:'${pageContext.request.contextPath}/after/user/toChangePassword'}
			});
		});
		if("1"=="${changePwd}"){
			$('#pwd').click();
		}
	});
</script>

<!--下拉滚动自动隐藏头部 --> 
<script src="${pageContext.request.contextPath}/asset/common/js/headroom.js"></script> 
<script src="${pageContext.request.contextPath}/asset/common/js/jquery.headroom.js"></script> 
<!--页面元素固定在窗口或屏幕的顶部 --> 
<script src="${pageContext.request.contextPath}/asset/common/js/stickUp.min.js"></script>
<!--定制checkbox--> 
<script src="${pageContext.request.contextPath}/asset/common/js/icheck.min.js"></script>
<!--menu响应菜单--> 
<script src="${pageContext.request.contextPath}/asset/common/js/sidebar-menu.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/asset/common/js/app-wfy.js" type="text/javascript"></script> 
</body>
</html>
