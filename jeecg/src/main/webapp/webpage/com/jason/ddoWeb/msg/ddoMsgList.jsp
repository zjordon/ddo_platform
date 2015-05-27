<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" charset="utf-8">
function ddoMsgListExportXls() {
	JeecgExcelExport("ddoMsgController.do?exportXls","ddoMsgList");
}
$(function() {
    var datagrid = $("#ddoMsgListtb");
    datagrid.find("input[name='channelName']").after("模糊查询请用*号表示，如*福建*");
});
</script>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  
  <t:datagrid name="ddoMsgList" title="ddo消息" actionUrl="ddoMsgController.do?datagrid" idField="id" fit="true" queryMode="group" autoLoadData="false"
   sortName="createDate" sortOrder="desc">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <%--
   <t:dgCol title="创建时间" field="createDate" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group" extend="{class:{value:'easyui-datebox'}}"></t:dgCol>
   <t:dgCol title="创建时间" field="createDate" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group" extend="{class:{value:'Wdate'}}"></t:dgCol>
   --%>
   <t:dgCol title="创建时间" field="createDate" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="用户手机号码" field="msisdn" query="true"></t:dgCol>
   <t:dgCol title="号码归属省份" field="msisdnProvinceCode" dictionary="t_s_territory where territorylevel = 1,territorycode,territoryname" query="true"></t:dgCol>
   <t:dgCol title="号码归属地市" field="msisdnCityCode" dictionary="t_s_territory where territorylevel = 2,territorycode,territoryname"></t:dgCol>
   <t:dgCol title="价格" field="billingBusinessId" dictionary="ddo_bill_business,id,price" customFormatter="formatToDouble"></t:dgCol>
   <t:dgCol title="发送结果" field="returnMsgCode" dictionary="retMsgCode"></t:dgCol>
   <t:dgCol title="计费状态" field="billStateCode" dictionary="billState" query="true"></t:dgCol>
   <t:dgCol title="渠道" field="channelName" query="true"></t:dgCol>
    
    <%--
     <t:dgCol title="渠道" field="channelId" dictionary="ddo_channel,id,name" query="true"></t:dgCol>
     <t:dgCol title="发送结果" field="sendResult" dictionary="sendResult"></t:dgCol>
   <t:dgCol title="返回消息编码" field="returnMsgCode" ></t:dgCol>
   <t:dgCol title="事务ID" field="transationId" ></t:dgCol>
   <t:dgCol title="发送时间" field="sendTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="计费状态返回时间" field="billStateTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   
  
   <t:dgCol title="请求id" field="requestId" ></t:dgCol>
   <t:dgCol title="重发标识" field="repeatFlag" ></t:dgCol>
   <t:dgCol title="请求返回时间" field="responseTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
    --%>
    <%-- 
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="ddoMsgController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="ddoMsgController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="ddoMsgController.do?addorupdate" funname="update"></t:dgToolBar>
   --%>
   <t:dgToolBar title="查看" icon="icon-search" url="ddoMsgController.do?addorupdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导出Excel" icon="icon-search" onclick="ddoMsgListExportXls();"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
    function formatToDouble(value) {
		return parseFloat(value)/100;
	}
    $(function() {
    	$("input[name='createDate_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
		$("input[name='createDate_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
        setTimeout(init, 200);
    });
    function init() {
        //alert($('#jeecgDemoList'));
        var href = decodeURIComponent(window.location.href);
        //alert(href);
        var idx = href.indexOf('selectedParams');
        if (idx != -1) {
            idx = href.indexOf("{", idx);
            if (idx != -1) {
                var endIdx = href.indexOf("}", idx);
                if (endIdx != -1) {
                    var selectedParams = href.substring(idx, endIdx + 1);
                    var jsonParam = $.parseJSON(selectedParams);
                    $('#ddoMsgListtb').find('*').each(function() {
                        if (jsonParam[$(this).attr('name')] != undefined) {
                            if ($(this)[0].tagName == "SELECT") {
                            	//alert($(this)[0].tagName);
                                //$(this).attr("value", "0");
                                $(this).val(jsonParam[$(this).attr('name')]);
                            } else if ($(this)[0].tagName == "INPUT") {
                            	//alert(jsonParam[$(this).attr('name')]);
                                $(this).val(jsonParam[$(this).attr('name')]);
                                //$(this).val("2015-04-01");
                            }
                             
                        }
                    });
                }
                 
            }
             
        }
        ddoMsgListsearch();
    }
</script>