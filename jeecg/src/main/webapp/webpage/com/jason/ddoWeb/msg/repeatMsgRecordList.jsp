<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="repeatMsgRecordList" title="消息重发记录" actionUrl="repeatMsgRecordController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="返回消息编码" field="returnMsgCode" ></t:dgCol>
   <t:dgCol title="请求id" field="ddoMsgId" ></t:dgCol>
   <t:dgCol title="请求返回时间" field="responseTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="repeatMsgRecordController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="repeatMsgRecordController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="repeatMsgRecordController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="repeatMsgRecordController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>