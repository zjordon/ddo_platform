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

import com.jason.ddoWeb.entity.warning.FullComplaintMonthEntity;
import com.jason.ddoWeb.service.warning.FullComplaintMonthServiceI;

/**   
 * @Title: Controller
 * @Description: 全量投诉按月
 * @author zhangdaihao
 * @date 2015-05-08 16:30:31
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/fullComplaintMonthController")
public class FullComplaintMonthController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FullComplaintMonthController.class);

	@Autowired
	private FullComplaintMonthServiceI fullComplaintMonthService;
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
	 * 全量投诉按月列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "fullComplaintMonth")
	public ModelAndView fullComplaintMonth(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/warning/fullComplaintMonthList");
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
	public void datagrid(FullComplaintMonthEntity fullComplaintMonth,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FullComplaintMonthEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fullComplaintMonth, request.getParameterMap());
		this.fullComplaintMonthService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除全量投诉按月
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FullComplaintMonthEntity fullComplaintMonth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		fullComplaintMonth = systemService.getEntity(FullComplaintMonthEntity.class, fullComplaintMonth.getId());
		message = "全量投诉按月删除成功";
		fullComplaintMonthService.delete(fullComplaintMonth);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加全量投诉按月
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(FullComplaintMonthEntity fullComplaintMonth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(fullComplaintMonth.getId())) {
			message = "全量投诉按月更新成功";
			FullComplaintMonthEntity t = fullComplaintMonthService.get(FullComplaintMonthEntity.class, fullComplaintMonth.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(fullComplaintMonth, t);
				fullComplaintMonthService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "全量投诉按月更新失败";
			}
		} else {
			message = "全量投诉按月添加成功";
			fullComplaintMonthService.save(fullComplaintMonth);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 全量投诉按月列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FullComplaintMonthEntity fullComplaintMonth, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(fullComplaintMonth.getId())) {
			fullComplaintMonth = fullComplaintMonthService.getEntity(FullComplaintMonthEntity.class, fullComplaintMonth.getId());
			req.setAttribute("fullComplaintMonthPage", fullComplaintMonth);
		}
		return new ModelAndView("com/jason/ddoWeb/warning/fullComplaintMonth");
	}
}
