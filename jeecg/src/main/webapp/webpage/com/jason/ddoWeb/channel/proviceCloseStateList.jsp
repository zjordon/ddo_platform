<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="proviceCloseStateList" title="省份关停状态" actionUrl="proviceCloseStateController.do?datagrid" idField="id" fit="true"
              sortName="provicePinyin" sortOrder="asc">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="省份" field="proviceCode" dictionary="t_s_territory where territorylevel = 1,territorycode,territoryname"></t:dgCol>
   <t:dgCol title="关停状态" field="closeState" replace="停用_1,启用_0" style="background:red;_1"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt funname="updateCloseState(id)" title="关停/启用"></t:dgFunOpt>
    <%--
   <t:dgCol title="省份的拼音名称" field="provicePinyin" ></t:dgCol>
   <t:dgToolBar title="录入" icon="icon-add" url="proviceCloseStateController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="proviceCloseStateController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="proviceCloseStateController.do?addorupdate" funname="detail"></t:dgToolBar>
    --%>
  </t:datagrid>
  </div>
 </div>
 <script>
 function updateCloseState(id) {
	    var url = "proviceCloseStateController.do?updateCloseState&id=" + id;
		//gridname='proviceCloseStateList';
		createdialog('关停确认 ', '确定执行关停操作吗 ?', url,'proviceCloseStateList');
 }
 </script>