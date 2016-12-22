<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="/WEB-INF/tags/page.tld" prefix="pageTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户编辑</title>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/js/themes/default/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/js/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/lang/zh_CN.js"></script>

</head>
<body>
<div class="topfixed">
	<div class="current"><s></s><a href="#">首页</a> >> <a href="#">系统管理</a> >> <span>用户编辑</span></div>
	<div class="btnbox">
		<input type="button" value="保 存" class="btn_pink" id="saveUserBtn"/>
		<input type="button" value="取 消" class="btn_grey" onclick="location='javascript:history.go(-1)'"/>
		<span>带<b>"*"</b> 的为必填项</span>
	</div>
</div>
<div class="content">
     <dl class="forms">
        <dd>
        	<label for="type" style="margin-right: -4px;">
        		<span>*</span>用户类型：
        	</label>
	        <select id="type" style="width:232px" name="type" require="require" title="用户类型">
		          <option style="display:none; opacity:0" value="">-- 请选择 --</option>
		          <option <c:if test="${obj.type == '0' }">selected="selected"</c:if> value="0">系统管理员</option>
		          		<option <c:if test="${obj.type == '1' }">selected="selected"</c:if> value="1">企业用户</option>
	        </select>
        </dd>
        <dd><label for="userName"><span>*</span>用户名：</label><input id="userName" name="userName" value="${obj.userName }" type="text" require="require" title="用户名"  autofocus/></dd>
        <dd><label for="password"><span>*</span>密码：</label><input id="password" name="password" value="${obj.password }" type="text" require="require" title="密码" /></dd>
        <dd><label for="fullName"><span>*</span>姓名：</label><input id="fullName"  name="extUser.fullName" value="${obj.extUser.fullName }" type="text" require="require" title="姓名" /></dd>
        <dd><label for="nickName">昵称：</label><input id="nickName" name="extUser.nickName" type="text" value="${obj.extUser.nickName }"/></dd>
        <dd>
        	<label for="type">
        		<span></span>用户头像：
        	</label>
        	<c:if test="${empty obj.extUser.avatar}">
        		<img id="img" style="width:75px;height:75px;margin-left: -4px;"  src='<c:url value="/asset/common/images/avatar.png"/>' />
        	</c:if>
        	<c:if test="${not empty (obj.extUser.avatar)}">
		       <img id="img" style="width:75px;height:75px;margin-left: -4px;" src="${obj.extUser.avatarImage.webAdd }"/>
		    </c:if>
        	<input id="avatar" name="extUser.avatar" type="hidden" value="${obj.extUser.avatar }"/>
        	<input id="avatarImageAvatar" name="extUser.avatarImage.webAdd" type="hidden" value="${obj.extUser.avatarImage.webAdd }"/>
        	<a href="javascript:void(0);" class="up_btn">上传头像</a>
        </dd>
        <dd>
        	<label for="type" style="margin-left: -9px;">
        		<span></span>用户职位：
        	</label>
        	<select name="extUser.position" style="width:171px" >
                <option value="">请选择</option>
                <c:forEach items="${positions }" var="p" >
                    <option value="${p.positionId }"  <c:if test="${obj.extUser.position == p.positionId }">selected="selected" </c:if> >${p.positionName}</option>
                </c:forEach>
            </select>
        </dd>
        <dd id="org" <c:if test="${obj.type != '200' }">style="display: none;"</c:if>>
        	<label for="type" style="margin-left: -4px;">
        		<span>*</span>所属机构：
        	</label>
        	<select style="width:140px;" name="extUser.orgId">
        		<option value="">请选择</option>
			    <c:forEach items="${orgList}" var="org" >
        			<option value="${org.hospitalId}"  <c:if test="${obj.extUser.orgId == org.hospitalId }">selected="selected"</c:if>>${org.hospitalName}</option>
       			</c:forEach>
         	</select>
        </dd>
        <dd>
        	<label for="groups" style="margin-left: -4px;"><span>*</span>用户组：</label>
        	<input id="groups" readonly="readonly"  type="text" value='<c:forEach items="${obj.groups }" varStatus="i" var="group"><c:if test="${i.count > 1 }">,</c:if>${group.groupName }</c:forEach>' require="require" title="用户组" />
	        	<span id="groupsSpan">
			    	<c:forEach items="${obj.groups }" varStatus="i" var="group">
					<input type="hidden" name="groups[${i.index }].id"
						value="${group.id }" />
					</c:forEach>
			    </span>
        </dd>
         <dd><label for="email">邮箱：</label><input id="email" name="extUser.email" value="${obj.extUser.email }" type="text"   vtype="email" autofocus/></dd>
        <dd>
	        <label for=" "><span>*</span>性别：</label>
	        <input id="male" style="width:20px" type="radio" name="extUser.gender" value="1"  <c:if test="${obj.extUser.gender != 2 }">checked="checked"</c:if>><label style="width:1px" class="radio">男</label>
	      	<input id="female" style="width: 20px;margin-left: 20px;" type="radio" name="extUser.gender" value="2" <c:if test="${obj.extUser.gender == 2 }">checked="checked"</c:if> ><label style="width:1px" class="radio">女</label>
      	</dd>
        <dd><label for="mobile">电话：</label><input id="mobile" name="extUser.mobile" value="${obj.extUser.mobile }" type="text" vtype="mobile"/></dd>
        <dd><label for="socialAccount">QQ：</label><input id="socialAccount" value="${obj.extUser.socialAccount }"  name="extUser.socialAccount" type="text" vtype="number"/></dd>
     </dl>
     <div id="groupsModal" style="display: none">
      	<jsp:include page="/modal/getGroupsByUserIdModal.jsp"></jsp:include>
      </div>
</div>
<script type="text/javascript">
	var groupMap = new HashMap();
	var group_index = "";
	$(function(){
		$("#type").focus();
		$("#saveUserBtn").click(function(){
			var f = $("dl.forms");
			if(!f.vali('validate'))return;
			var u = {
					id:'${obj.id}'
			};
			var imie = '${obj.imie}';
			if(imie)u['imie'] = imie;
			var _e ='${obj.enabled}';
			if(_e)u.enabled = _e;
			u['extUser.gender'] = $("input[name='extUser.gender']:checked").val();
			var backlist = ["male","female"];
		f.find("input,select:enabled").each(function(index, element) {
				if ($.inArray($(element).attr('id'), backlist) == -1) {
					var $name = $(element).attr('name');
					if($name)
						u[$name] = $(element).val();
				}
			});
			$.ajax({
				url:'${pageContext.request.contextPath}/after/user/saveOrUpdate',
				data:u,
				dataType:'json',
				type:'post',
				success:function(resp){
					if (new Number(resp) > 0){
						location.href="${pageContext.request.contextPath}/after/user/list";
					}else{
						layer.alert("保存失败！");
					}
				}
			});
		});
		
		$('#groups').on('click', function(){
			group_index= $.layer({
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
					$("#groupsSpan").html(_v);
					$("#groups").focus();//再次获得焦点。
			        $("#email").focus();//再次失去焦点，失去焦点相当于验证，这时候就能够正确的验证。再把焦点转移到下一个输入框
				}
			});
		});
		
		KindEditor.ready(function(K) {
			var editor = K.editor({
				allowFileManager : true,
				fieldName : 'imgFile',
				uploadJson:"${pageContext.request.contextPath}/public/upload/",
				afterUpload : function(url, data, name) {//返回的url
					if (data != null && data.error === 0) {
						var _img = $("#img");
						_img.attr("src",url);
						_img.show();
						$("#avatarImageAvatar").val(data.url);
						$('#avatar').val(data.imageId);
					} else {
						alert("文件上传错误");
					}
				}
			});
			K('.up_btn').click(
					function() {
						editor.loadPlugin('image', function() {
							editor.plugin.imageDialog({
								showRemote : false,
								clickFn : function(url, title, width, height,
										border, align) {
									editor.hideDialog();
								}
							});
						});
					});
		});
	   
		
		$('#type').live('change',function(){
			var value = $(this).val(),
				 _dd = $('#org'),
				 _sel = $("select[name='extUser.orgId']");
			if (value == '200'){
				_dd.show();
				_sel.attr("require","require").attr("title","机构");
				_sel.removeAttr("disabled");
			}else{
				_dd.hide();
				_sel.removeAttr("require");
				_sel.removeAttr("title");
				_sel.attr("disabled","disabled");
			}
		});
		
		$('#type').change();
	});
</script>
</body>
</html>