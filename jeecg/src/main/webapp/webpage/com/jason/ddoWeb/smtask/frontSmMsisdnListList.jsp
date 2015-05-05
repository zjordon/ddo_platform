<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="smMsisdnListList" title="号码清单" actionUrl="frontSmMsisdnListController.do?datagrid&smTaskId=${taskId}" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="手机号码" field="msisdn" query="true"></t:dgCol>
  </t:datagrid>
  </div>
 </div>