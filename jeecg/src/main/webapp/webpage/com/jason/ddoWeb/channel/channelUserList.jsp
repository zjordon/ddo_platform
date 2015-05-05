<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<%--
<script>
    $(function() {
        var datagrid = $("#channelUserListtb");
        datagrid.find("div[name='searchColums']").append($("#tempSearchColums div[name='searchColums']").html());
    });
</script>
<div id="tempSearchColums" style="display: none">
    <div name="searchColums">
        <span style="display:-moz-inline-box;display:inline-block;">
            <span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;" title="所属渠道">
                                           所属渠道：
            </span>
            <t:comboBox url="channelController.do?combox" name="channel.id" text="name" id="id"></t:comboBox>
        </span>
    </div>
</div>
--%>
  <t:datagrid name="channelUserList" title="渠道用户" actionUrl="channelUserController.do?datagrid" fit="true" fitColumns="true" idField="id" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="用户名" field="username" ></t:dgCol>
   <t:dgCol title="手机号" field="msisdn" query="true"></t:dgCol>
   <t:dgCol title="用户状态" field="state" dictionary="state"></t:dgCol>
   <t:dgCol title="所属渠道" field="channelId" query="true" queryMode="single"  dictionary="ddo_channel,id,name"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="channelUserController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="channelUserController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="channelUserController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="channelUserController.do?addorupdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="common.password.reset" icon="icon-edit" url="channelUserController.do?changepasswordforuser" funname="update"></t:dgToolBar>
  </t:datagrid>