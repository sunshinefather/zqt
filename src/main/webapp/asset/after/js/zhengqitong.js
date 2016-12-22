/*动态添图片*/
function imgUpload(upload){
    $(document).on("change",upload.inputClass,function(){
        var files = this.files;
        var mingurl=window.URL.createObjectURL(files[0]);
        var img=$(upload.contBoxClass).html();
        var deleImgClass=upload.deleImgClass.slice(1);
        var contentkuangClass=upload.contentkuangClass.slice(1);
        $(upload.contBoxClass).html("<div class="+contentkuangClass+"><span class="+deleImgClass+"><img src="+upload.deleImgUrl+" alt='删除图片'></span><img alt='骨架' src='"+mingurl+"'class='daolu_imgs'/></div>"+img);
        $(this).val("")
    });
    $(document).on("click",upload.deleImgClass,function(){
        $(this).parents(upload.contentkuangClass).remove();
    });
    $(document).on("click",upload.shangchunButClass, function (){
        $(upload.inputClass).click();
    });
}
$(function(){
   /* 图片点击放大*/
    $(document).on("click",".daolu_imgs",function(){
        $(".fangda_div").css("display","block");
        $(".famgda_img").attr("src",$(this).attr("src"))
    });
  /*  图片点击放大后 取消放大*/
    $(".fangda_guan").on("click",function(){
        $(".fangda_div").css("display","none");
    });
   /* 全选功能*/
    $(".quanxuan").on("click",function(){
        $(".input_xuanzhong").prop("checked",$(this).prop("checked"))
    });
/*    添加按钮跳转*/
    $(document).on("click",".tianjia",function(){
        window.location=$(this).attr("href")
    });
    /*返回按钮跳转*/
    $(document).on("click",".fanhui",function(){
        javascript :history.back();
    });
  /*  删除已上传的附件*/
    $(document).on("click",".delete_file",function(){
        var $inputFile=$(this).siblings(".wenjian_file");
        $(this).css("display","none");
        var $xininput=$inputFile.clone();
        var $td=$(this).siblings(".wenjian_file").parent();
        $inputFile.remove();
            $xininput.prependTo($td);
        $inputFile.val("");
    });
/*    如果已上传 显示上传删除按钮*/
    $(document).on("change",".wenjian_file",function(){
        $(".delete_file").css("display","inline-block")
    });
   /* 删除功能*/
    $(document).on("click",".delete_but",function(){
        $(".input_xuanzhong:checked").parents("tr").remove()
    });
/*点击行 选中*/
$(document).on("click","tr",function(){
       var $input=$(this).find(".input_xuanzhong");
        $input.prop("checked",!$input.prop("checked"));
 });
    $(document).on("click",".input_xuanzhong",function(){
        $(this).stopPropagation();
    })
});
/*点击查询按钮*/
$(document).on("click",".chaxun_guanbi_but",function(){
    $('#myform')[0].reset()
    $(".chaxun_cont").css("display","none");

});
/*查询关闭*/
$(document).on("click",".chakan_but",function(){
    $(".chaxun_cont").css("display","block")
});