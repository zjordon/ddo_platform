<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>短信任务</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="channelRequestController.do?save">
		<input id="id" name="id" type="hidden" value="${channelRequestPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">用户名:</label>
		      <input class="inputxt" id="username" name="username" 
					   value="${channelRequestPage.username}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">密码:</label>
		      <input class="inputxt" id="password" name="password" 
					   value="${channelRequestPage.password}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">发送指令:</label>
		      <input class="inputxt" id="content" name="content" ignore="ignore"
					   value="${channelRequestPage.content}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">产品id:</label>
		      <input class="inputxt" id="productId" name="productId" ignore="ignore"
					   value="${channelRequestPage.productId}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">定时时间:</label>
		      <input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="dstime" name="dstime" ignore="ignore"
					     value="<fmt:formatDate value='${channelRequestPage.dstime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">请求时间:</label>
		      <input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="requestTime" name="requestTime" ignore="ignore"
					     value="<fmt:formatDate value='${channelRequestPage.requestTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">状态:</label>
		      <input class="inputxt" id="state" name="state" 
					   value="${channelRequestPage.state}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">返回状态:</label>
		      <input class="inputxt" id="returnState" name="returnState" 
					   value="${channelRequestPage.returnState}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">开始处理时间:</label>
		      <input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="beginTime" name="beginTime" 
					     value="<fmt:formatDate value='${channelRequestPage.beginTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">处理完成时间:</label>
		      <input class="inputxt" id="endTime" name="endTime" ignore="ignore"
					   value="${channelRequestPage.endTime}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">渠道id:</label>
		      <input class="inputxt" id="channelId" name="channelId" ignore="ignore"
					   value="${channelRequestPage.channelId}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">来源类型:</label>
		      <input class="inputxt" id="sourceType" name="sourceType" 
					   value="${channelRequestPage.sourceType}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">处理结果:</label>
		      <input class="inputxt" id="processResult" name="processResult" 
					   value="${channelRequestPage.processResult}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>