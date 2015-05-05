package com.jason.ddoWeb.controller.msg;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.jason.ddoWeb.entity.msg.RepeatReportRecordEntity;
import com.jason.ddoWeb.service.msg.RepeatReportRecordServiceI;

/**   
 * @Title: Controller
 * @Description: 重新下发状态报告记录
 * @author zhangdaihao
 * @date 2015-04-07 16:46:27
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/repeatReportRecordController")
public class RepeatReportRecordController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RepeatReportRecordController.class);

	@Autowired
	private RepeatReportRecordServiceI repeatReportRecordService;
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
	 * 重新下发状态报告记录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "repeatReportRecord")
	public ModelAndView repeatReportRecord(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/msg/repeatReportRecordList");
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
	public void datagrid(RepeatReportRecordEntity repeatReportRecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(RepeatReportRecordEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, repeatReportRecord, request.getParameterMap());
		this.repeatReportRecordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除重新下发状态报告记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(RepeatReportRecordEntity repeatReportRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		repeatReportRecord = systemService.getEntity(RepeatReportRecordEntity.class, repeatReportRecord.getId());
		message = "重新下发状态报告记录删除成功";
		repeatReportRecordService.delete(repeatReportRecord);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加重新下发状态报告记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(RepeatReportRecordEntity repeatReportRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(repeatReportRecord.getId())) {
			message = "重新下发状态报告记录更新成功";
			RepeatReportRecordEntity t = repeatReportRecordService.get(RepeatReportRecordEntity.class, repeatReportRecord.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(repeatReportRecord, t);
				repeatReportRecordService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "重新下发状态报告记录更新失败";
			}
		} else {
			message = "重新下发状态报告记录添加成功";
			repeatReportRecordService.save(repeatReportRecord);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 重新下发状态报告记录列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(RepeatReportRecordEntity repeatReportRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(repeatReportRecord.getId())) {
			repeatReportRecord = repeatReportRecordService.getEntity(RepeatReportRecordEntity.class, repeatReportRecord.getId());
			req.setAttribute("repeatReportRecordPage", repeatReportRecord);
		}
		return new ModelAndView("com/jason/ddoWeb/msg/repeatReportRecord");
	}
}
