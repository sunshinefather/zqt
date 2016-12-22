/**
 * 电子书刊
 */
var zTree, rMenu, baseurl,isImg=true,jump="";
$(document).ready(function() {
	baseurl = $("#electronic").attr("url") + "after/electronic/";
	initdataTree("electronic", "getChapter/"+$("#bookId").val());// 传入初始化数据的url
	rMenu = $("#rMenu");
	$("#title").attr("disabled","disabled");
	$("#sequence").attr("disabled","disabled");
	$("#saveBook").live("click",function(){//保存书籍信息
		if($("#bookfromf").vali('validate')){//验证，验证通过时在进行修改
			if($("#logo").val()!=''){
				if(isImg){
					var parm = {"id":$("#id").val(),"name":$("#name").val(),"anthor":$("#anthor").val(),"orgId": $("#orgId").val(),"year":$("#year").val(),
							"logo":$("#logo").val(),"power":$("#powers").val(),"cycle":$("#cycle").val(),"introduction":$("#introduction").val()};
					 $.ajax({
							type : "post",
							url :  $('#basePath').val()+"/after/electronic/update",
							data : parm,
							success : function(data) {
								//修改节点信息
								var bookTree = zTree.getNodeByTId("electronic_1");
								bookTree.title = $("#name").val();
								zTree.updateNode(bookTree);
								window.location.href=$('#basePath').val()+"/after/electronic/list";
							}
						});
				}else{
					layer.alert("原logo已不存在，请选择新的logo");
				}
			}else{layer.alert("logo不能为空");}
		}
	});
	$("#saveChapter").live("click",function(){
		var node = zTree.getSelectedNodes()[0];//获取到节点
		if(node ==undefined ){//得不到节点信息 ,不许操作！
			layer.alert("得不到节点信息!请选择节点。");
		}else{
			if(jump =="add"){
				if($("#chapterFrom").vali('validate')){//通过验证
					 var parm = {"title":$("#title").val(),"sequence":$("#sequence").val(),"bookId":$("#id").val(),
							 "content":$("#content").val(),"parentId":$("#parentId").val()
							 };
					 $.ajax({
						type : "post",
						url :  $('#basePath').val()+"/after/electronic/addChapter",
						data : parm,
						success : function(data) {
							if (data.status == "200") {
								zTree.addNodes(node, data.data);//添加节点
								 $("#title").attr("disabled","disabled");
								 $("#sequence").attr("disabled","disabled");
								 _editor.readonly();
								layer.alert("添加成功！");
							} else {
								layer.alert("添加失败！");
							}
						}
					});
				}
			}else if(jump =="update"){//修改
					if($("#chapterFrom").vali('validate')){
					var parm = {"id":node.id,"title":$("#title").val(),"sequence":$("#sequence").val(),"bookId":$("#id").val(),
							 "content":$("#content").val(),"parentId":$("#parentId").val()
							 };
					 $.ajax({
						type : "post",
						url :  $('#basePath').val()+"/after/electronic/updateChapter",
						data : parm,
						success : function(data) {
							if (data.status == "200") {
								$.extend(node,data.data);
								zTree.updateNode(node);
								 $("#title").attr("disabled","disabled");
								 $("#sequence").attr("disabled","disabled");
								 _editor.readonly();
								layer.alert("修改成功！");
							} else {
								layer.alert("添加失败！");
							}
						}
					});
				}
			}
		}
	});
	//关闭弹出提示框
	$("#close1").live('click', function(){
		layer.close(orgName);
	});
	$("#close2").live('click', function(){
		layer.close(userAndOrg_index);
	});
});

function noImg(){
	isImg = false;
}

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
				name : "title"
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
	jump = "";
	 _editor.html("");
	if (treeNode && !treeNode.noR) {
		zTree.selectNode(treeNode);
		var isdel = "";
		if (treeNode.id == 0 || treeNode.isParent)
			isdel = "root";
		showRMenu(isdel, event.clientX, event.clientY,treeNode);
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
	jump = "";
	 hideRMenu();
	 _editor.html("");
	 if(treeNode.bookId !=null){
		 $.ajax({
				type : "post",
				url :  $('#basePath').val()+"/after/electronic/getChapterById",
				data : {"id":treeNode.id},
				success : function(data) {
					 $("#chapter").html(data);
					 $("#title").attr("disabled","disabled");
					 $("#sequence").attr("disabled","disabled");
					 _editor.readonly();
				}
			});
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
function showRMenu(type, x, y,node) {
	$("#rMenu ul").show();
	if(node.parentId=="0" || node.parentId==null){//父节点id为0，则代表为书本名称，只可以添加
		$("#m_del").hide();
		$("#m_add").show();
		$("#m_update").hide();
	}else{
		if (node.level >= 2) {//只能添加两级目录
			$("#m_del").show();
			$("#m_add").hide();
			$("#m_update").show();
		}else if (node.content == "" || node.content == null) {//可以有下级类别，则禁用富文本输入框。有下级则无法添加内容
			$("#m_del").show();
			$("#m_add").show();
			$("#m_update").show();
		} else if (node.content != "" && node.content != null){//不能有下级类别，无法新增下级类别。必先删除其内容
			$("#m_del").show();
			$("#m_add").hide();
			$("#m_update").show();
		}
	}
	if(node.children !=undefined && node.children.length>0 ){//该节点存在子节点，不能直接删除！
		$("#m_del").hide();
	}
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
	 _editor.readonly(false);
	 hideRMenu();
	 var node = zTree.getSelectedNodes()[0];
	 $("#parentId").val(node.id);
	 jump = "add";
	 //清空输入框
	 $("#title").val("");
	 $("#sequence").val("");
	 $("#content").val("");
	 $("#title").removeAttr("disabled");
	 $("#sequence").removeAttr("disabled");
	 _editor.readonly(false); 
}

/**
 * 删除节点
 */
function removeTreeNode() {
	jump = "";
	hideRMenu();
	var node = zTree.getSelectedNodes()[0];
	layer.confirm("确认删除吗?", function(index) {
		layer.close(index);
		var parms = {
			"id" : node.id
		};
		 $("#title").val("");
		 $("#sequence").val("");
		 $("#content").val("");
		 $("#title").attr("disabled","disabled");
		 $("#sequence").attr("disabled","disabled");
		 _editor.readonly(); 
		ajaxpost("del", parms, function(data) {
			zTree.removeNode(node);
		});
	});
}
/**
 * 更改节点
 */
function updateTreeNode() {
	 jump = "update";
	 hideRMenu();
	 var node = zTree.getSelectedNodes()[0];
	 if(node.bookId !=null){
		 $.ajax({
				type : "post",
				url :  $('#basePath').val()+"/after/electronic/getChapterById",
				data : {"id":node.id},
				success : function(data) {
					 $("#chapter").html(data);
					 $("#title").removeAttr("disabled");
					 $("#sequence").removeAttr("disabled");
					 if(node.children!=undefined && node.children.length >0){//有子级禁用富文本
						 _editor.readonly();
					 }else{
						 _editor.readonly(false); 
					 }
				}
			});
	 }
}

function disableds(){
	$("#title").attr("disabled","disabled");
	$("#sequence").attr("disabled","disabled");
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