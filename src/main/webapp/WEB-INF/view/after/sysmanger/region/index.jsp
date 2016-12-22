<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<c:url value="/asset/common/scripts/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/asset/common/scripts/zTree_v3/css/zTreeStyle/zTree_exp.css"/>" type="text/css"/>
<script type="text/javascript" src="<c:url value="/asset/common/js/jquery-1.8.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/zTree_v3/js/jquery.ztree.core-3.5.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/zTree_v3/js/jquery.ztree.excheck-3.5.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/zTree_v3/js/jquery.ztree.exedit-3.5.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/layer/layer.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/layer/extend/layer.ext.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/after/js/region.js"/>"></script>
<title>区域管理</title>
</head>
<body>
<div>
  <ul id="regionTree" class="ztree" url="<c:url value="/after/region/" />"></ul>
</div>
<div id="rMenu">
	<ul>
		<li id="m_add" onclick="addTreeNode();">增加区域</li>
		<li id="m_update" onclick="updateTreeNode(true);">修改区域</li>
		<li id="m_del" onclick="removeTreeNode();">删除区域</li>
	</ul>
</div>
<!-- 根据deOrup里面的值判断点击enter按钮时执行的事什么操作。1：添加  2:修改 -->
<input type="hidden" value="1" id="saveOrup"/>
</body>
</html>