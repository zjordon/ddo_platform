<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>重新下发状态报告记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="repeatReportRecordController.do?save">
		<input id="id" name="id" type="hidden" value="${repeatReportRecordPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">重发原因:</label>
		      <input class="inputxt" id="repeatReason" name="repeatReason" ignore="ignore"
					   value="${repeatReportRecordPage.repeatReason}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">重发时间:</label>
		      <input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="repeatDate" name="repeatDate" ignore="ignore"
					     value="<fmt:formatDate value='${repeatReportRecordPage.repeatDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">下发状态报告结果编码:</label>
		      <input class="inputxt" id="resultCode" name="resultCode" ignore="ignore"
					   value="${repeatReportRecordPage.resultCode}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">对应的计费状态报告id:</label>
		      <input class="inputxt" id="billReportId" name="billReportId" 
					   value="${repeatReportRecordPage.billReportId}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>