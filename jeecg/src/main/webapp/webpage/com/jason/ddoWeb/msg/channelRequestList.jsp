<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="channelRequestList" title="短信任务" actionUrl="channelRequestController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="用户名" field="username" ></t:dgCol>
   <t:dgCol title="密码" field="password" ></t:dgCol>
   <t:dgCol title="发送指令" field="content" ></t:dgCol>
   <t:dgCol title="产品id" field="productId" ></t:dgCol>
   <t:dgCol title="定时时间" field="dstime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="请求时间" field="requestTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="状态" field="state" ></t:dgCol>
   <t:dgCol title="返回状态" field="returnState" ></t:dgCol>
   <t:dgCol title="开始处理时间" field="beginTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="处理完成时间" field="endTime" ></t:dgCol>
   <t:dgCol title="渠道id" field="channelId" ></t:dgCol>
   <t:dgCol title="来源类型" field="sourceType" ></t:dgCol>
   <t:dgCol title="处理结果" field="processResult" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="channelRequestController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="channelRequestController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="channelRequestController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="channelRequestController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>