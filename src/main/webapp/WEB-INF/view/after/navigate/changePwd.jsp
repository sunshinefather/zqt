<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>政企通管理系统</title>
<link href="${pageContext.request.contextPath}/asset/common/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/asset/common/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/asset/common/css/bootstrap-theme-wfy.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/asset/common/css/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/asset/common/css/iocn.css" rel="stylesheet">

<script src="${pageContext.request.contextPath}/asset/common/js/jquery-1.11.1.min.js" type="text/javascript" ></script> 
<script src="${pageContext.request.contextPath}/asset/common/js/bootstrap.min.js"  type="text/javascript" ></script> 
<script src="${pageContext.request.contextPath}/asset/common/js/jquery.form.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/asset/common/js/jquery.validate.min.js"></script>
</head>
<body style="background-color: white;">
	<div class="contpad-15" style="margin-top: 40px;">
		<div class="form-lg">
		<form id="ch_password"  method="POST" action="" >
	         <ul>
	          		<li>
						<label>原密码</label>
						<div class="controls">
							<input type="password" name="old_password" id="old_password"/>
						</div>
	                </li>
	                <li>
						<label>设置新的密码</label>
						<div class="controls">
							<input type="password" name="chk_password" id="chk_password"/><br/>
		                    <span class="tp_txt">6-16位，区分大小写，只能使用字母、数字、特殊字符</span>
						</div>
	                </li>
	                 <li>
						<label>重复新的密码</label>
						<div class="controls">
							<input type="password" name="con_password" id="con_password"/>
						</div>
	                </li>  
	       	   <li style="padding-left:90px">
					<button type="button" class="btn btn-primary" id="sv" >保 存</button> 
               </li>
			</ul>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		jQuery.validator.addMethod("pwd", function(value, element){
			var tel = /^(\d+)$/;
			return this.optional(element) || (!tel.test(value));
			}, "不能为纯数字");
		$("#ch_password").validate({
			rules:{
				old_password:{  
				            required:true,  
				},  
				chk_password:{  
				            required:true, 
							rangelength:[6,16],
							pwd:true
				},  
				con_password:{  
		            required:true,
					equalTo:"#chk_password"
				}
			},  
		  messages:{  
			        old_password:{  
			            required:"请输入原密码"
		            },
			        chk_password:{  
			            required:"请输入新密码",  
			            rangelength:"密码长度必须在6-16位之间" 
		            },  
			        con_password:{  
			            required:"请再输入一次新密码",
						equalTo:"两次输入密码不一致" 
		            },
			 }
		});
		$("#sv").click(function(){
            if(!$("#ch_password").valid()){
    			return;	
            }
			var oldPwd = $("input[name='old_password']").val();
			var newPwd = $("input[name='chk_password']").val();
			$("#ch_password").ajaxSubmit({
				dataType:"json",
				url:"${pageContext.request.contextPath}/after/user/updatePwd",
				data:{"userId":"${SESSIONUSER.id}","oldPwd":oldPwd,"newPwd":newPwd},
				success: function(resp){
		   			if(resp && resp.status == 200 && resp.result >= 1){
		   				parent.layer.alert("修改成功",1,function(){
			   				//parent.layer.close(parent.layer.getFrameIndex(window.name));
		   					location="${pageContext.request.contextPath}/logout";
		   				});
		   			}else{
		   				parent.layer.alert("原密码错误");
		   			}
		   		}
			});
		});
	});
</script>
</html>