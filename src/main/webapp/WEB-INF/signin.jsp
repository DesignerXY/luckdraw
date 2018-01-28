<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String ctx = request.getContextPath();
	request.setAttribute("ctx", ctx);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign in controller</title>
<script src="${ctx}/resources/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<c:choose>
	<c:when test="${'canSignin' eq canSignin}">
		<input type="button" value="finish" onclick="doit('finish');"/>
	</c:when>
	<c:otherwise>
		<input type="button" value="start" onclick="doit('start');"/>
		<input type="button" value="restart" onclick="doit('restart');"/>
	</c:otherwise>
</c:choose>
<script type="text/javascript">
function doit(type) {
	$.ajax({ 
		url: "${ctx}/signin/" + type, 
		success: function(json){
			if (200 == json["code"])
        		alert("success");
			else
				alert(json["message"]);
			window.location.href = window.location.href;
      	}
	});
}
</script>
</body>
</html>