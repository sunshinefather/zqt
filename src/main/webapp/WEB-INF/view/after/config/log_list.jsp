<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
<script src="${pageContext.request.contextPath}/asset/common/scripts/layer/layer.min.js"></script>
</head>

<body>
<div class="topfixed">
<div class="current"><s></s><a href="#">首页</a> >> <a href="#">系统管理</a> >> <span>系统日志列表</span></div>

<div class="btnbox">
	<input type="button" value="删 除" class="btn_grey" onclick="javascript:void(0);" id="delete"/>
</div>
</div>

<div class="content">
<div>
<table border="0" class="table">
  <tr>
    <th width="10"><input type="checkbox" value="" name="" id="checkAll"></input></th>
    <th width="30">日志编号</th>
    <th width="40">日志时间</th>
    <th width="30">日志类型</th>
    <th width="30">操作人</th>
    <th width="140">操作方法</th>
    <th width="50">日志类容</th>
  </tr>
  <c:forEach items="${logs}" var="log">
  	 <tr>
	    <td style="word-break:break-all"><input type="checkbox" class='checkobx' value="${log.id}" name="" ></input></td>
	    <td style="word-break:break-all">${log.id}</td>
	    <td style="word-break:break-all"><fmt:formatDate value="${log.logTime}" pattern="yyyy/MM/dd kk:mm:ss"/></td>
	    <td style="word-break:break-all">${log.logType}</td>
	    <td style="word-break:break-all">${log.userName}</td>
	    <td style="word-break:break-all">${fn:substringAfter(log.handleMethod,"web.")}</td>
	    <td style="word-break:break-all">${log.logContent}</td>
	</tr>
  </c:forEach>
</table>
</div>
<pageTag:pagin totalPage="${page.totalPages}" pageSize="15" currentPage="${page.pageNo }" uri="${pageContext.request.contextPath }/after/log/list"></pageTag:pagin>
</div> 

<!--弹出窗-->
<script type="text/javascript">
$(function(){
	//删除系统配置信息
	//可多个删除
	$("#delete").bind("click",function(){
		var logIds = [];
		$("input[type='checkbox']:checked").each(function(){
			if($(this).val()!==''){
				logIds.push($(this).val());
			}
		});
		if(logIds.length==0){
			layer.alert("请选择删除的数据!");
			return;
		}else{
			layer.confirm("确认删除选中数据?",function(index){
				layer.close(index);
			//提交删除
				$.post("${pageContext.request.contextPath}/after/log/delete", {"logIds[]": logIds },function(resp){
					var data = eval("(" + resp +")");
					if(data != "" && data.result==='succ'){
						location.reload();
					}else{
						alert("删除信息失败.");
					}
				});
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
});

</script>
</body>
</html>