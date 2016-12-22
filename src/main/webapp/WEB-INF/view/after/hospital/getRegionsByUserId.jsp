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
	<ul id="region_tree" class="ztree"  style="-moz-user-select: none;overflow:auto;height:100%;"></ul>
</body>
<script type="text/javascript">
var region_setting = {
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
		        rootPId: "",
		        regionName: "regionName"
		    }
		},
		callback: {
		        onClick: function(event, treeId, treeNode){
		        	$("#regName").val(treeNode.regionName);
		        	if($("#organRegionId").val() != treeNode.id){
		        		$("#organRegionId").val(treeNode.id);//组织 机构管理用。
		        		getOrganizationByregionId();
		        	}
		        }
		    }
		};
		$(document).ready(function(){
			var type = '${SESSIONUSER.type}';
			var userId = '${SESSIONUSER.id}';
			if(type){
				if(type != '0'){
					 $.post('${pageContext.request.contextPath}/after/region/getRegionsByUserId',{userId:userId},function(data){
					       $.fn.zTree.init($("#region_tree"), region_setting, data.result);
					       if(type=='200'){
					    	   var treeObj = $.fn.zTree.getZTreeObj("region_tree");
					        	var nodes = treeObj.getNodes();
					        	$("#regName").val(nodes[0].regionName);
					        	$("#organRegionId").val(nodes[0].id);
					       }
					 });
				}else{
					 $.post('${pageContext.request.contextPath}/after/region/listregion',function(data){
					       $.fn.zTree.init($("#region_tree"), region_setting, data.result);
					       if(type=='200'){
					    	   var treeObj = $.fn.zTree.getZTreeObj("region_tree");
					        	var nodes = treeObj.getNodes();
					        	$("#regName").val(nodes[0].regionName);
					        	$("#organRegionId").val(nodes[0].id);
					       }
					  });
				}
			}
		});
</script>
</html>