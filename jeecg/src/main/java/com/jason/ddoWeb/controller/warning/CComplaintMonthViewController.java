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

import com.jason.ddoWeb.entity.warning.CComplaintMonthViewEntity;
import com.jason.ddoWeb.service.warning.CComplaintMonthViewServiceI;

/**   
 * @Title: Controller
 * @Description: 全量预警按月
 * @author zhangdaihao
 * @date 2015-05-08 16:47:09
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/cComplaintMonthViewController")
public class CComplaintMonthViewController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CComplaintMonthViewController.class);

	@Autowired
	private CComplaintMonthViewServiceI cComplaintMonthViewService;
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
	 * 全量预警按月列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "cComplaintMonthView")
	public ModelAndView cComplaintMonthView(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/warning/cComplaintMonthViewList");
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
	public void datagrid(CComplaintMonthViewEntity cComplaintMonthView,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CComplaintMonthViewEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cComplaintMonthView, request.getParameterMap());
		this.cComplaintMonthViewService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除全量预警按月
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(CComplaintMonthViewEntity cComplaintMonthView, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		cComplaintMonthView = systemService.getEntity(CComplaintMonthViewEntity.class, cComplaintMonthView.getId());
		message = "全量预警按月删除成功";
		cComplaintMonthViewService.delete(cComplaintMonthView);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加全量预警按月
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(CComplaintMonthViewEntity cComplaintMonthView, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(cComplaintMonthView.getId())) {
			message = "全量预警按月更新成功";
			CComplaintMonthViewEntity t = cComplaintMonthViewService.get(CComplaintMonthViewEntity.class, cComplaintMonthView.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(cComplaintMonthView, t);
				cComplaintMonthViewService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "全量预警按月更新失败";
			}
		} else {
			message = "全量预警按月添加成功";
			cComplaintMonthViewService.save(cComplaintMonthView);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 全量预警按月列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(CComplaintMonthViewEntity cComplaintMonthView, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cComplaintMonthView.getId())) {
			cComplaintMonthView = cComplaintMonthViewService.getEntity(CComplaintMonthViewEntity.class, cComplaintMonthView.getId());
			req.setAttribute("cComplaintMonthViewPage", cComplaintMonthView);
		}
		return new ModelAndView("com/jason/ddoWeb/warning/cComplaintMonthView");
	}
}
