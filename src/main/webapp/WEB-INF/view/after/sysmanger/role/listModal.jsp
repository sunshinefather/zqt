<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/pop.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/after/js/wu-style.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/js/tools.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/scripts/zTree_v3/css/zTreeStyle/zTreeStyle.css" />
<script charset="utf-8" type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/zTree_v3/js/jquery.ztree.core-3.5.min.js"></script>
<script charset="utf-8"  type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/zTree_v3/js/jquery.ztree.excheck-3.5.min.js"></script>
</head>
<body>
<div style=" padding:10px;">
<table width="100%" border="0" style="margin-left:-35px; margin-top:0px;" id="searchTable">
  <tr>
    <td width="300"><label for="roleName" class="left_label"  style="margin-left: 30px;">角色名：</label><input id="roleName"  name="roleName" type="text"  autofocus class="sm_text"/></td>
    <td width="229">
    	<label for="regionName" class="left_label">行政区划：</label>
    	<input id="regionName"  type="text" class="bg_text"  onclick="showMenu(this);"/>
    	<input type="hidden" name="regionId" />
    <td width="52"><input type="button" id="searchBtn" value="查 询" class="btn_pink"  style="margin-left:5px;"/></td>
  </tr>
</table>
</div>
<div style="height:200px;overflow: auto;">
<table border="0" class="table" style="padding:5px;" id="k" >
  	<thead>
  		<tr>
		    <th width="40"><input type="checkbox"  name="all"/></th>
		    <th width="70">角色名</th>
		    <th width="100">行政区划</th>
		  </tr>
  	</thead>
	<tbody id="roleTbody"></tbody>
</table>
</div>
<div class="bottombox">
	<div class="right">
		<input type="button" value="保 存" class="btn_pink" id="sure"/>
		<input type="button" value="取 消" class="btn_grey"  id="cancel"/>
	</div>
</div>
<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
	<jsp:include page="/modal/select_menu_radio.jsp" />
</div>
</body>
<script type="text/javascript">
var map = new HashMap();
$(function(){
		$("#searchBtn").click(function(){
			var u = {};
			$('#searchTable').find("input,select").each(function(index, element) {
				var $name = $(element).attr('name');
				var $value = $(element).val();
				if($name && $value)
					u[$name] = $(element).val();
			});
			$.post('${pageContext.request.contextPath}/after/role/search',u,function(resp){
			   if(resp instanceof Array){
					var arr = resp;
					var tbody = $("#roleTbody");
					tbody.html("");
					for(var i in arr){
						var obj = arr[i];
						var tr =  $("<tr><td width='40'><input type='checkbox'  name='role'  value='"
										+ obj.id
										+"'/></td><td width='70' data-name='roleName'>"
										+ obj.roleName
										+ "</td><td width='130'>"
										+ obj.region.regionName + "</td></tr>");
								tr.data("data", obj);
								tr.bind('click',function(){
									var _ = $(this);
									var _cb = _.find(":checkbox");
									var $id = _cb.val();
									var $roleName = $.trim(_.find("td[data-name='roleName']").text());
									if(_.hasClass('click')){
										_.removeClass('click');
										_cb.attr("checked", false);
										map.remove($id);
									}else{
										_.addClass('click');
										_cb.attr("checked", true);
										map.put($id,$roleName);
									}
								});
								tbody.append(tr);
							}
						}
					});
				});
		
		$("#k  thead th input[type='checkbox']").live('click',function(){
			 var chose = $(this);
			 $("#k tbody td input[type='checkbox']").each(function(){
				 var $i = $(this);
				 var $j = (chose.attr("checked")||false);
				 var $tr = $i.closest("tr");
				 $i.attr("checked",$j);
				 var $id = $i.val();
				 var $roleName = $.trim($tr.find("td[data-name='roleName']").text());
				 if(($j?true:false)){
					 map.put($id,$roleName);
					 $tr.addClass("click");
				 }else{
					 map.remove($id);
					 $tr.removeClass("click");
				 }
			 });
		});
		
		$("#sure").click(function(){
			parent.$(window.parent.document.body).data("roles",map);
			parent.layer.close(parent.layer.getFrameIndex(window.name));
		});
		
		$("#cancel").click(function(){
			parent.layer.close(parent.layer.getFrameIndex(window.name));
		});
		
		$("#searchBtn").click();
		
});
</script>
</html>