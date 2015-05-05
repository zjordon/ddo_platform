<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
  <title>监控ddo收发引擎</title>
  <script type="text/javascript" src="plug-in/jquery/jquery-1.8.0.min.js"></SCRIPT>
  <STYLE type=text/css>
	.status {
		text-align:center;
		width:auto;
		height:400px;
	}
	.status #status-msg {
		width:auto;
		height:50%;
	}
	.control {
		text-align:center;
		width:auto;
		height:100px;
	}
</STYLE>
 </head>
<body>
<div class="status">
	<div>收发引擎当前运行状态</div>
	<div id="status-msg"></div>
	<div><input id="get-status" type="button" value="获取状态"></div>
</div>
<div class="control">
	<div><input id="control-ddomsg" type="button" value="停止/启动收发引擎"></div>
</div>
</body>
<script type="text/javascript">
$(function() {
	$("#get-status").click(function(){
		$.getJSON("monitorController.do?ddoMsgStatus", function(data){
				  $("#status-msg").html(data.msg);
				});
	});
	$("#control-ddomsg").click(function(){
		$.ajax({ url: "monitorController.do?controlDdoMsg", success: function(){
		    alert("操作成功");
		    $.getJSON("monitorController.do?ddoMsgStatus", function(data){
				  $("#status-msg").html(data.msg);
				});
		}});
	});
});
</script>
</html>