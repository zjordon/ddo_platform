<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript" charset="utf-8">
	
	function blackListListExportXls() {
		JeecgExcelExport("blackListController.do?exportXls","blackListList");
	}
	function blackListListExportXlsByT() {
		JeecgExcelExport("blackListController.do?exportXlsByT","blackListList");
	}
	function blackListListImportXls() {
		openuploadwin('Excel导入', 'blackListController.do?upload', "blackListList");
	}
	
	function blackListListImportTxt() {
		openuploadwin('Excel导入', 'blackListController.do?upload', "blackListList");
	}
	
</script>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="blackListList" title="黑名单管理" actionUrl="blackListController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="手机号码" field="msisdn" query="true" autocomplete="true"></t:dgCol>
   <t:dgCol title="状态" field="state"  dictionary="state"></t:dgCol>
   <t:dgCol title="创建时间" field="createDate" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="blackListController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="blackListController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="blackListController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="blackListController.do?addorupdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入Txt" icon="icon-add" onclick="blackListListImportTxt()"></t:dgToolBar>
   <%-- <t:dgToolBar title="导出Excel" icon="icon-search" onclick="blackListListExportXls();"></t:dgToolBar>
   <t:dgToolBar title="导入Excel" icon="icon-search" onclick="blackListListImportXls()"></t:dgToolBar>
   <t:dgToolBar title="下载导入模板" icon="icon-search" onclick="blackListListExportXlsByT()"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>