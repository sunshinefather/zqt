<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统管理</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/asset/common/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/asset/common/css/layout.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/asset/common/css/pop.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/asset/common/css/module.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/asset/common/css/zixun.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/asset/common/css/suofang.css" />
<style>
.chat-user {
	cursor: pointer;
}

img {
	border-radius: 30px !important;
}

.fasongimg {
	cursor: pointer;
	border-radius: 0 !important;
	max-width: 300px;
}

.Music {
	width: 160px;
	height: 30px;
	overflow: hidden;
	border-radius: 5px;
}

.bofang {
	font-size: 18px;
	cursor: pointer;
}

.chakagengduo {
	text-align: center;
	color: #3666c3;
	cursor: pointer;
}

.users-list {
	overflow-y: auto;
}

#users_fenye {
	height: 30px;
	position: absolute;
	bottom: 0;
	right: 0;
}

#pagination1 {
	margin: 0 auto;
	float: right;
}

#pagination1>li>a:active {
	background-color: #337ab7;
	color: #ffffff;
}

.chat-users {
	position: absolute;
	width: 100%
}

.xiao-time {
	clear: right;
}
</style>

</head>
<body class="gray-bg" style="overflow: hidden">
	<div class="topfixed gray-bg" style="height: 40px; top: 0px;">
		<div class="current">
			<s></s><a href="#">首页</a> >> <a href="#">咨询管理</a> >> <span>咨询消息管理</span>
		</div>
	</div>
	<div class="wrapper wrapper-content  animated fadeInRight"
		style="margin-top: 40px;">
		<div class="row">
			<div class="col-sm-12" ng-controller="content">
				<div class="ibox chat-view">

					<div class="ibox-title">
						聊天窗口
						<!--最新消息时间-->
						<input type="text" ng-model="urename" class="bang"
							style="display: none">
					</div>
					<div class="ibox-content">
						<div class="row">
							<!--内容区-->
							<div class="col-sm-9">
								<div class="chat-discussion neirong">
									<img
										style="margin-left: 310px; width: 20px; height: 20px; display: none;"
										id="loading" alt="加载中..."
										src="/mom/asset/common/scripts/layer/skin/default/xubox_loading2.gif">
									<div class="liaotian"></div>
								</div>
							</div>
							<!--好友-->
							<div class="col-sm-3">
								<div class="chat-users">
									<div class="users-list" id="history"></div>
									<div id="users_fenye">
										<ul class="pagination" id="pagination1">
											<li class="prev"><a href="javascript:">上一页</a></li>
											<li class="next"><a href="javascript:">下一页</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<!--消息输入框-->
						<div class="row">
							<div class="col-sm-12">
								<div class="chat-message-form" style="border: 1px solid #d1d1d1">
									<div class="form-group">
										<!-- 内容回复框-->
										<textarea class="form-control message-input" name="message"
											id="message" placeholder="输入消息内容，按回车键发送"></textarea>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="suofang">
		<p style="color: #ffffff; font-size: 18px; line-height: 30px">
			按下<span style="color: #ffffff; font-size: 20px; font-weight: 600;">鼠标左键</span>拖动图片；点击<span
				style="color: #ffffff; font-size: 20px; font-weight: 600;">鼠标右键</span>关闭图片查看
		</p>
		<img
			src="${pageContext.request.contextPath}/asset/common/images/guan.png"
			class=" guan"> <img src="" class="fangdaimg">
		<div class="imgbut">
			<img
				src="${pageContext.request.contextPath}/asset/common/images/fangda.png"
				class="fangda"> <img
				src="${pageContext.request.contextPath}/asset/common/images/suoxiao.png"
				class="suoxiao"> <img
				src="${pageContext.request.contextPath}/asset/common/images/xuanzhuan.png"
				class="xuanzhuan">
		</div>
	</div>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/asset/common/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/asset/common/js/tools.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/asset/after/js/Math.uuid.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/asset/common/js/suofang.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/asset/common/scripts/layer/layer.js"></script>
	<script type="text/javascript">
		var curTargetId = null, //当前打开的聊天窗口的标识
		userName = '${SESSIONUSER.userName}', //当前登录用户名
		fullName = '${SESSIONUSER.extUser.fullName}', //当前登录用户姓名
		userType = '${SESSIONUSER.type}', //用户类型
		connection = top.connection, timeout = connection.timeout, //超时时间
		_parentBody = parent.$("body"), //父窗口
		_body = $("body"), //当前窗口
		_history = $("#history"), //历史会话列表容器
		_liaotianDiv = $(".liaotian"), //消息记录窗口
		_pageNo = 1, //页数
		_pageSize = 20, //历史聊天记录每页数量
		_chatType = 1, //会话类型
		_pageDiv = null, //最后一次分页标识，用于帮助定位滚动条
		neirongDiv = $(".neirong")[0], //消息记录窗口外部DIV
		_recordTextarea = $("#message"), //发送消息的消息框
		page = 1;//拉取历史会话记录页码
		flas = true;//点击上下页
		var ureNames;//用户姓名
		$(function() {
			var heights = $(document).height() - 248;
			$(".chat-users,.chat-discussion").css("height", heights);
			$(".users-list").css("height",
					parseInt($(".chat-users").css("height")) + (-30) + "px");
			//suofangs("所点击的图片","关闭按钮","要显示的框(页面)","显示页面里的图片","旋转按钮","放大按钮"，"缩小按钮");
			suofangs(".fasongimg", ".guan", "#suofang", ".fangdaimg",
					".xuanzhuan", ".fangda", ".suoxiao");

			getHistoryDialogues(1, 10);

			//给聊天信息窗口绑定滚动事件，分页加载数据
			$(neirongDiv).scroll(
					function() {
						if (this.scrollTop == 0 && _liaotianDiv.html())
							getHistoryMessages(curTargetId, _chatType, _pageNo,
									_pageSize);
					});

			//点击会话，拉取聊天记录
			$('#history div.chat-user').live(
					"click",
					function() {
						var self = $(this);
						$(".chat-user").css("background", "#ffffff");
						self.css("background", "#dff0d8");
						self.find(".newMessage").remove();//去掉新消息提示
						var targetId = self.attr("targetId");
						if (curTargetId != targetId) {// 没有打开对话窗口
							_liaotianDiv.empty();
							curTargetId = targetId;
							ureNames=self.attr("username");
							_pageNo = 1;
							getHistoryMessages(targetId, _chatType, _pageNo,
									_pageSize);
						}
					});

			/*回车发送内容*/
			$(".form-group")
					.keydown(
							function(event) {
								if (event.keyCode == 13) {
									var msg = _recordTextarea.val();
									if (msg) {
										if (curTargetId) {
											var uuid = Math.uuid(32, 62);
											var msgJson = {
												'headImage' : "${SESSIONUSER.extUser.avatarImage.webAdd}"
														|| (top.window._prefixs
																.get("mom") || top.window.location.origin
																+ "/mom/")
														+ "asset/common/images/logo.png",
												'messageInfo' : msg,
												'name' : fullName,
												'targetId' : curTargetId,
												'sender' : userName,
												'sendDate' : new Date()
														.getTime(),
												'contentType' : '1',
												'uuid' : uuid
											};
											var message = top
													.$msg(
															{
																type : "chat",
																to : msgJson.targetId
																+ "@"
																+ connection.domain,
																from : connection.jid,
																id : uuid
															})
													.c("subject")
													.t("message")
													.up()
													.c("body")
													.t(msgJson.messageInfo)
													.up()
													.c("userInfo",{
																"xmlns" : "user:extension:info"
													 }).c("name").t(
															msgJson.name).up()
													.c("avatar").t(
															msgJson.headImage)
													.up().c("type").t(userType)
													.up().c("other").up();
											connection.sendIQ(message,
													function(msg) {
												console.log(msg)
														console.log("发送消息成功："
																+ msg);
													}, function(msg) {
														console.log(msg)
														console.log("发送失败"+msg)
													}, timeout);

											_recordTextarea.val("");
											var content = new Array();
											_warpperMsg(content, msgJson);
											_liaotianDiv.append(content
													.join(''));
											neirongDiv.scrollTop = neirongDiv.scrollHeight;
										}
									}
								}
							});
			/* 点击分页  */
			$(".prev").on("click", function() {
				if (page > 1) {
					page--;
					getHistoryDialogues(page, 10)
				} else {
					layer.alert("当前为第一页", 5)
				}

			})
			$(".next").on("click", function() {
				flas = false;
				page++;
				getHistoryDialogues(page, 10)
			})

		});

		/**
		 * @TODO 处理实时消息
		 * @param msg 内容
		 */
		function realtimeMessage(msg) {
			if (msg) {
				var from = msg.getAttribute("from");
				var targetId = from.split("@")[0];
				var sender = from.split("/")[1];
				var subject = msg.getElementsByTagName("subject")[0].textContent;
				var body = msg.getElementsByTagName("body")[0].textContent;

				var sendTime = msg.getElementsByTagName("sendTime") ? msg
						.getElementsByTagName("sendTime").length > 0 ? msg
						.getElementsByTagName("sendTime")[0].textContent : null
						: null;
				var userinfoNode = msg.getElementsByTagName("userInfo") ? msg
						.getElementsByTagName("userInfo").length > 0 ? msg
						.getElementsByTagName("userInfo")[0] : null : null;
				var userinfo = {};
				if (userinfoNode != null && userinfoNode.childNodes != null) {
					for ( var i in userinfoNode.childNodes) {
						var node = userinfoNode.childNodes[i];
						if (node.tagName)
							userinfo[node.tagName] = node.textContent;
					}
				}
				if ("msg:sendReply" === subject && body) {//发送消息后的回执
					$("#" + body.trim().toLowerCase()).remove();
				} else {
					var historyLi = $("#" + targetId);
					if (!historyLi || historyLi.length == 0) {//会话列表不存在
						var advisory = {
							targetId : targetId,
							lastTime : sendTime,
							extension2 : userinfo.avatar,
							extension1 : userinfo.name,
							userId : sender
						}
						createSingleHistoryDialogues(advisory);
						historyLi = $("#" + targetId);
						if (!historyLi.find(".label-primary").html()) {
							historyLi
									.find(".xiao-time")
									.before(
											"<span class='pull-right label label-primary newMessage'>新消息</span><br/>");
						}

					} else {//会话列表存在
						if (curTargetId != targetId) {//没有打开聊天窗口//提前显示并显示红点
							historyLi.detach();
							$("#history").prepend(historyLi);
							if (!historyLi.find(".label-primary").html()) {
								historyLi
										.find(".xiao-time")
										.before(
												"<span class='pull-right label label-primary newMessage'>新消息</span>");
							}
						} else {//打开聊天窗口
							var content = new Array();
							var msgJson = {
								"targetId" : targetId,
								"name" : userinfo.name,
								"headImage" : userinfo.avatar,
								"sender" : sender,
								"sendDate" : sendTime,
								"contentType" : subject,
								"messageInfo" : body
							}
							_warpperMsg(content, msgJson);
							_liaotianDiv.append(content.join(''));
							neirongDiv.scrollTop = neirongDiv.scrollHeight;
						}
					}
				}
			}
		}

		/**
		 * @TODO 拉取历史会话记录
		 * @param pageNo 页码
		 * @param pageSize 每页记录数
		 */
		function getHistoryDialogues(pageNo, pageSize) {
			var iq = top.$iq({
				type : "get",
				id : "dialoguelist"
			}).c("query", {
				xmlns : "imcrm:dialogue:dialoguelist"
			}).c("pullDialogueArgs", {
				userId : userName,
				pageNo : pageNo || 1,
				pageSize : pageSize || 100
			});
			connection.sendIQ(iq, function(msg) {
				var data = $.parseJSON(msg.textContent)
				if (data.results.length > 0 || flas) {
					createHistoryDialogues(msg.textContent);
				} else {
					page = data.pageNo
					layer.alert("当前为最后一页", 5)
				}

			}, function(msg) {
				console.log("拉取会话列表失败：" + msg);
			}, timeout);
		}
		/**
		 * @TODO 解析拉取的会话记录JSON数据，构建列表
		 * @param data 会话记录JSON
		 */
		function createHistoryDialogues(data) {
			if (data) {
				_history.html("")
				data = $.parseJSON(data);
				var results = data.results;
				$(results).each(function(index, context) {
					var advisory = context;
					var type = context.extension2;
					if (advisory.targetId) {
						createSingleHistoryDialogues(advisory);
					}
				});
			}
		}

		/**
		 * @TODO 解析单个会话列表数据
		 * @param data 单个会话记录JSON
		 */
		function createSingleHistoryDialogues(advisory) {
			var dialogue = $("<div class='chat-user'></div>");
			dialogue.attr("id", advisory.targetId);
			dialogue.attr("targetId", advisory.targetId);
			dialogue.attr("userId", advisory.userId);
			dialogue.attr("userName", advisory.extension1);

			//时间
			var time = $("<div class='xinxiaoxi-kuang'><span class='pull-right label xiao-time'></span></div>");
			time.find("span").append(
					new Date(new Number(advisory.lastTime))
							.format("MM-dd hh:mm"));
			if (advisory.extension3 == 1) {
				time
						.find(".xiao-time")
						.before(
								"<span class='pull-right label label-primary newMessage'>新消息</span>");
			}
			dialogue.append(time);

			//头像
			var headImage = $("<img class='chat-avatar'/>");
			headImage
					.attr(
							"src",
							advisory.extension2
									|| "${pageContext.request.contextPath}/asset/common/image/108x108.png");
			dialogue.append(headImage);

			//用户姓名
			var fullName = $("<div class='chat-user-name'><a></a></div>");
			fullName.find("a").append(advisory.extension1);
			dialogue.append(fullName);
			_history.append(dialogue);
		}

		/**
		 * @param targetId 需要获取与谁聊天的记录这个目标ID
		 * @param chatType 聊天类型 1单聊 2群聊
		 * @param pageNo 页数
		 * @param pageSize 每页记录数
		 */
		function getHistoryMessages(targetId, chatType, pageNo, pageSize) {
			$("#loading").show();
			var iq = top.$iq({
				type : "get",
				id : "messagelist"
			}).c("query", {
				xmlns : "imcrm:message:messagelist"
			}).c("pullMessageArgs", {
				userId : userName,
				targetId : targetId,
				chatType : chatType,
				pageNo : pageNo || 1,
				pageSize : pageSize || 100
			});
			connection.sendIQ(iq, function(msg) {
				$("#loading").hide();
				createHistoryMessage(msg.textContent);
			}, function(msg) {
				console.log("获取聊天历史记录失败：" + msg);
			}, timeout);
		}

		/**
		 * @TODO 解析拉取的历史聊天记录JSON数据，构建列表
		 * @param messagesJson 聊天记录JSON
		 */
		function createHistoryMessage(messagesJson) {
			messagesJson = $.parseJSON(messagesJson);
			results = messagesJson.results;
			if (results.length > 0) {
				//添加页码
				_pageDiv = $('<div style="color: fuchsia;">--第<span id="num">'
						+ _pageNo + '</span>页--</div>');
				_liaotianDiv.prepend(_pageDiv);

				var content = new Array();
				for (var i = results.length - 1; i >= 0; i--) {
					var result = results[i];
					var msgJson = {
						targetId : result.accepter,
						name : result.extension1,
						headImage : result.extension2,
						sender : result.sender,
						sendDate : result.sendDate,
						contentType : result.contentType,
						messageInfo : result.messageInfo
					}
					_warpperMsg(content, msgJson);
				}
				_liaotianDiv.prepend(content.join(''));

				if (_pageNo++ > 1) {
					$(neirongDiv).scrollTop(_pageDiv.offset().top - 350);
				} else {
					//获取内容的滚动高度，让内容保持在底部
					neirongDiv.scrollTop = neirongDiv.scrollHeight;
				}
			}
		}

		/**
		 * @TODO 封装聊天记录
		 * @param msgJson 聊天记录JSON
		 * @param content 保存内容的数组
		 */
		function _warpperMsg(content, msgJson) {
			if (userName == msgJson.sender) {//消息发送者是当前登录人员
				msgJson.headImage = msgJson.headImage
						|| '${pageContext.request.contextPath}/asset/common/images/logo.png';
				content
						.push("<div class='chat-message'><img class='message-right' src='"+msgJson.headImage+"' /><div class='message-left'><a class='message-author' > ");
				content
						.push(msgJson.name
								+ "</a><span class='message-data' style='float: left'> ")
				content.push(new Date(new Number(msgJson.sendDate))
						.format("MM-dd hh:mm"));
				content
						.push("</span><span class='message-content'style='text-align: left'>");
				/* if (msgJson.uuid) {
					content
							.push('<img style="margin:0px; width: 20px; height: 20px;float:left " id="'
									+ msgJson.uuid.toLowerCase()
									+ '" alt="发送中..." src="/mom/asset/common/scripts/layer/skin/default/xubox_loading2.gif">')
				} */
			} else {
				msgJson.headImage = msgJson.headImage
						|| '${pageContext.request.contextPath}/asset/common/image/108x108.png';
				content
						.push("<div class='chat-message'><img class='message-avatar' src='"+msgJson.headImage+"' /><div class='message'><a class='message-author' > ");
				content
						.push(msgJson.name
								+ "</a><span class='message-date' style='float: right'> ")
				content.push(new Date(new Number(msgJson.sendDate))
						.format("MM-dd hh:mm"));
				content.push("</span><span class='message-content'>");
			}
			//处理消息内容 message(1), sound(2),image(3),attachment(4), other(5)
			switch (msgJson.contentType) {
			case '1':
			case 'message':
				content.push(msgJson.messageInfo);
				break;
			case '2':
			case 'sound':
				_handleAudio(msgJson.messageInfo, content);
				break;
			case '3':
			case 'image':
				if (userName == msgJson.sender) {
					content.push("<img class='fasongimg' src='")
					content.push(msgJson.messageInfo);
					content
							.push("'style='float: right'><p style='clear: both;height: 0;margin: 0;padding: 0'></p>");
				} else {
					content.push("<img class='fasongimg' src='");
					content.push(msgJson.messageInfo);
					content.push("'>");
				}
				break;
			}
			content.push("</span></div></div>");
		}

		/**
		 * @TODO 处理语音
		 * @param filePath
		 * @param content
		 * @return html
		 */
		function _handleAudio(filePath, content) {
			$
					.ajax({
						type : "post",
						url : "${pageContext.request.contextPath}/after/consultant/formatAudio",
						async : false,
						data : {
							"filePath" : filePath
						},
						success : function(data) {
							if (data) {
								filePath = filePath.replace(".amr", ".mp3");
							}
						}
					});
			content
					.push('<object type="application/x-shockwave-flash" data="${pageContext.request.contextPath}/asset/common/scripts/dewplayer/dewplayer-mini.swf?mp3=');
			content.push(filePath);
			content.push('" width="160" height="20" id="dewplayermini">');
			content.push('<param name="wmode" value="transparent" />');
			content
					.push('<param name="movie" value="${pageContext.request.contextPath}/asset/common/scripts/dewplayer/dewplayer-mini.swf?mp3=');
			content.push(filePath);
			content.push('" /></object>');
			return content;
		}
	</script>
</body>
</html>