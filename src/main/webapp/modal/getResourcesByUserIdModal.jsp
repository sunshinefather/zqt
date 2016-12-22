<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>根据用户ID获取资源</title>
<link rel="stylesheet" href="asset/common/scripts/zTree_v3/css/zTreeStyle/zTreeStyle.css" />
<script charset="utf-8" type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/zTree_v3/js/jquery.ztree.core-3.5.min.js"></script>
<script charset="utf-8"  type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/zTree_v3/js/jquery.ztree.excheck-3.5.min.js"></script>
</head>
<body>
	<ul id="resource_tree" class="ztree"  style="-moz-user-select: none;overflow:auto;height:100%;"></ul>
</body>
<script type="text/javascript">
var resource_setting = {
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
				name: "resourceName"
			},
		    simpleData: {
		        enable:true,
		        idKey: "id",
		        pIdKey: "parent",
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
		        }
		    }
		};
		$(document).ready(function(){
			var userid = '${SESSIONUSER.id}';
			if(userid){
				$.get('${pageContext.request.contextPath}/after/resource/jsons/'+userid,{type:'json'},function(resp){
					   var  data = eval("(" + resp + ")");
				       $.fn.zTree.init($("#resource_tree"), resource_setting, data.resources);
				       loadChecked();
				 });
			}
		});
		
		function loadChecked(){
			var roleid = '<%= request.getParameter("roleid")%>';
			var btnid = '<%= request.getParameter("btnid")%>';
			if(roleid){
				$.ajax({
					url:'${pageContext.request.contextPath}/after/role/getResourcesByRole',
					dataType:'json',
					data:{id:roleid},
					type:'post',
					success:function(resp){
						if(resp instanceof Array){
							var treeObj = $.fn.zTree.getZTreeObj("resource_tree");
							var notes =  treeObj.transformToArray(treeObj.getNodes()) ;
							var resourcesMap = new HashMap();
							for(var i in resp){
								resourcesMap.put(resp[i].id,resp[i].id);
							}
							for(var i in notes){
								var note = notes[i];
								if(resourcesMap.containsKey(note.id)){
									treeObj.checkNode(note,true,false,true);
								}
							}
						}
						$(btnid).removeAttr("disabled");
					}
				});
			}else{
				$(btnid).removeAttr("disabled");
			}
		}
		
</script>
</html>