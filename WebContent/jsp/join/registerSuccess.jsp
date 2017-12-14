<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() +
						":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="3; url=<%=path%>/jsp/join/login.jsp"> 
<title>Insert title here</title>
</head>
<body>
	<b><font color="red">注册成功</font></b>
	三秒后跳转到登录页...
</body>
</html>