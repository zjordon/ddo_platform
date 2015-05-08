<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>全量统计</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="fullStatisticsMonthController.do?save">
			<input id="id" name="id" type="hidden" value="${fullStatisticsMonthPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							sumMonth:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sumMonth" name="sumMonth" 
							   value="${fullStatisticsMonthPage.sumMonth}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							msisdnNum:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="msisdnNum" name="msisdnNum" 
							   value="${fullStatisticsMonthPage.msisdnNum}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							sumAmount:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sumAmount" name="sumAmount" 
							   value="${fullStatisticsMonthPage.sumAmount}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							msgNum:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="msgNum" name="msgNum" 
							   value="${fullStatisticsMonthPage.msgNum}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							sendSuccessNum:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sendSuccessNum" name="sendSuccessNum" 
							   value="${fullStatisticsMonthPage.sendSuccessNum}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							sendFailNum:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sendFailNum" name="sendFailNum" 
							   value="${fullStatisticsMonthPage.sendFailNum}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							billSuccessNum:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="billSuccessNum" name="billSuccessNum" 
							   value="${fullStatisticsMonthPage.billSuccessNum}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							billFailNum:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="billFailNum" name="billFailNum" ignore="ignore"
							   value="${fullStatisticsMonthPage.billFailNum}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>