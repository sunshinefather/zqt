<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>根据用户ID获取资源</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/scripts/zTree_v3/css/zTreeStyle/zTreeStyle.css" />
<script charset="utf-8" type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/zTree_v3/js/jquery.ztree.core-3.5.min.js"></script>
</head>
<body>
	<ul id="categorg_setting" class="ztree"   style="-moz-user-select: none;overflow:auto;height:100%;"></ul>
</body>
<script type="text/javascript">
var categorg_setting = {
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
				name: "categoryName"
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
					if(treeNode.hasChild==false){
						 $("#category").val(treeNode.categoryName);
						 $("#categoryId").val(treeNode.id);
						 layer.close(categoryName);
						 $("#category").focus();
						 $("#title").focus();
						 $("#category").focus();
					}
		        }
		    }
		};
		$(document).ready(function(){
			var userid = '${SESSIONUSER.id}';
			if(userid){
				$.get('${pageContext.request.contextPath}/after/knowledgestorecategory/list',function(data){
					  $.fn.zTree.init($("#categorg_setting"), categorg_setting, data.data);
				 });
			}
		});
		
</script>
</html>