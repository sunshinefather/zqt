/**
 * 弹出div层 依赖于layer
 * @param id 弹出层页面元素的id
 * @param title 弹出层的标题
 * @param wd 弹出层的高度
 * @param hg 弹出层的宽度
 */
var dialog_frame,dialog_div;

function dialogdiv(id,title,wd,hg){
	dialog_div=$.layer({
		type : 1,
		shade : [0.3 , '#000' , true],
		shadeClose : false,
		fadeIn: 300,
		title : title,
		offset : ['50%' , '50%'],
		closeBtn : [1 , true],
		border : [5, 0.3, '#666', true],
		area : [wd+'px' , hg+'px'],
		page: {
			dom:"#"+id
		},
		end : function(){
			
		}
	});
}
/**
 * 弹出frame层
 * @param url 页面地址
 * @param title 标题
 * @param wd 高度
 * @param hg 宽度
 */
function dialogframe(url,title,wd,hg,backfunc){
	dialog_frame=$.layer({
		type : 2,
		shade : [0.3 , '#000' , true],
		shadeClose : false,
		fadeIn: 300,
		title : title,
		offset : ['165px' , '50%'],
		closeBtn : [1 , true],
		border : [0],
		area : [wd+'px' , hg+'px'],
		iframe:{src:url},
		end : function(){
			if($.isFunction(backfunc)){
				backfunc($("body").data("data"));
			}
		}
	});
}