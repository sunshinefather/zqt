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
				name: "hospitalName",
				regoinId:"regionId"
			},
		    simpleData: {
		        enable:true,
		        idKey: "hospitalId",
		        pIdKey: "parentId",
		        rootPId: ""
		    }
		},
		callback: {
		        onClick: function(event, treeId, treeNode){
		        	if(treeNode.id!=0){//当id不为0的时候才可以选择区域 
		        	       var parentsId = getParentId(treeNode);
			               var o = {
			            		   id:treeNode.hospitalId,
			            		   name:treeNode.hospitalName,
			            		   parentsId:parentsId,
			            		   regoinId:treeNode.regionId
			               };
			               var _modal = $(event.target).closest("div");
			               _modal.data("data",o);
			               layer.close(orgion_index);
			        }
		        }
		    }
		};
	
	//循环获取父节点
    function getParentId(currentNode){
		if(!currentNode) return "";
		var parentIds = "";
    	var parentNode = currentNode.getParentNode();
    	if(parentNode != null){
    		if(parentNode.getParentNode()){
	    		parentIds = parentNode.id + "," +getParentId(parentNode);
    		}else{
    			parentIds = parentNode.id;
    		}
    	}
    	return parentIds;
    }
	
	//根据当前登录用户获得机构
	$.post('${pageContext.request.contextPath}/after/hospital/getOrgionByUserId',function(data){
	      $.fn.zTree.init($("#organ_tree"), orgion_setting, data.result);
	});
</script>
</html>