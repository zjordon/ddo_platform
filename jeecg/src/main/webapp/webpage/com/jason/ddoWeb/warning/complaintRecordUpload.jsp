<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>导入投诉记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  $(function(){
    //查看模式情况下,删除和上传附件功能禁止使用
    if(location.href.indexOf("load=detail")!=-1){
        $(".jeecgDetail").hide();
    }
   });
 
  function uploadFile(data){
      //$("#taskId").val(data.obj.id);
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
  <t:formvalid formid="formobj" dialog="true" layout="table" action="complaintRecordController.do?save" callback="@Override uploadFile">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							投诉清单文件:
						</label>
					</td>
					<td class="value">
						 <t:upload name="fiels" id="file_upload" extend="xls;xlsx" buttonText="添加文件" multi="false" uploader="complaintRecordController.do?importExcel">
            </t:upload>
            <div class="form" id="filediv"></div>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
  </t:formvalid>
 </body>