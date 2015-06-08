package com.jason.ddoWeb.controller.channel;

import java.util.ArrayList;
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
import org.jeecgframework.core.common.model.json.ComboBox;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.jason.ddoWeb.entity.channel.ChannelEntity;
import com.jason.ddoWeb.service.channel.ChannelServiceI;
import com.jason.ddoWeb.util.NumberUtils;

/**   
 * @Title: Controller
 * @Description: 渠道组管理
 * @author zhangdaihao
 * @date 2015-04-14 14:08:20
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/channelController")
public class ChannelController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ChannelController.class);

	@Autowired
	private ChannelServiceI channelService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@RequestMapping(params = "channelSimple")
	public ModelAndView channelSimple(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/channel/channelSimpleList");
	}


	/**
	 * 渠道组管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "channel")
	public ModelAndView channel(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/channel/channelList");
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
	public void datagrid(ChannelEntity channel,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ChannelEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, channel, request.getParameterMap());
		this.channelService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除渠道组管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ChannelEntity channel, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		channel = systemService.getEntity(ChannelEntity.class, channel.getId());
		message = "渠道组管理删除成功";
		channelService.delete(channel);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加渠道组管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ChannelEntity channel, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		//把界面上传过来的元转成分设置到实体中
		channel.setDayLimit(NumberUtils.doubleToLong(channel.getDayLimitDouble()));
		channel.setMonthLimit(NumberUtils.doubleToLong(channel.getMonthLimitDouble()));
		if (StringUtil.isNotEmpty(channel.getId())) {
			message = "渠道组管理更新成功";
			ChannelEntity t = channelService.get(ChannelEntity.class, channel.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(channel, t);
				channelService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "渠道组管理更新失败";
			}
		} else {
			message = "渠道组管理添加成功";
			channelService.save(channel);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 添加渠道组管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "updateState")
	@ResponseBody
	public AjaxJson updateState(ChannelEntity channel, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(channel.getId())) {
			message = "状态更新成功";
			ChannelEntity t = channelService.get(ChannelEntity.class, channel.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(channel, t);
				channelService.updateEntitie(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "状态更新失败";
			}
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 渠道组管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ChannelEntity channel, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(channel.getId())) {
			channel = channelService.getEntity(ChannelEntity.class, channel.getId());
			req.setAttribute("channelPage", channel);
		}
		return new ModelAndView("com/jason/ddoWeb/channel/channel");
	}
	
	@RequestMapping(params = "combox")
	@ResponseBody
	public List<ComboBox> combox(HttpServletRequest request, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ChannelEntity.class);
		List<ChannelEntity> ls = this.channelService.getListByCriteriaQuery(cq, false);
		List<ComboBox> comboxList = new ArrayList<ComboBox>(ls.size());
		for (ChannelEntity channel : ls) {
			ComboBox comboBox = new ComboBox();
			comboBox.setId(channel.getId());
			comboBox.setText(channel.getName());
			comboxList.add(comboBox);
		}
		return comboxList;
	}
}
