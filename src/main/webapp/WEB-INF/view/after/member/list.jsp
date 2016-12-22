<%@ page language="java" contentType="text/html; charset=utf-8" 
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tags/page.tld" prefix="pageTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>政企通管理系统</title>
<meta name="description" content="政企通管理系统" />
<meta name="keywords" content="政企通管理系统" />
<link rel="stylesheet" href="<c:url value="/asset/common/css/layout.css"/>"/>
<link rel="stylesheet" href="<c:url value="/asset/common/css/pop.css"/>"/>
<script type="text/javascript" src="<c:url value="/asset/common/js/jquery-1.8.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/js/jquery.form.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/after/js/wu-style.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/layer/layer.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/js/tools.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/js/layertools.js"/>"></script>
<script type="text/javascript">
	$(function() {
		$('[name=user]').live('click',function(e){
			if ($(this).attr('checked') == 'checked') {
				$(this).parent().parent().addClass('click');
				$(this).attr('checked', true);
			} else {
				$(this).parent().parent().removeClass('click');
				$(this).attr('checked', false);
			}
			e.stopPropagation();
		});
		//全选列表
		$("#checkAll").bind("click",function(){
			var $inputs = $("input[name='user']");
			if($(this).attr("checked") =="checked"){
				$inputs.attr("checked", true);
				$inputs.parents("tr").addClass("click");
			}else{
				$inputs.attr("checked", false);
				$inputs.parents("tr").removeClass("click");
			}
			
		});
	 	
		//删除
		$('#delete').live('click',function(){
			var ids = new Array();
			$('[name=user]:checked').each(function(){
				ids.push($(this).val());
			});
			if (ids.length <= 0)
				layer.alert("请勾选您要删除的会员!");			
			else if (ids.length > 0) {
				layer.confirm("您确定要删除勾选的会员吗？",function(index) {
					window.location.href = '${pageContext.request.contextPath}/after/user/delete/'+ids.toString()+"/2";
					layer.close(index);
				});
			}
		});
		$("#tbody > tr")
				.live(
						'click',
						function() {
							var userId = $(this).attr('userId');
							if (userId != '' && typeof (userId) != 'undefined') {
								location.href = '${pageContext.request.contextPath}/after/member/edit/'
										+ userId;
							}
						});
		$("#importBtn").click(function() {
			dialogdiv("importForm", "导入Excel", 300, 200);
		});
		
		$('#search').on('click', function(){
			var index= $.layer({
				type : 1,
				shade : [0.3 , '#000' , true],
				shadeClose : false,
				fadeIn: 300,
				title : ['用户查询','background:white;'],
				offset : ['120px' , '50%'],
				closeBtn : [1 , true],
				border : [5, 0.5, '#666', true],
				area : ['420px' , '320px'],
				page: {
					dom: '#userSearchForm'
				}
			});
			$("#search_email").focus();
		});	
		$("#orgionName").on('click', function(){
			orgion_index = $.layer({
				type : 1,
				shade : [0.3 , '#000' , true],
				shadeClose : false,
				fadeIn: 300,
				title : ['选择机构','background:white;'],
				offset : ['120px' , '50%'],
				closeBtn : [1 , true],
				border : [5, 0.5, '#666', true],
				area : ['480px' , '320px'],
				page: {
					dom: '#orgoinModal'
				},
				end : function(){
					var _orgion = $("#orgoinModal").data("data");
					if(_orgion){
						_n = _orgion.name;
						_v = _orgion.id;
						_o = _orgion.parentsId;
						$("#orgId").val(_v);
						$("#orgionName").val(_n);
						$("#orgionName").focus();
						$("#title").focus();
						$("#orgionName").focus();
					}
				}
			});
		});
	});
	function save() {
		var options = {
			type : 'POST',
			dataType : 'json',
			beforeSubmit : loadFileValidate,
			success : function(data) {
				var flag = data.status;
				if (flag == "200") {
					location.reload();
				} else {
					layer.alert(data.msg);
				}
			}
		};
		$("#importForm").ajaxSubmit(options);
	}
	/**
	 * 上传验证
	 */
	function loadFileValidate() {
		var filaname = $("#file").val();
		if (!filaname) {
			layer.alert("请选择上传文件！");
			return false;
		}
		var fileExt = filaname.substr(1 + filaname.lastIndexOf("."));
		if (fileExt == "xls" || fileExt == "xlsx") {
			return true;
		}
		layer.alert("不支持 " + fileExt + " 文件格式");
		return false;
	}
</script>
</head>
<body>
<div class="topfixed">
		<div class="current"><s></s><a href="#">首页</a> >> <a href="#">会员管理</a> >> <span>会员列表</span></div>
		
		<div class="btnbox">
			<a href="${pageContext.request.contextPath}/after/member/edit/null"><input type="button" value="新 增" class="btn_pink"/></a>
			<input type="button" value="删除" class="btn_grey" id="delete"/>
			<input id="search" type="button" value="查 询" class="btn_grey"/>
			<input type="button" value="导入" class="btn_grey" id="importBtn">
		</div>
</div>
<form id="importForm" action="<c:url value='/after/member/uploadExcel' />" style="display: none;padding: 30px;" method="post" enctype="multipart/form-data">
      <input type="file" id="file" name="file" value="" style="width:200px">
      <input type="button" value="上传" onclick="save();" >
      <br/>
     <a target="new" href="<c:url value='/download/template/members/xlsx' />">下载导入模板</a>
</form>
<div class="content">
<form action="${pageContext.request.contextPath}/after/member/list" method="post" id="userSearchForm" style="display: none">
		<dl class="forms" style="padding-bottom: 132px;">
			<dd>
				<label for="search_userName">用户名：</label><input id="search_email" value="${userName }" type="text"  name="userName"  style="margin-left: 4px;"/>
			</dd>
			<dd>
				<label for="search_nikeName">昵   称：</label><input  value="${nikeName }" type="text"  name="nikeName"  style="margin-left: 4px;"/>
			</dd>
			 <dd>
				<label for="search_nikeName">机    构：</label><input  value="${orgionName }" type="text"  name="orgName" id="orgionName" readonly="readonly"  style="margin-left: 4px;"/>
				<input  value="${orgId }" type="hidden"  name="orgId" id="orgId"  style="margin-left: 4px;"/>
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
			    <th width="30"><input type="checkbox"  id="checkAll" name="all"></input></th>
			    <th width="80">用户名</th>
			    <th width="80">昵称</th>
			    <th width="120">所属机构</th>
			    <th width="100">注册时间</th>
			    <th width="100">最后一次登录</th>
			   </tr>
		  </thead>
		  <tbody id="tbody">
			  <c:forEach items="${list }" var="user">
			  		<tr userId="${user.id }">
					    <td class="center"><input type="checkbox" value="${user.id }" name="user"></input></td>
					    <td>${user.userName }</td>
					    <td>${user.extUser.nickName }</td>
					    <td>${user.extUser.organization.hospitalName}</td>
					    <td>
					    	<fmt:formatDate value="${user.extUser.registerTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					    </td>
					    <td>
					    	<fmt:formatDate value="${user.extUser.lastActiveTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					    </td>
			  		</tr>
			  </c:forEach>
		  </tbody>
		</table>
	</div>

	<div class="pages">
    	<pageTag:pagin totalPage="${ page.totalPages}" currentPage="${page.pageNo }" uri="${pageContext.request.contextPath}/after/member/list"></pageTag:pagin>
    </div>
</div>
   <div id="orgoinModal" style="display: none">
 	<jsp:include page="/modal/getOrganModel.jsp"></jsp:include>
 </div>
  <input type="hidden" id="basePath" value="${pageContext.request.contextPath}"/>
</body>
</html>