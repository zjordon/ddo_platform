<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>分省预警</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="proviceComplaintMonthController.do?save">
			<input id="id" name="id" type="hidden" value="${proviceComplaintMonthPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							省份编码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="proviceCode" name="proviceCode" 
							   value="${proviceComplaintMonthPage.proviceCode}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							统计月份:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sumMonth" name="sumMonth" ignore="ignore"
							   value="${proviceComplaintMonthPage.sumMonth}" datatype="n">
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
							   value="${proviceComplaintMonthPage.num}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							投诉数阀值:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="numThreshold" name="numThreshold" ignore="ignore"
							   value="${proviceComplaintMonthPage.numThreshold}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							万投比阀值:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="ratioThreshold" name="ratioThreshold" ignore="ignore"
							   value="${proviceComplaintMonthPage.ratioThreshold}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>