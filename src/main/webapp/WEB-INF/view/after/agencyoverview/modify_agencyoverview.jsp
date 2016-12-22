<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="page" uri="/WEB-INF/tags/page.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>政企通管理系统</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/layout.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/css/module.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/js/themes/default/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/js/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/layer/layer.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/scripts/vali/css/vali.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/vali/vali.js" /></script>
<style type="text/css">
	.lingyu_text {
		width: 96%;
		height: 230px;
	}
</style>
</head>
<body>
	<div class="topfixed">
		<div class="current">
			<s></s><a href="#">首页</a> >> <a onclick="history.back()">医院概述管理</a> >> <span>查看医院概述</span>
		</div>
		<div class="btnbox">
			<input type="button" value="<< 返回" class="btn_grey goback" onclick="history.back()" /> <i></i> 
			<input type="button" value="修 改" class="btn_pink" id="modify" /> 
			<input type="button" value="保 存" class="btn_pink" id="save" style="display: none;" onclick="submitFrom()" />
			<input type="button" value="新增模块" class="btn_grey" style="display: none;" id="modx"> 
			<input type="button" value="取 消" class="btn_grey" id="cancel" style="display: none;" /> 
			<span>带<b>"*"</b> 的为必填项</span>
		</div>
	</div>
	<div class="content">
		<div class="right_bk" style="width: 98%;">
			<div style="border: none;">
				<dl class="cont">
					<dd>
						<label style="margin-left: -35px;"><span
							style="color: red">*</span>医院:</label> <input type="text"
							style="width: 530px;" id="orgionName"
							value="${org.hospitalName }" title="医院"
							require="require" /> 
							<input type="hidden" value="${org.hospitalId }" id="orgId"/>
					</dd>
					<em class="clear"></em>
				</dl>
			</div>
			<c:if test="${sequence >0}">
			<c:forEach items="${agencyOverview }" var="agen">
			<form action="" id="dateform${agen.id }">
				<div id="mod">
					<dd>
						<label style="margin-left: 15px;"><input type="checkbox"
							checked="checked" id="checks${agen.id }" ><span style="color: red">*</span>主题:</label>
						<input type="text" style="width: 530px;" name="summary" maxlength="15"
							value="${agen.title }" id="title${agen.id }" title="主题"
							require="require" />
					</dd>
					<em class="clear"></em> <br>
					<dd>
						<label style="margin-left: 30px;"><span style="color: red">*</span>顺序:</label>
						<input type="text" style="width: 130px;" require="require"
							vtype="number" id="sx${agen.id }" title="顺序" value="${agen.sequence }" />
					</dd>
					<div id="tabk">
						<ul class="tab">
							<span style="color: #FF3368; margin-left: 28px;"><strong>内容</strong></span>
						</ul>
					</div>
					<div style="display: block; padding: 5px; margin-left: 28px;"
						class="tabcont">
						<textarea class="lingyu_text one" name="content" id="content${agen.id }" 
							style="float: left; width: 98%; height: 230px; margin-top: 0px;">${agen.content }
  				</textarea>
					</div>
			</form>
			</c:forEach>
			</c:if>
			<c:if test="${sequence <=0}">
				<form action="" id="dateform${agen.id }">
				<div id="mod">
					<dd>
						<label style="margin-left: 15px;"><input type="checkbox"
							checked="checked" id="checks${agen.id }" ><span style="color: red">*</span>主题:</label>
						<input type="text" style="width: 530px;" name="summary" maxlength="15"
							value="${agen.title }" id="title${agen.id }" title="主题"
							require="require" />
					</dd>
					<em class="clear"></em> <br>
					<dd>
						<label style="margin-left: 30px;"><span style="color: red">*</span>顺序:</label>
						<input type="text" style="width: 130px;" require="require"
							vtype="number" id="sx${agen.id }" title="顺序" value="${agen.sequence }" />
					</dd>
					<div id="tabk">
						<ul class="tab">
							<span style="color: #FF3368; margin-left: 28px;"><strong>内容</strong></span>
						</ul>
					</div>
					<div style="display: block; padding: 5px; margin-left: 28px;"
						class="tabcont">
						<textarea class="lingyu_text one" name="content" id="content${agen.id }" 
							style="float: left; width: 98%; height: 230px; margin-top: 0px;">${agen.content }
  				</textarea>
					</div>
			</form>
			</c:if>
		</div>
		
	</div>
	<div id="mods"></div>
	<div class="clear"></div>
	<!-- </form> -->
	<input type="hidden" id="up" value="${upOrSe }">
	<input type="hidden" id="basePath" value="${pageContext.request.contextPath}" />
	<form action="${pageContext.request.contextPath}/after/agencyoverview/updatedata" id="overFrom" method="post">
		<div id="overDiv">
		</div>
	</form>
</body>
<script type="text/javascript">
	var $root = $('html, body');
	var i = '${sequence}';
	var ol= i;
	$(document).ready(function() {
		$("input[id^='checks']").live("click",function(){
			var self = $(this);
			var _c = (self.attr("checked")||false);
			if(!_c){
				if(confirm("确定要清空数据吗？")){
					self.attr("checked",false);
				}else{
					self.attr("checked",true);
				}
			}
		});
		
		if(ol<=0){
			i=1;
			ol=1;
		}
		var up = $("#up").val();
		if (up == 1) {
			$(".content :input").attr("disabled", "disabled");
			$("#orgionName").attr("disabled", "disabled");
		} else {
			$("#orgionName").attr("disabled", "disabled");
			$("#save").show();
			$("#modify").hide();
			$("#modx").show();
		}
		$("#cancel").live("click", function() {
			$(".content :input").attr("disabled", "disabled");
			$("#modify").show();
			$("#cancel").hide();
			$("#save").hide();
			$("#modx").hide();
		});
		$("#addAchive").live("click", function() {
			$(":input").removeAttr("disabled");
			$("#cancel").show();
			$("#modify").val("保 存");
		});
	});

	function submitFrom() {
		$("#overDiv").html("");
		var submit = true;
		var _f = $("input[type='checkbox']:checked");
		var datas = "";
		for(var i = 0;i < _f.length;i++){
			var self = $(_f[i]);
			var y = self.attr("id").substring(6, self.attr("id").length);
			var content = $.trim($("#content" + y).val());
			if(!$("#dateform" + y).vali('validate')){
				submit = false;
				break;
			}else if(!content){
				submit = false;
				layer.alert("请填写内容!",0);
				break;
			}else{
				datas += "<input  name='agencyOverviews["+i+"].title' value='"+$("#title" + y).val()+"'/>";
				datas += "<input  name='agencyOverviews["+i+"].sequence' value='"+$("#sx" + y).val()+"'/>";
				datas += "<input  name='agencyOverviews["+i+"].orgId' value='"+$("#orgId").val()+"'/>";
				datas += "<input  name='agencyOverviews["+i+"].content' value='"+content+"'/>";
			}
		}
		if (submit) {
			$("#overDiv").html(datas);
			$("#overFrom").submit();
		}
	}

	var _editor = null;
	KindEditor
			.ready(function(K) {
				$("#orgionName").attr("disabled", "disabled");
				var opp = "";
				_editor = K
						.create(
								'.one',
								{
									uploadJson : "${pageContext.request.contextPath}/public/upload/",
									afterBlur : function() {
										this.sync();
									}
								});
				$('#modify').live("click", function(K) {
					$(":input").removeAttr("disabled");
					$("#orgionName").attr("disabled", "disabled");
					$("#title").focus();
					$("#cancel").show();
					$("#save").show();
					$("#modify").hide();
					$("#modx").show();
				});
				$('#modx')
						.live(
								"click",
								function() {
									//记录值 
									var agen = new Array();
									for (var p = 2; p <= i; p++) {
										var parm = [
												$("#title" + p).val(),
												$("#sx" + p).val(),
												$("#content" + p).val(),
												$("#checks" + p)
														.attr("checked") ];
										agen.push(parm);
									}
									//清空html 清空后可以解决富文本创建多个以及富文本无法编译的bug
									$("#mods").html("");
									i++;
									opp += "<form   id='dateform"+i+"'>"
											+ "<dd>"
											+ "<label style='margin-left:15px;'><input type='checkbox'  id='checks"+i+"'><span style='color:red'>*</span>主题:</label>"
											+ " <input type='text'  style='width:530px;' name='summary'  id='title"+i+"' title='主题' require='require' maxlength='15'/>"
											+ "</dd>"
											+ "<em class='clear'></em>"
											+ "<br>"
											+ "<dd>"
											+ "<label style='margin-left:30px;'><span style='color:red'>*</span>顺序:</label>"
											+ "<input type='text'  style='width:130px;'  require='require' vtype='number' title='顺序' value='"+i+"' id='sx"+i+"'/>"
											+ "</dd>"
											+ $("#tabk").html()
											+ "<div style='isplay:block; padding: 5px; margin-left: 28px;' >"
											+ "<textarea class='lingyu_text' name='content' id='content"
											+ i
											+ "'"
											+ "style='width:96%; height:230px;margin-top:0px;'>"
											+ "</textarea></div></form>";
									$("#mods").html(opp);//重新赋值给div
									for (var p = 2; p <= i; p++) {//循环创建富文本
										if (p < i) {//重新创建富文本后会清空原先的值，把值赋值给原先输入框以及富文本
											if (agen[p - 2][3] != undefined) {
												$("#checks" + p).attr(
														"checked", "checked");
											}
											$("#content" + p).html(
													agen[p - 2][2]);
											$("#sx" + p).val(agen[p - 2][1]);
											$("#title" + p).val(agen[p - 2][0]);
										}
										K
												.create(
														'#content' + p,
														{
															uploadJson : "${pageContext.request.contextPath}/public/upload/",
															afterBlur : function() {
																this.sync();
															}
														});
									}
								});
			});
</script>
</html>
