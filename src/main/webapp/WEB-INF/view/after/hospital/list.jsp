<?xml version="1.0" encoding="utf-8" ?> 
<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tags/page.tld" prefix="pageTag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href='<c:url value="/asset/common/css/layout.css"/>'/>
<link rel="stylesheet" href='<c:url value="/asset/common/css/pop.css"/>'/>
<link rel="stylesheet" href="<c:url value="/asset/common/css/global.css"/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/asset/common/scripts/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>" />
<link rel="stylesheet" href="<c:url value="/asset/common/scripts/zTree_v3/css/zTreeStyle/zTree_exp.css"/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/asset/common/scripts/kindEditor/js/themes/default/default.css"/>" />

<script type="text/javascript" src="<c:url value="/asset/common/js/jquery-1.8.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/layer/layer.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/layer/extend/layer.ext.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/kindEditor/js/kindeditor-all-min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/kindEditor/lang/zh_CN.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/zTree_v3/js/jquery.ztree.core-3.5.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/zTree_v3/js/jquery.ztree.excheck-3.5.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/zTree_v3/js/jquery.ztree.exedit-3.5.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/asset/common/scripts/vali/css/vali.css"/>"/>
<script type="text/javascript"  src="<c:url value="/asset/common/scripts/vali/vali.js"/>"/></script>
<script type="text/javascript" src="<c:url value="/asset/after/js/hospital.js"/>"></script>
<script type="text/javascript">
	$(function(){
		var type = '${SESSIONUSER.type}';
		$("#add").live('click', function(){
			$("#addform").submit();
		});
	 	$.ajax({
					type : "post",
					url  : $('#basePath').val()+"/after/hospital/listorgan",
					success : function(data) {
						$("#showOrhide").html(data);
					}
		});	
	 	
	 	$("div .pages a").live("click",function(){
	 		var self = $(this);
	 		$.ajax({
				type : "post",
				url  : self.attr("href"),
				success : function(data) {
					$("#showOrhide").html(data);
				}
			});	
	 		return false;
	 	});
	 	
	 	$("#searchBtn").live("click",function(){
	 		var frm = $("#searchForm");
	 		var u = {};
	 		var backlist = [];
	 		frm.find("input,select").each(function(index, element) {
				if ($.inArray($(element).attr('id'), backlist) == -1) {
					var $name = $(element).attr('name');
					if($name)
						u[$name] = $(element).val();
				}
			});
	 		$.ajax({
				type : "post",
				url  : '${pageContext.request.contextPath}/after/hospital/listorgan',
				data:u,
				success : function(data) {
					$("#showOrhide").html(data);
					layer.close(index);
				}
			});	
	 	});
	
		//删除系统配置信息
		//可多个删除
		$("#delete").bind("click",function(){
			var ids = [];
			$("input[type='checkbox']:checked").each(function(){
				if($(this).val()!==''){
					ids.push($(this).val());
				}
			});
			if(ids.length==0){
				layer.alert("请选择删除数据");
				return;
			} else{
				//提交删除
				layer.confirm("确认删除选中数据?",function(index){
					layer.close(index);
					$.ajax({
							type : "post",
							url  : $('#basePath').val()+"/after/hospital/delete",
							data :"ids="+ids+"&regionId="+$("#organRegionId").val(),
							success : function(data) {
								$("#showOrhide").html(data);
								layer.alert($("#delmsg").val(),1);
							}
						});
				});
			}
		});
		
		//全选列表
		$("#checkAll").live("click",function(){
			if($(this).attr("checked") =="checked"){
				$("input[type='checkbox']").each(function(){
					var i = $(this).attr("disabled");
					if(i==undefined){
					  $(this).attr("checked", true);  
					  $(this).parents("tr").attr("class","click");
					}
				});
			}else{
				$("input[type='checkbox']").each(function(){
					  $(this).attr("checked", false);  
					  
					  $(this).parents("tr").attr("class","");
				});
			}
			
		});
	 	
		//编辑
		$("#ListTable tbody tr td:not(.ck)").live('click',function(){
			var $tr = $(this).closest('tr');
			var $id = $tr.attr('value');
			location="<c:url value='/after/hospital/query/'/>"+$id+"/1";
		});
		
		//添加点击多选框时，页面记录的展示效果
		$("input[name='userInput']").live('click',function(){
			if(($(this).attr("checked")?true:false))
				$(this).parents("tr").addClass("click");
			else
				$(this).parents("tr").removeClass("click");
		});
		
		
	//修改,单选
		$("#update").bind("click",function(){
			var ids = [];
			$("input[type='checkbox']:checked").each(function(){
				if($(this).val()!==''){
					ids.push($(this).val());
				}
			});
			if(ids.length ==0){
				layer.alert("请选择一条需要修改的数据");
				return;
			}
			if(ids.length>1){
				layer.alert("修改的数据只能单选");
				return;
			}
			location.href="${pageContext.request.contextPath}/after/hospital/query/"+ids[0]+"/2";
		});
	
		$('#search').on('click', function(){
			index= $.layer({
				type : 1,
				shade : [0.3 , '#000' , true],
				shadeClose : false,
				fadeIn: 300,
				title : ['组织机构查询','background:white;'],
				offset : ['120px' , '50%'],
				closeBtn : [1 , true],
				border : [5, 0.5, '#666', true],
				area : ['450px' , '350px'],
				page: {
					dom: '#searchForm'
				}
			});
		});	
	
	});
	function getOrganizationByregionId(){
		$("#showOrhide").html('');
		var regId = $("#organRegionId").val();
		$.ajax({
			type : "post",
			url  : $('#basePath').val()+"/after/hospital/listorgan",
			data :{"regionId" : regId},
			success : function(data) {
				$("#showOrhide").html(data);
			}
		});	
	}
</script>
<title>政企通管理系统</title>
</head>
<body>

<div class="topfixed">
<div class="current" style="font-size:13px;"><s></s><a href="#">首页</a> >> <span>组织机构管理</span></div>
<div class="btnbox">
<input type="button" value="新增" class="btn_pink"  id="add"/>
<input type="button" value="修改" class="btn_grey" onclick="javascript:;" id="update"/>
<input type="button" value="查询" class="btn_grey" onclick="javascript:void(0);" id="search"/>
<input type="button" value="删除" class="btn_grey" id="delete"/>
<!-- 添加表单 -->
<form  action="${pageContext.request.contextPath}/after/hospital/add" method="post" id="addform">
<input id="organRegionId" name="regionId"  type="hidden" value=""/>
<input id="regName" name="regionName" type="hidden" value=""/>
</form>
<input type="hidden" id="basePath" value="${pageContext.request.contextPath}"/>
</div>
</div>
<div class="content">
<div style="float:left; width:16%; height:100%;;overflow:auto;">
  <jsp:include page="getRegionsByUserId.jsp"></jsp:include>
</div>
<div style="float:left;width:2px;height:465px;background-color:#EFF0F4 ">
</div>
<div style="float:left; width:83%; height:100%;margin-top:10px;padding-left:3px; "   id="showOrhide">
	<jsp:include page="query.jsp"></jsp:include>
</div>
 </div> 
<form action="${pageContext.request.contextPath}/after/hospital/listorgan" method="post" id="searchForm" style="display: none">
	<dl class="forms" style="padding-bottom: 132px;">
		<dd>
			<label for="search_userName">机构名称：</label><input  value="${hospitalName }" type="text"  name="hospitalName"  style="margin-left: 4px;"/>
		</dd>
		<dd>
			<label for="search_nikeName">机构地址：</label><input  value="${address }" type="text"  name="address"  style="margin-left: 4px;"/>
		</dd>
		<dd>
			<label for="search_nikeName">机构编码：</label><input  value="${hospitalCode }" type="text"  name="hospitalCode"  style="margin-left: 4px;"/>
		</dd>
		 <dd>
			<label for="search_nikeName">机构类型：</label><pageTag:select name="hospitalType" key="hospitalType" id="hospitalType" defaultValue="${hospitalType }"  style="width:232px;margin-left: 4px;" />
		</dd> 
	</dl>
	<div class="bottombox" style="width:450px;">
		<div style="text-align: center; margin-top: 7px;">
			<input type="button" value="查 询" class="btn_pink" id="searchBtn"/>
		</div>
	</div>
</form>
</body>
</html>