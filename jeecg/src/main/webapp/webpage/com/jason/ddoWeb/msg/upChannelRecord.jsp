<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>上行渠道记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="upChannelRecordController.do?save">
		<input id="id" name="id" type="hidden" value="${upChannelRecordPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">上行结果编码:</label>
		      <input class="inputxt" id="resultCode" name="resultCode" ignore="ignore"
					   value="${upChannelRecordPage.resultCode}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">上行处理结果:</label>
		      <input class="inputxt" id="processResult" name="processResult" ignore="ignore"
					   value="${upChannelRecordPage.processResult}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">消息id:</label>
		      <input class="inputxt" id="ddoMsgId" name="ddoMsgId" ignore="ignore"
					   value="${upChannelRecordPage.ddoMsgId}">
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>