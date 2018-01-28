<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String ctx = request.getContextPath();
	request.setAttribute("ctx", ctx);
%>
<html>

	<head>
		<title>环博抽奖</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/index.css" />

	</head>

	<body>
		<div class="con">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
				<tr>
					<td align="center" valign="bottom">
						<div class="btn_cj">
							<a href="${ctx}/luckuser/shake?signinuserid=${signinuserid}">准备抽奖</a>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-top:3%;">
			<tr>
				<td class="td_border">
					<P>奖项设置</P>特等奖:<span>1800</span>元(1个)&nbsp;&nbsp;&nbsp;&nbsp;一等奖:<span>1000</span>元(2个)&nbsp;&nbsp;&nbsp;&nbsp;二等奖:<span>600</span>元(4个)&nbsp;&nbsp;&nbsp;&nbsp;三等奖:<span>388</span>元(10个)&nbsp;&nbsp;&nbsp;&nbsp;四等奖:<span>268</span>元(15个)</td>
			</tr>
			<tr>
				<td class="td_border">
					<P>抽奖规则</P>1、拿起手机开启微信扫一扫，扫描抽奖二维码，进入抽奖界面。<br><br> 2、当主持人说抽奖开始时在场人员拿起手机，进入抽奖界面，点击”准备抽奖”按钮后并进行摇一摇(抽奖分为三轮，每一轮前10位进入系统的就是本轮幸运者，所有奖项随机抽取)此时屏幕显示的奖项就是您最终获得的奖品。

					<br><br> 3、未中奖者进行下一轮，已摇中奖项者，将不可参加下一轮摇奖。

				</td>
			</tr>
		</table>
		<script src="${ctx}/resources/js/mui.min.js" type="text/javascript" charset="utf-8"></script>
		<c:if test="${not empty errorMsg}">
		<script type="text/javascript">
			/* mui.ready(function() {
				mui('table').on('tap', '.btn_cj', function() {
					window.location.href = "${ctx}/luckuser/shake?signinuserid=${signinuserid}";
				})
			}) */
			alert("${errorMsg}");
		</script>
		</c:if>
	</body>

</html>