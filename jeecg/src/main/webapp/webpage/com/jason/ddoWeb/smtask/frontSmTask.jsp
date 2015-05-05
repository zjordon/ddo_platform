<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>短信任务</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="frontSmTaskController.do?save">
			<input id="id" name="id" type="hidden" value="${smTaskPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							输入号码:
						</label>
					</td>
					<td class="value">
					    <textarea class="inputxt" rows="6" name="msisdnContent" datatype="s11-550"></textarea>
						<span class="Validform_checktip">手动输入，一行一个号码回车切换下一行，50个号码以上的请用文件导入提交。</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否去重:
						</label>
					</td>
					<td class="value">
					    <t:dictSelect field="recapture" typeGroupCode="bool" hasLabel="false" defaultVal="0" type="radio"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发送类型:
						</label>
					</td>
					<td class="value">
					    <t:dictSelect field="sendType" typeGroupCode="sendType" hasLabel="false" defaultVal="1" type="radio"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							定时发送时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="timeToSendTime" name="timeToSendTime" ignore="ignore"
							     value="">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							渠道:
						</label>
					</td>
					<td class="value">
					    <t:comboBox url="frontChannelController.do?combox" name="channelId" text="name" id="id" multiple="false" selectedValue="${smTaskPage.channelId}"></t:comboBox>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							计费业务:
						</label>
					</td>
					<td class="value">
					    <t:comboBox url="frontBillBusinessController.do?combox" name="billBusinessId" text="price" id="id" multiple="false" selectedValue="${smTaskPage.billBusinessId}"></t:comboBox>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>