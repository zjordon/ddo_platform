package com.jason.ddoWeb.controller.warning;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.jason.ddoWeb.entity.warning.ComplaintRecordEntity;
import com.jason.ddoWeb.service.warning.ComplaintRecordServiceI;

/**   
 * @Title: Controller
 * @Description: 投诉记录
 * @author zhangdaihao
 * @date 2015-06-04 14:55:45
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/complaintRecordController")
public class ComplaintRecordController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ComplaintRecordController.class);

	@Autowired
	private ComplaintRecordServiceI complaintRecordService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 投诉记录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "complaintRecord")
	public ModelAndView complaintRecord(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/warning/complaintRecordList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(ComplaintRecordEntity complaintRecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ComplaintRecordEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, complaintRecord, request.getParameterMap());
		this.complaintRecordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除投诉记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ComplaintRecordEntity complaintRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		complaintRecord = systemService.getEntity(ComplaintRecordEntity.class, complaintRecord.getId());
		message = "投诉记录删除成功";
		complaintRecordService.delete(complaintRecord);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加投诉记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ComplaintRecordEntity complaintRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
//		if (StringUtil.isNotEmpty(complaintRecord.getId())) {
//			message = "投诉记录更新成功";
//			ComplaintRecordEntity t = complaintRecordService.get(ComplaintRecordEntity.class, complaintRecord.getId());
//			try {
//				MyBeanUtils.copyBeanNotNull2Bean(complaintRecord, t);
//				complaintRecordService.saveOrUpdate(t);
//				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
//			} catch (Exception e) {
//				e.printStackTrace();
//				message = "投诉记录更新失败";
//			}
//		} else {
//			message = "投诉记录添加成功";
//			complaintRecordService.save(complaintRecord);
//			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
//		}
		j.setMsg("投诉记录导入成功");
		return j;
	}

	/**
	 * 投诉记录列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ComplaintRecordEntity complaintRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(complaintRecord.getId())) {
			complaintRecord = complaintRecordService.getEntity(ComplaintRecordEntity.class, complaintRecord.getId());
			req.setAttribute("complaintRecordPage", complaintRecord);
		}
		return new ModelAndView("com/jason/ddoWeb/warning/complaintRecord");
	}
	
	/**
	 * 批量导入号码
	 * @param smTask
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/jason/ddoWeb/warning/complaintRecordUpload");
	}
	
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		List<ComplaintRecordEntity> allRecords = new ArrayList<ComplaintRecordEntity>();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(0);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<ComplaintRecordEntity> listBlackList =  ExcelImportUtil.importExcelByIs(file.getInputStream(),ComplaintRecordEntity.class,params);
				allRecords.addAll(listBlackList);
			} catch (Exception e) {
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
//		for (ComplaintRecordEntity entity : allRecords) {
//			System.out.println(entity.getComplaintDate() + "," + entity.getMsisdn() + "," + entity.getProvice() + "," + entity.getCity());
//		}
		if (allRecords.isEmpty()) {
			j.setMsg("号码清单为空或格式错误");
		} else {
			this.complaintRecordService.saveList(allRecords);
			message = "投诉记录导入成功";
			j.setMsg(message);
			systemService.addLog(message, Globals.Log_Type_UPLOAD, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}
}
