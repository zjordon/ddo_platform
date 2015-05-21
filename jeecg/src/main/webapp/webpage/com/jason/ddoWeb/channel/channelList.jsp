<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</script>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="channelList" title="渠道组管理" actionUrl="channelController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="渠道名称" field="name" ></t:dgCol>
   <t:dgCol title="渠道编号" field="no" ></t:dgCol>
   <t:dgCol title="状态" field="state" dictionary="state"></t:dgCol>
   <t:dgCol title="日限额" field="dayLimit" customFormatter="formatToDouble"></t:dgCol>
   <t:dgCol title="月限额" field="monthLimit" customFormatter="formatToDouble"></t:dgCol>
   <t:dgCol title="关停状态" field="closeState" dictionary="closeState"></t:dgCol>
   <t:dgCol title="上行地址" field="upUrl" ></t:dgCol>
   <t:dgCol title="下行地址" field="downUrl" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="channelController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="channelController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="channelController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="channelController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
	function formatToDouble(value, rec, index) {
		return parseFloat(value)/100;
	}
</script>