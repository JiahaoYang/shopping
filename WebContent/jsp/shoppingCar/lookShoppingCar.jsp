<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/index.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center><br/><br/>
<%
	List<String> items = null;
	items = loginBean.getCar();
	if (items == null || items.size() == 0) {
%>
	<font color="green">购物车空空如也,快去</font>
	<a href="/shopping1/GoodsDao?key=2"><font color="red">购物</font></a>
<%
	return;
	}
%>
	<table>
	 <caption>我的购物车</caption>
	 <tr>
	 	<th>序号</th>
	 	<th>编号</th>
	 	<th>名称</th>
	 	<th>产地</th>
	 	<th>价格</th>
	 	<th>操作</th>
	 </tr>
<%
	double totalPrice = 0.0;
	for (int i = 0; i < items.size(); ++i) {
		String[] item = items.get(i).split(",");
%>
	<tr bgcolor="#43CD80">
		<td><%=i+1%></td>
		<td><%=item[0]%></td>
		<td><%=item[1]%></td>
		<td><%=item[2]%></td>
		<td><%=item[3]%></td>
		<td><a href="/shopping1/DeleteGoodFromCar?id=<%=i%>">删除</a></td>
	</tr>
<%
	totalPrice += Double.parseDouble(item[3]);
	}
	String backPage = "<a href='/shopping1/jsp/browse/showGoods.jsp'><font color=green>继续购物</font></a>";
%>
	</table>
	<table>
		<tr>
			<td>合计:<%=totalPrice%>RMB</td>
			<td>
				<form action="/shopping1/BuyGoods" method="post">
					<input type="submit" value="确认付款">
				</form>
			</td>
			<td><%=backPage%></td>
		</tr>
	</table>
</center>
</body>
</html>