<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>需要重新上行的记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="needUpChannelController.do?save">
		<input id="id" name="id" type="hidden" value="${needUpChannelPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">重发原因:</label>
		      <input class="inputxt" id="repeatReason" name="repeatReason" ignore="ignore"
					   value="${needUpChannelPage.repeatReason}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">重发记录id:</label>
		      <input class="inputxt" id="recordId" name="recordId" ignore="ignore"
					   value="${needUpChannelPage.recordId}">
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>