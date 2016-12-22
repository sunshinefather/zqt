/**
 * 验证框架
 * @author 刘创
 */
(function($) {
var methods = {
	init : init,
	validate : validate,
	unvalidate: unvalidate
};

function init() {
	var fields = $("input,select,textarea");
	$.each(fields, function(i, field){
		initElement($(field));
	});
};

function initElement(el) {
	if(el.hasClass("readonly")) {
		return false;
	}
	el.bind("focusout", function() {
		checkElement($(this));
	});
};

function checkElement(el) {
	el.removeAttr("validate");
	el.removeAttr("message");
	el.removeClass("invalid-field");
	destoryTip(el);
	
	var title = el.attr("title");
	var require = el.attr("require");
	var vtype = el.attr("vtype");
	var value = el.val()==null?null:el.val().replace(/(^\s*)(\s*$)/g, "");
	var flag = true;
	
	if(value) {
		//已填，校验
		//1.校验长度
		var len = 0;
		len = parseInt(el.attr("minlength"));
		if(len && len>0) {
			if(value.length < len) {
				el.attr("message", (title?title+":":"")+"长度最少"+len+"位");
				flag = false;
			}
		}
		len = parseInt(el.attr("maxlength"));
		if(len && len>0) {
			if(value.length > len) {
				el.attr("message", (title?title+":":"")+"长度最多"+len+"位。");
				flag = false;
			}
		}
		len = parseInt(el.attr("length"));
		if(len && len>0) {
			if(value.length != len) {
				el.attr("message", (title?title+":":"")+"长度应为"+len+"位。");
				flag = false;
			}
		}
		
		//2.校验正确性
		if(vtype) {
			switch(vtype) {
				case "money":
					var moneyRe = new RegExp($.fn.vali.defaults.regExps.moneyRe);
					if(!moneyRe.test(value)) {
						el.attr("message",(title?title+":":"")+ "金钱格式不对，请保留2位小数");
						flag = false;
					}
					break;
				case "email":
					var mailRe = new RegExp($.fn.vali.defaults.regExps.mailRe);
					if(!mailRe.test(value)) {
						el.attr("message", (title?title+":":"")+"应填写正确格式的邮箱地址");
						flag = false;
					}
					break;
				case "zipcode":
					var zipcodeRe = new RegExp($.fn.vali.defaults.regExps.zipcodeRe);
					if(!zipcodeRe.test(value)) {
						el.attr("message", (title?title+":":"")+"应填写6位邮政编码");
						flag = false;
					}
					break;
				case "number":
					var numberRe = new RegExp($.fn.vali.defaults.regExps.numberRe);
					if(!numberRe.test(value)) {
						el.attr("message", (title?title+":":"")+"应填写数字");
						flag = false;
					}
					break;
				case "nsrsbh":
					var nsrsbhRe = new RegExp($.fn.vali.defaults.regExps.nsrsbhRe);
					if(!nsrsbhRe.test(value)) {
						el.attr("message", "请填写纳税人识别号,15-20位字母和数字");
						flag = false;
					}
					break;
				case "phone":
					var phoneRe = new RegExp($.fn.vali.defaults.regExps.phoneRe);
					if(!phoneRe.test(value)) {
						el.attr("message", (title?title+":":"")+"请填写正确的电话号码");
						flag = false;
					}
					break;
				case "mobile":
					var mobileRe = new RegExp($.fn.vali.defaults.regExps.mobileRe);
					if(!mobileRe.test(value)) {
						el.attr("message", (title?title+":":"")+"应填写手机号码");
						flag = false;
					}
					break;
				case "sfz":
					if(value.length<=18){
						el.attr("message", ("身份证长度不够18位！"));
						flag = false;
					}
					iW = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1); 
					iSum = 0; 
					for( var i=0;i<17;i++){ 
					iC = value.charAt(i) ; 
					iVal = parseInt(iC); 
					iSum += iVal * iW[i]; 
					} 
					iJYM = iSum % 11;
					var sJYM = ''; 
					if(iJYM == 0) sJYM = "1"; 
					else if(iJYM == 1) sJYM = "0"; 
					else if(iJYM == 2) sJYM = "x"; 
					else if(iJYM == 3) sJYM = "9"; 
					else if(iJYM == 4) sJYM = "8"; 
					else if(iJYM == 5) sJYM = "7"; 
					else if(iJYM == 6) sJYM = "6"; 
					else if(iJYM == 7) sJYM = "5"; 
					else if(iJYM == 8) sJYM = "4"; 
					else if(iJYM == 9) sJYM = "3"; 
					else if(iJYM == 10) sJYM = "2"; 
					var cCheck = value.charAt(17).toLowerCase(); 
					if( cCheck != sJYM ){ 
						el.attr("message", ("请输入正确的身份证号码！"));
						flag = false;
					} 
					break;
				case "longitude":
					if(value>=0 && value<=180) {
					}else{
						el.attr("message", (title?title+":":"")+"经度应在0-180");
						flag = false;
					}
					break;
				case "latitude":
					if(value>=0 && value<=90) {
					}else{
						el.attr("message", (title?title+":":"")+"纬度应在0-90");
						flag = false;
					}
					break;
			}
		}
	} else {
		//未填，检查是否require
		if(require) {
			el.attr("message", (title?title+":":"")+"不能为空");
			flag = false;
		}
	}
	
	if(!flag) {
		el.attr("validate", "false");
		el.addClass("invalid-field");
		showTip(el);
	}
	return flag;
}

function validate() {
	var flag = true;
	var fields = $(this).find("input,select,textarea");
	$.each(fields, function(i, field){
		var result = checkElement($(field));
		if(flag)flag = result;
	});
	return flag;
};

/*
让某个控件不进行验证
*/
function unvalidate() {
	var fields = $(this).find("input,select,textarea");
	$.each(fields, function(i, field){
		$(field).unbind("focusout", checkElement);
	});
}

//借助layer显示错误信息
function showTip(el){
	if(el.attr('show') == "false")return;
	el.bind('mouseover.initTip',function(){
		if(layer){
			layer.tips($(this).attr("message"), $(this), {
				guide: 2,
				time: 1,
				style: ['background-color:#F26C4F; color:#fff', '#F26C4F'],
				maxWidth:240
			});
		}
	});
}

//销毁提示信息
function destoryTip(el){
	el.unbind('mouseover.initTip');
}

$.fn.vali = function(method) {
	if ( methods[method] ) {
      	return methods[method].apply( this, Array.prototype.slice.call( arguments, 1 ));
    } else if ( typeof method === 'object' || ! method ) {
      	return methods.init.apply( this, arguments );
    } else {
      	$.error( 'The method ' +  method + ' does not exist.' );
    }
};

$.fn.vali.defaults = {
	regExps: {mailRe:"^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$",
		zipcodeRe:"^[0-9]{6}$",
		moneyRe:"^([0-9]|[1-9][0-9]*)(\.[0-9]{2})$",
		numberRe:"^(0|[1-9])[0-9]*$",
		nsrsbhRe:"^[a-zA-Z0-9]{15,20}$",
		sfzRe:"^([0-9]{15})|([0-9]{14}[xX]?)|([0-9]{18})|([0-9]{17}[xX]?)$",
		phoneRe:"^[0-9-]{7,}$",
		mobileRe:"^1[3458][0-9]{9}$"
	}
};

$(function(){
	$('body').vali();
});

})(jQuery);