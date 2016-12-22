var zTree, curMenu;
$(document).ready(function(){       
	initRegionTree("menuTree",$("#menuTree").attr("url"));
});
 function initRegionTree(id,url){
    if(!url){
    	layer.alert("菜单加载失败!");
    	return;
    }
	var setting = {
		 view: {
			 showLine : false,
			 showIcon : false,
			 selectedMulti: false,
			 dblClickExpand : false,
			 addDiyDom : function(treeId, treeNode){
				 if(treeNode.isParent || !treeNode.getParentNode()){
					 var a = $("#" + treeNode.tId + "_a");
					 a.wrap("<h3></h3>");
				 }
				 var span = $("#" + treeNode.tId + "_span");
				 span.prepend("<s></s>");
			 }
		 },
		 data: {
			 key: {
				 name: "resourceName",
				 url:"resourceUrl"
			  },
			 simpleData: {
				 enable: true,
				 idKey: "id",
				 pIdKey: "parent"
			 }
		 },
		 callback:{
			 beforeClick:function beforeClick(treeId, node) {
					$("#"+zTree.setting.treeId).find(".cur").removeClass("cur");
					
					//单击事件处理 
					var id = node.id ;
					if ("e4feb8972f784ccf841cd28f9afb6325" == id) {//notice
						curPage = "notice";
					} else if ("" == id) {//announcement TODO 待增加通知
						curPage = "announcement";
					} else if ("041a605b98114c618f66258e34f48546" == id) {//advisory
						curPage = "advisory";
					} else {
						curPage = "other";
					}
					
					var nodeTId = node.tId;
					if(!node.isParent && node.getParentNode()){
						nodeTId = node.getParentNode().tId;
					}
					var h3 =  $("#" + nodeTId + "_a").parent();
					h3.addClass("cur");
					var a = $("#" + node.tId + "_a");
					a.attr("target","frame");
					if (node.isParent) {
						if (node.level === 0) {
							var pNode = curMenu;
							while (pNode && pNode.level !==0) {
								pNode = pNode.getParentNode();
							}
							if (pNode !== node) {
								zTree.expandNode(pNode, false);
							}

							var isOpen = false;
							for (var i=0,l=node.children.length; i<l; i++) {
								if(node.children[i].open) {
									isOpen = true;
									break;
								}
							}
							if (isOpen) {
								zTree.expandNode(node, true);
								curMenu = node;
							} else {
								zTree.expandNode(node.children[0].isParent?node.children[0]:node, true);
								curMenu = node.children[0];
							}
						} else {
							zTree.expandNode(node);
						}
					}else if((!node.children || !node.children.length > 0) && node.getParentNode()){
						$("#"+zTree.setting.treeId).find(".on").removeClass("on");
						a.parent().addClass("on");
					}
					var href = a.attr("href");
				    if(!href || href == "#")a.removeAttr("href");
					return true;
			 }
		 }
	};
	
    $.get(url,function(data){
    	data = data.result||data.resources;
	    $.fn.zTree.init($("#"+id), setting, data);
	    zTree = $.fn.zTree.getZTreeObj(id);
	    
	    //第一个顶级节点
	    curMenu = zTree.getNodes()[0];
	    zTree.expandNode(curMenu);
	    
	    //当顶级节点存在子节点时，获取顶级节点第一个子节点
	    var curChildrenMenu = curMenu.children;
	    if(curChildrenMenu && curChildrenMenu.length > 0){
	    	curMenu = curChildrenMenu[0];
	    }
	    var a = $("#" + curMenu.tId + "_a");
	    var href = a.attr("href");
	    if(href && href != "#")$("#frame").attr("src",href);
	    a.click();
    });
 }