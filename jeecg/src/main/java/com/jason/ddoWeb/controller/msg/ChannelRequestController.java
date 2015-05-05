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

import com.jason.ddoWeb.entity.msg.ChannelRequestEntity;
import com.jason.ddoWeb.service.msg.ChannelRequestServiceI;

/**   
 * @Title: Controller
 * @Description: 短信任务
 * @author zhangdaihao
 * @date 2015-03-30 08:54:26
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/channelRequestController")
public class ChannelRequestController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ChannelRequestController.class);

	@Autowired
	private ChannelRequestServiceI channelRequestService;
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
	 * 短信任务列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "channelRequest")
	public ModelAndView channelRequest(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/msg/channelRequestList");
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
	public void datagrid(ChannelRequestEntity channelRequest,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ChannelRequestEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, channelRequest, request.getParameterMap());
		this.channelRequestService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除短信任务
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ChannelRequestEntity channelRequest, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		channelRequest = systemService.getEntity(ChannelRequestEntity.class, channelRequest.getId());
		message = "短信任务删除成功";
		channelRequestService.delete(channelRequest);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加短信任务
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ChannelRequestEntity channelRequest, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(channelRequest.getId())) {
			message = "短信任务更新成功";
			ChannelRequestEntity t = channelRequestService.get(ChannelRequestEntity.class, channelRequest.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(channelRequest, t);
				channelRequestService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "短信任务更新失败";
			}
		} else {
			message = "短信任务添加成功";
			channelRequestService.save(channelRequest);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 短信任务列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ChannelRequestEntity channelRequest, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(channelRequest.getId())) {
			channelRequest = channelRequestService.getEntity(ChannelRequestEntity.class, channelRequest.getId());
			req.setAttribute("channelRequestPage", channelRequest);
		}
		return new ModelAndView("com/jason/ddoWeb/msg/channelRequest");
	}
}
