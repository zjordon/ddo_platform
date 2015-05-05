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

import com.jason.ddoWeb.entity.warning.ChannelComplaintEntity;
import com.jason.ddoWeb.service.warning.ChannelComplaintServiceI;

/**   
 * @Title: Controller
 * @Description: 渠道投诉
 * @author zhangdaihao
 * @date 2015-05-04 09:29:39
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/channelComplaintController")
public class ChannelComplaintController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ChannelComplaintController.class);

	@Autowired
	private ChannelComplaintServiceI channelComplaintService;
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
	@RequestMapping(params = "channelComplaint")
	public ModelAndView channelComplaint(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/warning/channelComplaintList");
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
	public void datagrid(ChannelComplaintEntity channelComplaint,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ChannelComplaintEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, channelComplaint, request.getParameterMap());
		this.channelComplaintService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除渠道投诉
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ChannelComplaintEntity channelComplaint, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		channelComplaint = systemService.getEntity(ChannelComplaintEntity.class, channelComplaint.getId());
		message = "渠道投诉删除成功";
		channelComplaintService.delete(channelComplaint);
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
	public AjaxJson save(ChannelComplaintEntity channelComplaint, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(channelComplaint.getId())) {
			message = "渠道投诉更新成功";
			ChannelComplaintEntity t = channelComplaintService.get(ChannelComplaintEntity.class, channelComplaint.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(channelComplaint, t);
				channelComplaintService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "渠道投诉更新失败";
			}
		} else {
			message = "渠道投诉添加成功";
			channelComplaintService.save(channelComplaint);
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
	public ModelAndView addorupdate(ChannelComplaintEntity channelComplaint, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(channelComplaint.getId())) {
			channelComplaint = channelComplaintService.getEntity(ChannelComplaintEntity.class, channelComplaint.getId());
			req.setAttribute("channelComplaintPage", channelComplaint);
		}
		return new ModelAndView("com/jason/ddoWeb/warning/channelComplaint");
	}
}
