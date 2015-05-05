<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="smReqeustList" title="短信请求" actionUrl="smReqeustController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="返回请求编号" field="responseNo" ></t:dgCol>
   <t:dgCol title="返回状态" field="responseState" ></t:dgCol>
   <t:dgCol title="请求返回时间" field="responseTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="发送时间" field="sendTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="任务id" field="smTaskId" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="smReqeustController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="smReqeustController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="smReqeustController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="smReqeustController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>