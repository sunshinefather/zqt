/**
 * 知识库分类
 */
var zTree, rMenu, baseurl, i, curentnode, isimg,orgion_index,isMsg=false,names,orgId,orgName,userAndOrg_index;
$(document).ready(function() {
	baseurl = $("#dataTree").attr("url") + "after/knowledgestorecategory/";
	initdataTree("dataTree", "list");// 传入初始化数据的url
	rMenu = $("#rMenu");
	$("#orgName").live('click', function(){
			showOrg();
	});
	$(".xubox_no").live('click', function(){
		$("#orgId").val(orgId);
		$("#orgName").val(orgName);
});
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
				name : "categoryName"
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
	$.post(baseurl + url, function(result) {
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
	if(treeNode.id!="0"){
		$("#orgName").val("");
		$("#orgId").val("");
		var node = zTree.getSelectedNodes()[0];
		if(node.id!=0){
		$("#hand").html("文章类别信息");
		curentnode = zTree.getSelectedNodes()[0];
		$("#treefrom").setFrom(curentnode);
		$.ajax({
			type : "post",
			url :  $('#basePath').val()+"/after/knowledgestorecategory/getCategoryById",
			data : {"id" : node.id},
			success : function(data) {
				var node = zTree.getNodeByParam("id", data.id, null);
				$.extend(node, data);
				zTree.updateNode(node);
				var org =data.data.organization;
				$("#orgName").val(org.name);
				if (data.data.imageId != null)
					$("#show").attr("src",
							 $('#basePath').val()+data.data.imageId);
				else
					$("#show").attr("src", $('#treefrom .up_btn').attr("default"));
			}
			});
		$("#showDiv").show();
		$("#showDiv :input").attr("disabled", "disabled");
		$("#save").hide();
		$("#close").hide();
		isimg = false;
		
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
	if(node.id==0){
		$("#m_del").hide();
		$("#m_update").hide();
	}else{
		$("#m_update").show();
	}
	$("#rMenu ul").show();
	if (type == "root") {
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
	isMsg = false;
	curentnode = zTree.getSelectedNodes()[0];
	//根据当前登录用户获得机构
	if(curentnode.id=="0" ){//当选择了根节点进行添加时，那么就根据当前用户来查询机构
		$.post( $('#basePath').val()+'/after/organization/getOrgionByUserId',function(data){
			  $.fn.zTree.init($("#organ_tree"), orgion_setting, data.result);
		});
	}else{
		$.post( $('#basePath').val()+'/after/knowledgestorecategory/getOrgByparentId',{parentId:curentnode.id},function(data){
			  $.fn.zTree.init($("#organ_tree"), orgion_setting, data.result);
		});
	}
	
	$("#imageId").val("");
	hideRMenu();
	
	$('#treefrom')[0].reset();
	$('#treefrom .up_btn').attr("src", $('#treefrom .up_btn').attr("default"));
	$("#treefrom #parent").val(curentnode.id);
	$('#treefrom #id').val("");
	var levelSeq = 1;
	var levelIndex = curentnode.levelIndex + 1;
	if (curentnode.isParent) {// 如果在父节点上添加子节点
		// 获取父节点的最后一个儿子节点的order+1
		levelSeq = parseInt(curentnode.children[curentnode.children.length - 1].levelSeq) + 1;
	}
	$("#levelSeq").val(levelSeq);
	$("#parentId").val(curentnode.id);
	$("#formatCode").val(curentnode.formatCode);
	$("#levelIndex").val(levelIndex);
	$("#showDiv").show();
	$("#hand").html("新增文章类别");
	$("#save").show();
	$("#close").show();
	$("#showDiv :input").removeAttr("disabled");
	isimg = true;

}
/**
 * 删除节点
 */
function removeTreeNode() {
	hideRMenu();
	var node = zTree.getSelectedNodes()[0];
	if(node.total<=0){
		layer.confirm("确认删除吗", function(index) {
			layer.close(index);
			var parms = {
				"id" : node.id
			};
			ajaxpost("del", parms, function(data) {
				zTree.removeNode(node);
				$("#showDiv").hide();
			});
		});
	}else{
		layer.alert("该类别下有文章，无法删除！");
	}
	
}
/**
 * 更改节点
 */
function updateTreeNode() {
	curentnode = zTree.getSelectedNodes()[0];
	var node = zTree.getSelectedNodes()[0];
	$("#id").val(node.id);
	$("#categoryName").val(node.categoryName);
	isMsg = true;
	hideRMenu();
	$.ajax({
		type : "post",
		url :  $('#basePath').val()+"/after/knowledgestorecategory/getCategoryById",
		data : {"id" : node.id},
		success : function(data) {
			data = data.data;
			if (data.imageId != undefined && data.imageId !=null)
				$("#show").attr("src",
						$('#basePath').val()+data.imageId);
			else
				$("#show").attr("src", $('#treefrom .up_btn').attr("default")); 
			var node = zTree.getNodeByParam("id", data.id, null);
			$.extend(node, data);
			zTree.updateNode(node);
			var org =data.organization;
			$("#orgName").val(org.name);
			$("#treefrom").setFrom(node);
		}
		});
	if(node.id=="0"|| node.parentId == "0"){//当选择了根节点进行添加时，那么就根据当前用户来查询机构
		$.post( $('#basePath').val()+'/after/organization/getOrgionByUserId',function(data){
			  $.fn.zTree.init($("#organ_tree"), orgion_setting, data.result);
		});
	}else{
		$.post( $('#basePath').val()+'/after/knowledgestorecategory/getOrgByparentId',{parentId:node.parentId},function(data){
			  $.fn.zTree.init($("#organ_tree"), orgion_setting, data.result);
		});
	}
	
	$("#showDiv").show();
	$("#hand").html("修改文章类别");
	$("#save").show();
	$("#close").show();
	$("#showDiv :input").removeAttr("disabled");
	isimg = true;
	$("#categoryName").focus();
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
	$.post(baseurl + url, parms, function(result) {
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
	if($("#treefrom").vali('validate')){
		var jsonparams = $("#treefrom").toJson();
		ajaxpost("save", jsonparams, function(data) {
			if (!$('#treefrom #id').val()) {
				zTree.addNodes(curentnode, data);
				$("#showDiv").hide();
			} else {
				var node = zTree.getNodeByParam("id", data.id, null);
				$.extend(node, data);
				zTree.updateNode(node);
				curentnode = zTree.getSelectedNodes()[0];
				$("#treefrom").setFrom(curentnode);
				$("#hand").html("文章类别信息");
				$("#categoryName").val(data.categoryName);
				isimg = false;
				$("#save").hide();
				$("#close").hide();
				$("#showDiv :input").attr("disabled", "disabled");
			}
		});
	}
}
/**
 * 隐藏showDiv
 */
function showDivhide() {
	$("#showDiv").hide();
}

function showOrg(){
	orgion_index = $.layer({
		type : 1,
		shade : [0.3 , '#000' , true],
		shadeClose : false,
		fadeIn: 300,
		title : '选择机构',
		offset : ['120px' , '50%'],
		closeBtn : [1 , true],
		border : [5, 0.5, '#666', true],
		area : ['480px' , '320px'],
		page: {
			dom: '#orgoinModal'
		},
		end : function(){
			$("#orgName").focus();
			$("#name").focus();
			$("#orgName").focus();
			if(isMsg==true && orgId != $("#orgId").val()){
				var nodes = zTree.getSelectedNodes()[0];
				var ipp = nodes.children;
				if(ipp != undefined){//代表有子级分类
					layer.confirm("&nbsp;&nbsp;&nbsp;&nbsp;<span style='color:#88C534'>"+curentnode.categoryName+"</span>&nbsp;&nbsp;下有子级类别，" +
							"修改该类别的所属机构会将该类别的子属类别的机构修改为<span style='color:#88C534'>" +
							$("#orgName").val()+"</span>，您确实要这样执行？",function(index){
						layer.close(index);
					});
				}
			}
		}
	});
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