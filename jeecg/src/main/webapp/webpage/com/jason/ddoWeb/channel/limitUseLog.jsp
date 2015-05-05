<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>渠道限额使用流水</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="limitUseLogController.do?save">
		<input id="id" name="id" type="hidden" value="${limitUseLogPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">本次使用的金额:</label>
		      <input class="inputxt" id="useAmount" name="useAmount" ignore="ignore"
					   value="${limitUseLogPage.useAmount}" datatype="d">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">使用后剩余的日金额:</label>
		      <input class="inputxt" id="remainDayAmount" name="remainDayAmount" ignore="ignore"
					   value="${limitUseLogPage.remainDayAmount}" datatype="d">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">使用后剩余的月金额:</label>
		      <input class="inputxt" id="remainMonthAmount" name="remainMonthAmount" ignore="ignore"
					   value="${limitUseLogPage.remainMonthAmount}" datatype="d">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">请求id:</label>
		      <input class="inputxt" id="requestId" name="requestId" ignore="ignore"
					   value="${limitUseLogPage.requestId}">
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>