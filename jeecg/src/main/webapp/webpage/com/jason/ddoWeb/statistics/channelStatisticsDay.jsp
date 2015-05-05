<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>分渠道统计</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="channelStatisticsDayController.do?save">
			<input id="id" name="id" type="hidden" value="${channelStatisticsDayPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							统计日期:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sumDate" name="sumDate" 
							   value="${channelStatisticsDayPage.sumDate}" datatype="n">
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
							   value="${channelStatisticsDayPage.channelId}" datatype="*">
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
							   value="${channelStatisticsDayPage.msisdnNum}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							计费金额:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sumAmount" name="sumAmount" 
							   value="${channelStatisticsDayPage.sumAmount}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							短信条数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="msgNum" name="msgNum" 
							   value="${channelStatisticsDayPage.msgNum}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发送成功数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sendSuccessNum" name="sendSuccessNum" 
							   value="${channelStatisticsDayPage.sendSuccessNum}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发送失败数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sendFailNum" name="sendFailNum" 
							   value="${channelStatisticsDayPage.sendFailNum}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							计费成功数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="billSuccessNum" name="billSuccessNum" 
							   value="${channelStatisticsDayPage.billSuccessNum}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							计费失败数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="billFailNum" name="billFailNum" 
							   value="${channelStatisticsDayPage.billFailNum}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>