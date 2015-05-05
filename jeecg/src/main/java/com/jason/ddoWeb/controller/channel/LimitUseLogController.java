package com.jason.ddoWeb.controller.channel;
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

import com.jason.ddoWeb.entity.channel.LimitUseLogEntity;
import com.jason.ddoWeb.service.channel.LimitUseLogServiceI;

/**   
 * @Title: Controller
 * @Description: 渠道限额使用流水
 * @author zhangdaihao
 * @date 2015-03-30 09:15:42
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/limitUseLogController")
public class LimitUseLogController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LimitUseLogController.class);

	@Autowired
	private LimitUseLogServiceI limitUseLogService;
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
	 * 渠道限额使用流水列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "limitUseLog")
	public ModelAndView limitUseLog(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/channel/limitUseLogList");
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
	public void datagrid(LimitUseLogEntity limitUseLog,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LimitUseLogEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, limitUseLog, request.getParameterMap());
		this.limitUseLogService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除渠道限额使用流水
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(LimitUseLogEntity limitUseLog, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		limitUseLog = systemService.getEntity(LimitUseLogEntity.class, limitUseLog.getId());
		message = "渠道限额使用流水删除成功";
		limitUseLogService.delete(limitUseLog);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加渠道限额使用流水
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(LimitUseLogEntity limitUseLog, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(limitUseLog.getId())) {
			message = "渠道限额使用流水更新成功";
			LimitUseLogEntity t = limitUseLogService.get(LimitUseLogEntity.class, limitUseLog.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(limitUseLog, t);
				limitUseLogService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "渠道限额使用流水更新失败";
			}
		} else {
			message = "渠道限额使用流水添加成功";
			limitUseLogService.save(limitUseLog);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 渠道限额使用流水列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(LimitUseLogEntity limitUseLog, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(limitUseLog.getId())) {
			limitUseLog = limitUseLogService.getEntity(LimitUseLogEntity.class, limitUseLog.getId());
			req.setAttribute("limitUseLogPage", limitUseLog);
		}
		return new ModelAndView("com/jason/ddoWeb/channel/limitUseLog");
	}
}
