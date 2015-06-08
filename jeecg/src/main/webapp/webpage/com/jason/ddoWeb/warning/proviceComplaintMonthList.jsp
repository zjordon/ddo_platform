<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="proviceComplaintMonthList" title="分省预警" actionUrl="proviceComplaintMonthController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="省份编码" field="proviceCode" ></t:dgCol>
   <t:dgCol title="统计月份" field="sumMonth" ></t:dgCol>
   <t:dgCol title="投诉数" field="num" ></t:dgCol>
   <t:dgCol title="投诉数阀值" field="numThreshold" ></t:dgCol>
   <t:dgCol title="万投比阀值" field="ratioThreshold" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="proviceComplaintMonthController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="proviceComplaintMonthController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="proviceComplaintMonthController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="proviceComplaintMonthController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>