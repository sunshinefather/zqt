/**
 * Created by wishcloud on 2016/1/12.
 */
var imgNum=0;
var z=$(".img").length;
function suofangs(imgClass,guan,suofang,fangda,xuanzhuan,fangdabut,suoxiaobut){
    var xuan=0;
    var top=parseInt($(fangda).css("margin-top"));
    var bottom=parseInt($(fangda).css("margin-bottom"));
    $(document).on("click",imgClass,function(){
        $(suofang).css("display","block");
        $(fangda).attr("src",$(this).attr("src"));
        imgNum=$(this).attr("value");
        $(fangda).css({
            width:"100%",
            transform:"rotateZ(0deg)",
            marginTop:top,
            marginBottom:bottom
        });
        $(suofang).scrollLeft(0);
        $(suofang).scrollTop(0)
    });
    /* 关闭单独显示图片页*/
    $(document).on("click",guan,function(){
        $(suofang).css("display","none");
        $(fangda).css("width","auto")
    });
    /*   图片放大事件*/
    $(document).on("click",fangdabut,function(){
        $(fangda).css("width",parseInt($(fangda).css("width"))+50+"px");
        if(xuan%20!=0){
            $(fangda).css("margin-top", (parseInt($(fangda).css("width"))-parseInt($(fangda).css("height")))/2+top+"px");
            $(fangda).css("margin-bottom", (parseInt($(fangda).css("width"))-parseInt($(fangda).css("height")))/2+bottom+"px");
        }else{
            $(fangda).css("margin-top",top+"px")
            $(fangda).css("margin-bottom",bottom+"px")
        }
        return false
    });
    /* 图片缩小事件*/
    $(document).on("click",suoxiaobut,function(){
        $(fangda).css("width",parseInt($(fangda).css("width"))-50+"px");
        if(xuan%20!=0){
            $(fangda).css("margin-top", (parseInt($(fangda).css("width"))-parseInt($(fangda).css("height")))/2+top+"px");
            $(fangda).css("margin-bottom", (parseInt($(fangda).css("width"))-parseInt($(fangda).css("height")))/2+bottom+"px");
        }else{
            $(fangda).css("margin-top",top+"px")
            $(fangda).css("margin-bottom",bottom+"px")
        }

        return false
    });
    /* 图片旋转事件*/
    $(document).on("click",xuanzhuan,function(){
        xuan+=90;
        if(xuan==360){
            xuan=0
        }
        if(xuan%20!=0){
            $(fangda).css("margin-top", (parseInt($(fangda).css("width"))-parseInt($(fangda).css("height")))/2+top+"px");
            $(fangda).css("margin-bottom", (parseInt($(fangda).css("width"))-parseInt($(fangda).css("height")))/2+bottom+"px");
        }else{
            $(fangda).css("margin-top",top+"px")
            $(fangda).css("margin-bottom",bottom+"px")
        }
        $(fangda).css("transform","rotateZ("+xuan+"deg)");
        return false
    });
    var X, Y, x,y;
    var mouse=false;
    $(fangda).on("mousedown",function(e){
        $(this).css("cursor","pointer");
        mouse=true;
        X = e.pageX;
        Y = e.pageY;
        var scrollX= $(suofang).scrollLeft()
        var scrollY= $(suofang).scrollTop()
        onmousemove=function(e){
            if(mouse){
                x=e.pageX;
                y=e.pageY;
                $(suofang).scrollLeft(X-x+scrollX)
                $(suofang).scrollTop(Y-y+scrollY)
                return false
            }
        }
        return false
    }).on("mouseup",function(){
        $(this).css("cursor","default");
        mouse=false;
        return false
    });
    $(fangda).on("click",function(){return false})

    $(document).on("contextmenu",suofang,function(){
        $(suofang).css("display","none");
        $(fangda).css("width","auto");
        return false
    });
    
    
    function leftImg(){
    	if(imgNum>0){
    		imgNum--;
    		imgSwitch(imgNum)
    	}
    }
    function rightImg(){
    	if(imgNum<z-1){
    		imgNum++;
    		imgSwitch(imgNum)
    	}
    }
    function imgSwitch(num){
    	var imgSrc=$(".img").eq(num).attr("src");
    	$(".fangdaimg").attr("src",imgSrc);
    	 $(fangda).css({
             width:"100%",
             transform:"rotateZ(0deg)",
             marginTop:top,
             marginBottom:bottom
         });
    }
    $(".leftBut").on("click",leftImg);
    $(".rightBut").on("click",rightImg);
}

