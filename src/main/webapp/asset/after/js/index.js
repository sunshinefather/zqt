//页面加载完毕执行
$(function(){
	//为dd添加样式
	var dds=$("dd");
	console.log(dds);
	for(var i=0;i<dds.length;i++){
		if(i==3){
			$(dds[3]).addClass("boxfieldset_cont_dd1");
		}else{
			$(dds[i]).addClass("boxfieldset_cont_dd");
		}
	}
});
//增加母亲档案信息
function addMotherInfo(){
	//弹出div，则创建一个div，予以弹出
	if(!$("#addMotherInfo")[0]){
		var div="<div class='addMotherInfo' id='addMotherInfo'>" +
					"<div style='border-bottom:2px dashed gary;'><span style='color:red;font-weight: bold;'>母亲档案查询</span><input type='button' value='关闭' onclick='closeDiv(\"addMotherInfo\");'/></div>"+
					"<div>" +
						"<form action='addMotherInfo'>" +
							""+
						"</form>"+
					"</div>"+
				"</div>";
		$("body").append(div);		
	}
}
//关闭某个对象
function closeDiv(id){
	$("#"+id).remove();
}