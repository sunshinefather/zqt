<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<c:url value="/asset/common/css/global.css"/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/asset/common/scripts/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/asset/common/scripts/zTree_v3/css/zTreeStyle/zTree_exp.css"/>" type="text/css"/>
<script type="text/javascript" src="<c:url value="/asset/common/js/jquery-1.8.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/zTree_v3/js/jquery.ztree.core-3.5.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/zTree_v3/js/jquery.ztree.excheck-3.5.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/zTree_v3/js/jquery.ztree.exedit-3.5.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/layer/layer.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/layer/extend/layer.ext.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/after/js/resource.js"/>"></script>
<title>资源信息管理</title>
<style type="text/css">
.red{color: #F30}
</style>
</head>
<body>
<div>
  <ul id="ResoureTree" class="ztree" url="<c:url value='/after/resource/'/>"></ul>
</div>
<!-- 右键菜单项 -->
<div id="rMenu">
	<ul>
		<li id="m_add" onclick="addTreeNode();">增加资源</li>
		<li id="m_update" onclick="updateTreeNode(true);">修改资源</li>
		<li id="m_del" onclick="removeTreeNode();">删除资源</li>
	</ul>
</div>
<!-- 添加表单 -->
<form id="resourcefrom" style="display: none;margin: 10px 80px">
<table>
	<tbody>
	  <tr >
	    <th><span class="red">*</span>资源名称:
	    <input type="hidden" id="id" name="id" value="" />
	    <input type="hidden" id="parent" name="parent" value="" />
	    </th>
	    <td><input name="resourceName" id="resourceName" value="" type="text"/></td>
	  </tr>
	  <tr>
	     <th><span class="red">*</span>资源编码:</th>
	     <td><input name="code" id="code" value="" type="text"/></td>
	  </tr>
	    <tr>
	    <th><span class="red">*</span>资源地址:</th>
	    <td><input name="resourceUrl" id="resourceUrl" value="#" type="text"/></td>
	  </tr>
	  <tr>
        <th><span class="red">*</span>资源类型:</th>
        <td><select name="resourceType" id="resourceType">
              <option value="1" selected="selected">菜单</option>
              <option value="2">功能</option>
           </select></td>
      </tr>
      <tr>
        <th><span class="red">*</span>项目标识:</th>
        <td><input name="project" id="project"  type="text"/></td>
      </tr>
     <tr>
	    <th><span style="margin-left:7px;">顺序编号:</span></th>
	    <td><input name="order" id="order" value="" type="text"/></td>
	  </tr>
	</tbody>
	<tfoot>
	  <tr>
	    <td colspan="2" style="text-align: center; padding: 12px;"><input type="button" value="保存" class="btn_pink" onclick="saveresource()"/></td>
	  </tr>
	</tfoot>
</table>
	
</form>

</body>
</html>