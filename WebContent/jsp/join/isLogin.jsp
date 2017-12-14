<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="yjh.entity.Login" %>
<%
	Login logined = (Login) session.getAttribute("loginBean");
	if (logined == null)
		response.sendRedirect("/shopping1/jsp/join/login.jsp");
%>