<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="limitUseLogList" title="渠道限额使用流水" actionUrl="limitUseLogController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="本次使用的金额" field="useAmount" ></t:dgCol>
   <t:dgCol title="使用后剩余的日金额" field="remainDayAmount" ></t:dgCol>
   <t:dgCol title="使用后剩余的月金额" field="remainMonthAmount" ></t:dgCol>
   <t:dgCol title="请求id" field="requestId" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="limitUseLogController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="limitUseLogController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="limitUseLogController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="limitUseLogController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>