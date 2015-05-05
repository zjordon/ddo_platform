<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>省份关停状态</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="proviceCloseStateController.do?save">
			<input id="id" name="id" type="hidden" value="${proviceCloseStatePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							省份编码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="proviceCode" name="proviceCode" 
							   value="${proviceCloseStatePage.proviceCode}" datatype="*">
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
						<input class="inputxt" id="closeState" name="closeState" 
							   value="${proviceCloseStatePage.closeState}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							省份的拼音名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="provicePinyin" name="provicePinyin" 
							   value="${proviceCloseStatePage.provicePinyin}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>