/**
 * 展示在car
 * 
 * @return {}
 */
var MapShowInfoClass = (function() {
	return {
		map:null, //保存地图
		realtime_marker_obj : new Object(),// 保存实时监控的marker
//		overlaycomplete:function(e) {// 处理测面
//			var num = BMapLib.GeoUtils.getPolygonArea(e.overlay)
//			num = num / 1000;
//			num = num.toFixed(3);
//			alert("面积为：" + num + "平方千米");
//		},
		showRealTimeCarInfo : function(infolist) {
			// 清理marker
			for (var key in MapShowInfoClass.realtime_marker_obj) {
				MapShowInfoClass.map.removeOverlay(MapShowInfoClass.realtime_marker_obj[key]);
			} 
			var opts = {
				width : 340, // 信息窗口宽度
				height : 160, // 信息窗口高度
				enableMessage : false
				// 设置允许信息窗发送短息
			}
			
			if(MapShowInfoClass.markerClusterer!=null){
				MapShowInfoClass.markerClusterer.clearMarkers();
			}
			var pointArray = new Array();
			for (var i = 0; i < infolist.length; i++) {
				var obj = infolist[i];
				pointArray[i] = new BMap.Point(obj.msgLon, obj.msgLat);
				var point = new BMap.Point(obj.msgLon, obj.msgLat);
//				var url = setCarIcon("car_online.png");
				var myIcon = new BMap.Icon(pro+"/asset/common/image/map/car_online.png", new BMap.Size(22, 32), { // 小车图片
					imageOffset : new BMap.Size(0, 0)
				});
				var marker = new BMap.Marker(point, {
							icon : myIcon
						});
				MapShowInfoClass.realtime_marker_obj[obj.carId] = marker;
//				marker.setRotation(obj.msg_direction);
				var label = new BMap.Label(obj.msgCarNum, {
							offset : new BMap.Size(20, -10)
						});
				marker.setLabel(label);
				// 方向
				var dir = obj.msgDirection;
				marker.setRotation(dir);
				MapShowInfoClass.map.addOverlay(marker);
				var content = "<h4 style='margin:0 0 5px 0;padding:0.2em 0'>"
						+ obj.msgCarNum
						+ "</h4>"
						+ "<p style='margin:0;line-height:1.5;font-size:13px;'>速度："
						+ obj.msgSpeed
						+ "km/h</p>"
						+ "<p style='margin:0;line-height:1.5;font-size:13px;'>方向："
						+ obj.msgDirectionStr
						+ "</p>"
						+ "<p style='margin:0;line-height:1.5;font-size:13px;'>状态："
						+ obj.msgState
						+ "</p>"
						+ "<p style='margin:0;line-height:1.5;font-size:13px;'>位置："
						+ obj.msgPlace + "</p>" 
						+"<div class='bottom-tab'>"
						+"<input type='hidden' name='objId' />"
						+"<a href='#' onclick='send("+obj.carId+")' ><img class='map_img' src='"+pro+"/asset/common/image/map/xq_img.png' align='absmiddle'>指令</a>"
						+"<a href='#' onclick='show("+obj.carId+")' ><img class='map_img' src='"+pro+"/asset/common/image/map/gj_img.png' align='absmiddle'>追踪</a>"
						+"<a href='#' onclick='showhistory("+obj.carId+")'><img class='map_img' src='"+pro+"/asset/common/image/map/zg_img.png' align='absmiddle'>轨迹</a>"
						+"<a href='#' ><img class='map_img' src='"+pro+"/asset/common/image/map/more.png' align='absmiddle'>更多</a>"
						+"</div>"
						+"</div>";
				addClickHandler(content, marker);
			}
			
			function addClickHandler(content, marker) {
				marker.addEventListener("click", function(e) {
							openInfo(content, e)
						});
			}
			function openInfo(content, e) {
				var p = e.target;
				var point = new BMap.Point(p.getPosition().lng,p.getPosition().lat);
				var infoWindow = new BMap.InfoWindow(content, opts); // 创建信息窗口对象
				MapShowInfoClass.map.openInfoWindow(infoWindow, point); // 开启信息窗口
			}
		},
		itemclickshowRealTimeCarInfo : function(msg_car_num) {
			var marker=MapShowInfoClass.realtime_marker_obj[msg_car_num];
			if(marker!=null){
				MapShowInfoClass.map.centerAndZoom(marker.point, 15);
			}
		},
		/**
		 * -------------历史轨迹------------------------
		 */
		i : 0,
		flag : 0,// 判断是否点了停止
		obj : new Object(),
		marker : null,
		opts1 : {
			width : 200, 
			height : 100, 
			enableMessage : false
			
		},
		polylinePointsArray : new Array(),
		play_timer : null,
		init_history_trajectory:function(data){
			if (data.length == 0) {
				alert("没有数据！");
				return;
			}
				
			//列表加数据 
			
			// 初始化
			MapShowInfoClass.i = 0;
			MapShowInfoClass.marker = null;
			MapShowInfoClass.obj = new Object();
			MapShowInfoClass.polylinePointsArray = new Array();
			MapShowInfoClass.play_timer = null;
			MapShowInfoClass.flag = 0;
			MapShowInfoClass.map.clearOverlays();
			$("#ex1").slider(
					{step: 1, min: 0, max: data.length});
//			$('#ex1').slider({
//			    max: 100
//			});
			//设置进度条的最大值
//			var ex1=$('#ex1').setValue(10);
//			var slider = new Slider("#ex1", {
//				precision: 0,
//				value:data.length
//			});
//			.setMaxValue(data.length);
			for (var i = 0; i < data.length; i++) {
				var point = new BMap.Point(data[i].msgLon,
						data[i].msgLat);
				MapShowInfoClass.polylinePointsArray
						.push(point);
				MapShowInfoClass.obj[i] = data[i];
			}

			// 绘制轨迹线
			var polyline = new BMap.Polyline(
					MapShowInfoClass.polylinePointsArray,
					{
						strokeColor : "red",
						strokeWeight : 5,
						strokeOpacity : 0.8
					})
			MapShowInfoClass.map
					.addOverlay(polyline);
			// 添加起点和终点

			var start_obj = data[0];
			var start_p = new BMap.Point(start_obj.msgLon,
					start_obj.msgLat);
			var start_icon = new BMap.Icon(
					pro+"/asset/common/image/map/start_point.png", new BMap.Size(31,
							38), { // 小车图片
						imageOffset : new BMap.Size(0, 0)
					});
			var start_marker = new BMap.Marker(start_p, {
						icon : start_icon
					});
 			MapShowInfoClass.map
					.addOverlay(start_marker);
 			MapShowInfoClass.map.centerAndZoom(start_p,15);
			// 终点
			var end_obj = data[data.length - 1];
			var end_p = new BMap.Point(end_obj.msgLon,
					end_obj.msgLat);
			var end_icon = new BMap.Icon(pro+"/asset/common/image/map/end_point.png",
					new BMap.Size(31, 38), { // 小车图片
						imageOffset : new BMap.Size(0, 0)
					});
			var end_marker = new BMap.Marker(end_p, {
						icon : end_icon
					});
			MapShowInfoClass.map
					.addOverlay(end_marker);

		},
		play_timer:null,
		//历史轨迹播放
		timerRun:function(speed){
			if(MapShowInfoClass.play_timer!=null){
				window.clearInterval(MapShowInfoClass.play_timer);
			}
			MapShowInfoClass.play_timer = null;
			MapShowInfoClass.play_timer = self.setInterval(
					"MapShowInfoClass.play()", speed);
			
		},
		play:function(){
			if (MapShowInfoClass.marker != null) {
				MapShowInfoClass.map
						.removeOverlay(MapShowInfoClass.marker);
				// 留下上一个点
				var ii=0;
				if(MapShowInfoClass.i>0){
					ii=MapShowInfoClass.i
					- 1;
				}
//				$("#ex1").slider(
//						{step: 1, min: 0,value:MapShowInfoClass.i, max: MapShowInfoClass.polylinePointsArray.length});
//				var slider = new Slider("#ex1", {
//					precision: 2,
//					value:MapShowInfoClass.i
//				});
				
				$("#ex1").slider().slider('setValue', MapShowInfoClass.i);
//				$('#ex1').setValue(MapShowInfoClass.i);
				var obj1 = MapShowInfoClass.obj[ii];
				var p = new BMap.Point(obj1.msgLon, obj1.msgLat);
				var myIcon_ = new BMap.Icon(pro+"/asset/common/image/map/car1.png",
						new BMap.Size(18, 19), { // 小车图片
							imageOffset : new BMap.Size(0, 0)
						});
				var show_marker = new BMap.Marker(p, {
							icon : myIcon_
						});
				show_marker.setRotation(obj1.msgDirection);
				MapShowInfoClass.map.addOverlay(show_marker);
				var content = "<h4 style='margin:0 0 5px 0;padding:0.2em 0'>"
						+ obj1.msgCarNum
						+ "</h4>"
						+ "<p style='margin:0;line-height:1.5;font-size:13px;'>速度："
						+ obj1.msgSpeed
						+ "km/h</p>"
						+ "<p style='margin:0;line-height:1.5;font-size:13px;'>方向："
						+ obj1.msgDirectionStr
						+ "</p>"
						+ "<p style='margin:0;line-height:1.5;font-size:13px;'>状态："
						+ obj1.msgState
						+ "</p>"
						+ "<p style='margin:0;line-height:1.5;font-size:13px;'>位置："
						+ obj1.msgPlace + "</p>" + "</div>";

				addClickHandler(content, show_marker);

				function addClickHandler(content, marker) {
					show_marker.addEventListener("click", function(e) {
								openInfo(content, e)
							});
				}
				function openInfo(content, e) {
					var p1 = e.target;
					var point1 = new BMap.Point(p1.getPosition().lng, p1
									.getPosition().lat);
					var infoWindow = new BMap.InfoWindow(content, opts); //
					infoWindow.setWidth(340);
					infoWindow.setHeight(150);
					MapShowInfoClass.map.openInfoWindow(
							infoWindow, point1); //

				}
				// 判断是否播放结束
				if (MapShowInfoClass.polylinePointsArray.length == MapShowInfoClass.i) {
					// 关闭timer
					window.clearInterval(MapShowInfoClass.play_timer);
					return;
				}
			}

			// 为slider设值
//			var MapShowInfoClass_slider = Ext
//					.getCmp('MapShowInfoClass_slider');
//			MapShowInfoClass_slider
//					.setValue(MapShowInfoClass.i);
			// 把点打在地图上
			var obj = MapShowInfoClass.obj[MapShowInfoClass.i];

			var opts = {
				width : 200, // 信息窗口宽度
				height : 100, // 信息窗口高度
				enableMessage : false
				// 设置允许信息窗发送短息
			}
			var point = new BMap.Point(obj.msgLon, obj.msgLat);
			var myIcon = new BMap.Icon(pro+"/asset/common/image/map/car111.png",
					new BMap.Size(22, 32), { // 小车图片
						imageOffset : new BMap.Size(0, 0)
					});
			MapShowInfoClass.marker = new BMap.Marker(point, {
						icon : myIcon
					});
			var label = new BMap.Label(obj.msgPhone, {
						offset : new BMap.Size(20, -10)
					});
			MapShowInfoClass.marker.setLabel(label);
			MapShowInfoClass.marker
					.setRotation(obj.msg_direction);
			if (MapShowInfoClass.i == 0) {
				MapShowInfoClass.map.panTo(point);
			}
			MapShowInfoClass.i++;
		},
		//标注
		lat:"",
		lon:"",
		biaozhu:function(){
//			MapShowInfoClass.map.clearOverlays();

			var styleOptions = {
				strokeColor : "red", // 边线颜色。
				fillColor : "red", // 填充颜色。当参数为空时，圆形将没有填充效果。
				strokeWeight : 3, // 边线的宽度，以像素为单位。
				strokeOpacity : 0.8, // 边线透明度，取值范围0 - 1。
				fillOpacity : 0.6, // 填充的透明度，取值范围0 - 1。
				strokeStyle : 'solid' // 边线的样式，solid或dashed。
			}
			// 实例化鼠标绘制工具
			var drawingManager = new BMapLib.DrawingManager(MapShowInfoClass.map, {
				isOpen : false, // 是否开启绘制模式
				enableDrawingTool : false, // 是否显示工具栏
				drawingToolOptions : {
					anchor : BMAP_ANCHOR_TOP_RIGHT, // 位置
					offset : new BMap.Size(5, 5), // 偏离值
					scale : 0.8
					// 工具栏缩放比例
				},
				circleOptions : styleOptions, // 圆的样式
				polylineOptions : styleOptions, // 线的样式
				polygonOptions : styleOptions, // 多边形的样式
				rectangleOptions : styleOptions
					// 矩形的样式
				});
			drawingManager.addEventListener('markercomplete', markercomplete);
			function markercomplete(e,overlay) {// 处理测面
				drawingManager.close();//关闭绘制
				// 查询全部车辆
				var p=overlay.point;
				MapShowInfoClass.lat=p.lat;
				MapShowInfoClass.lon=p.lng;
				document.getElementById("bz_input").value="经度："+p.lng+";纬度："+p.lat;
//				$("#bz_input").value("经度："+p.lng+";纬度："+p.lat);
			}
			drawingManager.setDrawingMode(BMAP_DRAWING_MARKER);
			drawingManager.open();
		}
		// 加载兴趣点到地图上
		,
		xqd_arr:new Array(),
		xqd_obj:new Object(),
		showxqd:function(arr){
			for(var j=0;j<MapShowInfoClass.xqd_arr.length;j++){
				var xqdO=MapShowInfoClass.xqd_arr[j];
				MapShowInfoClass.map.removeOverlay(xqdO);
			}
			
//			MapShowInfoClass.map.removeOverlay(MapShowInfoClass.xqd_arr);
			MapShowInfoClass.xqd_arr=new Array();
			MapShowInfoClass.xqd_obj=new Object();
			for(var i=0;i<arr.length;i++){
				var o=arr[i];
				var geom=o.railGeom;
				var arrg=geom.split(";");
				var arr1=arrg[0];
				var arr2=arr1.split(",");
				var point = new BMap.Point(arr2[1], arr2[0]);
//				MapShowInfoClass.map.centerAndZoom(point, 15);
				
				var marker = new BMap.Marker(point);  // 创建标注
				var label = new BMap.Label(o.railName, {
							offset : new BMap.Size(20, -10)
						});
				marker.setLabel(label);
				MapShowInfoClass.xqd_obj[o.id]=marker;
				MapShowInfoClass.map.addOverlay(marker);   
				MapShowInfoClass.xqd_arr.push(marker);
				
				// 将标注添加到地图中
//				marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
			}
		},
		//新增区域
		polygon_Str:"",
		addRailInfo : function() {
			MapShowInfoClass.polygon_Str="";
//			MapShowInfoClass.map.clearOverlays();

			var styleOptions = {
				strokeColor : "red", // 边线颜色。
				fillColor : "red", // 填充颜色。当参数为空时，圆形将没有填充效果。
				strokeWeight : 3, // 边线的宽度，以像素为单位。
				strokeOpacity : 0.8, // 边线透明度，取值范围0 - 1。
				fillOpacity : 0.6, // 填充的透明度，取值范围0 - 1。
				strokeStyle : 'solid' // 边线的样式，solid或dashed。
			}
			// 实例化鼠标绘制工具
			var drawingManager = new BMapLib.DrawingManager(MapShowInfoClass.map, {
				isOpen : false, // 是否开启绘制模式
				enableDrawingTool : false, // 是否显示工具栏
				drawingToolOptions : {
					anchor : BMAP_ANCHOR_TOP_RIGHT, // 位置
					offset : new BMap.Size(5, 5), // 偏离值
					scale : 0.8
					// 工具栏缩放比例
				},
				circleOptions : styleOptions, // 圆的样式
				polylineOptions : styleOptions, // 线的样式
				polygonOptions : styleOptions, // 多边形的样式
				rectangleOptions : styleOptions
					// 矩形的样式
				});
			drawingManager.addEventListener('overlaycomplete', overlaycomplete);
			function overlaycomplete(e) {// 处理测面
				// 查询全部车辆
				 var arr= e.overlay.getPath();
				
				for(var i=0;i<arr.length;i++){
					var o=arr[i];
					var lat=o.lat;
					var lng=o.lng;
					MapShowInfoClass.polygon_Str+=lat+","+lng+";";
				}
				quindex= $.layer({
					type : 1,
					shade: false,
					fadeIn: 300,
					title : ['区域管理','background:white;'],
					offset : ['120px' , '50%'],
					closeBtn : [1 , true],
					border : [5, 0.5, '#666', true],
					area : ['640px' , '320px'],
					page: {
						dom: '#qy_manager'
					}
				});
				 qyaddindex= $.layer({
						type : 1,
						shade: false,
						fadeIn: 300,
						title : ['区域新增','background:white;'],
						offset : ['120px' , '50%'],
						closeBtn : [1 , true],
						border : [5, 0.5, '#666', true],
						area : ['450px' , '242px'],
						page: {
							dom: '#qy_add'
						}
					});
			
			document.getElementById("qy_label").value="";
			document.getElementById("qy_remark").value="";			
				
			}
			drawingManager.setDrawingMode(BMAP_DRAWING_POLYGON);
			drawingManager.open();
		},
		qyarr:new Array(),
		qy_obj:new Object(),
		//区域查询显示区域在地图上
		showqy:function(data){
			for(var j=0;j<MapShowInfoClass.qyarr.length;j++){
				var qyO=MapShowInfoClass.qyarr[j];
				MapShowInfoClass.map.removeOverlay(qyO);
			}
			MapShowInfoClass.qyarr=new Array();
			MapShowInfoClass.qy_obj=new Object();
			for(var i=0;i<data.length;i++){
				var o=data[i];
				
				
				var rail_geom=o.railGeom;
				var pointArr = new Array();
				
				var arr = rail_geom.split(";");
				var point = null;
				arr.pop();
				MapShowInfoClass.qy_obj[o.id]=arr;
				for (var ii = 0; ii < arr.length; ii++) {
					var str = arr[ii];
					var pArr = str.split(",");
					var lat = parseFloat(pArr[1]);
					var lng = parseFloat(pArr[0]);
					point = new BMap.Point(lat, lng);
					pointArr.push(point);
				}
				var styleOptions = {
					strokeColor : "red", // 边线颜色。
					fillColor : "red", // 填充颜色。当参数为空时，圆形将没有填充效果。
					strokeWeight : 3, // 边线的宽度，以像素为单位。
					strokeOpacity : 0.8, // 边线透明度，取值范围0 - 1。
					fillOpacity : 0.6, // 填充的透明度，取值范围0 - 1。
					strokeStyle : 'solid' // 边线的样式，solid或dashed。
				}
				
				var polygon = new BMap.Polygon(pointArr, {
							strokeColor : "red", // 边线颜色。
							fillColor : "red", // 填充颜色。当参数为空时，圆形将没有填充效果。
							strokeWeight : 3, // 边线的宽度，以像素为单位。
							strokeOpacity : 0.8, // 边线透明度，取值范围0 - 1。
							fillOpacity : 0.6, // 填充的透明度，取值范围0 - 1。
							strokeStyle : 'solid' // 边线的样式，s
						});
//				MapShowInfoClass.map.clearOverlays();

//				// var polygon =new BMap.Polygon(pointArr,
//				// {strokeColor:"blue", strokeWeight:6, strokeOpacity:0.5});
				var label = new BMap.Label(o.railName, {
						offset : new BMap.Size(20, -10),
						position:point
					});
				MapShowInfoClass.map.addOverlay(polygon);
				MapShowInfoClass.map.addOverlay(label);
				MapShowInfoClass.qyarr.push(polygon);
			}
		}
		//区域定位
		,dingwei_qy:function(id){
			var arr=MapShowInfoClass.qy_obj[id];
			var str = arr[0];
			var pArr = str.split(",");
			var lat = parseFloat(pArr[1]);
			var lng = parseFloat(pArr[0]);
			var point = new BMap.Point(lat, lng);
			MapShowInfoClass.map.centerAndZoom(point, 15);
		},
		//绘画线路
		line_Str:"",
		add_line:function(){

			MapShowInfoClass.line_Str="";

			var styleOptions = {
				strokeColor : "red", // 边线颜色。
				fillColor : "red", // 填充颜色。当参数为空时，圆形将没有填充效果。
				strokeWeight : 3, // 边线的宽度，以像素为单位。
				strokeOpacity : 0.8, // 边线透明度，取值范围0 - 1。
				fillOpacity : 0.6, // 填充的透明度，取值范围0 - 1。
				strokeStyle : 'solid' // 边线的样式，solid或dashed。
			}
			// 实例化鼠标绘制工具
			var drawingManager = new BMapLib.DrawingManager(MapShowInfoClass.map, {
				isOpen : false, // 是否开启绘制模式
				enableDrawingTool : false, // 是否显示工具栏
				drawingToolOptions : {
					anchor : BMAP_ANCHOR_TOP_RIGHT, // 位置
					offset : new BMap.Size(5, 5), // 偏离值
					scale : 0.8
					// 工具栏缩放比例
				},
				circleOptions : styleOptions, // 圆的样式
				polylineOptions : styleOptions, // 线的样式
				polygonOptions : styleOptions, // 多边形的样式
				rectangleOptions : styleOptions
					// 矩形的样式
				});
			drawingManager.addEventListener('overlaycomplete', overlaycomplete);
			function overlaycomplete(e) {// 处理测面
				// 查询全部车辆
				 var arr= e.overlay.getPath();
				
				for(var i=0;i<arr.length;i++){
					var o=arr[i];
					var lat=o.lat;
					var lng=o.lng;
					MapShowInfoClass.line_Str+=lat+","+lng+";";
				}
				lineindex= $.layer({
					type : 1,
					shade: false,
					fadeIn: 300,
					title : ['线路管理','background:white;'],
					offset : ['120px' , '50%'],
					closeBtn : [1 , true],
					border : [5, 0.5, '#666', true],
					area : ['640px' , '320px'],
					page: {
						dom: '#line_manager'
					}
				});
				 lineaddindex= $.layer({
						type : 1,
						shade: false,
						fadeIn: 300,
						title : ['线路新增','background:white;'],
						offset : ['120px' , '50%'],
						closeBtn : [1 , true],
						border : [5, 0.5, '#666', true],
						area : ['450px' , '242px'],
						page: {
							dom: '#line_add'
						}
					});
				document.getElementById("line_label").value="";
				document.getElementById("line_remark").value="";
			}
			drawingManager.setDrawingMode(BMAP_DRAWING_POLYLINE);
			drawingManager.open();
			
		},
		//在地图上显示线路
		line_arr:new Array(),
		line_obj:null,
		show_line:function(data){
			for(var j=0;j<MapShowInfoClass.line_arr.length;j++){
				var xlO=MapShowInfoClass.line_arr[j];
				MapShowInfoClass.map.removeOverlay(xlO);
			}
			MapShowInfoClass.line_arr=new Array();
			MapShowInfoClass.line_obj=new Object();
			for(var i=0;i<data.length;i++){
				var o=data[i];
				
				
				var rail_geom=o.railGeom;
				var pointArr = new Array();
				
				var arr = rail_geom.split(";");
				var point = null;
				arr.pop();
				MapShowInfoClass.line_obj[o.id]=arr;
				for (var ii = 0; ii < arr.length; ii++) {
					var str = arr[ii];
					var pArr = str.split(",");
					var lat = parseFloat(pArr[1]);
					var lng = parseFloat(pArr[0]);
					point = new BMap.Point(lat, lng);
					pointArr.push(point);
				}
				var styleOptions = {
					strokeColor : "red", // 边线颜色。
					fillColor : "red", // 填充颜色。当参数为空时，圆形将没有填充效果。
					strokeWeight : 3, // 边线的宽度，以像素为单位。
					strokeOpacity : 0.8, // 边线透明度，取值范围0 - 1。
					fillOpacity : 0.6, // 填充的透明度，取值范围0 - 1。
					strokeStyle : 'solid' // 边线的样式，solid或dashed。
				}
				
				var polygon = new BMap.Polyline(pointArr, {
							strokeColor : "red", // 边线颜色。
							fillColor : "red", // 填充颜色。当参数为空时，圆形将没有填充效果。
							strokeWeight : 3, // 边线的宽度，以像素为单位。
							strokeOpacity : 0.8, // 边线透明度，取值范围0 - 1。
							fillOpacity : 0.6, // 填充的透明度，取值范围0 - 1。
							strokeStyle : 'solid' // 边线的样式，s
						});
//				MapShowInfoClass.map.clearOverlays();
//				// var polygon =new BMap.Polygon(pointArr,
//				// {strokeColor:"blue", strokeWeight:6, strokeOpacity:0.5});
				MapShowInfoClass.map.addOverlay(polygon);
				MapShowInfoClass.line_arr.push(polygon);
			}
		},
		//线路定位
		dingwei_line:function(id){
			var arr=MapShowInfoClass.line_obj[id];
			var str = arr[0];
			var pArr = str.split(",");
			var lat = parseFloat(pArr[1]);
			var lng = parseFloat(pArr[0]);
			var point = new BMap.Point(lat, lng);
			MapShowInfoClass.map.centerAndZoom(point, 15);
		}
		
		
	}
})();