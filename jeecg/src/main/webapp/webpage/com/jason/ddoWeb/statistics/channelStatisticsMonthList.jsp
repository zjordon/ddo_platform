<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<t:datagrid name="channelStatisticsMonthList" title="分渠道统计"
			actionUrl="channelStatisticsMonthController.do?datagrid" idField="id"
			fit="true" queryMode="group" sortName="sumMonth" sortOrder="desc"
			autoLoadData="false">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="统计月份" field="sumMonth" query="true" queryMode="group"></t:dgCol>
			<t:dgCol title="渠道" field="channelId"
				dictionary="ddo_channel,id,name" query="true"></t:dgCol>
			<t:dgCol title="用户数" field="msisdnNum"></t:dgCol>
			<t:dgCol title="计费金额" field="sumAmount"></t:dgCol>
			<t:dgCol title="短信条数" field="msgNum"></t:dgCol>
			<t:dgCol title="发送成功数" field="sendSuccessNum"></t:dgCol>
			<t:dgCol title="发送失败数" field="sendFailNum"></t:dgCol>
			<t:dgCol title="计费成功数" field="billSuccessNum"></t:dgCol>
			<t:dgCol title="计费失败数" field="billFailNum"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
			<t:dgFunOpt funname="viewDetail(channelId, sumMonth)" title="查看明细"></t:dgFunOpt>
			<%--
   <t:dgDelOpt title="删除" url="channelStatisticsMonthController.do?del&id={id}" />
  
   <t:dgToolBar title="录入" icon="icon-add" url="channelStatisticsMonthController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="channelStatisticsMonthController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="channelStatisticsMonthController.do?addorupdate" funname="detail"></t:dgToolBar>
    --%>
			<t:dgToolBar title="导出Excel" icon="icon-search"
				onclick="csdListExportXls();"></t:dgToolBar>
		</t:datagrid>
	</div>
</div>
<script type="text/javascript" charset="utf-8">
	function csdListExportXls() {
		JeecgExcelExport("channelStatisticsMonthController.do?exportXls",
				"channelStatisticsMonthList");
	}

	$(function() {
		$("input[name='sumMonth_begin']").attr("class", "Wdate").attr("style",
				"height:20px;width:90px;").click(function() {
			WdatePicker({
				dateFmt : 'yyyyMM'
			});
		});
		$("input[name='sumMonth_end']").attr("class", "Wdate").attr("style",
				"height:20px;width:90px;").click(function() {
			WdatePicker({
				dateFmt : 'yyyyMM'
			});
		});
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
					$('#channelStatisticsMonthListtb')
							.find('*')
							.each(
									function() {
										if (jsonParam[$(this).attr('name')] != undefined) {
											if ($(this)[0].tagName == "SELECT") {
												//alert($(this)[0].tagName);
												//$(this).attr("value", "0");
												$(this).val(
														jsonParam[$(this).attr(
																'name')]);
											} else if ($(this)[0].tagName == "INPUT") {
												//alert(jsonParam[$(this).attr('name')]);
												$(this).val(
														jsonParam[$(this).attr(
																'name')]);
												//$(this).val("2015-04-01");
											}

										}
									});
				}

			}

		}
		channelStatisticsMonthListsearch();
	}
	function viewDetail(channelId, sumMonth) {
		//alert(channelId + "," + sumMonth);
		//window.parent.addTab('流水统计','ddoMsgController.do?ddoMsg&amp;clickFunctionId=402848814ce57137014ce574685a0007','default');
		//createwindow('查看明细', 'ddoMsgController.do?ddoMsg&channelId=' + channelId + '&sumMonth=' + sumMonth);
		//把yyyymmdd格式的时间格式转化成yyyy-mm-dd的时间格式
		var year = Math.floor(sumMonth / 100);
		var month = sumMonth - year * 100;
		//var date = sumMonth - year * 10000 - month*100;
		//alert(date);
		var queryDateBegin = year + "-" + toString(month) + "-01";
		//计算查询的月份是哪一个月以确定该月的最后一天
		var lastDay = getLastDay(year, month);
		var queryDateEnd = year + "-" + toString(month) + "-" + toString(lastDay);
		//var queryDate = year + "-" + toString(month) + "-" + toString(date);
		//alert(queryDate);
		var url = "ddoMsgController.do?ddoMsg&amp;selectedParams="
				+ encodeURIComponent("{\"channelId\":\"" + channelId
						+ "\",\"createDate_begin\":\"" + queryDateBegin
						+ "\",\"createDate_end\":\"" + queryDateEnd + "\"}");
		window.parent.goToTab('流水统计', url, 'default')
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

	function getLastDay(year, month) {
		var lastDay = 31;
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			lastDay = 31;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			lastDay = 30;
		} else {
			//2月，根据是否润年计算
			if (isLeapYear(year)) {
				lastDay = 29;
			} else {
				lastDay = 28;
			}
		}
		return lastDay;
	}
	//是否是闰年
	function isLeapYear(iYear) {
		if (iYear % 4 == 0 && iYear % 100 != 0) {
			return true;
		} else {
			if (iYear % 400 == 0) {
				return true;
			} else {
				return false;
			}
		}
	}
</script>