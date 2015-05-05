<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>需要重新下发的状态报告</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="needRepeatReportController.do?save">
		<input id="id" name="id" type="hidden" value="${needRepeatReportPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">重发原因:</label>
		      <input class="inputxt" id="repeatReason" name="repeatReason" ignore="ignore"
					   value="${needRepeatReportPage.repeatReason}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">对应的计费状态报告id:</label>
		      <input class="inputxt" id="billReportId" name="billReportId" ignore="ignore"
					   value="${needRepeatReportPage.billReportId}">
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>