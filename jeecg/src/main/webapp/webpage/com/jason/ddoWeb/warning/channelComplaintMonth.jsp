<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>全量投诉按月</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="channelComplaintMonthController.do?save">
			<input id="id" name="id" type="hidden" value="${channelComplaintMonthPage.id }">
			<input id="channelId" name="channelId" type="hidden" value="${channelComplaintMonthPage.channelId }">
			<input id="sumMonth" name="sumMonth" type="hidden" value="${channelComplaintMonthPage.sumMonth }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							num:
						</label>
					</td>
					<td class="value">
					    <c:choose>
						<c:when test="${channelComplaintMonthPage.num!=null}">
							<input class="inputxt" id="num" name="num" ignore="ignore"
							   value="${channelComplaintMonthPage.num}" datatype="n">
						</c:when>
						<c:otherwise>
							<input class="inputxt" id="num" name="num" ignore="ignore"
							   value="0" datatype="n">
						</c:otherwise>
						</c:choose>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>