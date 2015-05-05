<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>计费状态报告</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="billReportController.do?save">
		<input id="id" name="id" type="hidden" value="${billReportPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">事务id:</label>
		      <input class="inputxt" id="transationId" name="transationId" ignore="ignore"
					   value="${billReportPage.transationId}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">计费状态编码:</label>
		      <input class="inputxt" id="billStateCode" name="billStateCode" ignore="ignore"
					   value="${billReportPage.billStateCode}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">状态:</label>
		      <input class="inputxt" id="state" name="state" 
					   value="${billReportPage.state}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">处理结果:</label>
		      <input class="inputxt" id="processResult" name="processResult" ignore="ignore"
					   value="${billReportPage.processResult}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">下发状态报告结果编码:</label>
		      <input class="inputxt" id="resultCode" name="resultCode" ignore="ignore"
					   value="${billReportPage.resultCode}">
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>