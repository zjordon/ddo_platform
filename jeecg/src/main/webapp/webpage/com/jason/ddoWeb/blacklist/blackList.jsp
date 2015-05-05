<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>黑名单管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="blackListController.do?save">
			<input id="id" name="id" type="hidden" value="${blackListPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							手机号码:
						</label>
					</td>
					<td class="value">
					    <c:choose>
						<c:when test="${blackListPage.id!=null}">
							${blackListPage.msisdn }
						</c:when>
						<c:otherwise>
							<input class="inputxt" id="msisdn" name="msisdn" 
							   value="${blackListPage.msisdn}" datatype="m" validType="ddo_black_list,msisdn">
						<span class="Validform_checktip"></span>
						</c:otherwise>
						</c:choose>
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
						<c:when test="${blackListPage.id!=null}">
							<t:dictSelect field="state" typeGroupCode="state" hasLabel="false" defaultVal="${blackListPage.state}" type="radio"></t:dictSelect>
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