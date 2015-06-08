<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="cComplaintMonthViewList" title="渠道投诉" actionUrl="cComplaintMonthViewController.do?datagrid" idField="id" fit="true"
              sortName="sumMonth" sortOrder="desc">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="渠道id" field="channelId" hidden="true"></t:dgCol>
   <t:dgCol title="渠道名称" field="channelName" query="true"></t:dgCol>
   <t:dgCol title="唯一标识" field="channelComplaintId" hidden="true"></t:dgCol>
   <t:dgCol title="月份" field="sumMonth" ></t:dgCol>
   <t:dgCol title="总金额" field="sumAmount" ></t:dgCol>
   <t:dgCol title="用户数" field="msisdnNum" ></t:dgCol>
   <t:dgCol title="投诉数" field="num" ></t:dgCol>
   <t:dgCol title="万投比" field="scale" customFormatter="formatScale"></t:dgCol>
   <t:dgCol title="投诉数管理阀值" field="numThreshold"></t:dgCol>
   <t:dgCol title="万投比管控阀值" field="ratioThreshold"></t:dgCol>
   <t:dgCol title="状态" field="closeState" replace="启用_1,禁用_0" style="background:red;_0"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <%-- 
   <t:dgDelOpt title="删除" url="cComplaintMonthViewController.do?del&id={id}" />
  
   <t:dgToolBar title="录入" icon="icon-add" url="cComplaintMonthViewController.do?addorupdate" funname="add"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="icon-edit" url="cComplaintMonthViewController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="cComplaintMonthViewController.do?addorupdate" funname="detail"></t:dgToolBar>
   <t:dgFunOpt funname="editComplaintNum(channelComplaintId, channelId, sumMonth)" title="编辑投诉数"></t:dgFunOpt>
   --%>
   <t:dgFunOpt funname="editNumThreshold(channelComplaintId, channelId, sumMonth)" title="编辑投诉数管控阀值"></t:dgFunOpt>
   <t:dgFunOpt funname="editRatioThreshold(channelComplaintId, channelId, sumMonth)" title="编辑万投比管控阀值"></t:dgFunOpt>
   <t:dgFunOpt funname="updateChannelState(channelId)" title="关停/启用"></t:dgFunOpt>
  </t:datagrid>
  </div>
 </div>
 <script>
 function editComplaintNum(channelComplaintId, channelId, sumMonth) {
	 createwindow('编辑投诉数','channelComplaintMonthController.do?addorupdate&id=' + channelComplaintId + "&channelId=" + channelId + "&sumMonth=" + sumMonth,null,null);
 }
 
 function editNumThreshold(channelComplaintId, channelId, sumMonth) {
	 createwindow('编辑投诉数管控阀值','channelComplaintMonthController.do?editNumThreshold&id=' + channelComplaintId + "&channelId=" + channelId + "&sumMonth=" + sumMonth,null,null);
 }
 
 function editRatioThreshold(channelComplaintId, channelId, sumMonth) {
	 createwindow('编辑万投比管控阀值','channelComplaintMonthController.do?editRatioThreshold&id=' + channelComplaintId + "&channelId=" + channelId + "&sumMonth=" + sumMonth,null,null);
 }
 
 function formatScale(val) {
	 if (val) {
		 return val + "‰";
	 }
	 return val;
 }
 
 
 function updateChannelState(id) {
	    var url = "channelController.do?save&id=" + id;
		//gridname='proviceCloseStateList';
		createdialog('关停确认 ', '确定执行关停操作吗 ?', url,'cComplaintMonthViewList');
}
 
 </script>