<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>事件</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="eventController.do?save">
			<input id="id" name="id" type="hidden" value="${eventPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							事件ID:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="eventId" name="eventId" ignore="ignore"
							   value="${eventPage.eventId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							参数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="param" name="param" ignore="ignore"
							   value="${eventPage.param}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							开始处理时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="beginTime" name="beginTime" ignore="ignore"
							     value="<fmt:formatDate value='${eventPage.beginTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							处理结束时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="endTime" name="endTime" ignore="ignore"
							     value="<fmt:formatDate value='${eventPage.endTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							处理结果:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="processResult" name="processResult" ignore="ignore"
							   value="${eventPage.processResult}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							失败原因:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="failMsg" name="failMsg" ignore="ignore"
							   value="${eventPage.failMsg}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>