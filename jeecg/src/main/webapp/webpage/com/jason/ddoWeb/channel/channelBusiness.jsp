<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>渠道计费点</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="channelBusinessController.do?save">
			<input id="id" name="id" type="hidden" value="${channelBusinessPage.id }">
			<input type="hidden" name="channelId" value="${channel.id}">
			<input type="hidden" name="closeState" value="0">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			    <tr>
					<td align="right">
						<label class="Validform_label">
							渠道:
						</label>
					</td>
					<td class="value">
						${channel.name}
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							计费业务:
						</label>
					</td>
					<td class="value">
						<t:comboBox url="billBusinessController.do?combox" name="billBusiness.id" text="code" id="id" multiple="false" selectedValue="${channelBusinessPage.billBusiness.id}"></t:comboBox>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							短信指令:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="instruct" name="instruct" 
							   value="${channelBusinessPage.instruct}" datatype="s0-24" validType="ddo_channel_business,instruct,id">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
						 <c:choose>
						<c:when test="${channelUserPage.id!=null}">
							<t:dictSelect field="state" typeGroupCode="state" hasLabel="false" defaultVal="${channelUserPage.state}" type="radio"></t:dictSelect>
						</c:when>
						<c:otherwise>
							<t:dictSelect field="state" typeGroupCode="state" hasLabel="false" defaultVal="1" type="radio"></t:dictSelect>
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>