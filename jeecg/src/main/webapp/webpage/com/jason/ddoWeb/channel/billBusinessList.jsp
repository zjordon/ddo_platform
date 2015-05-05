<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="billBusinessList" title="计费业务" actionUrl="billBusinessController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="计费点名称" field="name" ></t:dgCol>
   <t:dgCol title="计费价格" field="price" customFormatter="formatToDouble"></t:dgCol>
   <t:dgCol title="计费业务代码" field="code" ></t:dgCol>
   <t:dgCol title="状态" field="state" dictionary="state"></t:dgCol>
   <t:dgCol title="渠道计费业务代码" field="channelBillCode" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgToolBar title="录入" icon="icon-add" url="billBusinessController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="billBusinessController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="billBusinessController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
	function formatToDouble(value, rec, index) {
		return parseFloat(value)/100;
	}
</script>