<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="complaintRecordList" title="投诉记录" actionUrl="complaintRecordController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="投诉日期" field="complaintDate" ></t:dgCol>
   <t:dgCol title="用户手机号" field="msisdn" ></t:dgCol>
   <t:dgCol title="省份" field="provice" ></t:dgCol>
   <t:dgCol title="地市" field="city" ></t:dgCol>
   <t:dgToolBar title="批量导入号码" icon="icon-add" url="complaintRecordController.do?upload" funname="add"></t:dgToolBar>
   <%--
    <t:dgCol title="投诉月份" field="complaintMonth" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="complaintRecordController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="complaintRecordController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="complaintRecordController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="complaintRecordController.do?addorupdate" funname="detail"></t:dgToolBar>
    --%>
  </t:datagrid>
  </div>
 </div>