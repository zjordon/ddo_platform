<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>全量预警投诉</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="fullComplaintDayController.do?save">
			<input id="id" name="id" type="hidden" value="${fullComplaintDayPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							统计日期:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sumDate" name="sumDate" ignore="ignore"
							   value="${fullComplaintDayPage.sumDate}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							投诉数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="num" name="num" ignore="ignore"
							   value="${fullComplaintDayPage.num}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>