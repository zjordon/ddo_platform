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

import com.jason.ddoWeb.entity.channel.ChannelMonthLimitEntity;
import com.jason.ddoWeb.service.channel.ChannelMonthLimitServiceI;

/**   
 * @Title: Controller
 * @Description: 渠道月使用限额
 * @author zhangdaihao
 * @date 2015-03-31 11:36:56
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/channelMonthLimitController")
public class ChannelMonthLimitController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ChannelMonthLimitController.class);

	@Autowired
	private ChannelMonthLimitServiceI channelMonthLimitService;
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
	 * 渠道月使用限额列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "channelMonthLimit")
	public ModelAndView channelMonthLimit(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/channel/channelMonthLimitList");
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
	public void datagrid(ChannelMonthLimitEntity channelMonthLimit,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ChannelMonthLimitEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, channelMonthLimit, request.getParameterMap());
		this.channelMonthLimitService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除渠道月使用限额
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ChannelMonthLimitEntity channelMonthLimit, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		channelMonthLimit = systemService.getEntity(ChannelMonthLimitEntity.class, channelMonthLimit.getId());
		message = "渠道月使用限额删除成功";
		channelMonthLimitService.delete(channelMonthLimit);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加渠道月使用限额
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ChannelMonthLimitEntity channelMonthLimit, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(channelMonthLimit.getId())) {
			message = "渠道月使用限额更新成功";
			ChannelMonthLimitEntity t = channelMonthLimitService.get(ChannelMonthLimitEntity.class, channelMonthLimit.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(channelMonthLimit, t);
				channelMonthLimitService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "渠道月使用限额更新失败";
			}
		} else {
			message = "渠道月使用限额添加成功";
			channelMonthLimitService.save(channelMonthLimit);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 渠道月使用限额列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ChannelMonthLimitEntity channelMonthLimit, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(channelMonthLimit.getId())) {
			channelMonthLimit = channelMonthLimitService.getEntity(ChannelMonthLimitEntity.class, channelMonthLimit.getId());
			req.setAttribute("channelMonthLimitPage", channelMonthLimit);
		}
		return new ModelAndView("com/jason/ddoWeb/channel/channelMonthLimit");
	}
}
