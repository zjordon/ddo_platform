<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>投诉记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="complaintRecordController.do?save">
			<input id="id" name="id" type="hidden" value="${complaintRecordPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							投诉日期:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="complaintDate" name="complaintDate" ignore="ignore"
							   value="${complaintRecordPage.complaintDate}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							投诉月份:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="complaintMonth" name="complaintMonth" 
							   value="${complaintRecordPage.complaintMonth}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户手机号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="msisdn" name="msisdn" ignore="ignore"
							   value="${complaintRecordPage.msisdn}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							省份:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="provice" name="provice" ignore="ignore"
							   value="${complaintRecordPage.provice}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							地市:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="city" name="city" ignore="ignore"
							   value="${complaintRecordPage.city}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>