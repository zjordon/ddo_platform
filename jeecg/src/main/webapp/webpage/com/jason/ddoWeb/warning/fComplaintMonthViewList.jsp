<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="fComplaintViewList" title="全量预警" actionUrl="fComplaintMonthViewController.do?datagrid" idField="id" fit="true" sortName="sumMonth" sortOrder="desc">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="计费金额" field="sumAmount" ></t:dgCol>
   <t:dgCol title="月份" field="sumMonth" ></t:dgCol>
   <t:dgCol title="用户数" field="msisdnNum" ></t:dgCol>
   <t:dgCol title="投诉数" field="num" ></t:dgCol>
   <t:dgCol title="唯一标识" field="fullComplaintId" hidden="true"></t:dgCol>
   <t:dgCol title="万投比" field="scale" customFormatter="formatScale"></t:dgCol>
   <%--
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="fComplaintMonthViewController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="fComplaintMonthViewController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="fComplaintMonthViewController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="fComplaintMonthViewController.do?addorupdate" funname="detail"></t:dgToolBar>
    --%>
  </t:datagrid>
  </div>
 </div>
 <script>
 function formatScale(val) {
	 if (val) {
		 return val + "‰";
	 }
	 return val;
 }
 
 </script>