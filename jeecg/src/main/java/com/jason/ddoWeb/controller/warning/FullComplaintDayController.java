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

import com.jason.ddoWeb.entity.warning.FullComplaintDayEntity;
import com.jason.ddoWeb.service.warning.FullComplaintDayServiceI;

/**   
 * @Title: Controller
 * @Description: 全量预警投诉
 * @author zhangdaihao
 * @date 2015-05-04 11:40:16
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/fullComplaintDayController")
public class FullComplaintDayController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FullComplaintDayController.class);

	@Autowired
	private FullComplaintDayServiceI fullComplaintDayService;
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
	 * 全量预警投诉列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "fullComplaintDay")
	public ModelAndView fullComplaintDay(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/warning/fullComplaintDayList");
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
	public void datagrid(FullComplaintDayEntity fullComplaintDay,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FullComplaintDayEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fullComplaintDay, request.getParameterMap());
		this.fullComplaintDayService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除全量预警投诉
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FullComplaintDayEntity fullComplaintDay, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		fullComplaintDay = systemService.getEntity(FullComplaintDayEntity.class, fullComplaintDay.getId());
		message = "全量预警投诉删除成功";
		fullComplaintDayService.delete(fullComplaintDay);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加全量预警投诉
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(FullComplaintDayEntity fullComplaintDay, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(fullComplaintDay.getId())) {
			message = "全量预警投诉更新成功";
			FullComplaintDayEntity t = fullComplaintDayService.get(FullComplaintDayEntity.class, fullComplaintDay.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(fullComplaintDay, t);
				fullComplaintDayService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "全量预警投诉更新失败";
			}
		} else {
			message = "全量预警投诉添加成功";
			fullComplaintDayService.save(fullComplaintDay);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 全量预警投诉列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FullComplaintDayEntity fullComplaintDay, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(fullComplaintDay.getId())) {
			fullComplaintDay = fullComplaintDayService.getEntity(FullComplaintDayEntity.class, fullComplaintDay.getId());
			req.setAttribute("fullComplaintDayPage", fullComplaintDay);
		}
		return new ModelAndView("com/jason/ddoWeb/warning/fullComplaintDay");
	}
}
