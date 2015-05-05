<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="repeatReportRecordList" title="重新下发状态报告记录" actionUrl="repeatReportRecordController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="重发原因" field="repeatReason" ></t:dgCol>
   <t:dgCol title="重发时间" field="repeatDate" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="下发状态报告结果编码" field="resultCode" ></t:dgCol>
   <t:dgCol title="对应的计费状态报告id" field="billReportId" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="repeatReportRecordController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="repeatReportRecordController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="repeatReportRecordController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="repeatReportRecordController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>