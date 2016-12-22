var zTree, rMenu,baseurl,i,curentnode;
$(document).ready(function()
		{
	            baseurl=$("#ResoureTree").attr("url");
	            initRegionTree("ResoureTree","list");//传入初始化数据的url
				rMenu = $("#rMenu");
	 	});
//回车键响应 点击回车键添加区域
$(document).keyup(function(event){
	if(i!=undefined){
	  if(event.keyCode ==13){
		  		saveresource();
			}
	  }
	});
 function initRegionTree(id,url)
 {
    if(!url){
    	layer.alert("参数不能为空!");
    	return;
    }
	var setting = {
			 view: {selectedMulti: false},        
			 data: { key: {name: "resourceName"},
			         simpleData: {enable: true,idKey: "id",pIdKey: "parent"}
		       },
		 callback: {onRightClick: OnRightClick}
		          };
    $.post(baseurl+url,function(data){
    	 var parms=[{"id":0,"parent":-1,"resourceName":"资源管理","code":"0","resourceUrl":"","order":"0"}];
    	 for(var json_index in data.result){
    		 parms.push(data.result[json_index]);
    	 }
	     $.fn.zTree.init($("#"+id), setting,parms);
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
//添加资源
 function addTreeNode() {
		hideRMenu();
		curentnode=zTree.getSelectedNodes()[0];
		$("#resourcefrom #parent").val(curentnode.id);
		$('#resourcefrom #id').val("");
		var order=1;
		if(curentnode.isParent){//如果在父节点上添加子节点
			//获取父节点的最后一个儿子节点的order+1
			order=curentnode.children[curentnode.children.length-1].order+1;
		}
		$("#order").val(order);
		dialogdiv("resourcefrom","添加资源",400,250);
		$("#resourceName").focus();
		
	}
 //保存数据
function saveresource(){
	var jsonparams=$("#resourcefrom").toJson();
	ajaxregion("add",jsonparams,function(data){
		if(!$('#resourcefrom #id').val()){
			zTree.addNodes(curentnode,data);
		}else{
			var node=zTree.getNodeByParam("id",data.id, null);
			$.extend(node,data);
			zTree.updateNode(node);
		}
		layer.close(i);
		$('#resourcefrom')[0].reset();
		
	});
}
   //删除资源
	function removeTreeNode() {
		hideRMenu();
		var node = zTree.getSelectedNodes()[0];
		layer.confirm("确认删除吗",function(index){
			layer.close(index);
			ajaxregion("delete/"+node.id,null,function(data){
				zTree.removeNode(node);
				});
		});
	}
    //更新区域
	function updateTreeNode() {
		hideRMenu();
		curentnode=zTree.getSelectedNodes()[0];
		$("#resourcefrom").setFrom(curentnode);
		dialogdiv("resourcefrom","修改资源",400,250);
		$("#resourceName").focus();
	}
//公共ajax调用,此方法依赖于jQuery
function ajaxregion(url,parms,bankfunc){
	$.post(baseurl+url,parms,function(data){
		if(data.status!=200){
			layer.alert(data.msg);
		}else{
			if($.isFunction(bankfunc)){
				bankfunc(data.result);
			}
		}
	});
}
//弹出层的封装
function dialogdiv(id,title,wd,hg){
	i=$.layer({
		type : 1,
		shade : [0.3 , '#000' , true],
		shadeClose : false,
		fadeIn: 300,
		title : title,
		offset : ['120px' , '50%'],
		closeBtn : [1 , true],
		border : [5, 0.3, '#666', true],
		area : [wd+'px' , hg+'px'],
		page: {
			dom: "#"+id
		},
		end : function(){
			
		}
	});
}
//把表单数据转换成json数据(支持单选，复选和下拉菜单到)
$.fn.toJson = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a,function() {
        if (o[this.name]) {
            o[this.name]=o[this.name]+","+(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
//把json数据填充到表单(暂不支持单选，复选和下拉菜单到)
$.fn.setFrom=function(jsonObject){
	if(jsonObject){
		$.each(jsonObject, function(i) {
			if(typeof i=='string' && $("#"+i).length > 0){
				$("#"+i).val(jsonObject[i]);
			}
		});
	}
}