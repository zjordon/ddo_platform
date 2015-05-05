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

import com.jason.ddoWeb.entity.msg.NeedRepeatReportEntity;
import com.jason.ddoWeb.service.msg.NeedRepeatReportServiceI;

/**   
 * @Title: Controller
 * @Description: 需要重新下发的状态报告
 * @author zhangdaihao
 * @date 2015-04-07 16:45:49
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/needRepeatReportController")
public class NeedRepeatReportController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(NeedRepeatReportController.class);

	@Autowired
	private NeedRepeatReportServiceI needRepeatReportService;
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
	 * 需要重新下发的状态报告列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "needRepeatReport")
	public ModelAndView needRepeatReport(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/msg/needRepeatReportList");
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
	public void datagrid(NeedRepeatReportEntity needRepeatReport,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(NeedRepeatReportEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, needRepeatReport, request.getParameterMap());
		this.needRepeatReportService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除需要重新下发的状态报告
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(NeedRepeatReportEntity needRepeatReport, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		needRepeatReport = systemService.getEntity(NeedRepeatReportEntity.class, needRepeatReport.getId());
		message = "需要重新下发的状态报告删除成功";
		needRepeatReportService.delete(needRepeatReport);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加需要重新下发的状态报告
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(NeedRepeatReportEntity needRepeatReport, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(needRepeatReport.getId())) {
			message = "需要重新下发的状态报告更新成功";
			NeedRepeatReportEntity t = needRepeatReportService.get(NeedRepeatReportEntity.class, needRepeatReport.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(needRepeatReport, t);
				needRepeatReportService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "需要重新下发的状态报告更新失败";
			}
		} else {
			message = "需要重新下发的状态报告添加成功";
			needRepeatReportService.save(needRepeatReport);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 需要重新下发的状态报告列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(NeedRepeatReportEntity needRepeatReport, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(needRepeatReport.getId())) {
			needRepeatReport = needRepeatReportService.getEntity(NeedRepeatReportEntity.class, needRepeatReport.getId());
			req.setAttribute("needRepeatReportPage", needRepeatReport);
		}
		return new ModelAndView("com/jason/ddoWeb/msg/needRepeatReport");
	}
}
