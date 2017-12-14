<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/navbar.jsp" %>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf8");
	String userName = "";
	String userPwd = "";
	Cookie[] cookies = null;
	cookies = request.getCookies();
	if (cookies != null && cookies.length > 0) {
		for (Cookie cookie : cookies) {
			if ("userName".equals(cookie.getName()))
				userName = cookie.getValue();
			if ("userPwd".equals(cookie.getName()))
				userPwd = cookie.getValue();
		}
	}
%>
<div align="center">
	<form action="<%=path%>/HandleLogin" method="post">
	
	<table>
		<tr>
			<td colspan="2">
				<input name="userName" value="<%=userName %>" placeholder="userName"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="password" name="userPwd" value="<%=userPwd %>" placeholder="password"/>
			</td>
		</tr>
		<tr>
			<td>
				<input type="checkbox" name="isCookie" value="isCookie" checked="checked">记我十天
			</td>
			
			<td>
				<input type="submit" value="登陆"/>
			</td>
		</tr>
		</table>
		
	</form>
</div>
</body>
</html>