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
<link rel="shortcut icon" href="${ctx}/resources/imagesfavicon.ico">
<script src="${ctx}/resources/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>

<style type="text/css">
body{ margin:0; padding:0; font-size:0.8em; color: #fff; font-family:"微软雅黑"; background: url("${ctx}/resources/images/qd_bg2.jpg") no-repeat ; background-size:100% 100%;} 
.con{ width: 100%; background: url("${ctx}/resources/images/bg01.jpg") no-repeat ; background-size:100% 100%; height:74%; } 
.btn_cj{ background:url("${ctx}/resources/images/btn_bg.png") no-repeat; width:396px; height:132px; color:#e1ff00; text-align:center; font-size:53px; border:none; font-family:"微软雅黑";}
.wx_img img{ width:60px; margin:10px; border:3px solid #fff;}
.tittle{ color:#f24e30; padding:10px; font-size:1.2em; font-weight:bold; border-radius:10px; text-align:center; margin-bottom:10px;}
.zj_box{ text-align:center;}
.zj_box img{width:60px; margin:10px; border:3px solid #fff;}
.box{width:10%; float:left; padding:10px;}
.box2{width:15%; float:left; padding:10px;}
.box3{width:25%; float:left; padding:10px;}

</style>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td align="center" valign="top" style="padding-top:13%; padding-left:15%;">
	
		<div class="box">
              <div class="tittle" style="background:#f24e30; color:#fff;">特等奖</div>
              <div id="awards0" class="zj_box"></div>
        </div>
			
		<div class="box">
              <div class="tittle" style="background:#fdf4c6;">一等奖</div>
              <div id="awards1" class="zj_box"></div>
        </div>
			
		<div class="box2">
              <div class="tittle" style="background:#fcdfbe;">二等奖</div>
              <div id="awards2" class="zj_box"></div>
        </div>
			
		<div class="box2">
              <div class="tittle" style="background:#e5d1bb;">三等奖</div>
              <div id="awards3" class="zj_box"></div>
        </div>
						
		<div class="box3">
              <div class="tittle" style="background:#e2e2e2;">四等奖</div>
              <div id="awards4" class="zj_box"></div>
        </div>
		<div style="clear:both"></div>
	</td>
  </tr>
  <tr>
    <td align="center" valign="bottom" style="padding-bottom:2%"><input name="" type="button" value="返回首页" class="btn_cj" onclick="window.location.href='${ctx}/luckuser/controller';"></td>
  </tr>
</table>

<script type="text/javascript">
window.onload=function(){
	//假设这里每个五分钟执行一次test函数
	getLuckusers();
} 

function getLuckusers() {
	setTimeout(getLuckusers, 3000);
	$.ajax({
		  type : "POST",
		  async : false, //同步请求
		  url : "${ctx}/luckuser/list",
		  timeout:10000,
		  success:function(json){
			  if (200 == json["code"]) {
				  var data = json["data"] || '[]';
				  var li0 = '',
				  	li1 = '',
				  	li2 = '',
				  	li3 = '',
				  	li4 = '';
				  var ac0=0, ac1=0, ac2=0, ac3=0, ac4=0; 
				  var div = '';
				  for(var i=0; i<data.length; i++) {
					  div = '<img title="'+data[i]["username"]+'" src="'+(data[i]["headimgurl"] || '${ctx}/resources/images/timg.jpg')+'">';
					  if (0 == data[i]["awardsid"] - 1) {
						  li0 += div;
						  ac0++;
					  } else if (1 == data[i]["awardsid"] - 1) {
						  li1 += div;
						  ac1++;
					  } else if (2 == data[i]["awardsid"] - 1) {
						  li2 += div;
						  ac2++;
					  } else if (3 == data[i]["awardsid"] - 1) {
						  li3 += div;
						  ac3++;
					  } else if (4 == data[i]["awardsid"] - 1) {
						  li4 += div;
						  ac4++;
					  }
				  }
				  $("#awards0").html(li0);
				  $("#awards1").html(li1);
				  $("#awards2").html(li2);
				  $("#awards3").html(li3);
				  $("#awards4").html(li4);
			  }
		  },
		  error: function() {
		        // alert("失败，请稍后再试！");
		  }
	});
}
</script>
</body>
</html>
