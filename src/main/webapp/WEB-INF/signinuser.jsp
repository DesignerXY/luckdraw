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
	<c:when test="${not empty signinuser}">
		<script type="text/javascript">
			localStorage.setItem("userid", "${signinuser.userid}");
			window.location.href = "${ctx}/luckuser?signinuserid=${signinuser.id}";
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			window.location.href = "${ctx}/signinuser?firstRquest=1&userid=" + (localStorage.getItem("userid") || "");
		</script>
	</c:otherwise>
</c:choose>
</body>
</html>