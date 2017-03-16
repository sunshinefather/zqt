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
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/combo.select.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/js/jquery.combo.select.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/after/js/wu-style.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/js/tools.js"></script>
</head>
<body>
<div class="topfixed">
		<div class="current"><s></s><a href="#">首页</a> >> <a href="#">系统管理</a> >> <span>用户列表</span></div>
		
		<div class="btnbox">
			<a href="${pageContext.request.contextPath}/after/user/add"><input type="button" value="新 增" class="btn_pink"/></a>
			<input type="button" value="禁 用" class="btn_grey"  id="forbiddenBtn"/>
			<input type="button" value="启用" class="btn_grey"  id="enabledBtn"/>
			<input id="search" type="button" value="查 询" class="btn_grey"/>
			<input id="delete" type="button" value="删除" class="btn_grey"/>
		</div>
</div>

<div class="content">
	<form action="${pageContext.request.contextPath}/after/user/list" method="post" id="userSearchForm" style="display: none">
		<dl class="forms" style="padding-bottom: 132px;">
			<dd>
				<label for="search_userName">用户名：</label><input id="search_email" value="${userName }" type="text"  name="userName"  style="margin-left: 4px;"/>
			</dd>
			<dd>
					<label for="search_type">用户类型：</label>
					<select id="search_type"  name="type" style="display: inline-block;">
			          <option  value="">-- 请选择 --</option>
			          <option <c:if test="${type == '0' }">selected="selected"</c:if> value="0">系统管理员</option>
			          <option <c:if test="${type == '1' }">selected="selected"</c:if> value="1">企业用户</option>
		        </select>
			</dd>
		</dl>
		<div class="bottombox" style="width:420px;">
			<div style="text-align: center; margin-top: 7px;">
				<input type="submit" value="查 询" class="btn_pink" id="searchBtn"/>
			</div>
		</div>
	</form>
	<div>
		<table border="0" class="table" id="userListTable">
		  <thead>
		 	<tr>
			    <th width="30"><input type="checkbox"  name="all"></input></th>
			    <th width="80">用户名</th>
			    <th width="100">用户组</th>
			    <th width="60">用户类型</th>
			    <th width="50">账户状态</th>
			    <th width="100">注册时间</th>
			   </tr>
		  </thead>
		  <tbody>
			  <c:forEach items="${list }" var="user">
			  		<tr userid="${user.id }" >
					    <td class="center"><input type="checkbox" value="${user.id }" name="user" userType="${user.type }"></input></td>
					    <td>${user.userName }</td>
					    <td>
					    	<c:forEach items="${user.groups}" var="group"  >
					    		${group.groupName }
					    	</c:forEach>
					    </td>
					    <td>
					    	<c:if test="${user.type == '0'}">
					    		系统管理员
					    	</c:if>
					    	<c:if test="${user.type == '1'}">
					    		企业用户
					    	</c:if>
					    </td>
					    <td>
					    	<c:choose>
					    		<c:when test="${user.enabled }">
					    			启用
					    		</c:when>
					    		<c:otherwise>
					    			禁用
					    		</c:otherwise>
					    	</c:choose>
					    </td>
					    <td>
					    	<fmt:formatDate value="${user.extUser.registerTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					    </td>
			  		</tr>
			  </c:forEach>
		  </tbody>
		</table>
	</div>

	<div class="pages">
    	<pageTag:pagin totalPage="${ page.totalPages}" currentPage="${page.pageNo }" uri="${pageContext.request.contextPath}/after/user/list"></pageTag:pagin>
    </div>
</div>

<div class="modal fade" id="test">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">
        <p>One fine body&hellip;</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--弹出窗-->
<script type="text/javascript">
	var map = new HashMap();
	$(function(){
		$('#search_type').comboSelect();
		//删除
		$('#delete').live('click',function(){
			var ids = new Array();
			var names = new Array();
			$('[name=user]:checked').each(function(){
				if ('0' != $(this).attr('userType'))
					ids.push($(this).val());
				else {
					var name = $(this).parent().next().text();
					names.push(name);
				}
			});
			if (names.length > 0)
				layer.alert("用户："+names.toString()+"为管理员，无法删除!");
			else {
				if (ids.length <= 0)
					layer.alert("请勾选您要删除的用户!");			
				else if (ids.length > 0) {
					layer.confirm("您确定要删除勾选的用户吗？",function(index) {
						window.location.href = '${pageContext.request.contextPath}/after/user/delete/'+ids.toString()+"/1";
						layer.close(index);
					});
				}				
			}
		});
		$("#userListTable tbody tr td:not(.center)").live('click',function(){
			var $tr = $(this).closest('tr');
			var $id = $tr.attr('userid');
			location.href = '${pageContext.request.contextPath}/after/user/edit/'+$id;
		});
	});
	
	$("input[name='user']").live('click',function(e){
		var $i = $(this);
		var $id = $i.val();
		if(($(this).attr("checked")?true:false)) {
			$(this).parent().parent().addClass('click');
			map.put($id,$id);
		} else {
			$(this).parent().parent().removeClass('click');
			map.remove($id);			
		}
		e.stopPropagation();
	});
	
	$("#userListTable  thead th input[type='checkbox']").click(function(){
		 var chose = $(this);
		 $("#userListTable tbody td input[type='checkbox']").each(function(){
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
			$.post('${pageContext.request.contextPath}/after/user/transform',{ids:ids,state:0},function(resp){
				if(new Number(resp) > 0)location.reload();
			});
		}
	});
	
	$("#enabledBtn").click(function(){
		var ids = "";for(var i in map.values()){
			if(i>0)ids += ",";
			ids += map.values()[i];
		}
		if(ids){
			$.post('${pageContext.request.contextPath}/after/user/transform',{ids:ids,state:1},function(resp){
				if(new Number(resp) > 0)location.reload();
			});
		}
	});

	$('#search').on('click', function(){
		var index= $.layer({
			type : 1,
			shade : [0.3 , '#000' , true],
			shadeClose : false,
			fadeIn: 300,
			title : '用户查询',
			offset : ['120px' , '50%'],
			closeBtn : [1 , true],
			border : [5, 0.5, '#666', true],
			area : ['420px' , '280px'],
			page: {
				dom: '#userSearchForm'
			}
		});
		$("#search_email").focus();
	});	
</script>
</body>
</html>