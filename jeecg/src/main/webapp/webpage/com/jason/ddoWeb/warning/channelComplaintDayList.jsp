<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="channelComplaintDayList" title="渠道投诉数" actionUrl="channelComplaintDayController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="渠道id" field="channelId" ></t:dgCol>
   <t:dgCol title="统计日期" field="sumDate" ></t:dgCol>
   <t:dgCol title="投诉数" field="num" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="channelComplaintDayController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="channelComplaintDayController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="channelComplaintDayController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="channelComplaintDayController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>