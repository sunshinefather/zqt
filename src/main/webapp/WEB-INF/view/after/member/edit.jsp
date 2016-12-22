<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%> 
<%@ taglib uri="/WEB-INF/tags/page.tld" prefix="pageTag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="page" uri="/WEB-INF/tags/page.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>政企通管理系统</title>
<meta name="description" content="政企通管理系统" />
<meta name="keywords" content="政企通管理系统" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/asset/common/css/layout.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/asset/common/css/pop.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/asset/common/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/asset/after/js/wu-style.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/asset/common/scripts/layer/layer.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/asset/common/scripts/vali/vali.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/asset/common/scripts/vali/css/vali.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/asset/common/js/tools.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/js/themes/default/default.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/js/kindeditor-all-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/lang/zh_CN.js"></script>
</head>
<body>
	<div class="topfixed">
		<div class="current">
			<s></s><a href="#">首页</a> >> <a href="#">会员管理</a> >> <span>会员编辑</span>
		</div>
		<div class="btnbox">
			<input type="button" value="保 存" class="btn_pink" id="saveUserBtn" />
			<input type="button" value="取 消" class="btn_grey"
				onclick="location='javascript:history.go(-1)'" /> <span>带<b>"*"</b>
				的为必填项
			</span>
		</div>
	</div>
	<form id="myform">
		<div class="content">
			<dl class="forms">
				<dd>
					<label for="userName"><span>*</span>用户名：</label><input
						id="userName" name="userName"
						<c:if test="${!empty obj.userName}">disabled="disabled"</c:if>
						value="${obj.userName }" type="text" require="require" title="用户名"
						autofocus />
				</dd>
				<dd>
					<label for="password"><span>*</span>密码：</label><input id="password"
						name="password" value="${obj.password }" type="text"
						require="require" title="密码" />
				</dd>
				<dd>
					<label for="fullName"><span>*</span>姓名：</label><input id="fullName"
						name="extUser.fullName" value="${obj.extUser.fullName }"
						type="text" require="require" title="姓名" />
				</dd>
				<dd>
					<label for="nickName">昵称：</label><input id="nickName"
						name="extUser.nickName" type="text"
						value="${obj.extUser.nickName }" />
				</dd>
	          <dd class="ysn"><label for="height">排序：</label><input type="text" id="sort" name="extUser.sort" value="${obj.extUser.sort }"  vtype="number" title="排序" >
	          </dd>
				<dd>
					<input type="hidden" id="memberId" name="id" value="${obj.id }" />
					<label for="type"> <span></span>用户头像：
					</label>
					<c:if test="${empty obj.extUser.avatar}">
						<img id="img"
							style="width: 75px; height: 75px; margin-left: -4px;" src='<c:url value="/asset/common/images/avatar.png"/>' />
					</c:if>
					<c:if test="${!empty obj.extUser.avatar}">
						<img id="img" style="width: 75px; height: 75px; margin-left: -4px;" src="${obj.extUser.avatarImage.webAdd}" />
					</c:if>
					<input id="avatarImageAvatar" name="extUser.avatarImage.webAdd" type="hidden" value="${obj.extUser.avatarImage.webAdd }"/>
					<input id="avatar" name="extUser.avatar" type="hidden"
						value="${obj.extUser.avatar }" /> <a href="javascript:void(0);"
						class="up_btn">上传头像</a>
				</dd>
				<dd>
					<label for="type" style="margin-left: -9px;"> <span></span>用户职位：
					</label>
				    <select name="extUser.position" style="width:171px" >
				        <option value="">请选择</option>
				        <c:forEach items="${positions }" var="p" >
				            <option value="${p.positionId }"  <c:if test="${obj.extUser.position == p.positionId }">selected="selected" </c:if> >${p.positionName}</option>
				        </c:forEach>
				    </select>
				</dd>
				<dd>
					<label for="type" style="margin-left: -4px;"> <span>*</span>所属机构：
					</label> <select style="width: 140px;" name="extUser.orgId">
						<c:forEach items="${orgList}" var="org">
							<option value="${org.id}">${org.name}</option>
						</c:forEach>
					</select>
				</dd>
				<dd>
					<label for="email">邮箱：</label><input id="email"
						name="extUser.email" value="${obj.extUser.email }" type="text"
						vtype="email" autofocus />
				</dd>
				<dd>
					<label for=" "><span>*</span>性别：</label> <input id="male"
						style="width: 20px" type="radio" name="extUser.gender" value="1"
						<c:if test="${obj.extUser.gender != 2 }">checked="checked"</c:if>><label
						style="width: 1px" class="radio">男</label> <input id="female"
						style="width: 20px; margin-left: 20px;" type="radio"
						name="extUser.gender" value="2"
						<c:if test="${obj.extUser.gender == 2 }">checked="checked"</c:if>><label
						style="width: 1px" class="radio">女</label>
				</dd>
				<dd>
					<label for="mobile">电话：</label><input id="mobile"
						name="extUser.mobile" value="${obj.extUser.mobile }" type="text"
						vtype="mobile" />
				</dd>
				<dd>
                    <label for="mobile">电话2：</label><input id="mobile2"
                        name="extUser.mobile2" value="${obj.extUser.mobile2 }" type="text"
                        vtype="mobile" />
                </dd>
				<dd>
                    <label for="mobile">座机：</label><input id="mobile" name="extUser.tel" value="${obj.extUser.tel }" type="text"  maxlength="11"/>
                </dd>
                <dd>
                    <label for="mobile">座机2：</label><input id="mobile" name="extUser.tel2" value="${obj.extUser.tel2 }" type="text" maxlength="11" />
                </dd>
                <dd>
                    <label for="mobile">传真：</label><input id="mobile" name="extUser.fax" value="${obj.extUser.fax }" type="text" maxlength="11"/>
                </dd>
                <dd>
                    <label for="mobile">传真2：</label><input id="mobile" name="extUser.fax2" value="${obj.extUser.fax2 }" type="text" maxlength="11"/>
                </dd>
				<dd>
					<label for="extUser.hideMobile">隐藏电话：</label> <input 
						style="width: 20px" type="radio" name="extUser.hideMobile" value="0" id="hideMobile"
						<c:if test="${obj.extUser.hideMobile == '0' }">checked="checked"</c:if>><label
						style="width: 1px" class="radio">否</label> 
						<input style="width: 20px; margin-left: 20px;" type="radio"name="extUser.hideMobile" value="1"   id="showMobile"
								<c:if test="${obj.extUser.hideMobile == '1' }">checked="checked"</c:if>/><label
						style="width: 1px" class="radio">是</label>
				</dd>
				<dd>
					<label for="hasSend">发送通知：</label> <input
						style="width: 20px" type="radio" name="hasSend" value="0" id="hasSend"
						<c:if test="${obj.hasSend != true }">checked="checked"</c:if>><label
						style="width: 1px" class="radio">否</label> <input
						style="width: 20px; margin-left: 20px;" type="radio" id="send"
						name="hasSend" value="1"
						<c:if test="${obj.hasSend == true }">checked="checked"</c:if>><label
						style="width: 1px" class="radio">是</label>
				</dd> 
				<dd>
					<label for="socialAccount">QQ：</label><input id="socialAccount"
						value="${obj.extUser.socialAccount }" name="extUser.socialAccount"
						type="text" vtype="number" />
				</dd>
				<dd>
					<label for="socialAccount">岗位描述：</label>
					<textarea rows="" cols="" style="width: 225px; height: 100px;"
						id="description">${obj.extUser.descriptions }</textarea>
					<input type="text" value="" name="extUser.descriptions"
						id="descriptions" style="display: none" />
				</dd>
			</dl>
		</div>
	</form>
	<script type="text/javascript">
	var groupMap = new HashMap();
	var group_index = "";
	$(function(){
		 /**
		* 用户类别单击事件
		*
		*/
		$('#type').live('change',function(){
			var value = $(this).val();
			if (value == '200')
				$('#org').show();
			else
				$('#org').hide();}); 
		$("#img").show();
		$("#type").focus();
		 $("#saveUserBtn").live("click",function(){
				$("#descriptions").val($("#description").val());
				var f = $("dl.forms");
				if(!f.vali('validate'))return;
				var u = {
						id:'${obj.id}',
				};
				u['extUser.gender'] = $("input[name='extUser.gender']:checked").val();
				u['extUser.hideMobile'] = $("input[name='extUser.hideMobile']:checked").val();
				u['hasSend'] = $("input[name='hasSend']:checked").val();
				var backlist = ["male","female","hideMobile","showMobile","hasSend","send"];
				f.find("input,select").each(function(index, element) {
					if ($.inArray($(element).attr('id'), backlist) == -1) {
						var $name = $(element).attr('name');
						if($name){
							u[$name] = $(element).val();
						}
					}
				});
			 $.ajax({
				url:'${pageContext.request.contextPath}/after/member/modify',
				data:u,
				dataType:'json',
				type:'post',
				success:function(resp){
					if (new Number(resp) == 2) {
						layer.alert('用户名已存在!');
					} else if (new Number(resp) > 0){
						location.href="${pageContext.request.contextPath}/after/member/list";
					}
				}
			}); 
		});
	});
		KindEditor.ready(function(K) {
			var editor = K.editor({
				allowFileManager : true,
				fieldName : 'imgFile',
				uploadJson : "${pageContext.request.contextPath}/public/upload/",
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
	
</script>
</body>
</html>