<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String ctx = request.getContextPath();
	request.setAttribute("ctx", ctx);
%>
<html>
<head>
	<title>环博年会</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link rel="shortcut icon" href="${ctx}/resources/images/favicon.ico">
	<script src="${ctx}/resources/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>

	<style type="text/css">
		body{ margin:0; padding:0; font-size:0.8em; color: #fff; font-family:"微软雅黑"; background: url("${ctx}/resources/images/qd_bg.jpg") no-repeat ; background-size:100% 100%;} 
		.con{ width: 100%; background: url("${ctx}/resources/images/bg01.jpg") no-repeat ; background-size:100% 100%; height:74%; } 
		.btn_cj{ background:url("${ctx}/resources/images/btn_bg.png") no-repeat; width:396px; height:132px; color:#e1ff00; text-align:center; line-height:132px;  font-size:53px; border:none; font-family:"微软雅黑"}
		.wx_img img{ width:60px; margin:10px; border:3px solid #fff;}
	</style>
</head>

<body><table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td align="center">
	
	
	<table width="65%" border="0" cellspacing="0" cellpadding="0" style="padding-top:20%">
  <tr>
    <td id="li1" width="40%" align="right" valign="top" class="wx_img"></td>
    <td align="center" width="20%"><img src="${ctx}/resources/images/code.png"></td>
    <td id="li2" valign="top" width="40%" valign="top" class="wx_img"></td>
  </tr>
</table>
	
	
	</td>
  </tr>
  <tr>
    <td align="center" valign="bottom" style="padding-bottom:2%">
  	<input id="changeButton" type="button" value="开始抽奖" class="btn_cj" onclick="doit('start');">
	    <%-- <c:when test="${'start' eq status}">
	    	<input id="changeButton" type="button" value="暂停抽奖" class="btn_cj" onclick="doit('pause');">
		</c:when>
		<c:when test="${'pause' eq status or 'init' eq status}">
			<input id="changeButton" type="button" value="开始抽奖" class="btn_cj" onclick="doit('start');">
		</c:when>
		<c:when test="${'finish' eq status}">
			<input id="changeButton" type="button" value="抽奖已结束" class="btn_cj">
		</c:when> --%>
    </td>
  </tr>
</table>
<script type="text/javascript">
	window.onload=function(){
		//假设这里每个五分钟执行一次test函数
		getSigninusers();
		getDrawStatus();
	} 
	
	function getSigninusers() {
		setTimeout(getSigninusers, 3000);
		$.ajax({
			  type : "POST",
			  async : false, //同步请求
			  url : "${ctx}/signinuser/list",
			  timeout:10000,
			  success:function(json){
				  if (200 == json["code"]) {
					  var signuserVOs = json["data"] || '[]';
					  var li1 = '', li2 = '';
					  for(var i=0; i<signuserVOs.length; i++) {
						  if (0 == i%2) {
							  li1 += '<img src="'+signuserVOs[i]["headimgurl"]+'">';
						  } else {
							  li2 += '<img src="'+signuserVOs[i]["headimgurl"]+'">';
						  }
					  }
					  $("#li1").html(li1);
					  $("#li2").html(li2);
				  }
			  },
			  error: function() {
			        // alert("失败，请稍后再试！");
			  }
		});
	}
	
	function getDrawStatus() {
		setTimeout(getDrawStatus, 3000);
		$.ajax({
			  type : "POST",
			  async : false, //同步请求
			  url : "${ctx}/luckuser/drawStatus",
			  timeout:10000,
			  success:function(json){
				  if (200 == json["code"]) {
					  var status = json["data"] || "";
					  if ("start" == status) {
						  $("#changeButton").val("暂停抽奖").attr("onclick", "doit('pause')");
					  } else if ("pause" == status) {
						  $("#changeButton").val("开始抽奖").attr("onclick", "doit('start')");
					  } else if ("finish" == status) {
						  $("#changeButton").val("抽奖已结束").removeAttr("onclick");
					  }
				  }
			  },
			  error: function() {
			        // alert("失败，请稍后再试！");
			  }
		});
	}
	
	function doit(type) {
		$.ajax({
			  type : "POST",
			  async : false, //同步请求
			  url : "${ctx}/luckuser/" + type,
			  timeout:10000,
			  success:function(json){
				  if (200 == json["code"]) {
					  if ("start" == type)
					  		window.location.href = "${ctx}/results";
					  else
						 	window.location.href = window.location.href;
				  } else
					alert(json["message"]);
			  },
			  error: function() {
			        // alert("失败，请稍后再试！");
			  }
		});
	}
</script>
</body>
</html>
