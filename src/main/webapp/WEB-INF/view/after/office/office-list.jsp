<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
		<div class="current"><s></s><a href="#">首页</a> >> <a href="#">职务管理</a> >> <span>职务列表</span></div>
		
		<div class="btnbox">
			<a href="${pageContext.request.contextPath}/after/office/office-add"><input type="button" value="新 增" class="btn_pink"/></a>
			<input id="search" type="button" value="查 询" class="btn_grey"/>
			<input id="delete" type="button" value="删除" class="btn_grey"/>
		</div>
</div>

<div class="content">
	<form action="${pageContext.request.contextPath}/after/office/office-list" method="post" id="searchForm" style="display: none">
		<dl class="forms" style="padding-bottom: 170px;">
			<dd>
				<label for="search_userName">职务名称：</label><input id="search_officeName" value="${officeName }" type="text"  name="officeName"  style="margin-left: 4px;"/>
			</dd>
		</dl>
		<div class="bottombox" style="width:420px;">
			<div style="text-align: center; margin-top: 7px;">
				<input type="submit" value="查 询" class="btn_pink" id="searchBtn"/>
			</div>
		</div>
	</form>
	<div>
		<table border="0" class="table" id="listTable">
		  <thead>
		 	<tr>
			    <th width="30"><input type="checkbox"  name="all"></input></th>
			    <th width="30%">职务名称</th>
			    <th width="70%">创建时间</th>
			   </tr>
		  </thead>
		  <tbody>
			  <c:forEach items="${list }" var="obj">
			  		<tr oid="${obj.officeId }" >
					    <td class="center"><input type="checkbox" value="${obj.officeId }" name="obj" /></td>
					    <td>${fn:escapeXml(obj.officeName) }</td>
					    <td>
					    	<fmt:formatDate value="${obj.applyTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					    </td>
			  		</tr>
			  </c:forEach>
		  </tbody>
		</table>
	</div>

	<div class="pages">
    	<pageTag:pagin totalPage="${ page.totalPages}" currentPage="${page.pageNo }" uri="${pageContext.request.contextPath}/after/office/office-list"></pageTag:pagin>
    </div>
</div>

<!--弹出窗-->
<script type="text/javascript">
	var map = new HashMap();
	$(function(){
		//删除
		$('#delete').live('click',function(){
			var ids = new Array();
			$('[name=obj]:checked').each(function(){
					ids.push($(this).val());
			});
			$.get('${pageContext.request.contextPath}/after/office/office-deleteBatch/'+ids.join(","),function(resp){
				 if(new Number(resp) > 0)location.reload();
            },"json");
		});
		
		$("#listTable tbody tr td:not(.center)").live('click',function(){
			var $tr = $(this).closest('tr');
			var $id = $tr.attr('oid');
			location.href = '${pageContext.request.contextPath}/after/office/office-edit/'+$id;
		});
		
	});
	
	$("input[name='obj']").live('click',function(e){
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
	
	$("#listTable  thead th input[type='checkbox']").click(function(){
		 var chose = $(this);
		 $("#listTable tbody td input[type='checkbox']").each(function(){
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

	$('#search').on('click', function(){
		var index= $.layer({
			type : 1,
			shade : [0.3 , '#000' , true],
			shadeClose : false,
			fadeIn: 300,
			title : '职务查询',
			offset : ['120px' , '50%'],
			closeBtn : [1 , true],
			border : [5, 0.5, '#666', true],
			area : ['420px' , '280px'],
			page: {
				dom: '#searchForm'
			}
		});
		$("#search_officeName").focus();
	});	
</script>
</body>
</html>