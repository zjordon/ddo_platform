<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_channel_list" class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="channelList" title="渠道组管理" actionUrl="channelController.do?datagrid" idField="id" fit="true"
              treegrid="false" pagination="false" onLoadSuccess="loadSuccess">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="渠道名称" field="name" ></t:dgCol>
   <t:dgCol title="渠道编号" field="no" ></t:dgCol>
   <t:dgCol title="状态" field="state" dictionary="state"></t:dgCol>
   <t:dgCol title="关停状态" field="closeState" dictionary="closeState"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt funname="queryChannelBusiness(id,name)" title="计费点监控"></t:dgFunOpt>
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
     style="width: 800px; overflow: hidden;" id="eastPanel">
    <div class="easyui-panel" style="padding: 1px;" fit="true" border="false" id="userListpanel"></div>
</div>

<script type="text/javascript">
    $(function() {
        var li_east = 0;
    });

    function queryChannelBusiness(channelId, channelName){
        var title = '渠道名称: ' + channelName;
        if(li_east == 0){
            $('#main_channel_list').layout('expand','east');
        }
        $('#main_channel_list').layout('panel','east').panel('setTitle', title);
        $('#userListpanel').panel("refresh", "channelBusinessController.do?cBListForChannel&channelId=" + channelId);
    }
    function loadSuccess() {
        $('#main_channel_list').layout('panel','east').panel('setTitle', "");
        $('#main_channel_list').layout('collapse','east');
        $('#userListpanel').empty();
    }
</script>