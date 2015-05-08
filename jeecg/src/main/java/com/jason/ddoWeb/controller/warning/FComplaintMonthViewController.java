package com.jason.ddoWeb.controller.warning;
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

import com.jason.ddoWeb.entity.warning.FComplaintMonthViewEntity;
import com.jason.ddoWeb.service.warning.FComplaintMonthViewServiceI;

/**   
 * @Title: Controller
 * @Description: 全量预警
 * @author zhangdaihao
 * @date 2015-05-08 16:32:14
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/fComplaintMonthViewController")
public class FComplaintMonthViewController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FComplaintMonthViewController.class);

	@Autowired
	private FComplaintMonthViewServiceI fComplaintMonthViewService;
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
	 * 全量预警列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "fComplaintMonthView")
	public ModelAndView fComplaintMonthView(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/warning/fComplaintMonthViewList");
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
	public void datagrid(FComplaintMonthViewEntity fComplaintMonthView,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FComplaintMonthViewEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fComplaintMonthView, request.getParameterMap());
		this.fComplaintMonthViewService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除全量预警
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FComplaintMonthViewEntity fComplaintMonthView, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		fComplaintMonthView = systemService.getEntity(FComplaintMonthViewEntity.class, fComplaintMonthView.getId());
		message = "全量预警删除成功";
		fComplaintMonthViewService.delete(fComplaintMonthView);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加全量预警
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(FComplaintMonthViewEntity fComplaintMonthView, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(fComplaintMonthView.getId())) {
			message = "全量预警更新成功";
			FComplaintMonthViewEntity t = fComplaintMonthViewService.get(FComplaintMonthViewEntity.class, fComplaintMonthView.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(fComplaintMonthView, t);
				fComplaintMonthViewService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "全量预警更新失败";
			}
		} else {
			message = "全量预警添加成功";
			fComplaintMonthViewService.save(fComplaintMonthView);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 全量预警列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FComplaintMonthViewEntity fComplaintMonthView, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(fComplaintMonthView.getId())) {
			fComplaintMonthView = fComplaintMonthViewService.getEntity(FComplaintMonthViewEntity.class, fComplaintMonthView.getId());
			req.setAttribute("fComplaintMonthViewPage", fComplaintMonthView);
		}
		return new ModelAndView("com/jason/ddoWeb/warning/fComplaintMonthView");
	}
}
