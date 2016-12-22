$(function() {
$(".login-form").validate({
rules:{
userName:{
required:true,
rangelength:[3,20], 
},
password:{
required:true,

}
},
messages:{
userName:{
required:"请输入用户名",
rangelength:"用户名长度在3～20位之间", 
},
password:{
required:"请输入密码",
}
}
})
});

$(document).ready(function(){
//触发tooltip的事件
		 var options={animation:true,rigger:'hover'
			}
		$('.remAutoLogin').tooltip(options);
//更改body背景图
         var random_bg=Math.floor(Math.random()*4+1);
         var bg='url(asset/common/image/bg/lgbg_'+random_bg+'.jpg)';
         $(".login").css("background-image",bg);
//icheckbox单选复选
		  $('#remAutoLogin').iCheck({
			checkboxClass: 'icheckbox_flat-blue',
			radioClass: 'iradio_flat-blue',
		  });  
})
