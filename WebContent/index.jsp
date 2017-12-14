<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
 <jsp:useBean id="loginBean" class="yjh.entity.Login" scope="session"/>
  <ul class="user">
  	<li>
	<%
		String str = null;
		str = loginBean.getUsername();
		if (str == null || "".equals(str)) {
			//jsp中内置了session对象，Returns the current HttpSession associated with this request
			HttpSession s = request.getSession(true);
			s.invalidate();
	%>
  		<a href="jsp/join/login.jsp">登录</a>or<a href="jsp/join/register.jsp">注册</a>
	<%
			return;
		}
	%>
		<dl>
			<dt>
				欢迎您,<b><font color="red"><%=str%></font></b>
				<a href="<%=path%>/HandleExit"><font color="#CDC9C9">退出</font></a>
			</dt>
		</dl>

  	</li>
  </ul>
</body>
</html>