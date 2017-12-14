<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/navbar.jsp" %>
<!DOCTYPE>
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
</head>
<body>
<body>
<jsp:useBean id="userBean" class="yjh.entity.Register" scope="request"/>
<div align="center">
	<form action="<%=path%>/HandleRegister" method="post">
		<table>
			<tr>
				<td>用户名:<input type="text" name="userName" placeholder="*必填"/></td>
				<td>密码：<input type="password" name="userPwd" placeholder="*必填(6-16字符之间)"/></td>
			</tr>
			<tr>
				<td>重复密码：<input type="password" name="againPwd" placeholder="*必填"/></td>
				<td>联系电话：<input type="tel" name="phone" placeholder="*选填"/></td>
			</tr>
			<tr>
				<td>邮寄地址：<input type="text" name="address" placeholder="*选填"/></td>
				<td>真实姓名：<input type="text" name="realName" placeholder="选填"/></td>
			</tr>
			<tr>
				<td>
					状态:<font color=red><jsp:getProperty name="userBean" property="backNews" /></font>
				</td>
				<td>
					<input type="image" src="<%=path %>/image/page/submit.png" alt="submit" height="40" width="100"/>
				</td>
			</tr>
		</table>	
	</form>
</div>

</body>
</body>
</html>