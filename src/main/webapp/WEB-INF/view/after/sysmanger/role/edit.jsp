<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色编辑</title>
<meta name="description" content="政企通管理系统" />
<meta name="keywords" content="政企通管理系统" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/layout.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/pop.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/after/js/wu-style.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/vali/vali.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/scripts/vali/css/vali.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/js/tools.js"></script>
</head>
<body>
<div class="topfixed">
	<div class="current"><s></s><a href="#">首页</a> >> <a href="#">系统管理</a> >> <span>角色编辑</span></div>
	<div class="btnbox">
		<input type="button" value="保 存" class="btn_pink " id="saveRoleBtn" disabled="disabled"/>
		<input type="button" value="取 消" class="btn_grey" onclick="location='javascript:history.go(-1)'"/>
		<span>带<b>"*"</b> 的为必填项</span>
	</div>
</div>
<div class="content">
     <dl class="forms">
        <dd><label for="roleName" style="margin-left:4px;"><span>*</span>角色名称：</label><input id="roleName" name="roleName" value="${obj.roleName }" type="text" require="require" title="角色名称"  autofocus/></dd>
       	<dd>
        	<label for="region"><span>*</span>行政区划：</label>
        	<input id="region"  type="text"  value="${obj.region.regionName }" name="region.regionName" require="require" title="行政区划"/>
        	<input id="regionId" type="hidden" name="region.id" value="${obj.region.id }"/>
        </dd>
        <dd>
        	<label for="groups">用户组：</label>
        	<input id="groups"  type="text" value='<c:forEach items="${obj.groups }" varStatus="i" var="group"><c:if test="${i.count > 1 }">,</c:if>${group.groupName }</c:forEach>'/>
        	<hidden>
	        	<c:forEach items="${obj.groups }" varStatus="i" var="group">
	        		<input  type="hidden" name="groups[${i.index }].id" value="${group.id }"/>
	        	</c:forEach>
        	</hidden>
        </dd>
        <dd id="org_dd"  style="display: none">
        	<label for="org">机构：</label>
        	<input id="org" placeholder="机构角色必须选择所属机构" readonly="readonly" type="text"  value="<c:choose><c:when test="${not empty(obj.organization) }">${obj.organization.hospitalName }</c:when><c:otherwise>${SESSIONUSER.extUser.organization.hospitalName }</c:otherwise></c:choose>"/>
        	<input type="hidden"  name="organization.hospitalId" value="<c:choose><c:when test="${not empty(obj.organization) }">${obj.organization.hospitalId }</c:when><c:otherwise>${SESSIONUSER.extUser.organization.hospitalId }</c:otherwise></c:choose>"/>
        </dd>
     </dl>
      <dl class="formlist">  
  		<dt>
	      	<span style="text-align:left; margin-bottom:10px">
	      	<label for="Name">权限分配：</label></span>
	      	<div id="sidertree" style="background: none;overflow: auto;float:none;width: 240px;min-height: 510px;margin-left: -2px;overflow-x: hidden;border-right:none">
			 <div id="menu" style="width: 100%">
			 	<jsp:include page="/modal/getResourcesByUserIdModal.jsp">
			 		<jsp:param value="#saveRoleBtn" name="btnid"/>
			 		<jsp:param value="${obj.id }" name="roleid"/>
			 	</jsp:include>
			 </div>
			</div>
       	</dt>
      </dl>
      <div id="regionsModal" style="display: none">
      	<jsp:include page="/modal/getRegionsByUserIdModal.jsp"></jsp:include>
      </div>
      <div id="groupsModal" style="display: none">
      	<jsp:include page="/modal/getGroupsByUserIdModal.jsp"></jsp:include>
      </div>
</div>
<script type="text/javascript">
	var groupMap = new HashMap();
	var region_index = "";
	var group_index ="";
	$(function(){
		$("#roleName").focus();
		$("#saveRoleBtn").click(function(){
			var f = $("dl.forms");
			if(!f.vali('validate'))return;
			var u = {
					id:'${obj.id}',
					enabled:'${obj.enabled}'
			};
			var resources = $("#menu").data("data");
			if(resources){
				var arr = resources.elements;
				for(var i in arr){
					u['resources['+i+'].id'] = arr[i].value.id;
				}
			}
			var backlist = [];
			f.find("input,select").each(function(index, element) {
				if ($.inArray($(element).attr('id'), backlist) == -1) {
					var $name = $(element).attr('name');
					if($name)
						u[$name] = $(element).val();
				}
			});
			
			$.ajax({
				url:'${pageContext.request.contextPath}/after/role/saveOrUpdate',
				data:u,
				dataType:'json',
				type:'post',
				success:function(resp){
					if(new Number(resp) > 0){
							location.href="${pageContext.request.contextPath}/after/role/list";
					}
				}
			});
		});
		
		$('#region').on('click', function(){
			region_index = $.layer({
				type : 1,
				shade : [0.3 , '#000' , true],
				shadeClose : false,
				fadeIn: 300,
				title : '行政区划',
				offset : ['120px' , '50%'],
				closeBtn : [1 , true],
				border : [5, 0.5, '#666', true],
				area : ['480px' , '320px'],
				page: {
					dom: '#regionsModal'
				},
				end : function(){
					var _region = $("#regionsModal").data("data");
					var _n = "";
					var _v = "";
					if(_region){
						_n = _region.regionName;
						_v = _region.id;
					}
					$("#region").val(_n);
					$("#regionId").val(_v);
					$("#region").focus();//再次获得焦点。
			        $("#groups").focus();//再次失去焦点，失去焦点相当于验证，这时候就能够正确的验证。再把焦点转移到下一个输入框
				}
			});
		});
		
		$('#groups').on('click', function(){
			group_index = $.layer({
				type : 1,
				shade : [0.3 , '#000' , true],
				shadeClose : false,
				fadeIn: 300,
				title : '用户组',
				offset : ['120px' , '50%'],
				closeBtn : [1 , true],
				border : [5, 0.5, '#666', true],
				area : ['480px' , '320px'],
				page: {
					dom: '#groupsModal'
				},
				end : function(){
					var _region = $("#groupsModal").data("data");
					var _s = "";
					var _v = "";
					if(_region){
						var keys = _region.keys();
						for(var i in keys){
							var _id = keys[i];
							var _groupName = _region.get(_id).groupName;
							if(i > 0)_s += ",";
							_s += _groupName;
							_v += "<input type='hidden' name='groups["+i+"].id' value ='"+_id+"'/>";
						}
					}
					$("#groups").val(_s);
					$("#groups").parent().find("hidden").html(_v);
				}
			});
		});
	});
</script>
</body>
</html>