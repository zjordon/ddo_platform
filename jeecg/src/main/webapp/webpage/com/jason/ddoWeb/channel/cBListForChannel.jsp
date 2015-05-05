<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="channelBusinessList" title="渠道计费点" actionUrl="channelBusinessController.do?datagrid&channelId=${channelId}" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="渠道计费点" field="billBusiness.channelBillCode"></t:dgCol>
   <t:dgCol title="短信指令" field="instruct" ></t:dgCol>
   <t:dgCol title="动漫计费点" field="billBusiness.code"></t:dgCol>
   <t:dgCol title="状态" field="state" dictionary="state"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="channelBusinessController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="channelBusinessController.do?addorupdate&channelId=${channelId}" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="channelBusinessController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="channelBusinessController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>