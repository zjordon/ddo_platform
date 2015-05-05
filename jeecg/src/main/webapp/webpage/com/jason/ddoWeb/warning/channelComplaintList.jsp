<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="channelComplaintList" title="渠道投诉" actionUrl="channelComplaintController.do?datagrid" idField="id" fit="true"
              sortName="sumDate" sortOrder="desc">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="渠道名称" field="channelId" dictionary="ddo_channel, id, name"></t:dgCol>
   <t:dgCol title="日期" field="sumDate" ></t:dgCol>
   <t:dgCol title="总金额" field="sumAmount" ></t:dgCol>
   <t:dgCol title="用户数" field="msisdnNum" ></t:dgCol>
   <t:dgCol title="投诉数" field="num" ></t:dgCol>
   <t:dgCol title="唯一标识" field="channelComplaintId" hidden="true"></t:dgCol>
   <t:dgCol title="万投比" field="scale" customFormatter="formatScale"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <%-- 
   <t:dgDelOpt title="删除" url="channelComplaintController.do?del&id={id}" />
  
   <t:dgToolBar title="录入" icon="icon-add" url="channelComplaintController.do?addorupdate" funname="add"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="icon-edit" url="channelComplaintController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="channelComplaintController.do?addorupdate" funname="detail"></t:dgToolBar>
   --%>
   <t:dgFunOpt funname="editComplaintNum(channelComplaintId, channelId, sumDate)" title="编辑投诉数"></t:dgFunOpt>
  </t:datagrid>
  </div>
 </div>
 <script>
 function editComplaintNum(channelComplaintId, channelId, sumDate) {
	 createwindow('编辑投诉数','channelComplaintDayController.do?addorupdate&id=' + channelComplaintId + "&channelId=" + channelId + "&sumDate=" + sumDate,null,null);
 }
 
 function formatScale(val) {
	 if (val) {
		 return val + "‰";
	 }
	 return val;
 }
 
 </script>