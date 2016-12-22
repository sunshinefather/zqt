<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户组编辑</title>
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
	<div class="current"><s></s><a href="#">首页</a> >> <a href="#">系统管理</a> >> <span>用户组编辑</span></div>
	<div class="btnbox">
		<input type="button" value="保 存" class="btn_pink" id="saveGroupBtn"/>
		<input type="button" value="取 消" class="btn_grey" onclick="location='javascript:history.go(-1)'"/>
		<span>带<b>"*"</b> 的为必填项</span>
	</div>
</div>
<div class="content">
     <dl class="forms">
        <dd><label for="groupName" style="width: 84px;"><span>*</span>用户组名称：</label><input id="groupName" name="groupName" value="${obj.groupName }" type="text" require="require" title="用户组名称"  autofocus/></dd>
       	<dd>
        	<label for="roles" style="width:84px;"><span>*</span>角色：</label><input id="roles"  type="text" value='<c:forEach items="${obj.roles }" varStatus="i" var="role"><c:if test="${i.count > 1 }">,</c:if>${role.roleName }</c:forEach>'  require="require" title="角色"  />
        	<hidden>
	        	<c:forEach items="${obj.roles }" varStatus="i" var="role">
	        		<input  type="hidden" name="roles[${i.index }].id" value="${role.id }"/>
	        	</c:forEach>
        	</hidden>
        </dd>
        <dd id="org_dd"  style="display: none">
        	<label for="org">医院：</label>
        	<input id="org" placeholder="医院用户组必须选择所属医院" readonly="readonly" type="text"  value="<c:choose><c:when test="${not empty(obj.organization) }">${obj.organization.hospitalName }</c:when><c:otherwise>${SESSIONUSER.extUser.organization.hospitalName }</c:otherwise></c:choose>"/>
        	<input type="hidden"  name="organization.hospitalId" value="<c:choose><c:when test="${not empty(obj.organization) }">${obj.organization.hospitalId }</c:when><c:otherwise>${SESSIONUSER.extUser.organization.hospitalId }</c:otherwise></c:choose>"/>
        </dd>
     </dl>
</div>
<script type="text/javascript">
	var groupMap = new HashMap();
	var region_index = "";
	$(function(){
		$("#saveGroupBtn").click(function(){
			var f = $("dl.forms");
			if(!f.vali('validate'))return;
			var u = {
					id:'${obj.id}',
					enabled:'${obj.enabled}'
			};
			var backlist = [];
			f.find("input,select").each(function(index, element) {
				if ($.inArray($(element).attr('id'), backlist) == -1) {
					var $name = $(element).attr('name');
					if($name)
						u[$name] = $(element).val();
				}
			});
			
			$.ajax({
				url:'${pageContext.request.contextPath}/after/group/saveOrUpdate',
				data:u,
				dataType:'json',
				type:'post',
				success:function(resp){
					if(new Number(resp) > 0){
							location.href="${pageContext.request.contextPath}/after/group/list";
					}
				}
			});
		});
		
		$('#roles').on('click', function(){
			var source = $(this);
			$.layer({
				type : 2,
				shade : [0.3 , '#000' , true],
				shadeClose : false,
				fadeIn: 300,
				title : '选择角色',
				offset : ['120px' , '50%'],
				closeBtn : [1 , true],
				border : [0],
				area : ['580px' , '320px'],
				iframe:{src:'${pageContext.request.contextPath}/after/role/toSearch'},
				end: function(){
					var roles = $(document.body).data("roles");
					if(roles){
						var _hiv = "";
						var _v = "";
						for(var i in  roles.elements){
							var role = roles.elements[i];
							_v += role.value + ";";
							_hiv += '<input  type="hidden" name="roles['+i+'].id" value="'+role.key+'"/>';
						}
						source.next("hidden").html(_hiv);
						source.val(_v);
					}
				}
		});
	});
});
</script>
</body>
</html>