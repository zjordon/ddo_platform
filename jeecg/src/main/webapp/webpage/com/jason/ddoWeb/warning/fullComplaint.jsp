<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>全量预警</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="fullComplaintController.do?save">
			<input id="id" name="id" type="hidden" value="${fullComplaintPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							计费金额:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sumAmount" name="sumAmount" 
							   value="${fullComplaintPage.sumAmount}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							统计日期:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sumDate" name="sumDate" 
							   value="${fullComplaintPage.sumDate}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="msisdnNum" name="msisdnNum" 
							   value="${fullComplaintPage.msisdnNum}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							num:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="num" name="num" ignore="ignore"
							   value="${fullComplaintPage.num}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							唯一标识:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="fullComplaintId" name="fullComplaintId" ignore="ignore"
							   value="${fullComplaintPage.fullComplaintId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							scale:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="scale" name="scale" ignore="ignore"
							   value="${fullComplaintPage.scale}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>