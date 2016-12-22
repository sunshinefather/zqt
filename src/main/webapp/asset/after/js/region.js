var zTree, rMenu,baseurl,enter,updateenter;
$(document).ready(function()
		{      
	            baseurl=$("#regionTree").attr("url");
	            initRegionTree("regionTree","listregion");//传入初始化数据的url
				rMenu = $("#rMenu");
				
				
	 	});
//回车键响应 点击回车键添加区域
$(document).keyup(function(event){
	var saveOrup =  $("#saveOrup").val();
	
	//修改
	if(updateenter!=undefined && saveOrup==2){
		  if(event.keyCode ==13){
			  var name = $("#xubox_prompt").val();
			  if(name!="" && name!=null){
					var node=zTree.getSelectedNodes()[0];
					if (node) {
								var parms={"id":node.id,"regionName":name,"parentId":node.parentId};
								ajaxregion("saveregion",parms,function(data){
									node.regionName=data.regionName;
								    zTree.updateNode(node);
								});
								layer.close(updateenter);
							}else{
								layer.alert("不能为空",0);
							}
					}
		  }
		}
	
	
	if(enter!=undefined && saveOrup==1){
	  if(event.keyCode ==13){
		  var name = $("#xubox_prompt").val();
		  if(name!="" && name!=null){
				var node=zTree.getSelectedNodes()[0];
				if (node) {
					var id=node.id;
					var formatCode=node.formatCode;
					var levelSeq=1;
					var levelIndex=node.levelIndex+1;
					if(node.isParent){//如果在父节点上添加子节点
						//获取父节点的最后一个儿子节点的levelSeq+1
						levelSeq=node.children[node.children.length-1].levelSeq+1;
					}
					var parms={"parentId":id,"regionName":name,"formatCode":formatCode,"levelSeq":levelSeq,"levelIndex":levelIndex};
					ajaxregion("saveregion",parms,function(data){
					 zTree.addNodes(node,data);
					});
					layer.close();
				}
				layer.close(enter);
			}else{
				layer.alert("不能为空!",0);
			}
	  }
	}
	
	});
 function initRegionTree(id,url)
 {
    if(!url){
    	layer.alert("参数不能为空!",0);
    	return;
    }
	var setting = {
			 view: {selectedMulti: false},        
			 data: { key: {name: "regionName"},
	                 simpleData: {enable: true,idKey: "id",pIdKey: "parentId"}
			       },
		 callback: { onRightClick: OnRightClick}
		          };
    $.post(baseurl+url,function(data){
	     $.fn.zTree.init($("#"+id), setting,data.result);
	     zTree = $.fn.zTree.getZTreeObj(id);
    });
 }
 function OnRightClick(event, treeId, treeNode) {
		if (treeNode && !treeNode.noR) {
			zTree.selectNode(treeNode);
			var isdel="";
			if(treeNode.id==0 || treeNode.isParent)
				isdel="root";
			showRMenu(isdel,event.clientX, event.clientY);
		}
	}
 function showRMenu(type, x, y) {
		$("#rMenu ul").show();
		 var node = zTree.getSelectedNodes()[0];
		 if(node.id==0){
			$("#m_del").hide();
			$("#m_update").hide();
		 }else{
			 $("#m_update").show(); 
		 }
		if (type=="root") {
			$("#m_del").hide();
		} else {
			$("#m_del").show();
		}
		rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

		$("body").bind("mousedown", onBodyMouseDown);
 }
 function onBodyMouseDown(event){
		if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
			rMenu.css({"visibility" : "hidden"});
		}
	}
 function hideRMenu() {
		if (rMenu) rMenu.css({"visibility": "hidden"});
		$("body").unbind("mousedown", onBodyMouseDown);
	}
//添加区域
 function addTreeNode() {
	 	$("#saveOrup").val("1");
		hideRMenu();
		enter =layer.prompt({title:"请输入区域名称"}, function(name,index){
			if(name){
				var node=zTree.getSelectedNodes()[0];
				if (node) {
					var id=node.id;
					var formatCode=node.formatCode;
					var levelSeq=1;
					var levelIndex=node.levelIndex+1;
					if(node.isParent){//如果在父节点上添加子节点
						//获取父节点的最后一个儿子节点的levelSeq+1
						levelSeq=node.children[node.children.length-1].levelSeq+1;
					}
					var parms={"parentId":id,"regionName":name,"formatCode":formatCode,"levelSeq":levelSeq,"levelIndex":levelIndex};
					ajaxregion("saveregion",parms,function(data){
						 zTree.addNodes(node,data);
						 layer.close(index);
					});
				}
			}else{
				layer.alert("不能为空!",0);
			}
		});
		$("#xubox_prompt").focus();
	}
   //删除区域
	function removeTreeNode() {
		hideRMenu();
		var node = zTree.getSelectedNodes()[0];
		layer.confirm("确认删除吗",function(index){
			layer.close(index);
			var parms={"id":node.id};
			ajaxregion("removeregion",parms,function(data){
				zTree.removeNode(node);
				layer.alert(data,1);
				});
		});
	}
    //更新区域
	function updateTreeNode() {
		$("#saveOrup").val("2");
		hideRMenu();
		var node = zTree.getSelectedNodes()[0];
		if (node) {
			updateenter = layer.prompt({title:"请输入区域名称",val:node.regionName}, function(name,index){
				if(name){
					var parms={"id":node.id,"regionName":name,"parentId":node.parentId};
					ajaxregion("saveregion",parms,function(data){
						node.regionName=data.regionName;
					    zTree.updateNode(node);
					    layer.close(index);
					});
				}else{
					layer.alert("不能为空",0);
				}
			});
		}
		$("#xubox_prompt").focus();
	}
//公共ajax调用,此方法依赖于jQuery
function ajaxregion(url,parms,bankfunc){
	$.post(baseurl+url,parms,function(data){
		if(data.status!=200){
			layer.alert(data.msg,3);
		}else{
			if($.isFunction(bankfunc)){
				bankfunc(data.result);
			}
		}
	});
}