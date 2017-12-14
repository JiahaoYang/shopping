<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/index.jsp" %>
<%@ page import="com.sun.rowset.CachedRowSetImpl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>浏览商品</title>
</head>
<body>
<jsp:useBean id="goods" class="yjh.entity.Goods" scope="session"/>
<center><br/><br/>
<table>
	<caption><b>商品信息</b></caption>
	<tr>
		<th>序号</th>
		<th>商品名称</th>
		<th>价格</th>
		<th>查看详情</th>
		<th>添加到购物车</th>
	</tr>
<%	
	CachedRowSetImpl rowSet = goods.getRowSetImpl();
	
	if (rowSet == null) {
		out.println("数据库中无商品");
		return;
	}
	rowSet.last();
	int totalRecord = rowSet.getRow();
	int pageSize = goods.getPageSize();
	int totalPages = goods.getTotalPage();
	int currentPage = goods.getCurrentPage();
	
	//翻页
	if (request.getParameter("currentPage") != null) {
		int newCurrentPage = Integer.parseInt(request.getParameter("currentPage"));
		//页码在范围内
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
	
	if (totalPages > 0) {
		int index = (currentPage-1)*pageSize + 1;
		rowSet.absolute(index);
		boolean flag = true;
		for (int i = 0; i < pageSize && flag; ++i) {
	       int id = rowSet.getInt(1);
	       String name = rowSet.getString(2);
	       String made = rowSet.getString(3);
	       String price = rowSet.getString(4);
	       String number = rowSet.getString(5);
	       String pic = rowSet.getString(6);
	       int category = rowSet.getInt(7);
	       
	       String commodity = id + "," + name + "," + made + "," + price + 
	    		   				"," + number + "," + pic + "," + category;
	       String carButton = "<form action='/shopping1/PutToCar' method='post'>" + 
	    		   				"<input type='hidden' name='putToCar' value=" + commodity + "/>" +
	       						"<input type='submit' value='加入购物车'/></form>";
	       String detailButton = "<form action='/shopping1/jsp/browse/showDetail' method='post'>" + 
	   							"<input type='hidden' name='detail' value=" + commodity + "/>" +
								"<input type='submit' value='商品详情'/></form>";
%>
		<tr>
	    	<td><%= (currentPage-1)*pageSize + i+1 %></td>
	    	<td><%= name %></td>
	    	<td><%= price %>￥</td>
	    	<td><%= detailButton %></td>
	    	<td><%= carButton %></td>
		</tr>
<%			flag = rowSet.next();
		}
	}
%>
</table>
<table>
	<tr>
		<td>
			<form action="" method="post">
				<input type="hidden" name="currentPage" value="<%=currentPage-1%>"/>
				<input type="submit" value="上一页">
			</form>
		</td>	
		<td>
			<form action="" method="post">
				<input type="hidden" name="currentPage" value="<%=currentPage+1%>"/>
				<input type="submit" value="下一页">
			</form>
		</td>	
	</tr>
</table>
</center>
</body>
</html>