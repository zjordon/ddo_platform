package com.jason.ddoWeb.controller.event;
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

import com.jason.ddoWeb.entity.event.EventEntity;
import com.jason.ddoWeb.service.event.EventServiceI;

/**   
 * @Title: Controller
 * @Description: 事件
 * @author zhangdaihao
 * @date 2015-04-14 15:41:52
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/eventController")
public class EventController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EventController.class);

	@Autowired
	private EventServiceI eventService;
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
	 * 事件列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "event")
	public ModelAndView event(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/event/eventList");
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
	public void datagrid(EventEntity event,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EventEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, event, request.getParameterMap());
		this.eventService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除事件
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(EventEntity event, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		event = systemService.getEntity(EventEntity.class, event.getId());
		message = "事件删除成功";
		eventService.delete(event);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加事件
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(EventEntity event, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(event.getId())) {
			message = "事件更新成功";
			EventEntity t = eventService.get(EventEntity.class, event.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(event, t);
				eventService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "事件更新失败";
			}
		} else {
			message = "事件添加成功";
			eventService.save(event);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 事件列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(EventEntity event, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(event.getId())) {
			event = eventService.getEntity(EventEntity.class, event.getId());
			req.setAttribute("eventPage", event);
		}
		return new ModelAndView("com/jason/ddoWeb/event/event");
	}
}
