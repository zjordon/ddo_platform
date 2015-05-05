<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>重新上行记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="repeatUpRecordController.do?save">
		<input id="id" name="id" type="hidden" value="${repeatUpRecordPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">重发原因:</label>
		      <input class="inputxt" id="repeatReason" name="repeatReason" ignore="ignore"
					   value="${repeatUpRecordPage.repeatReason}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">重发时间:</label>
		      <input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="repeatTime" name="repeatTime" ignore="ignore"
					     value="<fmt:formatDate value='${repeatUpRecordPage.repeatTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">上行结果编码:</label>
		      <input class="inputxt" id="resultCode" name="resultCode" ignore="ignore"
					   value="${repeatUpRecordPage.resultCode}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">重发记录id:</label>
		      <input class="inputxt" id="recordId" name="recordId" 
					   value="${repeatUpRecordPage.recordId}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>