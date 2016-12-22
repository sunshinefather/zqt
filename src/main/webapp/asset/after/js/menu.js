function transformToTreeFormat(setting, sNodes) {
	var i,l,
	key = setting.idKey,
	parentKey = setting.pIdKey,
	childKey = setting.children;
	if (!key || key=="" || !sNodes) return [];

	if ($.isArray(sNodes)) {
		var r = [];
		var tmpMap = [];
		for (i=0, l=sNodes.length; i<l; i++) {
			tmpMap[sNodes[i][key]] = sNodes[i];
		}
		for (i=0, l=sNodes.length; i<l; i++) {
			if (tmpMap[sNodes[i][parentKey]] && sNodes[i][key] != sNodes[i][parentKey]) {
				if (!tmpMap[sNodes[i][parentKey]][childKey])
					tmpMap[sNodes[i][parentKey]][childKey] = [];
				tmpMap[sNodes[i][parentKey]][childKey].push(sNodes[i]);
			} else {
				r.push(sNodes[i]);
			}
		}
		return r;
	}else {
		return [sNodes];
	}
}

var setting = {
		idKey:'id',
		pIdKey:'parent',
		children:'children',
		name:'resourceName',
		url:'resourceUrl',
		code:'code'
};

function initMenu(menuTree,data){
	var nodes = transformToTreeFormat(setting,data);
	for(var i = 0;i < nodes.length;i++){
		var node = nodes[i],
			  url =node[setting.url],
			  children = node[setting.children]||[];
		if(!url || url === '#')
			url = "javascript:void(0);";
		var li = $('<li><a href="'+url+'" target="frame"><span class="title">'+node[setting.name]+'</span>'+(children.length > 0 ? '<span class="arrow "></span>':'')+'</a></li>');
		li.data("node",node);
		li.on('click',function(){
			initSubMenu($(this));
		});
		menuTree.append(li);
	}
}

function initSubMenu(source){
	if(source.data("isInit") != true){
		var node = source.data("node");
		var children = node[setting.children]||[];
		var ul = $('<ul class="sub-menu"></ul>');
		for(var i = 0;i < children.length;i++){
			var child = children[i],
			  	  url = child[setting.url];
			if(!url || url === '#')
					url = "javascript:void(0);";
			var li = $('<li><a href="'+url+'" target="frame">'+child[setting.name]+'<span class="arrow"></span></a></li>');
			li.data("node",child);
			li.on('click',function(){
				initSubMenu($(this));
			});
			ul.append(li);
		}
		if(children.length > 0)
			source.append(ul);
		source.data("isInit",true);
	}
}

$(document).ready(function(){
	var menuTree = $("#menuTree");
	var reqUrl = menuTree.attr("url");
	$.get(reqUrl,function(resp){
		var data = eval("("+resp+")");
		data = data.result||data.resources;
		initMenu(menuTree,data);
	});
});