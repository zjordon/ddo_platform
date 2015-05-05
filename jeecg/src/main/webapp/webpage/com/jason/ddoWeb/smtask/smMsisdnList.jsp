<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>号码清单</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="smMsisdnListController.do?save">
			<input id="id" name="id" type="hidden" value="${smMsisdnListPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							手机号码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="msisdn" name="msisdn" ignore="ignore"
							   value="${smMsisdnListPage.msisdn}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							任务id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="smTaskId" name="smTaskId" 
							   value="${smMsisdnListPage.smTaskId}" datatype="*">
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
						<input class="inputxt" id="smRequestId" name="smRequestId" ignore="ignore"
							   value="${smMsisdnListPage.smRequestId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>