<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>需要重发的消息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="needRepeatMsgController.do?save">
		<input id="id" name="id" type="hidden" value="${needRepeatMsgPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">重发原因:</label>
		      <input class="inputxt" id="repeatReason" name="repeatReason" ignore="ignore"
					   value="${needRepeatMsgPage.repeatReason}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">消息id:</label>
		      <input class="inputxt" id="ddoMsgId" name="ddoMsgId" ignore="ignore"
					   value="${needRepeatMsgPage.ddoMsgId}">
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>