<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>渠道组管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="channelController.do?save">
			<input id="id" name="id" type="hidden" value="${channelPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							渠道名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" 
							   value="${channelPage.name}" datatype="s0-32" validType="ddo_channel,name,id">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							渠道编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="no" name="no" 
							   value="${channelPage.no}" datatype="n" validType="ddo_channel,no,id">
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
						<c:when test="${channelPage.id!=null}">
							<t:dictSelect field="state" typeGroupCode="state" hasLabel="false" defaultVal="${channelPage.state}" type="radio"></t:dictSelect>
						</c:when>
						<c:otherwise>
							<t:dictSelect field="state" typeGroupCode="state" hasLabel="false" defaultVal="1" type="radio"></t:dictSelect>
						</c:otherwise>
						</c:choose>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							日限额(元):
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="dayLimitDouble" name="dayLimitDouble" 
							   value="${channelPage.dayLimit/100}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							月限额(元):
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="monthLimitDouble" name="monthLimitDouble" 
							   value="${channelPage.monthLimit/100}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							关停状态:
						</label>
					</td>
					<td class="value">
					     <c:choose>
						<c:when test="${channelPage.id!=null}">
							 <t:dictSelect field="closeState" typeGroupCode="closeState" hasLabel="false" defaultVal="${channelPage.closeState}" type="radio"></t:dictSelect>
						</c:when>
						<c:otherwise>
							 <t:dictSelect field="closeState" typeGroupCode="closeState" hasLabel="false" defaultVal="0" type="radio"></t:dictSelect>
						</c:otherwise>
						</c:choose>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							上行地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="upUrl" name="upUrl" ignore="ignore"
							   value="${channelPage.upUrl}" datatype="url">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							下行地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="downUrl" name="downUrl" ignore="ignore"
							   value="${channelPage.downUrl}" datatype="url">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							对应ddo收发引擎接收地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="postUrl" name="postUrl" ignore="ignore"
							   value="${channelPage.postUrl}" datatype="*10-128">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>