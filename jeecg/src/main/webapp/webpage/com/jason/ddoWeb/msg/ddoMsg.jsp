<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>ddo消息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ddoMsgController.do?save">
			<input id="id" name="id" type="hidden" value="${ddoMsgPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户手机号码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="msisdn" name="msisdn" 
							   value="${ddoMsgPage.msisdn}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							计费业务ID:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="billingBusinessId" name="billingBusinessId" 
							   value="${ddoMsgPage.billingBusinessId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							返回消息编码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="returnMsgCode" name="returnMsgCode" ignore="ignore"
							   value="${ddoMsgPage.returnMsgCode}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							事务ID:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="transationId" name="transationId" ignore="ignore"
							   value="${ddoMsgPage.transationId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发送时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="sendTime" name="sendTime" ignore="ignore"
							     value="<fmt:formatDate value='${ddoMsgPage.sendTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							计费状态编码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="billStateCode" name="billStateCode" ignore="ignore"
							   value="${ddoMsgPage.billStateCode}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							计费状态返回时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="billStateTime" name="billStateTime" ignore="ignore"
							     value="<fmt:formatDate value='${ddoMsgPage.billStateTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发送结果:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sendResult" name="sendResult" 
							   value="${ddoMsgPage.sendResult}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							手机号码归属省份编码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="msisdnProvinceCode" name="msisdnProvinceCode" ignore="ignore"
							   value="${ddoMsgPage.msisdnProvinceCode}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							手机号码归属地市编码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="msisdnCityCode" name="msisdnCityCode" ignore="ignore"
							   value="${ddoMsgPage.msisdnCityCode}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							渠道ID:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="channelId" name="channelId" ignore="ignore"
							   value="${ddoMsgPage.channelId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							请求id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="requestId" name="requestId" 
							   value="${ddoMsgPage.requestId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							重发标识:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="repeatFlag" name="repeatFlag" ignore="ignore"
							   value="${ddoMsgPage.repeatFlag}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							请求返回时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="responseTime" name="responseTime" ignore="ignore"
							     value="<fmt:formatDate value='${ddoMsgPage.responseTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>