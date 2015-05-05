<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>发送结果</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="sendResultRecordController.do?save">
			<input id="id" name="id" type="hidden" value="${sendResultRecordPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							ddo消息id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="ddoMsgId" name="ddoMsgId" 
							   value="${sendResultRecordPage.ddoMsgId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发送结果:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sendResult" name="sendResult" ignore="ignore"
							   value="${sendResultRecordPage.sendResult}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>