<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>编辑万投比管控阀值</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" layout="table" action="channelComplaintMonthController.do?save">
			<input id="id" name="id" type="hidden" value="${channelComplaintMonthPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							万投比管控阀值:
						</label>
					</td>
					<td class="value">
					    <c:choose>
						<c:when test="${channelComplaintMonthPage.ratioThreshold!=null}">
							<input class="inputxt" id="ratioThreshold" name="ratioThreshold" ignore="ignore"
							   value="${channelComplaintMonthPage.numThreshold}" datatype="d">
						</c:when>
						<c:otherwise>
							<input class="inputxt" id="ratioThreshold" name="ratioThreshold" ignore="ignore"
							   value="0" datatype="d">
						</c:otherwise>
						</c:choose>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>