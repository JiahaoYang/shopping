<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/join/isLogin.jsp" %>
<%@ include file="/index.jsp" %>
<%@ page import="com.sun.rowset.CachedRowSetImpl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单</title>
</head>
<body>
<jsp:useBean id="goods" class="yjh.entity.Goods" scope="session" />
<center><br/><br/>
<table>
<caption><b>我的订单</b></caption>
	<tr>
		<th>序号</th>
		<th>商品名</th>
		<th>购买数量</th>
	</tr> 	
<%
	CachedRowSetImpl rowSet = goods.getRowSetImpl();
	if (rowSet == null) {
		out.println("商品数据库中无数据");
		return;
	}
	rowSet.last();	//光标移动到最后一行数据
	int totalRecord = rowSet.getRow();	//当前行数
	int pageSize = goods.getPageSize();	//每页记录数
	int totalPages = goods.getTotalPage();	//总页数
	int currentPage = goods.getCurrentPage();	//当前页
	

	//翻页
	if (request.getParameter("currentPage") != null) {
		int newCurrentPage = Integer.parseInt(request.getParameter("currentPage"));
		//下一页在范围内
		if (newCurrentPage > 0 && newCurrentPage <= totalPages)
			currentPage = newCurrentPage;
		else
			currentPage = goods.getCurrentPage();	//还原
	}
	//分页
	if (totalRecord%pageSize == 0)
		totalPages = totalRecord/pageSize;
	else
		totalPages = totalRecord/pageSize+1;
	goods.setCurrentPage(currentPage);
	goods.setPageSize(pageSize);
	goods.setTotalPage(totalPages);
	
	if (totalPages >= 1) {
		
		/*
		if (goods.getCurrentPage() < 1)
			goods.setCurrentPage(goods.getTotalPage());

		if (goods.getCurrentPage() > goods.getTotalPage())
			goods.setCurrentPage(1);
		*/
		
		int index = ((goods.getCurrentPage()-1)*pageSize) + 1;
		rowSet.absolute(index);	//光标移动到当前页的第一条记录

		boolean flag = true;
		for (int i = 1; i <= pageSize && flag; ++i) {
			String commodityName = rowSet.getString(1);
			String orderNumber = rowSet.getString(2);
%>
		<tr>
			<td><%=i%></td>
			<td><%=commodityName%></td>
			<td><%=orderNumber%></td>
		</tr>
	<%
		flag = rowSet.next();
		}
	} 
	%>
</table>
<br><%= goods.getCurrentPage() %>/<%= goods.getTotalPage() %> 页
<table>
	<tr>
		<td>
			<form action="" method="post">
				<input type="hidden" name="currentPage" value="<%=(currentPage-1)%>">
				<input type="submit" value="上一页">
			</form>
		</td>
		<td>
			<form action="" method="post">
				<input type="hidden" name="currentPage" value="<%=(currentPage+1)%>">
				<input type="submit" value="下一页">
			</form>
		</td>
	</tr>
</table>
</center>
</body>
</html>