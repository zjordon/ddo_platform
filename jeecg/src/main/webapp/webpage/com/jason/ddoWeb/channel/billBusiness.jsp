<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>计费业务</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="billBusinessController.do?save">
			<input id="id" name="id" type="hidden" value="${billBusinessPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							计费点名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" 
							   value="${billBusinessPage.name}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							计费价格:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="price" name="priceDouble"
							   value="${billBusinessPage.price/100}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							计费业务代码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="code" name="code" 
							   value="${billBusinessPage.code}" datatype="*" validType="ddo_bill_business,code,id">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							渠道计费业务代码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="channelBillCode" name="channelBillCode" 
							   value="${billBusinessPage.channelBillCode}" datatype="*" validType="ddo_bill_business,channel_bill_code,id">
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
						<c:when test="${billBusinessPage.id!=null}">
							<t:dictSelect field="state" typeGroupCode="state" hasLabel="false" defaultVal="${billBusinessPage.state}" type="radio"></t:dictSelect>
						</c:when>
						<c:otherwise>
							<t:dictSelect field="state" typeGroupCode="state" hasLabel="false" defaultVal="1" type="radio"></t:dictSelect>
						</c:otherwise>
						</c:choose>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>