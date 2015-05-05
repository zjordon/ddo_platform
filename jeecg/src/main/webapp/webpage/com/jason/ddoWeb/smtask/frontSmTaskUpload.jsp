<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>短信任务</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  $(function(){
    //查看模式情况下,删除和上传附件功能禁止使用
    if(location.href.indexOf("load=detail")!=-1){
        $(".jeecgDetail").hide();
    }
   });
 
      function uploadFile(data){
          $("#taskId").val(data.obj.id);
          if($(".uploadify-queue-item").length>0){
              upload();
          }else{
              frameElement.api.opener.reloadTable();
              frameElement.api.close();
          }
      }
       
      function close(){
          frameElement.api.close();
      }
  </script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" layout="table" action="frontSmTaskController.do?save" callback="@Override uploadFile">
  	<input type="hidden" id="taskId" name="taskId" value=""/>
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							号码文件:
						</label>
					</td>
					<td class="value">
						 <t:upload name="fiels" id="file_upload" extend="txt" buttonText="添加文件" multi="false" formData="taskId" uploader="frontSmTaskController.do?importTxt">
            </t:upload>
            <div class="form" id="filediv"></div>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否去重:
						</label>
					</td>
					<td class="value">
					    <t:dictSelect field="recapture" typeGroupCode="bool" hasLabel="false" defaultVal="0" type="radio"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发送类型:
						</label>
					</td>
					<td class="value">
					    <t:dictSelect field="sendType" typeGroupCode="sendType" hasLabel="false" defaultVal="1" type="radio"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							定时发送时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="timeToSendTime" name="timeToSendTime" ignore="ignore"
							     value="">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							渠道:
						</label>
					</td>
					<td class="value">
					    <t:comboBox url="frontChannelController.do?combox" name="channelId" text="name" id="id" multiple="false"></t:comboBox>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							计费业务:
						</label>
					</td>
					<td class="value">
					    <t:comboBox url="frontBillBusinessController.do?combox" name="billBusinessId" text="price" id="id" multiple="false"></t:comboBox>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>