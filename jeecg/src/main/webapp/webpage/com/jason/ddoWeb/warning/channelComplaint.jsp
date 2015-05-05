<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>渠道投诉</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="channelComplaintController.do?save">
			<input id="id" name="id" type="hidden" value="${channelComplaintPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							渠道id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="channelId" name="channelId" 
							   value="${channelComplaintPage.channelId}" datatype="*">
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
							   value="${channelComplaintPage.sumAmount}" datatype="d">
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
							   value="${channelComplaintPage.msisdnNum}" datatype="n">
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
							   value="${channelComplaintPage.num}" datatype="n">
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
						<input class="inputxt" id="channelComplaintId" name="channelComplaintId" ignore="ignore"
							   value="${channelComplaintPage.channelComplaintId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>