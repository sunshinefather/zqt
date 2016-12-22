/**
 * Created by wishcloud on 2015/10/20.
 */

$(".yuan").on("click",bian);
function bian(){
    var d = new Date()
    var vYear = d.getFullYear()
    var vMon = d.getMonth() + 1
    var vDay = d.getDate()
    var h = d.getHours();
    var m = d.getMinutes();
    var se = d.getSeconds();
    var data=vYear+"年"+vMon+"月"+vDay+"日"
    var day=h+"："+m+"："+se
    $(this).siblings('.time').html(data);
    $(this).siblings('.day').html(day);
/*       var $js= $(this).parents(".dingwei");
    $js.siblings(".cuo").css("background","red");*/
}