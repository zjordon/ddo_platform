<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>渠道日使用限额</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="channelDayLimitController.do?save">
		<input id="id" name="id" type="hidden" value="${channelDayLimitPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">日期:</label>
		      <input class="inputxt" id="day" name="day" ignore="ignore"
					   value="${channelDayLimitPage.day}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">限额:</label>
		      <input class="inputxt" id="limitAmount" name="limitAmount" ignore="ignore"
					   value="${channelDayLimitPage.limitAmount}" datatype="d">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">渠道id:</label>
		      <input class="inputxt" id="channelId" name="channelId" 
					   value="${channelDayLimitPage.channelId}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>