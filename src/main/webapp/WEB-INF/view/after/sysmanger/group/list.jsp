<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tags/page.tld" prefix="pageTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>政企通管理系统</title>
<meta name="description" content="政企通管理系统" />
<meta name="keywords" content="政企通管理系统" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/layout.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/pop.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/after/js/wu-style.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/js/tools.js"></script>
</head>
<body>
<div class="topfixed">
		<div class="current"><s></s><a href="#">首页</a> >> <a href="#">系统管理</a> >> <span>用户组列表</span></div>
		
		<div class="btnbox">
			<a href="${pageContext.request.contextPath}/after/group/add"><input type="button" value="新 增" class="btn_pink"/></a>
			<input type="button" value="禁 用" class="btn_grey"  id="forbiddenBtn"/>
			<input type="button" value="启用" class="btn_grey"  id="enabledBtn"/>
			<input type="button" value="删 除" class="btn_grey"  id="deleteBtn"/>
			<input id="search" type="button" value="查 询" class="btn_grey"/>
		</div>
</div>

<div class="content">
	<form action="${pageContext.request.contextPath}/after/group/list" method="post" id="groupSearchForm" style="display: none">
		<dl class="forms selpanel">
			<dd>
				<label for="search_groupName">用户组名称：</label><input id="search_groupName" value="${groupName }" type="text"  name="groupName"/>
			</dd>
			<dd>
				<label for="search_regionName" style="margin-left:-4px;">行政区划：</label>
				<input id="search_regionName" value="${regionName }" name="regionName" type="text"/>
			</dd>
		</dl>
		<div style="height:137px;"></div>
		<div class="bottombox" style="width:420px;"> 
			<div style="text-align:center;margin-top:7px;">
				<input type="submit" value="查 询" class="btn_pink" id="searchBtn"/>
			</div>
		</div>
	</form>
	<div>
		<table border="0" class="table" id="groupListTable">
		  <thead>
		 	<tr>
			    <th width="30"><input type="checkbox"  name="all"></input></th>
			    <th width="60">用户组名称</th>
			    <th width="80">角色</th>
			    <th width="50">用户组状态</th>
			   </tr>
		  </thead>
		  <tbody>
			  <c:forEach items="${list }" var="group">
			  		<tr groupid="${group.id }" >
					    <td class="center"><input type="checkbox" value="${group.id }" name="group"></input></td>
					    <td>${group.groupName }</td>
					    <td>
					    	<c:forEach items="${group.roles}" var="role"  >
					    		${role.roleName }
					    	</c:forEach>
					    </td>
					    <td>
						    <c:choose>
						    		<c:when test="${group.enabled }">
						    			启用
						    		</c:when>
						    		<c:otherwise>
						    			禁用
						    		</c:otherwise>
					    	</c:choose>
				    	</td>
			  		</tr>
			  </c:forEach>
		  </tbody>
		</table>
	</div>

	<div class="pages">
    	<pageTag:pagin totalPage="${ page.totalPages}" currentPage="${page.pageNo }" uri="${pageContext.request.contextPath}/after/group/list"></pageTag:pagin>
    </div>
</div>

<!--弹出窗-->
<script type="text/javascript">
	var map = new HashMap();
	$(function(){
		$("#groupListTable tbody tr td:not(.center)").live('click',function(){
			var $tr = $(this).closest('tr');
			var $id = $tr.attr('groupid');
			location.href = '${pageContext.request.contextPath}/after/group/edit/'+$id;
		});
	});
	
	$("input[name='group']").live('click',function(){
		var $i = $(this);
		var $id = $i.val();
		if(($(this).attr("checked")?true:false))
			map.put($id,$id);
		else
			map.remove($id);
	});
	
	$("#groupListTable  thead th input[type='checkbox']").click(function(){
		 var chose = $(this);
		 $("#groupListTable tbody td input[type='checkbox']").each(function(){
			 var $i = $(this);
			 var $j = (chose.attr("checked")||false);
			 $i.attr("checked",$j);
			 var $id = $i.val();
			 if(($j?true:false))
				 map.put($id,$id);
			 else
				 map.remove($id);
		 });
	});
	
	$("#forbiddenBtn").click(function(){
		var ids = "";
		for(var i in map.values()){
			if(i>0)ids += ",";
			ids += map.values()[i];
		}
		if(ids){
			$.post('${pageContext.request.contextPath}/after/group/transform',{ids:ids,state:0},function(resp){
				if(new Number(resp) > 0)location.reload();
			});
		}
	});
	
	$("#enabledBtn").click(function(){
		var ids = "";
		for(var i in map.values()){
			if(i>0)ids += ",";
			ids += map.values()[i];
		}
		if(ids){
			$.post('${pageContext.request.contextPath}/after/group/transform',{ids:ids,state:1},function(resp){
				if(new Number(resp) > 0)location.reload();
			});
		}
	});
	
	$("#deleteBtn").click(function(){
		var ids = "";
		for(var i in map.values()){
			if(map.values()[i] =="f5d89ae246064893a8b311b996dc33fe"){//会员组不能删除
				layer.alert("会员组不能删除！");
				return;
			}
			if(i>0)ids += ",";
			ids += map.values()[i];
		}
		if(ids==""){
			layer.alert("请先选择删除数据！");
		}else{
			layer.confirm("确认删除选中数据?",function(index){
				layer.close(index);
				if(ids){
					$.post('${pageContext.request.contextPath}/after/group/deleteBatch',{ids:ids},function(resp){
						if(new Number(resp) > 0)location.reload();
					});
				} 
			});
		}
	});
	$('#search').on('click', function(){
		var index= $.layer({
			type : 1,
			shade : [0.3 , '#000' , true],
			shadeClose : false,
			fadeIn: 300,
			title : '用户组查询',
			offset : ['120px' , '50%'],
			closeBtn : [1 , true],
			border : [5, 0.5, '#666', true],
			area : ['420px' , '287px'],
			page: {
				dom: '#groupSearchForm'
			}
		});
		$("#search_groupName").focus();
	});	
</script>
</body>
</html>