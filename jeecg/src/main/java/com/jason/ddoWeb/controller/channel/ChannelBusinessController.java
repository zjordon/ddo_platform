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

import com.jason.ddoWeb.entity.channel.BillBusinessEntity;
import com.jason.ddoWeb.entity.channel.ChannelBusinessEntity;
import com.jason.ddoWeb.entity.channel.ChannelEntity;
import com.jason.ddoWeb.service.channel.ChannelBusinessServiceI;

/**
 * @Title: Controller
 * @Description: 渠道计费点
 * @author zhangdaihao
 * @date 2015-04-17 10:36:42
 * @version V1.0
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/channelBusinessController")
public class ChannelBusinessController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(ChannelBusinessController.class);

	@Autowired
	private ChannelBusinessServiceI channelBusinessService;
	@Autowired
	private SystemService systemService;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@RequestMapping(params = "cBListForChannel")
	public ModelAndView cBListForChannel(HttpServletRequest request) {
		String channelId = request.getParameter("channelId");
		if (!StringUtil.isEmpty(channelId)) {
			request.setAttribute("channelId", channelId);
		}
		// 获取计费业务列表生成界面上需要用到的替换条件
		List<BillBusinessEntity> billBusinessList = this.channelBusinessService
				.getList(BillBusinessEntity.class);
		if (!billBusinessList.isEmpty()) {
			StringBuilder replaceCBCodeBuilder = new StringBuilder();
			StringBuilder replaceCodeBuilder = new StringBuilder();
			for (BillBusinessEntity billBusiness : billBusinessList) {
				replaceCBCodeBuilder.append(billBusiness.getChannelBillCode())
						.append('_').append(billBusiness.getId()).append(',');
				replaceCodeBuilder.append(billBusiness.getCode()).append('_')
						.append(billBusiness.getId()).append(',');
			}
			replaceCBCodeBuilder
					.deleteCharAt(replaceCBCodeBuilder.length() - 1);
			replaceCodeBuilder.deleteCharAt(replaceCBCodeBuilder.length() - 1);
			System.out.println(replaceCBCodeBuilder.toString());
			System.out.println(replaceCodeBuilder.toString());
			request.setAttribute("replaceCBCode",
					replaceCBCodeBuilder.toString());
			request.setAttribute("replaceCode", replaceCodeBuilder.toString());
		}
		return new ModelAndView("com/jason/ddoWeb/channel/cBListForChannel");
	}

	/**
	 * 渠道计费点列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "channelBusiness")
	public ModelAndView channelBusiness(HttpServletRequest request) {

		return new ModelAndView("com/jason/ddoWeb/channel/channelBusinessList");
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
	public void datagrid(ChannelBusinessEntity channelBusiness,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ChannelBusinessEntity.class,
				dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				channelBusiness, request.getParameterMap());
		this.channelBusinessService.getDataGridReturn(cq, true);

		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除渠道计费点
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ChannelBusinessEntity channelBusiness,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		channelBusiness = systemService.getEntity(ChannelBusinessEntity.class,
				channelBusiness.getId());
		message = "渠道计费点删除成功";
		channelBusinessService.delete(channelBusiness);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加渠道计费点
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ChannelBusinessEntity channelBusiness,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(channelBusiness.getId())) {
			message = "渠道计费点更新成功";
			ChannelBusinessEntity t = channelBusinessService.get(
					ChannelBusinessEntity.class, channelBusiness.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(channelBusiness, t);
				channelBusinessService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "渠道计费点更新失败";
			}
		} else {
			message = "渠道计费点添加成功";
			channelBusinessService.save(channelBusiness);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 渠道计费点列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ChannelBusinessEntity channelBusiness,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(channelBusiness.getId())) {
			channelBusiness = channelBusinessService.getEntity(
					ChannelBusinessEntity.class, channelBusiness.getId());
			req.setAttribute("channelBusinessPage", channelBusiness);
		}
		String channelId = channelBusiness.getChannelId();
		if (StringUtil.isNotEmpty(channelId)) {
			ChannelEntity channel = this.channelBusinessService.get(ChannelEntity.class, channelId);
			if (channel != null) {
				req.setAttribute("channel", channel);
			}
		}
		return new ModelAndView("com/jason/ddoWeb/channel/channelBusiness");
	}
}
