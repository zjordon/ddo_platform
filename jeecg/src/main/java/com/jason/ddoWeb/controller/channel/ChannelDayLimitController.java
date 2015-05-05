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

import com.jason.ddoWeb.entity.channel.ChannelDayLimitEntity;
import com.jason.ddoWeb.service.channel.ChannelDayLimitServiceI;

/**   
 * @Title: Controller
 * @Description: 渠道日使用限额
 * @author zhangdaihao
 * @date 2015-03-31 11:36:36
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/channelDayLimitController")
public class ChannelDayLimitController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ChannelDayLimitController.class);

	@Autowired
	private ChannelDayLimitServiceI channelDayLimitService;
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
	 * 渠道日使用限额列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "channelDayLimit")
	public ModelAndView channelDayLimit(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/channel/channelDayLimitList");
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
	public void datagrid(ChannelDayLimitEntity channelDayLimit,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ChannelDayLimitEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, channelDayLimit, request.getParameterMap());
		this.channelDayLimitService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除渠道日使用限额
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ChannelDayLimitEntity channelDayLimit, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		channelDayLimit = systemService.getEntity(ChannelDayLimitEntity.class, channelDayLimit.getId());
		message = "渠道日使用限额删除成功";
		channelDayLimitService.delete(channelDayLimit);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加渠道日使用限额
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ChannelDayLimitEntity channelDayLimit, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(channelDayLimit.getId())) {
			message = "渠道日使用限额更新成功";
			ChannelDayLimitEntity t = channelDayLimitService.get(ChannelDayLimitEntity.class, channelDayLimit.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(channelDayLimit, t);
				channelDayLimitService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "渠道日使用限额更新失败";
			}
		} else {
			message = "渠道日使用限额添加成功";
			channelDayLimitService.save(channelDayLimit);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 渠道日使用限额列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ChannelDayLimitEntity channelDayLimit, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(channelDayLimit.getId())) {
			channelDayLimit = channelDayLimitService.getEntity(ChannelDayLimitEntity.class, channelDayLimit.getId());
			req.setAttribute("channelDayLimitPage", channelDayLimit);
		}
		return new ModelAndView("com/jason/ddoWeb/channel/channelDayLimit");
	}
}
