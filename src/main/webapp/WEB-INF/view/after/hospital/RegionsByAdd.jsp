<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>政企通管理系统</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/scripts/zTree_v3/css/zTreeStyle/zTreeStyle.css" />
<script charset="utf-8" src="${pageContext.request.contextPath}/asset/common/scripts/zTree_v3/js/jquery.ztree.core-3.5.min.js"></script>
</head>
<body>
	<ul id="regionadd_tree" class="ztree"  style="-moz-user-select: none;overflow:auto;height:100%;"></ul>
</body>
<script type="text/javascript">
var regionadd_setting = {
		view: {
		    dblClickExpand: false,
		    showLine: true,
		    selectedMulti: false
		},
		data: {
			key: {
				name: "regionName"
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
		        	if(treeNode.id!=0){//当id不为0的时候才可以选择区域 
		               var o = {
		            		   id:treeNode.id,
		            		   regionName:treeNode.regionName
		               };
		               layer.close(region_index);
		               $("#regionName").val(treeNode.regionName);
						$("#regionId").val(treeNode.id);
		        }
		        }
		    }
		};
$(document).ready(function(){
	var type = '${SESSIONUSER.type}';
	var userId = '${SESSIONUSER.id}';
	if(type){
		if(type != '0'){
			 $.post($("#basePath").val()+'/after/region/getRegionsByUserId',{userId:userId},function(data){
			       $.fn.zTree.init($("#region_tree"), regionadd_setting, data.result);
			 });
		}else{
			 $.post($("#basePath").val()+'/after/region/listregion',function(data){
			       $.fn.zTree.init($("#region_tree"), regionadd_setting, data.result);
			  });
		}
	}
});
</script>
</html>