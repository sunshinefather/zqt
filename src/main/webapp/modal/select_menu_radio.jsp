<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style>
<!--
input.radio {margin: 0 2px 0 8px;}
input.radio.first {margin-left:0;}
input.empty {color: lightgray;}
code {color: #2f332a;}
.highlight_red {color:#A60000;}
.highlight_green {color:#A7F43D;}
li {list-style: circle;font-size: 12px;}
li.title {list-style: none;}
ul.list {margin-left: 17px;}

div.content_wrap {width: 600px;height:380px;}
div.content_wrap div.left{float: left;width: 250px;}
div.content_wrap div.right{float: right;width: 340px;}
div.zTreeDemoBackground {width:250px;height:362px;text-align:left;}

ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:360px;overflow-y:scroll;overflow-x:auto;}
ul.log {border: 1px solid #617775;background: #f0f6e4;width:300px;height:170px;overflow: hidden;}
ul.log.small {height:45px;}
ul.log li {color: #666666;list-style: none;padding-left: 10px;}
ul.log li.dark {background-color: #E3E3E3;}

/* ruler */
div.ruler {height:20px; width:220px; background-color:#f0f6e4;border: 1px solid #333; margin-bottom: 5px; cursor: pointer}
div.ruler div.cursor {height:20px; width:30px; background-color:#3C6E31; color:white; text-align: right; padding-right: 5px; cursor: pointer}
-->
</style>
<ul id="select_region_tree" class="ztree"  style="-moz-user-select: none;overflow:auto;height:100%;margin: 0px;"></ul>
<script type="text/javascript">
var _s = null;
var select_region_setting = {
		check: {
			enable: true,
			chkStyle: "radio",
			radioType: "all"
		},
		view: {
			dblClickExpand: false
		},
		data: {
			key: {
				name: "regionName"
			},
			simpleData: {
				enable: true,
				idKey: "id",
		        pIdKey: "parentId",
		        rootPId: "-1"
			}
		},
		callback: {
			onClick: onClick,
			onCheck: onCheck
		}
	};

	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("select_region_tree");
		zTree.expandNode(treeNode,true,false,false,false);
		if(treeNode.id == 0){
			treeNode.nocheck = true;
			zTree.updateNode(treeNode,false);
			return false;
		}
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}

	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("select_region_tree"),
		nodes = zTree.getCheckedNodes(true),
		v = "",
		s = "";
		zTree.expandNode(treeNode,true,false,false,false);
		if(treeNode.id == 0){
			treeNode.nocheck = true;
			zTree.updateNode(treeNode,false);
			return false;
		}
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].regionName;
			s += nodes[i].id;
		}
		_s.attr("value", v);
		_s.next("input[type='hidden']").attr("value",s);
	}

	function showMenu(_this) {
		var zTree = $.fn.zTree.getZTreeObj("select_region_tree");
		zTree.cancelSelectedNode();
		_s = $(_this);
		var cityOffset = _s.offset();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + _s.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	}
	
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	
	function onBodyDown(event) {
		if (!(event.target.id == _s.attr('id') || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}

	$(function(){
	    $.post('after/region/listregion',function(data){
	       $.fn.zTree.init($("#select_region_tree"), select_region_setting, data.result);
	    });
	});
		
</script>