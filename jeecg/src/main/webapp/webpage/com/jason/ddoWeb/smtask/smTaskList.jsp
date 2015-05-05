<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_task_list" class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="smTaskList" title="短信任务" actionUrl="smTaskController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="号码数量" field="msisdnNum" ></t:dgCol>
   <t:dgCol title="是否去重" field="recapture" dictionary="bool"></t:dgCol>
   <t:dgCol title="发送类型" field="sendType" dictionary="sendType"></t:dgCol>
   <t:dgCol title="定时发送时间" field="timeToSendTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="状态" field="state" dictionary="taskState"></t:dgCol>
   <t:dgCol title="执行时间" field="executeTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="渠道" field="channelId" dictionary="ddo_channel, id, name"></t:dgCol>
   <t:dgCol title="计费业务" field="billBusinessId" dictionary="ddo_bill_business, id, price" customFormatter="formatToDouble"></t:dgCol>
   <t:dgCol title="失败信息" field="failMsg" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <%--<t:dgDelOpt title="删除" url="smTaskController.do?del&id={id}" /> --%>
   <t:dgFunOpt funname="queryMsisdnList(id)" title="号码清单"></t:dgFunOpt>
   <t:dgToolBar title="手工输入号码" icon="icon-add" url="smTaskController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="批量导入号码" icon="icon-add" url="smTaskController.do?upload" funname="add"></t:dgToolBar>
   <%--<t:dgToolBar title="编辑" icon="icon-edit" url="smTaskController.do?addorupdate" funname="update"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="查看" icon="icon-search" url="smTaskController.do?addorupdate" funname="detail"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
<div data-options="region:'east',
	title:'mytitle',
	collapsed:true,
	split:true,
	border:false,
	onExpand : function(){
		li_east = 1;
	},
	onCollapse : function() {
	    li_east = 0;
	}"
     style="width: 400px; overflow: hidden;" id="eastPanel">
    <div class="easyui-panel" style="padding: 1px;" fit="true" border="false" id="userListpanel"></div>
</div>

<script type="text/javascript">
    $(function() {
        var li_east = 0;
    });

    function queryMsisdnList(taskId){
        var title = '任务编号: ' + taskId;
        if(li_east == 0){
            $('#main_task_list').layout('expand','east');
        }
        $('#main_task_list').layout('panel','east').panel('setTitle', title);
        $('#userListpanel').panel("refresh", "smMsisdnListController.do?smMsisdnList&taskId=" + taskId);
    }
    function loadSuccess() {
        $('#main_task_list').layout('panel','east').panel('setTitle', "");
        $('#main_task_list').layout('collapse','east');
        $('#userListpanel').empty();
    }
    function formatToDouble(value) {
		return parseFloat(value)/100;
	}
</script>