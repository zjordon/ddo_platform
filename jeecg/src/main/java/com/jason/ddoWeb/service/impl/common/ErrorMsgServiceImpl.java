/**
 * 
 */
package com.jason.ddoWeb.service.impl.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.web.system.pojo.base.TSAttachment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.common.model.ErrorMsg;
import com.jason.ddoWeb.service.common.ErrorMsgServiceI;

/**
 *  生成错误信息公共service
 * @author jasonzhang
 *
 */
@Service("errorMsgService")
@Transactional
public class ErrorMsgServiceImpl extends CommonServiceImpl implements
		ErrorMsgServiceI {

	/* (non-Javadoc)
	 * @see com.jason.ddoWeb.service.common.ErrorMsgServiceI#saveErrorMsg(java.util.List, javax.servlet.http.HttpServletRequest, java.util.Map)
	 */
	@Override
	public String saveErrorMsg(List<ErrorMsg> errorMsgs,
			HttpServletRequest request, Map<String, Object> attributes) {
		//把错误信息写入文件
		TemplateExportParams params = new TemplateExportParams();
		params.setHeadingRows(1);
		params.setHeadingStartRow(0);
		params.setTemplateUrl("export/template/errormsgtemp.xls");
		Map<String,Object> map = new HashMap<String, Object>();
		Workbook book = ExcelExportUtil.exportExcel(params, ErrorMsg.class, errorMsgs, map);
		String uploadbasepath = ResourceUtil.getConfigByName("uploadpath");
		String path = uploadbasepath + "/";// 文件保存在硬盘的相对路径
		String realPath = request.getSession().getServletContext().getRealPath("/") + "/" + path;// 文件的硬盘真实路径
		File file = new File(realPath);
		if (!file.exists()) {
			file.mkdirs();// 创建根目录
		}
		String errormsgPath = realPath + "/" + ResourceUtil.getConfigByName("errormsgpath");
		file = new File(errormsgPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = System.currentTimeMillis() + ".xls";
		//String filePath = errormsgPath + "/" + fileName;
		try {
			FileOutputStream fos = new FileOutputStream(errormsgPath + "/" + fileName);
			book.write(fos);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		TSAttachment attachment = new TSAttachment();
		attachment.setRealpath("/" + ResourceUtil.getConfigByName("uploadpath") + "/" +  ResourceUtil.getConfigByName("errormsgpath") + "/" + fileName);
		attachment.setAttachmenttitle(fileName);
		attachment.setExtend("xls");
		super.save(attachment);
		StringBuilder builder = new StringBuilder();
		builder.append("commonController.do?viewFile&fileid=").append(attachment.getId());
		return builder.toString();
	}

}
