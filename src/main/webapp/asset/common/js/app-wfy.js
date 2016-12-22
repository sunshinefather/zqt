jQuery(document).ready(function() {    
	 App.init(); // initlayout and core plugins
})

//隐藏和展示header		
jQuery(function() {   
			$("#header").headroom({
			  "tolerance":5, //滚动状态的变化
			  "offset":62,  // 在元素没有固定之前，垂直方向的偏移量（以px为单位） 
			  "classes": {
				"initial": "animated",   // 当元素初始化后所设置的class  
				"pinned": "slideInLeft",  // 向上滚动时设置的class
				"unpinned": "slideOutLeft"  // 向下滚动时设置的class
			  }
			});
})

//滚动固定页面元素
jQuery(function() {
        $(document).ready( function() {
          $('.stickup').stickUp();
        });
});

//datetimepicker日历控件 
jQuery(function() {
    $('#datetimepicker').datetimepicker({
       pickTime: false
    });
})
  
 
//icheckbox单选复选
jQuery(function() {
  $('input').iCheck({
    checkboxClass: 'icheckbox_flat-blue',
    radioClass: 'iradio_flat-blue',
  });  
//icheckbox全选/反选
	$('#check-all').on('ifChecked', function(event){
	$('input').iCheck('check')
	});
	$('#check-all').on('ifUnchecked', function(event){
	$('input').iCheck('uncheck');
	});
})

//input-placeholder效果
jQuery(function() {
		$('.input-placeholder').bind({
		focus:function(){	
		if (this.value == this.defaultValue){
		this.value="";
		$(this).css("color","#333");
		}},
		blur:function(){
		if (this.value == ""){
		this.value = this.defaultValue;
		$(this).css("color","#999");
		}},
		});
})

//button-loading
jQuery(function() {
$('#preserve') .click(function () {  
        var btn = $(this)  
        btn.button('loading')  
        setTimeout(function () {  
          btn.button('reset')//将按钮状态初始化到最初  
        },1000)  
		});
		
$('#login') .click(function () {  
        var btn = $(this)  
        btn.button('loading')  
        setTimeout(function () {  
          btn.button('reset')//将按钮状态初始化到最初  
        },1000)  
		});
})

//////////////////////
////  高度、宽度  ////
//////////////////////

var heightp = parseInt($(window).height())
var height_header = parseInt($("#header").height())+1
var height_content = parseInt($(window).height())-height_header
//$(".page-sidebar-menu").css("max-height",height_content);
//alert($("body").height());
//alert($(".page-sidebar-menu").height());

//content自动获取高度//
var height = parseInt($(window).height())-67
var heightx = parseInt($(window).height())-53
var heightc = parseInt($(".form-search").height())
var heightd = parseInt($(".breadcrumbs").height())
var heighte = parseInt($(window).height())-180-heightc-heightd
var heightf = parseInt($(window).height())-106
$(".tablecont_b").css("min-height",heighte);
$(".sider").css("height",height);
$(".timeline").css("height",heightf-1);
$(".timelinebox").css("height",heightf);
$(".indmain").css("height",heightf);

//var width_cont = parseInt($(".height_content").width())
//$(".stickup").css("width",width_cont);
//alert($(".stickup").width());
//tree自动获取高度//
var heighta = parseInt($(".tablecont_b").height())+22
$(".dtree_clip").css("height",heighta);
var heightb = parseInt($(".dtree_clip").height())-1
$(".clip").css("height",heightb);
$(".dtree_clip").css("max-height",heightx);
$(".clip").css("max-height",heightx-2);
//alert($(".dtree_clip ul").height());

