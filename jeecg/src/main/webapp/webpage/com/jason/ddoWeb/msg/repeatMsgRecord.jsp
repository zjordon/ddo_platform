<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>消息重发记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="repeatMsgRecordController.do?save">
			<input id="id" name="id" type="hidden" value="${repeatMsgRecordPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							返回消息编码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="returnMsgCode" name="returnMsgCode" ignore="ignore"
							   value="${repeatMsgRecordPage.returnMsgCode}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							请求id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="ddoMsgId" name="ddoMsgId" 
							   value="${repeatMsgRecordPage.ddoMsgId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							请求返回时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="responseTime" name="responseTime" ignore="ignore"
							     value="<fmt:formatDate value='${repeatMsgRecordPage.responseTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>