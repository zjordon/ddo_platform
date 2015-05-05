<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="channelBusinessList" title="渠道计费点" actionUrl="channelBusinessController.do?datagrid&channelId=${channelId}" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="产品指令" field="instruct" ></t:dgCol>
   <t:dgCol title="状态" field="state" ></t:dgCol>
   <t:dgCol title="关停状态" field="closeState" ></t:dgCol>
   <t:dgCol title="计费业务id" field="billBusinessId" ></t:dgCol>
   <t:dgCol title="渠道id" field="channelId" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="channelBusinessController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="channelBusinessController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="channelBusinessController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="channelBusinessController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>