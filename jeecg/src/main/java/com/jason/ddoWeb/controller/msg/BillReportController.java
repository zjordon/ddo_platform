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

import com.jason.ddoWeb.entity.msg.BillReportEntity;
import com.jason.ddoWeb.service.msg.BillReportServiceI;

/**   
 * @Title: Controller
 * @Description: 计费状态报告
 * @author zhangdaihao
 * @date 2015-04-07 16:44:56
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/billReportController")
public class BillReportController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BillReportController.class);

	@Autowired
	private BillReportServiceI billReportService;
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
	 * 计费状态报告列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "billReport")
	public ModelAndView billReport(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/msg/billReportList");
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
	public void datagrid(BillReportEntity billReport,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BillReportEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, billReport, request.getParameterMap());
		this.billReportService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除计费状态报告
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(BillReportEntity billReport, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		billReport = systemService.getEntity(BillReportEntity.class, billReport.getId());
		message = "计费状态报告删除成功";
		billReportService.delete(billReport);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加计费状态报告
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(BillReportEntity billReport, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(billReport.getId())) {
			message = "计费状态报告更新成功";
			BillReportEntity t = billReportService.get(BillReportEntity.class, billReport.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(billReport, t);
				billReportService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "计费状态报告更新失败";
			}
		} else {
			message = "计费状态报告添加成功";
			billReportService.save(billReport);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 计费状态报告列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(BillReportEntity billReport, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(billReport.getId())) {
			billReport = billReportService.getEntity(BillReportEntity.class, billReport.getId());
			req.setAttribute("billReportPage", billReport);
		}
		return new ModelAndView("com/jason/ddoWeb/msg/billReport");
	}
}
