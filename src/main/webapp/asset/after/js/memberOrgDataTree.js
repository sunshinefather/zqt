/**
 * 会员单位分类
 */
var zTree, rMenu, baseurl, i, curentnode, isimg, orgion_index, isMsg = false, names, orgId, orgName, userAndOrg_index;
$(document).ready(function() {
	initdataTree("dataTree", $('#basePath').val()+"/after/memberOrg/getCategoryList");// 传入初始化数据的url
	rMenu = $("#rMenu");
});

/**
 * 初始化dataTree
 * 
 * @param id
 *            元素Id
 * @param url
 *            请求路径
 */
function initdataTree(id, url) {
	if (!url) {
		layer.alert("参数不能为空!");
		return;
	}
	// ztree设置
	var setting = {
		view : {
			selectedMulti : false
		},
		data : {
			key : {
				name : "name"
			},
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "parentId",
			}
		},
		callback : {
			onRightClick : OnRightClick,
			onClick : OnClick
		}
	};
	// 请求数据
	$.post(url, function(result) {
		$.fn.zTree.init($("#" + id), setting, result.data);
		zTree = $.fn.zTree.getZTreeObj(id);
	});
}
/**
 * 右键单击事件
 * 
 * @param event
 *            事件
 * @param treeId
 *            树ID
 * @param treeNode
 *            树节点
 */
function OnRightClick(event, treeId, treeNode) {
	if (treeNode && !treeNode.noR) {
		zTree.selectNode(treeNode);
		var isdel = "";
		if (treeNode.id == 0 || treeNode.isParent)
			isdel = "root";
		var hasChild = treeNode.hasChild;
		var total = treeNode.total;
		showRMenu(isdel, event.clientX, event.clientY, hasChild, total);
	}
}
/**
 * 左键单击事件
 * 
 * @param event
 *            事件
 * @param treeId
 *            树ID
 * @param treeNode
 *            树节点
 */
function OnClick(event, treeId, treeNode) {
	if (treeNode.id != "0") {
		var node = zTree.getSelectedNodes()[0];
		if (node && node.id != 0) {
			$("#hand").html("会员单位类别信息");
			curentnode = zTree.getSelectedNodes()[0];
			$('#id').val(curentnode.id);
			$('#name').val(curentnode.name);
			$('#parentId').val(curentnode.parentId);
			$('#parentsId').val(curentnode.parentsId);
			$("#showDiv").show();
			$("#showDiv :input").attr("disabled", "disabled");
			$("#save").hide();
			$("#close").hide();
		}
	}

}
/**
 * 显示右键菜单
 * 
 * @param type
 *            类别
 * @param x
 *            纵向高度
 * @param y
 *            横向高度
 * @param hasChild
 *            是否有下级节点
 * @param total
 *            下级节点文章数量
 * 
 */
function showRMenu(type, x, y, hasChild, total) {
	var node = zTree.getSelectedNodes()[0];
	if (node.id == 0) {
		$("#m_del").hide();
		$("#m_update").hide();
	} else {
		$("#m_update").show();
	}
	$("#rMenu ul").show();
	if (type == "root" || hasChild || total > 0) {
		$("#m_del").hide();
	} else {
		$("#m_del").show();
	}
	if (!hasChild && total > 0)
		$("#m_add").hide();
	else
		$("#m_add").show();
	rMenu.css({
		"top" : y + "px",
		"left" : x + "px",
		"visibility" : "visible"
	});
	$("body").bind("mousedown", onBodyMouseDown);
}
/**
 * 内容按下事件
 * 
 * @param event
 */
function onBodyMouseDown(event) {
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
		rMenu.css({
			"visibility" : "hidden"
		});
	}
}
/**
 * 隐藏右键菜单
 */
function hideRMenu() {
	if (rMenu)
		rMenu.css({
			"visibility" : "hidden"
		});
	$("body").unbind("mousedown", onBodyMouseDown);
}
/**
 * 增加节点
 */
function addTreeNode() {
	curentnode = zTree.getSelectedNodes()[0];
	hideRMenu();
	$('#treefrom')[0].reset();
	$('#treefrom .up_btn').attr("src", $('#treefrom .up_btn').attr("default"));
	$("#parentId").val(curentnode.id);
	if (curentnode.id == 0)
		$("#parentsId").val(curentnode.id);
	else
		$("#parentsId").val(curentnode.parentsId + ',' + curentnode.id);
	$('#id').val("");
	$("#showDiv").show();
	$("#hand").html("新增会员单位类别");
	$("#save").show();
	$("#close").show();
	$("#showDiv :input").removeAttr("disabled");
}
/**
 * 删除节点
 */
function removeTreeNode() {
	hideRMenu();
	var node = zTree.getSelectedNodes()[0];
	if (!node.hasChild) {
		layer.confirm("确认删除吗", function(index) {
			layer.close(index);
			var parms = {
				"id" : node.id
			};
			ajaxpost($('#basePath').val()+"/after/memberOrg/delCategoryById/" + node.id, parms, function(data) {
				zTree.removeNode(node);
				$("#showDiv").hide();
			});
		});
	} else {
		layer.alert("该类别下存在下级类别！");
	}

}
/**
 * 更改节点
 */
function updateTreeNode() {
	curentnode = zTree.getSelectedNodes()[0];
	var node = zTree.getSelectedNodes()[0];
	if (node && node.id != 0) {
		curentnode = zTree.getSelectedNodes()[0];
		$('#id').val(curentnode.id);
		$('#name').val(curentnode.name);
		$('#parentId').val(curentnode.parentId);
		$('#parentsId').val(curentnode.parentsId);
		$("#showDiv").show();
		$("#hand").html("修改会员单位类别");
		$("#save").show();
		$("#close").show();
		$("#showDiv :input").removeAttr("disabled");
		$("#name").focus();
	}
	hideRMenu();
}

/**
 * ajax请求
 * 
 * @param url
 *            请求路径
 * @param parms
 *            请求参数
 * @param bankfunc
 *            回调函数
 */
function ajaxpost(url, parms, bankfunc) {
	$.post(url, parms, function(result) {
		if (result.status != 200) {
			layer.alert(result.msg);
		} else {
			if ($.isFunction(bankfunc)) {
				bankfunc(result.data);
			}
		}
	});
}
/**
 * 保存数据
 */
function savedata() {
	if ($("#treefrom").vali('validate')) {
		var jsonparams = $("#treefrom").toJson();
		ajaxpost($('#basePath').val()+"/after/memberOrg/categoryModify", jsonparams, function(data) {
			if (!$('#treefrom #id').val()) {
				zTree.addNodes(curentnode, data);
			} else {
				var node = zTree.getNodeByParam("id", data.id, null);
				$.extend(node, data);
				zTree.updateNode(node);
				curentnode = zTree.getSelectedNodes()[0];
				$("#treefrom").setFrom(curentnode);
				$("#hand").html("会员单位类别信息");
				$("#name").val(data.name);
				$("#save").hide();
				$("#close").hide();
				$("#showDiv :input").attr("disabled", "disabled");
			}
			$("#showDiv").hide();
		});
	}
}
/**
 * 隐藏showDiv
 */
function showDivhide() {
	$("#showDiv").hide();
}

/**
 * 把表单数据转换成json数据(支持单选，复选和下拉菜单到)
 */
$.fn.toJson = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			o[this.name] = o[this.name] + "," + (this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};
/**
 * 把json数据填充到表单(暂不支持单选，复选和下拉菜单到)
 */
$.fn.setFrom = function(jsonObject) {
	if (jsonObject) {
		$.each(jsonObject, function(i) {
			if (typeof i == 'string' && $("#" + i).length > 0) {
				$("#" + i).val(jsonObject[i]);
			}
		});
	}
};