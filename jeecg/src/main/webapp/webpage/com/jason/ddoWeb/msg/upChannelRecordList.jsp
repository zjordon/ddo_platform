<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="upChannelRecordList" title="上行渠道记录" actionUrl="upChannelRecordController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="上行结果编码" field="resultCode" ></t:dgCol>
   <t:dgCol title="上行处理结果" field="processResult" ></t:dgCol>
   <t:dgCol title="消息id" field="ddoMsgId" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="upChannelRecordController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="upChannelRecordController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="upChannelRecordController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="upChannelRecordController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>