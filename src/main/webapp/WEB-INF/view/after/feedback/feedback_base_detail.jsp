<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>意见反馈详情</title>
<link rel="stylesheet"
	href="<c:url value="/asset/common/css/layout.css"/>" type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/asset/common/css/module.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/asset/common/css/pop.css"/>"
	type="text/css" />
<script type="text/javascript"
	src="<c:url value="/asset/common/js/jquery-1.8.3.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/asset/after/js/wu-style.js"/>"></script>
<style type="text/css">
dd {
	margin-top: 10px;
	margin-left: 5px;
}
</style>
<style>
* {
	margin: 0;
	padding: 0;
}

body {
	font-size: 14px;
	color: #323232;
}

#box {
	height: 100%;
	padding: 10px;
}

li {
	list-style: none;
	display: block;
	border-bottom: 1px solid #e0e0e0;
	padding-top: 10px;
	padding-bottom: 10px;
}

dd {
	margin-bottom: 10px;
}

.fontColor {
	color: #a9a9a9;
}

.cont {
	line-height: 25px;
}

.name, .titme {
	line-height: 30px;
}

#huifu {
	width: 100%;
	height: auto;
	position: fixed;
	bottom: 0;
	left: 0;
}

.conts {
	display: block;
	resize: none;
	overflow-y: auto;
	margin-left: 10px;
	background: #ffffff;
	outline: none;
	min-height: 20px;
	max-height: 60px;
	position: absolute;
	bottom: 5px;
	padding: 5px;
}

.conts::-webkit-scrollbar {
	width: 0;
}

.replyBut {
	width: 64px;
	height: 30px;
	border-radius: 5px;
	color: #ffffff;
	background: rgb(254, 120, 127);
	position: absolute;
	right: 10px;
	bottom: 10px;
	cursor: pointer;
	text-align: center;
	line-height: 30px;
}
.replyBut:active{
	background:rgb(199, 57, 64);;
}
.contff {
	width: 100%;
	display: table-cell;
	vertical-align: bottom;
	height: 40px;
	outline: none;
}

.contentBox {
	margin-bottom: 80px;
}
.img{
width: 49%;
}
</style>
</head>
<body>
	<div id="box">
		<ul>
			<li>
				<dl>
					<dd>
						<label style="font-weight: 700;">反馈人:</label> <span>${obj.remoteUser.remoteExtUser.fullName}</span>
					</dd>
					<dd>
						<label style="font-weight: 700;">反馈时间:</label> <span
							style="width: 530px;"><fmt:formatDate
								value="${obj.createDate }" pattern="yyyy-MM-dd HH:mm:ss" /></span>
					</dd>
					<dd>
						<label style="float: left; margin-right: 8px; font-weight: 700;">反馈内容:</label>
						<div
							style="width: 600px; white-space: normal; word-break: break-all; overflow: hidden;">${obj.content}</div>
					</dd>
					<dd>
						<label style="float: left; margin-right:  8px; font-weight: 700;">反馈图片:</label>
						<div style="width: 600px; white-space: normal; word-break: break-all; overflow: hidden;">
						<c:forEach items="${obj.imageAttachments}" var="c">
    						<img src='${pageContext.request.contextPath}${c.webAddr }' class='img' data-name="image">
	    				</c:forEach>
						</div>
					</dd>
				</dl>
			</li>
		</ul>
		<ul class="contentBox"></ul>
		<div id="huifu">
			<textarea id="replyInput" class="conts" contenteditable="true" rows="3"></textarea>
			<span class="replyBut">回复</span>
		</div>
	</div>
	</div>
</body>
<script type="text/javascript">
var $contentBox = $(".contentBox");//列表框 ul
var $inputCont = $("#replyInput");//输入的内容
	
	$(function(){

		//监听设置回复框宽度
		$(window).resize(function() {
			replyInputHeight();
		});
		//设置回复框宽度
		function replyInputHeight() {
			var widths = document.body.clientWidth - 120;
			$inputCont.css("width", widths + "px")
		};
	replyInputHeight();
	
	/* 点击回复发送内容 */
		$(".replyBut")
				.live("click",function() {
							var cont = {
								"feedBackId" : '${obj.feedBackId}',
								"content" : $inputCont.val()
							};
							if (cont.content) {
								$.ajax({
											type : "post",
											url : "${pageContext.request.contextPath}/after/feedback/feedback_base_replay",
											async : false,
											data : cont,
											success : function(data) {
												console.log(data)
												if (data) {
													$contentBox.html("");
													$inputCont.val("");
													obtainReplyData();
													/* $('a#${obj.feedBackId}', window.parent.document).html("已回复"); */
												}
											}
										});
							}

						});
	/* 获取以前回复的内容*/
		obtainReplyData();
	})


	/* 获取历史回复的内容 */
	function obtainReplyData(){
		$.ajax({
			 type : "get",
			 url : "${pageContext.request.contextPath}/after/feedback/feedback_replay_list",
			 data:{"feedBackId":"${obj.feedBackId}"},
			 async : false,
			 success : function(data) {
				 console.log(data)
				 $.each(data, function(index, value) { 
					 console.log(index)
					 obtainrSingleReplyCont(value)
					 }); 
			 }
			 });
	}
	 
	/* 获取单一回复列表内容 */
	function obtainrSingleReplyCont(obtainData) {
		console.log(obtainData)
		var replyList = $("<li></li>");
		//姓名
		var name = $("<p class='name fontColor'></p>");
		name.html(obtainData.remoteUser.remoteExtUser.fullName||obtainData.replayer);
		replyList.append(name);
		//内容
		var $div=$("<div><div>");
		var read = $("<p class='AlreadyReplyCont'style='white-space: pre-wrap'></p>");
		read.html(obtainData.content)
		$div.append(read);
		if(obtainData.imageAttachments!=null){
			$.each(obtainData.imageAttachments,function(num,val){
				var $img=$("<img src='' class='img'>");
				console.log(val.webAddr)
				$img.attr("src","${pageContext.request.contextPath}"+val.webAddr)
				$div.append($img);
			});
		}
		
		replyList.append($div);
		//时间
		var time = $("<p class='titme fontColor'></p>");
		time.html(obtainData.replayDate)
		replyList.append(time);
		
		$contentBox.append(replyList);
	};
</script>
</html>