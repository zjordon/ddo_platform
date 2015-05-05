<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>计费结果</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="billResultRecordController.do?save">
			<input id="id" name="id" type="hidden" value="${billResultRecordPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							ddo消息id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="ddoMsgId" name="ddoMsgId" ignore="ignore"
							   value="${billResultRecordPage.ddoMsgId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							计费结果:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="billResult" name="billResult" ignore="ignore"
							   value="${billResultRecordPage.billResult}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>