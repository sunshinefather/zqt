<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="textml; charset=utf-8">
<title>根据用户ID获取用户组</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/scripts/zTree_v3/css/zTreeStyle/zTreeStyle.css" />
<script charset="utf-8" type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/zTree_v3/js/jquery.ztree.core-3.5.min.js"></script>
<script charset="utf-8"  type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/zTree_v3/js/jquery.ztree.excheck-3.5.min.js"></script>
</head>
<body>
	<ul id="group_tree" class="ztree"  style="-moz-user-select: none;"></ul>
	<div style="overflow:auto;background-color: #F0EEE7;width:480px; height:40px;margin-top: 200px;">
	<div style="background-color: #C5C2BA;height:1px;width:100%;"></div>
	<div style="text-align: center;">
		<input style="margin-top:7px;" type="button" value="确 定" class="btn_pink" id="sureGroupsBtn"/>
		</div>
	</div>
</body>
<script type="text/javascript">
var group_setting = {
		view: {
		    dblClickExpand: true,
		    showLine: true,
		    selectedMulti: false
		},
		check:{
			enable:true,
			autoCheckTrigger: true
		},
		data: {
			key: {
				name: "groupName"
			},
		    simpleData: {
		        enable:true,
		        idKey: "region.id",
		        pIdKey: "region.parentId",
		        rootPId: ""
		    }
		},
		callback: {
				onCheck: function(event, treeId, treeNode){
		               var _modal = $(event.target).closest("div");
		               var data = _modal.data("data");
		               if(!data)data = new HashMap();
			           if(treeNode.checked){
			                   data.put(treeNode.id,{id:treeNode.id,groupName:treeNode.groupName});
			           	}else{
			           		data.remove(treeNode.id);
			           	}
		               _modal.data('data',data);
		        },
		        beforeCheck:function(treeId, treeNode){
		        	if(treeNode.regionName)return false;
		        }
		    }
		};
		$(document).ready(function(){
		    $.post('${pageContext.request.contextPath}/after/group/findGroupList',{id:'${SESSIONUSER.id}'},function(data){
		       $.fn.zTree.init($("#group_tree"), group_setting, data);
		    });
		    $("#sureGroupsBtn").click(function(){
		    	layer.close(group_index);
		    });
		});
</script>
</html>