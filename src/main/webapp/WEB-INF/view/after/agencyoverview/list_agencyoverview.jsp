<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%> 
<%@ taglib uri="/WEB-INF/tags/page.tld" prefix="pageTag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>政企通管理系统</title>
<link rel="stylesheet" href='<c:url value="/asset/common/css/layout.css"/>'/>
<script type="text/javascript" src='${pageContext.request.contextPath}/asset/common/js/jquery-1.8.3.min.js'></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/asset/after/js/wu-style.js'></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/asset/common/scripts/layer/layer.js'></script>
</head>
<body>
<div class="topfixed">
<div class="current"><s></s><a href="#">首页</a> >> <span>医院概述管理</span></div>
<div class="btnbox">
<input type="button" value="修改" class="btn_grey" onclick="javascript:void(0);" id="update"/>
</div>
</div>
<div class="content">
<div>
<table border="0" class="table" id="ListTable" >
  <tr>
    <th width="20"><input type="checkbox" id="checkAll" ></input></th>
    <th width="100">医院</th>
    <th width="300">主题</th>
  </tr>
<tbody>
    <c:forEach items="${overview }" var="over">
	  <tr style="font-weight:600" value="${over.hospitalId}">
	  <td class="ck"><input type="checkbox" value="${over.hospitalId }"  name="userInput" class="check"></input> </td>
	    <td style="word-break:break-all">${over.hospitalName }</td>
	     <td style="word-break:break-all">
	     <c:forEach items="${over.agencyOverviews }" var="overview">
	     	${overview.title }&emsp;
	     </c:forEach>
	   </td>
	  </tr>
    </c:forEach>
</tbody>
</table>
</div>
<div class="pages">
    <pageTag:pagin totalPage="${page.totalPages}" currentPage="${page.pageNo }" uri="${pageContext.request.contextPath}/after/agencyoverview/list"></pageTag:pagin>
</div>
</div>
<script type="text/javascript">
$(function(){
	//编辑
	$("#ListTable tbody tr td:not(.ck)").live('click',function(){
		var $tr = $(this).closest('tr');
		var $id = $tr.attr('value');
		location="${pageContext.request.contextPath}/after/agencyoverview/update/"+$id+"/1";
	});
	
	//全选
	$("#checkAll").click(function(){
		var self = $(this);
		$("input[name='userInput']").each(function(){
			 var _ck = (self.attr("checked")||false);
			 var ele = $(this);
			 ele.attr("checked",_ck);
			 if(_ck){
				 ele.closest("tr").addClass("click");
			 }else{
				 ele.closest("tr").removeClass("click");
			 }
		});
	});
	
    //修改,单选
	$("#update").bind("click",function(){
		var ids = [];
		$("input[name='userInput']:checked").each(function(i,ele){
			var self = $(ele);
			var _v = self.val();
			if(_v){
				ids.push(_v);
			}
		});
		if(ids.length ==0){
			layer.alert("请选择一条需要修改的数据",0);
			return;
		}
		if(ids.length>1){
			layer.alert("修改的数据只能单选",0);
			return;
		}
		location.href="${pageContext.request.contextPath}/after/agencyoverview/update/"+ids[0]+"/2";
	});
	
	$("input[name='userInput']:checked").each(function(){
		  $(this).closest('tr').attr("class","click");
	});
});
</script>
</body>
</html>
