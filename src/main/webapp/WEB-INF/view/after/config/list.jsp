<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib uri="/WEB-INF/tags/page.tld" prefix="pageTag" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<title>政企通管理系统</title>
<meta name="description" content="政企通管理系统" />
<meta name="keywords" content="政企通管理系统" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/layout.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/pop.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/after/js/wu-style.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/layer/layer.min.js"></script>
</head>

<body>
<div class="topfixed">
<div class="current"><s></s><a href="#">首页</a> >> <a href="#">系统管理</a> >> <span>系统属性列表</span></div>

<div class="btnbox">
	<!-- 新增 -->
	<input type="button" value="新 增" class="btn_pink" onclick="location='${pageContext.request.contextPath}/after/config/add'"/>
	<input type="button" value="删 除" class="btn_grey" onclick="javascript:void(0);" id="delete"/>
	<SPAN style="color: red">*系统配置信息请勿随意操作，如需变动请在开发人员或系统维护人员陪同下</SPAN>
	<!-- <input id="search" type="button" value="查 询" class="btn_grey"/> -->
</div>
</div>

<div class="content">
<div>
<table border="0" class="table" id="table">
  <tr>
    <th width="30"><input type="checkbox" value="" name="" id="checkAll"></input></th>
    <th width="10%">属性key</th>
    <th width="10%">属性类型</th>
    <th width="50%">属性值</th>
    <th width="30%">描述</th>
  </tr>
  <c:forEach items="${configs}" var="config">
  	 <tr>
	    <td class="center"><input type="checkbox" class='checkobx' value="${config.configKey}" name="" ></input></td>
	    <td style="word-break:break-all">${config.configKey}</td>
	    <td style="word-break:break-all">${config.configType}</td>
	    <td style="word-break:break-all">${config.configValue}</td>
	    <td style="word-break:break-all">${config.configDis}</td>
	</tr>
  </c:forEach>
</table>
</div>
    <pageTag:pagin totalPage="${page.totalPages}" pageSize="15" currentPage="${page.pageNo }" uri="${pageContext.request.contextPath }/after/config/list"></pageTag:pagin>
</div> 

<!--弹出窗-->
<script type="text/javascript">
$(function(){
	//删除系统配置信息
	//可多个删除
	$("#delete").bind("click",function(){
		var configKeys = new Array();
		$("input[type='checkbox']:checked").each(function(){
			if($(this).val()!==''){
				configKeys.push($(this).val());
			}
		});
		if(configKeys.length==0){
			layer.alert("请选择删除的数据!");
			return;
		}else{
			//提交删除
			layer.confirm("确认删除选中数据?",function(index){
					layer.close(index);
				$.post("${pageContext.request.contextPath}/after/config/delete", {"configKeys[]": configKeys },function(resp){
					var data = resp;
					if(data != "" && data.result==='succ'){
						location.reload();
					}else{
						layer.alert("删除信息失败.");
					}
				},"json");
			});
		}
	});
	
	//全选列表
	$("#checkAll").bind("click",function(){
		if($(this).attr("checked") =="checked"){
			$("input[type='checkbox']").each(function(){
				  $(this).attr("checked", true);  
			});
		}else{
			$("input[type='checkbox']").each(function(){
				  $(this).attr("checked", false);  
			});
		}
	});
	
	$("#table tbody tr td:not(.center)").live('click',function(){
        var $tr = $(this).closest('tr');
        var $id = $tr.find(".checkobx").val();
        location.href = '${pageContext.request.contextPath}/after/config/edit/'+$id;
    });
	
});

</script>
</body>
</html>