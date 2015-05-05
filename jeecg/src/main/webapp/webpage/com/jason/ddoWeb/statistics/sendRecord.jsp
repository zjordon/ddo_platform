<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>发送记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="sendRecordController.do?save">
			<input id="id" name="id" type="hidden" value="${sendRecordPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							ddo消息id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="ddoMsgId" name="ddoMsgId" 
							   value="${sendRecordPage.ddoMsgId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							手机号码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="msisdn" name="msisdn" 
							   value="${sendRecordPage.msisdn}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							渠道id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="channelId" name="channelId" 
							   value="${sendRecordPage.channelId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							计费业务id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="billingBusinessId" name="billingBusinessId" 
							   value="${sendRecordPage.billingBusinessId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发送日期:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sendDate" name="sendDate" 
							   value="${sendRecordPage.sendDate}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>