<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>根据用户ID获取行政区划</title>
<link rel="stylesheet" href="<c:url value="/asset/common/scripts/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>" />
<script type="text/javascript" src="<c:url value="/asset/common/scripts/zTree_v3/js/jquery.ztree.core-3.5.js"/>"></script>
</head>
<body>
	<ul id="organ_tree" class="ztree"  style="-moz-user-select: none;overflow:auto;height:100%;"></ul>
</body>
<script type="text/javascript">
var orgion_setting = {
		view: {
		    dblClickExpand: false,
		    showLine: true,
		    selectedMulti: false
		},
		data: {
			key: {
				name: "name",
				parentsId: "parentsId"
			},
		    simpleData: {
		        enable:true,
		        idKey: "id",
		        pIdKey: "parentId",
		        rootPId: ""
		    }
		},
		callback: {
		        onClick: function(event, treeId, treeNode){
		        	if(treeNode.id!=0){//当id不为0的时候才可以选择
		        		$("#orgionName").val(treeNode.name);
		        		$("#orgId").val(treeNode.id);
			               layer.close(orgion_index);
			               
			        }
		        }
		    }
		};
	$(document).ready(function(){
		$.post('${pageContext.request.contextPath}/after/hospital/getOrgionByUserId',function(data){
			  $.fn.zTree.init($("#organ_tree"), orgion_setting, data.result);
		});
	});
</script>
</html>