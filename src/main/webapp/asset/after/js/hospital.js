/**
 * 弹出区域选择框
 */
function showRegion() {
	region_index = $.layer({
		type : 1,
		shade : [ 0.3, '#000', true ],
		shadeClose : false,
		fadeIn : 300,
		title : '选择区域',
		offset : [ '120px', '50%' ],
		closeBtn : [ 1, true ],
		border : [ 5, 0.5, '#666', true ],
		area : [ '480px', '320px' ],
		page : {
			dom : '#regionsModal'
		},
		end : function() {
			var _region = $("#regionsModal").data("data");
			var _n = "";
			var _v = "";
			if (_region) {
				_n = _region.regionName;
				_v = _region.id;
			}
			$("#regionName").val(_n);
			$("#regionId").val(_v);
            $("#regionName").focus();//再次获得焦点。
            $("#address").focus();//再次失去焦点，失去焦点相当于验证，这时候就能够正确的验证。再把焦点转移到下一个输入框
		}
	});

}

/**
 * 公共的请求方法
 * 
 * @param url
 *            请求路径
 * @param parms
 *            请求参数
 * @param callBackFunc
 *            回调函数
 */
function commonPost(url, parms, callBackFunc) {
	$.post(baseurl + url, parms, function(data) {
		if (data.status != 200) {
			layer.alert(data.msg);
		} else {
			if ($.isFunction(callBackFunc)) {
				callBackFunc(data.result);
			}
		}
	});
}