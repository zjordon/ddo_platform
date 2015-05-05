<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>渠道用户</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="channelUserController.do?save">
			<input id="id" name="id" type="hidden" value="${channelUserPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户名:
						</label>
					</td>
					<td class="value">
					<c:choose>
					<c:when test="${channelUserPage.id!=null}">
						${channelUserPage.username }
					</c:when>
					<c:otherwise>
						<input class="inputxt" id="username" name="username" 
							   value="" datatype="s2-10" validType="ddo_channel_user,username,id">
						<span class="Validform_checktip"><t:mutiLang langKey="username.rang2to10"/></span>
					</c:otherwise>
					</c:choose>
					</td>
				</tr>
				<c:if test="${channelUserPage.id==null }">
				<tr>
					<td align="right">
						<label class="Validform_label">
							密码:
						</label>
					</td>
					<td class="value">
						<input type="password" class="inputxt" id="password" value="" name="password" plugin="passwordStrength" datatype="*6-18" errormsg="">
						<span class="passwordStrength" style="display: none;">
	                        <span><t:mutiLang langKey="common.weak"/></span>
	                        <span><t:mutiLang langKey="common.middle"/></span>
	                        <span class="last"><t:mutiLang langKey="common.strong"/></span>
                    	</span>
						<span class="Validform_checktip"><t:mutiLang langKey="password.rang6to18"/></span>
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.repeat.password"/>: </label></td>
					<td class="value">
	                    <input id="repassword" class="inputxt" type="password" value="" recheck="password" datatype="*6-18" errormsg="两次输入的密码不一致！">
	                    <span class="Validform_checktip"><t:mutiLang langKey="common.repeat.password"/></span>
	                </td>
				</tr>
				</c:if>
				<tr>
					<td align="right">
						<label class="Validform_label">
							手机号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="msisdn" name="msisdn" ignore="ignore"
							   value="${channelUserPage.msisdn}" datatype="m">
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
					     
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							所属渠道:
						</label>
					</td>
					<td class="value">
					    <t:comboBox url="channelController.do?combox" name="channelId" text="name" id="id" multiple="false" selectedValue="${channelUserPage.channelId}"></t:comboBox>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>