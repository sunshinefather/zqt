<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tags/page.tld" prefix="pageTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<title>政企通管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description" content="政企通管理系统" />
<meta name="keywords" content="政企通管理系统" />
<link rel="stylesheet" href="<c:url value="/asset/common/css/layout.css"/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/asset/common/css/module.css"/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/asset/common/scripts/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/js/themes/default/default.css" />
<script type="text/javascript" src="<c:url value="/asset/common/js/jquery-1.8.3.min.js"/>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/js/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/asset/common/scripts/kindEditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="<c:url value="/asset/common/scripts/layer/layer.js"/>"></script>
<script src="<c:url value="/asset/common/scripts/zTree_v3/js/jquery.ztree.core-3.5.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/asset/common/js/tools.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/asset/common/scripts/vali/css/vali.css"/>"/>
<script type="text/javascript"  src="<c:url value="/asset/common/scripts/vali/vali.js"/>"/></script>
<script type="text/javascript">
var region_index,orgion_index,regId,orgId;
$(function(){
	$("#name").focus();
	$("#regionName").on('click', function(){
		regId = $("#regionId").val();
		orgId = $("#orgionId").val();
		if(orgId==0){
			var type = '${SESSIONUSER.type}';
			var userId = '${SESSIONUSER.id}';
			if(type){
				if(type != '0'){
					 $.post('${pageContext.request.contextPath}/after/region/getRegionsByUserId',{userId:userId},function(data){
					       $.fn.zTree.init($("#regionadd_tree"), regionadd_setting, data.result);
					 });
				}else{
					 $.post('${pageContext.request.contextPath}/after/region/listregion',function(data){
					       $.fn.zTree.init($("#regionadd_tree"), regionadd_setting, data.result);
					  });
				}
			}
		}else{
			$.post('${pageContext.request.contextPath}/after/hospital/getRegionByOrgId',{orgId:orgId},function(data){
				  $.fn.zTree.init($("#regionadd_tree"), regionadd_setting, data.result);
			});
		}
		region_index = $.layer({
			type : 1,
			shade : [0.3 , '#000' , true],
			shadeClose : false,
			fadeIn: 300,
			title : '选择区域',
			offset : ['120px' , '50%'],
			closeBtn : [1 , true],
			border : [5, 0.5, '#666', true],
			area : ['480px' , '320px'],
			page: {
				dom: '#regionsModal'
			},
			end : function(){
				$("#regionName").focus();
				$("#address").focus();
			}
		});
	});
	$("#orgionName").on('click', function(){
			var hospitalId = $("#hospitalId").val();
			orgion_index = $.layer({
				type : 1,
				shade : [0.3 , '#000' , true],
				shadeClose : false,
				fadeIn: 300,
				title : '选择机构',
				offset : ['120px' , '50%'],
				closeBtn : [1 , true],
				border : [5, 0.5, '#666', true],
				area : ['480px' , '320px'],
				page: {
					dom: '#orgoinModal'
				},
				end : function(){
					var _orgion = $("#orgoinModal").data("data");
					var _n = "";
					var _v = "";
					var _o = "";
					var _r = "";
					if(_orgion){
						_n = _orgion.name;
						_v = _orgion.id;
						_o = _orgion.parentsId;
						_r = _orgion.regoinId
					}
					
					if(hospitalId != _v){//不能选自己为自己的所属机构
						$("#parentsId").val(_o);
						$("#orgionName").val(_n);
						$("#orgionId").val(_v);
						if(_r){
							$.post('${pageContext.request.contextPath}/after/hospital/contrastOrganization',{orgId:hospitalId,regId:_r},function(data){
								if(data.result =='1' && _v!=""){
									layer.alert("所属机构与所在区域不符合，请重新选择区域");
									$("#regionName").val("");
									$("#regionId").val("");
									$("#regionName").focus();
								}
							});
						}
					}
				}
			});
	});
	var up = $("#up").val();
	if(up==1){
		$(".content :input").attr("disabled","disabled");
	}else{
		$("#save").show();
		$("#modify").hide();
		$("#name").focus();
	}
	$('.tab  >li').click(function() {
		$('.tab  >li').each(function(e) {
			$(this).removeClass("cur");
		});
		$(this).addClass("cur");
		var ref = $(this).attr("ref");
		$(".tabcont").hide();
		$("#tab_" + ref).show();

	});
	$("#modify").click(function() {
		$(":input").removeAttr("disabled");
		$("#name").focus();
		$("#cancel").show();
		$("#save").show();
		$("#modify").hide();
		editor.readonly(false);
	});
	$("#cancel").click(function() {
		$(".content :input").attr("disabled", "disabled");
		$("#modify").show();
		$("#cancel").hide();
		$("#save").hide();
		editor.readonly();
	});
	$(".table >tbody > tr").click(function() {
		$(".table >tbody>tr").removeClass("click");
		$(this).addClass("click");
	});
	$("#addAchive").click(function() {
		$(":input").removeAttr("disabled");
		$("#cancel").show();
		$("#modify").val("保 存");
	});
	$("#save").click(function() {
		if(!$("#myform").vali('validate')){
			return false;
		}
		var hospitalCode = $("#hospitalCode").val();
		if(hospitalCode){
			$.ajax({
				type : "get",
				url  : '${pageContext.request.contextPath}/after/hospital/valid/'+hospitalCode+'?hospitalId=${updateorgan.hospitalId}',
				success : function(resp) {
					if(resp){
						layer.alert("机构编码已存在",3);
						$("#hospitalCode").focus();
						return false;
					}
					$("#myform").submit();
				}
			});	
		}else{
			$("#myform").submit();
		}
	});
});

var editor = null;
KindEditor.ready(function(K) {
	editor = K.create('#content', {
        resizeType : 1,
        allowPreviewEmoticons : false,
        allowImageUpload : true,
        readonlyMode : true,
        uploadJson:"${pageContext.request.contextPath}/public/upload/",
        afterBlur : function() {
            this.sync();
        }
    });
  var logoEditor = K.editor({
        allowFileManager : true,
        fieldName : 'imgFile',
        uploadJson:"${pageContext.request.contextPath}/public/upload/",
        afterBlur : function() {
            this.sync();
        },
        afterUpload : function(url, data, name) {//返回的url
            if (data != null && data.error === 0) {
                var _img = $("#logoimg");
                _img.attr("src",url);
                _img.show();
                $('#logos').val(data.imageId);
            } else {
                alert("文件上传错误");
            }
        }
    });
    K('#logo').click(function() {
    	logoEditor.loadPlugin('image', function() {
    		logoEditor.plugin.imageDialog({
                     showRemote : false,
                     clickFn : function(url, title, width, height,
                             border, align) {
                    	 logoEditor.hideDialog();
                     }
                 });
             });
   });
    
    var up = $("#up").val();
    if(up==1){
        editor.readonly();
    }else{
    	editor.readonly(false);
    }
    
    
    $("a[opt='delete']").live('click',function(){
		$("#parentsId").val("");
		$("#orgionId").val("");
		$("#orgionName").val("");
	});
    
});

</script>
<style type="text/css">
b{
color:red;
}
</style>
</head>

<body>
<input type="hidden" id="prompt" value="${prompt }"/>
<div class="topfixed">
  <div class="current"><s></s><a href="#">首页</a> >> <a href="${pageContext.request.contextPath}/after/hospital/list">机构管理</a> >> <span>编辑机构</span></div>
  <div class="btnbox">
  <input type="button" value="<< 返回" class="btn_grey goback"  onclick="history.back()"/>
<i></i>
<input type="button" value="修 改" class="btn_pink"  id="modify" />
<input type="button" value="保 存" class="btn_pink"  id="save" style="display:none;"/>
<input type="button" value="取 消" class="btn_grey"  id="cancel" style="display:none;"/>
 <span>带<b>"*"</b> 的为必填项</span>
  </div>
</div>
<div class="content">
<form action="${pageContext.request.contextPath}/after/hospital/update" method="post" id="myform">
<input type="hidden" value="${updateorgan.hospitalId }" name="hospitalId" id="hospitalId">
<input type="hidden"  name="parentsId" value="${updateorgan.parentsId }" id="parentsId"/>
  <div class="">
    <div class="right_bk" style="width:98%;">
      <div style="border:none;" >
        <dl class="cont">
          <dd class="ysn" style=""><label for="height"><b>*</b>机构名称：
          </label><input style="width:229px;" type="text" name="hospitalName" value="${updateorgan.hospitalName }" title="机构名称" id="name"  require="require" maxlength="40"/></dd>
          <em class="clear"></em>
           <dd class="ysn" style="margin-left: -4px;"><label for="height">所属机构：</label>
          			  <input type="hidden" id="orgionId" name="parentId" value="${updateorgan.parentId }"  style="width:228px">
                <input type="text" id="orgionName" name="orgionName" value="${parent }" readonly="readonly"  style="width:228px;"  maxlength="40">
                <span style="margin-left: 5px;"><a href="javascript:void(0);" opt="delete">清除</a></span>
          </dd>
          <em class="clear"></em> 
          <dd class="ysn" style="margin-left: -4px;"><label for="height"><b>*</b>所在区域：</label>
           <input type="hidden" id="regionId" name="regionId" value="${updateorgan.region.id }"  style="width:228px">
           <input type="text" id="regionName" name="regionName" value="${updateorgan.region.regionName }" style="width:228px;" title="所在区域" require="require" maxlength="32">
          </dd>
          <em class="clear"></em>
          <dd class="ysn"><label for="height">机构编码：</label>
                  <input type="text" id="hospitalCode" name="hospitalCode" value="${updateorgan.hospitalCode }"  style="width:228px" >
          </dd>
          <em class="clear"></em>
          <dd class="ysn"><label for="height">机构类型：</label>
                    <pageTag:select name="hospitalType" key="hospitalType" id="hospitalType" defaultValue="${updateorgan.hospitalType }"   style="width:171px" />
          </dd>
          <em class="clear"></em>
          <dd class="ysn"><label for="height">机构密级：</label>
                    <pageTag:select name="securityLevel" key="securityLevel" id="securityLevel" defaultValue="${updateorgan.securityLevel }"   style="width:171px" />
          </dd>
         <em class="clear"></em>
          <dd class="ysn"><label for="height">排序：</label>
                  <input type="text" id="sort" name="sort" value="${updateorgan.sort }"  style="width:228px" vtype="number">
          </dd>
           <em class="clear"></em>
          <dd class="ysn" style=""><label for="height">机构地址： </label><input style="width:229px;" type="text" name="address" value="${updateorgan.address}" title="机构地址"  maxlength="60"/></dd>
          <em class="clear"></em>
          <dd class="ysn" style="height:55px;"><label for="height">logo：</label>
          <c:if test="${empty updateorgan.logo }">
             <img alt="" src="<c:url value="/asset/common/images/default.jpg"/>"   id="logoimg" style="width:50px;height:50px;"/>
           	<input type="button" id="logo" class="btn_grey" value="选择图片"/>(建议大小 88*100)
          	<input style="width:229px;" type="hidden" id="logos" name="logo" title="logo"  maxlength="128"/>
          </c:if>
          <c:if test="${!empty updateorgan.logo }">
            <img alt="" src="${updateorgan.logoImage.webAdd }" id="logoimg" style="width:50px;height:50px;"/>
           	<input type="button" id="logo" class="btn_grey" value="选择图片"/>(建议大小 88*100)
          	<input style="width:229px;" type="hidden" id="logos" name="logo" title="logo" value="${updateorgan.logo }"  maxlength="128"/>
          </c:if>
          </dd>
          <em class="clear"></em>
          <em class="clear"></em>
           <dd class="ysn" style="">
           <label for="height">经度：</label><input style="width:82px;" type="text" name="lng" value="${updateorgan.lng}" title="经度" vtype="latitude"  maxlength="7" />
           					纬度：</label><input style="width:82px;" type="text" name="lat" value="${updateorgan.lat}" title="纬度" vtype="longitude"  maxlength="7"/></dd>
          <em class="clear"></em>
           <dd class="ysn"><label for="contactPhone">联系人：</label><input type="text" id="contact" name="contact"  style="width:228px" maxlength="11"  value="${updateorgan.contact }">
          </dd>
          <em class="clear"></em>
          <dd class="ysn"><label for="contactPhone">联系电话：</label><input type="text" id="contactPhone" name="contactPhone"  value="${updateorgan.contactPhone }" style="width:228px" maxlength="11">
          </dd>
        </dl>
      </div>
    </div>
  </div>
      <em class="clear"></em>
   <ul class="tab">
   	<span style="color:#FF3368;margin-left:10px;"><strong>简介</strong></span>
  </ul>
  <div style="display:block; padding: 5px;" class="tabcont" >
        	 <textarea class="lingyu_text" name="introduction" id="content" 
         	style="float:left;width:98%; height:210px;margin-top:0px; ">${updateorgan.introduction}
  </textarea>
  </form>
</div>
 <div class="clear"></div>
 	 <input type="hidden" id="up" value="${upOrSe }">
      <div id="regionsModal" style="display: none">
       <jsp:include page="RegionsByAdd.jsp"></jsp:include>
      </div>
        <div  id="orgoinModal" style="display: none">
       <jsp:include page="getOrgan.jsp"></jsp:include>
      </div>
        <input type="hidden" id="basePath" value="${pageContext.request.contextPath}"/>
</body>
</html>
