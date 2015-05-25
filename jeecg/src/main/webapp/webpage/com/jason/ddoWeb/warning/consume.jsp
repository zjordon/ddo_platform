<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>扣费限制配置</title>
<t:base type="jquery,tools,toptip"></t:base>
<link rel="stylesheet" href="plug-in/Validform/css/style.css" type="text/css"/>
<link rel="stylesheet" href="plug-in/Validform/css/tablefrom.css" type="text/css"/>
<script type="text/javascript" src="plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js"></script>
<script type="text/javascript" src="plug-in/Validform/js/Validform_Datatype_zh-cn.js"></script>
<script type="text/javascript" src="plug-in/Validform/js/datatype_zh-cn.js"></script>
<SCRIPT type="text/javascript" src="plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js"></SCRIPT>

<link rel="stylesheet" href="plug-in/jmobile/css/style.css">
<link rel="stylesheet" href="plug-in/jmobile/css/jquery-ui.css">
<script type="text/javascript" src="plug-in/jmobile/js/jquery-ui.js"></SCRIPT>
<STYLE type=text/css>
	.btn_wrap BUTTON {
		WIDTH: 30%
	}
</STYLE>
</head>
<body style="overflow-y: hidden" scroll="no">
<form id="formobj" action="consumeController.do?save" name="formobj" method="post">
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable" id = "formtableId">
		<tr>
			<td align="right" width="15%" nowrap><label class="Validform_label">每月最多扣费金额: </label></td>
			<td class="value" width="85%">
				<input id="userName" class="inputxt" name="monthDeductionAmountLimit" value="${map.monthDeductionAmountLimit}" datatype="d">
				<span class="Validform_checktip"></span>
			</td>
		</tr>
		<tr id= "add_phnoe">
			<td align="right" nowrap><label class="Validform_label">每月最多扣费次数: </label></td>
			<td class="value"><input  class="inputxt" name="monthDeductionNumLimit" value="${map.monthDeductionNumLimit}" datatype="n"> <span class="Validform_checktip"></span></td>
		</tr>
		<tr><td colspan=2>
		    <input type="button" id="btn_sub" value="保存">
		    <input type="button" id="btn_reset" value="重置">
		</td></tr>
	</table>
</form>
<script type="text/javascript">
			$(function() {
				$("#formobj")
						.Validform(
								{
									tiptype : 1,
									btnSubmit : "#btn_sub",
									btnReset : "#btn_reset",
									ajaxPost : true,
									callback : function(data) {
										
										if (data.success == true) {
											alert(data.msg);
										} else {
											if (data.responseText == '' || data.responseText == undefined) {
												alert(data.msg);
											} else {
												try {
													var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'),data.responseText.indexOf('错误信息'));
													alert(emsg);
												} catch (ex) {
													alert(data.responseText);
												}
											}
											return false;
										}
									}
								});
			});
		</script>
</body>