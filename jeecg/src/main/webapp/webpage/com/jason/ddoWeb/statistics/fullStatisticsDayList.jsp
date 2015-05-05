<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="fullStatisticsDayList" title="全量统计" actionUrl="fullStatisticsDayController.do?datagrid" idField="id" fit="true" 
   sortName="sumDate" sortOrder="desc" autoLoadData="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="统计日期" field="sumDate" ></t:dgCol>
   <t:dgCol title="用户数" field="msisdnNum" ></t:dgCol>
   <t:dgCol title="计费金额" field="sumAmount" ></t:dgCol>
   <t:dgCol title="短信条数" field="msgNum" ></t:dgCol>
   <t:dgCol title="发送成功数" field="sendSuccessNum" ></t:dgCol>
   <t:dgCol title="发送失败数" field="sendFailNum" ></t:dgCol>
   <t:dgCol title="计费成功数" field="billSuccessNum" ></t:dgCol>
   <t:dgCol title="计费失败数" field="billFailNum" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
    <t:dgFunOpt funname="viewDetail(sumDate)" title="查看明细"></t:dgFunOpt>
   <%-- 
   <t:dgDelOpt title="删除" url="fullStatisticsDayController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="fullStatisticsDayController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="fullStatisticsDayController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="fullStatisticsDayController.do?addorupdate" funname="detail"></t:dgToolBar>
   --%>
    <t:dgToolBar title="导出Excel" icon="icon-search" onclick="fsdListExportXls();"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
    function fsdListExportXls() {
		JeecgExcelExport("fullStatisticsDayController.do?exportXls","fullStatisticsDayList");
	}
    function viewDetail(sumDate){
    	//把yyyymmdd格式的时间格式转化成yyyy-mm-dd的时间格式
    	//var year = Math.floor(sumDate/10000);
    	//var month = Math.floor(sumDate/100) - year*100;
    	//var date = sumDate - year * 10000 - month*100;
    	//alert(date);
    	//var queryDate = year + "-" + toString(month) + "-" + toString(date);
    	//alert(queryDate);
    	var url = "channelStatisticsDayController.do?channelStatisticsDay&amp;selectedParams=" + encodeURIComponent("{\"sumDate_begin\":" + sumDate + ",\"sumDate_end\":" + sumDate + "}");
       // alert(url);
    	window.parent.goToTab('分渠道统计',url,'default')
        //alert(window.parent.goToTabWithHref);
        //window.parent.goToTabWithHref('流水统计','ddoMsgController.do?ddoMsg&amp;clickFunctionId=402848814ce57137014ce574685a0007','default');
    }
    
    function toString(intNum) {
    	if (intNum < 10) {
    		return "0" + intNum;
    	} else {
    		return intNum.toString();
    	}
    }
</script>