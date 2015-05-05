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

import com.jason.ddoWeb.entity.warning.ChannelComplaintDayEntity;
import com.jason.ddoWeb.service.warning.ChannelComplaintDayServiceI;

/**   
 * @Title: Controller
 * @Description: 渠道投诉
 * @author zhangdaihao
 * @date 2015-05-04 09:31:54
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/channelComplaintDayController")
public class ChannelComplaintDayController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ChannelComplaintDayController.class);

	@Autowired
	private ChannelComplaintDayServiceI channelComplaintDayService;
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
	 * 渠道投诉列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "channelComplaintDay")
	public ModelAndView channelComplaintDay(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/warning/channelComplaintDayList");
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
	public void datagrid(ChannelComplaintDayEntity channelComplaintDay,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ChannelComplaintDayEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, channelComplaintDay, request.getParameterMap());
		this.channelComplaintDayService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除渠道投诉
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ChannelComplaintDayEntity channelComplaintDay, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		channelComplaintDay = systemService.getEntity(ChannelComplaintDayEntity.class, channelComplaintDay.getId());
		message = "渠道投诉删除成功";
		channelComplaintDayService.delete(channelComplaintDay);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加渠道投诉
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ChannelComplaintDayEntity channelComplaintDay, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(channelComplaintDay.getId())) {
			message = "渠道投诉更新成功";
			ChannelComplaintDayEntity t = channelComplaintDayService.get(ChannelComplaintDayEntity.class, channelComplaintDay.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(channelComplaintDay, t);
				channelComplaintDayService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "渠道投诉更新失败";
			}
		} else {
			message = "渠道投诉添加成功";
			channelComplaintDayService.save(channelComplaintDay);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 渠道投诉列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ChannelComplaintDayEntity channelComplaintDay, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(channelComplaintDay.getId())) {
			channelComplaintDay = channelComplaintDayService.getEntity(ChannelComplaintDayEntity.class, channelComplaintDay.getId());
			//req.setAttribute("channelComplaintDayPage", channelComplaintDay);
		}
		req.setAttribute("channelComplaintDayPage", channelComplaintDay);
		return new ModelAndView("com/jason/ddoWeb/warning/channelComplaintDay");
	}
}
