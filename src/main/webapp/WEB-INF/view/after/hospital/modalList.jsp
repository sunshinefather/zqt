<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div class="topfixed">
		<div class="current">
			<form action="${pageContext.request.contextPath}/after/hospital/modalList" method="get" id="searchForm" >
					<label for="search_userName">机构名称：</label><input  value="${hospitalName }" type="text"  name="hospitalName"  style="margin-left: 4px;"/>
					<label for="search_nikeName">机构地址：</label><input  value="${address }" type="text"  name="address"  style="margin-left: 4px;"/>
					<label for="search_nikeName">机构编码：</label><input  value="${hospitalCode }" type="text"  name="hospitalCode"  style="margin-left: 4px;"/>
					<div style="margin-top: 10px;">
						<label for="search_nikeName">机构类型：</label><pageTag:select name="hospitalType" key="hospitalType" id="hospitalType" defaultValue="${hospitalType }"  style="width:158px;margin-left: 4px;" />
						<div style="text-align: right; ">
							<input type="submit" value="查 询" class="btn_grey" id="searchBtn"/>
						</div>
					</div>
			</form>
		</div>
</div>

<div class="content">
	<div>
		<table border="0" class="table" id="ListTable" >
			 <tr>
			    <th width="5"></th>
			    <th width="85">机构名称</th>
			    <th width="30">机构编码</th>
			    <th width="50">机构类型</th>
			    <th width="50">所在区域</th>
			    <th width="80">机构地址</th>
				<th width="65">创建时间</th>
			    </tr>
				<tbody>
			    <c:forEach items="${list }" var="organ">
				  <tr style="font-weight:600" oid="${organ.hospitalId}">
				  	<td style="word-break:break-all" class="ck"></td>
				    <td style="word-break:break-all" data-name="hospitalName">${fn:escapeXml(organ.hospitalName )}</td>
				    <td style="word-break:break-all">${fn:escapeXml(organ.hospitalCode )}</td>
				    <td style="word-break:break-all">${fn:escapeXml(organ.hospitalType )}</td>
				    <td style="word-break:break-all" >${organ.region.regionName}</td>
				    <td style="word-break:break-all">${fn:escapeXml(organ.address) }</td>
					<td><fmt:formatDate value="${organ.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				  </tr>
			    </c:forEach>
				<tbody>
			</table>
	</div>

	<div class="pages">
    	<pageTag:pagin totalPage="${page.totalPages}" currentPage="${page.pageNo }" uri="${pageContext.request.contextPath}/after/hospital/modalList"></pageTag:pagin>
    </div>
</div>

<!--弹出窗-->
<script type="text/javascript">
	$(function(){
		$('#ListTable tbody tr').live('dblclick', function(){
			var self = $(this);
			var data = {
					hospitalId:self.attr("oid"),
					hospitalName:self.find("td[data-name='hospitalName']").html()
			};
			parent.$(window.parent.document.body).data("data",data);
			parent.layer.close(parent.layer.getFrameIndex(window.name));
		});	
	});
</script>
</body>
</html>