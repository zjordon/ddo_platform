<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.jason.ddoMsg.externalInterface.ChannelRequestInterface"%>
<%
String username = request.getParameter("username");
String pass = request.getParameter("password");
String msisdn = request.getParameter("mobile");
String instruct = request.getParameter("content");
String productId = request.getParameter("productid");
String dstTime = request.getParameter("dstime");
String resp = ChannelRequestInterface.getInstance().receiveRequest(username, pass, msisdn, instruct, productId, dstTime, 2);
%>
<%=resp%>