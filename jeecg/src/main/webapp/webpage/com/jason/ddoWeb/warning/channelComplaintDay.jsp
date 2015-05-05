<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>渠道投诉数</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" layout="table" action="channelComplaintDayController.do?save">
			<input id="id" name="id" type="hidden" value="${channelComplaintDayPage.id }">
			<input id="channelId" name="channelId" type="hidden" value="${channelComplaintDayPage.channelId }">
			<input id="sumDate" name="sumDate" type="hidden" value="${channelComplaintDayPage.sumDate }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							投诉数:
						</label>
					</td>
					<td class="value">
					    <c:choose>
						<c:when test="${channelComplaintDayPage.num!=null}">
							<input class="inputxt" id="num" name="num" ignore="ignore"
							   value="${channelComplaintDayPage.num}" datatype="n">
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