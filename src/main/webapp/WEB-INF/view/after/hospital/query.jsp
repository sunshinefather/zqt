<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
		<div class="current"><s></s><a href="#">企业信息管理</a> >><span>基本信息</span></div>
		
		<div class="btnbox">
			<a href="${pageContext.request.contextPath}/after/hospital/add">
			<input type="button" value="新 增" class="btn_pink"/></a>
			<input id="delete" type="button" value="删除" class="btn_grey"/>
			<input id="search" type="button" value="查 询" class="btn_grey"/>
		</div>
</div>

<div class="content">
	<form action="${pageContext.request.contextPath}/after/hospital/listorgan" method="post" id="userSearchForm" style="display: none">
		<dl class="forms" style="padding-bottom: 132px;">
			<dd>
				<label for="search_userName">企业名称：</label>
				<input id="hospitalName" value="" type="text"  name="hospitalName"  style="margin-left: 4px;"/>
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
			    <th width="20"><input type="checkbox" id="checkAll" value="" name=""></input></th>
			    <th width="85">企业名称</th>
			    <th width="65">企业类别</th>
		        <th width="40">注册资本(万元)</th>
			    <th width="30">是否高新企业</th>
			    <th width="45">高新企业获得时间</th>
			    <th width="45">高新企业结束时间</th>
			    <th width="30">用工人数</th>
			    <th width="60">更新信息</th>
			   </tr>
		  </thead>
		  <tbody>
		    <c:forEach items="${list }" var="organ">
			  <tr style="font-weight:600" userid="${organ.hospitalId}">
			  <td style="word-break:break-all" class="center">
					<input type="checkbox" value="${organ.hospitalId }"  name="user" class="check" ></input>
			    <td style="word-break:break-all">${fn:escapeXml(organ.hospitalName )}</td>
			    <td style="word-break:break-all">${fn:escapeXml(organ.hospitalType )}</td>
			    <td style="word-break:break-all">${fn:escapeXml(organ.registeredCapital )}</td>
			    <td style="word-break:break-all" >${organ.isHighSalary}</td>
			    <td style="word-break:break-all" >${organ.highSalaryStart}</td>
			    <td style="word-break:break-all" >${organ.highSalaryEnd}</td>
			    <td style="word-break:break-all" >${organ.peoples}</td>
			    <td style="word-break:break-all"class="center"><a href="${pageContext.request.contextPath}/after/obtainSupport/list?hospitalId=${organ.hospitalId }">所获扶持</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/after/product/list?hospitalId=${organ.hospitalId }">产品信息</a></td>
			  </tr>
		    </c:forEach>
		  </tbody>
		</table>
	</div>

	<div class="pages">
    	<pageTag:pagin totalPage="${ page.totalPages}" currentPage="${page.pageNo }" uri="${pageContext.request.contextPath}/after/hospital/listorgan"></pageTag:pagin>
    </div>
</div>

<!--弹出窗-->
<script type="text/javascript">
	var map = new HashMap();
	$(function(){
		//删除
		$('#delete').live('click',function(){
			var ids = new Array();
			var names = new Array();
			$('[name=user]:checked').each(function(){
				ids.push($(this).val());
			});
			if (ids.length <= 0)
				layer.alert("请勾选您要删除的数据!");			
			else if (ids.length > 0) {
				layer.confirm("您确定要删除勾选的数据吗？",function(index) {
					$.ajax({
						url:'${pageContext.request.contextPath}/after/hospital/delete?ids='+ids.join("`"),
						dataType:'json',
						type:'get',
						success:function(resp){
							if (resp.status == 1){
								location="${pageContext.request.contextPath}/after/hospital/listorgan";
							}else{
								layer.alert("操作失败！");
							}
							layer.close(index);
						}
					});
				});
			}
		});
		$("#userListTable tbody tr td:not(.center)").live('click',function(){
			var $tr = $(this).closest('tr');
			var $id = $tr.attr('userid');
			location = '${pageContext.request.contextPath}/after/hospital/edit/'+$id;
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

	$('#search').on('click', function(){
		var index= $.layer({
			type : 1,
			shade : [0.3 , '#000' , true],
			shadeClose : false,
			fadeIn: 300,
			title : '查询',
			offset : ['120px' , '50%'],
			closeBtn : [1 , true],
			border : [5, 0.5, '#666', true],
			area : ['420px' , '240px'],
			page: {
				dom: '#userSearchForm'
			}
		});
		$("#title").focus();
	});	
</script>
</body>
</html>