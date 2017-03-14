<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tags/page.tld" prefix="pageTag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="zh-cn">
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>意见反馈管理</title>
<link rel="stylesheet" href='<c:url value="/asset/common/css/layout.css"/>'/>
<link rel="stylesheet" href='<c:url value="/asset/common/css/pop.css"/>'/>
<script type="text/javascript" src='<c:url value="/asset/common/js/jquery-1.8.3.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/asset/after/js/wu-style.js"/>'></script>
<script type="text/javascript" src='<c:url value="/asset/common/scripts/layer/layer.js"/>'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/js/tools.js"></script>
</head>
<body>
<div class="topfixed">
<div class="current"><s></s><a href="#">首页</a> >> <span>意见反馈管理</span></div>
<div class="btnbox">
<input type="button" value="删除" class="btn_grey" id="delete"/>
</div>
</div>
<div class="content">
<div>
<table border="0" class="table" id="ListTable" >
	<thead>
	  <tr>
	    <th width="20"><input type="checkbox" id="checkAll"  name="all"></input></th>
	    <th width="80">用户名</th>
	    <th width="100">问题来源</th>
	    <th width="250">意见</th>
	    <th width="100">时间</th>
	    <th width="50">操作</th>
	    </tr>
	</thead>
	<tbody>
	    <c:forEach items="${list }" var="obj">
		  <tr style="font-weight:600" value="${obj.feedBackId}">
			  	<td class="ck"><input type="checkbox" value="${obj.feedBackId }"  name="feedback" class="check"></input></td>
			    <td style="word-break:break-all">${obj.remoteUser.remoteExtUser.fullName }</td>
			    <td>
			    	<c:choose>
   						<c:when test="${obj.remoteUser.type eq '300'}">孕宝客户端</c:when>
    					<c:when test="${obj.remoteUser.type eq '201'}">孕宝医生端</c:when>
    					<c:otherwise></c:otherwise>
					</c:choose>
			    </td>
			    <td>${fn:substring(obj.content,0,40) }</td>
				<td><fmt:formatDate value="${obj.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><a class="reply" id="${obj.feedBackId }" href="javascript:void(0)">
					<c:choose>
   						<c:when test="${obj.webReadState}">已回复</c:when>
    					<c:otherwise>未回复</c:otherwise>
					</c:choose>
				</a></td>
		  </tr>
	    </c:forEach>
	<tbody>
</table>
</div>
<div class="pages">
    <pageTag:pagin totalPage="${page.totalPages}" currentPage="${page.pageNo }" uri="${pageContext.request.contextPath}/after/feedback/feedback_base_list"></pageTag:pagin>
</div>

<script type="text/javascript">
	var map = new HashMap();
	$(function(){
		
		$("input[name='feedback']").live('click',function(){
			var $i = $(this);
			var $id = $i.val();
			if(($(this).attr("checked")?true:false))
				map.put($id,$id);
			else
				map.remove($id);
		});
		
		$("#ListTable  thead th input[type='checkbox']").click(function(){
			 var chose = $(this);
			 $("#ListTable tbody td input[type='checkbox']").each(function(){
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
		
		$("#delete").click(function(){
			var ids = "";
			for(var i in map.values()){
				if(i>0)ids += ",";
				ids += map.values()[i];
			}
			if(ids.length>0){
			layer.confirm("确认删除选中数据?",function(index){
				layer.close(index);
				if(ids){
					$.post('${pageContext.request.contextPath}/after/feedback/feedback_base_batchDelete',{ids:ids},function(resp){
						if(new Number(resp) > 0)location.reload();
					});
				}
			});
			}else{
				layer.alert("请先选择数据!",0);
			}
		});
		
		$("#ListTable tbody tr td:not(.ck)").live('click',function(){
			var self = $(this);
			var _tr = self.closest("tr");
			$.layer({
				type : 2,
				shade : [0.3 , '#ccc' , true],
				shadeClose : true,
				fadeIn: 300,
				title : ['意见回复','background:white;'],
				offset : ['120px' , '50%'],
				closeBtn : [1 , true],
				border : [0],
				area : ['680px' , '420px'],
				iframe:{src:'${pageContext.request.contextPath}/after/feedback/feedback_base_detail/'+_tr.attr('value')}
			});
		});	
		
	});
</script>
</body>
</html>
