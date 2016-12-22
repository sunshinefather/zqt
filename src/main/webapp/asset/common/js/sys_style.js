// JavaScript Document
$(document).ready(function(){
// 机构类别
  $(".cont").children("input").click(function(){
    $(this).parent().children(".orgnamebox").slideDown("fast");
  });
  $(".close").click(function(){
    $(this).parent().hide("fast");
  });
  $("#area_s").click(function(){
    $(".arealist").slideDown("fast");
  });
  $("#CouponCode").click(function(){
    $(".wircop").slideDown("fast");
  });

// 分支机构 
	$("#area").mouseover(function(){
		$(".areabox").show();
	}).mouseout(function(){
		$(".areabox").hide();
	});
	$(".areabox").hover(function(){
		$(this).show();
		$("#area").addClass("hover");
	},function(){
		$(this).hide();
		$("#area").removeClass("hover");
	});
	$(".areabox dt").click(function(){
		var $text=$(this).text();
		$(".lgtxt").text($text);
		$(".areabox").hide();
	});
// 账号信息 
	$("#user").mouseover(function(){
		var $width=$(this).width();
		$(".userinf").width($width+12);
		$(".userinf").show();
		$(this).attr("class","hover");
	}).mouseout(function(){
		$(".userinf").hide();
		$(this).attr("class","");
	});
	$(".userinf").hover(function(){
		$(this).show();
		$("#user").addClass("hover");
	},function(){
		$(this).hide();
		$("#user").removeClass("hover");
	});
});

//star
$(document).ready(function(){
    var stepW = 26;
    var description = new Array("差","一般","好","很好","非常好");
    var stars = $(".grade > dd");
    var descriptionTemp;
    $(".star_score").css("width",0);
    stars.each(function(i){
        $(stars[i]).click(function(e){
            var n = i+1;
            $(".star_score").css({"width":stepW*n});
            descriptionTemp = description[i];
            $(this).find('a').blur();
            return stopDefault(e);
            return descriptionTemp;
        });
    });
    stars.each(function(i){
        $(stars[i]).hover(
            function(){
                $(".description").text(description[i]);
				$(".description").css("color","#e40000");
            },
            function(){
                if(descriptionTemp != null)
                    $(".description").text(descriptionTemp);
                else 
                    $(".description").text("请评分");
            }
        );
    });
});
function stopDefault(e){
    if(e && e.preventDefault)
           e.preventDefault();
    else
           window.event.returnValue = false;
    return false;
};

//praise
  $(document).ready(function(e) {
    $('.praise').one("click", function (){
  var left = parseInt($(this).offset().left)+5, top =  parseInt($(this).offset().top)-18, obj=$(this);
  $(this).append('<span id="praise"><b>+1<\/b></\span>');
  $('#praise').css({'position':'absolute','z-index':'100', 'color':'#f18300','font-size':'16px','left':left+'px','top':top+'px'}).animate({top:top-10,left:left+5},'slow',function(){
   $(this).fadeIn('fast').remove();
   var Num = parseInt(obj.next('span').text());
       Num++;
    obj.next('span').text(Num);
  });
    $(this).find("s").addClass("already");
	$(this).parent().attr('title','您已经赞过了');
  return false;
 });
});
 
$(document).ready(function () {
	$(".orglist > li").hover(function () {   
	   $(this).children(".fn").stop(true,true).slideDown(100)
	}, function () {
		$(this).children(".fn").stop(true,true).hide()
	})
});
